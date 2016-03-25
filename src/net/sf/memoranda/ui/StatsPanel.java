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
	JTextArea textArea = new JTextArea();
	private String fileLocation = Util.getEnvDir();
	
    public StatsPanel() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	add(tabbedPane);
    	
    	getTimes();
    	textArea.setEditable(false);
    	textArea.setBackground(Color.WHITE);
    	tabbedPane.addTab("Time", null, textArea, null);
       
    	
    }
    
    public void getTimes(){
    	try {
    		   FileReader fileReader = new FileReader(fileLocation + "times.txt");
    		   BufferedReader reader = new BufferedReader(fileReader);
    		   textArea.read(reader,"textArea");
    		}catch (IOException ioe) {
    		   System.out.println(ioe);
    		}
    }
}