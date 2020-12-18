//var db = firebase.firestore();
var marker;
var cont = 0;

//------------------------------------------//
//------------------------------------------//
// markerUbicacion() --> 
//------------------------------------------//
//------------------------------------------//

/*

function markerUbicacion(){
  
    if(cont > 0){
        marker.setMap(null)
    }
    
    db.collection("Sensores").where("serialnumber", "==", "erwtfw4352452").get().then(function(querySnapshot){ //ultima lat i long
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
    

}




