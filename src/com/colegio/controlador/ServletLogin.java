package com.colegio.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.colegio.interfaces.LoginInterface;
import com.colegio.interfaces.PersonaInterface;
import com.colegio.modelo.Login;
import com.colegio.modelo.Persona;
import com.colegio.mysql.LoginSQL;
import com.colegio.mysql.PersonaSQL;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		
		Login login = new Login();
		login.setCodigo(user);
		login.setPass(pass);
		
		LoginInterface loginSQL = new LoginSQL();
		
		if (loginSQL.iniciarSesion(login)) {
			
			HttpSession session = request.getSession();
			session.setAttribute("usuario", login);
			
			RequestDispatcher despachador = request.getRequestDispatcher("ServletPersona");
			despachador.forward(request, response);
			
			
		}else {
			response.sendRedirect("index.jsp");
		}
		
		
	}

}
