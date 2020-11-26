let map;

var heatMapData = [];
var heatmap;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 38.99574102, lng: -0.15975315 },
    zoom: 16,
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
}

function medidasRandom(){
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

/*heatmap = new google.maps.visualization.HeatmapLayer({
    data: heatMapData
});*/
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