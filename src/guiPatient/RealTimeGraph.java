package guiPatient;

import javax.swing.JFrame;

import org.knowm.xchart.*;

public class RealTimeGraph {

	private XYChart chart;
	private SwingWrapper<XYChart> sw;
	private String plotType;

	public RealTimeGraph(double[]Xaxis,double[]Yaxis, String plotName,String xAxis, String yAxis, String legend) {
		double[][] data=RealTimeGraph.merger(Xaxis, Yaxis);
		chart = QuickChart.getChart(plotName, xAxis, yAxis, legend, data[0], data[1]);
		sw = new SwingWrapper<XYChart>(chart);
		this.plotType=legend;
	}

	public SwingWrapper<XYChart> updateGraph(double[] Xaxis, double[]Yaxis){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("interrupted thread while painting graph.");
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				double[][]data=RealTimeGraph.merger(Xaxis, Yaxis);
				chart.updateXYSeries(plotType, data[0], data[1], null);
				sw.repaintChart();
			}
		});
		return this.sw;
	}

	public JFrame getGraph(){
		JFrame graph=sw.displayChart();
		graph.setVisible(false);
		return(graph);
	}
	private static double[][] merger(double []Xaxis,double[]Yaxis) {
		return new double[][] { Xaxis, Yaxis };
	}
}
