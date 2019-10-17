package connectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
		} catch (IOException e) {
			System.out.println("could not colose the connection!");
			e.printStackTrace();
		}
	}
	public void sendUserProfile() {
		
	}
	public void sendRealTimeFeed(UserProfile up) {
		
	}
	public void sendReport(Report rp) {
		
	}
}
