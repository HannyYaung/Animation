package com.hanny.animation;

import android.animation.ObjectAnimator;
import android.graphics.Path;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        sportsView = findViewById(R.id.SV);

        findViewById(R.id.btDraw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(sportsView, "progress", 0, 65);
//                objectAnimator.setInterpolator(new CycleInterpolator(0.5f));
//                objectAnimator.setDuration(1500);
//                objectAnimator.start();
//                ivLacher.animate().translationX(500).setDuration(500);


                ObjectAnimator animator = ObjectAnimator.ofFloat(ivLacher2, "translationX", 800);
                animator.setDuration(500);
                animator.setInterpolator(pathInterpolator);
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
