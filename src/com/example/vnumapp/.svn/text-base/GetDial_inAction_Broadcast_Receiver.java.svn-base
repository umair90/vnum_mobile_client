package com.example.vnumapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GetDial_inAction_Broadcast_Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent in) {
		String action = in.getAction();
		Log.e("EEEEEEEEE", "BroadCastReceiver called");
		if (action.equalsIgnoreCase("VNUMDailinAction")) {

			Intent GetDialinAction = new Intent(context, CallingOne.class);
			GetDialinAction.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(GetDialinAction);

		} else if (action.equalsIgnoreCase("VNUMCallBackReminder")) {

			Intent intent = new Intent(context, CallBackReminder.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);

		}

	}
}