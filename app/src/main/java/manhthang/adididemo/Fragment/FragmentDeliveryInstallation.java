package manhthang.adididemo.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import manhthang.adididemo.Dialog.ProductDialog;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.FragmentDeliveryIstallationBinding;

public class FragmentDeliveryInstallation extends Fragment {

    FragmentDeliveryIstallationBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery_istallation, container, false);

        setUpOnClick();
        return binding.getRoot();
    }

    private void setUpOnClick() {
        binding.tvProucts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProductDialog productDialog;
                productDialog = new ProductDialog.Builder(binding.getRoot().getContext())
                        .title("Chọn loại sản phẩm")              // String or String Resource ID
                        // String or String Resource ID

                        .negativeButtonText("Hủy")   // String or String Resource ID
                        .cancelable(true) // Dialog will dismiss if clicked outside
                        .enableAnimation(false)
                        // To enable enter and exit animations
                        .negativeClickListener(new ProductDialog.Listener() {
                            @Override
                            public void onClick(ProductDialog productDialog) {
                                productDialog.dismiss();
                            }
                        }).build();
                productDialog.show();
            }
        });

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
