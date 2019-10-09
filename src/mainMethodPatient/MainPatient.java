package mainMethodPatient;

import fileManager.FileManager;
import guiPatient.ConnectingToBitalino;
import guiPatient.GuiPatient;
import guiPatient.MainScreen;
import guiPatient.UserConfiguration;

public class MainPatient {
	
	public static void main(String[] args) {
		UserProfile up = new UserProfile();
		if(!up.configExists()) {
			
			UserConfiguration uc = new UserConfiguration(up);
			while(!up.configExists()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			MainScreen ms = new MainScreen();
		}else {
			MainScreen ms = new MainScreen();
		}
	}
	public static void ConnectToBitalino() {
		ConnectingToBitalino b= new ConnectingToBitalino();
		//physically connect the bitalino
		/*if(bitalino.isConnected()) {
			b.successfullyConnected();
			GuiPatient g= new GuiPatient(time1, eegInput, time2, ecgInput, name, surname, weight, gender, age)
		}else {
			b.failedToConnect();
		}*/
	}
	public static void loadReport(String path) {
		FileManager.readUserConfig();
		FileManager.readData(path);
		FileManager.getComments();
		//I still have to create the data viewer to see the reports.
	}

}
