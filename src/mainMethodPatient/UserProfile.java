package mainMethodPatient;

import java.util.List;
import bitalinoManager.BitalinoManager;
import fileManager.FileManager;

/*this class only exists to facilitate the existence of the bitalino and the personal data all mashed into a blob that 
 * can be tossed around without having to pass 200 arguments at the same time...
 * Future Rodri, this serves the purpose of simplifying the GUI do NOT repent of this later on, this is actually good.
 * Sincerely: past Rodri*/
public class UserProfile {
	private BitalinoManager m;
	
	private String name;
	private String surname;
	private int age;
	private int weight;
	private char gender;
	private boolean exists;
	public UserProfile() {
		if(FileManager.isConfigured()) {
			m=new BitalinoManager();
			String[]temp=FileManager.readUserConfig();
			System.out.println(temp[2]);
			name=temp[0];
			surname=temp[1];
			age=Integer.parseInt(temp[2]);
			weight=Integer.parseInt(temp[3]);
			gender=temp[4].toCharArray()[0];
			exists=true;
		}else {
			System.out.println("could not create a user profile, config file is damaged or missing");
			exists =false;
		}
	}
	
	public boolean configExists() {
		return exists;
	}
	
	public void createProfile(String[]userconfig) {
		FileManager.writeUserConfig(userconfig[0], userconfig[1], Integer.parseInt(userconfig[2]), Integer.parseInt(userconfig[3]), userconfig[4].toCharArray()[0]);
		this.name=userconfig[0];
		this.surname=userconfig[1];
		this.age=Integer.parseInt(userconfig[2]);
		this.weight=Integer.parseInt(userconfig[3]);
		this.gender=userconfig[4].toCharArray()[0];
		exists=true;
		m=new BitalinoManager();
	}
	
	public void initiateBitalino(String mac) {
		m.connect(mac);
	}
	public boolean bitalinoIsconnected() {
		if(m.isConnected()) {
			return true;
		}else{
			return false;
		}
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getAge() {
		return age;
	}
	public int getWeight() {
		return weight;
	}
	public char getGender() {
		return gender;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public BitalinoManager getBitalinoManager() {
		return this.m;
	}
	
}
