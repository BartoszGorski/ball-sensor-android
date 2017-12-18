package pl.edu.pwr.s219195.sensors;


public class PlayerModel extends BallModel {

    private final float ALFA = 0.1f;
    private final float GROW_MULTIPLAY = 0.03f;
    private final int DEAD_BALL_SIZE = 1;
    private final float STARVE_SPEED = 0.02f;
    private final float MAX_BOUNCE_STRENGTH = 0.6f;
    private float mBounceStrength = 0.4f;
    private float mStarveDelay = 0.0f;

    PlayerModel(int aXball, int aYball, int aRadius) {
        super(aXball, aYball, aRadius);
    }

    void setPosition(float aX, float aY, Painter aPainter) {
        mVelocityX -= (aX * ALFA);
        mVelocityY += (aY * ALFA);

        mXball += mVelocityX;
        mYball += mVelocityY;

        if (mXball - mRadius <= 0) {
            mXball = 1 + mRadius;
            mVelocityX *= -mBounceStrength;
        }
        if (mXball + mRadius >= aPainter.getWidth()) {
            mXball = aPainter.getWidth() - 1 - mRadius;
            mVelocityX *= -mBounceStrength;
        }

        if (mYball - mRadius <= 0) {
            mYball = 1 + mRadius;
            mVelocityY *= -mBounceStrength;
        }
        if (mYball + mRadius >= aPainter.getHeight()) {
            mYball = aPainter.getHeight() - 1 - mRadius;
            mVelocityY *= -mBounceStrength;
        }

        aPainter.invalidate();
    }

    public boolean starve() {
        mStarveDelay += STARVE_SPEED;
        if (mStarveDelay >= 1) {
            mRadius--;
            mStarveDelay = 0.0f;
            if (mBounceStrength <= MAX_BOUNCE_STRENGTH) {
                mBounceStrength += GROW_MULTIPLAY;
            }
            if (mRadius <= DEAD_BALL_SIZE) {
                return true;
            }
        }
        return false;
    }

    public void grow(int aGrowValue) {
        mRadius += aGrowValue;
        if (mBounceStrength > 0) {
            mBounceStrength -= aGrowValue * GROW_MULTIPLAY;
        }
    }
}
