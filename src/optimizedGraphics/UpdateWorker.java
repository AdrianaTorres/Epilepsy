package optimizedGraphics;

import java.util.List;

import javax.swing.SwingWorker;

import org.knowm.xchart.XYChart;

import mainMethodPatient.UserProfile;

public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {
	//not sure what this is for and why would I ever need this... Feels like I could just use 
	//the Thread.sleep(100) and publish methods in the XYPanel itself with no need to trash with no model
    private XYChart chart;
    List <Double> [] data;
    public UpdateWorker(List<Double>[]data) {
    	this.data=data;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(100);
            publish(data);
            System.out.println("merily!");
        }
    }

    @Override
    protected void process(List<List<Double>[]> chunks) {
        for (List<Double>[] data : chunks) {
            //monitor.updateData(data);
        }
    }

}
