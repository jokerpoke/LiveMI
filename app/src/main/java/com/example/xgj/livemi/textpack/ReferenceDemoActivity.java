package com.example.xgj.livemi.textpack;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.example.xgj.livemi.R;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class ReferenceDemoActivity extends AppCompatActivity {
    private static final String TAG = "22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference_demo);

        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();//不用 Context 也能获取屏幕密度
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        Log.d(TAG, "onCreate:==== " + widthPixels + "====" + heightPixels);

        TextView TV=new TextView(this);
        int i = TV.generateViewId();//new 出来的 View 可以用 View.generateViewId() （API 17 以上可用） 生成 id，系统保证唯一
        Log.d(TAG, "onCreate: +i+=="+i);

        String str = new String("哈哈");
        SoftReference softReference = new SoftReference(str);
        String newStr = (String) softReference.get();
        Log.d(String.valueOf(TAG), "onCreate: " + newStr);
        ReferenceQueue referenceQueue = new ReferenceQueue();
        SoftReference softReference2 = new SoftReference(str, referenceQueue);
        referenceQueue.poll();

        //        Log.d(TAG, "onCreate: "+(String) reference.get());
    }
}
