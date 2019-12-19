package manhthang.adididemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import manhthang.adididemo.Common;
import manhthang.adididemo.MainActivity;
import manhthang.adididemo.R;
import manhthang.adididemo.SharedPrefs;
import manhthang.adididemo.databinding.FragmentFunctionBinding;

public class FragmentFunction extends Fragment {
    private FragmentFunctionBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_function, container, false);

        setUpOnClick();
        return binding.getRoot();
    }

    private void setUpOnClick() {

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
    }

    private void logOut() {
        SharedPrefs.getInstance().remove(SharedPrefs.PHONENUMBER);
        SharedPrefs.getInstance().remove(SharedPrefs.PASSWORD);
        SharedPrefs.getInstance().put(SharedPrefs.STATUSLOGIN,false);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
