package com.deneme1.erel.kredikartbasvuru;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.deneme1.erel.kredikartbasvuru.model.Register;
import com.deneme1.erel.kredikartbasvuru.service.ServiceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


//120202001 - Ali Erdem Akın

//130202027 -Büşra Nur ALTINTAŞ

//130202066 Bengü AYDIN

//130202034 Melike YÜCEL

//120202081 Münevver TURAN

//130202065 Begüm AYDIN


public class MainActivity extends AppCompatActivity {

    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long dosyaBoyutu = 0;

    public static final String myid = "myid001";
    public static final int actMode = Activity.MODE_PRIVATE;

    private EditText adSoyad, tc;
    private boolean cinsiyet;
    private ArrayList<String> tercih = new ArrayList<>();

    RadioGroup rg;
    RadioButton rb1, rb2;
    private CheckBox cb1, cb2, cb3, cb4, cb5;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adSoyad = (EditText) findViewById(R.id.etAdi);
        tc = (EditText) findViewById(R.id.etTc);

        rg = (RadioGroup) findViewById(R.id.rgroup);
        rb1 = (RadioButton) findViewById(R.id.rbutton1);
        rb2 = (RadioButton) findViewById(R.id.rbutton2);

        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);
        b1 = (Button) findViewById(R.id.button1);

        AutoCompleteTextView iller=(AutoCompleteTextView)findViewById(R.id.auto);
        String[] Cihaz= getResources().getStringArray(R.array.cihaz);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Cihaz);
        iller.setAdapter(adapter);

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int butonid = rg.getCheckedRadioButtonId();
                String text = "";
                switch (butonid) {
                    case R.id.rbutton1: {
                        text = "KADIN";
                        cinsiyet = false;
                        break;
                    }
                    case R.id.rbutton2: {
                        text = "ERKEK ";
                        cinsiyet = true;
                        break;
                    }
                }

                if (cb1.isChecked()) {
                    text = text + " " + cb1.getText().toString() + "\n";
                    tercih.add(cb1.getText().toString());
                }
                if (cb2.isChecked()) {
                    text = text + " " + cb2.getText().toString() + "\n";
                    tercih.add(cb2.getText().toString());
                }
                if (cb3.isChecked()) {
                    text = text + " " + cb3.getText().toString() + "\n";
                    tercih.add(cb3.getText().toString());
                }
                if (cb4.isChecked()) {
                    text = text + " " + cb4.getText().toString() + "\n";
                    tercih.add(cb4.getText().toString());
                }
                if (cb5.isChecked()) {
                    text = text + " " + cb5.getText().toString() + "\n";
                    tercih.add(cb5.getText().toString());
                }


                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("İşlem Tamamlanıyor ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();


                progressBarStatus = 0;

                dosyaBoyutu = 0;

                new Thread(new Runnable() {
                    public void run() {
                        while (progressBarStatus < 100) {
                            progressBarStatus = indirmeSimulasyon();

                            try {

                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                            progressBarHandler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }


                        if (progressBarStatus >= 100) {


                            try {

                                Thread.sleep(100);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                            progressBar.dismiss();
                            String TCKN = tc.getText().toString();
                            String ad_Soyad = adSoyad.getText().toString();
                            Register register = new Register(TCKN, ad_Soyad, cinsiyet, tercih);
                            sendForm(register);
                        }
                    }
                }).start();



            }
        });

    }

    public int indirmeSimulasyon() {

        while (dosyaBoyutu <= 100) {
            //Sürekli dosyaBoyutu değişkeni 1 artırılarak sanki bir dosya indirmesi yapılıyormuş gibi simulasyon yapılacak.
            dosyaBoyutu++;
            return (int) dosyaBoyutu;

        }
        return 100;
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
        editor.putString("TC", tc.getText().toString());
        editor.apply();
    }


    protected void updateFromSavedState() {

        SharedPreferences myPrefs = getSharedPreferences(myid, actMode);

        if ((myPrefs != null)) {

            String kullaniciAdSoyad = myPrefs.getString("AdSoyad", "");
            String kullaniciTc = myPrefs.getString("TC", "");

            adSoyad.setText(kullaniciAdSoyad);
            tc.setText(kullaniciTc);
        }
    }

    protected void clearMyPreferences() {
        SharedPreferences myPrefs = getSharedPreferences(myid, actMode);
        SharedPreferences.Editor myEditor = myPrefs.edit();
        myEditor.clear();
        myEditor.apply();
    }

    private void sendForm(Register register){
        ServiceManager.ServiceRequest apiService = ServiceManager.getClient().create(ServiceManager.ServiceRequest.class);
        Call<Boolean> call = apiService.addForm(register);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()){
                        Toast.makeText(getApplicationContext(),"Form kaydedildi.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable ignored) {
            }
        });

    }
}
