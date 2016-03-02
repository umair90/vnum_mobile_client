package com.example.vnumapp;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.kit.WebSocketException;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class CallBack extends Plugin {

	int status = -1;
	final Handler handler = new Handler();
	public static Timer timer = new Timer();
	TimerTask mTimerTask;

	@Override
	//Executes the request and returns PluginResult
	public PluginResult execute(String action, JSONArray arg1, String arg2) {
		// TODO Auto-generated method stub
		Log.e("Call Back Plugin", action);
		if (action.equals("Five")) {
			status = 1005;

			int t = 2000;
			doTimerTask(t);

			try {
				JSONObject getDialIn = new JSONObject();

				getDialIn.put("virt_no", Connection.virtNo);
				getDialIn.put("status", status);
				getDialIn.put("request", "get_dialin_action");
				getDialIn.put("type", "get_dialin_action");
				Connection.btc.send(getDialIn.toString(), "UTF-8");

				Log.e("Token Sent", getDialIn.toString());

				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}

			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}

		} else if (action.equals("Ten")) {
			status = 1010;
			int t = 10 * 60 * 1000;
			doTimerTask(t);

			try {
				JSONObject getDialIn = new JSONObject();

				getDialIn.put("virt_no", Connection.virtNo);
				getDialIn.put("status", status);
				getDialIn.put("request", "get_dialin_action");
				getDialIn.put("type", "get_dialin_action");
				Connection.btc.send(getDialIn.toString(), "UTF-8");

				Log.e("Token Sent", getDialIn.toString());
				// Connection.btc.sendToken(getDialInToken);
				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}

		} else if (action.equals("Fifteen")) {
			status = 1015;
			int t = 15 * 60 * 1000;
			doTimerTask(t);

			try {

				JSONObject getDialIn = new JSONObject();

				getDialIn.put("virt_no", Connection.virtNo);
				getDialIn.put("status", status);
				getDialIn.put("request", "get_dialin_action");
				getDialIn.put("type", "get_dialin_actiont");
				Connection.btc.send(getDialIn.toString(), "UTF-8");

				Log.e("Token Sent", getDialIn.toString());

				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}

		} else if (action.equals("Twenty")) {
			status = 1020;
			int t = 20 * 60 * 1000;
			doTimerTask(t);

			try {

				JSONObject getDialIn = new JSONObject();

				getDialIn.put("virt_no", Connection.virtNo);
				getDialIn.put("status", status);
				getDialIn.put("request", "get_dialin_action");
				getDialIn.put("type", "get_dialin_action");
				Connection.btc.send(getDialIn.toString(), "UTF-8");

				Log.e("Token Sent", getDialIn.toString());

				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());// log errors
				return new PluginResult(PluginResult.Status.ERROR);
			}

			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}

		} else {
			return null;

		}
	}

	public void doTimerTask(long time) { // Reminder Time of call back i.e (5 or
											// 10 or 15 or 20 min)

		mTimerTask = new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {

						Intent intent = new Intent();
						intent.setAction("VNUMCallBackReminder");
						Connection.context.sendBroadcast(intent);
						Log.d("TIMER", "TimerTask run");
					}
				});
			}
		};
		timer.schedule(mTimerTask, time, 24 * 60 * 60 * 1000);

	}

}
