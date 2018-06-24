package com.colegio.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.json.JSONObject;

public class ServerWebSocket {
	
	Set<Session> chatroonUsers = Collections.synchronizedSet(new HashSet<Session>());
	@OnOpen
	public void handleOpen(Session userSession) {
		System.out.println(chatroonUsers);
		chatroonUsers.add(userSession);
		System.out.println(userSession);
		
	}
	@OnMessage
	public void handleMessage(String message ,Session userSession) throws IOException {
		String userName= (String) userSession.getUserProperties().get("username");
		if(userName == null) {
			userSession.getUserProperties().put("username", message);
			userSession.getBasicRemote().sendText(buildJsonData("System", "Estas Conectado ahora como "+ message));
		}else {
			Iterator<Session> iterador = chatroonUsers.iterator();
			while(iterador.hasNext()) {
				System.out.println(iterador);
				System.out.println(chatroonUsers);
				iterador.next().getBasicRemote().sendText(buildJsonData(userName, message));
			}
			System.out.println("esta aqui");
		}
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		chatroonUsers.remove(userSession);
		
	}	
	private String buildJsonData(String userName, String message) {
		
		/*JsonObject jsonObject = Json.createObjectBuilder().add("message", userName + ": "+message).build();
		StringWriter stringWriter = new StringWriter();
		try(JsonWriter jsonWriter = Json.createWriter(stringWriter)){
			jsonWriter.writer(jsonObject);
		}
		return stringWriter.toString();*/
		
		JSONObject json = new JSONObject();
		try {
			json.put("message", message);
			System.out.println(json.put("message",message).toString());
			return json.toString();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

}
