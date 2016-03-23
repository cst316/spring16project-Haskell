package net.sf.memoranda.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.sf.memoranda.util.Util;

public class StatsPanel extends JPanel {
	JTextArea timeTextArea = new JTextArea();
	JTextArea linesTextArea = new JTextArea();
	private String fileLocation = Util.getEnvDir();
	
    public StatsPanel() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	add(tabbedPane);
    	
    	getTimes();
    	timeTextArea.setEditable(false);
    	timeTextArea.setBackground(Color.WHITE);
    	tabbedPane.addTab("Time", null, timeTextArea, null);
    	
    	updateLines();
    	linesTextArea.setEditable(false);
    	linesTextArea.setBackground(Color.WHITE);
    	tabbedPane.addTab("Lines", null, linesTextArea, null);
       
    	
    }
    
    public void getTimes(){
    	try {
    		   FileReader fileReader = new FileReader(fileLocation + "times.txt");
    		   BufferedReader reader = new BufferedReader(fileReader);
    		   timeTextArea.read(reader,"timeTextArea");
    		}catch (IOException ioe) {
    		   System.out.println(ioe);
    		}
    }
    
    public void updateLines(){
    	try {
    		   FileReader fileReader = new FileReader(fileLocation + "times.txt");
    		   BufferedReader reader = new BufferedReader(fileReader);
    		   timeTextArea.read(reader,"timeTextArea");
    		}catch (IOException ioe) {
    		   System.out.println(ioe);
    		}
    }
}