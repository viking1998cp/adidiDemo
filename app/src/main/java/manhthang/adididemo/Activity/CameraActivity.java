package manhthang.adididemo.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import manhthang.adididemo.Common;
import manhthang.adididemo.CustomCamera.CameraPreview;
import manhthang.adididemo.Manager.GoogleApiModel;
import manhthang.adididemo.Object.ImageAlbumObject;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityCameraCaptrureBinding;

public class CameraActivity extends Activity {

    private ActivityCameraCaptrureBinding binding;
    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private Context myContext;
    private GoogleApiModel googleGPS;

    private boolean cameraFront = false;
    public static Bitmap bitmap;

    private ImageView imgPreview, img_change_camera_back;
    private int Orient = 90;
    private int LastOrient = 0;
    private final int change_camera = 0;
    private int temp_change_camera = change_camera;
    private String stateFlash = "auto";
    private boolean isSafeToTakePicture = true;
    private int witdh, height;
    private static final int PICTURE_QUALITY = 85;
    private double kinhdo;
    private double vido;
    private double acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera_captrure);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;

        mCamera =  Camera.open();
        mCamera.setDisplayOrientation(90);

        mPreview = new CameraPreview(myContext, mCamera);
        binding.cPreview.addView(mPreview);

        mCamera.startPreview();

        setUpOnclick();

    }

    private void setUpOnclick() {
        binding.imvCaptrure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isSafeToTakePicture) {

                        // MD5.showLog("Orient:" + Orient);

                        // MD5.showLog("LastOrient:" + LastOrient);

                        mPreview.getmCamera().takePicture(shutterCallback, null, jpegCallback);
//                        binding.cPreview.setVisibility(View.VISIBLE);
//                        new Handler().postDelayed(new Runnable() {
//                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                            @Override
//                            public void run() {
//                                binding.cPreview.setVisibility(View.INVISIBLE);
//                            }
//                        }, 150);
//                        isSafeToTakePicture = false;
                    }
                } catch (Exception ex) {
                    isSafeToTakePicture = true;
                    ex.printStackTrace();
                }
            }
        });

        binding.imvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa

                } else {

                }
            }
        });

    }

    // xoay camera mac dinh theo chieu dung
    private int getLastOrient(int orient) {
        Log.d("orient: ", orient + "");

       /* if (temp_change_camera == 0) {
            return 90;
        }else {
            return 270;
        }*/

        if (temp_change_camera == 0) {
            if (orient == -1 || (orient >= 0 && orient < 45) || (orient >= 315)) {
                return 90;
            } else if (orient >= 45 && orient < 180) {
                return 180;// chinh xoay hinh anh chup
            } else if (orient < 315 && orient >= 180) {
                return 0;
            }
        } else {
            if (orient == -1 || (orient >= 0 && orient < 45) || (orient >= 315)) {
                return 90;
            } else if (orient >= 45 && orient < 180) {
                return 180;// chinh xoay hinh anh chup
            } else if (orient < 315 && orient >= 180) {
                return 0;
            }

           /* if (orient == -1 || (orient >= 0 && orient < 45) || (orient >= 315)) {
                return 270;
            } else if (orient >= 45 && orient < 180) {
                return 180;// chinh xoay hinh anh chup
            } else if (orient < 315 && orient >= 180) {
                return 0;
            }*/

        }
        return 0;

    }



    public void onResume() {

        super.onResume();
        if(mCamera == null) {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mPicture = getPictureCallback();
            mPreview.refreshCamera(mCamera);
            Log.d("nu", "null");
        }else {
            Log.d("nu","no null");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //when on Pause, release camera in order to be used from other applications
        releaseCamera();
    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                Intent intent = new Intent(CameraActivity.this,PictureActivity.class);
//                startActivity(intent);
            }
        };
        return picture;
    }

    private final Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = null;
            try {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inJustDecodeBounds = true;
                opt.inMutable = true;
                opt.inJustDecodeBounds = false;
                opt.inDither = false;
                opt.inPurgeable = true;
                opt.inInputShareable = true;
                opt.inPreferredConfig = Bitmap.Config.RGB_565;

                Matrix matrix = new Matrix();
                //int witdh = 800, height = 600;


                opt.inSampleSize = calculateInSampleSize(opt, witdh, height);
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opt);


                if (temp_change_camera == 1) {
                    // bitmap = getResizedBitmap(bitmap, witdh, height, matrix);
                    float sizew = bitmap.getWidth() / witdh;
                    float sizeh = bitmap.getHeight() / height;
                    float sizewh = Math.max(sizew, sizeh);
                    if (sizewh > 1) {
                        bitmap = resizeBitmap(bitmap, sizewh);
                    }
                    matrix.setScale(2, 2);
//                    matrix.postRotate();
                    bitmap = getResizedBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), matrix);
                } else {

                    float sizew = bitmap.getWidth() / witdh;
                    float sizeh = bitmap.getHeight() / height;
                    float sizewh = Math.max(sizew, sizeh);
                    if (sizewh > 1) {
                        bitmap = resizeBitmap(bitmap, sizewh);
                    }
                    matrix.setScale(1, 1);
                    matrix.postRotate(LastOrient);
                    bitmap = getResizedBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), matrix);
                    //opt.inSampleSize = calculateInSampleSize(opt, witdh, height);
                    // bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opt);
                    // bitmap = getResizedBitmap(bitmap, witdh, height, matrix);

                }


                bitmap = themTimestampVaoAnh(bitmap);

                if (!SaveImageToDisk(bitmap)) {
                    Common.ShowToast(CameraActivity.this,getString(R.string.toast_msg_err_save_picture_please_try));
                }
            } catch (OutOfMemoryError ex) {
                //HamDungChung.ShowToast(Activity_ChupAnh_New.this, "Lỗi trong quá trình chụp và lưu ảnh, vui lòng chụp lại");
                //ex.printStackTrace();
                witdh = 600;
                height = 450;
                try {
                    System.gc();
                } catch (Exception ignored) {

                }

            } catch (Exception e) {
                // HamDungChung.ShowToast(Activity_ChupAnh_New.this, "Lỗi trong quá trình chụp và lưu ảnh, vui lòng chụp lại");
                //e.printStackTrace();
                witdh = 600;
                height = 450;
                try {
                    System.gc();
                } catch (Exception ignored) {

                }
            } finally {
                try {
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    System.gc();
                } catch (Exception ignored) {

                }
                try {
                    mPreview.getmCamera().startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isSafeToTakePicture = true;
            }


        }
    };

    // them thoi gian len tren anh khi chup
    private Bitmap themTimestampVaoAnh(Bitmap toEdit) {

        String dateTime = Common.formatChangePointDateTime(Calendar.getInstance().getTime()); // Lay thoi gian hien tai ve len anh chup

        Canvas cs = new Canvas(toEdit);
        Paint tPaint = new Paint();
        tPaint.setTextSize(35);
        tPaint.setColor(Color.RED);
        tPaint.setStyle(Paint.Style.FILL);
        float height = tPaint.measureText("yY");
        float weight = tPaint.measureText(dateTime);
        cs.drawText(dateTime, cs.getWidth() - weight - 10f, cs.getHeight() - height, tPaint);

        return toEdit;
    }

    private static Bitmap resizeBitmap(Bitmap bitmap, float pickSize) {
        int dstWidth = (int) (bitmap.getWidth() / pickSize);
        int dstHeight = (int) (bitmap.getHeight() / pickSize);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, dstWidth,
                dstHeight, false);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return scaledBitmap;
    }

    private Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight, Matrix matrix) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        //Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        if (bm != null && !bm.isRecycled()) {
            bm.recycle();
        }
        return resizedBitmap;
    }



    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    // luu anh khi chup vao bo nho may
    private boolean SaveImageToDisk(Bitmap bm) {
        FileOutputStream outStream = null;

        // Write to SD Card
        try {
            String fileName = String.format("%d.jpg",
                    System.currentTimeMillis());
            File dir;
            File outFile;
            File sdCard = Environment.getExternalStorageDirectory();
            dir = new File(sdCard.getAbsolutePath() + "/ADiDi");
            dir.mkdirs();
            outFile = new File(dir, fileName);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, PICTURE_QUALITY, stream);
            Bitmap anhNho = Bitmap.createScaledBitmap(bm, bm.getWidth(), bm.getHeight(), false);

            try {
                if (imgPreview != null && imgPreview.getDrawable() != null) {
                    Bitmap bmNho = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
                    if (bmNho != null && !bmNho.isRecycled()) {
                        bmNho.recycle();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                imgPreview.setImageDrawable(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                imgPreview.setImageBitmap(anhNho);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            byte[] byteArray = stream.toByteArray();
            outStream = new FileOutputStream(outFile);
            outStream.write(byteArray);
            outStream.flush();

            try {
                ImageAlbumObject image = new ImageAlbumObject();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
                String dateTime = sdf.format(Calendar.getInstance().getTime());
                image.setThoigianchup(dateTime);
                image.setKinhdo(kinhdo);
                image.setVido(vido);
                image.setDiachi(Common.getDiaChi( vido, kinhdo));
                image.setAcc(acc);
                image.setPath(fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }

            refreshGallery(outFile);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bm != null && !bm.isRecycled()) {
                try {
                    bm.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
    // bat tieng khi nhan nut chup anh
    private final Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        public void onShutter() {
            // Log.d(TAG, "onShutter'd");
        }
    };

    private void refreshGallery(File file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(file));
        sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onDestroy() {
        if (mPreview != null) {
            try {
                mPreview.stop();
                binding.cPreview.removeView(mPreview);
                mPreview = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (imgPreview != null) {
            try {
                if (imgPreview != null && imgPreview.getDrawable() != null) {
                    Bitmap bmNho = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
                    if (bmNho != null && !bmNho.isRecycled()) {
                        bmNho.recycle();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                imgPreview.setImageDrawable(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            googleGPS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}
