// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("popup");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


function getInfoMiSensor(){ //Funcion para obtener la información acerca de MiSensor
  console.log("Si que entra en la función");
  
  const IDSensor = document.querySelector("#IDSensor");
 
  const Valor = document.querySelector("#Valor");
  
  db.collection("Mediciones").doc("06VpBL7d66GeT9zCmaIG").get().then(function(doc) {
      if (doc.exists) {
          console.log("Document data: ", doc.data());
          //  valores vacios

          IDSensor.innerHTML = "IDSensor:";
          Valor.innerHTML = "Valor:";
          
          //Aqui dentro pondremos la info del sensor a la pagina web
          IDSensor.innerHTML += "<p> " + doc.data().IDSensor + " </p>";
          Valor.innerHTML += "<p> " + doc.data().Valor + " </p>";
          console.log(IDSensor.innerHTML);
          
      } else {
          console.log("No se ha encontrado el documento");
      }
  }).catch(function(error) {
      console.log("Error intentando coger el documento: ", error);
  });
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