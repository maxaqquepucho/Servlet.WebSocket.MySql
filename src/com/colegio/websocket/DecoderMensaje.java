package com.colegio.websocket;

import java.io.IOException;
import java.io.Reader;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.json.JSONStringer;

import com.colegio.modelo.*;



public class DecoderMensaje implements Decoder.TextStream<Persona>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Persona decode(Reader reader) throws DecodeException, IOException {
		// TODO Auto-generated method stub
		Persona persona = new Persona();
		
		try {
			JSONStringer json = new JSONStringer();
			
			json.object();
			String codigo =  json.key("codigo").value(persona.getCodigo()).toString();
			String nombre = json.key("nombre").value(persona.getNombre()).toString();
			String apellido = json.key("apellido").value(persona.getApellido()).toString();
			String dni = json.key("DNI").value(persona.getDni()).toString();
			
			persona.setCodigo(codigo);
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setDni(dni);
			return persona;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
