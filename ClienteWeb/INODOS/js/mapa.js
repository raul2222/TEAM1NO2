let map;

var heatMapData = [];
var heatmap;

const limitesGandia = {
  north: 39.022936,
  south: 38.945009,
  west: -0.201845,
  east: -0.143217
}

const esquinasImagen = {
  north: 39.022936,
  south: 38.945009,
  west: -0.242204,
  east: -0.100732
}

const locationEstacion = { lat: 38.96819788848196, lng: -0.19047359706012285 };

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 38.981761, lng: -0.169679 },
    restriction:{
      latLngBounds: limitesGandia
    } ,
	  disableDefaultUI: true,
    gestureHandling: "greedy",
    zoom: 14,
    styles: [
  {
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#f5f5f5"
      }
    ]
  },
  {
    "elementType": "labels.icon",
    "stylers": [
      {
        "visibility": "off"
      }
    ]
  },
  {
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#616161"
      }
    ]
  },
  {
    "elementType": "labels.text.stroke",
    "stylers": [
      {
        "color": "#f5f5f5"
      }
    ]
  },
  {
    "featureType": "administrative.land_parcel",
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#bdbdbd"
      }
    ]
  },
  {
    "featureType": "poi",
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#eeeeee"
      }
    ]
  },
  {
    "featureType": "poi",
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#757575"
      }
    ]
  },
  {
    "featureType": "poi.park",
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#e5e5e5"
      }
    ]
  },
  {
    "featureType": "poi.park",
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#9e9e9e"
      }
    ]
  },
  {
    "featureType": "road",
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#ffffff"
      }
    ]
  },
  {
    "featureType": "road.arterial",
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#757575"
      }
    ]
  },
  {
    "featureType": "road.highway",
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#dadada"
      }
    ]
  },
  {
    "featureType": "road.highway",
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#616161"
      }
    ]
  },
  {
    "featureType": "road.local",
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#9e9e9e"
      }
    ]
  },
  {
    "featureType": "transit.line",
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#e5e5e5"
      }
    ]
  },
  {
    "featureType": "transit.station",
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#eeeeee"
      }
    ]
  },
  {
    "featureType": "water",
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#c9c9c9"
      }
    ]
  },
  {
    "featureType": "water",
    "elementType": "labels.text.fill",
    "stylers": [
      {
        "color": "#9e9e9e"
      }
    ]
  }
]
  });
    
    
    
  //medidasRandom();
  //heatmap.setMap(map);
  //subirMedidas();
 
  var valorEstacion;
  

  db.collection("Estaciones").doc("7cBiyMkFd5MBgNQVcdKA").get().then(function(doc){
    if(doc.exists){
      valorEstacion = doc.data().Valor;
      console.log("Estacion medida oficial: ", valorEstacion);

      //Marker en el mapa
    const image =
    'images/iconoEstacion.png'; //Referencia al icono del marker
    
    const estacionMarker = new google.maps.Marker({ //Aqui creamos el marker
        position: locationEstacion,
        map,
        title: "Estacion de medida oficial de Gandia",
        icon: image,
    });
    
    //Creamos un pequeño pop-up con la medición de la estación
    var contentString = '<h5> <br>' + "Valor NO2: " + valorEstacion + '<h5>'
    var infoEstacion = new google.maps.InfoWindow({ //Añadimos InfoWindow (popup)
        content: contentString
    });
    estacionMarker.addListener('click', function(){ //Le pasamos la info al popup
        infoEstacion.open(map, estacionMarker);
    });

    } else {
      console.log("No existe documento");
    }
  }).catch(function(error){
      console.log("Error: ", error);
  });


    
}

function cambiarMapa(gas){
    if(gas == "NO2"){
      imagenMapaSO2.setMap(null);
      imagenMapaCO.setMap(null);
      imagenMapaNO2.setMap(map);
      imagenMapaNO2.setOpacity(0.6);
    } else if(gas == "SO2"){
      imagenMapaNO2.setMap(null);
      imagenMapaCO.setMap(null);
      imagenMapaSO2.setMap(map);
      imagenMapaSO2.setOpacity(0.6);
    } else if(gas == "CO"){
      imagenMapaSO2.setMap(null);
      imagenMapaNO2.setMap(null);
      imagenMapaCO.setMap(map);
      imagenMapaCO.setOpacity(0.6);
    }
}

function medidasSO2(){
  heatMapData = []
    heatmap.setMap(null)
  heatMapData = [
new google.maps.LatLng(38.9920962, -0.179),
new google.maps.LatLng(38.9920962, -0.177),
new google.maps.LatLng(38.9920962, -0.175),
new google.maps.LatLng(38.9920962, -0.173),
new google.maps.LatLng(38.9920962, -0.171),
new google.maps.LatLng(38.9920962, -0.169),
new google.maps.LatLng(38.9920962, -0.167),
new google.maps.LatLng(38.9941875, -0.165),
new google.maps.LatLng(38.9941875, -0.163),
new google.maps.LatLng(38.9941875, -0.161),
new google.maps.LatLng(38.9941875, -0.159),
new google.maps.LatLng(38.9941875, -0.157),
new google.maps.LatLng(38.9941875, -0.155),
new google.maps.LatLng(38.9941875, -0.153)
];

heatmap = new google.maps.visualization.HeatmapLayer({
  data: heatMapData
});

heatmap.setMap(map);

}

function medidasCO(){
  heatMapData = []
    heatmap.setMap(null)
  heatMapData = [
new google.maps.LatLng(38.9920962, -0.18),
new google.maps.LatLng(38.9920962, -0.187),
new google.maps.LatLng(38.9920962, -0.185),
new google.maps.LatLng(38.9920962, -0.183),
new google.maps.LatLng(38.9920962, -0.181),
new google.maps.LatLng(38.9920962, -0.179),
new google.maps.LatLng(38.9920962, -0.167),
new google.maps.LatLng(38.9941875, -0.165),
new google.maps.LatLng(38.9941865, -0.163),
new google.maps.LatLng(38.9941875, -0.161),
new google.maps.LatLng(38.9941872, -0.159),
new google.maps.LatLng(38.9941871, -0.157),
new google.maps.LatLng(38.9941875, -0.155),
new google.maps.LatLng(38.9941878, -0.153)
];

heatmap = new google.maps.visualization.HeatmapLayer({
  data: heatMapData
});

heatmap.setMap(map);

}



//require("firebase/firestore");
//const firebase = require("firebase");


//firebase.analytics();

//lat 39.0060389 a 38.9854093
//lng -0.1570515 a -0.1818705
//valor 0 a 5
//momento 1604569712
//idsensor 10

/*function subirMedidas(){
    for(var i = 0; i < 100; i++){
        db.collection("mediciones").add({
            lat: (Math.random() * (38.9854093 - 39.0060389) + 39.0060389).toFixed(7),
            lng: (Math.random() * (-0.1818705 + 0.1570515) - 0.1570515).toFixed(7),
            valor: Math.floor(Math.random() * 6),
            momento: 1604569712,
            idsensor: 10
        }).then(function(docRef){
            console.log("Document written with ID: ", docRef.id);
        }).catch(function(error){
            console.error("Error adding document: ", error);
        })
    }
}

*/