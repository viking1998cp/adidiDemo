package manhthang.adididemo.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import manhthang.adididemo.Activity.DeliveryActivity;
import manhthang.adididemo.Data.Product;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.FragmentDeliveryIstallationBinding;

public class FragmentDeliveryInstallation extends Fragment {

    FragmentDeliveryIstallationBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery_istallation, container, false);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }




    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
    //Lấy data từ eventbus
    // UI updates must run on MainThread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        binding.etUserLocation.setText(event);
    }

}
