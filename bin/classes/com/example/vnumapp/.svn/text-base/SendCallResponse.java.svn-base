package com.example.vnumapp;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.client.token.BaseTokenClient;
import org.jwebsocket.kit.WebSocketException;


import android.util.Log;

public class SendCallResponse extends Plugin {

	public static BaseTokenClient btc = new BaseTokenClient();
	int status = -1;

	@Override
	public PluginResult execute(String action, JSONArray arg1, String arg2) {
		// TODO Auto-generated method stub
		Log.e("SendCallResponse Plugin", action);
		if (action.equals("Accept")) {
			status = 0;

			try {
				 JSONObject getDial =new JSONObject();
				   
						
					getDial.put("status", status);// my number
					getDial.put("virt_no", Connection.virtNo);
					getDial.put("caller_no", Connection.caller_no);
					getDial.put("request", "get_dialin_action");
					
					btc.send(getDial.toString(), "UTF-8");
				
				Log.e("Token Sent", getDial.toString());
				
				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.i("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}
			catch (JSONException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);    
			}
			

		} else if (action.equals("Reject")) {
			status = 10;

			try {
				 JSONObject getDial =new JSONObject();
				    
						
					getDial.put("status", status);// my number
					getDial.put("virt_no", Connection.virtNo);
					getDial.put("caller_no", Connection.caller_no);
					getDial.put("request", "get_dialin_action");
					
					btc.send(getDial.toString(), "UTF-8");
				
				Log.e("Token Sent", getDial.toString());
	
				return new PluginResult(PluginResult.Status.OK);

			} catch (WebSocketException ex) {
				Log.i("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}
			catch (JSONException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);  
			}
			
		} else if (action.equals("Block")) {
			status = 15;

			try {
				 JSONObject getDial =new JSONObject();
				
						
					getDial.put("status", status);// my number
					getDial.put("virt_no", Connection.virtNo);
					getDial.put("caller_no", Connection.caller_no);
					getDial.put("request", "get_dialin_action");
					
					Log.e("Token Sent", getDial.toString());
					btc.send(getDial.toString(), "UTF-8");
			
				return new PluginResult(PluginResult.Status.OK);

			} catch (WebSocketException ex) {
				Log.i("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}
			catch (JSONException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}

		} else if (action.equals("NextExten")) {
			status = 12;

			try {
				JSONObject getDial =new JSONObject();
			   
					
				getDial.put("status", status);// my number
				getDial.put("virt_no", Connection.virtNo);
				getDial.put("caller_no", Connection.caller_no);
				getDial.put("request", "get_dialin_action");
				
				Log.e("Token Sent", getDial.toString());
				btc.send(getDial.toString(), "UTF-8");
				
				return new PluginResult(PluginResult.Status.OK);

			} catch (WebSocketException ex) {

				Log.i("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}
			catch (JSONException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);   
			}

		} else if (action.equals("VM")) {
			status = 13;

			try {

				JSONObject getDial =new JSONObject();
			   
					
				getDial.put("status", status);// my number
				getDial.put("virt_no", Connection.virtNo);
				getDial.put("caller_no", Connection.caller_no);
				getDial.put("request", "get_dialin_action");
				
				Log.e("Token Sent", getDial.toString());
				btc.send(getDial.toString(), "UTF-8");
		
				return new PluginResult(PluginResult.Status.OK);

			} catch (WebSocketException ex) {
				Log.i("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}
			catch (JSONException ex) {
				Log.e("Debug", ex.getMessage());// log errors
			    }
			

		} else if (action.equals("FWDOP")) {
			status = 14;

			try {
				JSONObject getDial =new JSONObject();
			  
					
				getDial.put("status", status);// my number
				getDial.put("virt_no", Connection.virtNo);
				getDial.put("caller_no", Connection.caller_no);
				getDial.put("request", "get_dialin_action");
				
				Log.e("Token Sent", getDial.toString());
				btc.send(getDial.toString(), "UTF-8");

			
				return new PluginResult(PluginResult.Status.OK);

			} catch (WebSocketException ex) {
				Log.i("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}
			catch (JSONException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);   
			}
			

		} else {
			return new PluginResult(PluginResult.Status.ERROR);

		}
		return null;

	}

}
