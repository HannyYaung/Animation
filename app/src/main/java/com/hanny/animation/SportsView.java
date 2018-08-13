package com.hanny.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SportsView extends View {

    float progress =0;
    private RectF rectF;
    private Paint paint;

    public SportsView(Context context) {
        this(context, null);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rectF = new RectF(100,300,600,800);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF,135,progress*2.7f,false,paint);
    }
}
