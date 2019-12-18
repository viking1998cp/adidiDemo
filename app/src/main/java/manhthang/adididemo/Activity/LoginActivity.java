package manhthang.adididemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import manhthang.adididemo.Fragment.FragmentPassword;
import manhthang.adididemo.Adapter.MainViewPager;
import manhthang.adididemo.Fragment.FragmentOTP;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityLoginBinding;

/**
 * Created by manh thắng 98.
 */

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        ActionToolbar();
        setInitView();

    }
    private void setInitView() {
        MainViewPager mainViewPager = new MainViewPager(getSupportFragmentManager());
        mainViewPager.addfragment(new FragmentPassword(),"Mật khẩu");
        mainViewPager.addfragment(new FragmentOTP(),"Mã OTP");
        binding.viewPager.setAdapter(mainViewPager);
        binding.mytablayout.setupWithViewPager(binding.viewPager);


    }
    private void ActionToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
