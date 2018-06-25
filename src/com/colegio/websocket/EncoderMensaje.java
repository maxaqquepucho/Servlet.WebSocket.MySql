package com.colegio.websocket;

import com.colegio.modelo.*;

import java.io.IOException;
import java.io.Writer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class EncoderMensaje implements Encoder.TextStream<Persona> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(Persona persona, Writer writer) throws EncodeException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			json.put("codigo", persona.getCodigo());
			json.put("nombre", persona.getNombre());
			json.put("apellido", persona.getApellido());
			json.put("DNI", persona.getDni());
			json.write(writer);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}finally {
			writer.close();
		}
		
	}

}
