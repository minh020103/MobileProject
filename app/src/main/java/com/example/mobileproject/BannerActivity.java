package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Slide;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class BannerActivity extends AppCompatActivity {

    public ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_layout);

        imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.anh1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.anh2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.anh3, ScaleTypes.FIT));

        imageSlider.setImageList(imageList,ScaleTypes.FIT);

    }
}