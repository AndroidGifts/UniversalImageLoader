package com.android.gifts.universalimageloader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MainActivity extends AppCompatActivity {

    ImageView myImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String imageURI = "http://androidgifts.com/wp-content/uploads/2016/04/target_image.png";
        myImage = (ImageView) findViewById(R.id.my_image);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_placeholder) // resource or drawable
                .showImageForEmptyUri(R.drawable.on_empty_url) // resource or drawable
                .showImageOnFail(R.drawable.on_fail) // resource or drawable
                .delayBeforeLoading(3000)
                .resetViewBeforeLoading(true)  // default
                .cacheInMemory(true) // default => false
                .cacheOnDisk(true) // default => false
                .build();

        imageLoader.displayImage(imageURI, myImage, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                Toast.makeText(getApplicationContext(), "Loading Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Toast.makeText(getApplicationContext(), "Loading Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Toast.makeText(getApplicationContext(), "Loading Complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Toast.makeText(getApplicationContext(), "Loading Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
