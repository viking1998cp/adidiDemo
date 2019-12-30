package manhthang.adididemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.io.File;

import manhthang.adididemo.Common;
import manhthang.adididemo.Object.ImageAlbum;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityPictrureBinding;

public class PictrureActivity extends AppCompatActivity {

    private ActivityPictrureBinding binding;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    private ImageAlbum image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pictrure);

        image = (ImageAlbum) getIntent().getSerializableExtra("image");
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        initView();
        setUpOnClick();
    }

    private void setUpOnClick() {

        binding.imvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
    }

    private void initView() {
        Bitmap bitmap = Common.returnBitmapFromStorage(image.getPath());
        if(bitmap != null){
            binding.imvImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
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
