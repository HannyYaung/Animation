package com.hanny.animation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SportsView sportsView;
    private ImageView ivLacher;
    private View ivLacher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        sportsView = findViewById(R.id.SV);

        findViewById(R.id.btDraw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(sportsView, "progress", 0, 65);
//                objectAnimator.start();
//                ivLacher.animate().translationX(500).setDuration(500);


                ObjectAnimator animator = ObjectAnimator.ofFloat(ivLacher2,"translationX",500);
                animator.setDuration(1000);
//                animator.setInterpolator(new LinearInterpolator());
                animator.setInterpolator(new AnticipateOvershootInterpolator());
                animator.start();


            }
        });

        ivLacher = findViewById(R.id.ivLacher);
        ivLacher2 = findViewById(R.id.ivLacher2);

    }


}
