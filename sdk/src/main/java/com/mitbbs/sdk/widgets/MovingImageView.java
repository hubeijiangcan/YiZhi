package com.mitbbs.sdk.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.mitbbs.sdk.R;

/**
 * daa:2017/12/27.
 *
 * @author:jc 自定义可以背景滚动的ImageView
 */
public class MovingImageView extends AppCompatImageView {
    private float canvasWidth, canvasHeight;
    private float imageWidth, imageHeight;
    private float offsetWidth, offsetHeight;
    /**
     * 移动类型
     */
    private int movementType;

    /**
     * 限定最大比值
     * canvasHeight/drawableHeight 或者 canvasWidth/drawableWidth
     */
    private float maxRelativeSize;
    /**
     * 最小相对偏移值，图片最起码可以位移图*0.2的距离
     */
    private float minRelativeOffset;
    private int mSpeed;
    private long startDelay;
    private int mRepetitions;
    /**
     * load完毕后是否移动
     */
    private boolean loadOnCreate;

    private MovingViewAnimator mAnimator;

    public MovingImageView(Context context) {
        this(context, null);
    }

    public MovingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.MovingImageView, defStyleAttr, 0);

        try {
            maxRelativeSize = a.getFloat(R.styleable.MovingImageView_miv_max_relative_size, 3.0f);
            minRelativeOffset = a.getFloat(R.styleable.MovingImageView_miv_min_relative_offset,
                    0.2f);
            mSpeed = a.getInt(R.styleable.MovingImageView_miv_speed, 50);
            mRepetitions = a.getInt(R.styleable.MovingImageView_miv_repetitions, -1);
            startDelay = a.getInt(R.styleable.MovingImageView_miv_start_delay, 0);
            loadOnCreate = a.getBoolean(R.styleable.MovingImageView_miv_load_on_create, true);
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        super.setScaleType(ScaleType.MATRIX);
        mAnimator = new MovingViewAnimator(this);
    }

    /**
     * 更新canvas size
     *
     * @param w    new width.
     * @param h    new height.
     * @param oldw old width.
     * @param oldh old height.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = (float) w - (float) (getPaddingLeft() + getPaddingRight());
        canvasHeight = (float) h - (float) (getPaddingTop() + getPaddingBottom());
        updateAll();
    }

    private void updateAll() {
        if (getDrawable() != null) {
            updateImageSize();
            updateOffsets();
            updateAnimatorValues();
        }
    }

    /**
     * 更新图片Size
     */
    private void updateImageSize() {
        imageWidth = getDrawable().getIntrinsicWidth();
        imageHeight = getDrawable().getIntrinsicHeight();
    }

    /**
     * 更新偏移量，确定动画范围
     */
    private void updateOffsets() {
        float minSizeX = imageWidth * minRelativeOffset;
        float minSizeY = imageHeight * minRelativeOffset;
        offsetWidth = (imageWidth - canvasWidth - minSizeX) > 0 ? imageWidth - canvasWidth : 0;
        offsetHeight = (imageHeight - canvasHeight - minSizeY) > 0 ? imageHeight - canvasHeight : 0;
    }

    /**
     * 更新动画基本数据
     */
    private void updateAnimatorValues() {
        if (canvasWidth == 0 || canvasHeight == 0) {
            return;
        }
        float scale = calculateTypeAndScale();
        if (scale == 0) {
            return;
        }

        float w = (imageWidth * scale) - canvasWidth;
        float h = (imageHeight * scale) - canvasWidth;

        mAnimator.updateValues(movementType, w, h);
        mAnimator.setStartDelay(startDelay);
        mAnimator.setSpeed(mSpeed);
        mAnimator.setRepetition(mRepetitions);

        if (loadOnCreate) {
            startMoving();
        }
    }



    /**
     * 设置最佳的运动类型
     * 计算缩放比例
     *
     * @return image scale.
     */
    private float calculateTypeAndScale() {
        movementType = MovingViewAnimator.AUTO_MOVE;
        float scale = 1f;
        float scaleByImage = Math.max(imageWidth / canvasWidth, imageHeight / canvasHeight);
        Matrix matrix = new Matrix();
        //图片太小，无法动画，需要放大
        if (offsetWidth == 0 && offsetHeight == 0) {
            //画布宽度/图片宽度
            float sW = canvasWidth / imageWidth;
            //画布高度/图片高度
            float sH = canvasHeight / imageHeight;

            if (sW > sH) {
                //限定最大缩放值
                scale = Math.min(sW, maxRelativeSize);
                matrix.setTranslate((canvasWidth - imageWidth * scale) / 2f, 0);
                //垂直移动
                movementType = MovingViewAnimator.VERTICAL_MOVE;
            } else if (sH > sW) {
                scale = Math.min(sH, maxRelativeSize);
                matrix.setTranslate(0, (canvasHeight - imageHeight * scale) / 2);
                movementType = MovingViewAnimator.HORIZONTAL_MOVE;
            } else {
                scale = Math.max(sW, maxRelativeSize);
                movementType = (scale == sW) ? MovingViewAnimator.NONE_MOVE :
                        MovingViewAnimator.DIAGONAL_MOVE;
            }
        } else if (offsetWidth == 0) {
            //宽度太小，无法执行水平动画，放大宽度
            scale = canvasWidth / imageWidth;
            movementType = MovingViewAnimator.VERTICAL_MOVE;
        } else if (offsetHeight == 0) {
            //高度太小，无法执行垂直动画，放大高度
            scale = canvasHeight / imageHeight;
            movementType = MovingViewAnimator.HORIZONTAL_MOVE;
        } else if (scaleByImage > maxRelativeSize) {
            scale = maxRelativeSize / scaleByImage;
            if (imageWidth * scale < canvasWidth || imageHeight * scale < canvasHeight) {
                scale = Math.max(canvasWidth / imageWidth, canvasHeight / imageHeight);
            }
        }
        matrix.preScale(scale, scale);
        setImageMatrix(matrix);
        return scale;
    }

    /**
     * 开始移动
     * 默认不停的移动
     */
    public void startMoving() {
        starMoving(-1);
    }

    public void starMoving(int repetition) {
        mAnimator.setRepetition(-1);
        mAnimator.start();
    }
    /**
     * 恢复移动
     */
    public void resumeMoving(){
        mAnimator.resume();
    }
    /**
     * 暂停移动
     */
    public void pauseMoving() {
        mAnimator.pause();
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
//        禁止设置ScaleType
//        super.setScaleType(scaleType);
    }

    /**
     * 停止移动
     */
    public void stopMoving() {
        mAnimator.stop();
    }
    /**
     * 获取当前状态
     *
     * @return
     */
    public boolean isLoadOnCreate() {
        return loadOnCreate;
    }

    public void setLoadOnCreate(boolean loadOnCreate) {
        this.loadOnCreate = loadOnCreate;
    }
    /**
     * 获取animator
     *
     * @return
     */
    public MovingViewAnimator getMovingAnimator() {
        return mAnimator;
    }

    public float getMaxRelativeSize() {
        return maxRelativeSize;
    }

    public void setMaxRelativeSize(float max) {
        maxRelativeSize = max;
        updateAnimatorValues();
    }

    public float getMinRelativeOffset() {
        return minRelativeOffset;
    }

    public void setMinRelativeOffset(float min) {
        minRelativeOffset = min;
        updateAnimatorValues();
    }
    public MovingViewAnimator.MovingState getMovingState() {
        return mAnimator.getMovingState();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        updateAll();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        updateAll();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        updateAll();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        updateAll();
    }
}
