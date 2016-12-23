package com.deneme1.erel.kredikartbasvuru;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class other  extends Activity {

    TextView tv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);

        tv = (TextView)findViewById(R.id.tv);


        Bundle datas = getIntent().getExtras();

        tv.setText(datas.getString("isim"));


    }

}
