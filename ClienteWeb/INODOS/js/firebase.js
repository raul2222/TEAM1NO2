var db = firebase.firestore();
var storage = firebase.storage();
var storageRef = storage.ref();
var pathRef = storageRef.child('mapas_gandia/actual.png');

let imagenMapa;
var res = [];
var cont = 0;

function getMediciones(){
    console.log("hola");
    heatMapData = []
    db.collection("Mediciones").get().then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
            console.log(`${doc.id} => ${doc.data()}`);
            var w = 1;
            heatMapData.push({location: new google.maps.LatLng(doc.data().Latitud, doc.data().Longitud), weight: w})
            res.push({location: new google.maps.LatLng(doc.data().Latitud, doc.data().Longitud), weight: w, date: doc.data().Momento})
        })
        
        heatmap = new google.maps.visualization.HeatmapLayer({
            data: heatMapData
        });
        heatmap.setMap(map);
    })
}

function getImagenDelMapa(){
    pathRef.getDownloadURL().then(function(url){
        imagenMapa = new google.maps.GroundOverlay(
            url,
            esquinasImagen
        );
        imagenMapa.setMap(map);
        imagenMapa.setOpacity(0.6);
    }).catch(function(error){
        console.error(error);
    })
}

function getInfoMiSensor(){ //Funcion para obtener la información acerca de MiSensor
    console.log("Si que entra en la función");
    
    const IDSensor = document.querySelector("#IDSensor");
    const Latitud = document.querySelector("#Latitud");
    const Longitud = document.querySelector("#Longitud");
    const MomentoUnix = document.querySelector("#MomentoUnix");
    const Valor = document.querySelector("#Valor");
    const NumSerie = document.querySelector("#NumSerie");
    
    db.collection("Usuarios").doc("te1zEDApDxBgwMRihthy").get().then(function(doc) { //Comprobamos que el usuario que buscamos SI que existe
        if (doc.exists) {
            console.log("Document data: ", doc.data());
            var IDSensor2 = doc.data().idsensor;
            
            db.collection("Mediciones").where("IDSensor", "==", "1" ).get().then((querySnapshot) => {
                querySnapshot.forEach((doc2) => { //Comprobamos que ese Usuario tiene una Medicion
                    var IDSensor3 = doc2.data().IDSensor;
                    if(IDSensor3 == IDSensor2 && cont == 0){ //Aqui dentro pondremos la info del sensor a la pagina web
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
    }).catch(function(error) {
        console.log("Error intentando coger el documento: ", error);
    });

    
}

function aplicarIntervaloDeTiempo(){
    heatMapData = []
    heatmap.setMap(null)
    var fechaInicio = new Date(document.getElementById("fechaInicio").value).getTime() / 1000
    var fechaFin = new Date(document.getElementById("fechaFin").value).getTime() / 1000
    console.log(fechaInicio + " " + fechaFin)
    console.log(res)
    for(var i = 0; i < res.length; i++){
        if(res[i].date >= fechaInicio && res[i].date <= fechaFin){
            heatMapData.push({location: res[i].location, weight: res[i].weight})
        }
    }
    console.log(heatMapData)
    heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatMapData
    });
    heatmap.setMap(map);
}

function vaciarYNO2(){
    console.log("Intento vaciar");
    heatMapData = []
    heatmap.setMap(null)
    getMediciones();
}