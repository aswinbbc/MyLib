package com.thefuturalabs.helper;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import com.google.android.material.snackbar.Snackbar;

public interface MyMessage {

   Context context= VolleyAppController.getInstance();

    static void ShortToast(String msg){
        Toast.makeText(VolleyAppController.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }

    static void LongToast(String msg){
        Toast.makeText(VolleyAppController.getInstance(),msg,Toast.LENGTH_LONG).show();
    }

    static void dialogBox(Context context,String title,String content,final OnYes onYesClick){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content)
                .setTitle(title)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onYesClick.onSuccess();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    static void showSnack(Activity activity,String content,final OnYes onClick){
        View parentLayout = activity.findViewById(android.R.id.content);
        Snackbar.make(parentLayout, content, Snackbar.LENGTH_LONG)
                .setAction("CLICK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.onSuccess();
                    }
                })
                .setActionTextColor(activity.getResources().getColor(android.R.color.holo_red_light ))
                .show();
    }

    static void Notification(String title, String message,Class contextClass) {

            NotificationManager mNotificationManager;

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context, "notify_001");
            Intent ii = new Intent(context, contextClass);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, ii, 0);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();

            bigText.setBigContentTitle(title);
            bigText.setSummaryText(message);

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(message);
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigText);

            mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "Your_channel_id";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(channelId);
            }

            mNotificationManager.notify(0, mBuilder.build());

    }

    interface OnYes{
        void onSuccess();
    }
}
