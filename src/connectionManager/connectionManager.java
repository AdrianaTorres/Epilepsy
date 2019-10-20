package connectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import fileManager.Report;
import mainMethodPatient.UserProfile;
/*Socket socket = new Socket("localhost", 9009);
PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
System.out.println("Connection established... sending text");
printWriter.println("Header File\n\n");
printWriter.println("Tell me, what is it you plan");
printWriter.println("to do with your one wild");
printWriter.println("and precious life?");
printWriter.println("Mary Oliver");
System.out.println("Sending stop command");
printWriter.println("Stop");
releaseResources(printWriter, socket);
System.exit(0);*/
public class connectionManager {
	private String ip;
	private Socket manager;
	private PrintWriter pw;
	private Thread t;
	
	public connectionManager(String ip) {
		this.ip=ip;
		try {
			manager= new Socket(ip,9876);
			pw= new PrintWriter(manager.getOutputStream(),true);
		} catch (Exception e) {
			System.out.println("could not connect to server!");
			manager=null;
			pw=null;
			e.printStackTrace();
		} 
	}
	public void closeConnection() {
		try {
			manager.close();
			pw.close();
			this.killThread();
		} catch (IOException e) {
			System.out.println("could not colose the connection!");
			e.printStackTrace();
		}
	}
	public void sendUserProfile(UserProfile up) {
		this.pw.println(up.getName());
		this.pw.println(up.getSurname());
		this.pw.println(up.getAge());
		this.pw.println(up.getWeight());
	}
	public void sendRealTimeFeed(UserProfile up) {
		this.pw.println("USER REQUESTS MONITORING");
		t = new Thread(new Runnable() {
			public void run() {
				while(true) {
					List <Double> ecg=up.getBitalinoManager().getECGData()[1];
					List <Double> eeg=up.getBitalinoManager().getEEGData()[1];
					List <Double> time1=up.getBitalinoManager().getECGData()[0];
					List <Double> time2=up.getBitalinoManager().getEEGData()[0];
					Iterator iterator_1 = eeg.iterator();
					pw.println("PREPARE TO RECIEVE EEG");
					for (Iterator iterator = time2.iterator(); iterator.hasNext();) {
						pw.println(iterator.next());
						pw.println(iterator_1.next());
					}
					pw.println("PREPARE TO RECIEVE ECG");
					iterator_1 = ecg.iterator();
					for (Iterator iterator = time1.iterator(); iterator.hasNext();) {
						pw.println(iterator.next());
						pw.println(iterator_1.next());
					}
				}
			}
		});
	}
	public void sendReport(Report rp) {
		
	}
	public void sendAlert() {
		this.pw.println("USER REQUESTS ASSISTANCE");
	}
	private void killThread() {
		this.t.interrupt();
	}
}
