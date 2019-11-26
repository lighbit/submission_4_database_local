package com.example.submission3_zulkaranen;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.submission3_zulkaranen.favorit.MoviesFavorit;
import com.example.submission3_zulkaranen.favorit.TvShowFavorit;
import com.example.submission3_zulkaranen.view.MoviesFragment;
import com.example.submission3_zulkaranen.view.TVShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

/**
 * @author zulkarnaen
 */
public class MainActivity extends AppCompatActivity {
    private int flagMenuFav = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.navigation_movies);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    fragment = new MoviesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name_movie);
                    flagMenuFav = 1;
                    return true;
                case R.id.navigation_tv:
                    fragment = new TVShowFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name_tvshow);
                    flagMenuFav = 2;
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else if (item.getItemId() == R.id.love) {
            if (flagMenuFav == 1) {
                Intent intent = new Intent(this, MoviesFavorit.class);
                startActivity(intent);
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name_movie);
                Toast.makeText(this, R.string.text_favorit_movie, Toast.LENGTH_SHORT).show();
            } else if (flagMenuFav == 2) {
                Intent intent = new Intent(this, TvShowFavorit.class);
                startActivity(intent);
                Toast.makeText(this, R.string.text_favorit_tvShow, Toast.LENGTH_SHORT).show();
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name_tvshow);
            }


        }
        return super.onOptionsItemSelected(item);
    }

}
