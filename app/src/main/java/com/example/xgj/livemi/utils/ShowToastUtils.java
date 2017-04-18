package com.example.xgj.livemi.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chen on 2017/4/18.
 */

public class ShowToastUtils {


    private static Toast toast;

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
