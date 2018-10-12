package com.kamikazemeteor.kametscrum;

import android.app.Fragment;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DisplayScrumActivity extends AppCompatActivity implements SensorEventListener {
    private static Boolean numberRevealed;
    private static int imgSrc;
    //private static SensorManager sensorMgr;
    //private static Sensor accelSensor;
    private static float x, y, z;
    private static final float ERROR = 7.0f;
    private static boolean init;
    private static boolean isFlipped;

    public static class TileBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.tile_back, container, false);

            ImageView imageView = (ImageView)view.findViewById(R.id.imgTileBack);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(
                                    R.animator.tile_flip_right_in,
                                    R.animator.tile_flip_right_out,
                                    R.animator.tile_flip_left_in,
                                    R.animator.tile_flip_left_out)
                            .replace(R.id.scrumDisplay, new TileFrontFragment())
                            .addToBackStack(null)
                            .commit();
                    isFlipped = false;
                }
            });

            return view;
        }
    }

    public static class TileFrontFragment extends Fragment {
        private ImageView imageView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            imageView = (ImageView)inflater.inflate(R.layout.tile_front,
                    container, false);
            imageView.setImageResource(imgSrc);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
            return imageView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scrum);

        //sensorMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        //accelSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensorMgr.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
        init = false;

        TileBackFragment backTile = new TileBackFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.scrumDisplay, backTile)
                .commit();

        numberRevealed = false;
        Intent intent = getIntent();
        imgSrc = intent.getIntExtra(MainActivity.IMAGE_SOURCE, 0);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float a, b, c;
        a = event.values[0];
        b = event.values[1];
        c = event.values[2];

        if (!init) {
            x = a;
            y = b;
            z = c;
            init = true;
        }
        else {
            float diffX = Math.abs(x - a);
            float diffY = Math.abs(y - b);
            float diffZ = Math.abs(z - c);

            if (diffX < ERROR) { diffX = 0.0f; }
            if (diffY < ERROR) { diffY = 0.0f; }
            if (diffZ < ERROR) { diffZ = 0.0f; }

            x = a;
            y = b;
            z = c;

            if (diffX > diffY && !isFlipped) {
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.animator.tile_flip_right_in,
                                R.animator.tile_flip_right_out,
                                R.animator.tile_flip_left_in,
                                R.animator.tile_flip_left_out)
                        .replace(R.id.scrumDisplay, new TileFrontFragment())
                        .addToBackStack(null)
                        .commit();
                isFlipped = true;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isFlipped = false;
    }

    protected void onResume() {
        super.onResume();
        //sensorMgr.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected  void onPause() {
        super.onPause();
        //sensorMgr.unregisterListener(this);
    }
}
