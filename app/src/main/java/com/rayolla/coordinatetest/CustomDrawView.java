package com.rayolla.coordinatetest;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomDrawView extends View {
    private static final String TAG = "CoordinateTest_CustomDrawView";
    private static final int DEFAULT_LINE_INTERVAL = 30;
    private static final int START_X = 20;
    private static final int START_Y = 20;
    private int mWidth_X;
    private int mHeight_Y;
    private int mLineNumber = 50;
    private int mXNum = 50;   // number to draw from X to Y
    private int mYNum = 46;   // number to draw from Y to X

    // FOR TEST
//    private int mXNum = 2;  // number to draw from X to Y
//    private int mYNum = 2;  // number to draw from Y to X

    public CustomDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "width(X): " + canvas.getWidth());
        Log.d(TAG, "height(Y): " + canvas.getHeight());
        mWidth_X = canvas.getWidth();
        mHeight_Y = canvas.getHeight();

        Log.d(TAG, "MaximumBitmapHeight: " + canvas.getMaximumBitmapHeight());
        Log.d(TAG, "MaximumBitmapWidth: " + canvas.getMaximumBitmapWidth());

        Log.d(TAG, "X축 샘플 100:  ");

        /*
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorPrimary));
//        paint.setStrokeWidth(5);    // 선 두께

//        paint.setStrokeWidth(20);
//        canvas.drawPoint(100, 100, paint);  // 점

        paint.setStrokeWidth(2);    // 선 두께
        paint.setColor(Color.BLACK);
        */

        /*
        canvas.drawLine(140, 140, 140, 1000, paint);
        canvas.drawLine(200, 200, 200, 1000, paint);
        canvas.drawLine(400, 400, 400, 1200, paint);
        canvas.drawLine(430, 400, 430, 1200, paint);
        canvas.drawLine(470, 400, 470, 1200, paint);
        */

        // X축
        /*
        canvas.drawLine(140, 140, 140, 1200, paint);
        canvas.drawLine(170, 140, 170, 1200, paint);
        canvas.drawLine(200, 140, 200, 1200, paint);
        canvas.drawLine(230, 140, 230, 1200, paint);
        */

        // Y축
        // 점선 설정
        /*
//        PathEffect pathEffect = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect pathEffect = new DashPathEffect(new float[]{10, 5}, 0);
        paint.setPathEffect(pathEffect);

        canvas.drawLine(140, 140, 3100, 140, paint);
        canvas.drawLine(140, 170, 3100, 170, paint);
        canvas.drawLine(140, 200, 3100, 200, paint);
        */

        drawX(canvas);
        drawY(canvas);
    }

    private void drawX(Canvas canvas) {
        Paint paint = new Paint();
        PathEffect pathEffect = new DashPathEffect(new float[]{10, 5}, 0);

        int startX = START_X;
        int startY = START_Y;
        int stopX = START_X;
//        int stopY = mXNum*DEFAULT_LINE_INTERVAL;
        int stopY = START_Y + (mYNum-1)*DEFAULT_LINE_INTERVAL;

        Log.d(TAG, "drawX:");
        Log.d(TAG, "startX   startY   stopX   stopY");
        for (int i=0; i<mXNum; i++) {
            boolean dot = false;
            boolean thick = false;

            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);
            paint.setPathEffect(null);

            if (i != 0) {
                if (i%10 != 0 && i%5 == 0) {
                    Log.d(TAG, "i: " + i);
                    dot = true;
                }

                if (i%10 == 0) {
                    thick = true;
                }
            }

            if (dot) {
                paint.setStrokeWidth(3);
                paint.setPathEffect(pathEffect);
            }

            if (thick) {
                paint.setStrokeWidth(4);
                paint.setColor(Color.RED);
            }

//            Log.d(TAG, startX + (i*DEFAULT_LINE_INTERVAL)+ "   " + startY + "   " + stopX + (i*DEFAULT_LINE_INTERVAL) + "   " + stopY);
            Log.d(TAG, String.format("%d   %d   %d   %d", startX + (i*DEFAULT_LINE_INTERVAL), startY, stopX + (i*DEFAULT_LINE_INTERVAL), stopY));
            canvas.drawLine(startX + (i*DEFAULT_LINE_INTERVAL), startY, stopX + (i*DEFAULT_LINE_INTERVAL), stopY, paint);
        }
    }

    private void drawY(Canvas canvas) {
        Paint paint = new Paint();

        int startX = START_X;
        int startY = START_Y;
//        int stopX = mWidth_X - 30;
        int stopX = START_X + (mXNum - 1)*DEFAULT_LINE_INTERVAL;
        int stopY = START_Y;
        int i;
        int count = 0;

        Log.d(TAG, "drawY:");
        Log.d(TAG, "startX   startY   stopX   stopY");
        for (i=mYNum-1; i>=0; i--) {
            boolean dot = false;
            boolean thick = false;

            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);

            if (count != 0 && count%10 == 0) {
                thick = true;
            }

            if (thick) {
                paint.setStrokeWidth(4);
                paint.setColor(Color.BLUE);
            }

//            Log.d(TAG, startX + "   " + startY + (i*DEFAULT_LINE_INTERVAL) + "   " + stopX + "   " + stopY + (i*DEFAULT_LINE_INTERVAL));
            Log.d(TAG, String.format("%d   %d   %d   %d", startX, startY + (i*DEFAULT_LINE_INTERVAL), stopX, stopY + (i*DEFAULT_LINE_INTERVAL)));
            canvas.drawLine(startX, startY + (i*DEFAULT_LINE_INTERVAL), stopX, stopY + (i*DEFAULT_LINE_INTERVAL), paint);

            count++;
        }
    }
}
