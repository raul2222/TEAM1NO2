var db = firebase.firestore();
var nDeSensores;
var inactivos = [];
var cont;
var aux1, aux2;
var pdf = new jsPDF();
var x=10,y=10;

function generarInforme(){
    db.collection('Sensores').get().then(snap => {
        console.log(snap.size);
        nDeSensores = snap.size;
        generarHoras();
    });
}

function generarHoras(){
    cont = 0;
    db.collection('EstadosNodos').orderBy('Momento', 'asc').get().then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
            if(cont==0){
                cont++;
            } else{
                if(doc.data().Estado == "En movimiento"){
                    if(parseInt(doc.data().Momento) - aux1 > 3600){
                        pdf.text("AVISO: Inactividad de más de 24h del sensor " + doc.data().IDSensor, x, y);
                        y = y + 10;
                        console.log("inactivo");
                    }
                }
            }
            aux1 = parseInt(doc.data().Momento);
        })
    })
    setTimeout(function(){
        pdf.save("informe.pdf");
    }, 2000)
}