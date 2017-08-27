package com.kamikazemeteor.kametscrum;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DisplayScrumActivity extends AppCompatActivity {
    private static Boolean numberRevealed;
    private static int imgSrc;

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
                }
            });

            return view;
        }
    }

    public static class TileFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ImageView imageView = (ImageView)inflater.inflate(R.layout.tile_front,
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

        TileBackFragment backTile = new TileBackFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.scrumDisplay, backTile)
                .commit();

        numberRevealed = false;
        Intent intent = getIntent();
        imgSrc = intent.getIntExtra(MainActivity.IMAGE_SOURCE, 0);
    }
}
