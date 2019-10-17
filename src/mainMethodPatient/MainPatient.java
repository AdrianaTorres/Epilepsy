package mainMethodPatient;

import fileManager.FileManager;
import guiPatient.ConnectingToBitalino;
import guiPatient.GuiPatient;
import guiPatient.MainScreen;
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
			MainScreen ms = new MainScreen(up);
		}else {
			MainScreen ms = new MainScreen(up);
		}
	}
	public static void loadReport(String path) {
		FileManager.readUserConfig();
		FileManager.readData(path);
		//I still have to create the data viewer to see the reports.
	}
	public static void stopRecording(UserProfile up, String comments) {
		up.getBitalinoManager().stop();
		FileManager.writeData(up.getBitalinoManager().getECGData(), up.getBitalinoManager().getEEGData(), comments);
		
	}

}
