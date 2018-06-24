package com.androidbegin.callbocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony.*;

import java.lang.reflect.Method;

public class CallBarring extends BroadcastReceiver
{
    private String number;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (!intent.getAction().equals("android.intent.action.PHONE_STATE"))
            return;
        else
        {
            number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(MainActivity.blockList.contains(new Blacklist(number)))
            {
                disconnectPhoneItelephony(context);
                return;
            }
        }
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void disconnectPhoneItelephony(Context context)
    {
        aidl telephonyService;
        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        try
        {
            Class c = Class.forName(telephony.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            telephonyService = (aidl) m.invoke(telephony);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}