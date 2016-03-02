package com.example.vnumapp;

import java.sql.Date;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.kit.WebSocketException;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContactsPlugin extends Plugin {

	SQLiteDatabase db;
	String virtNo;
	String cellNo;
	String cName = "ME";
	Long date = new Long("1346919543546");
	Date dt = new Date(date);
	String group = "100";
	String nameFile;
	String groupBlocked;
	String cellNo2;
	String email = "abc@xyz.com";
	String startTime;
	String endTime;
	String groupBlockedStartTime;
	String grpBlockedEndTime;

	@Override
	public PluginResult execute(String action, JSONArray args, String arg2) {
		// TODO Auto-generated method stub
		Log.e("Contact Plugin Called", action);
		if (action.equals("add")) {
			try {
				cellNo = args.getString(0);
				db = SQLiteDatabase
						.openDatabase(
								"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
								null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
				getContactData(cellNo);
				db.close();
				// Map<String, Object> cMap = new HashMap<String, Object>();
				JSONObject cMap = new JSONObject();
				cMap.put("virt_no", Connection.virtNo);
				cMap.put("cell_no", cellNo);
				cMap.put("c_name", cName);
				cMap.put("dt", dt);
				cMap.put("grp", group);
				cMap.put("name_file", nameFile);

				cMap.put("cell_no2", cellNo2);
				cMap.put("email", email);
				cMap.put("start_time", startTime);
				cMap.put("end_time", endTime);
				cMap.put("grp_blocked_start_time", groupBlockedStartTime);
				cMap.put("grp_blocked_end_time", grpBlockedEndTime);

				JSONObject addContact = new JSONObject();
				//addContact.put("ns", "com.ef.mobile_app.VnumServer");
				addContact.put("type", "addContact");
				addContact.put("virt_no", Connection.virtNo);
				addContact.put("real_no", cellNo);
				addContact.put("contact_map", cMap);
				Connection.btc.send(addContact.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);// try to send

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			} catch (WebSocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (action.equals("edit")) {
			try {
				cellNo = args.getString(0);
				db = SQLiteDatabase
						.openDatabase(
								"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
								null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
				getContactData(cellNo);
				db.close();
			

				JSONObject cMap = new JSONObject();
				cMap.put("virt_no", Connection.virtNo);
				cMap.put("cell_no", cellNo);
				cMap.put("c_name", cName);
				cMap.put("dt", dt);
				cMap.put("grp", group);
				cMap.put("name_file", nameFile);
				cMap.put("cell_no2", cellNo2);
				cMap.put("email", email);
				cMap.put("start_time", startTime);
				cMap.put("end_time", endTime);
				cMap.put("grp_blocked_start_time", groupBlockedStartTime);
				cMap.put("grp_blocked_end_time", grpBlockedEndTime);

				JSONObject editContact = new JSONObject();
				//editContact.put("ns", "com.ef.mobile_app.VnumServer");
				editContact.put("virt_no", Connection.virtNo);
				editContact.put("real_no", cellNo);
				editContact.put("contact_map", cMap);
				editContact.put("type", "editContact");
				Connection.btc.send(editContact.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);

			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}

		} else if (action.equals("delete")) {
			try {

				cellNo = args.getString(0);

				JSONObject deleteContact = new JSONObject();
				//deleteContact.put("ns", "com.ef.mobile_app.VnumServer");
				deleteContact.put("virt_no", Connection.virtNo);
				deleteContact.put("real_no", cellNo);
				deleteContact.put("type", "deleteContact");

				Connection.btc.send(deleteContact.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);// try to send

			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}
		} else if (action.equals("sendSms")) {

			Log.e("SendSmS", "SendSMS Plugin Called");

			int id;

			try {
				id = args.getInt(0);
				Log.e("sendSms MSG ID", " " + id);
				db = SQLiteDatabase
						.openDatabase(
								"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
								null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);

				
				JSONObject m = new JSONObject();
				m = (JSONObject) getSmsData(id);

				JSONObject sendSMS = new JSONObject();
				//sendSMS.put("ns", "com.ef.mobile_app.VnumServer");
				sendSMS.put("virt_no", Connection.virtNo);
				sendSMS.put("send_sms", m);
				sendSMS.put("type", "sendSMS");

				Connection.btc.send(sendSMS.toString(), "UTF-8");

				db.close();

				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}
		} else if (action.equals("sendSmsSchedule")) {

			int id;
			String send_dt = "";

			try {
				id = args.getInt(0);
				send_dt = args.getString(1);

				Log.e("sendSms MSG ID", " " + id);
				db = SQLiteDatabase
						.openDatabase(
								"data/data/com.example.vnumapp/app_database/file__0/0000000000000001.db",
								null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
				JSONObject m = new JSONObject();
				m = (JSONObject) getSmsData(id);
				db.close();
				m.put("send_dt", send_dt);

				JSONObject sendSchduledSms = new JSONObject();
				sendSchduledSms.put("virt_no", Connection.virtNo);
				sendSchduledSms.put("send_schduled_sms", m);
				sendSchduledSms.put("type", "sendSchduledSms");
				Connection.btc.send(sendSchduledSms.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}
		}else if (action.equals("getSMSinbox")) {    /// get_sms_log

			

			try {
				
				Log.e("sms Log", "getSMSinbox");
				JSONObject smsLog = new JSONObject();
				smsLog.put("virt_no", Connection.virtNo);
				smsLog.put("type", "getSMSinbox");
				Connection.btc.send(smsLog.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}
		}else if (action.equals("getSMSoutbox")) {    /// get_sms_log

			

			try {
				
				Log.e("sms Log", "getSMSoutbox");
				JSONObject smsLog = new JSONObject();
				smsLog.put("virt_no", Connection.virtNo);
				smsLog.put("type", "getSMSoutbox");
				Connection.btc.send(smsLog.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}
		}else if (action.equals("saveBlockSettings")) {
			Log.e("SaveBlock", "SaveBlock");

			String request;
			String option;
			String known;
			String group;
			String cellno;
			String start;
			String end;
			try {

				JSONObject saveBlockSettings = new JSONObject();
				saveBlockSettings.put("ns", "com.ef.mobile_app.VnumServer");

				JSONObject cMap = new JSONObject();

				request = args.getString(0);
				cMap.put("virt_no", Connection.virtNo);
				if (request.equals("all_call_block")) {
					cMap.put("request", request);
					option = args.getString(1);
					cMap.put("option_selected", option);
					if (option.equals("block_schedule")) {

						known = args.getString(2);
						start = args.getString(3);
						end = args.getString(4);
						if (known.equals("all_known")) {

							cMap.put("contact_list", 100);
							cMap.put("all_unknown_block", -100);
							cMap.put("known_block_start_time", start);
							cMap.put("known_block_end_time", end);
						} else if (known.equals("all_unknown")) {
							cMap.put("all_unknown_block", 100);
							cMap.put("contact_list", -100);

							cMap.put("unknown_block_start_time", start);
							cMap.put("unknown_block_end_time", end);
						} else if (known.equals("all")) {

							cMap.put("all_block", 100);
							cMap.put("all_start_time", start);
							cMap.put("all_end_time", end);
						}

						else {
							cMap.put("all_block", -100);
							// unblock
							cMap.put("option_selected", "all_block");

						}

					} else if (option.equals("block")) {
						known = args.getString(2);
						if (known.equals("all_known")) {
							cMap.put("contact_list", 100);
							cMap.put("all_unknown_block", -100);
						} else if (known.equals("all_unknown")) {
							cMap.put("all_unknown_block", 100);
							cMap.put("contact_list", -100);
						} else if (known.equals("all")) {
							cMap.put("all_block", 100);
							cMap.put("option_selected", "all_block");
						}

						else {
							cMap.put("all_block", -100);
							// unblock
							cMap.put("option_selected", "all_block");

						}

					}

					else if (option.equals("unblock")) {
						known = args.getString(2);
						if (known.equals("all_known")) {
							cMap.put("contact_list", -100);
							cMap.put("all_unknown_block", 100);
						} else if (known.equals("all_unknown")) {
							cMap.put("all_unknown_block", -100);
							cMap.put("contact_list", 100);
						} else if (known.equals("all")) {
							cMap.put("all_block", 100);
							cMap.put("option_selected", "all_block");
						}
					}

				} else if (request.equals("group_block")) {
					cMap.put("request", request);
					option = args.getString(1);
					cMap.put("option_selected", option);
					if (option.equals("block_schedule")) {
						group = args.getString(2);
						start = args.getString(3);
						end = args.getString(4);
						cMap.put("grp", group);
						cMap.put("grp_block_start_time", start);
						cMap.put("grp_block_end_time", end);
					} else if (option.equals("block")
							|| option.equals("unblock")) {
						group = args.getString(2);
						cMap.put("grp", group);
					}
				} else if (request.equals("single_no_block")) {
					cMap.put("request", request);
					option = args.getString(1);
					cMap.put("option_selected", option);
					if (option.equals("block_schedule")) {
						cellno = args.getString(2);
						start = args.getString(3);
						end = args.getString(4);
						cMap.put("cell_no", cellno);
						cMap.put("block_start_time", start);
						cMap.put("block_end_time", end);
					} else if (option.equals("block")
							|| option.equals("unblock")) {
						cellno = args.getString(2);
						cMap.put("cell_no", cellno);
					}

				}
				saveBlockSettings.put("virt_no", Connection.virtNo);
				saveBlockSettings.put("type", "saveBlockSettings");
				saveBlockSettings.put("block_settings", cMap);
				Connection.btc.send(saveBlockSettings.toString(), "UTF-8");
				return new PluginResult(PluginResult.Status.OK);// try to send

			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}
		} else if (action.equals("saveLogoffSchedule")) {

			try {

				String startTime = args.getString(0);
				String endTime = args.getString(1);

				Log.e("Starttime", startTime);
				Log.e("Endtime", endTime);

				JSONObject saveLogOffSchedule = new JSONObject();
				saveLogOffSchedule.put("virt_no", Connection.virtNo);
				saveLogOffSchedule.put("start_time", startTime);
				saveLogOffSchedule.put("end_time", endTime);
				saveLogOffSchedule.put("request", "saveLogoffSchedule");
				saveLogOffSchedule.put("type", "saveLogoffSchedule");
				Connection.btc.send(saveLogOffSchedule.toString(), "UTF-8");
				return new PluginResult(PluginResult.Status.OK);// try to send

			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new PluginResult(PluginResult.Status.ERROR);
			}
		} else if (action.equals("getCallLog")) {

			try {

				JSONObject getCallLog = new JSONObject();
				getCallLog.put("virt_no", Connection.virtNo);
				getCallLog.put("request", "getCallLog");
				getCallLog.put("type", "getCallLog");
				Connection.btc.send(getCallLog.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);// try to send
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (action.equals("clearCallLog")) {

			try {

				JSONObject clearCallLogs = new JSONObject();
				clearCallLogs.put("virt_no", Connection.virtNo);
				clearCallLogs.put("request", "clearCallLog");
				clearCallLogs.put("type", "clearCallLog");
				Connection.btc.send(clearCallLogs.toString(), "UTF-8");
				return new PluginResult(PluginResult.Status.OK);// try to send

			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

		else if (action.equals("updateSignature")) {

			try {

				String signature = args.getString(0);

				JSONObject updateSignature = new JSONObject();
				updateSignature.put("virt_no", Connection.virtNo);
				updateSignature.put("signature", signature);
				updateSignature.put("request", "updateSignature");
				updateSignature.put("type", "updateSignature");
				Connection.btc.send(updateSignature.toString(), "UTF-8");

				return new PluginResult(PluginResult.Status.OK);// try to send
				// the token to
				// the server
			} catch (WebSocketException ex) {
				Log.e("Debug", ex.getMessage());
				return new PluginResult(PluginResult.Status.ERROR);// log errors
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
		}

		else {
			return new PluginResult(PluginResult.Status.ERROR);
		}
		return null;

	}

	void getContactData(String cNo) {

		Cursor cursor = db.query("Contacts", null, "cell_no ='" + cNo + "'",
				null, null, null, null);

		StringBuilder ret = new StringBuilder("Saved Contacts: "
				+ cursor.getCount() + "\n\n");
		DatabaseUtils.dumpCursor(cursor);
		while (cursor.moveToNext()) {
		
			cName = cursor.getString(3);
			group = cursor.getString(5);
			nameFile = cursor.getString(6);
			cellNo2 = cursor.getString(8);
			email = cursor.getString(9);
			startTime = cursor.getString(10);
			endTime = cursor.getString(11);
			groupBlockedStartTime = cursor.getString(12);
			grpBlockedEndTime = cursor.getString(13);

			ret.append(virtNo + ":" + cName + ": " + dt + ": " + group + ": "
					+ cellNo2 + ": " + nameFile + ": " + email + ":"
					+ startTime + " : " + endTime + " : "
					+ groupBlockedStartTime + " : " + " : "
					+ groupBlockedStartTime + "\n");
		}
		Log.d("ContactData= ", ret.toString());
		cursor.close();

	}

	JSONObject getSmsData(int id) {

		try {

			Cursor cursor = db.query("sendsms", null, "id='" + id + "'", null,
					null, null, null);
			String to = "";
			String from = "";
			String msg = "";
			DatabaseUtils.dumpCursor(cursor);
			while (cursor.moveToNext()) {
				from = cursor.getString(1);
				to = cursor.getString(2);
				msg = cursor.getString(3);
			}

			JSONObject cMap = new JSONObject();
			cMap.put("to", to);
			cMap.put("from", Connection.virtNo);
			cMap.put("msg", msg);

			cursor.close();
			return cMap;
		} catch (JSONException ex) {

			Log.e("JSONException", ex.toString());
			return null;
		}

	}

}
