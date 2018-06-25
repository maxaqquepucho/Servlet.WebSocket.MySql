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

import org.json.JSONObject;

import com.colegio.modelo.Persona;

@ServerEndpoint(value = "/ServerWebSocket",  decoders = {DecoderMensaje.class}, encoders = {EncoderMensaje.class})
public class ServerWebSocket {
	
	private static final List<Session> conectados = new ArrayList<>();
	
	@OnOpen
	public void inicio(Session session) {
		conectados.add(session);
		System.out.println("Esta conectado...");
	}
	
	/*@OnMessage
	public void mensaje(String message, Session userSession ) throws IOException {
		String userName = (String ) userSession.getUserProperties().get("nombreUsuario");
		//userSession.getUserProperties().put("nombreUsuario", userName);
		for(Session session : conectados) {
			session.getBasicRemote().sendText(crearObjetoJson(userName, message));
		}
	}*/
	
	@OnMessage
	public void mensaje(String message, Persona persona)throws IOException {
		System.out.println(persona.getCodigo());
		System.out.println(persona.getNombre());
		System.out.println(persona.getApellido());
		System.out.println(persona.getDni());
		for(Session session :  conectados) {
			try {
				session.getBasicRemote().sendObject(message);
			} catch (EncodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
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
