// -*- mode: c++ -*-

#ifndef MEDIDOR_H_INCLUIDO
#define MEDIDOR_H_INCLUIDO

// ------------------------------------------------------
// ------------------------------------------------------
class Medidor {

  // .....................................................
  // .....................................................
private:

public:

  // .....................................................
  // constructor
  // .....................................................
  Medidor(  ) {
  } // ()

  // .....................................................
  // .....................................................
  void iniciarMedidor() {
	  Serial1.begin(9600);
  } // ()
  
  void resetSensor(){
     Serial.print("entrando en calibracion");
     String a;
     Serial1.println("R");delay(1);
      while(Serial1.available()) {
         a= Serial1.readString();// read the incoming data as string
         Serial.println(a);
       }
      for (int i = 0; i<12000; i++) {
          while(!Serial1.available()) { }
          Serial.print(Serial1.read());
          delay(1);
      }
      Serial.println("FIN");
  }
  
  void calibradoZero(){
     Serial.print("entrando en calibracion");
     String a;
     Serial1.println("Z");delay(1);
      while(Serial1.available()) {
         a= Serial1.readString();// read the incoming data as string
         Serial.println(a);
       }
      for (int i = 0; i<12000; i++) {
          while(!Serial1.available()) { }
          Serial.print(Serial1.read());
          delay(1);
      }
      Serial.println("FIN");
  }

  // .....................................................
  // .....................................................
  void medirNO2 (int sensorData[11]) {
      //int sensorData [11];
      Serial1.print('\r'); // Inicia una lectura del sensor. Ahora hay que espera a que nos envíe algo de vuelta!	        
      for (int i = 0; i<11; i++) {
        while(!Serial1.available()) { }
        sensorData[i] = Serial1.parseInt();
      }
      //return sensorData;
  } // ()


  // .....................................................
  // .....................................................
  int medirTemperatura() {
	return -12; // qué frío !
  } // ()
	
}; // class

// ------------------------------------------------------
// ------------------------------------------------------
// ------------------------------------------------------
// ------------------------------------------------------
#endif
