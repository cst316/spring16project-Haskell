package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.ui.Calendar2;

public class CalendarPanel2 extends JPanel{
	JPanel calendarPanel2 = new JPanel(new BorderLayout());
	Calendar2 calendar2 = new Calendar2(CurrentDate.get());
	
	
	public CalendarPanel2(){
		//this.setLayout(new BorderLayout());
		this.add(calendarPanel2);
		calendar2.setPreferredSize(new Dimension(getMaximumSize()));
		calendarPanel2.add(calendar2, new BorderLayout().CENTER);
		calendar2.setVisible(true);
		
			/*test.add(testLabel, BorderLayout.NORTH);
			testLabel.setVisible(true);*/
	}
}
