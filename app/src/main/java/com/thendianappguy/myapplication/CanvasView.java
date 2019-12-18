package com.thendianappguy.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {

    public int widths;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPaths;
    private float mX, mY;
    private float mCurX = 0f;
    private float mCurY = 0f;
    private static final float TOLERANCE = 5;
    private Paint mPaint;
    Context context;


    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        mPaths = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPaths,mPaint);
    }

    private void startTouch(float x, float y){
        mPaths.moveTo(x,y);
        mX = x;
        mY = y;
    }

    private void moveTouch(float x, float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y- mY);
        if(dx >= TOLERANCE || dy>= TOLERANCE){
            mPaths.quadTo(mX,mY,(x+mX)/2,(y+mY)/2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas(){
        mPaths.reset();
        invalidate();
    }

    private void upTouch(){
        mPaths.lineTo(mX,mY);

        // draw a dot on click
        if (mX == mCurX && mY == mCurY) {
            mPaths.lineTo(mCurX, mCurY + 2);
            mPaths.lineTo(mCurX + 1, mCurY + 2);
            mPaths.lineTo(mCurX + 1, mCurY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startTouch(x,y);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                moveTouch(x,y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;

        }


        return true;
    }
}
