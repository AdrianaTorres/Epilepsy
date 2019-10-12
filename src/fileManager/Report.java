package fileManager;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private List <Double>[] ecgData;
	private List <Double>[] eegData;
	private String comments;
	private String symptoms;
	
	public Report (List<Double>[] ecg,List<Double>[] eeg,String comments) {
		this.ecgData=ecg;
		this.eegData=eeg;
		this.comments=comments;
	}
	
	public List<Double>[] getEcgData() {
		return ecgData;
	}
	public List<Double>[] getEegData() {
		return eegData;
	}
	public String getComments() {
		return comments;
	}
	public void setEcgData(List<Double>[] ecgData) {
		this.ecgData = ecgData;
	}
	public void setEegData(List<Double>[] eegData) {
		this.eegData = eegData;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
