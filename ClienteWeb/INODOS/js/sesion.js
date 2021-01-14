
//-----------------------------------------
//-----------------------------------------
//-----------------------------------------
//Comprovamos que hay una sesion

function haySesion(){
    var a = sessionStorage.getItem("idsensor");
    if(a != null){
        return true
    } else{
        return false;
    }
}

//-----------------------------------------
//-----------------------------------------
//-----------------------------------------
//administramos la barra lateral, es decir, si no has iniciado sesion no podras ver la opcion de Mi sensor
// ya que esta pagina solo sera para la gente que haya iniciado sesion
//al igual con el boton de iniciar sesion, una vez hayas iniciado sesion, se quedara guardada la sesion
// y el boton de iniciar sesion se cambiara por el de cerrar sesion.


function administrarBarraLateral(){
    if(haySesion()){
        document.getElementById("btn_cerrar_sesion").hidden = false;
        document.getElementById("btn_iniciar_sesion").hidden = true;
        document.getElementById("btn_mi_sensor").hidden = false;


    }
}

//-----------------------------------------
//-----------------------------------------
//-----------------------------------------
//el boton de cerrar sesion, nos cerrara la sesion que estem nosotros actualmente, por tanto si queremos otra vex
// acceder a los datos de nuestro sensor nos obligara a tener que iniciar sesion otra vez.
//una vez se cierra la sesion nos redigira a la pagina principal.
// en este caso nuestra pa principal sera home.

function cerrarSesion(){
    sessionStorage.removeItem("idsensor");
    window.location.href = "./index.html";

}