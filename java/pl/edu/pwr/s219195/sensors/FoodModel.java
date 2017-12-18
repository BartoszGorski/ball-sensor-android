package pl.edu.pwr.s219195.sensors;


import java.util.Random;

public class FoodModel extends BallModel {
    FoodModel(int aXball, int aYball, int aRadius) {
        super(aXball, aYball, aRadius);
    }

    FoodModel(Painter mPainterFood, int aMaxRadius, int aMinRadius) {
        super(0, 0, 0);

        int maxX = mPainterFood.getWidth() - aMaxRadius - 1;
        int maxY = mPainterFood.getHeight() - aMaxRadius - 1;
        int minX = aMaxRadius + 1;
        int minY = aMaxRadius + 1;

        Random randomGenerator = new Random();
        mRadius = randomGenerator.nextInt(aMaxRadius - aMinRadius + 1) + aMinRadius;
        mXball = randomGenerator.nextInt(maxX - minX + 1) + minX;
        mYball = randomGenerator.nextInt(maxY - minY + 1) + minY;
    }


    public boolean isEaten(Painter mPainterFood, PlayerModel aPlayerModel) {
        if (circlesColliding(mXball, mYball, mRadius, aPlayerModel.mXball, aPlayerModel.mYball, aPlayerModel.mRadius)) {
            mPainterFood.invalidate();
            return true;
        }
        mPainterFood.invalidate();
        return false;
    }

    boolean circlesColliding(float x1, float y1, int radius1, float x2, float y2, int radius2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        int radii = radius1 + radius2;
        if ((dx * dx) + (dy * dy) < radii * radii) {
            return true;
        } else {
            return false;
        }
    }
}
