package com.deneme1.erel.kredikartbasvuru;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deneme1.erel.kredikartbasvuru.webservice.ControlInput;
import com.deneme1.erel.kredikartbasvuru.webservice.MessageResult;
import com.deneme1.erel.kredikartbasvuru.webservice.WebServiceCaller;
import com.deneme1.erel.kredikartbasvuru.webservice.WebServiceCallerImpl;



public class other  extends Activity {

    TextView tv ;
    int maas,yas;
    String isim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);

        tv = (TextView)findViewById(R.id.tv);
        Button btn = (Button) findViewById(R.id.button);
        Bundle datas = getIntent().getExtras();
        isim = datas.getString("isim");
        String maasString =  datas.getString("maas");
        String yasString = datas.getString("yas");

        maas = Integer.parseInt(maasString);
        yas = Integer.parseInt(yasString);
        yas = 2016- yas;

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ControlInput cntinput= new ControlInput();
                cntinput.setMaas(maas);
                cntinput.setYas(yas);
                controlAsync lt = new controlAsync();
                lt.input = cntinput;
                lt.execute();
            }
        });
    }


    private class controlAsync extends AsyncTask<String,Void,String>{
        public ControlInput input;
        public String sonuc = "";

        protected String doInBackground(String... urls){
            WebServiceCaller i = new WebServiceCallerImpl();
            sonuc = i.getMessage(input);
            return "";
        }
        protected void onPostExecute(String result){
            tv.setText("Sayın "+isim+":\n\n"+sonuc);
        }
    }
}
