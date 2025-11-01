package com.snagotp.app;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;

/**
 * MainActivity - Main entry point for the SnagOTP app
 * Integrates OTP detection from SMS/RCS messages
 * Contributors: Ensure proper permissions are granted before using OTP features
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private static final int SMS_PERMISSION_REQUEST_CODE = 100;
    
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    
    // BroadcastReceiver for SMS
    private SmsReceiver smsReceiver;
    private boolean isReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        // Initialize drawer layout and navigation view
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
        // Setup drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        
        // Load default fragment (Home)
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home);
        }
        
        // Initialize SMS receiver for OTP detection
        initializeSmsReceiver();
        
        // Check and request SMS permissions
        checkSmsPermissions();
    }

    /**
     * Initialize the SMS broadcast receiver
     */
    private void initializeSmsReceiver() {
        try {
            smsReceiver = new SmsReceiver();
            Log.i(TAG, "SMS Receiver initialized");
        } catch (Exception e) {
            Log.e(TAG, "Error initializing SMS receiver: " + e.getMessage(), e);
        }
    }

    /**
     * Check if SMS permissions are granted, request if not
     */
    private void checkSmsPermissions() {
        // Check if we need to request permissions (Android 6.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                
                Log.i(TAG, "SMS permission not granted, requesting...");
                
                // Request SMS permissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
                        SMS_PERMISSION_REQUEST_CODE);
            } else {
                // Permission already granted, register receiver
                Log.i(TAG, "SMS permission already granted");
                registerSmsReceiver();
            }
        } else {
            // For older Android versions, permissions are granted at install time
            registerSmsReceiver();
        }
    }

    /**
     * Register the SMS broadcast receiver
     */
    private void registerSmsReceiver() {
        if (smsReceiver != null && !isReceiverRegistered) {
            try {
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.provider.Telephony.SMS_RECEIVED");
                filter.setPriority(Integer.MAX_VALUE);
                
                // Register receiver
                registerReceiver(smsReceiver, filter);
                isReceiverRegistered = true;
                
                Log.i(TAG, "SMS Receiver registered successfully");
                Toast.makeText(this, "OTP auto-detection enabled", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "Error registering SMS receiver: " + e.getMessage(), e);
                Toast.makeText(this, "Failed to enable OTP detection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Unregister the SMS broadcast receiver
     */
    private void unregisterSmsReceiver() {
        if (smsReceiver != null && isReceiverRegistered) {
            try {
                unregisterReceiver(smsReceiver);
                isReceiverRegistered = false;
                Log.i(TAG, "SMS Receiver unregistered");
            } catch (Exception e) {
                Log.e(TAG, "Error unregistering SMS receiver: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Handle permission request results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "SMS permission granted by user");
                Toast.makeText(this, "SMS permission granted. OTP auto-detection enabled.", Toast.LENGTH_LONG).show();
                
                // Register the receiver now that permission is granted
                registerSmsReceiver();
            } else {
                Log.w(TAG, "SMS permission denied by user");
                Toast.makeText(this, "SMS permission denied. OTP auto-detection disabled.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();
        
        if (itemId == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (itemId == R.id.nav_snags) {
            fragment = new SnagsFragment();
        } else if (itemId == R.id.nav_settings) {
            fragment = new SettingsFragment();
        } else if (itemId == R.id.nav_help) {
            fragment = new HelpFragment();
        }
        
        if (fragment != null) {
            loadFragment(fragment);
        }
        
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Unregister receiver when activity is paused to save resources
     */
    @Override
    protected void onPause() {
        super.onPause();
        // Optional: Unregister receiver when app is in background
        // Comment out if you want OTP detection to work in background
        // unregisterSmsReceiver();
    }

    /**
     * Re-register receiver when activity resumes
     */
    @Override
    protected void onResume() {
        super.onResume();
        // Re-register if needed
        if (smsReceiver != null && !isReceiverRegistered) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                    == PackageManager.PERMISSION_GRANTED) {
                registerSmsReceiver();
            }
        }
    }

    /**
     * Clean up receiver when activity is destroyed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterSmsReceiver();
        Log.i(TAG, "MainActivity destroyed, SMS receiver cleaned up");
    }
}
