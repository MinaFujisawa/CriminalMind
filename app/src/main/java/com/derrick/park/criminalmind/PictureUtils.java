package com.derrick.park.criminalmind;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Environment;

import java.io.File;

/**
 * Created by MinaFujisawa on 2017/07/11.
 */

public class PictureUtils {


    public static Bitmap getScaledBitmap(String path, int photoViewWidth, int photoViewHeight){
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

        //figure out how much to scale down by
        int inSampleSize = Math.round(photoViewHeight > photoViewWidth ? photoViewHeight : photoViewWidth);; //2なら画像の縦横のピクセル数を1/2にしたサイズ。3なら1/3にしたサイズで読み込まれる。
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //Read in and create final bitmap
        return BitmapFactory.decodeFile(path, options);
    }
}
