// -*- mode: c++ -*-

// --------------------------------------------------------------
// RAUL SANTOS LOPEZ     26/11/2020
// --------------------------------------------------------------

#ifndef BATTERY_H_INCLUIDO
#define BATTERY_H_INCLUIDO
#include <Arduino.h>
#include "Arduino_nRF5x_lowPower.h" // LowPower Library for nRF5x

// ----------------------------------------------------------
#define PIN_VBAT   A3   
#define PIN_MOSFET   19
#define VBAT_MV_PER_LSB   (0.73242188F)   // 3.0V ADC range and 12-bit ADC resolution = 3000mV/4096
#define VBAT_DIVIDER      (0.5F)          // 10K + 10K voltage divider on VBAT
#define VBAT_DIVIDER_COMP (2.0F)          // Compensation factor for the VBAT divider
#define REAL_VBAT_MV_PER_LSB (VBAT_DIVIDER_COMP * VBAT_MV_PER_LSB)

// ----------------------------------------------------------
// ----------------------------------------------------------
class BATTERY {
private:

  Arduino_nRF5x_lowPower low;
  uint32_t vbat_pin = PIN_VBAT; 
  
  // readVBAT -> float
  float readVBAT(void) {
      low.enableDCDC();
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
      //Disable Mosfet because the current flow to ground
      //Serial.println(raw * REAL_VBAT_MV_PER_LSB);
      stopMosfet();
      low.disableDCDC();
      // Convert the raw value to compensated mv, taking the resistor-
      // divider into account (providing the actual LIPO voltage)
      // ADC range is 0..3000mV and resolution is 12-bit (0..4095)
      return raw * REAL_VBAT_MV_PER_LSB;
  }//()
  
  /**
   * Asymmetric sigmoidal approximation
   * https://www.desmos.com/calculator/oyhpsu8jnw
   *
   * c - c / [1 + (k*x/v)^4.5]^3
   */
  uint8_t asigmoidal(uint16_t voltage, uint16_t minVoltage, uint16_t maxVoltage) {
    uint8_t result = 101 - (101 / pow(1 + pow(1.33 * (voltage - minVoltage)/(maxVoltage - minVoltage) ,4.5), 3));
    return result >= 100 ? 100 : result;
  }

  // float -> mvToPercent-> uint8_T
  // y = -2E-08x6 + 6E-06x5 - 0.0007x4 + 0.0365x3 - 1.2132x2 + 31.439x + 3199.3

  uint8_t mvToPercent(float mvolts) {
      //float res = -0.000000000000001 * pow (x, 6) + 0.00000000002 * pow (x, 5) - 0.0000002* pow (x, 4) + 0.0013 * pow (x, 3) - 3.9195 * pow (x, 2) + 6175 * x - 0.000004;
      uint8_t res = asigmoidal(mvolts, 3200,4200);
      //Serial.println(res);
      /*
      if(mvolts<3300)
        return 0;
    
      if(mvolts <3600) {
        mvolts -= 3300;
        return mvolts/30;
      }
      mvolts -= 3600;
      float res = 14.7 + (mvolts * 0.15F );
      if (res > 100){
        return 100;
      }*/
      return res;  // thats mvolts /6.66666666
  }



     /* nrf_gpio_cfg(PIN_VBAT,
    NRF_GPIO_PIN_DIR_OUTPUT,
    NRF_GPIO_PIN_INPUT_DISCONNECT,
    NRF_GPIO_PIN_NOPULL,
    NRF_GPIO_PIN_S0D1,*/

public:

  void stopMosfet(void){
    digitalWrite(PIN_MOSFET, HIGH);
    //nrf_gpio_cfg(vbat_pin, NRF_GPIO_PIN_INPUT_DISCONNECT);
  }

  // .........................................................
  // .........................................................
  BATTERY () {
    //nrf_gpio_cfg(PIN_VBAT, NRF_GPIO_PIN_NOPULL);
    pinMode(PIN_MOSFET, OUTPUT);
    digitalWrite(PIN_MOSFET, LOW);
  }

  // .........................................................
  // obtenerProcerntaje -> uint8_t
  uint8_t obtenerPorcentaje(){
    float vbat_mv = 0;
    int i;
    for(i=0;i<=10;i++){
      vbat_mv = vbat_mv + readVBAT();
     delay(1);
    }
    //float res = vbat_mv/i;
    //Serial.println(res);
    // Convert from raw mv to percentage (based on LIPO chemistry)
    return mvToPercent(vbat_mv/i);
  }

  float obtenerVolt(){
    float vbat_mv = 0;
    int i;
    for(i=0;i<=10;i++){
      vbat_mv = vbat_mv + readVBAT();
     delay(1);
    }
    return vbat_mv/i;
  }

  // .........................................................
  // .........................................................

}; // class

// ----------------------------------------------------------
// ----------------------------------------------------------
// ----------------------------------------------------------
// ----------------------------------------------------------
#endif
