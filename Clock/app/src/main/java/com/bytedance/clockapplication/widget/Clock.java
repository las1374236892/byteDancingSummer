package com.bytedance.clockapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Locale;
import java.util.logging.LogRecord;

public class Clock extends View {

    private final static String TAG = Clock.class.getSimpleName();

    private static final int FULL_ANGLE = 360;

    private static final int CUSTOM_ALPHA = 140;
    private static final int FULL_ALPHA = 255;

    private static final int DEFAULT_PRIMARY_COLOR = Color.WHITE;
    private static final int DEFAULT_SECONDARY_COLOR = Color.LTGRAY;

    private static final float DEFAULT_DEGREE_STROKE_WIDTH = 0.010f;

    public final static int AM = 0;

    private static final int RIGHT_ANGLE = 90;

    private int mWidth, mCenterX, mCenterY, mRadius;



    /**
     * properties
     */
    private int centerInnerColor;
    private int centerOuterColor;

    private int secondsNeedleColor;
    private int hoursNeedleColor;
    private int minutesNeedleColor;

    private int degreesColor;

    private int hoursValuesColor;

    private int numbersColor;

    private boolean mShowAnalog = true;

    public Clock(Context context) {
        super(context);
        init(context, null);
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int size;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heightWithoutPadding = height - getPaddingTop() - getPaddingBottom();

        if (widthWithoutPadding > heightWithoutPadding) {
            size = heightWithoutPadding;
        } else {
            size = widthWithoutPadding;
        }

        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size + getPaddingTop() + getPaddingBottom());
    }

    private void init(Context context, AttributeSet attrs) {

        this.centerInnerColor = Color.LTGRAY;
        this.centerOuterColor = DEFAULT_PRIMARY_COLOR;

        this.secondsNeedleColor = DEFAULT_SECONDARY_COLOR;
        this.hoursNeedleColor = DEFAULT_PRIMARY_COLOR;
        this.minutesNeedleColor = DEFAULT_PRIMARY_COLOR;

        this.degreesColor = DEFAULT_PRIMARY_COLOR;

        this.hoursValuesColor = DEFAULT_PRIMARY_COLOR;

        numbersColor = Color.WHITE;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        mWidth = getHeight() > getWidth() ? getWidth() : getHeight();

        int halfWidth = mWidth / 2;
        mCenterX = halfWidth;
        mCenterY = halfWidth;
        mRadius = halfWidth;

        if (mShowAnalog) {
            drawDegrees(canvas);
            drawHoursValues(canvas);
            drawNeedles(canvas);
            drawCenter(canvas);
        } else {
            drawNumbers(canvas);
        }

    }

    private void drawDegrees(Canvas canvas) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(mWidth * DEFAULT_DEGREE_STROKE_WIDTH);
        paint.setColor(degreesColor);

        int rPadded = mCenterX - (int) (mWidth * 0.01f);
        int rEnd = mCenterX - (int) (mWidth * 0.05f);
        //int rPadded = mCenterX;
        //int rEnd = mCenterX;

        for (int i = 0; i < FULL_ANGLE; i += 6 /* Step */) {

            if ((i % RIGHT_ANGLE) != 0 && (i % 15) != 0)
                paint.setAlpha(CUSTOM_ALPHA);
            else {
                paint.setAlpha(FULL_ALPHA);
            }

            int startX = (int) (mCenterX + rPadded * Math.cos(Math.toRadians(i)));
            int startY = (int) (mCenterX - rPadded * Math.sin(Math.toRadians(i)));

            int stopX = (int) (mCenterX + rEnd * Math.cos(Math.toRadians(i)));
            int stopY = (int) (mCenterX - rEnd * Math.sin(Math.toRadians(i)));

            canvas.drawLine(startX, startY, stopX, stopY, paint);

        }
    }

    /**
     * @param canvas
     */
    private void drawNumbers(Canvas canvas) {

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(mWidth * 0.2f);
        textPaint.setColor(numbersColor);
        textPaint.setColor(numbersColor);
        textPaint.setAntiAlias(true);

        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int amPm = calendar.get(Calendar.AM_PM);

        String time = String.format("%s:%s:%s%s",
                String.format(Locale.getDefault(), "%02d", hour),
                String.format(Locale.getDefault(), "%02d", minute),
                String.format(Locale.getDefault(), "%02d", second),
                amPm == AM ? "AM" : "PM");

        SpannableStringBuilder spannableString = new SpannableStringBuilder(time);
        spannableString.setSpan(new RelativeSizeSpan(0.3f), spannableString.toString().length() - 2, spannableString.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // se superscript percent

        StaticLayout layout = new StaticLayout(spannableString, textPaint, canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1, 1, true);
        canvas.translate(mCenterX - layout.getWidth() / 2f, mCenterY - layout.getHeight() / 2f);
        layout.draw(canvas);
    }

    /**
     * Draw Hour Text Values, such as 1 2 3 ...
     *
     * @param canvas
     */
    private void drawHoursValues(Canvas canvas) {
        // Default Color:
        // - hoursValuesColor
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(hoursValuesColor);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);

        int distance = mCenterX - (int)(mWidth * 0.1f);
        String[] hours = {"3","2","1","12","11","10","9","8","7","6","5","4"};
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        for(int i=0;i < FULL_ANGLE;i += 30){
            int numberIndex = i/30;

            int X = (int)(mCenterX + distance * Math.cos(Math.toRadians(i)));
            int Y = (int)(mCenterX - distance * Math.sin(Math.toRadians(i)));


            canvas.drawText(hours[numberIndex], X, Y - top/2 - bottom/2,textPaint);
        }

    }

    /**
     * Draw hours, minutes needles
     * Draw progress that indicates hours needle disposition.
     *
     * @param canvas
     */
    private void drawNeedles(final Canvas canvas) {
        // Default Color:
        // - secondsNeedleColor
        Paint paintSecond = new Paint();
        int secondDistance = mCenterX - (int)(mWidth * 0.1f);
        paintSecond.setStyle(Paint.Style.FILL);
        paintSecond.setColor(hoursNeedleColor);
        paintSecond.setStrokeWidth(10f);

        // - hoursNeedleColor
        Paint paintHour = new Paint();
        int hourDistance = mCenterX - (int)(mWidth * 0.3f);
        paintHour.setStyle(Paint.Style.FILL);
        paintHour.setColor(hoursNeedleColor);
        paintHour.setStrokeWidth(20f);

        // - minutesNeedleColor
        Paint paintMinute = new Paint();
        int minuteDistance = mCenterX - (int)(mWidth * 0.2f);
        paintMinute.setStyle(Paint.Style.FILL);
        paintMinute.setColor(minutesNeedleColor);
        paintMinute.setStrokeWidth(15f);



        Calendar calendar = Calendar.getInstance();

        float hour = calendar.get(Calendar.HOUR);
        float minute = calendar.get(Calendar.MINUTE);
        float second = calendar.get(Calendar.SECOND);
        System.out.println(hour + ":" + minute + ":" + second);
        int secondX = (int)(mCenterX + secondDistance * Math.sin(Math.toRadians(second*6)));
        int secondY = (int)(mCenterX - secondDistance * Math.cos(Math.toRadians(second*6)));

        float munDegrees = (float) (minute*6 + second*6/60);
        float minuteX = (float) (mCenterX + minuteDistance * Math.sin(Math.toRadians((munDegrees))));
        float minuteY = (float) (mCenterX - minuteDistance * Math.cos(Math.toRadians((munDegrees))));

        float hourDegrees = (float) (hour*30 + munDegrees/60);
        float hourX = (float) (mCenterX + hourDistance * Math.sin(Math.toRadians((hourDegrees))));
        float hourY = (float) (mCenterX - hourDistance * Math.cos(Math.toRadians((hourDegrees))));

        canvas.drawLine(hourX, hourY, mRadius, mRadius,paintHour);
        canvas.drawLine(minuteX, minuteY , mRadius, mRadius,paintMinute);
        canvas.drawLine(secondX, secondY, mRadius, mRadius,paintSecond);

    }

    /**
     * Draw Center Dot
     *
     * @param canvas
     */
    private void drawCenter(Canvas canvas) {
        // Default Color:
        // - centerInnerColor
        Paint centerInner = new Paint();
        centerInner.setColor(centerInnerColor);
        centerInner.setStrokeWidth(20f);
        centerInner.setStyle(Paint.Style.FILL);
        canvas.drawPoint((float) (mCenterX),(float) (mCenterY),centerInner);
        // - centerOuterColor
        Paint centerOuter = new Paint();
        centerOuter.setColor(centerOuterColor);
        centerOuter.setStrokeWidth(30f);
        centerOuter.setStyle(Paint.Style.FILL);
        canvas.drawPoint((float) (mCenterX),(float) (mCenterY),centerOuter);

    }

    public void setShowAnalog(boolean showAnalog) {
        mShowAnalog = showAnalog;
        invalidate();
    }

    public boolean isShowAnalog() {
        return mShowAnalog;
    }



}