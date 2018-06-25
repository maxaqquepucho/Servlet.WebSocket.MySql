
(function(window, document, JSON){
    let url = 'ws://localhost:8080/Servlet.WebSocket.MySql/ServerWebSocket',
    ws = new WebSocket(url),
    btnAgregar = document.getElementById("btnAgregar"),
    nombreUsuario = document.getElementById('nombreUsuario');

    let codigo = document.getElementById('codigo'),
        nombre = document.getElementById('nombre'),
        apellido = document.getElementById('apellido'),
        dni = document.getElementById('dni');


    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    ws.onerror = onError;

    btnAgregar.addEventListener('click', enviarDatos);
    function onOpen(){
        console.log('conectado...')
    }

    function onClose(){
        console.log('Desconectado...');
    }

    function enviarDatos(){
        var datos = {
            codigo: codigo.value,
            nombre: nombre.value,
            apellido: apellido.value,
            dni: dni.value
        };
        ws.send(JSON.stringify(datos));
        console.log(datos);
    }

    function onMessage(evt){
        let obj = JSON.parse(evt.data);
        console.log(obj);
    }

    function onError(){
        console.log('se cerro la session');
    }

})(window, document, JSON);
