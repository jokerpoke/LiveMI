package com.example.xgj.livemi.textpack;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.xgj.livemi.R;

public class Main4Activity extends AppCompatActivity {
    //拦截dialog的点击自动调用dimiss（）执行，使其当点击之后弹窗依然存在
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);

        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {//让返回键消失，不取消dialog
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        alertDialog.setTitle("提示");
        alertDialog.setMessage("hhhhh");
        alertDialog.setPositiveButton("确定", null);

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.setCanceledOnTouchOutside(false);
        alertDialog1.show();
        //        关键位置
        alertDialog1.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main4Activity.this, "nihao", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
