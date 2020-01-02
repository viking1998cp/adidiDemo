package manhthang.adididemo.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import manhthang.adididemo.Common;
import manhthang.adididemo.Object.ImageAlbum;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.FragmentPictrureBinding;
/**
 * Created by manh thắng 98.
 */
public class FragmentViewPicture extends Fragment {

    private FragmentPictrureBinding binding;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    private ImageAlbum image;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pictrure, container, false);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            image = (ImageAlbum) bundle.getSerializable("image");
        }

        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

        initView();
        setUpOnClick();

        return binding.getRoot();
    }

    private void setUpOnClick() {

        binding.imvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        binding.imvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("image", image);
                getActivity().setResult(1, intent);
                getActivity().finish();
            }
        });

        binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                scaleGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    private void initView() {
        Bitmap bitmap = Common.returnBitmapFromStorage(image.getPath());
        if(bitmap != null){
            binding.imvImage.setImageBitmap(bitmap);
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            binding.imvImage.setScaleX(mScaleFactor);
            binding.imvImage.setScaleY(mScaleFactor);
            return true;
        }
    }
}
