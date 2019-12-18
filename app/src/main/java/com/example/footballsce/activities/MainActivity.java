package com.example.footballsce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.footballsce.R;
import com.example.footballsce.fragments.ChampionsFragment;
import com.example.footballsce.fragments.EplFragment;
import com.example.footballsce.fragments.EuropaFragment;
import com.example.footballsce.fragments.UEFAFragment;
import com.example.footballsce.fragments.LaligaFragment;
import com.example.footballsce.fragments.LeagueFragment;
import com.example.footballsce.fragments.LigaIndoFragment;
import com.example.footballsce.fragments.SerieAFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private LeagueFragment leagueFragment;
    private EplFragment eplFragment;
    private LaligaFragment laligaFragment;
    private SerieAFragment serieAFragment;
    private LigaIndoFragment ligaIndoFragment;
    private UEFAFragment uefaFragment;
    private ChampionsFragment championsFragment;
    private EuropaFragment europaFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            loadFragment(new LeagueFragment());
            BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
            // beri listener pada saat item/menu bottomnavigation terpilih
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
            leagueFragment = new LeagueFragment();
            eplFragment = new EplFragment();
            laligaFragment = new LaligaFragment();
            serieAFragment = new SerieAFragment();
            ligaIndoFragment = new LigaIndoFragment();
            uefaFragment = new UEFAFragment();
            europaFragment = new EuropaFragment();
            championsFragment = new ChampionsFragment();
        }

        private boolean loadFragment(Fragment fragment) {
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_dynamic, fragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            }
            return false;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.league:
                    fragment = new LeagueFragment();
                    break;
                case R.id.uefa:
                    fragment = new UEFAFragment();
                    break;
            }
            return loadFragment(fragment);
        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }

    public void LoadInggris(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.framejadwal, eplFragment).commit();
    }
    public void LoadSpanyol(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.framejadwal, laligaFragment).commit();
    }
    public void LoadItaly(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.framejadwal, serieAFragment).commit();
    }
    public void LoadLigaIndonesia(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.framejadwal,ligaIndoFragment).commit();
    }
    public void LoadUefa(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_dynamic, uefaFragment).commit();
    }
    public void LoadChampions (View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.framejadwal, championsFragment).commit();
    }
    public void LoadEuropa (View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.framejadwal, europaFragment).commit();
    }

}
