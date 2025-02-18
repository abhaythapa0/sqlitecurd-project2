package com.example.sqlite_project2;

import android.app.Activity;
import android.net.Uri;
import android.view.View;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class ImageUtility {
    public static void picImage (View  view, Activity activity) {
        ImagePicker.with(activity)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();

    }

}
