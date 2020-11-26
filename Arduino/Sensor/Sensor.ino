// -*-c++-*-

// --------------------------------------------------------------
//
// Jordi Bataller i Mascarell
// 2019-07-07
//
// --------------------------------------------------------------

// https://learn.sparkfun.com/tutorials/nrf52840-development-with-arduino-and-circuitpython

// https://stackoverflow.com/questions/29246805/can-an-ibeacon-have-a-data-payload

// --------------------------------------------------------------
// --------------------------------------------------------------
#include <bluefruit.h>
#include "Arduino_nRF5x_lowPower.h" // LowPower Library for nRF5x

#undef min // vaya tela, están definidos en bluefruit.h y  !
#undef max // colisionan con los de la biblioteca estándar

// --------------------------------------------------------------
// --------------------------------------------------------------
#include "LED.h"
#include "PuertoSerie.h"
#include "Battery.h"


// --------------------------------------------------------------
// --------------------------------------------------------------
namespace Globales {
  
  LED elLED ( /* NUMERO DEL PIN LED = */ 7 );

  //PuertoSerie elPuerto ( /* velocidad = */ 9600 ); // 115200 o 9600 o ... //Ya no es necesario el monitor

  // Serial1 en el ejemplo de Curro creo que es la conexión placa-sensor 
};

// --------------------------------------------------------------
// --------------------------------------------------------------
#include "EmisoraBLE.h"
#include "Publicador.h"
#include "Medidor.h"


// --------------------------------------------------------------
// --------------------------------------------------------------
namespace Globales {

  Publicador elPublicador;

  Medidor elMedidor;

  //BATTERY bat;

}; // namespace

// --------------------------------------------------------------
// --------------------------------------------------------------
void inicializarPlaquita () {

  // Esperar 60 minutos para el heater del sensor
  // delay(60*60*1000);

  //nRF5x_lowPower.enableDCDC(); to enable the DC/DC converter
  nRF5x_lowPower.disableDCDC(); 
  
  //Configure WDT for 120 seconds
  
  NRF_WDT->CONFIG         = 0x01;     // Configure WDT to run when CPU is asleep
  NRF_WDT->CRV            = (3932159 / 3);    // now(120s/2) CRV = timeout * 32768 + 1
  NRF_WDT->RREN           = 0x01;     // Enable the RR[0] reload register
  NRF_WDT->TASKS_START    = 1;        // Start WDT    

} // ()

// --------------------------------------------------------------
// setup()
// --------------------------------------------------------------
void setup() {

  //comentado para arrancar sin encender el minitor serial
  //Globales::elPuerto.esperarDisponible();

  // 
  // 
  inicializarPlaquita();

  // Suspend Loop() to save power
  // suspendLoop();

  // 
  // 
  Globales::elPublicador.encenderEmisora();

  // Globales::elPublicador.laEmisora.pruebaEmision();
  // 
  // 
  Globales::elMedidor.iniciarMedidor();

  // 
  // 
  //Globales::elPuerto.escribir( "---- setup(): fin ---- \n " );

} // setup ()

// --------------------------------------------------------------
// --------------------------------------------------------------
inline void lucecitas() {
  using namespace Globales;
  elLED.brillar( 40 ); // 100 encendido
  esperar ( 40 ); //  100 apagado
} // ()

// --------------------------------------------------------------
// loop ()
// --------------------------------------------------------------
namespace Loop {
  uint8_t cont = 0;
  int tiempo = 0;
  uint8_t battery = 0;
};

// ..............................................................
// ..............................................................
void loop () {

  using namespace Loop;
  using namespace Globales;

  cont++;

  //elPuerto.escribir( "\n---- loop(): empieza " );
  //elPuerto.escribir( cont );
  //elPuerto.escribir( "\n" );

  lucecitas();

  // 
  // mido y publico
  // 

  //nRF5x_lowPower.enableDCDC();
  //battery = bat.obtenerPorcentaje();
  //nRF5x_lowPower.disableDCDC(); 
  int valorNO2 = elMedidor.medirNO2();

  elPublicador.publicarNO2( valorNO2,
							cont,
							1000 // intervalo de emisión
							);
  /*
  // mido y publico
  // 
  int valorTemperatura = elMedidor.medirTemperatura();
  
  elPublicador.publicarTemperatura( valorTemperatura, 
									cont,
									1000 // intervalo de emisión
									);

  // 
  // prueba para emitir un iBeacon y poner
  // en la carga (21 bytes = uuid 16 major 2 minor 2 txPower 1 )
  // lo que queramos (sin seguir dicho formato)
  // 
  // Al terminar la prueba hay que hacer Publicador::laEmisora privado
  // 
  char datos[21] = {
	'H', 'o', 'l', 'a',
	'H', 'o', 'l', 'a',
	'H', 'o', 'l', 'a',
	'H', 'o', 'l', 'a',
	'H', 'o', 'l', 'a',
	'H'
  };

  // elPublicador.laEmisora.emitirAnuncioIBeaconLibre ( &datos[0], 21 );
  elPublicador.laEmisora.emitirAnuncioIBeaconLibre ( "MolaMolaMolaMolaMolaM", 21 );
  */

  elPublicador.laEmisora.detenerAnuncio();

  tiempo = 0;
  while (tiempo <= 2) {
      //nRF5x_lowPower.powerMode(POWER_MODE_CONSTANT_LATENCY);
      //nRF5x_lowPower.powerMode(POWER_MODE_OFF);
      nRF5x_lowPower.powerMode(POWER_MODE_LOW_POWER);
      delay(5100);
      tiempo++;
  }
  
  // Reload the WDTs RR[0] reload register
  NRF_WDT->RR[0] = WDT_RR_RR_Reload; 
  
  // 
  // 
  //elPuerto.escribir( "---- loop(): acaba **** " );
  //elPuerto.escribir( cont );
  //elPuerto.escribir( "\n" );
  
} // loop ()
// --------------------------------------------------------------
// --------------------------------------------------------------
// --------------------------------------------------------------
// --------------------------------------------------------------

/**@brief Function for putting the chip into sleep mode.
 *
 * @note This function will not return.
 */

  
