package com.hanny.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class PointFEvaluator implements TypeEvaluator<PointF> {
    PointF newPointF = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x = startValue.x + (fraction * (endValue.x - startValue.x));
        float y = startValue.y + (fraction * (endValue.y - startValue.y));
        newPointF.set(x, y);
        return newPointF;
    }

}
