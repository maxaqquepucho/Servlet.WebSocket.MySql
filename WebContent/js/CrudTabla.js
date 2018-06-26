
// (function(window, document, JSON){
    let url = 'ws://localhost:8080/Servlet.WebSocket.MySql/ServerWebSocket',
    ws = new WebSocket(url),
    btnAgregar = document.getElementById("btnAgregar"),
    nombreUsuario = document.getElementById('nombreUsuario');

    let codigo = document.getElementById('codigo'),
        nombre = document.getElementById('nombre'),
        apellido = document.getElementById('apellido'),
        dni = document.getElementById('dni'),
        indiceRow = document.getElementById('indiceFila');

    document.getElementById("indiceFila").disabled = true;

    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    ws.onerror = onError;

/*===================================================*/
    function onOpen(){
        console.log('conectado...')
    }

    function onClose(){
        console.log('Desconectado...');
    }

    function onMessage(evt){
        let obj = JSON.parse(evt.data);
        switch (obj.accion) {
            case 'Agregar':
                mensajeAgregarFila(obj);
                break;
            case "Eliminar":
                mensajeEliminarFila(obj);
                break;
            case "Editar":
                mensajeEditarFila(obj);
                break;
            default: console.log("No se pudo realizar esta accion");

        }
        console.log(obj);
    }

    function onError(){
        console.log('se cerro la session');
    }
/*===================================================*/

btnAgregar.addEventListener('click', agregarEditarFila);

    function agregarEditarFila(){
            var datos = {
                accion: btnAgregar.value,
                codigo: codigo.value,
                nombre: nombre.value,
                apellido: apellido.value,
                dni: dni.value,
                indiceRow: indiceRow.value
            };
            ws.send(JSON.stringify(datos)+'');

        //console.log(datos);
    }

    function mensajeAgregarFila(objeto){
        let row = tabla.insertRow(numFilas());

        let cell1 = row.insertCell(0),
            cell2 = row.insertCell(1),
            cell3 = row.insertCell(2),
            cell4 = row.insertCell(3),
            cell5 = row.insertCell(4),
            cell6 = row.insertCell(5);

            cell1.setAttribute('scope','row')

            cell1.innerHTML = objeto.codigo;
            cell2.innerHTML = objeto.nombre;
            cell3.innerHTML = objeto.apellido;
            cell4.innerHTML = objeto.dni;
            cell5.innerHTML = `<a href="#" class="btn btn-primary eliminar" onclick="eliminarFila(this)" >Eliminar</a>`;
            cell6.innerHTML =`<a href="#" class="btn btn-warning" onclick="editarFila(this)">Editar</a>`;
            console.log('Fila '+numFilas());
            limpiarCampos();
    }

    function mensajeEliminarFila(objeto) {
        let tablaPersona = document.querySelector('#personas');
        tablaPersona.children[objeto.indiceFila].remove();
        console.log('Se elimino la fila: '+objeto.indiceFila);
    }

    function mensajeEditarFila(objeto){
        let tablaPersona = document.querySelector('#personas');
        let fila = tablaPersona.children[objeto.indiceRow];
        let celda = fila.getElementsByTagName('td');

        celda[0].innerHTML = objeto.codigo;
        celda[1].innerHTML = objeto.nombre;
        celda[2].innerHTML = objeto.apellido;
        celda[3].innerHTML = objeto.dni;

        btnAgregar.value = 'Agregar';
        limpiarCampos();
    }

    function eliminarFila(t){
        let td = t.parentNode;
        let tr = td.parentNode;
        var table = tr.parentNode;

        let datos = {
            accion: 'Eliminar',
            codigo: tr.children[0].textContent,
            indiceFila: tr.sectionRowIndex
        };

        ws.send(JSON.stringify(datos)+'');
    }

    function editarFila(t){
        let td = t.parentNode;
        let fila = td.parentNode;

        codigo.value = fila.children[0].textContent;
        indiceRow.value = fila.sectionRowIndex;
        nombre.value = fila.children[1].textContent;
        apellido.value = fila.children[2].textContent;
        dni.value = fila.children[3].textContent;
        btnAgregar.value = 'Editar';
    }


    let tabla = document.querySelector('#personas');
    function numFilas() {
        return  tabla.rows.length;
    }

    function limpiarCampos() {
        indiceRow.value = '';
        codigo.value = '';
        nombre.value = '';
        apellido.value = '';
        dni.value = '';
    }
// })(window, document, JSON);
