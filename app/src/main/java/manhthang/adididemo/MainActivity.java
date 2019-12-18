package manhthang.adididemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import manhthang.adididemo.Activity.HomeMainActivity;
import manhthang.adididemo.Fragment.Fragment_HomePage;
import manhthang.adididemo.Activity.LoginActivity;
import manhthang.adididemo.Activity.RegisterActivity;
import manhthang.adididemo.Dialog.LoadingDialog;
import manhthang.adididemo.databinding.ActivityMainBinding;

/**
 * Created by manh thắng 98.
 */

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        checkStatusLogin();
        setOnClick();
//        LoadingDialog loadingDialog = new LoadingDialog(binding.getRoot().getContext(), R.style.translucentDialog);
//        loadingDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        loadingDialog.setCancelable(false);
//        loadingDialog.show();


    }

    private void checkStatusLogin() {
        boolean status = SharedPrefs.getInstance().get(Common.STATUSLOGIN, Boolean.class);
        if (status) {
            Intent intent = new Intent(MainActivity.this, HomeMainActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }


    private void setOnClick() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
