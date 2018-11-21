package com.software.miedo.pocketfisireal.presentation.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.core.CustomViewPager;
import com.software.miedo.pocketfisireal.core.FixedTabsPagerAdapter;
import com.software.miedo.pocketfisireal.data.local.SessionManager;
import com.software.miedo.pocketfisireal.model.UserEntity;
import com.software.miedo.pocketfisireal.presentation.fragments.courses.CoursesFragment;
import com.software.miedo.pocketfisireal.presentation.fragments.news.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private CustomViewPager viewPager;
    private BottomNavigationView navigation;
    private SessionManager sessionManager;
    private FixedTabsPagerAdapter adapter;

    private static boolean ANIMATED = false;

    public MenuItem refrescar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        // Configurando el ViewPager
        viewPager = (CustomViewPager) findViewById(R.id.container);
        adapter = new FixedTabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_courses);
        navigation.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);

        refrescar = menu.findItem(R.id.refresh);
        setFragmentById(navigation.getSelectedItemId());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.refresh) {
            int tab = navigation.getSelectedItemId();

            switch (tab) {
                case R.id.navigation_news:
                    adapter.doRefresh(0);

                    return true;
                case R.id.navigation_courses:
                    adapter.doRefresh(1);

                    return true;
                case R.id.navigation_support:
                    adapter.doRefresh(3);

                    return true;
            }


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return setFragmentById(item.getItemId());
        }
    };

    protected boolean setFragmentById(int itemId) {
        switch (itemId) {
            case R.id.navigation_news:
                getSupportActionBar().setTitle("Noticias");
                viewPager.setCurrentItem(0, ANIMATED);
                refrescar.setVisible(true);
                return true;
            case R.id.navigation_courses:
                getSupportActionBar().setTitle("Cursos");
                viewPager.setCurrentItem(1, ANIMATED);
                refrescar.setVisible(true);
                return true;

            case R.id.navigation_support:
                getSupportActionBar().setTitle("Soporte");
                if (sessionManager.isLogged()) {
                    viewPager.setCurrentItem(3, ANIMATED); // identificado
                    //getSupportActionBar().setSubtitle("Identificado");
                    refrescar.setVisible(true);
                } else {
                    viewPager.setCurrentItem(2, ANIMATED); // login
                    //getSupportActionBar().setSubtitle("No identificado");
                    refrescar.setVisible(false);
                }

                return true;
        }
        return false;
    }

    public void initSession(UserEntity user) {
        sessionManager.initSession(user);
        viewPager.setCurrentItem(3, true);
    }

    public void closeSession() {
        sessionManager.closeSession();
        viewPager.setCurrentItem(2, true);
    }


}
