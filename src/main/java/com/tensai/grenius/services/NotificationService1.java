package com.tensai.grenius.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.tensai.grenius.GREniusApplication;
import com.tensai.grenius.R;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.di.component.ServiceComponent;
import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.receivers.NetworkChangeReceiver;
import com.tensai.grenius.ui.home.HomeActivity;
import com.tensai.grenius.ui.login.LoginActivity;

import java.util.Date;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rishabhpanwar on 22/08/17.
 */

public class NotificationService1 extends BaseService{

    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private static int NOTIFICATION_ID = 1;
    Notification notification;

    @Inject
    DataManager dataManager;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService1(String name) {
        super(name);
    }

    public NotificationService1() {
        super("NotificationService1");
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        serviceComponent = DaggerServiceComponent.builder()
//                .applicationComponent(((GREniusApplication) getApplication()).getApplicationComponent())
//                .build();
        getServiceComponent().inject(this);
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //super(intent);
        Log.i("ABCDEF","Notifications sent1.");
        dataManager.getWordOfDay(dataManager.getCurrentUserid(),dataManager.getSessionId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, WordOfDay>() {
                    @Override
                    public WordOfDay call(Throwable throwable) {
                        //enable network change
                        ComponentName receiver = new ComponentName(getApplicationContext(), NetworkChangeReceiver.class);
                        PackageManager pm = getApplicationContext().getPackageManager();

                        pm.setComponentEnabledSetting(receiver,
                                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                PackageManager.DONT_KILL_APP);
                        return null;
                    }
                })
                .subscribe(new Action1<WordOfDay>() {
                    @Override
                    public void call(WordOfDay wordOfDay) {
                        if(wordOfDay==null){
                            wordOfDay=dataManager.getSavedWordOfDay();
                            Log.d("ABCDEF",wordOfDay.getDate()+" "+new Date().getDate());

                            //enable network change
                            ComponentName receiver = new ComponentName(getApplicationContext(), NetworkChangeReceiver.class);
                            PackageManager pm = getApplicationContext().getPackageManager();

                            pm.setComponentEnabledSetting(receiver,
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                    PackageManager.DONT_KILL_APP);
                        }
                        else{
                            Log.d("ABCDEF","Received in notif service"+wordOfDay.getDate());
                            //disable network change
                            ComponentName receiver = new ComponentName(getApplicationContext(), NetworkChangeReceiver.class);
                            PackageManager pm = getApplicationContext().getPackageManager();

                            pm.setComponentEnabledSetting(receiver,
                                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                    PackageManager.DONT_KILL_APP);
                            notifyCustom(wordOfDay);
                        }
                    }
                });
    }
    public void notifyCustom(WordOfDay wordOfDay){

        String word=wordOfDay.getWord();
        String wod=Character.toUpperCase(word.charAt(0)) + word.substring(1);
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("test", "test");
        mIntent.putExtras(bundle);
        pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Resources res = this.getResources();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        notification = new NotificationCompat.Builder(this)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setSmallIcon(R.drawable.ic_smallicon)
                .setTicker("Word of Day")
                .setAutoCancel(true)
                .setPriority(8)
                .setSound(soundUri)
                .setContentTitle("Word of the Day:- "+wod)
                .setContentText(wordOfDay.getMeaning())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(wordOfDay.getMeaning()))
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
        notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
        notification.ledARGB = 0xFFFFA500;
        notification.ledOnMS = 800;
        notification.ledOffMS = 1000;
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

}