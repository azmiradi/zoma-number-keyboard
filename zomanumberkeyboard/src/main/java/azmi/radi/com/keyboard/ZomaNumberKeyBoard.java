package azmi.radi.com.keyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class ZomaNumberKeyBoard extends ConstraintLayout {
    @Dimension
    private int keyWidth = 0;
    @Dimension
    private int keyHeight = 0;
    @Dimension
    private int keyPadding = 0;
    @DrawableRes
    private int numberKeyBackground = 0;
    @ColorRes
    private int numberKeyBackgroundTint= 0;
    @ColorRes
    private int numberKeyTextColor = 0;
    @Dimension
    private int numberKeyTextSize = 0;
     private final static int DEFAULT_KEY_WIDTH_DP = 0;
    private final static int DEFAULT_KEY_HEIGHT_DP = 0;
    private final static int DEFAULT_KEY_PADDING_DP = 16;
    private final static int DEFAULT_KEY_TEXT_SIZE_SP = 50;

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

    public void setKeyHeight(int spd) {
        if (spd == DEFAULT_KEY_HEIGHT_DP) return;
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setHeight(spd);
        }
        requestLayout();
    }

    public void setKeyWidth(int spd) {
        if (spd == DEFAULT_KEY_WIDTH_DP) return;
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setWidth(spd);
        }
        requestLayout();
    }

    public void setKeyPadding(int spd) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setPadding(spd, spd, spd, spd);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void setNumberKeyTextColor(@ColorRes int color) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setTextColor(color);
        }
    }

    public void setNumberKeyTextSize(@Dimension int size) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setTextSize(size);
        }
    }
     public void setNumberKeyBackground(@DrawableRes int drawable) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setBackgroundResource(drawable);
        }
    }
     @SuppressLint("ResourceAsColor")
     public void setNumberKeyBackgroundTint(@ColorRes int color) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP && key
                    instanceof AppCompatButton) {
                key.setBackgroundTintList(ColorStateList.valueOf(getContext().getResources()
                        .getColor(color)));
            } else {
                ViewCompat.setBackgroundTintList(key, ColorStateList.valueOf(color));
            }
        }
    }

    public void setNumberKeyTypeface(Typeface typeface) {
        for (int i = 0; i < numericKeys.size(); i++) {
            Button key = numericKeys.get(i);
            key.setTypeface(typeface);
        }
    }

     private void inflateView(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ZomaNumberKeyboard, 0, 0);
        try {
        keyWidth = typedArray.getLayoutDimension(R.styleable.ZomaNumberKeyboard_numberkeyboard_keyWidth, DEFAULT_KEY_WIDTH_DP);
        keyHeight = typedArray.getLayoutDimension(R.styleable.ZomaNumberKeyboard_numberkeyboard_keyHeight, DEFAULT_KEY_HEIGHT_DP);
        // Get key padding
        keyPadding = typedArray.getDimensionPixelSize(
                R.styleable.ZomaNumberKeyboard_numberkeyboard_keyPadding,
                dpToPx());
        // Get number key background
        numberKeyBackground = typedArray.getResourceId(
                R.styleable.ZomaNumberKeyboard_numberkeyboard_numberKeyBackground,
                R.drawable.bu_back);
        // Get number key text color
        numberKeyTextColor = typedArray.getResourceId(
                R.styleable.ZomaNumberKeyboard_numberkeyboard_numberKeyTextColor,
                R.color.black);
        numberKeyTextSize = typedArray.getResourceId(
                R.styleable.ZomaNumberKeyboard_numberkeyboard_keyTextSize,
                DEFAULT_KEY_TEXT_SIZE_SP);
       numberKeyBackgroundTint = typedArray.getResourceId(
                    R.styleable.ZomaNumberKeyboard_numberkeyboard_numberKeyBackgroundTint,
                    R.color.white);

          } finally {
            typedArray.recycle();
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        View view = View.inflate(getContext(), R.layout.zomanumberkeyboard_layout, this);
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
            }
            else if (val == 11)
                key.setOnClickListener(v -> zomaNumberKeyBoardListener.onCommaClicked());
            else
                key.setOnClickListener(v -> zomaNumberKeyBoardListener.onNumberClicked(val));
        }
    }

    private int dpToPx() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) ZomaNumberKeyBoard.DEFAULT_KEY_PADDING_DP, getContext().getResources().getDisplayMetrics());
    }

     private void setStyles() {
        setKeyWidth(keyWidth);
        setKeyHeight(keyHeight);
        setKeyPadding(keyPadding);
        setNumberKeyBackground(numberKeyBackground);
        setNumberKeyTextColor(numberKeyTextColor);
        setNumberKeyTextSize(numberKeyTextSize);
        setNumberKeyBackgroundTint(numberKeyBackgroundTint);
    }
}
