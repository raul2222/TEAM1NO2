var db = firebase.firestore();

function getMediciones(){
    console.log("hola");
    heatMapData = [];
    db.collection("Mediciones").get().then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
            console.log(`${doc.id} => ${doc.data()}`);
            var w = (doc.data().Medicion * 3)/100;
            heatMapData.push({location: new google.maps.LatLng(doc.data().Latitud, doc.data().Longitud), weight: w})
        })
        
        heatmap = new google.maps.visualization.HeatmapLayer({
            data: heatMapData
        });
        heatmap.setMap(map);
    })
}

//setInterval(getMediciones, 60000);



function getInfoMiSensor(){ //Funcion para obtener la información acerca de MiSensor
    console.log("Si que entra en la función");
    
    const IDSensor = document.querySelector("#IDSensor");
    const Latitud = document.querySelector("#Latitud");
    const Longitud = document.querySelector("#Longitud");
    const MomentoUnix = document.querySelector("#MomentoUnix");
    const Valor = document.querySelector("#Valor");
    const NumSerie = document.querySelector("NumSerie");
    
    db.collection("Usuarios").doc("FXT3LMEeACdvG8A8Mi5Q").get().then(function(doc) { //Comprobamos que el usuario que buscamos SI que existe
        if (doc.exists) {
            console.log("Document data: ", doc.data());
            var IDSensor2 = doc.data().idsensor;
            
            db.collection("Mediciones").doc("06VpBL7d66GeT9zCmaIG").get().then(function(doc2) { //Comprobamos que ese Usuario tiene una Medicion
                if(doc2.exists){
                    var IDSensor3 = doc2.data().IDSensor;
                    if(IDSensor3 == IDSensor2){
                        IDSensor.innerHTML += "<p> " + doc2.data().IDSensor + " </p>";
                        Latitud.innerHTML += "<p> " + doc2.data().Latitud + " </p>";
                        Longitud.innerHTML += "<p> " + doc2.data().Longitud + " </p>";
                        MomentoUnix.innerHTML += "<p> " + doc2.data().Momento + " </p>";
                        Valor.innerHTML += "<p> " + doc2.data().Valor + " </p>";
                        NumSerie.innerHTML += "<p> " + "06VpBL7d66GeT9zCmaIG" + " </p>";
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