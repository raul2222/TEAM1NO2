// -*- mode: c++ -*-

// --------------------------------------------------------------
// RAUL SANTOS LOPEZ     26/11/2020
// --------------------------------------------------------------

#ifndef BATTERY_H_INCLUIDO
#define BATTERY_H_INCLUIDO
#include <Arduino.h>

// ----------------------------------------------------------
#define PIN_VBAT   A3   // this is just a mock read, we'll use the light sensor, so we can run the test
#define PIN_MOSFET   19
#define VBAT_MV_PER_LSB   (0.73242188F)   // 3.0V ADC range and 12-bit ADC resolution = 3000mV/4096
#define VBAT_DIVIDER      (0.5F)          // 150K + 150K voltage divider on VBAT
#define VBAT_DIVIDER_COMP (2.0F)          // Compensation factor for the VBAT divider
#define REAL_VBAT_MV_PER_LSB (VBAT_DIVIDER_COMP * VBAT_MV_PER_LSB)

// ----------------------------------------------------------
// ----------------------------------------------------------
class BATTERY {
private:
  uint32_t vbat_pin = PIN_VBAT; 

  float readVBAT(void) {
      float raw;
    
      // Set the analog reference to 3.0V (default = 3.6V)
      analogReference(AR_INTERNAL_3_0);
    
      // Set the resolution to 12-bit (0..4095)
      analogReadResolution(12); // Can be 8, 10, 12 or 14
      digitalWrite(PIN_MOSFET, HIGH);
      // Let the ADC settle
      delay(1);
    
      // Get the raw 12-bit, 0..3000mV ADC value
      raw = analogRead(vbat_pin);
      digitalWrite(PIN_MOSFET, LOW);
      // Set the ADC back to the default settings
      analogReference(AR_DEFAULT);
      analogReadResolution(10);
      // Convert the raw value to compensated mv, taking the resistor-
      // divider into account (providing the actual LIPO voltage)
      // ADC range is 0..3000mV and resolution is 12-bit (0..4095)
      return raw * REAL_VBAT_MV_PER_LSB;
  }


  uint8_t mvToPercent(float mvolts) {
    if(mvolts<3300)
      return 0;
  
    if(mvolts <3600) {
      mvolts -= 3300;
      return mvolts/30;
    }
    mvolts -= 3600;
    uint8_t res = 14.7 + (mvolts * 0.15F );
    if (res > 100){
      return 100;
    }
    return res;  // thats mvolts /6.66666666
  }

public:

  // .........................................................
  // .........................................................
  BATTERY () {
    pinMode(PIN_MOSFET, OUTPUT);
    digitalWrite(PIN_MOSFET, LOW);
  }

  // .........................................................
  // .........................................................
  uint8_t obtenerPorcentaje(){
    float vbat_mv = 0;
    float vbat_temp = 0;
    for(int i=0;i<=20;i++){
      vbat_temp = readVBAT();
      if(vbat_temp > vbat_mv){
        vbat_mv = vbat_temp;
      }
     delay(2);
    }

    // Convert from raw mv to percentage (based on LIPO chemistry)
    return mvToPercent(vbat_mv);
  }

  // .........................................................
  // .........................................................

}; // class

// ----------------------------------------------------------
// ----------------------------------------------------------
// ----------------------------------------------------------
// ----------------------------------------------------------
#endif
