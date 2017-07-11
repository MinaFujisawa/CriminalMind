package com.derrick.park.criminalmind;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by MinaFujisawa on 2017/07/11.
 */

public class PictureUtils {
    
    public static Bitmap getScaleBitmap (String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
    
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {

        // read in the dimensions of the image on disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        assert options.inJustDecodeBounds = false;
        BitmapFactory.decodeFile(path, options); //decode = エンコードされたデータを元の状態に戻す

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //figure out how much to scale down by
        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            float heightScale = srcHeight / destHeight;
            float widthScale = srcWidth / destWidth;

            inSampleSize = Math.round(heightScale > widthScale ? heightScale : widthScale);

        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //Read in and create final bitmap
        return BitmapFactory.decodeFile(path, options);
    }
}
