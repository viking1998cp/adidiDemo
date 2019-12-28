package manhthang.adididemo.CustomCamera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // create the surface and start camera preview
            if (mCamera == null) {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            }
        } catch (IOException e) {
            Log.d(VIEW_LOG_TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void refreshCamera(Camera camera) {
        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }
        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        setCamera(camera);
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e) {
            Log.d(VIEW_LOG_TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.
        refreshCamera(mCamera);
    }

    public void setCamera(Camera camera) {
        //method to set a camera instance
        mCamera = camera;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        // mCamera.release();

    }

    public Camera getmCamera() {
        return mCamera;
    }
    public void stop() {
        if (null == getmCamera()) {
            return;
        }
//        try {
//            getmCamera().unlock();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            mHolder.removeCallback(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getmCamera().setPreviewDisplay(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            getmCamera().stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setPreviewCallback(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            getmCamera().release();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            setCamera(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPreviewCallback(Camera.PreviewCallback callback) {
        if (null == getmCamera()) {
            return;
        }
        getmCamera().setPreviewCallback(callback);
    }

    public void setFlashMode(String CameraFlashMode) {
        try {
            Camera.Parameters params = getmCamera().getParameters();
            params.setFlashMode(CameraFlashMode);
            getmCamera().setParameters(params);
        } catch (Exception e) {
            Log.d("BBB", "setFlashMode: "+e.getMessage());
            e.printStackTrace();
        }
    }

}