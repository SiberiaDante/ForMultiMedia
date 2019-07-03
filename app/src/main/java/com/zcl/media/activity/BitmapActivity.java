package com.zcl.media.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.zcl.media.R;
import com.zcl.media.utils.ThreadUtils;

import java.io.File;

public class BitmapActivity extends AppCompatActivity {

    public static final String TAG = BitmapActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        getBitmap();
    }

    private void getBitmap() {
        ImageView ivBitmap = findViewById(R.id.ivBitmap);
//        获取 Android 数据目录。即data的目录(/data)
//        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getDataDirectory().getPath() + File.separator + "11.jpg");
//        获取外部存储目录即 SDCard  (/storage/sdcard)
//        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + File.separator + "11.jpg");
//        获取 Android 的根目录 即系统主目录(/system)
//        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getRootDirectory().getPath() + File.separator + "11.jpg");
//        获取 Android 下载/缓存内容目录。即(/cache)
        /*

         */
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + File.separator + "11.jpg");
        ivBitmap.setImageBitmap(bitmap);
    }

    private void setSurface() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (surfaceHolder == null) {
                    return;
                }
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);

                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + File.separator + "11.jpg");  // 获取bitmap
                Canvas canvas = surfaceHolder.lockCanvas();  // 先锁定当前surfaceView的画布
                canvas.drawBitmap(bitmap, 0, 0, paint); //执行绘制操作
                surfaceHolder.unlockCanvasAndPost(canvas); // 解除锁定并显示在界面上
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
    }

    public class CustomView extends View {

        Paint paint = new Paint();
        Bitmap bitmap;

        public CustomView(Context context) {
            super(context);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + File.separator + "11.jpg");  // 获取bitmap
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // 不建议在onDraw做任何分配内存的操作
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, paint);
            }
        }
    }

}
