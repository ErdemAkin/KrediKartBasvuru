package com.deneme1.erel.kredikartbasvuru;

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
//


public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb1, rb2;
    private CheckBox cb1, cb2, cb3, cb4, cb5;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        text = "BAYAN";
                        break;
                    }
                    case R.id.rbutton2: {
                        text = "BAY ";
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
}
