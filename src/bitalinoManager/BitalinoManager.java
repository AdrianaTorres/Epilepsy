package bitalinoManager;

import java.util.ArrayList;
import java.util.List;

public class BitalinoManager implements BitalinoModel{
	private double phase;
	private double phase1;
	private double radians=0;
	public void connect() {
		// TODO Auto-generated method stub
		
	}

	public boolean isConnected() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Double>[] getECGData() {
		phase = Math.random()*10;
        List<Double> xData = new ArrayList<>(100);
        List<Double> yData = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            radians = radians+i*0.01;
            xData.add(radians);
            yData.add(Math.sin(radians));
        }
        return new List[]{xData, yData};
	}

	@Override
	public List<Double>[] getEEGData() {
		phase1 += 2 * Math.PI * 2 / 20.0;
        List<Double> xData = new ArrayList<>(100);
        List<Double> yData = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            double radians = phase1 + (2 * Math.PI / 100 * i);
            xData.add(radians);
            yData.add(Math.sin(radians));
        }
        return new List[]{xData, yData};
	}

}
