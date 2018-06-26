package com.colegio.websocket;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;

import com.colegio.interfaces.PersonaInterface;
import com.colegio.modelo.Persona;
import com.colegio.mysql.PersonaSQL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ServerEndpoint(value = "/ServerWebSocket",  decoders = {DecoderMensaje.class}, encoders = {EncoderMensaje.class})
public class ServerWebSocket {
	
	private static final List<Session> conectados = new ArrayList<>();
	
	@OnOpen
	public void inicio(Session session) {
		conectados.add(session);
		System.out.println("Esta conectado...");
	}
	
	@OnMessage
	public void mensaje(String message, Session userSession ) throws IOException, JSONException, EncodeException {
		//String userName = (String ) userSession.getUserProperties().get("nombreUsuario");
		//userSession.getUserProperties().put("nombreUsuario", userName);
		JsonParser parser = new JsonParser();
		JsonObject gson = parser.parse(message).getAsJsonObject();
		String accion = gson.get("accion").getAsString();
		String codigo = "";
		String nombre = "";
		String apellido = "";
		String dni = "";
		PersonaInterface personaSQL = new PersonaSQL();
		switch (accion) {
		case "Agregar":
				codigo = gson.get("codigo").getAsString();
				nombre = gson.get("nombre").getAsString();
				apellido = gson.get("apellido").getAsString();
				dni = gson.get("dni").getAsString();
			
				Persona persona = new Persona();
				persona.setCodigo(codigo);
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				persona.setDni(dni);
				
				if (personaSQL.agregar(persona)) {
					System.out.println("Se agrego exitosamente un usuario: "+codigo);
					for(Session session : conectados) {
						session.getBasicRemote().sendText(message);
						
					}
				} else {
					System.out.println("arror al agregar");
					 userSession.getBasicRemote().sendText("{\"accion\":\"alertaAgregar\"}");
					 
				}
			break;
		case "Eliminar":
				codigo = gson.get("codigo").getAsString();
				persona = new Persona();
				persona.setCodigo(codigo);
				if (personaSQL.eliminar(codigo)) {
					System.out.println("Se elimino un usuario satisfactoriamente: "+codigo);
					for(Session session : conectados) {
						session.getBasicRemote().sendText(message);
					}
				} else {
					System.out.println("arror al Eliminar");
					 userSession.getBasicRemote().sendText("{\"accion\":\"alertaEliminar\"}");
				}
			break;
		case "Editar":
				codigo = gson.get("codigo").getAsString();
				nombre = gson.get("nombre").getAsString();
				apellido = gson.get("apellido").getAsString();
				dni = gson.get("dni").getAsString();
			
				persona = new Persona();
				persona.setCodigo(codigo);
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				persona.setDni(dni);
				if (personaSQL.actulizar(persona)) {
					System.out.println("Se edito un usuario satisfactoriamente: "+codigo);
					for(Session session : conectados) {
						session.getBasicRemote().sendText(message);
					}
				} else {
					System.out.println("arror al Eliminar");
					 userSession.getBasicRemote().sendText("{\"accion\":\"alertaEditar\"}");
				}
				
			break;
		default:
			System.out.println("NO EXISTE ESTA ACCION EN EL OBJETO RECIBIDO POR EL CLIENTE");
			break;
		}
		
		
		
		
		System.out.println(codigo+"\n "+nombre+"\n "+apellido);
		
		
		
	}
	/*@OnMessage
	public void mensaje( Persona persona)throws IOException, EncodeException {
		System.out.println(persona.getCodigo());
		System.out.println(persona.getNombre());
		System.out.println(persona.getApellido());
		System.out.println(persona.getDni());
		for(Session session :  conectados) {
			session.getBasicRemote().sendObject(persona);
		}
	}*/
	
	@OnClose
	public void Salir(Session session) {
		conectados.remove(session);
		System.out.println("se desconecto la session : "+session);
	}
	
	
	/*private String crearObjetoJson(String userName, String mensaje) {
		JSONObject json = new JSONObject();
		try {
			json.put("accion", mensaje);
			json.put("accion2", mensaje+"2");
			System.out.println(json.toString());
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
