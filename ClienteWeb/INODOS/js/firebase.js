var db = firebase.firestore();


//-----------------------------------------------------//
//-----------------------------------------------------//
//-----------------------------------------------------//


var res = [];
var cont = 0;


//-----------------------------------------------------//
//-----------------------------------------------------//
//-----------------------------------------------------//

function getMediciones() {
    
    console.log("hola");
    heatMapData = [];
    db.collection("Mediciones").get().then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
            console.log(`${doc.id} => ${doc.data()}`);
            var w = 1;
            heatMapData.push({
                location: new google.maps.LatLng(doc.data().Latitud, doc.data().Longitud),
                weight: w
            })
            res.push({
                location: new google.maps.LatLng(doc.data().Latitud, doc.data().Longitud),
                weight: w,
                date: doc.data().Momento
            })
        })

        heatmap = new google.maps.visualization.HeatmapLayer({
            data: heatMapData
        });
        heatmap.setMap(map);
    })
}


//-----------------------------------------------------//
//-----------------------------------------------------//
//-----------------------------------------------------//


function getInfoMiSensor() { //Funcion para obtener la información acerca de MiSensor
    console.log("Si que entra en la función");

    const IDSensor = document.querySelector("#IDSensor");
    const Latitud = document.querySelector("#Latitud");
    const Longitud = document.querySelector("#Longitud");
    const MomentoUnix = document.querySelector("#MomentoUnix");
    const Valor = document.querySelector("#Valor");
    const NumSerie = document.querySelector("#NumSerie");

    db.collection("Usuarios").doc("FXT3LMEeACdvG8A8Mi5Q").get().then(function (doc) { //Comprobamos que el usuario que buscamos SI que existe
        if (doc.exists) {
            console.log("Document data: ", doc.data());
            var IDSensor2 = doc.data().idsensor;

            db.collection("Mediciones").where("IDSensor", "==", "1").get().then((querySnapshot) => {
                querySnapshot.forEach((doc2) => { //Comprobamos que ese Usuario tiene una Medicion
                    var IDSensor3 = doc2.data().IDSensor;
                    if (IDSensor3 == IDSensor2 && cont == 0) { //Aqui dentro pondremos la info del sensor a la pagina web
                        IDSensor.innerHTML += "<p> " + doc2.data().IDSensor + " </p>";
                        Latitud.innerHTML += "<p> " + doc2.data().Latitud + " </p>";
                        Longitud.innerHTML += "<p> " + doc2.data().Longitud + " </p>";
                        MomentoUnix.innerHTML += "<p> " + doc2.data().Momento + " </p>";
                        Valor.innerHTML += "<p> " + doc2.data().Valor + " </p>";
                        NumSerie.innerHTML += "<p> " + "03Kl7siezTOx8iCP9itf" + " </p>";
                        cont++;
                    }
                });
            });


        } else {
            console.log("No se ha encontrado el documento");
        }
    }).catch(function (error) {
        console.log("Error intentando coger el documento: ", error);
    });


}


//-----------------------------------------------------//
//-----------------------------------------------------//
//-----------------------------------------------------//


function aplicarIntervaloDeTiempo() {
    heatMapData = []
    heatmap.setMap(null)
    var fechaInicio = new Date(document.getElementById("fechaInicio").value).getTime() / 1000
    var fechaFin = new Date(document.getElementById("fechaFin").value).getTime() / 1000
    console.log(fechaInicio + " " + fechaFin)
    console.log(res)
    for (var i = 0; i < res.length; i++) {
        if (res[i].date >= fechaInicio && res[i].date <= fechaFin) {
            heatMapData.push({
                location: res[i].location,
                weight: res[i].weight
            })
        }
    }
    console.log(heatMapData)
    heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatMapData
    });
    heatmap.setMap(map);
}


//-----------------------------------------------------//
//-----------------------------------------------------//
//-----------------------------------------------------//


/*function login() {

    var label = document.getElementById("errorlogin");
    console.log("Si que entra en la función");

    const telefono = document.getElementById("telefono").value
    const contrasena = document.getElementById("password").value;

    db.collection("Usuarios").where("telefono", "==", telefono).get().then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
            if (!querySnapshot.empty) {
                var data = doc.data();
                //console.log(data.password);
                if (data.password == contrasena) {
                    window.location.href = "./index.html";
                    return;
                } else {
                    label.style.display = "block";
                    console.log("telefono o contraseña incorrecto")
                    return;
                }
                console.log(doc.data().nombre);
            } else {
                console.log("Documento no existe")
                label.style.display = "block";
                return;
            }

        });

    }).catch(function (error) {
        console.log(error.message);
    })
    label.style.display = "block";
}*/


//-----------------------------------------------------//
//-----------------------------------------------------//
//-----------------------------------------------------//


function login() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var user = firebase.auth().currentUser;
    console.log(user)
    firebase.auth().signInWithEmailAndPassword(email, password).then((user) => {  //login bien hecho
            console.log(user.user.email);
            db.collection("Usuarios").where('email', '==', user.user.email).limit(1).get()
            .then((query) =>{
                query.forEach(function(doc){
                    console.log(doc.data());
                    sessionStorage.setItem("idsensor", doc.data().idsensor);
                    sessionStorage.setItem("email", doc.data().email);
                    window.location.href = "./index.html";

                })
            })
        })
        .catch((error) => { //errores
            label.style.display = "block";
            console.log("email o contraseña incorrecto")
            var errorCode = error.code;
            var errorMessage = error.message;
            console.log(errorMessage);
        });
}


//-----------------------------------------------------//
//-----------------------------------------------------//
//-----------------------------------------------------//



 function registrarse() {

            const nombre = document.getElementById("nombre").value;
            const apellido = document.getElementById("apellido").value;
            const email = document.getElementById("email").value;
            const telefono = document.getElementById("telefono").value;
            const contrasenya = document.getElementById("password").value;
            const idsensor = document.getElementById("idsensor").value;

            firebase.auth().createUserWithEmailAndPassword(email, contrasenya)
                .then((user) => {
                    var user = firebase.auth().currentUser;
                    var uid = user.uid;
                    console.log(uid);

                    db.collection("Usuarios").add({
                        nombre: nombre,
                        apellido: apellido,
                        email: email,
                        id: uid,
                        idsensor: idsensor,
                        telefono: telefono

                    }).then(function (docRef) {
                        console.log(docRef.id);
                        label2.style.display = "block";


                    }).catch(function (error) {
                        console.log(error.message);
                    })

                    console.log();
                })
                .catch((error) => {
                    //if(nombre && apellido && email && telefono && contrasenya && idsensor === ''){
                        label.style.display = "block";
                        console.log("campos incompletos")
                    //} else{
                        //label2.style.display = "block";
                        //console.log("registrado correctamente")
                    //}
                    
                    var errorCode = error.code;
                    var errorMessage = error.message;
                    console.log(errorMessage);
                });
        }


        

/*function registrarse() {

    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const telefono = document.getElementById("telefono").value;
    const contrasena = document.getElementById("password").value;

    if (nombre == "" || apellido == "" || telefono == "" || contrasena == "") {
        console.log("Los campos estan vacios");
    } else {

        let a = db.collection("Usuarios");
        a.get().then(function (documentSnapshots) {
            let ultimo = documentSnapshots.docs.length + 1;

            db.collection("Usuarios").doc().set({
                    apellidos: apellido,
                    idsensor: "1",
                    idusuario: "" + ultimo,
                    nombre: nombre,
                    password: contrasena,
                    telefono: telefono

                })
                .then(function () {
                    console.log("Document successfully written!");
                    window.location.href = "./login.html";
                })
                .catch(function (error) {
                    console.error("Error writing document: ", error);
                });
        })
    }
}*/
