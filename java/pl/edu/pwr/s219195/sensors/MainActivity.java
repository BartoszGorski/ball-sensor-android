package pl.edu.pwr.s219195.sensors;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView textView = (TextView) findViewById(R.id.textViewScore);
        Painter painterPlayer = (Painter) findViewById(R.id.painterPlayer);
        Painter painterFood = (Painter) findViewById(R.id.painterFood);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (mSensor != null) {
            mController = new Controller(new Handler(), textView, painterPlayer, painterFood);
            Thread t = new Thread(mController);
            t.start();
        } else {
            textView.setText("Sorry, there are no accelerometers on your device.");
        }
    }

    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];

        mController.setPhoneRotation(x, y);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
