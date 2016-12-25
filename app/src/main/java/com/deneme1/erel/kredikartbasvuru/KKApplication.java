package com.deneme1.erel.kredikartbasvuru;

import android.app.Application;

import com.deneme1.erel.kredikartbasvuru.model.PushNotification;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

/**
 * Created by Melike on 25.12.2016.
 */

public class KKApplication extends Application {

    public static PushNotification pushNotification = null;

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.startInit(this)
                .setNotificationReceivedHandler(new notificationOpenedHandler())
                .init();
    }

    private class notificationOpenedHandler implements OneSignal.NotificationReceivedHandler {

        @Override
        public void notificationReceived(OSNotification notification) {
            String title = notification.payload.title;
            String message = notification.payload.body;

            if (message != null) {
                pushNotification = new PushNotification(title, message);
            }
        }
    }

}
