package com.example.a2020falll_deep_fake.picture;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    SurfaceHolder holder;
    Camera camera = null;

    public CameraSurfaceView(Context context) {
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int cameraId = findFrontSideCamera();
        camera = Camera.open(cameraId);

        Camera.Parameters param = camera.getParameters();
        List sizes = param.getSupportedPictureSizes();
        Camera.Size optimalSize;
        optimalSize = getOptimalPreviewSize(sizes, 1440, 1440);
        param.setPictureSize(optimalSize.width,optimalSize.height);
        camera.setParameters(param);
        camera.setDisplayOrientation(90);
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public boolean capture(Camera.PictureCallback callback){
        if(camera != null){
            camera.takePicture(null, null, callback);
            return true;
        } else {
            return false;
        }
    }

    private int findFrontSideCamera() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();

        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cmInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cmInfo);
            if (cmInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
         return cameraId;
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) h / w;
        if (sizes == null) {
            return null;
        }
        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;
        int targetHeight = h;
        for (Camera.Size size : sizes) {
            double ratio = (double) size.height / size.width;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) {
                continue;
            }
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
}