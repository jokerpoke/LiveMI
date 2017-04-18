package com.example.xgj.livemi.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xgj.livemi.R;

public class PrepareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, PrepareActivity.class);
        context.startActivity(intent);
    }
}
