package com.colegio.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.colegio.interfaces.PersonaInterface;
import com.colegio.modelo.Login;
import com.colegio.modelo.Persona;
import com.colegio.mysql.PersonaSQL;

/**
 * Servlet implementation class ServletPersona
 */
@WebServlet(name ="/ServletPersona", urlPatterns = {"/ServletPersona"})
public class ServletPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		PersonaInterface persona = new PersonaSQL(); 
		
		//El "usuario" es el atributo que enviaste del LoginServlet
 		Login usuario = (Login) session.getAttribute("usuario");
 		
 		if (usuario != null) {
			
 			try {
 				List<Persona> lista = persona.mostrar();
 				if (lista != null) {
 					request.setAttribute("lista", lista);
 					System.out.println("La lista no es null");
 				} else {
 					System.out.println("No Existe ninguna lita o probablemente hay un error");
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 			}finally {
 				System.out.println("Se realizo correctamente en envio del listado de persona - ServletPersona");
 			}
 			
 			RequestDispatcher despachador = request.getRequestDispatcher("TablaPersona.jsp");
			despachador.forward(request, response);
 			System.out.println("Llegaste con exito al ServletPersona");
		}else {
			RequestDispatcher despachador = request.getRequestDispatcher("error.jsp");
			despachador.forward(request, response);
		}
		
		
	}

	/*private String verificar(HttpServletRequest request, Persona persona) {
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
	}*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
