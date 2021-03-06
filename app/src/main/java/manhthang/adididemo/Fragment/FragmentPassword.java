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

import manhthang.adididemo.Activity.HomeMainActivity;
import manhthang.adididemo.Common;
import manhthang.adididemo.Data.AccountData;
import manhthang.adididemo.Dialog.LoadingDialog;
import manhthang.adididemo.Object.User;
import manhthang.adididemo.R;
import manhthang.adididemo.SharedPrefs;
import manhthang.adididemo.databinding.FragmentPasswordBinding;
/**
 * Created by manh thắng 98.
 */
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
            User user = AccountData.getAccount();
            if (phone.equalsIgnoreCase(user.getPhoneNumber()) &&
                    password.equalsIgnoreCase(user.getPassWord())) {
                saveAccount(phone, password);
                Intent intent = new Intent(getActivity(), HomeMainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }else {
                Common.ShowToast(getContext(),getActivity().getString(R.string.errorAccount));
            }
        }
    }

    private void saveAccount(String phone, String password) {
        SharedPrefs.getInstance().put(SharedPrefs.PHONENUMBER, binding.editPhone.getText().toString());
        SharedPrefs.getInstance().put(SharedPrefs.PASSWORD, binding.editPass.getText().toString());
        SharedPrefs.getInstance().put(SharedPrefs.STATUSLOGIN, true);
    }

}
