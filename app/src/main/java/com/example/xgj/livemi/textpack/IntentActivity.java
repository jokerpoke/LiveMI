package com.example.xgj.livemi.textpack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xgj.livemi.R;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);


    }

    public void btn(View view) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        Intent chooser = intent.createChooser(intent, "哈哈");
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(chooser);
        }
    }
}
