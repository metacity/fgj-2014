package fi.mimiiroju.fgj;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	
	private HandlerThread mDataProcessorThread;
	private Handler mDataHandler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new FGJ2014(), cfg);
        
       /* BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter != null && btAdapter.isEnabled()) {
        	Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        	for (BluetoothDevice device : pairedDevices) {
        		if ("MbM-1007".equalsIgnoreCase(device.getName())) {
        			initMyontecPants(device);
        			break;
        		}
        	}
        } else {
        	Toast.makeText(this, getString(R.string.btNotFound), Toast.LENGTH_LONG).show();
        }
    }
    
    private void initMyontecPants(BluetoothDevice device) {
    	mDataProcessorThread = new HandlerThread("DataProcessorThread");
    	mDataProcessorThread.start();
    	//mDataHandler*/
    }
}