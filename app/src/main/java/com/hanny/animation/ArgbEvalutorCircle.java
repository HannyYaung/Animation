package com.hanny.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ArgbEvalutorCircle extends View {
    private int color;
    private Paint paint;

    public ArgbEvalutorCircle(Context context) {
        this(context, null);
    }

    public ArgbEvalutorCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArgbEvalutorCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(400, 400, 400, paint);
    }
}
