package com.colegio.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.colegio.interfaces.PersonaInterface;
import com.colegio.modelo.Persona;



import com.colegio.jdbc.*;

public class PersonaSQL implements PersonaInterface{
	
	protected ArrayList lista = new ArrayList();
	private final  ConectarMysql mysql;
	public PersonaSQL() {
		this.mysql = new ConectarMysql();
	}
	public void setElemento(Persona elemento) 
	{
		try 
		{lista.set(lista.size(), elemento);} 
		catch (Exception e) 
		{lista.add(elemento);}
	}
	
	String SQL = "";
	@Override
	public ArrayList<Persona> mostrar() {
		ArrayList<Persona> lista = new ArrayList<Persona>();
		//Persona persona = new Persona();
		SQL = "select * from persona";
		mysql.establecerConexion();
		Connection conectado = mysql.getConnection();
		
		
		
		try {
			PreparedStatement ps = conectado.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				Persona persona = new Persona();
				persona.setCodigo(rs.getString("codigo"));
				persona.setNombre(rs.getString("nombre"));
				persona.setApellido(rs.getString("apellido"));
				persona.setDni(rs.getString("dni"));
				//setElemento(persona);
				//lista.set(lista.size()+1, persona);
				lista.add(persona);
			}
				
				//Para comprobar que esta trayendo los datos de la base de datos
				//System.out.println(personas.toString());
				//System.out.println(rs.getString("codigo"));
				//System.out.println(rs.getString("nombre"));
				//Integer.parseInt(personas.getCodigo());
				
			System.out.println("AQUI : "+lista.toString());
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//mysql.cerrarConexion();
		}
		return null;
	}

	@Override
	public boolean agregar(Persona persona) {

		SQL = "INSERT INTO persona (codigo, nombre, apellido, dni) " + 
				"            VALUES ( ?, ?, ?, ?);";
		mysql.establecerConexion();
		Connection conectado = mysql.getConnection();
		
		try {
			PreparedStatement pst = conectado.prepareStatement(SQL);
			pst.setString(1, persona.getCodigo());
			pst.setString(2, persona.getNombre());
			pst.setString(3, persona.getApellido());
			pst.setString(4, persona.getDni());
			
			
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
		SQL = "UPDATE persona SET nombre = ?, apellido = ?, dni = ? " + 
		"                     WHERE codigo = ?";
		mysql.establecerConexion();
		Connection conectado = mysql.getConnection();
		
		try {
			PreparedStatement pst = conectado.prepareStatement(SQL);
			
			pst.setString(1, personas.getNombre());
			pst.setString(2, personas.getApellido());
			pst.setString(3, personas.getDni());
			pst.setString(4, personas.getCodigo());
			
			
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
		SQL = "DELETE FROM persona WHERE codigo = ?";
		mysql.establecerConexion();
		Connection conectado = mysql.getConnection();
		
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
