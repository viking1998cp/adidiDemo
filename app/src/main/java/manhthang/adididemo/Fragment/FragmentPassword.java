package manhthang.adididemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import manhthang.adididemo.Common;
import manhthang.adididemo.Data.AccountData;
import manhthang.adididemo.Dialog.LoadingDialog;
import manhthang.adididemo.R;
import manhthang.adididemo.SharedPrefs;
import manhthang.adididemo.databinding.FragmentPasswordBinding;

public class FragmentPassword extends Fragment {
    private View view;
    private FragmentPasswordBinding binding;
    private LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_password,
                null,
                false);
        setUploading();


        binding.btnControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.show();

                String phone = binding.editPhone.getText().toString();
                String password = binding.editPass.getText().toString();

                checkLogin(phone, password);
                loadingDialog.dismiss();
            }
        });


        View view = binding.getRoot();
        return view;
    }

    private void setUploading() {
        loadingDialog = new LoadingDialog(binding.getRoot().getContext(), R.style.translucentDialog);
        loadingDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        loadingDialog.setCancelable(false);
    }



    private void checkLogin(String phone, String password) {
        if (phone.isEmpty()) {
            binding.editPhone.setError("Không được để trống số điện thoại");
        }
        if (password.isEmpty()) {
            binding.editPass.setError("Không được để trống mất khẩu");
        }
        if (!phone.isEmpty() && !password.isEmpty()) {
            if (phone.equalsIgnoreCase(AccountData.USERNAME) &&
                    password.equalsIgnoreCase(AccountData.PASSWORD)) {
                saveAccount(phone, password);
                Intent intent = new Intent(getActivity(), Fragment_HomePage.class);
                startActivity(intent);
                getActivity().finish();

            }else {
                Common.ShowToast(getContext(),"Vui lòng kiểm tra tài khoản hoặc mật khẩu");
            }
        }
    }

    private void saveAccount(String phone, String password) {
        SharedPrefs.getInstance().put(Common.PHONENUMBER, binding.editPhone.getText().toString());
        SharedPrefs.getInstance().put(Common.PASSWORD, binding.editPass.getText().toString());
        SharedPrefs.getInstance().put(Common.STATUSLOGIN, true);
    }

}
