package net.sf.memoranda.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import net.sf.memoranda.util.Util;
import net.sf.memoranda.util.lineCounter;

public class StatsPanel extends JPanel {
	JTextArea timeTextArea = new JTextArea();
	JTextArea linesTextArea = new JTextArea();
	JScrollPane scroll = new JScrollPane(linesTextArea);
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
    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

    	tabbedPane.addTab("Lines", null, scroll, null);
       
    	
    }
    
    public void getTimes(){
    	try {
    		File f = new File(fileLocation + "times.txt");
    		if(!f.exists()) { 
    			PrintWriter writer = new PrintWriter(fileLocation + "times.txt", "UTF-8");
    			writer.println("");
    			writer.close();
    		}
    		FileReader fileReader = new FileReader(fileLocation + "times.txt");
    		BufferedReader reader = new BufferedReader(fileReader);
    		timeTextArea.read(reader,"timeTextArea");
    	}catch (IOException ioe) {
    		   System.out.println(ioe);
    	}
    }
    
    public void updateLines(){
    	try {
    		lineCounter lc = new lineCounter();
    		lc.updateLineFile();
    		FileReader fileReader = new FileReader(fileLocation + "lines.txt");
    		BufferedReader reader = new BufferedReader(fileReader);
    		linesTextArea.read(reader,"lineTextArea");
    		}catch (IOException ioe) {
    		   System.out.println(ioe);
    		}
    }
}