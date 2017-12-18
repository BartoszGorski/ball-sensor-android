package pl.edu.pwr.s219195.sensors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Painter extends View {

    private BallModel mBallModel;
    private Paint mPaintBall;
    private int mWidth;
    private int mHeight;

    public Painter(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintBall = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBall.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mBallModel.getXball(), mBallModel.getYball(), mBallModel.getRadius(), mPaintBall);
        canvas.drawLine(0, 0, mWidth, 0, mPaintBall);
        canvas.drawLine(mWidth, 0, mWidth, mHeight, mPaintBall);
        canvas.drawLine(mWidth, mHeight, 0, mHeight, mPaintBall);
        canvas.drawLine(0, mHeight, 0, 0, mPaintBall);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void setModel(BallModel aBallModel) {
        mBallModel = aBallModel;
    }

    public void setColor(int aColor) {
        mPaintBall.setColor(aColor);
    }
}
