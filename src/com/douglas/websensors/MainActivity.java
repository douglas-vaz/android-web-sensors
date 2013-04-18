package com.douglas.websensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	  private SensorManager mSensorManager;
	  private Sensor mLight;

	  private Server S = new Server(8080);
	  
	  private TextView text;

	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	    text = (TextView)findViewById(R.id.textViewVealu);
	    
	    try{
	    	S.start();
	    }
	    catch(Exception e)
	    {
	    	Log.e("NanoHTTPD", e.toString());
	    }
	  }

	  @Override
	  public final void onAccuracyChanged(Sensor sensor, int accuracy) {
	    // Do something here if sensor accuracy changes.
	  }

	  @Override
	  public final void onSensorChanged(SensorEvent event) {
	    // The light sensor returns a single value.
	    // Many sensors return 3 values, one for each axis.
	    float lux = event.values[0];
	    String vals[] = {"Light Sensor: "+lux};
	    
	    text.setText("Light Sensor: "+lux);
	    
	    S.setData(vals);
	    // Do something with this sensor value.
	  }

	  @Override
	  protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	  }

	}