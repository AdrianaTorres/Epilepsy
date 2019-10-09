package fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileManager {
	private static String comment;
	public FileManager(){
		String path=System.getProperty("user.dir");		
		String configLog=path+"\\config log.txt";
		System.out.println(configLog);
		File conf= new File(configLog);
		if(!conf.isFile()) {
			try {
				conf.createNewFile();
				conf = new File(path+"\\reports");
				conf.mkdir();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("not possible to create config File or folder...");
			}
		}
	}
	
	public static void writeUserConfig(String name, String surname, int age, int weight,char gender) {
		File manager= new File(System.getProperty("user.dir")+"\\config log.txt");
		PrintWriter data = null;
		try {
			data = new PrintWriter(manager);
			data.println(name);
			data.println(surname);
			data.println(age);
			data.println(weight);
			data.println(gender);
			data.close();
		} catch (Exception e) {
			System.out.println("could not write user config data");
			e.printStackTrace();
		}finally {
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not close properly the file");
				e.printStackTrace();
			}
		}
	}
	public boolean isConfigured() {
		String path=System.getProperty("user.dir");		
		String configLog=path+"\\config log.txt";
		System.out.println(configLog);
		File conf= new File(configLog);
		return conf.isFile();
	}
	public static String readUserConfig() {
		File manager= new File(System.getProperty("user.dir")+"\\config log.txt");
		String record;
		BufferedReader data=null;
		try {
			data= new BufferedReader(new InputStreamReader(new FileInputStream(manager)));
			record="";
			String readLine="";
			
			while((readLine=data.readLine())!= null) {
				record=record+readLine+" ";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			record="";
		}finally {
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not read user config");
				e.printStackTrace();
			}
		}
		return record;
	}
	public void writeData(List<Double>[]ecg, List<Double>[]eeg, String comments) {
		int counter=1;
		File manager = new File(System.getProperty("user.dir")+"\\reports\\report_"+counter);
		while(manager.isFile()) {
			counter++;
		}
		manager= new File(System.getProperty("user.dir")+"\\reports\\report_"+counter);
		PrintWriter data =null;
		try {
			manager.createNewFile();
			data= new PrintWriter(manager);
			Iterator iterator1= eeg[1].iterator();
			Iterator iterator3= ecg[1].iterator();
			
			data.println("EEG DATA");
			for (Iterator iterator = eeg[0].iterator(); iterator.hasNext();) {
				data.print(iterator.next());
				data.print(" ");
				data.print(iterator1.next());
				data.println();
			}
			data.println("ECG DATA");
			for (Iterator iterator2 = ecg[0].iterator(); iterator2.hasNext();) {
				data.print(iterator2.next());
				data.print(" ");
				data.print(iterator3.next());
				data.println();
			}
			data.println("COMMENTS");
			data.println(comments);
		}catch(Exception e) {
			System.out.println("could not create report file");
			e.printStackTrace();
		}finally {
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not close report file");
				e.printStackTrace();
			}
			
		}
	}
	public static List[] readData(String path) {
		ArrayList<Double> time1 = new ArrayList <Double>();
		ArrayList<Double> time2 = new ArrayList <Double>();
		ArrayList<Double> ecg = new ArrayList <Double>();
		ArrayList<Double> eeg = new ArrayList <Double>();
		
		File manager = new File(path);
		BufferedReader data= null;
		try {
			data = new BufferedReader(new InputStreamReader(new FileInputStream(manager)));
			String inputRead;
			boolean phaseOneComplete=false;
			boolean comments=false;
			System.out.println(data.readLine());
			while((inputRead=data.readLine())!=null) {
				
				if(inputRead.equals("COMMENTS")) {
					comments=true;
				}
				
				char[] temp=inputRead.toCharArray();
				double time=0;
				double input=0;
				
				if(inputRead.equals("ECG DATA")) {
					phaseOneComplete=true;
					continue;
				}else if(!comments){
					String helper="";
					for (int i = 0; i < temp.length; i++) {
						if(temp[i]!=' ') {
							helper=helper+temp[i];
						}else {
							time=Double.parseDouble(helper);
							helper="";
						}
						if(i==temp.length-1) {
							input=Double.parseDouble(helper);
						}
					}
				}
				if(comments) {
					comment=inputRead;
				}
				if(phaseOneComplete) {
					time2.add(time);
					ecg.add(input);
				}else {
					time1.add(time);
					eeg.add(input);
				}
			}
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not close reader");
				e.printStackTrace();
			}
			return new List[] {time1,ecg,time2,eeg};
		} catch (Exception e) {
			System.out.println("could not read report");
			e.printStackTrace();
			return null;
		}
		
	}
	public static String getComments() {
		return comment;
	}
}
