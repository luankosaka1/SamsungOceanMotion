package android.kosaka.br.samsungmotion;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.motion.Smotion;
import com.samsung.android.sdk.motion.SmotionPedometer;


public class MyActivity extends Activity implements SmotionPedometer.ChangeListener {
    TextView valuePassos;
    TextView valueCalorias;
    Smotion motion;
    SmotionPedometer motionPedometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        valuePassos = (TextView) findViewById(R.id.value_passos);
        valueCalorias = (TextView) findViewById(R.id.value_calorias);

        motion = new Smotion();

        try {
            motion.initialize(this);

            motionPedometer = new SmotionPedometer(Looper.getMainLooper(), motion);
            motionPedometer.start(this);
        } catch (SsdkUnsupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChanged(SmotionPedometer.Info info) {
        valueCalorias.setText(Double.toString(info.getCalorie()) + " kcal");
        valuePassos.setText(Double.toString(info.getSpeed()) + " passos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
