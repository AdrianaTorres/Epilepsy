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
	public static void ConnectToBitalino(UserProfile up) {
		ConnectingToBitalino b= new ConnectingToBitalino();
		//physically connect the bitalino
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(up.getBitalinoManager().isConnected()) {
			b.successfullyConnected();
			GuiPatient g= new GuiPatient(up);
		}else {
			b.failedToConnect();
		}
	}
	public static void loadReport(String path) {
		FileManager.readUserConfig();
		FileManager.readData(path);
		//I still have to create the data viewer to see the reports.
	}

}
