package com.rnwater.shape.group;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.rnwater.shape.R;
public class ConstraintLayoutShape extends ConstraintLayout {
    private boolean openSelector;
    //自定背景边框Drawable
    private GradientDrawable gradientDrawable;
    //按下时的Drawable
    private GradientDrawable selectorDrawable;
    //填充色
    private int solidColor = 0;
    //边框色
    private int strokeColor = 0;
    //按下填充色
    private int solidTouchColor = 0;
    //按下边框色
    private int strokeTouchColor = 0;
    //边框宽度
    private int strokeWidth = 0;
    //渐变的color
    private int beginColor=-1;
    private int centerColor=-1;
    private int endColor=-1;
    private String gradient;
    //四个角的弧度
    private float radius;
    private float topLeftRadius;
    private float topRightRadius;
    private float bottomLeftRadius;
    private float bottomRightRadius;
    //边框虚线的宽度
    float dashWidth = 0;
    //边框虚线的间隙
    float dashGap = 0;
    //字体色
    public ConstraintLayoutShape(Context context) {
        this(context,null);
    }

    public ConstraintLayoutShape(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ConstraintLayoutShape(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
        initLayoutBack();
    }

    private void initLayoutBack() {
        //默认背景
        gradientDrawable = getNeedDrawable(new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
                        bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius},
                solidColor, strokeWidth, strokeColor, dashWidth, dashGap);
        //如果设置了选中时的背景
        if (openSelector) {
            selectorDrawable = getNeedDrawable(new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
                            bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius},
                    solidTouchColor, strokeWidth, strokeTouchColor, dashWidth, dashGap);
            //动态生成Selector
            StateListDrawable stateListDrawable = new StateListDrawable();
            int[][] states = new int[2][];
            states[0] = new int[]{android.R.attr.state_selected, android.R.attr.state_selected};
            states[1] = new int[]{};
            //是否按下
            int pressed = android.R.attr.state_selected;
            stateListDrawable.addState(new int[]{pressed}, selectorDrawable);
            stateListDrawable.addState(new int[]{}, gradientDrawable);
            setBackground(stateListDrawable);
        } else {
            setBackground(gradientDrawable);
        }
    }
    /**
     * 初始化参数
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.shapeView, 0, 0);

        openSelector = ta.getBoolean(R.styleable.shapeView_shape_openSelector, false);

        solidColor = ta.getInteger(R.styleable.shapeView_shape_solidColor, 0x00000000);
        strokeColor = ta.getInteger(R.styleable.shapeView_shape_strokeColor, 0x00000000);

        solidTouchColor = ta.getInteger(R.styleable.shapeView_shape_solidTouchColor, 0x00000000);
        strokeTouchColor = ta.getInteger(R.styleable.shapeView_shape_strokeTouchColor, 0x00000000);
        strokeWidth = (int) ta.getDimension(R.styleable.shapeView_shape_strokeWidth, 0);

        //四个角单独设置会覆盖radius设置
        radius = ta.getDimension(R.styleable.shapeView_shape_radius, 0);
        topLeftRadius = ta.getDimension(R.styleable.shapeView_shape_topLeftRadius, radius);
        topRightRadius = ta.getDimension(R.styleable.shapeView_shape_topRightRadius, radius);
        bottomLeftRadius = ta.getDimension(R.styleable.shapeView_shape_bottomLeftRadius, radius);
        bottomRightRadius = ta.getDimension(R.styleable.shapeView_shape_bottomRightRadius, radius);

        dashGap = ta.getDimension(R.styleable.shapeView_shape_dashGap, 0);
        dashWidth = ta.getDimension(R.styleable.shapeView_shape_dashWidth, 0);

        beginColor = ta.getInteger(R.styleable.shapeView_shape_beginColor, 0x00000000);
        centerColor = ta.getInteger(R.styleable.shapeView_shape_centerColor, 0x00000000);
        endColor = ta.getInteger(R.styleable.shapeView_shape_endColor, 0x00000000);
        gradient = ta.getString(R.styleable.shapeView_shape_gradient);
        ta.recycle();
    }

    /**
     * @param radius      四个角的半径
     * @param colors      渐变的颜色
     * @param strokeWidth 边框宽度
     * @param strokeColor 边框颜色
     * @return
     */
    public static GradientDrawable getNeedDrawable(float[] radius, int[] colors, int strokeWidth, int strokeColor) {
        GradientDrawable drawable;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable = new GradientDrawable();
            drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            drawable.setColors(colors);
        } else {
            drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        }
        drawable.setCornerRadii(radius);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        return drawable;
    }

    /**
     * @param radius      四个角的半径
     * @param bgColor     背景颜色
     * @param strokeWidth 边框宽度
     * @param strokeColor 边框颜色
     * @return
     */
    public static GradientDrawable getNeedDrawable(float[] radius, int bgColor, int strokeWidth, int strokeColor) {
        GradientDrawable needDrawable = getNeedDrawable(radius, bgColor, strokeWidth, strokeColor, 0, 0);
        return needDrawable;
    }

    /**
     * @param radius      四个角的半径
     * @param bgColor     背景颜色
     * @param strokeWidth 边框宽度
     * @param strokeColor 边框颜色
     * @param dashWidth   虚线边框宽度
     * @param dashGap     虚线边框间隙
     * @return
     */
    public static GradientDrawable getNeedDrawable(float[] radius, int bgColor, int strokeWidth, int strokeColor, float dashWidth, float dashGap) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadii(radius);
        drawable.setStroke(strokeWidth, strokeColor, dashWidth, dashGap);
        drawable.setColor(bgColor);
        return drawable;
    }
}
