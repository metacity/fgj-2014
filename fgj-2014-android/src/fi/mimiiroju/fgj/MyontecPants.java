package fi.mimiiroju.fgj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import com.badlogic.gdx.Gdx;

public class MyontecPants {
	
	// Handler's Message values for "what" field; measurement value is "arg1" field
	public static final int LEFT_QUADRICEPS = 1;
	public static final int RIGHT_QUADRICEPS = 2;
	public static final int LEFT_HAMSTRINGS = 3;
	public static final int RIGHT_HAMSTRINGS = 4;
	public static final int JUMP = 5;
	
	private static final String TAG = "MyontecPants";

	private final BluetoothDevice mBtDevice;
	private final Handler mOutputHandler;
	private final ExecutorService mReaderPool;
	private final ExecutorService mSenderPool;

	private BluetoothSocket mBtSocket;
	private DataInputStream mInStream;
	private DataOutputStream mOutStream;

	public MyontecPants(BluetoothDevice device, Handler outputHandler) {
		mBtDevice = device;
		mOutputHandler = outputHandler;
		mReaderPool = Executors.newSingleThreadExecutor();
		mSenderPool = Executors.newSingleThreadExecutor();
	}

	public void connect(boolean startMeasurement) throws IOException {
		mBtSocket = mBtDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
		mBtSocket.connect();
		
		mInStream = new DataInputStream(mBtSocket.getInputStream());
		mOutStream = new DataOutputStream(mBtSocket.getOutputStream());
		
		Gdx.app.log(TAG, "Connected!");
		
		if (startMeasurement) {
			startMeasurement();
		}
	}

	public void disconnect() throws IOException {
		Gdx.app.log(TAG, "Disconnecting Myontec Pants...");
		sendCommand("omn\r", true);
		
		// ..and terminate connection
		if (mOutStream != null) mOutStream.close();
		if (mInStream != null) mInStream.close();
		if (mBtSocket != null) mBtSocket.close();
	}

	public void startMeasurement() throws IOException {
		// Send "start" command
		sendCommand("om4\r", false);

		// Instantiate reader thread
		mReaderPool.execute(new ReaderTask());
		Gdx.app.log(TAG, "Measurement started...");
	}
	
	private void sendCommand(final String command, boolean waitForCompletion) {
		Future<Void> future = mSenderPool.submit(new Callable<Void>() {
			@Override
            public Void call() throws Exception {
				mOutStream.write(command.getBytes("US-ASCII"));
				mOutStream.flush();
				Gdx.app.log(TAG, "Command sent successfully: " + command);
				return null;
            }
		});
		
		// Possibly wait to stop measurement..
		if (waitForCompletion) {
			try {
	            future.get();
            } catch (Exception ex) {
            	Gdx.app.log(TAG, "Failed to wait for send command Future: " + ex.toString());
            }
		}
	}


	private class ReaderTask implements Runnable {
		
		private final int[] prevValues = new int[4];
		private final byte[] samples = new byte[8]; // Each datagroup is 8 bytes

		@Override
		public void run() {
			try {
				// Read and ignore header (303 bytes)
				mInStream.readFully(new byte[303]);
				
				while (true) {
					mInStream.readFully(samples);
					//Gdx.app.log(TAG, Arrays.toString(samples));
					
					// Ignore a "marker" packet
					if (!isMarkerPacket()) {
						handleSamples();
					}
				}
			} catch (IOException ioex) {
				Gdx.app.log(TAG, ioex.toString());
			}
		}
		
		private boolean isMarkerPacket() {
			return (samples[0] == 0x7F && samples[1] >= 0xF0 && samples[1] <= 0xFF);
		}
		
		private void handleSamples() {
			int ch1Value = prevValues[0] = ((samples[1] & 0xAF) << 8) + (samples[0] & 0xFF);
			mOutputHandler.obtainMessage(LEFT_QUADRICEPS, ch1Value, 0).sendToTarget();
			
			int ch2Value  = prevValues[1] = ((samples[3] & 0xFF) << 8) + (samples[2] & 0xFF);
			mOutputHandler.obtainMessage(RIGHT_QUADRICEPS, ch2Value, 0).sendToTarget();
			
			int ch3Value = prevValues[2] = ((samples[5] & 0xFF) << 8) + (samples[4] & 0xFF);
			mOutputHandler.obtainMessage(LEFT_HAMSTRINGS, ch3Value, 0).sendToTarget();
			
			int ch4Value = prevValues[3] = ((samples[7] & 0xFF) << 8) + (samples[6] & 0xFF);
			mOutputHandler.obtainMessage(RIGHT_HAMSTRINGS, ch4Value, 0).sendToTarget();
			
			if (prevValues[0] > 85 && prevValues[1] > 85) {  // Etureidet vaan
				mOutputHandler.obtainMessage(JUMP, prevValues[0] + prevValues[1], 0).sendToTarget();
			}
		}
	}
}
