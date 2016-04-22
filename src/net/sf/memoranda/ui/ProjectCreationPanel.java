package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectManager;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.lineCounter;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ProjectCreationPanel extends JFrame implements ActionListener, ChangeListener, ListSelectionListener{
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
    	//Description Label and Text Area//
    private JLabel descLabel;
    private JTextArea description;	
    private JScrollPane descScrollPane;
    	//Priority and Stage//
    private JLabel statusLabel;
    private JComboBox statusComboBox;
    private JLabel stageLabel;
    private JComboBox stageComboBox;
    	//Customer Label, Field, and Checkbox//
    private JLabel customerLabel;
    private JTextField customerField;
    private JCheckBox customerChB;
    	//File Selection//
    private JButton fileButton;
    private JTextField fileField;
    private JFileChooser fileChooser;
		//Team Member Adding//
    private DefaultListModel listModel = new DefaultListModel();
    private JList list = new JList(listModel);
	private JScrollPane teamScrollPane;
	private JLabel teamLabel;
	private JLabel addMemberLabel;
	private JTextField addMemberField;
	private JButton addButton;
	private JButton removeButton;
	private int selected;
	
	//Bottom Panel and its components//
	private JPanel bottomPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JButton clearButton;
	private JLabel errorLabel;
	private JLabel requirementLabel;
	
	//Estimation Components//
	private JPanel estimationPanel;
	private JLabel lblDesign;
	private JSpinner designSpinner;
	private JLabel lblPlanning;
	private JSpinner planningSpinner;
	private JLabel lblDesignReview;
	private JSpinner designReviewSpinner;
	private JLabel lblCode;
	private JSpinner codeSpinner;
	private JLabel lblCodeReview;
	private JSpinner codeReviewSpinner;
	private JLabel lblCompile;
	private JSpinner compileSpinner;
	private JLabel lblTest;
	private JSpinner testSpinner;
	private JLabel lblPostmortem;
	private JSpinner postmortemSpinner;
	private JLabel lblTotal;
	private JTextField totalTextField;
	private JPanel estBottomPanel;
	private JButton estOkButton;
	private JButton estCancelButton;
	private JButton estClearButton;
	private JButton estBackButton;
	private boolean estIsBuilt;
	private JLabel estNote;
	
	public ProjectCreationPanel() {
		this.setBounds(400, 100, 500, 650);
		c = this.getContentPane();
		c.setLayout(null);
		
		//Build Each Panel
		buildTopPanel();
		buildCenterPanel();
		buildBottomPanel();
		
		//Add them all to the container//
		c.add(topPanel);
		c.add(centerPanel);
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
		centerPanel.setLayout(null);
		
		//Project Name Label and Field//
		titleLabel = new JLabel("*Name");
		titleLabel.setBounds(15, 22, 39, 14);
		centerPanel.add(titleLabel);
		
		prTitleField = new JTextField();
		prTitleField.setBounds(57, 19, 204, 20);
		centerPanel.add(prTitleField);
		prTitleField.setColumns(10);
		
		//Start Date Components//
		sdLabel = new JLabel("Start Date");
		sdLabel.setBounds(18, 69, 59, 14);
		centerPanel.add(sdLabel);
		
    	startDate = new JSpinner(new SpinnerDateModel());
    	startDate.setBounds(82, 66, 85, 20);
        startDate.setLocale(Local.getCurrentLocale());
		//Added by (jcscoobyrs) on 17-Nov-2003 at 14:24:43 PM
		//---------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
		startDate.setEditor(new JSpinner.DateEditor(startDate, sdf.toPattern()));
		//---------------------------------------------------
        startDate.addChangeListener(this);
        centerPanel.add(startDate);
		
		//End Date Components//
        endDate = new JSpinner(new SpinnerDateModel());
        endDate.setEnabled(false);
        endDate.setBounds(308, 66, 85, 20);
        endDate.setLocale(Local.getCurrentLocale());
		//Added by (jcscoobyrs) on 17-Nov-2003 at 14:24:43 PM
		//---------------------------------------------------
		endDate.setEditor(new JSpinner.DateEditor(endDate, 
			sdf.toPattern()));
		//---------------------------------------------------
		endDate.addChangeListener(this);
        centerPanel.add(endDate);
        
        endDateChB = new JCheckBox();
        endDateChB.setBounds(222, 66, 20, 20);
        endDateChB.setForeground(Color.gray);
        endDateChB.addActionListener(this);
        centerPanel.add(endDateChB);
        
        edButton = new JButton();
        edButton.setEnabled(false);
        edButton.setBounds(396, 66, 20, 20);
        edButton.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/calendar.png")));
        edButton.addActionListener(this);
        centerPanel.add(edButton);
        
		endLabel = new JLabel(Local.getString("End Date"));
		endLabel.setForeground(Color.gray);
		endLabel.setBounds(248, 69, 59, 14);
		centerPanel.add(endLabel);
		
		//Description Components//
		descLabel = new JLabel("Description");
		descLabel.setBounds(18, 103, 70, 14);
		centerPanel.add(descLabel);
		
		descScrollPane = new JScrollPane();
		descScrollPane.setBounds(21, 128, 416, 74);
		descScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		centerPanel.add(descScrollPane);
		
		description = new JTextArea();
		descScrollPane.setViewportView(description);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBorder(BorderFactory.createCompoundBorder(
					(BorderFactory.createLineBorder(Color.gray)), 
					(BorderFactory.createEmptyBorder(0,5,0,5))));
		
		
		//Customer Components//
		customerLabel = new JLabel("Customer");
		customerLabel.setBounds(262,218,70,25);
		customerLabel.setForeground(Color.gray);
		centerPanel.add(customerLabel);
	
        customerChB = new JCheckBox();
        customerChB.setBounds(241, 220, 20, 20);
        customerChB.addActionListener(this);
        centerPanel.add(customerChB);
		
        customerField = new JTextField(" Optional");
        customerField.setBounds(330, 220, 107, 20);
        customerField.setForeground(Color.gray);
        centerPanel.add(customerField);
        
        //Status Components//
		statusLabel = new JLabel("Priority");
		statusLabel.setBounds(31, 218, 49, 25);
		centerPanel.add(statusLabel);
		
		statusComboBox = new JComboBox(new DefaultComboBoxModel(new String[] {"Very Low", "Low", "Medium", "High", "Very High"}));
		statusComboBox.setBackground(new Color(255, 255, 255));
		statusComboBox.setSelectedIndex(2);
		statusComboBox.setBounds(82, 220, 109, 20);
		centerPanel.add(statusComboBox);
		
		//File (Directory) Chooser Components//
		fileButton = new JButton("*Choose Project File");
		fileButton.setBounds(19, 266, 148, 20);
		fileButton.addActionListener(this);
		centerPanel.add(fileButton);
		
		fileField = new JTextField();
		fileField.setBounds(176, 266, 261, 20);
		fileField.setBackground(Color.white);
		fileField.setEditable(false);
		centerPanel.add(fileField);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		//Stage Components//
		stageLabel = new JLabel("Stage");
		stageLabel.setBounds(271, 22, 47, 14);
		centerPanel.add(stageLabel);
		
		stageComboBox = new JComboBox();
		stageComboBox.setBackground(new Color(255, 255, 255));
		stageComboBox.setModel(new DefaultComboBoxModel(new String[] {"Planning", "Design", "Design Review", "Code", "Code Review", "Compile", "Test", "Postmortem"}));
		stageComboBox.setBounds(308, 19, 129, 20);
		centerPanel.add(stageComboBox);
		
		//Team Components//
		teamScrollPane = new JScrollPane();
		teamScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		teamScrollPane.setBounds(21, 317, 198, 107);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		teamScrollPane.setViewportView(list);
		teamLabel = new JLabel("Team Members:");
		teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamScrollPane.setColumnHeaderView(teamLabel);
		centerPanel.add(teamScrollPane);
		
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		addButton.setBounds(244, 367, 191, 23);
		centerPanel.add(addButton);
		
		removeButton = new JButton("Remove Selected");
		removeButton.addActionListener(this);
		removeButton.setBounds(244, 401, 191, 23);
		centerPanel.add(removeButton);
		
		addMemberLabel = new JLabel("*Team Member Name");
		addMemberLabel.setBounds(244, 311, 138, 14);
		centerPanel.add(addMemberLabel);
		
		addMemberField = new JTextField();
		addMemberField.setBounds(244, 336, 191, 20);
		addMemberField.setColumns(10);
		centerPanel.add(addMemberField);
	}

	public void buildBottomPanel(){
		bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(10, 527, 464, 73);
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		//Bottom Buttons//
		okButton = new JButton("Next");
		okButton.setBounds(327, 26, 112, 23);
		okButton.addActionListener(this);
		bottomPanel.add(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(176, 26, 112, 23);
		cancelButton.addActionListener(this);
		bottomPanel.add(cancelButton);
		
		errorLabel = new JLabel();
    	errorLabel.setBounds(175, 53, 280, 20);
    	errorLabel.setForeground(Color.red);
    	errorLabel.setVisible(false);
    	bottomPanel.add(errorLabel);
    	
    	requirementLabel = new JLabel("* indicates required field");
    	requirementLabel.setBounds(15, 53, 280, 20);
    	requirementLabel.setVisible(true);
    	bottomPanel.add(requirementLabel);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(25, 26, 112, 23);
		clearButton.addActionListener(this);
		bottomPanel.add(clearButton);
	
	}
	
	public JSpinner getSpinner(JSpinner js){
		//Make the model that will be used for all estimation spinners//
		SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, 5000.0, 0.50);
		js = new JSpinner(model);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor)js.getEditor();
		DecimalFormat format = editor.getFormat();
		format.setMinimumFractionDigits(1);
		
		//Return it//
		return js;
	}
	
	
	public void buildEstimationPanel(){
		//Panel Info//
		estimationPanel = new JPanel();
		estimationPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		estimationPanel.setBounds(10, 75, 464, 441);
		estimationPanel.setLayout(null);
		
		//Labels//
		lblPlanning = new JLabel("Planning");
		lblPlanning.setBounds(50, 45, 60, 14);
		estimationPanel.add(lblPlanning);
		
		lblDesign = new JLabel("Design");
		lblDesign.setBounds(263, 45, 46, 14);
		estimationPanel.add(lblDesign);
		
		lblDesignReview = new JLabel("Design Review");
		lblDesignReview.setBounds(15, 125, 97, 14);
		estimationPanel.add(lblDesignReview);
		
		lblCode = new JLabel("Code");
		lblCode.setBounds(269, 125, 46, 14);
		estimationPanel.add(lblCode);
		
		lblCodeReview = new JLabel("Code Review");
		lblCodeReview.setBounds(23, 203, 89, 14);
		estimationPanel.add(lblCodeReview);
		
		lblCompile = new JLabel("Compile");
		lblCompile.setBounds(253, 203, 46, 14);
		estimationPanel.add(lblCompile);
		
		lblTest = new JLabel("Test");
		lblTest.setBounds(70, 278, 46, 14);
		estimationPanel.add(lblTest);
		
		lblPostmortem = new JLabel("Postmortem");
		lblPostmortem.setBounds(230, 278, 78, 14);
		estimationPanel.add(lblPostmortem);
		
		//Spinners//
		planningSpinner = getSpinner(planningSpinner);
		planningSpinner.setBounds(114, 37, 89, 30);
		planningSpinner.addChangeListener(this);
		estimationPanel.add(planningSpinner);
		
		designSpinner = getSpinner(designSpinner);
		designSpinner.setBounds(314, 37, 89, 30);
		designSpinner.addChangeListener(this);
		estimationPanel.add(designSpinner);
		
		designReviewSpinner = getSpinner(designReviewSpinner);
		designReviewSpinner.setBounds(114, 117, 89, 30);
		designReviewSpinner.addChangeListener(this);
		estimationPanel.add(designReviewSpinner);
		
		codeReviewSpinner = getSpinner(codeReviewSpinner);
		codeReviewSpinner.setBounds(114, 195, 89, 30);
		codeReviewSpinner.addChangeListener(this);
		estimationPanel.add(codeReviewSpinner);
		
		testSpinner = getSpinner(testSpinner);
		testSpinner.setBounds(114, 270, 89, 30);
		testSpinner.addChangeListener(this);
		estimationPanel.add(testSpinner);
		
		codeSpinner = getSpinner(codeSpinner);
		codeSpinner.setBounds(314, 117, 89, 30);
		codeSpinner.addChangeListener(this);
		estimationPanel.add(codeSpinner);
		
		compileSpinner = getSpinner(compileSpinner);
		compileSpinner.setBounds(314, 195, 89, 30);
		compileSpinner.addChangeListener(this);
		estimationPanel.add(compileSpinner);
		
		postmortemSpinner = getSpinner(postmortemSpinner);
		postmortemSpinner.setBounds(314, 270, 89, 30);
		postmortemSpinner.addChangeListener(this);
		estimationPanel.add(postmortemSpinner);
		
		//Total//
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(163, 375, 40, 14);
		estimationPanel.add(lblTotal);
		
		totalTextField = new JTextField("0");
		totalTextField.setBounds(206, 372, 86, 20);
		totalTextField.setEditable(false);
		totalTextField.setColumns(10);
		totalTextField.setBackground(Color.white);
		totalTextField.setOpaque(true);
		estimationPanel.add(totalTextField);
		
		//Note explaining this information is not required//
		estNote = new JLabel("Note: This information is not required for completion.");
		estNote.setBounds(10, 415, 350, 20);
		estimationPanel.add(estNote);
		
		//Set flag for build to true//
		estIsBuilt = true;
	}
	
	public void updateTotalEst(){
		totalTextField.setText("" + ((double)planningSpinner.getValue() + (double)designSpinner.getValue() + 
		(double)designReviewSpinner.getValue() + (double)codeSpinner.getValue() + (double)codeReviewSpinner.getValue() +
		(double)compileSpinner.getValue() + (double)testSpinner.getValue() + (double)postmortemSpinner.getValue())); 
	}
	
	public void buildEstimationBottomPanel(){	
		estBottomPanel = new JPanel();
		estBottomPanel.setLayout(null);
		estBottomPanel.setBounds(10, 527, 464, 73);
		estBottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		//Bottom Buttons//
		estOkButton = new JButton("Finish");
		estOkButton.setBounds(360, 26, 94, 23);
		estOkButton.addActionListener(this);
		estBottomPanel.add(estOkButton);
		
		estCancelButton = new JButton("Cancel");
		estCancelButton.setBounds(248, 26, 94, 23);
		estCancelButton.addActionListener(this);
		estBottomPanel.add(estCancelButton);
		
		estClearButton = new JButton("Clear");
		estClearButton.setBounds(127, 26, 94, 23);
		estClearButton.addActionListener(this);
		estBottomPanel.add(estClearButton);
		
		estBackButton = new JButton("Back");
		estBackButton.setBounds(10, 26, 94, 23);
		estBackButton.addActionListener(this);
		estBottomPanel.add(estBackButton);
			
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList l = (JList)e.getSource();
		selected = l.getSelectedIndex();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		//File Chooser Button//
		if(o == fileButton){
			int returnValue = fileChooser.showOpenDialog(this);
			
			if(returnValue == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				fileField.setText(file.getPath());
				lineCounter.setFileLOC(file.getPath());
			}
			
		}
		
		//Customer Check Box//
		if(o == customerChB){
			if(customerChB.isSelected()){
				customerLabel.setForeground(Color.BLACK);
				customerField.setText("");
				customerField.setForeground(Color.BLACK);
			}
			else{
				customerLabel.setForeground(Color.gray);
				customerField.setText(" Optional");
				customerField.setForeground(Color.gray);
			}
		}
		
		//End Date Check Box//
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
		
		//Calendar Button for End Date//
		if(o == edButton){
			endCalFrame.setLocation((this.getContentPane().getWidth() / 2),0);
	        endCalFrame.setSize((this.getContentPane().getWidth() / 2), 
	            this.getContentPane().getHeight());
	        this.getLayeredPane().add(endCalFrame);
	        endCalFrame.setTitle(Local.getString("End date"));
	        endCalFrame.show();
		}
		
		
		//Default Lines of Code Radio Button//
		/*
		if(o == LOCdefault){
			//Blacken the text//
			LOCdefault.setForeground(Color.black);
			
			//deselect and gray other//
			LOCanother.setForeground(Color.gray);
			
			//Disable the Field//
			LOCspinner.setForeground(Color.gray);
			LOCspinner.setEnabled(false);
			
		}
		
		//Alternate Lines Of Code Radio Button//
		if(o == LOCanother){
			LOCanother.setForeground(Color.black);
			
			//deselect and Gray the default//
			LOCdefault.setForeground(Color.gray);
			
			//Enable the Field//
			LOCspinner.setValue(0);
			LOCspinner.setEnabled(true);
			
		}
		*/
		
		//Add Team Member Button//
		if (o == addButton  && !addMemberField.getText().equals("")){
        	listModel.addElement(addMemberField.getText());
        	list = new JList(listModel);
        	addMemberField.setText("");
        }
		
		//Remove Team Member Button//
		if(o == removeButton){
			listModel.remove(selected);
			list = new JList(listModel);
		}
		
		//OK Button//
		if(o == okButton){
			
	      //Add all team members//
	        String[] teammembers = new String[list.getModel().getSize()];
	        for(int i = 0; i < list.getModel().getSize(); i++){
	        	teammembers[i] = (String)list.getModel().getElementAt(i);
	        }
	        
	        //If the data given is invalid...//
	        if(prTitleField.getText().length() == 0 || fileField.getText().length() == 0){
	        	//Show the user an error//
	        	errorLabel.setText("Required Information Not Given, Cannot Continue!");
	        	errorLabel.setVisible(true);
	        }
	        else if(teammembers.length == 0){
	        	errorLabel.setText("               Must have at least 1 team member!");
	        	errorLabel.setVisible(true);
	        }
	        else{
	        	//Change header for user understanding//
	            header.setText(Local.getString("Estimating Phases (in hours)"));
	        	
	        	//Make sure label disappears//
	        	errorLabel.setVisible(false);
	        	
	        	//Need to set invisible so JSpinners show up//
	        	centerPanel.setVisible(false);
	        	bottomPanel.setVisible(false);
	        	
	        	//Out with the OLD...//
	        	c.remove(centerPanel);
				c.remove(bottomPanel);
	        	
				//...In with the NEW//
				if(estIsBuilt == false){
					buildEstimationPanel();
		    		buildEstimationBottomPanel();
				}
				else{
					estimationPanel.setVisible(true);
					estBottomPanel.setVisible(true);
				}
				c.add(estimationPanel);
				c.add(estBottomPanel);
				
				//Repaint to show changes//
				c.repaint();
	        }
		}
		
		//Cancel Button//
		if(o == cancelButton){
			this.setVisible(false);
			this.dispose();
		}
		
		//Clear button//
		if(o == clearButton){
			//Make new Panel//
			ProjectCreationPanel pcp = new ProjectCreationPanel();
			
			//Dispose of the old one//
			this.setVisible(false);
			this.dispose();
		}
		
		if(o == estOkButton){
			//Get title and stage//
			String title = prTitleField.getText();
			
			//Get start and end dates//
			CalendarDate startD = new CalendarDate((Date) startDate.getModel().getValue());
	        CalendarDate endD = null;
	        if (endDateChB.isSelected()){
	            endD = new CalendarDate((Date) endDate.getModel().getValue());
	        }
			
	        //New Add//
			Project prj = ProjectManager.createProject(title, 
													   description.getText(), 
													   startD, 
													   endD,
													   (String) stageComboBox.getSelectedItem(),
													   (String) statusComboBox.getSelectedItem(),
													   customerField.getText(),
													   fileField.getText(),
													   (double) planningSpinner.getValue(),
													   (double) designSpinner.getValue(),
													   (double) designReviewSpinner.getValue(),
													   (double) codeSpinner.getValue(),
													   (double) codeReviewSpinner.getValue(),
													   (double) compileSpinner.getValue(),
													   (double) testSpinner.getValue(),
													   (double) postmortemSpinner.getValue());
	        
	        /*
			//Add project to manager//
			Project prj = ProjectManager.createProject(title, startD, endD);
			*/
	        
	        CurrentStorage.get().storeProjectManager();
	        
	        //Dispose of frame//
	        this.setVisible(false);
	        this.dispose();
		}
		
		if(o == estCancelButton){
			//Dispose of frame//
	        this.setVisible(false);
	        this.dispose();
		}
		
		if(o == estClearButton){
			planningSpinner.setValue(0);
			designSpinner.setValue(0);
			designReviewSpinner.setValue(0);
			codeSpinner.setValue(0);
			codeReviewSpinner.setValue(0);
			compileSpinner.setValue(0);
			testSpinner.setValue(0);
			postmortemSpinner.setValue(0);
		}
		
		if(o == estBackButton){
			//Put header back to old text//
	        header.setText(Local.getString("New Project"));
			
        	//Need to set invisible so components show up//
        	estimationPanel.setVisible(false);
        	estBottomPanel.setVisible(false);
        	
        	//Out with the NEW...//
        	c.remove(estimationPanel);
			c.remove(estBottomPanel);
        	
			//Make sure label reappears//
        	errorLabel.setVisible(true);
			
        	//Make panels visible again//
        	centerPanel.setVisible(true);
        	bottomPanel.setVisible(true);
        	
			//...In with the OLD//
			c.add(centerPanel);
			c.add(bottomPanel);
			
			
			//Repaint to show changes//
			c.repaint();
		}
		
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		Object o = e.getSource();
		
		if(o == startDate){
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
		
		if(o == endDate){
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
		
		//If any of the spinners are changed...//
		if(o == planningSpinner ||
			o == designSpinner ||
			o == designReviewSpinner ||
			o == codeSpinner ||
			o == codeReviewSpinner ||
			o == compileSpinner ||
			o == testSpinner ||
			o == postmortemSpinner){
			//...update the total//
			updateTotalEst();
		}
	
	}
}
