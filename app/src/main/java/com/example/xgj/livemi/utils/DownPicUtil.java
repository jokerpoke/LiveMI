package com.example.xgj.livemi.utils;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static android.R.attr.path;

/**
 * 图片下载的工具类
 */
public class DownPicUtil {

    /**
     * 下载图片，返回图片的地址
     *
     * @param url
     */
    public static void downPic(Context context, String url, DownFinishListener downFinishListener) {
        // 获取存储卡的目录
        String filePath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filePath + File.separator + "webViewCache");
        if (!file.exists()) {
            file.mkdir();
        }

        loadPic(file.getPath(), url, downFinishListener);
    }

    //由url保存图片
    private static void loadPic(final String filePath, final String url, final DownFinishListener downFinishListener) {
        Log.e("下载图片的url", url);
        new AsyncTask<Void, Void, String>() {
            String fileName;
            InputStream is;
            OutputStream out;

            @Override
            protected String doInBackground(Void... voids) {

                // 下载文件的名称
                //                String[] split = url.split("\\/");
                //                String newString = split[split.length - 1];
                //                fileName =newString.substring(newString.length()-20,newString.length()-1) ;
                fileName = url.substring(url.lastIndexOf("/") + 1);
                if (fileName.contains("?")) {
                    fileName = fileName.substring(0, fileName.lastIndexOf("?"));
                }
                //                LOG.D("fileName = " + fileName);
                // 创建目标文件,不是文件夹
                File picFile = new File(filePath + File.separator + fileName);
                if (picFile.exists()) {
                    return picFile.getPath();
                }

                try {
                    URL picUrl = new URL(url);
                    //通过图片的链接打开输入流
                    is = picUrl.openStream();
                    if (is == null) {
                        return null;
                    }
                    out = new FileOutputStream(picFile);
                    byte[] b = new byte[1024];
                    int end;
                    while ((end = is.read(b)) != -1) {
                        out.write(b, 0, end);
                    }

                    Log.e("OK??", "----------");
                    if (is != null) {
                        is.close();
                    }

                    if (out != null) {
                        out.close();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return picFile.getPath();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    downFinishListener.getDownPath(s);
                }
            }
        }.execute();
    }


    /**
     * 下载图片，返回图片的地址
     *
     * @param bitmap
     */
    public static void downPicBitmap(Context context, Bitmap bitmap, DownFinishListener downFinishListener) {
        // 获取存储卡的目录
        String filePath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filePath + File.separator + "SaveBitmap");
        if (!file.exists()) {
            file.mkdir();
        }

        saveImageToGallery(context, bitmap, downFinishListener);
    }


    //由bitmap保存图片到本地
    public static void saveImageToGallery(Context context, Bitmap bmp, DownFinishListener downFinishListener) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //注释掉以下方法图片只会保存一次，这个方法注释掉，然后路径通知全路径
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        downFinishListener.getDownPath(null);
    }

    //下载完成回调的接口
    public interface DownFinishListener {

        void getDownPath(String s);
    }
}


