package com.tensai.grenius.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.activity.WhyGrenius;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesFragment;
import com.tensai.grenius.ui.home.dashboard_fragment.DashboardFragment;
import com.tensai.grenius.ui.home.marked_fragment.MarkedWordsFragment;
import com.tensai.grenius.ui.home.quiz_fragment.QuizCallerFragment;
import com.tensai.grenius.ui.home.quiz_fragment.QuizFragment;
import com.tensai.grenius.ui.home.words.WordTabFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllFragment;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordsSynonymFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView, DashboardFragment.OnFragmentInteractionListener, QuizFragment.OnFragmentInteractionListener
        ,WordsAllFragment.OnFragmentInteractionListener,ArticlesFragment.OnFragmentInteractionListener,WordsSynonymFragment.OnFragmentInteractionListener {

    @Inject
    HomePresenter<HomeView> presenter;

    @BindView(R.id.bottom_navigation)
    public BottomNavigationViewEx bottomNavigation;

    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView profilePictureView;
    TextView username;
    String userId,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        presenter.onAttach(HomeActivity.this);

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        showFragment(DashboardFragment.class);

        View hView =  navigationView.getHeaderView(0);
        profilePictureView = (ImageView) hView.findViewById(R.id.userImage);
        username = (TextView) hView.findViewById(R.id.userName);

        presenter.getUserDetails();

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(1)
                .cornerRadiusDp(80)
                .oval(false)
                .build();

        if(userId!=null){
            Picasso.with(getApplicationContext())
                    .load("https://graph.facebook.com/" +userId+ "/picture?type=large")
                    .fit()
                    .transform(transformation)
                    .into(profilePictureView);
        }
        else {
            Log.i("ABC:","In ELSE");
            Picasso.with(getApplicationContext())
                    .load(R.drawable.jsocial)
                    .fit()
                    .transform(transformation)
                    .into(profilePictureView);
        }


        username.setText(userName);

        NavDrawer();
        BottomNav();
    }

    private void BottomNav(){
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.setCurrentItem(2);

        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.nav_words:
                                showFragment(WordTabFragment.class);
                                break;
                            case R.id.nav_categories:
                                showFragment(WordsSynonymFragment.class);
                                break;
                            case R.id.nav_home:
                                showFragment(DashboardFragment.class);
                                break;
                            case R.id.nav_quiz:
                                //showFragment(QuizCallerFragment.class);
                                QuizCallerFragment qcf = new QuizCallerFragment();
                                qcf.show(getSupportFragmentManager(),"demo");
                                break;
                            case R.id.nav_articles:
                                showFragment(ArticlesFragment.class);
                                break;
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
           getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    private void NavDrawer(){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.nav_home) {
                            showFragment(DashboardFragment.class);
                            bottomNavigation.setCurrentItem(2);
                        } else if (id == R.id.nav_why) {
                            Intent intent=new Intent(getApplicationContext(), WhyGrenius.class);
                            startActivity(intent);
                        } else if (id == R.id.nav_contact) {
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto", "gre.tensai@gmail.com", null));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                            startActivity(Intent.createChooser(emailIntent, null));
                        } else if (id == R.id.nav_share) {
                            Intent sendIntent= new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT,"Content");
                            sendIntent.setType("text/plain");
                            startActivity(sendIntent);
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                }
        );
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
        if (id == R.id.action_bookmark) {
            showFragment(MarkedWordsFragment.class);
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
                .addToBackStack(null)
                .replace(R.id.mainFrame, fragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showUserDetails(String userId,String userName) {
        this.userId=userId;
        this.userName=userName;
    }
}