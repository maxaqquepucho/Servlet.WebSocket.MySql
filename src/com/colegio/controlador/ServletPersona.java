package com.colegio.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.colegio.tools.ConvertirNumero;
import com.colegio.modelo.Persona;;

/**
 * Servlet implementation class ServletPersona
 */
@WebServlet("/ServletPersona")
public class ServletPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String user = request.getParameter("usuario");
		//String pass = request.getParameter("pass");
		
		System.out.println("Llegaste con exito");
		
	}

	private String verificar(HttpServletRequest request, Persona persona) {
		String mensaje = "<ul>";
		
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dni = request.getParameter("dni");
		
		if((codigo == null) || (codigo.trim().length() == 0)) {
			mensaje += "<li> verifique que halla ingresado correctamente el Codigo </li>";
		}
		if((nombre == null) || (nombre.trim().length() == 0)) {
			mensaje += "<li> verifique que halla ingresado correctamente el nombre </li>";
		}
		if((apellido == null) || (apellido.trim().length() == 0)) {
			mensaje += "<li>Verifique que halla ingresado un numero correctamente para el apellido </li>";
		}
		if((dni == null) || (dni.trim().length() == 0)) {
			mensaje += "<li>Verifique que halla ingresado correctamente el dni</li>";
		}
		
		persona.setCodigo(codigo);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setDni(dni);
		
		if(mensaje.equals("<ul>")) {
			mensaje = null;
		}else {
			mensaje += "</ul>";
		}
		
		return mensaje;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
