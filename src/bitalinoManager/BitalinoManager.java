package bitalinoManager;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BitalinoManager implements BitalinoModel, Runnable{
	private boolean connected;
	private BITalino bitalino = null;
	private List<Double> ECGtime;
	private List<Double> EEGtime;
	private List<Double> ECGdata;
	private List<Double> EEGdata;
	private double time;

	public static Frame[] frame;

	public void connect(String Mac) {
		connected=false;
		try {
			bitalino= new BITalino();
			String macAddress = Mac;
			int SamplingRate = 10;
			System.out.println("connecting...");
			System.out.println(macAddress);
			bitalino.open(macAddress, SamplingRate);
			System.out.println("connected!");
			int[] channelsToAcquire = {2, 4};
			bitalino.start(channelsToAcquire);
			connected =true;
			ECGtime=new ArrayList<>();
			EEGtime=new ArrayList<>();
			ECGdata=new ArrayList<>();
			EEGdata=new ArrayList<>();
		}catch (Exception e) {
			Logger.getLogger(BitalinoManager.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("could not connect to the bitalino, please try again");
		} catch (Throwable e) {
			Logger.getLogger(BitalinoManager.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public List<Double>[] getECGData() {
		return new List[]{this.ECGtime, this.ECGdata};
	}

	@Override
	public List<Double>[] getEEGData() {
		return new List[]{this.EEGtime, this.ECGdata};
	}

	@Override
	public void run() {
		try {
			this.time=System.currentTimeMillis();
			while(true) {
				frame = bitalino.read(10);
				for (int i = 0; i < frame.length; i++) {
					System.out.println(frame[i].analog[2]);
					System.out.println(frame[i].analog[4]);
					this.ECGtime.add(System.currentTimeMillis()-time);
					this.EEGtime.add(System.currentTimeMillis()-time);
					this.EEGdata.add((double) frame[i].analog[4]);
					this.ECGdata.add((double) frame[i].analog[2]);
				}
			}
		}catch(BITalinoException e) {
			System.out.println("FATAL ERROR:");
			System.out.println("failed to read from bitalino");
			e.printStackTrace();
			System.exit(0);
		}

	}
	public void stop(){
		try {
			this.bitalino.stop();
		} catch (BITalinoException e) {
			System.out.println("failed to close properly bitalino");
			e.printStackTrace();
		}
	}
	public void start() {
		try {
			this.bitalino.start(new int[] {2,4});
		} catch (Throwable e) {
			System.out.println("could not inititate channels...");
			e.printStackTrace();
		}
	}
	public void purgeData() {
		ECGtime=new ArrayList<>();
		EEGtime=new ArrayList<>();
		ECGdata=new ArrayList<>();
		EEGdata=new ArrayList<>();
	}

}
