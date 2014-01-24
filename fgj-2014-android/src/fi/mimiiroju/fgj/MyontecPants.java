package fi.mimiiroju.fgj;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;

public class MyontecPants {
	
	private final BluetoothDevice mDevice;
	private final Handler mDataProcessor;

	public MyontecPants(BluetoothDevice device, Handler dataProcessor) {
		mDevice = device;
		mDataProcessor = dataProcessor;
		
		connect();
	}
	
	private void connect() {
		
	}
}
