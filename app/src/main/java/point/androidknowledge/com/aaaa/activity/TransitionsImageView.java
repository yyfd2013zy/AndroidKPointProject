package point.androidknowledge.com.aaaa.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import point.androidknowledge.com.aaaa.R;

public class TransitionsImageView extends RelativeLayout {
    private String TAG = TransitionsImageView.class.getSimpleName();
    public static final int ANIM_SLIDE_IN_RIGHT = 0x01;//右侧滑入
    public static final int ANIM_SLIDE_IN_LEFT = 0x02;//左侧滑入
    public static final int ANIM_SLIDE_IN_UP = 0x03;//左侧滑入
    public static final int ANIM_SLIDE_IN_DOWN = 0x04;//左侧滑入
    public static final int ANIM_FADE_IN = 0x05;//淡入
    public static final int ANIM_ZOOM_IN = 0x06;//缩放
    public static final int ANIM_ROTATE_IN = 0x07;//旋转进入
    public static final int ANIM_ROTATE_IN_UP_LEFT = 0x08;//左上角
    public static final int ANIM_FLIP_IN_X = 0x09;//翻页
    public static final int ANIM_ROLLIN_IN = 0x10;//x轴翻滚动
    public int mAnimType = 0x00;
    private boolean isInAnimal;//是否处于动画过程中
    private ImageView imageViewFirst, imageViewSecond;
    private Animation animation;
    private Context mContext;

    public TransitionsImageView(Context context) {
        super(context);
        mContext = context;
        initView(context);
    }

    public TransitionsImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
    }

    public TransitionsImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    public TransitionsImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initView(context);
    }

    public void setAnimType(int animType) {
        mAnimType = animType;
        switch (animType) {
            case ANIM_SLIDE_IN_RIGHT:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_right);
                break;
            case ANIM_SLIDE_IN_LEFT:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left);
                break;
            case ANIM_SLIDE_IN_UP:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_up);
                break;
            case ANIM_SLIDE_IN_DOWN:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_down);
                break;
            case ANIM_FADE_IN:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
                break;
            case ANIM_ZOOM_IN:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_in);
                break;
            case ANIM_ROTATE_IN:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.rote_in);
                break;
            case ANIM_ROTATE_IN_UP_LEFT:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.rote_in_up_left);
                break;
            case ANIM_FLIP_IN_X:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.flip_back);
                break;
            case ANIM_ROLLIN_IN:
                animation = AnimationUtils.loadAnimation(mContext, R.anim.rolling_in);
                break;
        }
    }

    public void setImage(int index) {
        if (isInAnimal) {
            Log.d(TAG, "Animation is being executed , Return！");
            return;
        }
        if ((int) imageViewFirst.getTag(R.id.show) == 1) {
            imageViewSecond.setBackgroundResource(index);
            addView(imageViewSecond);
            //开启动画
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mAnimType == ANIM_FLIP_IN_X) {
                        Animation animaFlipFont = AnimationUtils.loadAnimation(mContext, R.anim.flip_font);
                        animaFlipFont.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                removeView(imageViewFirst);
                                isInAnimal = false;
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        imageViewSecond.startAnimation(animaFlipFont);
                    } else {
                        removeView(imageViewFirst);
                        isInAnimal = false;
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            isInAnimal = true;
            imageViewFirst.setTag(R.id.show, 0);
            imageViewSecond.setTag(R.id.show, 1);
            imageViewSecond.startAnimation(animation);

        } else if ((int) imageViewSecond.getTag(R.id.show) == 1) {
            imageViewFirst.setBackgroundResource(index);
            addView(imageViewFirst);

            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mAnimType == ANIM_FLIP_IN_X) {
                        Animation animaFlipFont = AnimationUtils.loadAnimation(mContext, R.anim.flip_font);
                        animaFlipFont.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                removeView(imageViewSecond);
                                isInAnimal = false;
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        imageViewFirst.startAnimation(animaFlipFont);
                    } else {
                        removeView(imageViewSecond);
                        isInAnimal = false;
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            isInAnimal = true;
            imageViewSecond.setTag(R.id.show, 0);
            imageViewFirst.setTag(R.id.show, 1);
            imageViewFirst.startAnimation(animation);
        }

    }


    private void initView(Context context) {
        imageViewFirst = new ImageView(context);
        imageViewFirst.setBackgroundColor(0xFFFFFFFF);
        imageViewFirst.setScaleType(ImageView.ScaleType.CENTER);//居中显示
        imageViewFirst.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));//定义组件

        imageViewSecond = new ImageView(context);
        imageViewSecond.setBackgroundColor(0xFFFFFFFF);
        imageViewSecond.setScaleType(ImageView.ScaleType.CENTER);//居中显示
        imageViewSecond.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));//定义组件

        imageViewFirst.setTag(R.id.show, 1);
        imageViewSecond.setTag(R.id.show, 0);
        addView(imageViewFirst);
    }


}
