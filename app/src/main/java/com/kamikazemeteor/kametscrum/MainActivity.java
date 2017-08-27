package com.kamikazemeteor.kametscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static final String IMAGE_SOURCE = "com.kamikaze.kametscrum.IMAGE_SOURCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imgScrum0);
        imageView.setTag(R.mipmap.scrum_0);
        imageView = (ImageView)findViewById(R.id.imgScrum05);
        imageView.setTag(R.mipmap.scrum_05);
        imageView = (ImageView)findViewById(R.id.imgScrum1);
        imageView.setTag(R.mipmap.scrum_1);
        imageView = (ImageView)findViewById(R.id.imgScrum2);
        imageView.setTag(R.mipmap.scrum_2);
        imageView = (ImageView)findViewById(R.id.imgScrum3);
        imageView.setTag(R.mipmap.scrum_3);
        imageView = (ImageView)findViewById(R.id.imgScrum5);
        imageView.setTag(R.mipmap.scrum_5);
        imageView = (ImageView)findViewById(R.id.imgScrum8);
        imageView.setTag(R.mipmap.scrum_8);
        imageView = (ImageView)findViewById(R.id.imgScrum13);
        imageView.setTag(R.mipmap.scrum_13);
        imageView = (ImageView)findViewById(R.id.imgScrum20);
        imageView.setTag(R.mipmap.scrum_20);
        imageView = (ImageView)findViewById(R.id.imgScrum40);
        imageView.setTag(R.mipmap.scrum_40);
        imageView = (ImageView)findViewById(R.id.imgScrum100);
        imageView.setTag(R.mipmap.scrum_100);
        imageView = (ImageView)findViewById(R.id.imgScrumInf);
        imageView.setTag(R.mipmap.scrum_inf);
        imageView = (ImageView)findViewById(R.id.imgScrumQM);
        imageView.setTag(R.mipmap.scrum_qm);
        imageView = (ImageView)findViewById(R.id.imgScrumCoffee);
        imageView.setTag(R.mipmap.scrum_coffee);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayScrumActivity.class);
        ImageView imageView = (ImageView)view;
        int imgSrc = (Integer)imageView.getTag();
        intent.putExtra(IMAGE_SOURCE, imgSrc);
        startActivity(intent);
    }
}
