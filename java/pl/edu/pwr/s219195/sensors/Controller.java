package pl.edu.pwr.s219195.sensors;

import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;

class Controller implements Runnable {
    private Handler mHandler;
    private float mX = 0.0f, mY = 0.0f;
    private Painter mPainterPlayer, mPainterFood;
    private PlayerModel mPlayerModel;
    private FoodModel mFoodModel;
    private int mScore = -1;
    private TextView mTextView;

    public Controller(Handler aH, TextView aTextView, Painter aPainterPlayer, Painter aPainterFood) {
        mHandler = aH;
        mTextView = aTextView;
        mPainterPlayer = aPainterPlayer;
        mPainterFood = aPainterFood;
        mPainterFood.invalidate();
        mPlayerModel = new PlayerModel(21, 21, 20);
        mFoodModel = new FoodModel(21, 21, 5);
        mPainterPlayer.setModel(mPlayerModel);
        mPainterPlayer.setColor(Color.BLACK);
        mPainterFood.setModel(mFoodModel);
        mPainterFood.setColor(Color.GREEN);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(8);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mPlayerModel != null) {
                            mPlayerModel.setPosition(mX, mY, mPainterPlayer);
                            if (mPlayerModel.starve()) {
                                mPlayerModel = null;
                            } else {
                                if (mFoodModel.isEaten(mPainterFood, mPlayerModel)) {
                                    mScore++;
                                    mTextView.setText("Score: " + mScore);
                                    mPlayerModel.grow(5);
                                    mFoodModel = new FoodModel(mPainterFood, 50, 35);
                                    mPainterFood.setModel(mFoodModel);
                                }
                            }
                        } else {
                            mPainterFood = null;
                            mPainterPlayer = null;
                            mFoodModel = null;
                        }
                    }
                });

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void setPhoneRotation(float x, float y) {
        mX = x;
        mY = y;
    }
}