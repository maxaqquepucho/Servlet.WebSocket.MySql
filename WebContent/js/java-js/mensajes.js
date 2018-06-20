(function(window, document) { // asilamos el componente
  // creamos el contedor de las tostadas o la tostadora
  var container = document.createElement('div');
  container.className = 'toast-container';
  document.body.appendChild(container);

  // esta es la funcion que hace la tostada
  window.doToast = function(message) {
    // creamos tostada
    var toast = document.createElement('div');
    toast.className = 'toast-toast';
    toast.innerHTML = message;

    // agregamos a la tostadora
    container.appendChild(toast);

    // programamos su eliminación
    setTimeout(function() {
      // cuando acabe de desaparecer, lo eliminamos del dom.
      toast.addEventListener("transitionend", function() {
         container.removeChild(toast);
      }, false);

      // agregamos un estilo que inicie la "transition".
      toast.classList.add("fadeout");
    }, 2000); // OP dijo, "solo dos segundos"
  }
})(window, document);

// ejempo de uso
doToast("Hola mundo!");

// ejemplo retardado de uso
setTimeout(function() {
   doToast("Soy una tostada");
}, 1200);
