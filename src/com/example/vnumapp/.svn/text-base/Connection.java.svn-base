package com.example.vnumapp;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.*;


import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.api.WebSocketClientEvent;
import org.jwebsocket.api.WebSocketClientTokenListener;
import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.client.plugins.rpc.Rpc;
import org.jwebsocket.client.plugins.rpc.RpcListener;
import org.jwebsocket.client.plugins.rpc.Rrpc;
import org.jwebsocket.client.token.BaseTokenClient;
import org.jwebsocket.kit.WebSocketException;
import org.jwebsocket.token.Token;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Connection extends Plugin implements WebSocketClientTokenListener {

	public static String virtNo = null;
	public static String password_str = null;
	public static boolean isConnected = false;
	public static boolean loginSucces = false;
	public String hashed_password = null;

	// EventDataSQLHelper eventsData;
	public static BaseTokenClient btc = new BaseTokenClient();
	public static Context context;
	SQLiteDatabase db;
	Cursor cursor;

	static public Token aToken;
	public Object[] mapData = new Object[39];

	public static String caller_no;

	//Executes the request and returns PluginResult
		public PluginResult execute(String action, JSONArray args, String callbackId) {

		Log.e("Connection Plugin Called", action);
		if (action.equals("connect")) {
			try {
				virtNo = args.getString(0);
				password_str = args.getString(1);
				Log.e(virtNo, password_str);

				if (!btc.isConnected()) {

					btc.addListener(this);// add this class as a listener
					btc.addListener(new RpcListener());// add an rpc listener
					Rpc.setDefaultBaseTokenClient(btc);// set it to the default

					Rrpc.setDefaultBaseTokenClient(btc);// same here

					connect();
				}

				login();
				Thread.sleep(3000);

				if (loginSucces) {
					getContactsList(virtNo);
					return new PluginResult(PluginResult.Status.OK);
				} else {
					return new PluginResult(PluginResult.Status.ERROR);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.OK);
			}
		} else if (action.equals("getCallerNo")) {

			return new PluginResult(PluginResult.Status.OK, caller_no);
		} else {
			return new PluginResult(PluginResult.Status.INVALID_ACTION);
		}
		//
	}

	public void connect() {      

		try {

			String connString = "wss://192.168.12.153:9797/jWebSocket/jWebSocket";

			Log.e("Debug", "connecting to " + connString + " ...");

			btc.open(connString);

		} catch (Exception e) {
			Log.e("Debug", "Error while connecting..." + e.getMessage());// log

		}

	}

	public void login() {             //Sending Login Packet
		hashed_password = md5(password_str);

		try {
			JSONObject Login = new JSONObject();
		//	Login.put("ns", "com.ef.mobile_app.VnumServer");
			Login.put("type", "login");
			Login.put("virt_no", virtNo);
			Login.put("pwd", hashed_password);
			Login.put("request", "login");

			btc.send(Login.toString(), "UTF-8");
		} catch (WebSocketException ex) {
			Log.e("Debug", ex.getMessage());
		} catch (JSONException ex) {
			Log.e("Debug", ex.getMessage());
		}

	}

	public void getContactsList(String vNum) {   //Sending contact list request

		try {
			JSONObject getContacts = new JSONObject();
			//getContacts.put("ns", "com.ef.mobile_app.VnumServer");
			getContacts.put("virt_no", vNum);
			getContacts.put("type", "getContactList");

			;
			btc.send(getContacts.toString(), "UTF-8");
													

		} catch (WebSocketException ex) {
			Log.e("Debug", ex.getMessage());
		} catch (JSONException ex) {
			Log.e("Debug", ex.getMessage());
		}

	}

	public static String md5(String input) {  //Message-Digest algorithm 5
                                              //For Converting Password into Hash Values
		String md5 = null;

		if (null == input)
			return null;

		try {

			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}

	public boolean checkNetworkConnectivity() {  //Checking Network Connectivity
		boolean isConnected = false;
		final ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		final android.net.NetworkInfo wifi = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final android.net.NetworkInfo mobile = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (wifi.isAvailable() && wifi.isConnected()) {

			isConnected = true;
		} else if (mobile.isAvailable() && mobile.isConnected()) {

			isConnected = true;
		} else {

			isConnected = false;
		}
		return isConnected;
	}

	@Override
	public void processToken(WebSocketClientEvent arg0, Token arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processClosed(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processOpened(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processOpening(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processPacket(WebSocketClientEvent arg0, WebSocketPacket arg1) {
		// TODO Auto-generated method stub
		Message lMsg = new Message();
		lMsg.what = 1;
		lMsg.obj = arg1;
		messageHandler.sendMessage(lMsg);

	}

	@Override
	public void processReconnecting(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub

	}

	private Handler messageHandler = new Handler() {
		@Override
		public void handleMessage(Message aMessage) { // This will receive packets of login, contact list and call log
			{

  				WebSocketPacket wsp = (WebSocketPacket) aMessage.obj;
				Log.d("Packet Received", wsp.getString());
				JSONObject response = null;
				String type = "";
				try {
					response = new JSONObject(wsp.getString());
					type = response.getString("type");
					;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (type.equals("login")) {
					int status = 0;
					try {

						status = response.getInt("status");

					} catch (JSONException e) {
						e.printStackTrace();
					}
					Log.i("VM", status + "");
					if (status == -2) {
						// loginSucces = false;
						Toast.makeText(context, "Invalid number entered",
								Toast.LENGTH_LONG).show();

					}

					else if (status == -3) {
						loginSucces = false;
						Toast.makeText(context, "Invalid Password Entered",
								Toast.LENGTH_LONG).show();

					}

					else if (status == 1) {

						loginSucces = true;
						try {
							virtNo = response.getString("virt_no");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Toast.makeText(context, "Validated Successfully",
								Toast.LENGTH_LONG).show();

					}

					else if (status == -1) {

						Toast.makeText(context,
								"Login Failed. Please try again",
								Toast.LENGTH_LONG).show();

					}
				} else if (type.equals("getContactList")) {

					JSONArray contactArray = null;
					JSONObject m1 = null;
					try {

						contactArray = (JSONArray) response.get("contact_list");
						m1 = (JSONObject) contactArray.get(0);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.d("MAP 1=", m1.toString());

					JSONObject m = new JSONObject();
					int count = contactArray.length();
					Log.e("Size of List=  ", count + "");

					db = SQLiteDatabase
							.openDatabase(
									"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
									null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
					String virt_no = "", cell_no = "", c_name = "", name_file = "", cell_no2 = "", email = "", start_time = "", end_time, grp_blocked_start_time, grp_blocked_end_time;
					String dt = "";
					int grp = 0;

					for (int i = 0; i < count; i++) {
						try {
							m = (JSONObject) contactArray.get(i);
							Log.e("Value of Map", m.toString());
							virt_no = m.getString("virt_no");
							cell_no = m.getString("cell_no");
							c_name = m.getString("c_name");

							dt = (String) m.get("dt");

							grp = (Integer) m.get("grp");

							cell_no2 = m.getString("cell_no2");
							email = m.getString("email");
							;
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Log.e("getContactList Data", " virt_no= " + virt_no
								+ " cell_no= " + cell_no + " c_name= " + c_name
								+ " dt= " + dt + " grp= " + grp + " cell_no2= "
								+ cell_no2 + " email= " + email);

						ContentValues values = new ContentValues();
						Log.d("======", "");
						values.put("id", i);
						values.put("virt_no", virt_no);
						values.put("cell_no", cell_no);
						values.put("c_name", c_name);
						values.put("dt", dt);
						values.put("grp", Integer.toString(grp));
						values.put("cell_no2", cell_no2);
						values.put("email", email);

						db.insert("Contacts", null, values);

					}

					db.close();

				}else if (type.equals("getSMSinbox")) {

					JSONArray inboxArray = null;
					JSONObject m1 = null;
					try {

						inboxArray = (JSONArray) response.get("sms_inbox");
						m1 = (JSONObject) inboxArray.get(0);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.d("MAP 1=", m1.toString());

					JSONObject m = new JSONObject();
					int count = inboxArray.length();
					Log.e("Size of List=  ", count + "");

					db = SQLiteDatabase
							.openDatabase(
									"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
									null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
					String sender = "", receiver = "", msg = "";
					String dt = "";

					for (int i = 0; i < count; i++) {
						try {
							m = (JSONObject) inboxArray.get(i);
							Log.e("Value of Map", m.toString());
							sender = m.getString("sender");
							receiver = m.getString("receiver");
							msg = m.getString("msgdata");
							dt = (String) m.get("msgtime");

						//	dt = (String) m.get("dt");

						//	grp = (Integer) m.get("grp");

						//	cell_no2 = m.getString("cell_no2");
						//	email = m.getString("email");
							//;
						} catch (JSONException e) {
							e.printStackTrace();
						}
				//		Log.e("getContactList Data", " virt_no= " + virt_no
					//			+ " cell_no= " + cell_no + " c_name= " + c_name
					//			+ " dt= " + dt + " grp= " + grp + " cell_no2= "
					//			+ cell_no2 + " email= " + email);

						ContentValues values = new ContentValues();
						Log.d("======", "");
						values.put("id", i);
						values.put("sender", sender);
						values.put("receiver", receiver);
						values.put("msg", msg);
						values.put("time", dt);
					//	values.put("dt", dt);
					//	values.put("grp", Integer.toString(grp));
					///	values.put("cell_no2", cell_no2);
					//	values.put("email", email);

						db.insert("receivesms", null, values);

					}

					db.close();

				}else if (type.equals("getSMSoutbox")) {

					JSONArray inboxArray = null;
					JSONObject m1 = null;
					try {

						inboxArray = (JSONArray) response.get("sms_outbox");
						m1 = (JSONObject) inboxArray.get(0);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.d("MAP 1=", m1.toString());

					JSONObject m = new JSONObject();
					int count = inboxArray.length();
					Log.e("Size of List=  ", count + "");

					db = SQLiteDatabase
							.openDatabase(
									"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
									null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
					String sender = "", receiver = "", msg = "";
					String dt = "";

					for (int i = 0; i < count; i++) {
						try {
							m = (JSONObject) inboxArray.get(i);
							Log.e("Value of Map", m.toString());
							sender = m.getString("sender");
							receiver = m.getString("receiver");
							msg = m.getString("msgdata");
							dt = (String) m.get("msgtime");

						//	dt = (String) m.get("dt");

						//	grp = (Integer) m.get("grp");

						//	cell_no2 = m.getString("cell_no2");
						//	email = m.getString("email");
							//;
						} catch (JSONException e) {
							e.printStackTrace();
						}
				//		Log.e("getContactList Data", " virt_no= " + virt_no
					//			+ " cell_no= " + cell_no + " c_name= " + c_name
					//			+ " dt= " + dt + " grp= " + grp + " cell_no2= "
					//			+ cell_no2 + " email= " + email);

						ContentValues values = new ContentValues();
						Log.d("======", "");
						values.put("id", i);
						values.put("sender", sender);
						values.put("receiver", receiver);
						values.put("msg", msg);
						values.put("time", dt);
					//	values.put("dt", dt);
					//	values.put("grp", Integer.toString(grp));
					///	values.put("cell_no2", cell_no2);
					//	values.put("email", email);

						db.insert("sendsms", null, values);

					}

					db.close();

				}else if (type.equals("getCallLog")) {

					JSONArray calllogArray = null;
					
					try {

						calllogArray = (JSONArray) response.get("call_log");
						

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

					JSONObject m = new JSONObject();
					int count = calllogArray.length();
					Log.e("Size of List=  ", count + "");

					db = SQLiteDatabase
							.openDatabase(
									"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
									null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
					String src, dst, dispose;
					String dt = "";

					for (int i = 0; i < count; i++) {
						try {
							m = (JSONObject) calllogArray.get(i);
							src = (String) m.get("src");
							dst = (String) m.get("dst");
							if (m.get("calldate") != null) {

								dt = (String) m.get("calldate");

							}

							dispose = (String) m.get("disposition");

							Log.e("Contacts MAP Data", " Source= " + src
									+ " destination= " + dst + " dt= " + dt
									+ " disposition= " + dispose);
							if (virtNo.equals(src)) {
								ContentValues values = new ContentValues();
								values.put("number", dst);
								values.put("date", dt);
								values.put("type", "dialed");
								db.insert("call_log", null, values);

							} else if (virtNo.equals(dst)) {
								if (dispose.equals("ANSWERED")) {

									ContentValues values = new ContentValues();
									values.put("number", src);
									values.put("date", dt);
									values.put("type", "received");
									db.insert("call_log", null, values);

								}

								else {

									ContentValues values = new ContentValues();
									values.put("number", src);
									values.put("date", dt);
									values.put("type", "missed");
									db.insert("call_log", null, values);

								}
							}

						}

						catch (JSONException e) {
							e.printStackTrace();
						}
					}

					db.close();

				}

			}

		}
	};

}
