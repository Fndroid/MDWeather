package com.fndroid.byweather;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImgActivity extends AppCompatActivity {

	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_img);
		mImageView = (ImageView) findViewById(R.id.iv);
		mImageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra("img"));

	}
}
