package mainMethodPatient;

import fileManager.FileManager;
import guiPatient.ConnectingToBitalino;
import guiPatient.GuiPatient;
import guiPatient.MainScreen;

public class MainPatient {

	public static void main(String[] args) {
		MainScreen ms= new MainScreen();
	}
	public static void configureUser(String[] data) {
		FileManager.writeUserConfig(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4].toCharArray()[0]);
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
