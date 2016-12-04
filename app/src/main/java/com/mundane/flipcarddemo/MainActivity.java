package com.mundane.flipcarddemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.iv_a)
    ImageView   mIvA;
    @Bind(R.id.iv_b)
    ImageView   mIvB;
    @Bind(R.id.activity_main)
    FrameLayout mActivityMain;
    private ObjectAnimator mAnimatorA1;
    private ObjectAnimator mAnimatorB1;
    private ObjectAnimator mAnimatorA2;
    private ObjectAnimator mAnimatorB2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        //  翻过去
        mAnimatorA1 = ObjectAnimator.ofFloat(mIvA, View.ROTATION_Y, 0, 90).setDuration(500);
        mAnimatorB1 = ObjectAnimator.ofFloat(mIvB, View.ROTATION_Y, 90, 180).setDuration(500);
        mAnimatorA1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mActivityMain.setClickable(false);
            }


            @Override
            public void onAnimationEnd(Animator animation) {
                mActivityMain.setClickable(true);
                mAnimatorB1.start();
            }
        });
        mAnimatorB1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mActivityMain.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mActivityMain.setClickable(true);
            }
        });

        //  翻回来
        mAnimatorA2 = ObjectAnimator.ofFloat(mIvA, View.ROTATION_Y, 90, 0).setDuration(500);
        mAnimatorB2 = ObjectAnimator.ofFloat(mIvB, View.ROTATION_Y, 180, 90).setDuration(500);
        mAnimatorB2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mActivityMain.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mActivityMain.setClickable(true);
                mAnimatorA2.start();
            }
        });
        mAnimatorA2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mActivityMain.setClickable(false);
            }


            @Override
            public void onAnimationEnd(Animator animation) {
                mActivityMain.setClickable(true);
            }
        });
    }


    @OnClick({ R.id.activity_main })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main:
                if (mIvB.getRotationY() == 90) {
                    mAnimatorA1.start();
                } else {
                    mAnimatorB2.start();
                }
                break;
        }
    }
}
