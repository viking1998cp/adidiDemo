package manhthang.adididemo.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import manhthang.adididemo.Activity.CameraActivity;
import manhthang.adididemo.Adapter.ItemImageProductAdapter;
import manhthang.adididemo.Dialog.ProductDialog;
import manhthang.adididemo.Object.ImageAlbum;
import manhthang.adididemo.Object.ProductGroup;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.FragmentDeliveryIstallationBinding;
import manhthang.adididemo.databinding.ItemImageAlbumBinding;

public class FragmentDeliveryInstallation extends Fragment {

    private FragmentDeliveryIstallationBinding binding;
    private ProductGroup product;
    private ItemImageProductAdapter productImageAdapter;
    private ArrayList<ImageAlbum> images;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery_istallation, container, false);

        setUpOnClick();
        setUpImageAlbumProduct();
        return binding.getRoot();
    }

    private void setUpImageAlbumProduct() {

        images = new ArrayList<>();
        productImageAdapter = new ItemImageProductAdapter(images);
        binding.recycleViewImageProduct.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 5));
        binding.recycleViewImageProduct.setAdapter(productImageAdapter);
    }

    private ProductDialog productDialog;
    private void setUpOnClick() {

        binding.imvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        productDialog = new ProductDialog.Builder(binding.getRoot().getContext())
                .title("Chọn loại sản phẩm")              // String or String Resource ID
                // String or String Resource ID

                .negativeButtonText("Xác nhận")   // String or String Resource ID
                .cancelable(true) // Dialog will dismiss if clicked outside
                .enableAnimation(false)
                // To enable enter and exit animations
                .negativeClickListener(new ProductDialog.Listener() {
                    @Override
                    public void onClick(ProductDialog productDialog) {

                        product = productDialog.getProduct();
                        binding.tvProucts.setText(product.getNameGroup());
                        Log.d("BBB", "onClick: "+product.getNameGroup());
                        productDialog.dismiss();
                    }
                }).build();
        binding.tvProucts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1 && requestCode==1){
            ImageAlbum image = (ImageAlbum) data.getSerializableExtra("image");
            if(image != null){
                images.add(image);
                productImageAdapter.notifyDataSetChanged();
            }
            Log.d("BBB", "onActivityResult: NNN");
        }
        Log.d("BBB", "onActivityResult: "+resultCode);

    }
}
