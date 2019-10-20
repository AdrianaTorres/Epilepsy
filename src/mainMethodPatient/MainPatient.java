package mainMethodPatient;

import connectionManager.connectionManager;
import fileManager.FileManager;
import guiPatient.ConnectingToBitalino;
import guiPatient.GuiPatient;
import guiPatient.MainScreen;
import guiPatient.PatientViewer;
import guiPatient.UserConfiguration;

public class MainPatient {

	public static void main(String[] args) {
		UserProfile up=new UserProfile();
		if(!up.configExists()) {
			UserConfiguration uc = new UserConfiguration(up);
			while(!up.configExists()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		connectionManager cm = new connectionManager(up.getIP());
		cm.sendUserProfile(up);
		MainScreen ms = new MainScreen(up,cm);
	}
	public static void loadReport(String path,UserProfile up) {
		PatientViewer p= new PatientViewer(up, FileManager.readData(path));
	}
	public static void stopRecording(UserProfile up, String comments) {
		up.getBitalinoManager().stop();
		FileManager.writeData(up.getBitalinoManager().getECGFull(), up.getBitalinoManager().getEEGFull(), comments);
		up.getBitalinoManager().purgeData();

	}
}
