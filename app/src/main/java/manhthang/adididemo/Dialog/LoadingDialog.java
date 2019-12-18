package manhthang.adididemo.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.github.jorgecastillo.State;
import com.github.jorgecastillo.listener.OnStateChangeListener;
import com.google.android.material.tabs.TabLayout;

import manhthang.adididemo.PathVectorImage;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.LoadingDialogBinding;

public class LoadingDialog extends Dialog {
    private LoadingDialogBinding binding;

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                                        R.layout.loading_dialog,
                                        null,
                                        false);
        setContentView(binding.getRoot());
        binding.fillableLoader.setSvgPath(PathVectorImage.LOGO);
        Log.d("BBB", "onCreate: "+binding.getRoot().getRootView().getHeight());
//        binding.fillableLoader.setOriginalDimensions(binding.getRoot().getWidth()/2,binding.getRoot().getHeight()/2);
        binding.fillableLoader.start();
        binding.fillableLoader.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {

                if(state==State.FINISHED){
                    binding.fillableLoader.start();
                }
            }
        });
    }

}
