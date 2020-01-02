package manhthang.adididemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import manhthang.adididemo.Common;
import manhthang.adididemo.Data.AccountData;
import manhthang.adididemo.Object.User;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityComfirmOtpBinding;
/**
 * Created by manh thắng 98.
 */

public class ComfirmOTPActivity extends AppCompatActivity {
    ActivityComfirmOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comfirm_otp);
        ActionToolbar();

        setUpOnClick();
    }

    private void setUpOnClick() {
        //Check mã OTP
        binding.btnAcvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.otpComfirm.getText().toString().equalsIgnoreCase(AccountData.OTP)){
                    Intent intent = new Intent(ComfirmOTPActivity.this, HomeMainActivity.class);
                    User user = (User) getIntent().getSerializableExtra("user");
                    AccountData.register(user);
                    Common.ShowToast(ComfirmOTPActivity.this,getString(R.string.successRegister));
                    startActivity(intent);
                }else {
                    Common.ShowToast(ComfirmOTPActivity.this,getString(R.string.errorRegister));
                }
            }
        });
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
