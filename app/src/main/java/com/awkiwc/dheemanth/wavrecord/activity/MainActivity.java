package com.awkiwc.dheemanth.wavrecord.activity;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.awkiwc.dheemanth.wavrecord.fragments.HomeFragment;
import com.awkiwc.dheemanth.wavrecord.fragments.LoginFragment;
import com.awkiwc.dheemanth.wavrecord.fragments.RegistrationFragment;
import com.awkiwc.dheemanth.wavrecord.fragments.VoiceFragment;
import com.awkiwc.dheemanth.wavrecord.R;
import com.awkiwc.dheemanth.wavrecord.enums.FragmentType;
import com.awkiwc.dheemanth.wavrecord.interfaces.Counter;
import com.awkiwc.dheemanth.wavrecord.interfaces.FragmentInterface;
import com.awkiwc.dheemanth.wavrecord.util.ActionBarHelper;
import com.awkiwc.dheemanth.wavrecord.util.AwkiwcPreferenceManager;
import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;


public class MainActivity extends AppCompatActivity implements Counter {

    public static final String developerId = "db3015aef2ee411aaeaefeb3098481be";
    final int REQUEST_PERMISSION_CODE = 1000;

    private int count = 0;
    private static final String FLAG_VALUE = "flagValue";
    private static final String DISPLAY_HOME_AS_UP_ENABLED = "displayHomeAsUpEnabled";
    private static final String ACTIONBAR_TITLE  = "actionBarTitle";
    private boolean displayHome = false;
    private String actionBarTitle;
    ActionBarHelper actionBarHelper;
    int flagValue=0;

    public void setCustomToolbar(int flags) {
        getSupportActionBar().setCustomView(actionBarHelper.getActionBarView(getLayoutInflater(), flags));
    }

    AwkiwcPreferenceManager preferenceManager;
    Fragment currentFragment = null;

    public ActionBarHelper getActionBarHelper() {
        return actionBarHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        if (!checkPermissionFromDevice()) {
            requestPermission();
        }

        performInitTasks(savedInstanceState);
        FragmentType type = FragmentType.LOGIN;
        openRelevantFragment(type, null, false);

    }

    public void openRelevantFragment(FragmentType fragmentType, Bundle arguments, boolean isReplace) {
        FragmentInterface fragment = null;
        boolean commitAllowingStateLoss = false;
        AwkiwcUtils.hideKeyboardOnButtonPress(this);
        if(actionBarHelper != null) {
            getSupportActionBar().setCustomView(actionBarHelper.getActionBarView(getLayoutInflater(), ActionBarHelper.HOME_PAGE_MORE_ICON | ActionBarHelper.TITLE));
        }
        String tag = null;
        switch (fragmentType) {
            case LOGIN:
                fragment = new LoginFragment();
                break;
            case SIGNUP:
                fragment = new RegistrationFragment();
                commitAllowingStateLoss = true;
                break;
            case HOME:
                clearBackStack();
                fragment = new HomeFragment();
                setActionBarTitleAndEnableBack("Khul ja Sim Sim", false);
                break;
            case VOICE:
                if(count == 3){
                    count = 0;
                    fragment = new LoginFragment();
                } else {
                    fragment = new VoiceFragment();
                }
                break;
        }
        tag = fragmentType.toString();
        openOrReplaceFragment(fragmentType, fragment, tag, arguments, isReplace, commitAllowingStateLoss);
    }

    private void setActionBarTitleAndEnableBack(String title, boolean displayHomeEnabled) {
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(displayHomeEnabled);
            displayHome = displayHomeEnabled;
            actionBarTitle = title;
            actionBarHelper.setActionBarTitle(title);
        }
    }
    private void openOrReplaceFragment(FragmentType fragmentType, FragmentInterface fragment, String tag, Bundle arguments, boolean isReplace, boolean commitAllowingStateLoss) {
        Fragment newFragment;
        if (fragment instanceof Fragment) {
            newFragment = (Fragment) fragment;
            // A user has entered through login or sign up. Now, login and signup are not relevant, hence remove from the stack
            if ((currentFragment instanceof LoginFragment || currentFragment instanceof RegistrationFragment) && (newFragment instanceof HomeFragment)) {
                while (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStackImmediate();
                }
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setVisibility(View.VISIBLE);
            }
            if (arguments != null) {
                newFragment.setArguments(arguments);
            }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.slide_in_left, R.animator.slide_out_right);
            if (isReplace) {
                transaction.replace(R.id.fragment_container, newFragment, tag);
            } else {
                transaction.add(R.id.fragment_container, newFragment, tag);
            }
            transaction.addToBackStack(tag);
            if (commitAllowingStateLoss) {
                transaction.commitAllowingStateLoss();
            } else {
                transaction.commit();
            }
            currentFragment = newFragment;
            AwkiwcUtils.fragmentType = fragmentType;
        }
    }

    private void clearBackStack() {
        final android.app.FragmentManager fm = getFragmentManager();
        popFragmentFromStack(FragmentType.HOME.toString(), fm);
    }

    private void popFragmentFromStack(String tag, android.app.FragmentManager fm) {
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    private void performInitTasks(Bundle savedInstanceState) {
        preferenceManager = new AwkiwcPreferenceManager(getApplicationContext());
        AwkiwcUtils.preferenceManager = preferenceManager;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        invalidateOptionsMenu();
        actionBarHelper = new ActionBarHelper();
        if(savedInstanceState == null) {
            setCustomToolbar(ActionBarHelper.HOME_PAGE_MORE_ICON | ActionBarHelper.SETTINGS_ICON | ActionBarHelper.TITLE);
        } else {
            flagValue = savedInstanceState.getInt(FLAG_VALUE);
            displayHome = savedInstanceState.getBoolean(DISPLAY_HOME_AS_UP_ENABLED);
            actionBarTitle = savedInstanceState.getString(ACTIONBAR_TITLE);
            setCustomToolbar(flagValue);
            setActionBarTitleAndEnableBack(actionBarTitle, displayHome);
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        return write_external_storage_result == PackageManager.PERMISSION_GRANTED && record_audio_result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void increment() {
        this.count++;
    }
}
