package com.colegio.modelo;

public class Login extends Persona {
	private String pass;

	public Login(String codigo, String nombre, String apellido, String dni, String pass) {
		super(codigo, nombre, apellido, dni);
		this.pass = pass;
	}

	public Login() {
		
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
}
