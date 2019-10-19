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
	
	private List<Double> ECGtimeShort;
	private List<Double> EEGtimeShort;
	private List<Double> ECGdataShort;
	private List<Double> EEGdataShort;
	private long time;

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
			int[] channelsToAcquire = {0, 1};
			bitalino.start(channelsToAcquire);
			connected =true;
			ECGtime=new ArrayList<>();
			EEGtime=new ArrayList<>();
			ECGdata=new ArrayList<>();
			EEGdata=new ArrayList<>();
			ECGtimeShort=new ArrayList<>();
			EEGtimeShort=new ArrayList<>();
			ECGdataShort=new ArrayList<>();
			EEGdataShort=new ArrayList<>();
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
		return new List[]{this.ECGtimeShort, this.ECGdataShort};
	}

	@Override
	public List<Double>[] getEEGData() {
			return new List[]{this.EEGtimeShort, this.EEGdataShort};
	}

	@Override
	public void run() {
		try {
			this.time=System.currentTimeMillis();
			while(true) {
				frame = bitalino.read(1);
				for (int i = 0; i < frame.length; i++) {
					if(ECGtimeShort.size()>80) {
						this.ECGdata.add(this.ECGdataShort.remove(0));
						this.ECGtime.add(this.ECGtimeShort.remove(0));
					}
					if(EEGtimeShort.size()>80) {
						double exitVal1=this.EEGdataShort.remove(0);
						this.EEGdata.add(exitVal1);
						exitVal1=this.EEGtimeShort.remove(0);
						this.EEGtime.add(exitVal1);
					}
					double actualTime=(double)(System.currentTimeMillis()-time);
					this.ECGtimeShort.add(actualTime);
					this.EEGtimeShort.add(actualTime);
					//System.out.println(actualTime +"    "+frame[i].analog[0]+"   "+frame[i].analog[1]);
					this.ECGdataShort.add((double) frame[i].analog[0]);
					this.EEGdataShort.add((double) frame[i].analog[1]);
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
			Iterator iterator_1= ECGtimeShort.iterator();
			for (Iterator iterator = ECGdataShort.iterator(); iterator.hasNext();) {
				double data = (double) iterator.next();
				double time = (double) iterator_1.next();
				this.ECGdata.add(data);
				this.ECGtime.add(time);
			}
			iterator_1= EEGtimeShort.iterator();
			for (Iterator iterator = EEGdataShort.iterator(); iterator.hasNext();) {
				double data = (double) iterator.next();
				double time = (double) iterator_1.next();
				this.EEGdata.add(data);
				this.EEGtime.add(time);
			}
			System.out.println(EEGdata.size()+"   "+ECGdata.size());
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
	public List <Double>[] getECGFull(){
		return new List[] {this.ECGtime, this.ECGdata};
	}
	public List <Double>[] getEEGFull(){
		return new List[] {this.EEGtime, this.EEGdata};
	}

}
