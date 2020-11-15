//var db = firebase.firestore();
var marker;
var cont = 0;

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
    
    db.collection("Sensores").where("serialnumber", "==", "erwtfw4352452").get().then(function(querySnapshot){
        querySnapshot.forEach(function(doc){
            console.log(doc.data().ultimalat + ", " + doc.data().ultimalng)
            marker = new google.maps.Marker({
                position: {lat: doc.data().ultimalat, lng: doc.data().ultimalng},
                map: map,
                title: "Mi posición"
            });
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


