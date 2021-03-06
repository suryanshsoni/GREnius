package com.tensai.grenius.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.receivers.AlarmReceiverMain;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesFragment;
import com.tensai.grenius.ui.home.dashboard_fragment.DashboardFragment;
import com.tensai.grenius.ui.home.institutes.InstitutesActivity;
import com.tensai.grenius.ui.home.marked_fragment.MarkedWordsActivity;
import com.tensai.grenius.ui.home.quiz_fragment.QuizCallerFragment;
import com.tensai.grenius.ui.home.quiz_fragment.QuizFragment;
import com.tensai.grenius.ui.home.words.WordTabFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllFragment;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordsSynonymFragment;
import com.tensai.grenius.ui.login.LoginActivity;
import com.tensai.grenius.ui.profile.ProfileActivity;
import com.tensai.grenius.view.SlideTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeView, DashboardFragment.OnFragmentInteractionListener, QuizFragment.OnFragmentInteractionListener
        , WordsAllFragment.OnFragmentInteractionListener, ArticlesFragment.OnFragmentInteractionListener, WordsSynonymFragment.OnFragmentInteractionListener, QuizCallerFragment.Callback, AvatarSelectionFragment.OnFragmentInteractionListener {

    @Inject
    HomePresenter<HomeView> presenter;
    @Inject
    FirebaseAnalytics firebaseAnalytics;
    @BindView(R.id.bottom_navigation)
    public BottomNavigationViewEx bottomNavigation;
    @BindView(R.id.rl_home)
    RelativeLayout rlHome;

    List<Integer> arr_colour= new ArrayList<Integer>();
    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView profilePictureView;
    TextView username;
    SlideTextView tv_progress;
    PieChart progressChart;
    String userId, userName, fbToken;
    int resourceId;

    protected ArrayList<Word> markedlist;

    private String SELECTED_ITEM = "";
    private final int WORD_MENU_POSITION = 0;
    private final int CATEGORIES_MENU_POSITION = 1;
    private final int HOME_MENU_POSITION = 2;
    private final int QUIZ_MENU_POSITION = 3;
    private final int ARTICLES_MENU_POSITION = 4;
    boolean isDrawerOpened=false;

    Stack<String> frag_selected_back = new Stack<String>();
    public PendingIntent pendingIntent,pendingIntentRemember;
    public AlarmManager alarmManager,alarmManagerRemember;
    public Intent alarmIntent,alarmIntentRemember;



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
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                if(isDrawerOpened)
                    frag_selected_back.pop();
                isDrawerOpened=false;
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                frag_selected_back.push(SELECTED_ITEM);
                isDrawerOpened=true;
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        presenter.onAttach(HomeActivity.this);
        presenter.getUserDetails();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        showFragment(DashboardFragment.class);
        SELECTED_ITEM = "2";

        View hView = navigationView.getHeaderView(0);
        profilePictureView = (ImageView) hView.findViewById(R.id.userImage);
        username = (TextView) hView.findViewById(R.id.userName);
        tv_progress = (SlideTextView) hView.findViewById(R.id.tv_progress);
        progressChart = (PieChart) hView.findViewById(R.id.progress_nav);
        Legend legend = progressChart.getLegend();
        legend.setEnabled(false);
        progressChart.setDrawHoleEnabled(true);
        progressChart.setHoleRadius(50f);
        progressChart.setDescription("");
        arr_colour.add(R.color.white);
        arr_colour.add(R.color.transparent);
        presenter.getProgress();

        resourceId = presenter.getResourceId();

        if (resourceId == 0) {
            profilePictureView.setImageResource(R.drawable.avatar_three);
        } else {
            switchResource(resourceId);
        }

        if ( fbToken != null) {
            setFBProfilePicture();
        } else {
            profilePictureView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AvatarSelectionFragment avatarSelectionFragment = new AvatarSelectionFragment();
                    avatarSelectionFragment.show(getSupportFragmentManager(), "demo");
                }
            });
        }

        username.setText(userName);

        NavDrawer();
        BottomNav();
        if(!presenter.isAlarmSet()){
            setNotification();
        }

    }

    private void BottomNav() {
        bottomNavigation.enableShiftingMode(false);
        if (!isNetworkConnected()) {
            showSnackbar(rlHome,getResources().getString(R.string.network_error));
        }
        bottomNavigation.setCurrentItem(HOME_MENU_POSITION);

        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.nav_words:
                                showFragment(WordTabFragment.class);
                                SELECTED_ITEM = String.valueOf(WORD_MENU_POSITION);
                                break;
                            case R.id.nav_categories:
                                showFragment(WordsSynonymFragment.class);
                                SELECTED_ITEM = String.valueOf(CATEGORIES_MENU_POSITION);
                                break;
                            case R.id.nav_home:
                                showFragment(DashboardFragment.class);
                                SELECTED_ITEM = String.valueOf(HOME_MENU_POSITION);
                                if (!isNetworkConnected()) {
                                    showSnackbar(rlHome,getResources().getString(R.string.network_error));
                                }
                                break;
                            case R.id.nav_quiz:
                                //showFragment(QuizCallerFragment.class);
                                QuizCallerFragment qcf = new QuizCallerFragment();
                                qcf.show(getSupportFragmentManager(), "demo");
                                break;
                            case R.id.nav_articles:
                                showFragment(ArticlesFragment.class);
                                SELECTED_ITEM = String.valueOf(ARTICLES_MENU_POSITION);
                                if (!isNetworkConnected()) {
                                    showSnackbar(rlHome,getResources().getString(R.string.network_error));
                                }
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
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            try{
                if(isDrawerOpened){
                    SELECTED_ITEM=frag_selected_back.pop();
                    isDrawerOpened=false;
                }
                else{

                    int i = bottomNavigation.getCurrentItem();
                    bottomNavigation.getMenu().getItem(i).setChecked(false);
                    getFragmentManager().popBackStack();
                    int cur_frag = Integer.parseInt(frag_selected_back.pop());
                    bottomNavigation.getMenu().getItem(cur_frag).setChecked(true);
                    SELECTED_ITEM = String.valueOf(cur_frag);
                    //bottomNavigation.setCurrentItem(cur_frag);
                    //bottomNavigation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        } else {
            super.onBackPressed();
        }
    }

    private void NavDrawer() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.nav_home) {
                            showFragment(DashboardFragment.class);
                            bottomNavigation.setCurrentItem(HOME_MENU_POSITION);
                        } else if (id == R.id.nav_share) {
                            share("Learn 2000+ new words, test your abilities with quizzes, stay updated with our especially curated articles and much more. Head straight to our app on:- "+getResources().getString(R.string.app_url));
                        } else if(id == R.id.nav_update){
                            if(isNetworkConnected()) {
                                presenter.update();
                                Bundle params = new Bundle();
                                params.putString("emailId", userId);
                                firebaseAnalytics.logEvent("update", params);
                            }
                            else
                                showSnackbar(rlHome,getResources().getString(R.string.network_error));
                        } else if (id == R.id.nav_why){
                            Intent why = new Intent(HomeActivity.this,WhyGrenius.class);
                            startActivity(why);
                        }else if (id == R.id.nav_insti) {
                            if(isNetworkConnected()){
                                Intent insti = new Intent(HomeActivity.this,InstitutesActivity.class);
                                startActivity(insti);
                            }else {
                                showSnackbar(rlHome,getResources().getString(R.string.network_error));
                            }
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
            Intent intent = new Intent(this, MarkedWordsActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.menu_profile){
            Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
            profileIntent.putExtra("resourceID",resourceId);
            profileIntent.putExtra("FBToken",fbToken);
            profileIntent.putExtra("Name",userName);
            startActivity(profileIntent);
            return true;
        }else if(id == R.id.menu_contact){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "gre.tensai@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            startActivity(Intent.createChooser(emailIntent, null));
            return true;
        }else if(id == R.id.menu_signout){
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Sign Out")
                    .setMessage("Are you sure you want to Sign Out?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            markedlist = (ArrayList<Word>) presenter.getMarkedWords();
                            if (isNetworkConnected()) {
                                presenter.uploadBookmarkedWords(markedlist);
                                try {
                                    presenter.unsetAlarm();
                                    unsetNotification();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }else{
                                onUploadBookmarkError();
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.ic_logout)
                    .show();
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentmanager = getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(SELECTED_ITEM)
                .replace(R.id.mainFrame, fragment)
                .commit();
        frag_selected_back.push(SELECTED_ITEM);
        firebaseAnalytics.setCurrentScreen(HomeActivity.this,""+fragmentClass.getSimpleName(),"Home");
    }

    @Override
    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showWelcomeText() {
        showSnackbar(rlHome,"Welcome "+userName);
    }

    @Override
    public void showUserDetails(String userId, String userName, String fbToken) {
        this.userId = userId;
        this.userName = userName;
        this.fbToken = fbToken;
    }

    @Override
    public void redirectLogOut() {
        Intent logoutIntent = LoginActivity.getIntent(this);
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutIntent);
        finish();
    }

    public void setFBProfilePicture(){
        final Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(80)
                .oval(false)
                .build();

        Picasso.with(this)
                .load("https://graph.facebook.com/" + fbToken + "/picture?type=large")
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .transform(transformation)
                .into(profilePictureView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        // Try again online if cache failed
                        Picasso.with(HomeActivity.this)
                                .load("https://graph.facebook.com/" + fbToken + "/picture?type=large")
                                .error(R.drawable.avatar_three)
                                .fit()
                                .transform(transformation)
                                .into(profilePictureView);
                    }
                });
    }

    @Override
    public void onUploadBookmarkError() {
        AlertDialog.Builder retrybuilder;
        retrybuilder = new AlertDialog.Builder(HomeActivity.this);
        retrybuilder.setTitle("Sign Out")
                .setMessage("We are unable to back up your data, do you still wish to sign out?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteUserData();
                        try {
                            presenter.isAlarmSet();
                            alarmManager.cancel(pendingIntent);
                            alarmManagerRemember.cancel(pendingIntentRemember);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cancel
                    }
                })
                .setIcon(R.drawable.ic_logout)
                .show();
    }

    @Override
    public void sendResourceId(int resourceId) {
        switchResource(resourceId);
        this.resourceId = resourceId;
    }

    public void switchResource(int resourceId) {
        presenter.setResourceId(resourceId);
        switch (resourceId) {

            case R.id.avatar_one:
                try {
                    Picasso.with(getApplicationContext())
                            .load(R.drawable.avatar_one)
                            .into(profilePictureView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.avatar_two:
                try {
                    Picasso.with(getApplicationContext())
                            .load(R.drawable.avatar_two)
                            .into(profilePictureView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.avatar_three:
                try {
                    Picasso.with(getApplicationContext())
                            .load(R.drawable.avatar_three)
                            .into(profilePictureView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.avatar_four:
                try {
                    Picasso.with(getApplicationContext())
                            .load(R.drawable.avatar_four)
                            .into(profilePictureView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void checkBackStackOnQuizClose() {
        bottomNavigation.getMenu().getItem(QUIZ_MENU_POSITION).setChecked(false);
        bottomNavigation.getMenu().getItem(Integer.parseInt(SELECTED_ITEM)).setChecked(true);
    }

    public void checkBackPressedOnQuiz() {
        if (!SELECTED_ITEM.equals(String.valueOf(QUIZ_MENU_POSITION))) {
            checkBackStackOnQuizClose();
        }
    }

    public void callQuiz(Bundle quiz) {
        QuizFragment quizFragment = new QuizFragment();
        quizFragment.setArguments(quiz);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(SELECTED_ITEM)
                .replace(R.id.mainFrame, quizFragment)
                .commit();
        frag_selected_back.push(SELECTED_ITEM);
        Log.d("Demo", "Added: " + SELECTED_ITEM);
        SELECTED_ITEM = String.valueOf(QUIZ_MENU_POSITION);
        Log.d("Firebase",""+QuizFragment.class.getSimpleName());
        firebaseAnalytics.setCurrentScreen(HomeActivity.this,""+QuizFragment.class.getSimpleName(),"Home");
    }

    public void callQuizFromWordlist(Bundle quiz) {
        callQuiz(quiz);
        bottomNavigation.getMenu().getItem(WORD_MENU_POSITION).setChecked(true);
        bottomNavigation.getMenu().getItem(Integer.parseInt(SELECTED_ITEM)).setChecked(true);
    }

    public void pushWordOntoStack() {
        frag_selected_back.push(String.valueOf(WORD_MENU_POSITION));
    }

    public void pushCategoryOntoStack() {
        frag_selected_back.push(String.valueOf(CATEGORIES_MENU_POSITION));
    }

    public int getWordCount() {
        return presenter.getWordCount();
    }

    public void setNotification(){

        Log.i("Alarm:","Set");

        alarmIntent = new Intent(this, AlarmReceiverMain.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

/*
        alarmManagerRemember = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmIntentRemember = new Intent(this, AlarmReceiverRemember.class);
        pendingIntentRemember = PendingIntent.getBroadcast(this, 0, alarmIntentRemember, 0);


        Calendar calendarRemember = Calendar.getInstance();
        calendarRemember.set(Calendar.HOUR_OF_DAY, 18);
        calendarRemember.set(Calendar.MINUTE,37);
        if(calendarRemember.before(new GregorianCalendar())){
            calendarRemember.add(Calendar.MINUTE, 2);
        }
        alarmManagerRemember.setRepeating(AlarmManager.RTC_WAKEUP, calendarRemember.getTimeInMillis(),
                1000*120,pendingIntentRemember);
   */
    }

    public void unsetNotification(){
        Log.i("Alarm:","Unset");
        alarmIntent = new Intent(this, AlarmReceiverMain.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

 /*       alarmManagerRemember = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmIntentRemember = new Intent(this, AlarmReceiverRemember.class);
        pendingIntentRemember = PendingIntent.getBroadcast(this, 0, alarmIntentRemember, 0);
        alarmManagerRemember.cancel(pendingIntentRemember);
*/
    }

    @Override
    public void showProgress(int progress){
        if(progress!=7){
            tv_progress.setText("Profile "+progress*100/7+"% complete");
        } else {
            tv_progress.setText("Yay! Profile complete");
        }

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("");
        xVals.add("");
        yvalues.add(new Entry(progress,0));
        yvalues.add(new Entry(7-progress,1));
        PieDataSet dataSet = new PieDataSet(yvalues, "");
        int arr[]=new int[arr_colour.size()];
        int i=0;
        for(Integer n : arr_colour){
            arr[i++]=n;
        }
        dataSet.setColors(arr, this);
        PieData data = new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(.001f);
        progressChart.setData(data);
        progressChart.setVisibility(View.VISIBLE);
        progressChart.animateY(2000);
    }

}