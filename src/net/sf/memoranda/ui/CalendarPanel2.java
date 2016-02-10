package net.sf.memoranda.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.ui.Calendar2;

public class CalendarPanel2 extends JPanel{
	JPanel test = new JPanel(new BorderLayout());
	Calendar2 calendar2 = new Calendar2(CurrentDate.get());
	//JLabel testLabel = new JLabel("testLabel");
	
	
	public CalendarPanel2(){
		//this.setLayout(new BorderLayout());
		this.add(test);
		test.add(calendar2, new BorderLayout().CENTER);
		calendar2.setVisible(true);
		
			/*test.add(testLabel, BorderLayout.NORTH);
			testLabel.setVisible(true);*/
	}
}
