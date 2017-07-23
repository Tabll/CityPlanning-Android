package com.wts.sxgh.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;
import com.wts.sxgh.sxgh.R;

/**
 * Created by wts on 2016/12/16.
 */

public class SlidingMenu extends HorizontalScrollView {

    //未使用自定义属性时调用
    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;
    private int mMenuRightPadding = 300;//单位dp
    private int mMenuWidth;
    private boolean once;//判断是否设置过宽和高
    public static boolean isBeingOpen = false;
    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //设置宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        if(!once){
            mWapper = (LinearLayout)getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth-mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //设置偏移量隐藏以侧滑菜单
    @Override
    protected void onLayout(boolean changed,int l,int t,int r,int b){
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth, 0);
        }//隐藏
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        int action = ev.getAction();
        switch(action){
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();//隐藏在左边的宽度
                if(scrollX >= mMenuWidth / 2){//大于等于菜单宽度的一半
                    this.smoothScrollTo(mMenuWidth, 0);
                }else{
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    //当使用了自定义的属性的时候调用
    public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO 自动生成的构造函数存根
        //获取定义的属性
        TypedArray tpa = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyle, 0);
        int n = tpa.getIndexCount();
        for(int i = 0 ; i < n ; i++){
            int attr = tpa.getIndex(i);
            switch(attr){
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = tpa.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }

        tpa.recycle();//TypedArray使用后一定要释放！！

        //获得屏幕的宽和高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;//给宽度赋值
    }

    public SlidingMenu(Context context) {
        this(context, null);
    }

    //滚动发生时调用
    @Override
    protected void onScrollChanged(int l,int t,int oldl,int oldt){
        super.onScrollChanged(l,t,oldl,oldt);
        RelativeLayout reLayout = (RelativeLayout) findViewById(R.id.l_menu);
        //优化卡顿
        if(l != mMenuWidth) {
            reLayout.setVisibility(View.VISIBLE);
            isBeingOpen = true;
        }else{
            reLayout.setVisibility(View.INVISIBLE);
            isBeingOpen = false;
        }
        //调用属性动画，设置Translation
        float scale = l*1.0f/mMenuWidth;//从1-0
        ViewHelper.setTranslationX(mMenu,mMenuWidth*scale/2);
    }
}
