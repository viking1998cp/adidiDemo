package manhthang.adididemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

import manhthang.adididemo.Object.ImageAlbumObject;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityPictrureBinding;

public class PictrureActivity extends AppCompatActivity {

    private ActivityPictrureBinding binding;
    private ImageAlbumObject image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pictrure);

        image = (ImageAlbumObject) getIntent().getSerializableExtra("image");
        initView();
    }

    private void initView() {
        File imgFile = new File(image.getPath());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            binding.imvImage.setImageBitmap(myBitmap);
            Log.d("BBB", "initView: BB");
        }
    }
}
