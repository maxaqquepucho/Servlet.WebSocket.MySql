package com.colegio.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.colegio.modelo.Persona;

public interface PersonaInterface {
	public ArrayList<Persona> mostrar();
	public boolean agregar(Persona persona);
	public Persona buscar(String codigo);
	public boolean actulizar(Persona persona);
	public boolean eliminar(String codigo);
	
	public String getMessage();
}
