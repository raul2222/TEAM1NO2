// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("popup");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];






function getInfoMiSensor(){ //Funcion para obtener la información acerca de MiSensor
  console.log("Si que entra en la función");
  
  let IDSensor = document.querySelector("#IDSensor");
 
  let Valor = document.querySelector("#Valor");

  var ultimoValor;

  var cont = 0;


  
  db.collection("Mediciones").where("IDSensor", "==", "1" ).get().then((querySnapshot) => {
    querySnapshot.forEach((doc) => {

      if(cont == 0){
        ultimoValor = doc.data().Valor; // cojo tadas mediciones del sensor
        cont++;
        document.getElementById("Valor").value = ultimoValor;
      }


      //console.log(ultimoValor);
    });

  })

      //if (doc.exists) {

        /*.then(function(querySnapshot) {
          querySnapshot.forEach(function(doc) {
              // doc.data() is never undefined for query doc snapshots
              console.log(doc.id, " => ", doc.data());
          });*/
      


      .catch(function(error) {
          console.log("Error getting documents: ", error);
      })

      document.getElementById("IDSensor").value = 1;
      //console.log(ultimoValor);
      //document.getElementById("Valor").value = "" + ultimoValor;
          
    
}

// When the user clicks on the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
  getInfoMiSensor(); //llamo a la función que me obtiene la informacion de mi sensor
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
  getInfoMiSensor();
  
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}