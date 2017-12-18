package pl.edu.pwr.s219195.sensors;

public class BallModel
{
    protected float mXball = 200.0f;
    protected float mYball = 200.0f;
    protected int mRadius = 20;
    protected float mVelocityX = 0.0f;
    protected float mVelocityY = 0.0f;

    BallModel(float aXball, float aYball, int aRadius)
    {
        mXball = aXball;
        mYball = aYball;
        mRadius = aRadius;
    }

    float getXball()
    {
        return mXball;
    }

    float getYball()
    {
        return mYball;
    }

    float getRadius()
    {
        return mRadius;
    }
}
