package com.example.myanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * created by jump on 2020/5/10
 * describe:
 */
public class RoadNumberDistributedView extends View {
    private Paint paintA, paintB, paintC, paintText;
    private float cornerRadius;
    private float measuredWidth;
    private float mHeight;
    private int percentA=34, percentB=34, percentC=32;

    public RoadNumberDistributedView(Context context) {
        this(context, null);
    }

    public RoadNumberDistributedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoadNumberDistributedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        paintA = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintA.setColor(Color.GREEN);
        paintA.setStyle(Paint.Style.FILL);
        paintB = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintB.setColor(Color.YELLOW);
        paintB.setStyle(Paint.Style.FILL);
        paintC = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintC.setColor(Color.MAGENTA);
        paintC.setStyle(Paint.Style.FILL);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextSize(getResources().getDimensionPixelSize(R.dimen.road_text_size));
        paintText.setColor(Color.BLACK);

        cornerRadius = getResources().getDimensionPixelSize(R.dimen.corner_radius);

        mHeight = getResources().getDimensionPixelSize(R.dimen.road_height);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(widthSize, 40);
        }else {
            setMeasuredDimension(widthSize,heightSize);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measuredWidth = getMeasuredWidth();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0, 0, measuredWidth * percentA / 100, mHeight, cornerRadius, cornerRadius, paintA);
        canvas.drawRect(cornerRadius, 0, measuredWidth * percentA / 100, mHeight, paintA);
        canvas.drawText(percentA + "%", 0, mHeight + 65, paintText);
        canvas.drawRect(measuredWidth * percentA / 100, 0, measuredWidth * (percentA + percentB) / 100, mHeight, paintB);
        float textWidthB = paintText.measureText(percentB + "%");
        canvas.drawText(percentB + "%", measuredWidth * percentA / 100 + measuredWidth * percentB / 100 / 2 - textWidthB / 2, mHeight + 65, paintText);
        canvas.drawRoundRect(measuredWidth * (percentA + percentB) / 100, 0, measuredWidth, mHeight, cornerRadius, cornerRadius, paintC);
        canvas.drawRect(measuredWidth * (percentA + percentB) / 100, 0, measuredWidth * (percentA + percentB) / 100 + cornerRadius, mHeight, paintC);
        float textWidthC = paintText.measureText(percentC + "%");
        canvas.drawText(percentC + "%", measuredWidth - textWidthC, mHeight + 65, paintText);
    }

    public void setRoadData(int a, int b, int c) {
        int total = a + b + c;
        percentA = a * 100 / total;
        percentB = b * 100 / total;
        percentC = c * 100 / total;
        if (percentA + percentB + percentC == 97) {
            percentA++;
            percentB++;
            percentC++;
        } else if (percentA + percentB + percentC == 98) {
            percentA++;
            percentB++;
        } else if (percentA + percentB + percentC == 99) {
            percentA++;
        }

    }
}
