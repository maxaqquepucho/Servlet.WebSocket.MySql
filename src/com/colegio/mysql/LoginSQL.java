package com.colegio.mysql;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.colegio.interfaces.LoginInterface;
import com.colegio.jdbc.ConectarMysql;
import com.colegio.modelo.Login;
import com.colegio.modelo.Persona;
import com.mysql.jdbc.*;


public class LoginSQL implements LoginInterface{

	private final  ConectarMysql mysql;
	public LoginSQL() {
		this.mysql = new ConectarMysql();
	}

	@Override
	public boolean iniciarSesion(Login persona) {
		String SQL = "select A.codigo, B.usuario , B.pass" +
				"     from persona as A inner join login as B on " +
				"     (A.codigo = B.usuario) " +
				"     where B.usuario =? AND B.pass =?; ";

		mysql.establecerConexion();
		Connection conectado = mysql.getConnection();
		String pass = "";
		try {
			PreparedStatement pst = conectado.prepareStatement(SQL);
			pst.setString(1, persona.getCodigo());
			pst.setString(2, persona.getPass());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				pass = rs.getString("pass");
			}
			mysql.cerrarConexion();
			if (pass != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}



}
