package optimizedGraphics;

import java.util.List;

import javax.swing.SwingWorker;

import org.knowm.xchart.XYChart;

public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private ChartMonitor monitor;
    private XYChart chart;

    public UpdateWorker(ChartMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(200);
            publish(monitor.getModel().getECGData());
        }
    }

    @Override
    protected void process(List<List<Double>[]> chunks) {
        for (List<Double>[] data : chunks) {
            monitor.updateData(data);
        }
    }

}