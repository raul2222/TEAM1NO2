var db = firebase.firestore();

const ultimasMediciones = [];
const ultimosMomentos = [];
var contador = 0; 

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



