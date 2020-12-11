var db = firebase.firestore();

// Get the modal
var modal = document.getElementById("login");

// Get the button that opens the modal
var btn = document.getElementById("btn-iniciarSesion");

var userName = document.querySelector('#nom').value;
var pass = document.querySelector('#password').value;


function login(){ //Funcion para obtener la información acerca de MiSensor
    console.log("Si que entra en la función");

    db.collection("Usuarios").doc("FXT3LMEeACdvG8A8Mi5Q").get().then(function(doc) { //Comprobamos que el usuario que buscamos SI que existe
        if (doc.exists) {
            var user = doc.data().nombre;

            db.collection("Mediciones").doc("0jWZyo0ySn4W8TmohXe5").get().then(function(doc2) { //Comprobamos que ese Usuario tiene una Medicion
                if(doc2.exists){
                    var user2 = doc2.data().nombre;
                    if(user == userName){
                        window.open("index.html")
                    }
                }
            });
            //Aqui dentro pondremos la info del sensor a la pagina web

            
        } else {
            console.log("No se ha encontrado el documento");
        }
    }).catch(function(error) {
        console.log("Error intentando coger el documento: ", error);
    });

  
}
  