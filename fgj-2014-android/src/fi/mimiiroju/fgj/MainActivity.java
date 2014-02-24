package fi.mimiiroju.fgj;

import java.io.IOException;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MainActivity extends AndroidApplication {

	private Handler mDataHandler;
	private MyontecPants mMyontecPants;
	
	private FGJ2014 theGame;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;

		theGame = new FGJ2014();
		initialize(theGame, cfg);

		mDataHandler = new Handler(getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == MyontecPants.JUMP) {
					Gdx.app.log(getClass().getSimpleName(), "JUMP!");
					GameScreen gameScreen = theGame.gameScreen;
					if (gameScreen != null) {
						gameScreen.world.palle.jump(msg.arg1 / 350.0f);
					}
				} else {
					//Gdx.app.log(getClass().getSimpleName(), "Muscle activity: " + msg.arg1 + " \u00B5V");
				}
			}
		};

		BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
		if (btAdapter != null && btAdapter.isEnabled()) {
			Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
			for (BluetoothDevice device : pairedDevices) {
				if (device.getName().startsWith("MbM-")) {
					initMyontecPants(device);
					break;
				}
			}
		} else {
			Toast.makeText(this, getString(R.string.btNotFound), Toast.LENGTH_LONG).show();
		}
	}

	@Override
    protected void onDestroy() {
	    super.onDestroy();
	    if (mMyontecPants != null) {
			try {
				mMyontecPants.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    }

	private void initMyontecPants(BluetoothDevice device) {
		mMyontecPants = new MyontecPants(device, mDataHandler);
		try {
			mMyontecPants.connect(true);
		} catch (IOException ioex) {
			Gdx.app.log(getClass().getSimpleName(), ioex.toString());
		}
	}
}