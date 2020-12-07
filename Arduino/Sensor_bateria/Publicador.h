// -*- mode: c++ -*-

// --------------------------------------------------------------
// Jordi Bataller i Mascarell
// --------------------------------------------------------------

#ifndef PUBLICADOR_H_INCLUIDO
#define PUBLICADOR_H_INCLUIDO

// --------------------------------------------------------------
// --------------------------------------------------------------
class Publicador {

  // ............................................................
  // ............................................................
private:

  uint8_t beaconUUID[16] = { 
	'T', 'E', 'A', 'M', '-', 'G', 'T', 'I', 
	'-', '1', 'N', 'O', '2', '-', '3', 'A'
	};


  // ............................................................
  // ............................................................
public:
  EmisoraBLE laEmisora {
	  buffer1, //  nombre emisora
	  0xF11F, // fabricanteID
	  4 // txPower
	  };
  
  const int RSSI = -53; // por poner algo, de momento no lo uso
  
  String buffer1 ;
  // ............................................................
  // ............................................................
public:

    void setName(String nombre ){
      buffer1 = nombre;
      //Serial.println(buffer1);
    }

  // ............................................................
  // ............................................................
  enum MedicionesID  {
  	NO2 = 1,
  	TEMPERATURA = 2,
  	RUIDO = 3,
    BATTERY = 4
  };

  // ............................................................
  // ............................................................
  Publicador( ) {
	// ATENCION: no hacerlo aquí. (*this).laEmisora.encenderEmisora();
	// Pondremos un método para llamarlo desde el setup() más tarde
  } // ()

  // ............................................................
  // ............................................................
  void encenderEmisora() {
	  (*this).laEmisora.encenderEmisora();
  } // ()

  // ............................................................
  // ............................................................
  void publicarNO2( int16_t valorNO2, uint8_t contador,
					long tiempoEspera, uint8_t nivel_bat ) {

	//
	// 1. empezamos anuncio
	//
  
	//uint16_t major =  (MedicionesID::NO2 << 8) + contador;
  uint16_t major =  (nivel_bat << 8) + contador;
 
	(*this).laEmisora.emitirAnuncioIBeacon( (*this).beaconUUID, 
											major,
											valorNO2, // minor
											(*this).RSSI // rssi
                      ,buffer1
									);


  /*
	Globales::elPuerto.escribir( "   publicarNO2(): valor=" );
	Globales::elPuerto.escribir( valorNO2 );
	Globales::elPuerto.escribir( "   contador=" );
	Globales::elPuerto.escribir( contador );
	Globales::elPuerto.escribir( "   todo="  );
	Globales::elPuerto.escribir( major );
	Globales::elPuerto.escribir( "\n" );
	*/

	//
	// 2. esperamos el tiempo que nos digan
	//
	esperar( tiempoEspera );

	//
	// 3. paramos anuncio
	//
	(*this).laEmisora.detenerAnuncio();
  } // ()

  // ............................................................
  // ............................................................
  void publicarTemperatura( int16_t valorTemperatura,
							uint8_t contador, long tiempoEspera ) {

	uint16_t major = (MedicionesID::TEMPERATURA << 8) + contador;
 /*
	(*this).laEmisora.emitirAnuncioIBeacon( (*this).beaconUUID, 
											major,
											valorTemperatura, // minor
											(*this).RSSI // rssi
									);
	esperar( tiempoEspera );
  */

	(*this).laEmisora.detenerAnuncio();
  } // ()
	
}; // class

// --------------------------------------------------------------
// --------------------------------------------------------------
// --------------------------------------------------------------
// --------------------------------------------------------------
#endif
