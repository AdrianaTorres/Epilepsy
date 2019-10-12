package guiPatient;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import fileManager.Report;
import mainMethodPatient.UserProfile;
import optimizedGraphics.XYPanel;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;


public class PatientViewer {
	private JFrame f =new JFrame();
	private JPanel contentPane;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextArea textArea; 
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private static int sufferedSymptoms;
	
	private double[]timeEEG;
	private double[]timeECG;
	private double[]ECGdata;
	private double[]EEGdata;
	
	private int indexECG;
	private int indexEEG;
	
	private StaticGraph eegGraph;
	private StaticGraph ecgGraph;
	
	public PatientViewer(UserProfile user,Report rep) {

		indexECG=0;
		indexEEG=0;
		timeEEG=new double[rep.getEegData()[0].size()];
		EEGdata=new double[rep.getEegData()[0].size()];
		
		timeECG=new double[rep.getEegData()[1].size()];
		timeECG=new double[rep.getEegData()[1].size()];
		Iterator iterator_1 =rep.getEcgData()[1].iterator();
		int i=0;
		for (Iterator iterator = rep.getEcgData()[0].iterator(); iterator.hasNext();) {
			timeECG[i]=(double)iterator.next();
			ECGdata[i]=(double)iterator_1.next();
			i++;
		}
		i=0;
		iterator_1 =rep.getEegData()[1].iterator();
		for (Iterator iterator = rep.getEegData()[0].iterator(); iterator.hasNext();) {
			timeEEG[i]=(double)iterator.next();
			EEGdata[i]=(double)iterator_1.next();
			i++;
		}
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		f.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.windowBorder);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 2, 0, 0));

		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout());
		panel_6.setBackground(Color.BLACK);
		
		eegGraph=new StaticGraph(chartEEGData("next"),"EEG");
		panel_6.add(eegGraph,BorderLayout.CENTER);
		JButton button_1=new JButton("next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Double>[] display=chartEEGData("previous");
				if(display[0]!=null) {
					panel_6.remove(eegGraph);
					eegGraph=new StaticGraph(display,"EEG");
					panel_6.add(eegGraph);
				}
			}
		});
		JButton button_2=new JButton("previous");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Double>[] display=chartEEGData("next");
				if(display[0]!=null) {
					panel_6.remove(eegGraph);
					eegGraph=new StaticGraph(display,"EEG");
					panel_6.add(eegGraph);
				}
			}
		});
		
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		
		button_1.setFont(new Font("Segoe UI",Font.PLAIN,11));
		button_2.setFont(new Font("Segoe UI",Font.PLAIN,11));
		
		panel_6.add(button_2,BorderLayout.SOUTH);
		panel_6.add(button_1,BorderLayout.SOUTH);
		
		panel_5.add(panel_6);

		panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setLayout(new BorderLayout());
		panel_7.setBackground(Color.BLACK);
		
		StaticGraph ecgGraph=new StaticGraph(chartEEGData("next"), "Ecg");
		
		panel_7.add(ecgGraph,BorderLayout.CENTER);
		
		JButton button_3=new JButton("next");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Double>[] display=chartECGData("next");
				if(display[0]!=null) {
					panel_7.remove(ecgGraph);
					PatientViewer.this.ecgGraph=new StaticGraph(display,"ECG");
					panel_7.add(ecgGraph);
				}
			}
		});
		JButton button_4=new JButton("previous");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Double>[] display=chartECGData("previous");
				if(display[0]!=null) {
					panel_7.remove(ecgGraph);
					PatientViewer.this.ecgGraph=new StaticGraph(display,"ECG");
					panel_7.add(ecgGraph);
				}
			}
		});
		
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.DARK_GRAY);
		
		button_3.setFont(new Font("Segoe UI",Font.PLAIN,11));
		button_4.setFont(new Font("Segoe UI",Font.PLAIN,11));
		
		panel_7.add(button_4,BorderLayout.SOUTH);
		panel_7.add(button_3,BorderLayout.SOUTH);

		panel_5.add(panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_8.setBackground(SystemColor.windowBorder);
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.GRAY);
		panel_10.setLayout(new GridLayout(11,10));

		JLabel label1=new JLabel("Name");
		JLabel label2=new JLabel("Surname");
		JLabel label3=new JLabel("Age");
		JLabel label4=new JLabel("Weight");

		Font ui=new Font("Segoe UI",Font.PLAIN,14);
		label1.setFont(ui);
		label2.setFont(ui);
		label3.setFont(ui);
		label4.setFont(ui);
		text1=new JTextField();
		text2=new JTextField();
		text3=new JTextField();
		text4=new JTextField();
		text1.setBackground(Color.GRAY);
		text2.setBackground(Color.GRAY);
		text3.setBackground(Color.GRAY);
		text4.setBackground(Color.GRAY);
		text1.setBorder(new LineBorder(Color.DARK_GRAY));
		text2.setBorder(new LineBorder(Color.DARK_GRAY));
		text3.setBorder(new LineBorder(Color.DARK_GRAY));
		text4.setBorder(new LineBorder(Color.DARK_GRAY));
		text1.setFont(ui);
		text2.setFont(ui);
		text3.setFont(ui);
		text4.setFont(ui);
		

		JLabel filler=new JLabel();

		panel_10.add(label1);
		panel_10.add(text1);
		panel_10.add(label2);
		panel_10.add(text2);

		panel_10.add(filler);
		panel_10.add(filler);
		panel_10.add(filler);
		panel_10.add(filler);

		panel_10.add(label3);
		panel_10.add(text3);
		panel_10.add(label4);
		panel_10.add(text4);

		panel_8.add(panel_10);
		textArea = new JTextArea();
		textArea.setFont(ui);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textArea.setBackground(new Color(64, 64, 64));
		panel_8.add(textArea);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.desktop);
		JLabel title =new JLabel("Patient Monotorer 3000");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Segoe UI",80,15));
		panel_1.setLayout(new GridLayout(1,3));
		panel_1.add(title,Component.LEFT_ALIGNMENT);
		JLabel devBuild=new JLabel("devBuild alpha 2.0");
		devBuild.setForeground(Color.white);
		devBuild.setFont(new Font("Segoe UI",80,15));
		panel_1.add(devBuild);
		JLabel stats=new JLabel("Telemedicine 2019-2020");
		stats.setForeground(Color.white);
		stats.setFont(new Font("Segoe UI",80,15));
		panel_1.add(stats);
		contentPane.add(panel_1, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.desktop);
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.desktop);
		contentPane.add(panel_3, BorderLayout.WEST);

		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.desktop);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(panel_4, BorderLayout.EAST);

		try {
			BufferedImage nominal;
			if(user.getGender()=='m') {
				nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\NominalMale.jpg"));
			}else {
				nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\NominalFemale.jpg"));
			}
			JLabel picLabel = new JLabel(new ImageIcon(nominal));
			panel_4.add(picLabel);
		} catch (IOException e) {
			System.out.println("failed to import image!");
			e.printStackTrace();
		}

		JPanel panel_9=new JPanel();
		panel_9.setBackground(Color.BLACK);


		panel_4.add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] {1};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{1.0};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.0};
		panel_9.setLayout(gbl_panel_9);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblStatus.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 0;
		panel_9.add(lblStatus, gbc_lblStatus);
		
		JButton stop = new JButton("close");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
			}
		});
		stop.setForeground(Color.BLACK);
		stop.setBackground(Color.GRAY);
		stop.setFont(new Font("Segoe UI",Font.PLAIN,11));
		GridBagConstraints gbc_lblStop = new GridBagConstraints();
		gbc_lblStop.insets = new Insets(0, 0, 5, 0);
		gbc_lblStop.gridx = 0;
		gbc_lblStop.gridy = 2;
		gbc_lblStop.fill = GridBagConstraints.HORIZONTAL;
		panel_9.add(stop, gbc_lblStop);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		GridBagConstraints gbc_lbVS_1 = new GridBagConstraints();
		gbc_lbVS_1.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_1.gridx = 0;
		gbc_lbVS_1.gridy = 1;
		panel_9.add(verticalStrut_1, gbc_lbVS_1);
		GridBagConstraints gbc_lbVS_2 = new GridBagConstraints();
		gbc_lbVS_2.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_2.gridx = 0;
		gbc_lbVS_2.gridy = 3;
		panel_9.add(verticalStrut_2, gbc_lbVS_2);
		GridBagConstraints gbc_lbVS_3 = new GridBagConstraints();
		gbc_lbVS_3.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_3.gridx = 0;
		gbc_lbVS_3.gridy = 5;
		panel_9.add(verticalStrut_3, gbc_lbVS_3);

		
		this.setAge(user.getAge());
		this.setWeight(user.getWeight());
		this.setName(user.getName());
		this.setSurName(user.getSurname());
		f.setVisible(true);
	}
	public void setName(String name) {
		this.text1.setText(name);
		text1.setVisible(false);
		text1.setVisible(true);
	}
	public void setSurName(String surname) {
		this.text2.setText(surname);
		text2.setVisible(false);
		text2.setVisible(true);
	}
	public void setAge(int age) {
		this.text3.setText(""+age);
		text3.setVisible(false);
		text3.setVisible(true);
	}
	public void setWeight(int weight) {
		this.text4.setText(""+weight);
		text4.setVisible(false);
		text4.setVisible(true);
	}
	public void setComment(String comment) {
		this.textArea.setText(comment);
		textArea.setVisible(false);
		textArea.setVisible(true);
	}
	public static void setSymptoms(int s) {
		sufferedSymptoms=s;
	}
	public static int getSymptoms() {
		return sufferedSymptoms;
	}
	private List<Double>[] chartECGData(String action){
		int defaultShift=50;
		double[] shiftedTime=new double[defaultShift];
		double[] shiftedData=new double[defaultShift];
		if(action.equals("next")) {
			if(indexECG+defaultShift<ECGdata.length-1) {
				for (int i = 0; i < defaultShift; i++) {
					shiftedTime[i]=this.timeECG[indexECG+i];
					shiftedData[i]=this.ECGdata[indexECG+i];
				}
				indexECG=indexECG+defaultShift;
			}else {
				shiftedTime=null;
				shiftedData=null;
			}
		}else {
			if(indexECG-defaultShift>=0) {
				for (int i = 0; i < defaultShift; i++) {
					shiftedTime[i]=this.timeECG[indexECG-defaultShift+i];
					shiftedData[i]=this.ECGdata[indexECG-defaultShift+i];
				}
				indexECG=indexECG-defaultShift;
			}else {
				shiftedTime=null;
				shiftedData=null;
			}
		}
		return godLeftMeUnfinished(shiftedTime, shiftedData);
	}
	private List<Double>[] chartEEGData(String action){
		int defaultShift=50;
		double[] shiftedTime=new double[defaultShift];
		double[] shiftedData=new double[defaultShift];
		if(action.equals("next")) {
			if(indexEEG+defaultShift<EEGdata.length-1) {
				for (int i = 0; i < defaultShift; i++) {
					shiftedTime[i]=this.timeEEG[indexECG+i];
					shiftedData[i]=this.EEGdata[indexECG+i];
				}
				indexEEG=indexEEG+defaultShift;
			}else {
				shiftedTime=null;
				shiftedData=null;
			}
		}else {
			if(indexEEG-defaultShift>=0) {
				for (int i = 0; i < defaultShift; i++) {
					shiftedTime[i]=this.timeEEG[indexEEG-defaultShift+i];
					shiftedData[i]=this.EEGdata[indexEEG-defaultShift+i];
				}
				indexEEG=indexEEG-defaultShift;
			}else {
				shiftedTime=null;
				shiftedData=null;
			}
		}
		return godLeftMeUnfinished(shiftedTime, shiftedData);
	}
	/*Ok, this is a horrible idea, let's do it: the thing is that the static chart class requires a Jlist to work... I, being the lazy ass that I am
	 * I just decided it was better to index an array of elements and then translate into lists as we need it instead of doing that from the beginning.
	 *  The following code does the translation
	 * of array to lists.*/
	private List<Double>[] godLeftMeUnfinished(double[]time, double[]data){
		List <Double> time1= new ArrayList <Double> ();
		List <Double> data1= new ArrayList <Double> ();
		for (int i = 0; i < data.length; i++) {
			time1.add(time[i]);
			data1.add(data[i]);
		}
		return new List [] {time1, data1};
	}
}
