package guiHospital;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class FancyListSellection extends DefaultListCellRenderer {
	private HashMap theChosen = new HashMap();
	private HashMap theCritical = new HashMap();

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		System.out.println(value);
		String content=(String)value;
		
		if (content.contains("(SYMPTOMS)")) {
			theChosen.put(value, "chosen");
		}
		if (content.contains("(CRITICAL)")) {
			theCritical.put(value, "critical");
		}

		if (theCritical.containsKey(value)) {
			setForeground(Color.red);
		} else {
		}
		if(theChosen.containsKey(value)) {
			setForeground(Color.yellow);
		}else {
		}

		return (this);
	}
}
