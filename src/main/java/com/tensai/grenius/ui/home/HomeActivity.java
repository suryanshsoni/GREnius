package com.tensai.grenius.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesFragment;
import com.tensai.grenius.ui.home.dashboard_fragment.DashboardFragment;
import com.tensai.grenius.ui.home.quiz_fragment.QuizFragment;
import com.tensai.grenius.ui.home.settings_fragment.SettingsFragment;
import com.tensai.grenius.ui.home.words_all_fragment.WordsAllFragment;
import com.tensai.grenius.ui.home.words_high_frequency_fragment.WordsHighFreqFragment;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordsSynonymFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView, DashboardFragment.OnFragmentInteractionListener, QuizFragment.OnFragmentInteractionListener
        , WordsAllFragment.OnFragmentInteractionListener {

    @Inject
    HomePresenter<HomeView> presenter;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Log.d("MENU", item.getItemId() + "|Item id");
                        switch (item.getItemId()) {
                            case R.id.nav_words:
                                WordsAllFragment fragment = WordsAllFragment.getInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment)
                                        .commit();
                                break;
                            case R.id.nav_categories:
                                //showFragment(WordsSynonymFragment.class);
                                break;

                            case R.id.nav_home:
                                showFragment(DashboardFragment.class);
                                break;

                            case R.id.nav_quiz:
                                showFragment(QuizFragment.class);
                                break;

                            case R.id.nav_articles:
                                showFragment(ArticlesFragment.class);
                                break;
                        }
                        return true;
                    }
                }
        );
        showFragment(DashboardFragment.class);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    private void showFragment(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentmanager = getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);
    }
}
