//var db = firebase.firestore();
var marker;
var cont = 0;

//------------------------------------------//
//------------------------------------------//
// markerUbicacion() --> 
//------------------------------------------//
//------------------------------------------//

function markerUbicacion(){
    /*db.collection("Mediciones").get().then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
            latlong.push({lat: doc.data().latitud, lng: doc.data().longitud})
            console.log("pongo una posicion")
        })
    })*/
    if(cont > 0){
        marker.setMap(null)
    }
    
    db.collection("Mediciones").where("IDSensor", "==", "1").get().then(function(querySnapshot){ //ultima lat i long
        querySnapshot.forEach(function(doc){
            console.log(doc.data().Latitud + ", " + doc.data().Longitud)
            if(cont > 0){
                console.log("No hago nada")
            } else{
            marker = new google.maps.Marker({
                position: {lat:parseFloat(doc.data().Latitud), lng:parseFloat(doc.data().Longitud) },
                
                //position: {lat: doc.data().latitud, lng: doc.data().longitud},
                map: map,
                title: "Mi posición"
            });
             }
            cont++;
        })
    })
    
        
        /*ubicacion = new google.maps.visualization.HeatmapLayer({
            data: latlong
        });*/
        
    
        /*const marker = new google.maps.Marker({
            position: latlong[0],
            map: map,
        });*/
        
        //ubicacion.setMap(map);
}

//setInterval(markerUbicacion, 20000);


