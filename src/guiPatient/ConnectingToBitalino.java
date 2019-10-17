package guiPatient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mainMethodPatient.UserProfile;
import optimizedGraphics.UpdateWorker;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectingToBitalino {
	private JFrame f =new JFrame();
	private JPanel contentPane;
	private DefaultBoundedRangeModel model;
	private JProgressBar jb;
	private JPanel panel_3;
	private JButton button_1;
	private JTextField textField_1;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private Component horizontalStrut_a;
	private Component horizontalStrut_b;
	private UserProfile up;
	public ConnectingToBitalino(UserProfile up) {
		this.up=up;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(500, 100, 750, 300);
		f.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		f.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,1));
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		panel_3 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_3.setBackground(Color.BLACK);
		JLabel label_1 = new JLabel("PLEASE INTRODUCE THE MAC ADDRESS OF YOUR BITALINO");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		Font ui =new Font("Segoe UI",Font.PLAIN,25);
		label_1.setFont(ui);
		label_1.setForeground(Color.WHITE);
		contentPane.add(panel_1);
		contentPane.add(panel_2);
		contentPane.add(panel_3);
		panel_1.setLayout(new BorderLayout());

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_1, BorderLayout.WEST);
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_2, BorderLayout.EAST);
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_1, BorderLayout.NORTH);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_2, BorderLayout.SOUTH);

		panel_1.add(label_1);

		textField_1 = new JTextField ();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBackground(Color.GRAY);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(ui);
		panel_2.setLayout(new BorderLayout());
		panel_2.add(textField_1, BorderLayout.CENTER);

		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		panel_2.add(horizontalStrut_3, BorderLayout.WEST);
		Component horizontalStrut_4 = Box.createHorizontalStrut(30);
		panel_2.add(horizontalStrut_4, BorderLayout.EAST);
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_3, BorderLayout.NORTH);
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_4, BorderLayout.SOUTH);

		button_1 =new JButton("Connect!");

		button_1.setBackground(Color.DARK_GRAY);
		button_1.setForeground(Color.WHITE);
		button_1.setFont(ui);

		panel_3.setLayout(new BorderLayout());
		panel_3.add(button_1, BorderLayout.CENTER);

		horizontalStrut_5 = Box.createHorizontalStrut(160);
		panel_3.add(horizontalStrut_5, BorderLayout.WEST);
		horizontalStrut_6 = Box.createHorizontalStrut(160);
		panel_3.add(horizontalStrut_6, BorderLayout.EAST);
		Component verticalStrut_5 = Box.createVerticalStrut(30);
		panel_3.add(verticalStrut_5, BorderLayout.NORTH);
		Component verticalStrut_6 = Box.createVerticalStrut(30);
		panel_3.add(verticalStrut_6, BorderLayout.SOUTH);


		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_3.remove(button_1);

				model = new DefaultBoundedRangeModel();
				model.setMinimum(0);
				model.setMaximum(100);

				jb = new JProgressBar(model);
				jb.setBackground(Color.DARK_GRAY);
				jb.setForeground(Color.WHITE);
				jb.setString("Working...");
				panel_3.add(jb);
				horizontalStrut_a=Box.createHorizontalStrut(80);
				horizontalStrut_b=Box.createHorizontalStrut(80);
				panel_3.remove(horizontalStrut_5);
				panel_3.remove(horizontalStrut_6);
				panel_3.add(horizontalStrut_a, BorderLayout.WEST);
				panel_3.add(horizontalStrut_b, BorderLayout.EAST);
				panel_3.setVisible(false);
				panel_3.setVisible(true);
				textField_1.setEnabled(false);
				up.initiateBitalino(textField_1.getText());
				iterate();
			}
		});
		f.setVisible(true);
	}
	private Thread t;
	private void iterate(){  
		t = new Thread(new Runnable() {
			public void run() {
				int i = 1;
				long time =System.currentTimeMillis();
				model.setMinimum(0);
				model.setMaximum(100);
				try {
					while (i <= 100 ) {
						long currentTime=System.currentTimeMillis();
						if(up.bitalinoIsconnected()) {
							Thread bit = new Thread(up.getBitalinoManager());
							bit.start();
							f.dispose();
							GuiPatient g= new GuiPatient(up);
							t.interrupt();
						}
						System.out.println(currentTime-time+"	"+time+"	"+currentTime);
						if((currentTime-time)/7500>0.5) {
							System.out.println("paso!");
							failedToConnect();
						}
						if(!t.isInterrupted()) {
							model.setValue(i);
							i++;
							Thread.sleep(10);
							if(i==100) {
								model.setValue(0);
								i=0;
							}
						}else {
							break;
						}
					}

				} catch (InterruptedException ex) {
					model.setValue(model.getMaximum());
				}
			}
		});
		t.start();
	}
	private void stop() {
		t.interrupt();
	}
	public void failedToConnect() {
		stop();
		panel_3.remove(jb);
		panel_3.remove(horizontalStrut_a);
		panel_3.remove(horizontalStrut_b);
		panel_3.add(button_1,BorderLayout.CENTER);
		panel_3.add(horizontalStrut_5,BorderLayout.EAST);
		panel_3.add(horizontalStrut_6,BorderLayout.WEST);
		textField_1.setEnabled(true);
		contentPane.setVisible(false);
		contentPane.setVisible(true);
	}
	public void successfullyConnected() {
		f.dispose();
	}
}

