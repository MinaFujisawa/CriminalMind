package com.derrick.park.criminalmind;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by MinaFujisawa on 2017/07/11.
 */

public class PictureUtils {

    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
    
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {

        // read in the dimensions of the image on disk
        //BitmapFactory.Optionsは元画像のサイズを読み込む段階で小さくするに使う
        BitmapFactory.Options options = new BitmapFactory.Options();

        //この値をtrueにすると実際には画像を読み込まず、画像のサイズ情報だけを取得
        assert options.inJustDecodeBounds = false;
        //画像ファイル読み込み, decode = エンコードされたデータを元の状態に戻す
        BitmapFactory.decodeFile(path, options);

        //読み込んだサイズはoptions.outWidthとoptions.outHeightに格納される
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //figure out how much to scale down by ここがわからない
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
