package com.derrick.park.criminalmind;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;

/**
 * Created by MinaFujisawa on 2017/07/12.
 */

public class ZoomedPhotoFragment extends android.support.v4.app.DialogFragment{

    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    private ImageView mPhotoView;
    private File mPhotoFile;

    public static final String PHOTO_FILE = "photo_file";



    public static ZoomedPhotoFragment newInstance(File mPhotoFile) {
        ZoomedPhotoFragment fragment = new ZoomedPhotoFragment();
        Bundle args = new Bundle();
        args.putSerializable(PHOTO_FILE, mPhotoFile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPhotoFile = (File) getArguments().getSerializable(PHOTO_FILE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_photo, container, false);

        ImageView mPhotoView = (ImageView) v.findViewById(R.id.expanded_image);
//        Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(),getArgumentsctivity());
        Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
        mPhotoView.setImageBitmap(bitmap);
        return v;
    }


}
