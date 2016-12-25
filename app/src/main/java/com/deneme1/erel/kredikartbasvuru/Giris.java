package com.deneme1.erel.kredikartbasvuru;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by PC on 13.12.2016.
 */

public class Giris extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris);

        if(KKApplication.pushNotification != null){
            AlertDialog alertDialog = new AlertDialog.Builder(Giris.this).create();
            alertDialog.setTitle(KKApplication.pushNotification.getTitle());
            alertDialog.setMessage(KKApplication.pushNotification.getMessage());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            KKApplication.pushNotification = null;
                            startActivity(new Intent(Giris.this, MainActivity.class));
                            finish();
                        }
                    });
            alertDialog.show();
        }else {
            Thread gec = new Thread() {

                @Override
                public void run() {
                    try {
                        this.sleep(2000);
                    } catch (InterruptedException e) {

                    } finally {
                        startActivity(new Intent(Giris.this, MainActivity.class));
                    }
                }

            };

            gec.start();
        }
    }

}
