package manhthang.adididemo.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import manhthang.adididemo.MainActivity;
import manhthang.adididemo.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
/**
 * Created by manh thắng 98.
 */
public class SplashActivity extends Activity {

    private AlertDialog myAlertDialog;

    private String strJson;

    /**
     * @return -True: connect GooglePlayServices success
     * -False: Error
     */
    private boolean checkPlayServices() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
                showErrorDialog(status);
            } else {
                Toast.makeText(this, getResources().getString(R.string.toast_msg_no_support),
                        Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * @param code
     */
    private void showErrorDialog(int code) {
        GooglePlayServicesUtil.getErrorDialog(code, this,
                REQUEST_CODE_RECOVER_PLAY_SERVICES).show();
    }

    private static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 1001;

    /**
     * Check permissions Android >=6
     *
     * @return -True if all permissions agree
     * -False if one permisson not agree
     */
    private boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int FourPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS);
        int FivePermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int SixPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FivePermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    private static final int RequestPermissionCode = 101;

    private void requestPermission() {
        ActivityCompat.requestPermissions(SplashActivity.this, new String[]
                {CAMERA,
                        READ_EXTERNAL_STORAGE,
                        ACCESS_FINE_LOCATION,
                        SEND_SMS,
                        READ_PHONE_STATE,
                        WRITE_EXTERNAL_STORAGE
                }, RequestPermissionCode);

    }

    /**
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //getConfigLink();
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_RECOVER_PLAY_SERVICES:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, getResources().getString(R.string.toast_msg_setup_service_google),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        //  Config.Current_Activity = this;
       /* String SPnameLanguage = "chonngonngu";
        SharedPreferences SPLanguage = getSharedPreferences(SPnameLanguage, MODE_PRIVATE);
        Boolean checkLanguage = SPLanguage.getBoolean(SPnameLanguage, true);
        if (checkLanguage) {
            Locale myLocale = new Locale("vi");
            //saveLocale(lang, activity);
            Locale.setDefault(myLocale);
            android.content.res.Configuration config = new android.content.res.Configuration();
            config.locale = myLocale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {
            Locale myLocale = new Locale("en");
            //saveLocale(lang, activity);
            Locale.setDefault(myLocale);
            android.content.res.Configuration config = new android.content.res.Configuration();
            config.locale = myLocale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission()) {
                requestPermission();
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        if (myAlertDialog != null) {
            try {
                myAlertDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (checkPlayServices()) {
                if (!isTaskRoot()) {
                    finish();
                    return;
                }

                //tv_Vesion = findViewById(R.id.tv_Vesion);
                //			tv_Vesion.setText(Config.VersionCode);
                /*Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            cd = new ConnectionDetector(getApplicationContext());
                            isInternetPresent = cd.isConnectingToInternet();
                            if (isInternetPresent == true) {
                                try {
                                    new CheckVersion().execute();
                                } catch (Exception e) {
                                    // TODO: handle exception
                                    Toast.makeText(getBaseContext(),
                                            getResources().getString(R.string.toast_message_error_try),
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {

                                myAlertDialog = AlertDialog(getResources().getString(R.string.toast_message_not_network));
                                if (!isFinishing()) {
                                    myAlertDialog.show();
                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, splash_duration);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }


    // Hàm khởi tạo Dialog
    private AlertDialog AlertDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Thiết lập tiêu đề hiển thị
        builder.setTitle(getResources().getString(R.string.dialog_thongbao));
        // Thiết lập thông báo hiển thị
        builder.setMessage(msg);
        // Tạo nút Thoát
        builder.setNegativeButton(getResources().getString(R.string.dialog_title_close),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        return builder.create();
    }

}
