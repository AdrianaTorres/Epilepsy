package optimizedGraphics;

import java.util.List;

import javax.swing.SwingWorker;

import org.knowm.xchart.XYChart;

public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private ChartMonitor monitor;
    private XYChart chart;
    private String plotType;

    public UpdateWorker(ChartMonitor monitor, String plotType) {
        this.monitor = monitor;
        this.plotType=plotType;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(200);
            if(this.plotType.contains("ECG")) {
            	publish(monitor.getModel().getECGData());
            }else {
            	publish(monitor.getModel().getEEGData());
            }   
        }
    }

    @Override
    protected void process(List<List<Double>[]> chunks) {
        for (List<Double>[] data : chunks) {
            monitor.updateData(data);
        }
    }

}