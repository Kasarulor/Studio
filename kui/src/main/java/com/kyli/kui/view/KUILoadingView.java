package com.kyli.kui.view;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.kyli.kui.utils.log.LogUtils;

/**
 * @author Kyli   2022年11月10日16:13:06
 * 绘制转圈的加载 视图   依赖{@link android.animation.ValueAnimator} 实现
 */
public class KUILoadingView extends View {

    private static final String TAG = "KUILoadingView";

    /**
     * 设置背景颜色
     */
    private int kBgColor = Color.BLACK;
    /**
     * 结果值动画
     */
    private ValueAnimator kValueAnimator;

    /**
     * 画笔
     */
    private Paint kPaint;


    /**
     * 线条颜色
     */
    private int kLineDefaultColor = Color.WHITE;

    /**
     * 显示线条数
     */
    private int kCount = 8;
    /**
     * 显示弧度数
     */
    private int kRadian = 360 / kCount;


    private int kValueAnimatorProcess = 0;


    private int kRadian2 = 10;

    public KUILoadingView(Context context) {
        super(context);
        init();
    }

    public KUILoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KUILoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        kPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        kPaint.setColor(kLineDefaultColor);
        kPaint.setStrokeWidth(10);
        kPaint.setStyle(Paint.Style.FILL);
        kPaint.setStrokeCap(Paint.Cap.ROUND);
    }


    private void startAnimator() {

        if (kValueAnimator == null) {
            //循环次数
            kValueAnimator = ValueAnimator.ofInt(0, kCount - 1);
            kValueAnimator.setDuration(800);

            kValueAnimator.setRepeatMode(ValueAnimator.RESTART);
            kValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            //允许插值器  0-1之前 -开始->结束  基本上等同于时间点
            kValueAnimator.setInterpolator(new LinearInterpolator());
            //估计值   估值器  根据插值器能估算出传入的值
            //            kValueAnimator.setEvaluator();

            kValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int
                            kValueAnimatorProcess = (int) animation.getAnimatedValue();
                    Log.e(TAG, kValueAnimatorProcess + "");
                    if (KUILoadingView.this.kValueAnimatorProcess != kValueAnimatorProcess) {
                        KUILoadingView.this.kValueAnimatorProcess = kValueAnimatorProcess;
                        postInvalidate();

                    }

                }
            });
            kValueAnimator.start();
        } else {
            if (!kValueAnimator.isStarted()) {
                kValueAnimator.start();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getContext().getResources().getDisplayMetrics()
        ), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getContext().getResources().getDisplayMetrics()
        ));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(kBgColor);
//        kPaint.setColor(color);
        //保存最初的画布 并创建layer

        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), kPaint);

        drawLoadingInCanvas(canvas);
        //然后恢复到最初之上
        canvas.restoreToCount(saveCount);
    }

    int i = 10;

    private void drawLoadingInCanvas(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        i += 10;
        if (i > 360)
            i = 10;

        canvas.rotate(i, width / 2f, height / 2f);

        final int alpha = 255;
        int once = (alpha - 150) / kCount;//基于100以上渐变
        for (int i = 0; i < kCount; i++) {

            if (i == kValueAnimatorProcess) {
                kPaint.setAlpha(alpha);
            } else if (i < kValueAnimatorProcess) {
                int nAlpha = alpha - (kValueAnimatorProcess - i) * once;
                kPaint.setAlpha(nAlpha);
            } else {
                int nAlpha = alpha - (i - kValueAnimatorProcess) * once;
                kPaint.setAlpha(nAlpha);
            }
            canvas.drawLine(width / 2f, 20, width / 2f, height / 2f - 20, kPaint);
            canvas.rotate(kRadian, width / 2f, height / 2f);
        }

    }


    public void start() {
        startAnimator();
    }

    public void stop() {
        if (kValueAnimator != null) {
            //移除所有的更新监听
            kValueAnimator.removeAllUpdateListeners();
            kValueAnimator.cancel();
            kValueAnimator = null;
        }
    }

}
