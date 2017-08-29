package net.fengyun.unified.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import net.fengyun.common.app.Activity;
import net.fengyun.unified.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;

/**
 * 自定义相机功能
 *
 * @author fengyun
 */
public class CustomCameraActivity extends Activity implements SurfaceHolder.Callback {

    private Camera mCamera;

    /**
     * MainActivity跳转的入口
     * @param context 上下文
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, CustomCameraActivity.class));
    }


    @BindView(R.id.preview)
    SurfaceView mPreView;
    private SurfaceHolder mHolder;
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            FileOutputStream fos = null;
            File tempFile = new File("/sdcard/temp.png");
            try {
                fos = new FileOutputStream(tempFile);
                fos.write(data);
                Intent intent = new Intent(CustomCameraActivity.this,TestActivity.class);
                intent.putExtra("picPath",tempFile.getAbsolutePath());
                startActivity(intent);
                CustomCameraActivity.this.finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_custom_camera;
    }

    public void capture(View view) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setPreviewSize(800, 400);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        //得到 SurfaceHolder
        mHolder = mPreView.getHolder();
        //设置回调事件
        mHolder.addCallback(this);
        //设置点击事件，实现对焦功能
        mPreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.autoFocus(null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera();
            if (mHolder != null) {
                setStartPreView(mCamera, mHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * 获取Camera对象
     *
     * @return
     */
    private Camera getCamera() {
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }

        return camera;
    }

    /**
     * 开始预览相机内容
     */
    private void setStartPreView(Camera camera, SurfaceHolder holder) {
        //相机和holder绑定
        try {
            camera.setPreviewDisplay(holder);
            //将系统Camera预览角度进行调整
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mHolder.removeCallback(this);
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.lock();
            mCamera.release();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreView(mCamera, mHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        setStartPreView(mCamera, mHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }
}
