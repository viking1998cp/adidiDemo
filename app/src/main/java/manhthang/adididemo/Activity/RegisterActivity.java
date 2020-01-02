package manhthang.adididemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import manhthang.adididemo.Object.User;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityRegisterBinding;

/**
 * Created by manh thắng 98.
 */

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        registerButtonClick();
        ActionToolbar();
    }

    private void registerButtonClick() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check null
                User user =new User();
                Boolean check =true;
                if(binding.etfirstName.getText().toString().isEmpty()){
                    binding.etfirstName.setError(getString(R.string.errorFirstName));
                    check = false;
                }
                if(binding.etLastname.getText().toString().isEmpty()){
                    binding.etLastname.setError(getString(R.string.errorLastName));
                    check = false;
                }
                if(binding.etPhone.getText().toString().isEmpty()){
                    binding.etPhone.setError(getString(R.string.errorPhone));
                    check = false;
                }
                if(binding.etEmail.getText().toString().isEmpty()){
                    binding.etEmail.setError(getString(R.string.errorEmail));
                    check = false;
                }
                if(binding.etPass.getText().toString().isEmpty()){
                    binding.etPass.setError(getString(R.string.errorPass));
                    check = false;
                }
                if(check){
                    user.setFirstName(binding.etfirstName.getText().toString());
                    user.setLastName(binding.etLastname.getText().toString());
                    user.setPhoneNumber(binding.etPhone.getText().toString());
                    user.setEmail(binding.etEmail.getText().toString());
                    user.setPassWord(binding.etPass.getText().toString());
                    Intent intent = new Intent(RegisterActivity.this, ComfirmOTPActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
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
