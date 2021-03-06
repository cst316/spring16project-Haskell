package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//update
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
//import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JCheckBox;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;

/*$Id: TaskDialog.java,v 1.25 2005/12/01 08:12:26 alexeya Exp $*/

/**
 * 
 * @custom Duncan McQuarrie
 * 
 * @version 1.5
 * This is the TasDialog class, that handles the GUI elements for the 
 * Task Dialog window
 *
 */
public class TaskDialog extends JDialog {
   	private static final long serialVersionUID = -62365071932024344L;
	JPanel mPanel = new JPanel(new BorderLayout());
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton cancelB = new JButton();
    JButton okB = new JButton();
    Border border1;
    Border border2;
    JPanel dialogTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel header = new JLabel();
    public boolean CANCELLED = true;
    JPanel jPanel8 = new JPanel(new GridBagLayout());
    Border border3;
    Border border4;
//    Border border5;
//    Border border6;
    JPanel jPanel2 = new JPanel(new GridLayout(3, 2));
    JTextField todoField = new JTextField();
    
    // added by rawsushi
    JTextField effortField = new JTextField();
    JTextArea descriptionField = new JTextArea();
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionField);
    
//    Border border7;
    Border border8;
    CalendarFrame startCalFrame = new CalendarFrame();
    CalendarFrame endCalFrame = new CalendarFrame();
    String[] priority = {Local.getString("Lowest"), Local.getString("Low"),
        Local.getString("Normal"), Local.getString("High"),
        Local.getString("Highest")};
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
    JPanel jPanel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel jPanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel jLabel6 = new JLabel();
    JButton setStartDateB = new JButton();
    JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel jLabel2 = new JLabel();
    JSpinner startDate;
    JSpinner endDate;
//    JSpinner endDate = new JSpinner(new SpinnerDateModel());
    JButton setEndDateB = new JButton();
    //JPanel jPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel jPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel jPanelEffort = new JPanel(new FlowLayout(FlowLayout.LEFT));
//    JPanel jPanelNotes = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    JButton setNotifB = new JButton();
    JComboBox<?> priorityCB = new JComboBox<Object>(priority);
    JLabel jLabel7 = new JLabel();
    // added by rawsushi
    JLabel jLabelEffort = new JLabel();
    JLabel jLabelDescription = new JLabel();
	JCheckBox chkEndDate = new JCheckBox();
	
	JPanel jPanelProgress = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JLabel jLabelProgress = new JLabel();
	JSpinner progress = new JSpinner(new SpinnerNumberModel(0, 0, 100, 5));
	
	//Forbid to set dates outside the bounds
	CalendarDate startDateMin = CurrentProject.get().getStartDate();
	CalendarDate startDateMax = CurrentProject.get().getEndDate();
	CalendarDate endDateMin = startDateMin;
	CalendarDate endDateMax = startDateMax;
	
	//Changes made to original project 
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel generalTab = new JPanel();
	private final JPanel estimationTab = new JPanel(new GridLayout(8, 2, 0, 0));
	private final JPanel designEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblDesign = new JLabel("Design");
	private JSpinner designSpinner;
	private final JPanel planningEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblPlanning = new JLabel("Planning");
	private JSpinner planningSpinner;
	private final JPanel designRevEsT = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblDesignReview = new JLabel("Design Review");
	private JSpinner designReviewSpinner;
	private final JPanel codeEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblCode = new JLabel("Code");
	private JSpinner codeSpinner = new JSpinner();
	private final JPanel codeReviewEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblCodeReview = new JLabel("Code Review");
	private JSpinner codeReviewSpinner = new JSpinner();
	private final JPanel compileEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblCompile = new JLabel("Compile");
	private JSpinner compileSpinner = new JSpinner();
	private final JPanel testEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblTest = new JLabel("Test");
	private JSpinner testSpinner = new JSpinner();
	private final JPanel postmortemEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblPostmortem = new JLabel("Postmortem");
	private JSpinner postmortemSpinner = new JSpinner();
	private final JPanel totalEst = new JPanel(new GridLayout(0, 2, 0, 0));
	private final JLabel lblTotal = new JLabel("Total Est(hrs)");
	private final JFormattedTextField totalEstTextField = new JFormattedTextField();
	//components added 2/17/16
	private final JPanel timerTab = new JPanel();
	private final JLabel lblStartTime = new JLabel("Start Time");
	private final JButton btnStart = new JButton("Start");
	private final JFormattedTextField startTextField = new JFormattedTextField(); ;
	private final JLabel lblEndTime = new JLabel("End Time");
	private final JButton btnEnd = new JButton("End");
	private final JFormattedTextField endTextField = new JFormattedTextField();
	private final JLabel lblSession = new JLabel("Session Time");
	private final JFormattedTextField sessionTextField = new JFormattedTextField();
	private long startTime;
	private long endTime;
	private long initialTime;
	private long finalTime;
	private String fileLocation = Util.getEnvDir();
	//defect tab components
	private final JPanel defectTab = new JPanel();
	private JTable defectTable;
	private final JLabel lblClass = new JLabel("Class");
	private final JTextField classField = new JTextField();
	private final JLabel lblLine = new JLabel("Line #");
	private final JTextField lineNumField = new JTextField();
	private final JLabel lblType = new JLabel("Type");
	private int numDefects;
	private DefaultTableModel model = new DefaultTableModel();
	
	/**
	 * Constructor for class TasK Dialog
	 * super from JDialog
	 * @param frame - frame with contents to be displayed
	 * @param title - string title of the taskDialog window
	 */
    public TaskDialog(Frame frame, String title) {
        super(frame, title, true);
        lineNumField.setText("");
        lineNumField.setBounds(136, 39, 86, 20);
        lineNumField.setColumns(10);
        classField.setBounds(10, 39, 86, 20);
        classField.setColumns(10);
       
        try {
            jbInit();            
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    
    /**
     * Handles initialization of JFrame objects and components
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	void jbInit() throws Exception {
    	Dimension dim = new Dimension(846, 532);
    	this.setPreferredSize(dim);
        border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        border2 = BorderFactory.createEtchedBorder(Color.white, 
            new Color(142, 142, 142));
        border3 = new TitledBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0), 
        Local.getString("Task Name"), TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border4 = BorderFactory.createEmptyBorder(0, 5, 0, 5);
//        border5 = BorderFactory.createEmptyBorder();
//        border6 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
//            Color.white, Color.white, new Color(178, 178, 178),
//            new Color(124, 124, 124));
//        border7 = BorderFactory.createLineBorder(Color.white, 2);
        border8 = BorderFactory.createEtchedBorder(Color.white, 
            new Color(178, 178, 178));
		chkEndDate_actionPerformed(null);
        mPanel.setBorder(border1);
        dialogTitlePanel.setBackground(Color.WHITE);
        dialogTitlePanel.setBorder(border4);
        //dialogTitlePanel.setMinimumSize(new Dimension(159, 52));
        //dialogTitlePanel.setPreferredSize(new Dimension(159, 52));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("To do"));
        header.setIcon(new ImageIcon(net.sf.memoranda.ui.TaskDialog.class.getResource(
            "resources/icons/task48.png")));
        GridBagConstraints gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 1;
        gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 1;
        gbCon.anchor = GridBagConstraints.WEST;
        gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 3;
		SimpleDateFormat sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
        getContentPane().add(mPanel);
        
                startDate = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
                endDate = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
        chkEndDate.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		chkEndDate_actionPerformed(e);
        	}
        });
        cancelB.setMaximumSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setText(Local.getString("Cancel"));
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText(Local.getString("Ok"));
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okB_actionPerformed(e);
            }
        });
        
        this.getRootPane().setDefaultButton(okB);
        mPanel.add(buttonsPanel, BorderLayout.NORTH);
        buttonsPanel.add(okB, null);
        buttonsPanel.add(cancelB, null);
        mPanel.add(tabbedPane, BorderLayout.SOUTH);
        tabbedPane.addTab("General", null, generalTab, null);
        generalTab.setBorder(border2);
                
                        jLabelEffort.setMaximumSize(new Dimension(100, 16));
                        jLabelEffort.setMinimumSize(new Dimension(60, 16));
                        jLabelEffort.setText(Local.getString("Est Effort(hrs)"));
                        effortField.setBorder(border8);
                        effortField.setPreferredSize(new Dimension(30, 24));
                        
                                startDate.setBorder(border8);
                                startDate.setPreferredSize(new Dimension(80, 24));                
                                // //Added by (jcscoobyrs) on 14-Nov-2003 at 10:45:16 PM
                                startDate.setEditor(new JSpinner.DateEditor(startDate, sdf.toPattern()));
                                
                                        startDate.addChangeListener(new ChangeListener() {
                                            public void stateChanged(ChangeEvent e) {
                                            	// it's an ugly hack so that the spinner can increase day by day
                                            	SpinnerDateModel sdm = new SpinnerDateModel((Date)startDate.getModel().getValue(),null,null,Calendar.DAY_OF_WEEK);
                                            	startDate.setModel(sdm);
                                
                                                if (ignoreStartChanged)
                                                    return;
                                                ignoreStartChanged = true;
                                                Date sd = (Date) startDate.getModel().getValue();
                                                Date ed = (Date) endDate.getModel().getValue();
                                                if (sd.after(ed) && chkEndDate.isSelected()) {
                                                    startDate.getModel().setValue(ed);
                                                    sd = ed;
                                                }
                                				if ((startDateMax != null) && sd.after(startDateMax.getDate())) {
                                					startDate.getModel().setValue(startDateMax.getDate());
                                                    sd = startDateMax.getDate();
                                				}
                                                if ((startDateMin != null) && sd.before(startDateMin.getDate())) {
                                                    startDate.getModel().setValue(startDateMin.getDate());
                                                    sd = startDateMin.getDate();
                                                }
                                                startCalFrame.cal.set(new CalendarDate(sd));
                                                ignoreStartChanged = false;
                                            }
                                        });
                                        
                                                jLabel6.setText(Local.getString("Start date"));
                                                //jLabel6.setPreferredSize(new Dimension(60, 16));
                                                jLabel6.setMinimumSize(new Dimension(60, 16));
                                                jLabel6.setMaximumSize(new Dimension(100, 16));
                                                setStartDateB.setMinimumSize(new Dimension(24, 24));
                                                setStartDateB.setPreferredSize(new Dimension(24, 24));
                                                setStartDateB.setText("");
                                                setStartDateB.setIcon(
                                                    new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/calendar.png")));
                                                setStartDateB.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        setStartDateB_actionPerformed(e);
                                                    }
                                                });
                                                jLabel2.setMaximumSize(new Dimension(270, 16));
                                                //jLabel2.setPreferredSize(new Dimension(60, 16));
                                                jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
                                                jLabel2.setText(Local.getString("End date"));
                                                endDate.setBorder(border8);
                                                endDate.setPreferredSize(new Dimension(80, 24));
                                                
		endDate.setEditor(new JSpinner.DateEditor(endDate, sdf.toPattern())); //Added by (jcscoobyrs) on
		//14-Nov-2003 at 10:45:16PM
        
        endDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	// it's an ugly hack so that the spinner can increase day by day
            	SpinnerDateModel sdm = new SpinnerDateModel((Date)endDate.getModel().getValue(),null,null,Calendar.DAY_OF_WEEK);
            	endDate.setModel(sdm);
            	
                if (ignoreEndChanged)
                    return;
                ignoreEndChanged = true;
                Date sd = (Date) startDate.getModel().getValue();
                Date ed = (Date) endDate.getModel().getValue();				
				if (ed.before(sd)) {
                    endDate.getModel().setValue(ed);
                    ed = sd;
                }
				if ((endDateMax != null) && ed.after(endDateMax.getDate())) {
					endDate.getModel().setValue(endDateMax.getDate());
                    ed = endDateMax.getDate();
				}
                if ((endDateMin != null) && ed.before(endDateMin.getDate())) {
                    endDate.getModel().setValue(endDateMin.getDate());
                    ed = endDateMin.getDate();
                }
				endCalFrame.cal.set(new CalendarDate(ed));
                ignoreEndChanged = false;
            }
        });
        setEndDateB.setMinimumSize(new Dimension(24, 24));
        setEndDateB.setPreferredSize(new Dimension(24, 24));
        setEndDateB.setText("");
        setEndDateB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/calendar.png")));
        setEndDateB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEndDateB_actionPerformed(e);
            }
        });
        
        setNotifB.setText(Local.getString("Set notification"));
        setNotifB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/notify.png")));
        setNotifB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setNotifB_actionPerformed(e);
            }
        });
        jLabel7.setMaximumSize(new Dimension(100, 16));
        jLabel7.setMinimumSize(new Dimension(60, 16));
        //jLabel7.setPreferredSize(new Dimension(60, 16));
        jLabel7.setText(Local.getString("Priority"));
        
                priorityCB.setFont(new java.awt.Font("Dialog", 0, 11));
                jPanel4.add(jLabel7, null);
                generalTab.setLayout(new GridLayout(0, 1, 0, 0));
                
                GridBagLayout gbLayout = (GridBagLayout) jPanel8.getLayout();
                jPanel8.setBorder(border3);
                
                todoField.setBorder(border8);
                todoField.setPreferredSize(new Dimension(375, 24));
                gbLayout.setConstraints(todoField,gbCon);
                
                jLabelDescription.setMaximumSize(new Dimension(100, 16));
                jLabelDescription.setMinimumSize(new Dimension(60, 16));
                jLabelDescription.setText(Local.getString("Description"));
                gbLayout.setConstraints(jLabelDescription,gbCon);
                
                        descriptionField.setBorder(border8);
                        descriptionField.setPreferredSize(new Dimension(375, 387)); // 3 additional pixels from 384 so that the last line is not cut off
                        descriptionField.setLineWrap(true);
                        descriptionField.setWrapStyleWord(true);
                        descriptionScrollPane.setPreferredSize(new Dimension(375,96));
                        gbLayout.setConstraints(descriptionScrollPane,gbCon);
                        generalTab.add(jPanel8);
                        jPanel8.add(todoField, null);
                        jPanel8.add(jLabelDescription);
                        jPanel8.add(descriptionScrollPane, null);
                generalTab.add(jPanel2);
                jPanel2.add(jPanel6, null);
                jPanel6.add(jLabel6, null);
                jPanel6.add(startDate, null);
                jPanel6.add(setStartDateB, null);
                jPanel2.add(jPanel1, null);
                jPanel1.add(chkEndDate, null);
                jPanel1.add(jLabel2, null);
                jPanel1.add(endDate, null);
                jPanel1.add(setEndDateB, null);
                // added by rawsushi
                jPanel2.add(jPanelEffort, null);
                jPanelEffort.add(jLabelEffort, null);
                jPanelEffort.add(effortField, null);
                
                        jPanel2.add(jPanel4, null);
                        jPanel4.add(priorityCB, null);
                        jPanel2.add(jPanel3, null);
                        
                        jPanel3.add(setNotifB, null);
                        
                        jLabelProgress.setText(Local.getString("Progress"));
                        jPanelProgress.add(jLabelProgress, null);
                        jPanelProgress.add(progress, null);
                        jPanel2.add(jPanelProgress);
                        
                        priorityCB.setSelectedItem(Local.getString("Normal"));
                        
                        //Refactored code. Replaced large chunks in initialization
                        //process with these methods. Allows for each individual tab to be
                        //refactored separately
                        phaseTabInit();
                        
                        timerTabInit();

                        defectTabInit();
                 
        this.getContentPane().add(dialogTitlePanel, BorderLayout.NORTH);
        dialogTitlePanel.add(header, null);
        startCalFrame.cal.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ignoreStartChanged)
                    return;
                startDate.getModel().setValue(startCalFrame.cal.get().getCalendar().getTime());
            }
        });
        
        endCalFrame.cal.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ignoreEndChanged)
                    return;
                endDate.getModel().setValue(endCalFrame.cal.get().getCalendar().getTime());
            }
        });
    }
    
    /**
     * @author Duncan MCQuarrie
     * 
     * this method handles defectTab initialization
     * called in JbInit()
     */
    private void defectTabInit() {
    	tabbedPane.addTab("Defect Log", null, defectTab, null);
        defectTab.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 795, 81);
        defectTab.add(panel);
        panel.setLayout(null);
        lblClass.setBounds(10, 11, 46, 14);
        
        panel.add(lblClass);
        
        panel.add(classField);
        lblLine.setBounds(136, 11, 46, 14);
        
        panel.add(lblLine);
        
        panel.add(lineNumField);
        lblType.setBounds(261, 11, 46, 14);
        
        panel.add(lblType);
        
        JComboBox typeComboBox = new JComboBox();
        typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "UI", "Error Handling", "Syntax", "Control Flow"}));
        typeComboBox.setBounds(261, 39, 100, 20);
        panel.add(typeComboBox);
        
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(378, 11, 46, 14);
        panel.add(lblStatus);
        
        JComboBox statusComboBox = new JComboBox();
        statusComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Open", "In Progress", "Closed"}));
        statusComboBox.setBounds(378, 39, 100, 20);
        panel.add(statusComboBox);
        numDefects = 0;		//Used to track how many defects have been added to the table
        JButton btnAdd = new JButton("Add");
        btnAdd.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int initialRows = 10;
        		int dateCol = 0;	//Integers mapped to column locations on table
                int classCol = 1;
                int lineCol = 2;
                int typeCol = 3;
                int statusCol = 4;
                
                //Takes values form GUI fields and inserts them into the jTable
        		String defectDate = new SimpleDateFormat("MM.dd.YYYY").format(new Date());
        		defectTable.setValueAt(defectDate, numDefects , dateCol);
        		defectTable.setValueAt(classField.getText(), numDefects, classCol);
        		defectTable.setValueAt(lineNumField.getText(), numDefects, lineCol);
        		defectTable.setValueAt(typeComboBox.getSelectedItem(), numDefects, typeCol);
        		defectTable.setValueAt(statusComboBox.getSelectedItem(), numDefects, statusCol);
        		
        		++numDefects;			//increments defect count
        		
        		if (numDefects >= initialRows)
        			model.addRow(new Object[]{null,null,null,null,null});
        	}
        });
        btnAdd.setBounds(523, 38, 89, 23);
        panel.add(btnAdd);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        scrollPane.setBounds(10, 103, 795, 148);
        defectTab.add(scrollPane);
        
        numDefects = 0;		//Used to track how many defects have been added to the table
        defectTable = new JTable();
        model = new DefaultTableModel(
            	new Object[][] {
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null},
            		{null, null, null, null, null}}, new String[] {"Date", "Class", "Line #", "Type", "Status"}
            );
        defectTable.setModel(model);
        scrollPane.setViewportView(defectTable);
		
	}
    
    /**
     * @author Duncan McQuarrie
     * This method handles initialization of timerTab components
     * called in jbInit()
     */
    private void timerTabInit() {
    	 tabbedPane.addTab("Timer", null, timerTab, null);
         timerTab.setLayout(null);
         
         
         lblStartTime.setBounds(10, 23, 60, 14);
         timerTab.add(lblStartTime);
         startTextField.setBackground(Color.WHITE);
         startTextField.setEditable(false);
         
         startTextField.setBounds(106, 20, 90, 20);
         timerTab.add(startTextField);
         btnStart.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mousePressed(MouseEvent e) {
         		startTime = System.currentTimeMillis();
         		initialTime= System.nanoTime(); 
         		
         		startTextField.setText(convertMillitoHMS(startTime));
         	}
         });
         
         
         btnStart.setBounds(10, 48, 89, 23);
         timerTab.add(btnStart);
         
         
         lblEndTime.setBounds(10, 109, 55, 14);
         timerTab.add(lblEndTime);
         
         btnEnd.setBounds(10, 134, 89, 23);
         timerTab.add(btnEnd);
         btnEnd.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mousePressed(MouseEvent e) {
         		endTime = System.currentTimeMillis();
         		
         		endTextField.setText(convertMillitoHMS(endTime));;
         		
         		long sessionTime = System.nanoTime() - initialTime;
         		sessionTime = (long) (sessionTime / 1000000.0);				//converts nanoseconds to milliseconds
         		
         		String sessionString = convertTimertoHMS(sessionTime);
         		
         		try {
         			Writer output = new BufferedWriter(new FileWriter(fileLocation + "times.txt", true));
             		output.append("Date: " + new SimpleDateFormat("MM-dd-yy").format(new Date()) + " Start Time: " + convertMillitoHMS(startTime)
             				+ " End Time: " + convertMillitoHMS(endTime) + " Time Passed: " + sessionString + "\n");
             		output.close();
             		
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
         		
         		sessionTextField.setText(sessionString);

         	}
         });
         endTextField.setBackground(Color.WHITE);
         
       
         endTextField.setEditable(false);
         endTextField.setBounds(106, 106, 90, 20);
         timerTab.add(endTextField);
         lblSession.setBounds(10, 181, 80, 14);
         
         timerTab.add(lblSession);
         sessionTextField.setBackground(Color.WHITE);
         sessionTextField.setEditable(false);
         sessionTextField.setBounds(10, 206, 90, 20);
         
         timerTab.add(sessionTextField);
		
	}
    
    /**
     * @author Duncan McQuarrie
     * This method handles initialization of phase estimation tab components
     */
	private void phaseTabInit() {
    	tabbedPane.addTab("Phase Estimation", null, estimationTab, null);
        GridLayout layout = new GridLayout(0, 2, 0, 0);
        //estimationTab.setLayout(layout);
        
        estimationTab.add(planningEst);
        //planningEst.setLayout(layout);
        lblPlanning.setHorizontalAlignment(SwingConstants.CENTER);
        
        planningEst.add(lblPlanning);               
        planningSpinner = getSpinner(planningSpinner); 
        planningEst.add(planningSpinner);
        planningSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) { 
        		updateTotalEst();
        	}
       	});
        
        estimationTab.add(designEst);
        //designEst.setLayout(layout);
        lblDesign.setHorizontalAlignment(SwingConstants.CENTER);
        
        designEst.add(lblDesign);
        designSpinner = getSpinner(designSpinner);
        designEst.add(designSpinner);
        designSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) { 
        		updateTotalEst(); 
        	}
        });
        
        estimationTab.add(designRevEsT);
        //designRevEsT.setLayout(layout);
        lblDesignReview.setHorizontalAlignment(SwingConstants.CENTER);
        
        designRevEsT.add(lblDesignReview);
        designReviewSpinner = getSpinner(designReviewSpinner);
        designRevEsT.add(designReviewSpinner);
        designReviewSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) { 
        		updateTotalEst(); 
        	}
        });
        
        estimationTab.add(codeEst);
        //codeEst.setLayout(layout);
        lblCode.setHorizontalAlignment(SwingConstants.CENTER);
        
        codeEst.add(lblCode);
        codeSpinner = getSpinner(codeSpinner);
        codeEst.add(codeSpinner);
        codeSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) { 
        		updateTotalEst(); 
        	}
        });
        
        estimationTab.add(codeReviewEst);
        //codeReviewEst.setLayout(layout);
        lblCodeReview.setHorizontalAlignment(SwingConstants.CENTER);
        
        codeReviewEst.add(lblCodeReview);
        codeReviewSpinner = getSpinner(codeReviewSpinner);
        codeReviewEst.add(codeReviewSpinner);
        codeReviewSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) { 
        		updateTotalEst(); 
        	}
        });
        
        estimationTab.add(compileEst);
        //compileEst.setLayout(layout);
        lblCompile.setHorizontalAlignment(SwingConstants.CENTER);
        
        compileEst.add(lblCompile);                       
        compileSpinner = getSpinner(compileSpinner);
        compileEst.add(compileSpinner);
        compileSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) { 
        		updateTotalEst();
        	}
        });
        
        estimationTab.add(testEst);
        //testEst.setLayout(layout);
        lblTest.setHorizontalAlignment(SwingConstants.CENTER);
        
        testEst.add(lblTest);
        testSpinner = getSpinner(testSpinner);
        testEst.add(testSpinner);
        testSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) { 
        		updateTotalEst();
        	}
        });
        
        estimationTab.add(postmortemEst);
        //postmortemEst.setLayout(layout);
        lblPostmortem.setHorizontalAlignment(SwingConstants.CENTER);
        
        postmortemEst.add(lblPostmortem);     
        postmortemSpinner = getSpinner(postmortemSpinner);
        postmortemEst.add(postmortemSpinner);
        postmortemSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		updateTotalEst();
        	}
        });
        
        
        estimationTab.add(totalEst);
        //totalEst.setLayout(layout);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        
        totalEst.add(lblTotal);
        totalEstTextField.setEditable(false);
        
        totalEst.add(totalEstTextField);
		
	}
	
	/**
	 * @author Zachary Jaros
	 * 
	 * This method takes a JSpinner js and sets it to the model
	 * (double, min 0, max 5000, incr 0.5)
	 * @param js - jspinner object
	 * @return js - returns the same jSpinner but with a new model
	 */
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
    
    /**
     * @author Duncan McQuarrie
     * This method takes an time input in milliseconds, and converts it
     * to a time formatted in hours:minutes:seconds
     * 
     * @param time - the time in milliseconds after UTC
     * @return formattedTime - a string that displays time in Hours:Minutes:Seconds
     */
    protected String convertMillitoHMS(long time) {
		Date timeInFormat = new Date(time);
		SimpleDateFormat formatDef = new SimpleDateFormat("h:mm:s");
		String formattedTime = formatDef.format(timeInFormat);
		return formattedTime;
		
		
	}
    /**
     * @author Chris Young
     * There was a bug where elapsed time would always have a 5 hour time displayed
     * This is the easiest way to fix the 5 hour error
     * 
     * @param time - millisecond time value
     * @return - time formatted in hours:minutes:seconds
     */
    protected String convertTimertoHMS(long time) {
    	int seconds = (int) (time / 1000) % 60 ;
    	int minutes = (int) ((time / (1000*60)) % 60);
    	int hours   = (int) ((time / (1000*60*60)) % 24);
    	return (hours + ":" + minutes + ":" + seconds);
	}
    
    /**
     * @author Duncan McQuarrie
     * Method called in event handlers for phase estimate spinners
     * each time a spinner is incremented, the total of all spinners
     * is updated
     */
	public void updateTotalEst(){
        		totalEstTextField.setText("" + ((double)planningSpinner.getValue() + (double)designSpinner.getValue() + 
        		(double)designReviewSpinner.getValue() + (double)codeSpinner.getValue() + (double)codeReviewSpinner.getValue() +
        		(double)compileSpinner.getValue() + (double)testSpinner.getValue() + (double)postmortemSpinner.getValue())); 
        	}

	public void setStartDate(CalendarDate d) {
		this.startDate.getModel().setValue(d.getDate());
	}
	
	public void setEndDate(CalendarDate d) {		
		if (d != null) 
			this.endDate.getModel().setValue(d.getDate());
	}
	
	public void setStartDateLimit(CalendarDate min, CalendarDate max) {
		this.startDateMin = min;
		this.startDateMax = max;
	}
	//rand
	public void setEndDateLimit(CalendarDate min, CalendarDate max) {
		this.endDateMin = min;
		this.endDateMax = max;
	}
	
    void okB_actionPerformed(ActionEvent e) {
	CANCELLED = false;
        this.dispose();
    }

    void cancelB_actionPerformed(ActionEvent e) {
        this.dispose();
    }
	
	void chkEndDate_actionPerformed(ActionEvent e) {
		//endDate.setEnabled(chkEndDate.isSelected());
		setEndDateB.setEnabled(chkEndDate.isSelected());
		jLabel2.setEnabled(chkEndDate.isSelected());
		if(chkEndDate.isSelected()) {
			Date currentEndDate = (Date) endDate.getModel().getValue();
			Date currentStartDate = (Date) startDate.getModel().getValue();
			if(currentEndDate.getTime() < currentStartDate.getTime()) {
				endDate.getModel().setValue(currentStartDate);
			}
		}
	}

    void setStartDateB_actionPerformed(ActionEvent e) {
        startCalFrame.setLocation(setStartDateB.getLocation());
        startCalFrame.setSize(200, 200);
        this.getLayeredPane().add(startCalFrame);
        startCalFrame.show();

    }

    void setEndDateB_actionPerformed(ActionEvent e) {
        endCalFrame.setLocation(setEndDateB.getLocation());
        endCalFrame.setSize(200, 200);
        this.getLayeredPane().add(endCalFrame);
        endCalFrame.show();
    }
    
    void setNotifB_actionPerformed(ActionEvent e) {
    	((AppFrame)App.getFrame()).workPanel.dailyItemsPanel.eventsPanel.newEventB_actionPerformed(e, 
			this.todoField.getText(), (Date)startDate.getModel().getValue(),(Date)endDate.getModel().getValue());
    }
    
    /**
     * @author Trent Ferree
     * 
     * Method sets the tab position using tabNumber
     * @param tabNumber - integer representing position on the tabbed pane
     */
    void setTab(int tabNumber){
    	tabbedPane.setSelectedIndex(tabNumber);
    }
}