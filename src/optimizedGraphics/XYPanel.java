package optimizedGraphics;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import mainMethodPatient.UserProfile;



public class XYPanel extends JPanel  {

    private UserProfile model;
    private XYChart chart;

    //requires a 2 sets of lists: list<Double>[] to work, one for Xaxis and one for Yaxis.
    public XYPanel(UserProfile model,String plotName) {
        this.model = model;
        chart = new XYChartBuilder().width(400).height(200).title(plotName).build();
        List<Double>[] data;
        if(plotName.contains("ECG")) {
        	data = model.getECGData();
        }else {
        	data = model.getEEGData();
        }
        
        chart.addSeries("sine", data[0], data[1]);
        setLayout(new BorderLayout());

        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        add(chartPane);
        UpdateWorker worker;
        if(plotName.contains("ECG")) {
        	worker= new UpdateWorker(model.getECGData());
        }else {
        	worker= new UpdateWorker(model.getEEGData());
        }
        
        worker.execute();
    }
}
