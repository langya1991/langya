package jack.demo.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import jack.demo.R;
import jack.demo.utils.DensityUtils;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class SinaSportLayout extends LinearLayout {
    private View mProgressLayout;
    private RatioProgressBar mProgress;
    private TextView mTvLeft, mTvRight;

    private OnSinaSportListener mListener;

    public SinaSportLayout(Context context) {
        this(context, null);
    }

    public SinaSportLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SinaSportLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ImageView imgLeft = new ImageView(context);
        imgLeft.setImageResource(R.drawable.sina_selector_left_btn);
        int padding = DensityUtils.px2dip(context, 30);
        imgLeft.setPadding(padding, padding, padding, padding);
        addView(imgLeft, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        mProgressLayout = View.inflate(context, R.layout.progress_layout, null);
        mProgress = (RatioProgressBar) mProgressLayout.findViewById(R.id.progress);
        mTvLeft = (TextView) mProgressLayout.findViewById(R.id.tv_left_text);
        mTvRight = (TextView) mProgressLayout.findViewById(R.id.tv_right_text);
        addView(mProgressLayout, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));

        ImageView imgRight = new ImageView(context);
        imgRight.setPadding(padding, padding, padding, padding);
        imgRight.setImageResource(R.drawable.sina_selector_right_btn);
        addView(imgRight, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        imgLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启按钮缩放动画
                btnAnim(v);
                if (mListener != null) {
                    mListener.onLeftClick(mTvLeft);
                }
            }
        });
        imgRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnim(v);
                if (mListener != null) {
                    mListener.onRightClick(mTvRight);
                }
            }
        });

        mTvLeft.setText(mProgress.getLeftProgressValue() + "");
        mTvRight.setText(mProgress.getRightProgressValue() + "");

    }

    public void setOnSinaSportListener(OnSinaSportListener listener) {
        mListener = listener;
    }

    private void btnAnim(View view) {
        ObjectAnimator animatorX =
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator animatorY =
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.playTogether(animatorX, animatorY);
        set.start();
    }

    public void incrementLeftProgressValue(int value) {
        if (mProgress != null) {
            mProgress.setLeftProgressValue(value);
        }
    }

    public void incrementRightProgressValue(int value) {
        if (mProgress != null) {
            mProgress.setRightProgressValue(value);
        }
    }

    public int getLeftProgressValue() {
        if (mProgress != null) {
            return mProgress.getLeftProgressValue();
        }
        return 0;
    }

    public int getRightProgressValue() {
        if (mProgress != null) {
            return mProgress.getRightProgressValue();
        }
        return 0;
    }

    public interface OnSinaSportListener {
        void onLeftClick(TextView tvLeft);

        void onRightClick(TextView tvRight);
    }
}
