var db = firebase.firestore();

const ultimasMediciones = [];
const ultimosMomentos = [];
var contador = 0; 

const arrayUnix = [];
var contadorUnix = 0;

function dibujarGraficaConUltimas5Mediciones(){ //Funcion para obtener la información acerca de MiSensor
    
    console.log("Si que entra en la función");

    db.collection("Mediciones").where("IDSensor", "==", "1" ).get().then((querySnapshot) => {
      querySnapshot.forEach((doc) => {
  
        if(contador <= 4){   
          ultimoValor = doc.data().Valor; // cojo tadas mediciones del sensor
          ultimoMomento = doc.data().Momento;
          ultimasMediciones.push(ultimoValor);
          ultimosMomentos.push(ultimoMomento);
          contador++;
        }

        console.log("Ultimas mediciones: " + ultimosMomentos[4]);

        var ctxL = document.getElementById("lineChart").getContext('2d');
            var myLineChart = new Chart(ctxL, {
                type: 'line',
                data: {
                    labels: [
                        "", 
                        "", 
                        "", 
                        "", 
                        ""
                    ],
                    datasets: [{
                        label: "Últimas 5 Mediciones",
                        data: [
                            ultimasMediciones[4], 
                            ultimasMediciones[3], 
                            ultimasMediciones[2], 
                            ultimasMediciones[1], 
                            ultimasMediciones[0]],
                        backgroundColor: [
                        'rgba(209, 238, 222, .5)',
                        ],
                        borderColor: [
                        'rgba(209, 238, 222, 1)',
                        ],
                        borderWidth: 2
                    }
                    ]
                },
                options: {
                responsive: true
                }
            });
      });
  
    }).catch(function(error) {
        console.log("Error getting documents: ", error);
    })
    
  }



//Aquí vamos a dar los valores para la gráfica
//line

function dibujarGrafica(){

        console.log("Ultimas mediciones: " + ultimasMediciones);

}

/*function obtenerMediaUltimasHoras (){

  const dateTime = Date.now();
  const momentoActual = Math.floor(dateTime / 1000);
  
  console.log("El momento Unix actual es de: " + momentoActual); //momento actual en Unix

  const momentoUnDiaAntes = momentoActual - 86400; //diferencia de 24h

  console.log("El momento Unix de hace 24h es de: " + momentoUnDiaAntes); //momento hace 24h en Unix


  db.collection("Mediciones2").where("Momento", ">=", momentoUnDiaAntes && "<=", momentoActual).get().then((querySnapshot) => { //queremos los valores de las ultimas 24h
    querySnapshot.forEach((doc) => {

      //guardamos los valores en el array que estan dentro del rango
      for (var i = 0; i < 20000000000000000; i++){
        valorEnRango = doc.data().Valor; 
        arrayUnix.push (valorEnRango);
      }

      //calculamos la media de los valores del array
      var total = 0;
      for( i=0; i < arrayUnix.length; i++)
      {
          total += parseInt(arrayUnix[i]);
          document.write("<br>"+ arrayUnix[i]);
      }

      var media = total/arrayUnix.length;
      //const MediaNO2 = document.querySelector("#Media");
      //document.write("<br><br>Media: "+ media +"<br>");
      console.log("Media de las ultimas 24h: " + media);
      //MediaNO2.innerHTML += "<p> " + media + " </p>";

      // indicamos si la media esta por encima o debajo
      //aqui tengo que comparar con la media de la estacion oficial que esta guardada en un array
      /*for(i=0; i<ultimosUnix.length; i++)
      {
          if(media>parseInt(ultimosUnix[i]))
              document.write("<br>"+ultimosUnix[i]+" esta por debajo de la media")
          else if(media<parseInt(ultimosUnix[i]))
              document.write("<br>"+ultimosUnix[i]+" esta por encima de la media")
          else
              document.write("<br>"+ultimosUnix[i]+" es el mismo valor que la media");
      }
      
    });

  }).catch(function(error) {
      console.log("Error getting documents: ", error);
  })


}*/



$.ajax({
  url: 'last_NO2.csv',
  dataType: 'text',
}).done(leerDatoCsv);

function leerDatoCsv(data) {
  //console.log(data);
  var datoFinal = document.getElementById('MediaEstacion');
  data.toString();
  //console.log(data);
  datoFinal.innerText = data;
}





