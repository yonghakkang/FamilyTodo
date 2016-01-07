package com.mapletree.zihover.familytodo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DaveMacPro on 2015-10-19.
 */
public class SmsReceiver extends BroadcastReceiver {

    static final String logTag = "SmsReceiver";
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION)) {
            //Bundel null check
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return;
            }

            //pdu object null check
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            if (pdusObj == null) {
                return;
            }

            String str = ""; // 인텐트에 넣기 위한 임의 String 변수 선언

            //message
            SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];
            for (int i = 0; i < pdusObj.length; i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                str = smsMessages[i].getMessageBody(); // 임의의 String 변수에 값 넣음
            }

            Toast.makeText(context,
                    str, Toast.LENGTH_SHORT).show();

            intent.putExtra("test_test", str);
            intent.setClass(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }

       /* if (intent.getAction().equals(ACTION)) {
            //Bundel 널 체크
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return;
            }

            //pdu 객체 널 체크
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            if (pdusObj == null) {
                return;
            }

            //message 처리
            SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];
            for (int i = 0; i < pdusObj.length; i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                Log.e(logTag, "NEW SMS " + i + "th");
                Log.e(logTag, "DisplayOriginatingAddress : "
                        + smsMessages[i].getDisplayOriginatingAddress());
                Log.e(logTag, "DisplayMessageBody : "
                        + smsMessages[i].getDisplayMessageBody());
                Log.e(logTag, "EmailBody : "
                        + smsMessages[i].getEmailBody());
                Log.e(logTag, "EmailFrom : "
                        + smsMessages[i].getEmailFrom());
                Log.e(logTag, "OriginatingAddress : "
                        + smsMessages[i].getOriginatingAddress());
                Log.e(logTag, "MessageBody : "
                        + smsMessages[i].getMessageBody());
                Log.e(logTag, "ServiceCenterAddress : "
                        + smsMessages[i].getServiceCenterAddress());
                Log.e(logTag, "TimestampMillis : "
                        + smsMessages[i].getTimestampMillis());

                Toast.makeText(context, smsMessages[i].getMessageBody(), 0).show();
            }
        }*/
    }

}
