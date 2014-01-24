package fi.mimiiroju.fgj;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;

public class MyontecPants {
	
	private final BluetoothDevice mDevice;
	private final Handler mOutputHandler;

	public MyontecPants(BluetoothDevice device, Handler outputHandler) {
		mDevice = device;
		mOutputHandler = outputHandler;
		
		connect();
	}
	
	private void connect() {
		
	}
}
