package com.mapletree.zihover.familytodo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.mapletree.zihover.familytodo.model.Book;
import com.mapletree.zihover.familytodo.sqlite.MySQLiteHelper;

import java.util.List;

import io.realm.Realm;
import model.AutoIncrementHelper;
import model.Expense;
import model.Person;

/**
 * Created by DaveMacPro on 2015-10-19.
 */
public class SmsReceiver extends BroadcastReceiver {

    static final String logTag = "SmsReceiver";
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    private Realm realm;
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION)) {
            //Bundel null check
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return;
            }

            //pdu object null check
            Log.d("t","--------------------dsf------------------------");
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            if (pdusObj == null) {
                return;
            }

            String str = ""; // 인텐트에 넣기 위한 임의 String 변수 선언
            String phone = "";


            //message
            SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];
            for (int i = 0; i < pdusObj.length; i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                str = smsMessages[i].getMessageBody(); // 임의의 String 변수에 값 넣음

                phone = smsMessages[i].getDisplayOriginatingAddress();

            }

            Toast.makeText(context,
                    str, Toast.LENGTH_SHORT).show();

            intent.putExtra("test_test", str);
            intent.setClass(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
           // context.startActivity(intent);


            sendNotification(context,str,phone);

            addSMS(context,str,phone);
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

    private void addSMS(Context context,String msg, String phonNumser){
        if(realm == null){
            realm = Realm.getInstance(context);
        }

        int index = AutoIncrementHelper.getNextIndex(context, Expense.class);

        Expense exp = SmsParser.parse(msg);
        exp.setId(index);
        realm.beginTransaction();

        // Add a Expense Info
        realm.copyToRealmOrUpdate(exp);

        /*Expense exp = realm.createObject(Expense.class);
        exp.setId(index);
        exp.setPlace(msg);
        exp.setCard("KB꾹민");
        exp.setDate("16/01/21");
        exp.setValue("30,000원");*/

        // When the transaction is committed, all changes a synced to disk.
        realm.commitTransaction();




        //MySQLiteHelper db = new MySQLiteHelper(context);

        // add Books
       // db.addBook(new Book(msg, phonNumser));

    }
    private RemoteViews getRemoteViews (Context context,String msg, String phonNumser){
        //create a remote view from widget layout
        RemoteViews views = new RemoteViews(context.getApplicationContext().getPackageName(), R.layout.noti_layout);


       // ImageView layoutGet=(ImageView) ((Activity)context).findViewById(R.id.imageView);
      // ViewGroup.LayoutParams layParamsGet= layoutGet.getLayoutParams();
        int width=200;//layParamsGet.width;
        int height=100;//layParamsGet.height;

        //create a bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        //create a canvas from existant bitmap that will be used for drawing
        Canvas canvas = new Canvas(bitmap);

        //create new paint
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(0xff74AC23);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);

        //draw circle
        canvas.drawRect(0, 0, width-60, 40, p);
        canvas.drawText("테스트",width-60,40,p);

        //set our bitmap to view
        views.setImageViewBitmap(R.id.imageView, bitmap);

        return views;
    }
    private void sendNotification(Context context, String msg, String phonNumser){

        RemoteViews rv = getRemoteViews(context,msg,phonNumser);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.abc_btn_check_material)
                        .setContent(rv);
                        //.setContentTitle(msg)
                        //.setContentText(msg);
        // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(context, ResultActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ResultActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
                mNotificationManager.notify(1, mBuilder.build());
    }

}
