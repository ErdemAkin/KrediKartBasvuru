package com.deneme1.erel.kredikartbasvuru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by PC on 13.12.2016.
 */

public class Giris extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris);

        Thread gec = new Thread() {

            @Override
            public void run() {

                try {
                    this.sleep(3000);
                } catch (InterruptedException e) {

                } finally {
                    startActivity(new Intent(Giris.this, MainActivity.class));

                }

            }

        };

        gec.start();
    }

}
