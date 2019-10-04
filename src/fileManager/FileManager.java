package fileManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileManager {
	public FileManager(){
		String path=System.getProperty("user.dir");		
		String configLog=path+"\\config log.txt";
		System.out.println(configLog);
		File conf= new File(configLog);
		if(!conf.isFile()) {
			try {
				conf.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("not possible to create config File");
			}
		}
	}
	public void writeUserConfig(String name, String surname, int age, int weight,char gender) {
		File manager= new File(System.getProperty("user.dir")+"\\config log.txt");
		try {
			PrintWriter data = new PrintWriter(manager);
			data.println(name);
			data.println(surname);
			data.println(age);
			data.println(weight);
			data.println(gender);
			data.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isConfigured() {
		String path=System.getProperty("user.dir");		
		String configLog=path+"\\config log.txt";
		System.out.println(configLog);
		File conf= new File(configLog);
		return conf.isFile();
	}
	public String readUserConfig() {
		File manager= new File(System.getProperty("user.dir")+"\\config log.txt");
		String record;
		try {
			BufferedReader data= new BufferedReader(new InputStreamReader(new FileInputStream(manager)));
			record="";
			String readLine="";
			
			while((readLine=data.readLine())!= null) {
				record=record+readLine+" ";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			record="";
		}
		return record;
	}
}
