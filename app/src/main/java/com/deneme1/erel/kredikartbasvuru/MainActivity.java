package com.deneme1.erel.kredikartbasvuru;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//120202001 - Ali Erdem Akın

//130202027 -Büşra Nur ALTINTAŞ

//130202066 Bengü AYDIN

//130202034 Melike YÜCEL

//120202081 Münevver TURAN
//deneme


public class MainActivity extends AppCompatActivity {

    public static final String myid = "myid001";
    public static final int actMode = Activity.MODE_PRIVATE;


    private  EditText adSoyad ,tc;


    RadioGroup rg;
    RadioButton rb1, rb2;
    private CheckBox cb1, cb2, cb3, cb4, cb5;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adSoyad = (EditText)findViewById(R.id.etAdi);
        tc = (EditText)findViewById(R.id.etTc);


        rg = (RadioGroup) findViewById(R.id.rgroup);
        rb1 = (RadioButton) findViewById(R.id.rbutton1);
        rb2 = (RadioButton) findViewById(R.id.rbutton2);

        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);
        b1 = (Button) findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int butonid = rg.getCheckedRadioButtonId();
                String text = "";
                switch (butonid) {
                    case R.id.rbutton1: {
                        text = "KADIN";
                        break;
                    }
                    case R.id.rbutton2: {
                        text = "ERKEK ";
                        break;
                    }
                }

                if (cb1.isChecked()) {
                    text = text + " " + cb1.getText().toString() + "\n";
                }
                if (cb2.isChecked()) {
                    text = text + " " + cb2.getText().toString() + "\n";
                }
                if (cb3.isChecked()) {
                    text = text + " " + cb3.getText().toString() + "\n";
                }
                if (cb4.isChecked()) {
                    text = text + " " + cb4.getText().toString() + "\n";
                }
                if (cb5.isChecked()) {
                    text = text + " " + cb5.getText().toString() + "\n";
                }

                Toast.makeText(getApplicationContext(), "Yanıtlarınız gönderildi: \n" + text, 0).show();

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        updateFromSavedState();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveDataFromCurrentState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    protected void saveDataFromCurrentState() {
        SharedPreferences preferences = getSharedPreferences(myid, actMode);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("AdSoyad", adSoyad.getText().toString());
        editor.putString("TC",tc.getText().toString());
        editor.commit();
    }





    protected void updateFromSavedState() {

        SharedPreferences myPrefs = getSharedPreferences(myid, actMode);

        if ((myPrefs != null)) {

            String kullaniciAdSoyad = myPrefs.getString("AdSoyad", "");
            String kullaniciTc = myPrefs.getString("TC","");

            adSoyad.setText(kullaniciAdSoyad);
            tc.setText(kullaniciTc);
        }
    }
    protected void clearMyPreferences() {
        SharedPreferences myPrefs = getSharedPreferences(myid, actMode);
        SharedPreferences.Editor myEditor = myPrefs.edit();
        myEditor.clear();
        myEditor.commit();
    }

}
