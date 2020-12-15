function login(){ //Funcion para obtener la información acerca de MiSensor
    console.log("Si que entra en la función");
    
    const nombre = document.querySelector("#nom").value;
    const contrasena = document.querySelector("#password").value;
   
    db.collection("Usuarios").doc("FXT3LMEeACdvG8A8Mi5Q").get().then(function(doc) { //Comprobamos que el usuario que buscamos SI que existe
        if (doc.exists) {
            console.log("Document data: ", doc.data());
            var a = doc.data().nombre;
            
            db.collection("Usuarios").doc("FXT3LMEeACdvG8A8Mi5Q").get().then(function(doc2) { //Comprobamos que ese Usuario tiene una Medicion
                if(doc2.exists){
                    var b = doc2.data().password;
                    if(nombre == a && contrasena ==b){
                        window.open("https://www.w3schools.com");
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

login();



function phoneAuth() {
    //get the number
    var number=document.getElementById('nom').value;
    //phone number authentication function of firebase
    //it takes two parameter first one is number,,,second one is recaptcha
    firebase.auth().signInWithPhoneNumber(number,window.recaptchaVerifier).then(function (confirmationResult) {
        //s is in lowercase
        window.confirmationResult=confirmationResult;
        coderesult=confirmationResult;
        console.log(coderesult);
        alert("Message sent");
    }).catch(function (error) {
        alert(error.message);
    });
}