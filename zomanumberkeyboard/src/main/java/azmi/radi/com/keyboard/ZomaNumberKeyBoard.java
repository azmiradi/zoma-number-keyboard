package azmi.radi.com.keyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

public class ZomaNumberKeyBoard extends ConstraintLayout {
    @Dimension
    private int keyWidth = 0;
    @Dimension
    private int keyHeight = 0;
    @Dimension
    private int keyPadding = 0;
    @ColorRes
    private int numberKeyBackground = 0;
    @ColorRes
    private int numberKeyBackgroundRipple = 0;
    @ColorRes
    private int keyBoardBackground = 0;
    @DrawableRes
    private int numberKeyShapeDrawable = 0;
    @ColorRes
    private int numberKeyTextColor = 0;
    @Dimension
    private int numberKeyTextSize = 0;
    @Dimension
   private int keyMarginTop=0,keyMarginLeft=0,keyMarginRight=0,keyMarginBottom=0,keyMargin=0;
    private final static int DEFAULT_KEY_WIDTH_DP = -1;
    private final static int DEFAULT_KEY_HEIGHT_DP = -1;
    private final static int DEFAULT_KEY_PADDING_DP = 0;
    private final static int DEFAULT_KEY_TEXT_SIZE_SP = 20;
    private final static int DEFAULT_KEY_MARGIN = 0;
    LayoutParams  params;
    public  final static int CUSTOM_SHAPE=R.drawable.custom_shape;
    public  final static int NORMAL_SHAPE=R.drawable.normal_shape;
    ConstraintLayout keyBoardBackGround;
    ArrayList<Button> numericKeys;

    public ZomaNumberKeyBoard(@NonNull Context context) {
        super(context);
        inflateView(null);
    }

    public ZomaNumberKeyBoard(@NonNull Context context,
                              @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateView(attrs);
    }

    public ZomaNumberKeyBoard(@NonNull Context context, @Nullable
            AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(attrs);
    }

    public ZomaNumberKeyBoard(@NonNull Context context,
                              @Nullable AttributeSet attrs,
                              int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateView(attrs);
    }


    public void setOnListener(ZomaNumberKeyBoardListener zomaNumberKeyBoardListener) {
        setupListeners(zomaNumberKeyBoardListener);
    }

    public void setKeyWidth(int px) {
        if (px == DEFAULT_KEY_WIDTH_DP) return;
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key=numericKeys.get(i);
            key.getLayoutParams().width = px;
        }
        requestLayout();
    }

    /**
     * Sets key height in px.
     */
    public void setKeyHeight(int px) {
        if (px == DEFAULT_KEY_HEIGHT_DP) return;
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key=numericKeys.get(i);
            key.getLayoutParams().height = px;
        }
        requestLayout();
    }


    public void setKeyMargin(int top,int left,int right,int bottom) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            params=new LayoutParams(key.getLayoutParams());
            params.setMargins(left,top,right,bottom);
            key.setLayoutParams(params);
         }
    }



    public void setKeyBoardBackGround(@ColorRes int color){
        keyBoardBackGround.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }
    public void setKeyPadding(int spd) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setPadding(spd, spd, spd, spd);
        }
    }

     public void setNumberKeyTextColor(@ColorRes int color) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setTextColor(ContextCompat.getColor(getContext(), color));
        }
    }

    public void setNumberKeyTextSize(@Dimension int size) {
        if (size==DEFAULT_KEY_TEXT_SIZE_SP)return;
        size=size/8;
        for (int i = 0; i < numericKeys.size(); i++) {
             Button key = numericKeys.get(i);
             key.setTextSize(convertSpToPixels(size));
         }
    }



    public void setNumberKeyBackgroundColors(@ColorRes int colorPressed,
                                             @ColorRes int backGroundColor,
                                             @DrawableRes int shapeBackGround) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            setColorsBackGround(key,ContextCompat.getColor(getContext(), colorPressed)
                    ,ContextCompat.getColor(getContext(), backGroundColor),shapeBackGround);
        }
    }


    public void setNumberKeyTypeface(Typeface typeface) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setTypeface(typeface);
        }
    }

    private void inflateView(AttributeSet attrs) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.ZomaNumberKeyboard,
                0, 0);
        try {
            keyWidth = typedArray.getLayoutDimension(R.styleable.ZomaNumberKeyboard_numberkeyboard_keyWidth, DEFAULT_KEY_WIDTH_DP);
            keyHeight = typedArray.getLayoutDimension(R.styleable.ZomaNumberKeyboard_numberkeyboard_keyHeight, DEFAULT_KEY_HEIGHT_DP);
            // Get key padding
            keyPadding = typedArray.getDimensionPixelSize(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_keyPadding,
                    dpToPx(DEFAULT_KEY_PADDING_DP));
            // Get number key text color
            numberKeyTextColor = typedArray.getResourceId(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_numberKeyTextColor,
                    R.color.black);
            numberKeyTextSize =  typedArray.getDimensionPixelSize(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_keyTextSize,
                    DEFAULT_KEY_TEXT_SIZE_SP);
            //

            numberKeyBackground = typedArray.getResourceId(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_numberKeyBackground,
                    R.color.teal_200);
            //
            numberKeyBackgroundRipple = typedArray.getResourceId(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_numberKeyRippleColor,
                    R.color.gray);
            //
            keyBoardBackground = typedArray.getResourceId(
                    R.styleable.ZomaNumberKeyboard_keyboard_background,
                    R.color.white);
            numberKeyShapeDrawable = typedArray.getResourceId(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_numberKeyShapeDrawable,
                    R.drawable.custom_shape);

            keyMargin =typedArray.getDimensionPixelSize(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_keyMargin,
                    dpToPx(DEFAULT_KEY_MARGIN));

        } finally {
            typedArray.recycle();
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        View view = View.inflate(getContext(), R.layout.zomanumberkeyboard_layout, this);
        keyBoardBackGround=view.findViewById(R.id.constrint);
        // Get numeric keys
        numericKeys = new ArrayList<>(12);
        numericKeys.add(view.findViewById(R.id.key_0));
        numericKeys.add(view.findViewById(R.id.key_1));
        numericKeys.add(view.findViewById(R.id.key_2));
        numericKeys.add(view.findViewById(R.id.key_3));
        numericKeys.add(view.findViewById(R.id.key_4));
        numericKeys.add(view.findViewById(R.id.key_5));
        numericKeys.add(view.findViewById(R.id.key_6));
        numericKeys.add(view.findViewById(R.id.key_7));
        numericKeys.add(view.findViewById(R.id.key_8));
        numericKeys.add(view.findViewById(R.id.key_9));
        numericKeys.add(view.findViewById(R.id.clear));
        numericKeys.add(view.findViewById(R.id.key_comma));


        setStyles();
    }

    private void setupListeners(ZomaNumberKeyBoardListener zomaNumberKeyBoardListener) {
        // Set number callbacks
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            int val = i;
            // Set clear key callbacks
            if (val == 10) {
                key.setOnClickListener(v -> zomaNumberKeyBoardListener.onClearClicked());
                key.setOnLongClickListener(v ->
                        {
                            zomaNumberKeyBoardListener.onLongClearClicked();
                            return false;
                        }
                );
            } else if (val == 11)
                key.setOnClickListener(v -> zomaNumberKeyBoardListener.onCommaClicked());
            else
                key.setOnClickListener(v -> zomaNumberKeyBoardListener.onNumberClicked(val));
        }
    }

    private int dpToPx(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) px, getContext().getResources().getDisplayMetrics());
    }
    public  float convertSpToPixels(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getContext().getResources().getDisplayMetrics());
    }
    private void setStyles() {
        setKeyWidth(keyWidth);
        setKeyHeight(keyHeight);
        setKeyPadding(keyPadding);
        setNumberKeyTextColor(numberKeyTextColor);
        setNumberKeyTextSize(numberKeyTextSize);
        setKeyBoardBackGround(keyBoardBackground);
        setKeyMargin(keyMargin,keyMargin,keyMargin,keyMargin);
         setNumberKeyBackgroundColors(numberKeyBackgroundRipple,numberKeyBackground,numberKeyShapeDrawable);
    }


    private void setColorsBackGround(Button myButton,int colorPressed,int backGroundColor,int shapeBackGround) {
        if (android.os.Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.LOLLIPOP) {
            myButton.setBackgroundResource(shapeBackGround);
         }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
             ColorStateList colorStateListRipple = new ColorStateList(
                    new int[][]{{0}},
                    new int[]{colorPressed} // ripple color
            );

            RippleDrawable rippleDrawable = (RippleDrawable) myButton.getBackground();
            rippleDrawable.setColor(colorStateListRipple);
            myButton.setBackground(rippleDrawable); // applying the ripple color
        }

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed}, // when pressed
                        new int[]{android.R.attr.state_enabled}, // normal state color
                        new int[]{} // normal state color
                },
                new int[]{
                        colorPressed, // when pressed
                        backGroundColor, // normal state color
                        backGroundColor // normal state color
                }
        );

        ViewCompat.setBackgroundTintList(myButton, colorStateList);
    }

}
