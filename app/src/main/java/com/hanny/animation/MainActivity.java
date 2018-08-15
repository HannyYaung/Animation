package com.hanny.animation;

import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SportsView sportsView;
    private ImageView ivLacher;
    private View ivLacher2;
    private PathInterpolator pathInterpolator;
    private ArgbEvalutorCircle argbCircle;
    private View pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sportsView = findViewById(R.id.SV);
//        argbCircle = findViewById(R.id.ArgbCircle);
//        pv = findViewById(R.id.pv);

        findViewById(R.id.btDraw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(sportsView, "progress", 0, 65);
//                objectAnimator.setInterpolator(new CycleInterpolator(0.5f));
//                objectAnimator.setDuration(1500);
//                objectAnimator.start();
//                ivLacher.animate().translationX(500).setDuration(500);


//                ObjectAnimator animator = ObjectAnimator.ofFloat(ivLacher2, "translationX", 800);
////                animator.setDuration(500);
////                animator.setInterpolator(pathInterpolator);
////                animator.start();

                //ArgbEvalautor
//                ObjectAnimator animator = ObjectAnimator.ofInt(argbCircle,"color",0xffff0000,0xff00ff00);
//                animator.setEvaluator(new ArgbEvaluator());
//                animator.setDuration(3000);
//                animator.start();

//                ObjectAnimator animator = ObjectAnimator.ofArgb(argbCircle,"color",0xffff0000,0xff00ff00);
//                animator.setDuration(3000);
//                animator.start();

//                ObjectAnimator animator = ObjectAnimator.ofInt(argbCircle, "color", 0xffff0000,0xff00ff00);
//
//                animator.setEvaluator(new HsvEvaluator());
//                animator.start();

                //自定义PointFEvaluator
//                ObjectAnimator animator = ObjectAnimator.ofObject(pv, "position", new PointFEvaluator(), new PointF(0, 0), new PointF(1, 1));
//                animator.start();

                Keyframe keyframe1 = Keyframe.ofFloat(0, 0);

                Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);

                Keyframe keyframe3 = Keyframe.ofFloat(1, 80);

                PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);

                ObjectAnimator animator  = ObjectAnimator.ofPropertyValuesHolder(sportsView,holder);
                animator.start();

            }

        });

        Path path = new Path();
        path.lineTo(0.25f, 0.25f);
        path.moveTo(0.25f, 1.5f);
        path.lineTo(1, 1);
        pathInterpolator = new PathInterpolator(path);

        ivLacher = findViewById(R.id.ivLacher);
        ivLacher2 = findViewById(R.id.ivLacher2);

    }


}
