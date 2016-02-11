package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectManager;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ProjectCreationPanel extends JFrame implements ActionListener, ListSelectionListener{
	
	//Container for content pane//
	private Container c = null;
	
	//Top Panel and its components//
	private JPanel topPanel = null;
	private JLabel header = new JLabel();
	
	//Center Panel and its components//
	private JPanel centerPanel;
		//Project Title and Text Field//
	private JLabel titleLabel;
	private JTextField prTitleField;
		//Start Date and End Date Stuff//
	private JLabel sdLabel;
	private JSpinner startDate;
	private JLabel endLabel;
	private JSpinner endDate;
	private JCheckBox endDateChB;
	private JButton edButton;
	boolean ignoreStartChanged = false;
	boolean ignoreEndChanged = false;
	CalendarFrame endCalFrame = new CalendarFrame();
    CalendarFrame startCalFrame = new CalendarFrame();
    	//Priority and Stage//
    private JLabel statusLabel;
    private JComboBox statusComboBox;
    private JLabel stageLabel;
    private JComboBox stageComboBox;
    
    	//Customer Label, Field, and Checkbox//
    private JLabel customerLabel;
    private JTextField customerField;
    private JCheckBox customerChB;
    
		//Team Member Adding//
    private DefaultListModel listModel = new DefaultListModel();
    private JList list = new JList(listModel);
	private JScrollPane teamScrollPane;
	private JLabel addMemberLabel;
	private JTextField addMemberField;
	private JButton addButton;
	private JButton removeButton;
	private String selected;
	
    	//Base Lines of Code//
	private JLabel LOClabel;
	private JRadioButton LOCdefault;
	private JRadioButton LOCanother;
	private JTextField LOCField;
	
	
		//File Location//
	private JFileChooser fileChooser;
	
	//Bottom Panel and its components//
	private JPanel bottomPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JButton clearButton;
	
	public ProjectCreationPanel() {
		
		
		
		this.setBounds(400, 100, 500, 650);
		
		c = this.getContentPane();
		getContentPane().setLayout(null);
		
		
		//Build Each Panel
		buildTopPanel();
		buildCenterPanel();
		buildBottomPanel();
		
		//Add them all to the container//
		c.add(topPanel);
		c.add(centerPanel);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(21, 78, 70, 14);
		centerPanel.add(lblDescription);
		
		JTextArea txtrTextBox = new JTextArea();
		txtrTextBox.setBounds(21, 92, 414, 74);
		txtrTextBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		centerPanel.add(txtrTextBox);
		c.add(bottomPanel);
		
		
		
		
		//Visibility and Close Operation//
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	public void buildTopPanel(){
		topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setLayout(null);
		topPanel.setBounds(10, 11, 464, 53);
		header.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		header.setBounds(10, 0, 350, 41);
    	header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("New Project"));
        header.setIcon(new ImageIcon(net.sf.memoranda.ui.ProjectDialog.class.getResource(
            "resources/icons/project48.png")));
        topPanel.add(header);
        
        
	}
	
	public void buildCenterPanel(){
		centerPanel = new JPanel();
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setBounds(10, 75, 464, 441);
		getContentPane().add(centerPanel);
		centerPanel.setLayout(null);
		
		titleLabel = new JLabel("Name");
		titleLabel.setBounds(21, 22, 39, 14);
		centerPanel.add(titleLabel);
		
		prTitleField = new JTextField();
		prTitleField.setBounds(57, 19, 204, 20);
		centerPanel.add(prTitleField);
		prTitleField.setColumns(10);
		
		sdLabel = new JLabel("Start Date");
		sdLabel.setBounds(21, 53, 59, 14);
		centerPanel.add(sdLabel);
		
		//Start Date Spinner//
    	startDate = new JSpinner(new SpinnerDateModel());
    	startDate.setBounds(82, 50, 85, 20);
        startDate.setLocale(Local.getCurrentLocale());
		//Added by (jcscoobyrs) on 17-Nov-2003 at 14:24:43 PM
		//---------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
		startDate.setEditor(new JSpinner.DateEditor(startDate, sdf.toPattern()));
		//---------------------------------------------------
        startDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (ignoreStartChanged) return;
                ignoreStartChanged = true;
                Date sd = (Date) startDate.getModel().getValue();
                if (endDate.isEnabled()) {
                  Date ed = (Date) endDate.getModel().getValue();
                  if (sd.after(ed)) {
                    startDate.getModel().setValue(ed);
                    sd = ed;
                  }
                }
                startCalFrame.cal.set(new CalendarDate(sd));
                ignoreStartChanged = false;
            }
        });
        
        centerPanel.add(startDate);
		
		//End Date JSpinner//
        endDate = new JSpinner(new SpinnerDateModel());
        endDate.setEnabled(false);
        endDate.setBounds(308, 53, 85, 20);
        endDate.setLocale(Local.getCurrentLocale());
		//Added by (jcscoobyrs) on 17-Nov-2003 at 14:24:43 PM
		//---------------------------------------------------
		endDate.setEditor(new JSpinner.DateEditor(endDate, 
			sdf.toPattern()));
		//---------------------------------------------------
        endDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (ignoreEndChanged) return;
                ignoreEndChanged = true;
                Date sd = (Date) startDate.getModel().getValue();
                Date ed = (Date) endDate.getModel().getValue();
                if (sd.after(ed)) {
                    endDate.getModel().setValue(sd);
                    ed = sd;
                }
                endCalFrame.cal.set(new CalendarDate(ed));
                ignoreEndChanged = false;
            }
        });
        centerPanel.add(endDate);
        
        
        //End Date Check Box//
        endDateChB = new JCheckBox();
        endDateChB.setBounds(224, 53, 20, 20);
        endDateChB.setForeground(Color.gray);
        endDateChB.addActionListener(this);
        centerPanel.add(endDateChB);
        
        edButton = new JButton();
        edButton.setEnabled(false);
        edButton.setBounds(397, 53, 20, 20);
        edButton.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/calendar.png")));
        edButton.addActionListener(this);
        centerPanel.add(edButton);
        
		endLabel = new JLabel(Local.getString("End Date"));
		endLabel.setForeground(Color.gray);
		endLabel.setBounds(250, 56, 59, 14);
		centerPanel.add(endLabel);
		
		customerLabel = new JLabel("Customer");
		customerLabel.setBounds(265,235,70,25);
		customerLabel.setForeground(Color.gray);
		centerPanel.add(customerLabel);
		

        customerChB = new JCheckBox();
        customerChB.setBounds(240, 237, 20, 20);
        customerChB.addActionListener(this);
        centerPanel.add(customerChB);
		
        customerField = new JTextField(" Optional");
        customerField.setBounds(330, 237, 100, 20);
        customerField.setForeground(Color.gray);
        centerPanel.add(customerField);
        
		statusLabel = new JLabel("Priority");
		statusLabel.setBounds(35, 235, 49, 25);
		centerPanel.add(statusLabel);
		
		statusComboBox = new JComboBox(new DefaultComboBoxModel(new String[] {"Very Low", "Low", "Medium", "High", "Very High"}));
		statusComboBox.setBackground(new Color(255, 255, 255));
		//statusComboBox.setModel(new DefaultComboBoxModel(new String[] {"Very Low", "Low", "Medium", "High", "Very High"}));
		statusComboBox.setSelectedIndex(2);
		statusComboBox.setBounds(82, 237, 109, 20);
		centerPanel.add(statusComboBox);
		
		stageLabel = new JLabel("Stage");
		stageLabel.setBounds(271, 22, 47, 14);
		centerPanel.add(stageLabel);
		
		stageComboBox = new JComboBox();
		stageComboBox.setBackground(new Color(255, 255, 255));
		stageComboBox.setModel(new DefaultComboBoxModel(new String[] {"Planning", "Design", "Design Review", "Code", "Code Review", "Compile", "Test", "Postmortem"}));
		stageComboBox.setBounds(308, 19, 127, 20);
		centerPanel.add(stageComboBox);
		
		teamScrollPane = new JScrollPane();
		teamScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		teamScrollPane.setBounds(21, 307, 198, 107);
		centerPanel.add(teamScrollPane);
		
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		
		
		
		teamScrollPane.setViewportView(list);
		
		JLabel lblTeamMembers = new JLabel("Team Members:");
		lblTeamMembers.setHorizontalAlignment(SwingConstants.CENTER);
		teamScrollPane.setColumnHeaderView(lblTeamMembers);
		
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		addButton.setBounds(244, 357, 191, 23);
		centerPanel.add(addButton);
		
		removeButton = new JButton("Remove Selected");
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//int index = list.getSelectedIndex();
				
				//listModel.remove(index);
				listModel.removeElement(list.getSelectedValue());
				System.out.println(list.getSelectedValue());
				list = new JList(listModel);
				
			}
		});
		removeButton.setBounds(244, 391, 191, 23);
		centerPanel.add(removeButton);
		
		addMemberLabel = new JLabel("Team Member Name");
		addMemberLabel.setBounds(244, 301, 138, 14);
		centerPanel.add(addMemberLabel);
		
		addMemberField = new JTextField();
		addMemberField.setBounds(244, 326, 191, 20);
		addMemberField.setColumns(10);
		centerPanel.add(addMemberField);
		
		LOClabel = new JLabel("Size of Current Code");
		LOClabel.setBounds(21, 175, 120, 14);
		centerPanel.add(LOClabel);
		
		LOCanother = new JRadioButton("Another Amount:");
		LOCanother.setBounds(147, 195, 120, 20);
		LOCanother.setForeground(Color.gray);
		LOCanother.addActionListener(this);
		LOCanother.setFocusable(false);
	    centerPanel.add(LOCanother);
		
	        
	    LOCdefault = new JRadioButton("(Default) 0");
	    LOCdefault.setSelected(true);
	    LOCdefault.setBounds(147, 173, 85, 19);
	    LOCdefault.addActionListener(this);
	    LOCdefault.setFocusable(false);
		centerPanel.add(LOCdefault);
	        
		LOCField = new JTextField();
		LOCField.setBounds(271, 195, 107, 20);
		LOCField.setColumns(10);
		LOCField.disable();
		centerPanel.add(LOCField);
		
		/*
		fileChooser = new JFileChooser();
		fileChooser.setBounds(x, y, width, height);
		*/
	}


	public void buildBottomPanel(){
		bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(10, 527, 464, 73);
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		okButton = new JButton("Ok");
		okButton.setBounds(327, 26, 112, 23);
		okButton.addActionListener(this);
		bottomPanel.add(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(176, 26, 112, 23);
		cancelButton.addActionListener(this);
		bottomPanel.add(cancelButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(25, 26, 112, 23);
		clearButton.addActionListener(this);
		bottomPanel.add(clearButton);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//selected = e.getSource().getSelectedValue();
		//System.out.println(e);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == customerChB && customerChB.isSelected() == true){
			customerLabel.setForeground(Color.BLACK);
			customerField.setText("");
			customerField.setForeground(Color.BLACK);
		}
		
		if(o == customerChB && customerChB.isSelected() == false){
			customerLabel.setForeground(Color.gray);
			customerField.setText(" Optional");
			customerField.setForeground(Color.gray);
		}
		
		if(o == endDateChB){
			endDate.setEnabled(endDateChB.isSelected());
	        edButton.setEnabled(endDateChB.isSelected());
	        if (endDateChB.isSelected()) {
	            endDateChB.setForeground(Color.BLACK);
	            endLabel.setForeground(Color.BLACK);
	            endDate.getModel().setValue(startDate.getModel().getValue());
	        }
	        else{
	        	endDateChB.setForeground(Color.GRAY);
	        	endLabel.setForeground(Color.gray);
	        }
		}
		
		if(o == edButton){
			endCalFrame.setLocation((this.getContentPane().getWidth() / 2),0);
	        endCalFrame.setSize((this.getContentPane().getWidth() / 2), 
	            this.getContentPane().getHeight());
	        this.getLayeredPane().add(endCalFrame);
	        endCalFrame.setTitle(Local.getString("End date"));
	        endCalFrame.show();
		}
		
		if(o == LOCdefault){
			//Blacken the text//
			LOCdefault.setForeground(Color.black);
			
			LOCanother.setSelected(false);
			
			//Disable the Field//
			LOCField.setForeground(Color.gray);
			LOCField.disable();
			
		}
		
		if(o == LOCanother){
			LOCanother.setForeground(Color.black);
			
			//Gray the default//
			LOCdefault.setForeground(Color.gray);
			
			//Deselect it//
			LOCdefault.setSelected(false);
			
			//Enable the Field//
			LOCField.setText("");
			LOCField.enable();
			
		}
		
		if (o == addButton  && addMemberField.getText() != ""){
        	listModel.addElement(addMemberField.getText());
        	list = new JList(listModel);
        	addMemberField.setText("");
        }
		
		if(o == removeButton){
			//int index = list.getSelectedIndex();
			
			//listModel.remove(index);
			listModel.removeElement(list.getSelectedValue());
			System.out.println(list.getSelectedValue());
			list = new JList(listModel);
		}
		
		if(o == okButton){
			String title = prTitleField.getText();
			String stage = (String) stageComboBox.getSelectedItem();
			
			CalendarDate startD = new CalendarDate((Date) startDate.getModel().getValue());
	        CalendarDate endD = null;
	        if (endDateChB.isSelected()){
	            endD = new CalendarDate((Date) endDate.getModel().getValue());
	        }
			Project prj = ProjectManager.createProject(title, startD, endD);
	        CurrentStorage.get().storeProjectManager();
		}
		
		if(o == cancelButton){
			this.setVisible(false);
			this.dispose();
		}
		
		if(o == clearButton){
			buildCenterPanel();
			c.add(centerPanel);
			
			c.revalidate();
			c.repaint();
		}
		
	}
}
