package com.example.servehumanity.bll;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.loader.content.CursorLoader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ImageBLL {
    public static void MakeStrict() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    public static String getRealPathFromUri(Uri uri, Context context) {
        String [] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    public static void previewImage(String imagePath, ImageView imageView) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        } else {
            Log.i("PreviewImage", "PreviewImageError");
        }
    }

    public static Bitmap loadImageFromURL(String imagePath) {
        MakeStrict();
        Bitmap bmp;
        try {
            java.net.URL url = new java.net.URL(imagePath);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bmp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
