package muliteitem.itheima.com.mulitrecycler.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import muliteitem.itheima.com.mulitrecycler.view.activity.MainActivity;

/**
 * @作者 : 杨玉安
 * @日期 : 2017/1/11 0011 10:03
 *
 */
public class SimpleViewPagerIndicator extends LinearLayout {
    //平常显示的颜色
    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    //选中后显示的颜色
    private static final int COLOR_TEXT_CLICK = 0xFFff621c;
    //TOP下面线条的颜色
    private static final int COLOR_INDICATOR_COLOR = Color.RED;
    //传过来的Title数组
    private String[] mTitles;
    //数量
    private int mTabCount;

    private int mIndicatorColor = COLOR_INDICATOR_COLOR;
    //x移动的值
    private float mTranslationX;
    //
    private Paint mPaint = new Paint();

    private int mTabWidth;

    private ViewPager mViewPager;

    private int oldPosition = 0;
    //接口的全局变量
    private OnPageChangeListener onPageChangeListener;

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
        MainActivity activity = new MainActivity();
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(mIndicatorColor);
        //设置空心线宽
        mPaint.setStrokeWidth(9.0F);
    }

    //根据数组的个数设置显示的宽度
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTabWidth = w / mTabCount;
    }



    public void setTitles(String[] titles) {
        mTitles = titles;
        mTabCount = titles.length;
        generateTitleView();
    }


//    public void setIndicatorColor(int indicatorColor) {
//        this.mIndicatorColor = indicatorColor;
//    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        //随着x的偏移量画线
        canvas.translate(mTranslationX, getHeight() - 2);
        canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
        canvas.restore();
    }





    //ViewPager的自定义监听
    //注意：使用了SimpleViewPagerIndicator之后，
    // 需要使用indicator.setOnPageChangeListener（...）监听页面的切换，
    // 不要再使用viewpager .setOnPageChangeListener（...）设置了。
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener)
    {
        this.onPageChangeListener = onPageChangeListener;
    }

    public interface OnPageChangeListener
    {
        public void onPageSelected(int position);
    }


    /**
     * 选中当前页 。此方法一定要在setTitles()，setViewPager()之后调用！
     *
     * @param position
     */
    public void setCurrentItem(int position)
    {
        oldPosition = position;
        generateTitleView();
        getChildAt(position).performClick();
    }


    public void setViewPager(ViewPager viewPager) {
        if (viewPager instanceof ViewPager) {
            this.mViewPager = viewPager;
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    View v = getChildAt(position);
                    if (v instanceof TextView) {
                        TextView tv = (TextView) v;
                        tv.setTextColor(COLOR_TEXT_CLICK);
                        TextView oldTv = (TextView) getChildAt(oldPosition);
                        oldTv.setTextColor(COLOR_TEXT_NORMAL);

                        oldPosition = position;

                    }
                    if (onPageChangeListener != null) {
                        onPageChangeListener.onPageSelected(position);
                    }
                }

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    scroll(position, positionOffset);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            // setCurrentItem(0);
        }
    }
    //这个偏移量是从ViewPager那里监听切换时回传过来的数据
    public void scroll(int position, float offset) {
        mTranslationX = getWidth() / mTabCount * (position + offset);
        invalidate();
    }

    //切换
    private void generateTitleView() {
        if (getChildCount() > 0)
            this.removeAllViews();
        int count = mTitles.length;

        setWeightSum(count);
        for (int i = 0; i < count; i++) {
            TextView tv = new TextView(getContext());
            LayoutParams lp = new LayoutParams(0,
                    LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            if(i == 0)
            {
                tv.setTextColor(COLOR_TEXT_CLICK);
            }
            tv.setGravity(Gravity.CENTER);
            tv.setText(mTitles[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setLayoutParams(lp);
            final int finalI = i;
            //Title的点击
            final int finalI1 = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mViewPager != null)
                    {
                        mViewPager.setCurrentItem(finalI1);
                    }
                    ((TextView) v).setTextColor(COLOR_TEXT_CLICK);
                }
            });
            addView(tv);
        }
    }

}
