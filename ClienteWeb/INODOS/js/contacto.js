const nombre = document.querySelector("#nombre");
const email = document.querySelector("#email");
const asunto = document.querySelector("#asunto");
const mensaje = document.querySelector("#mensaje");

function sendMail() {

    if (nombre.value.length != 0 && email.value.length != 0 && asunto.value.length != 0 && mensaje.value.length != 0 ){
        window.open("mailto:"+email.value.toString()+'?cc='+nombre.value.toString()+'&subject='+asunto.value.toString()+'&body='+mensaje.value.toString());
    }

        
}
