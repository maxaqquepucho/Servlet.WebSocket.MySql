package com.colegio.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.colegio.interfaces.PersonaInterface;
import com.colegio.modelo.Persona;

import com.colegio.jdbc.*;

public class PersonaSQL implements PersonaInterface{

	private final  ConectarMysql mysql;
	public PersonaSQL() {
		this.mysql = new ConectarMysql();
	}
	
	String SQL = "";
	@Override
	public List<Persona> mostrar() {
		List<Persona> lista = new LinkedList<Persona>();
		Persona personas = new Persona();
		SQL = "select * from personas";
		Connection conectado = mysql.getConnection();
		mysql.establecerConexion();
		
		try {
			Statement st = conectado.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			
			while (rs.next()) {
				personas.setCodigo(rs.getString("codigo"));
				personas.setNombre(rs.getString("nombre"));
				personas.setApellido(rs.getString("apellido"));
				personas.setDni(rs.getString("dni"));
				
				
				lista.add(personas);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			mysql.cerrarConexion();
		}
		return lista;
	}

	@Override
	public boolean agregar(Persona personas) {

		SQL = "INSERT INTO personas (nombre, apellido, dni) " + 
				"            VALUES ( ?, ?, ?);";
		Connection conectado = mysql.getConnection();
		mysql.establecerConexion();
		
		try {
			PreparedStatement pst = conectado.prepareStatement(SQL);
			pst.setString(1, personas.getNombre());
			pst.setString(2, personas.getApellido());
			pst.setString(3, personas.getDni());
			
			
			int n = pst.executeUpdate();
			mysql.cerrarConexion();
			if (n != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			System.out.println("Se Agrego con Exito - @Override\r\n" + 
					"	public boolean agregar");
		}
	}

	@Override
	public Persona buscar(String codigo) {
		Persona personas = new Persona();
		
		SQL = "SELECT * from personas where codigo = ?";
		Connection conectado = mysql.getConnection();
		mysql.establecerConexion();
		
		try {
			PreparedStatement pst = conectado.prepareStatement(SQL);
			pst.setString(1, codigo);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				personas.setCodigo(rs.getString("codigo"));
				personas.setNombre(rs.getString("nombre"));
				personas.setApellido(rs.getString("apellido"));
				personas.setDni(rs.getString("dni"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			mysql.cerrarConexion();
		}
		
		return personas;
	}

	@Override
	public boolean actulizar(Persona personas) {
		SQL = "UPDATE personas SET nombre = ?, apellido = ?, correo = ?, celular = ?, " + 
		"                     tipoUsusario = ?, sexo = ?, contrasenia = ? " + 
		"                     WHERE codigo = ?";
		Connection conectado = mysql.getConnection();
		mysql.establecerConexion();
		
		try {
			PreparedStatement pst = conectado.prepareStatement(SQL);
			
			pst.setString(1, personas.getCodigo());
			pst.setString(2, personas.getNombre());
			pst.setString(3, personas.getApellido());
			pst.setString(4, personas.getDni());
			
			
			int n = pst.executeUpdate();
			mysql.cerrarConexion();
			if (n != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminar(String codigo) {
		SQL = "DELETE FROM personas WHERE codigo = ?";
		Connection conectado = mysql.getConnection();
		mysql.establecerConexion();
		
		try {
			PreparedStatement  pst = conectado.prepareStatement(SQL);
			pst.setString(1, codigo);
			
			int n = pst.executeUpdate();
			mysql.cerrarConexion();
			if (n != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
