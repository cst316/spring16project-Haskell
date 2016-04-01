/**
 * Project.java
 * Created on 11.02.2003, 16:11:47 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import net.sf.memoranda.date.CalendarDate;

/**
 * 
 */

/*$Id: Project.java,v 1.5 2004/11/22 10:02:37 alexeya Exp $*/
public interface Project {
    
    public static final int SCHEDULED = 0;
   
    public static final int ACTIVE = 1;
    
    public static final int COMPLETED = 2;
    
    public static final int FROZEN = 4;
    
    public static final int FAILED = 5;
    
    
    
    
    String getID();
    
    /*
    public String getFilename();
    public void setFilename();
    */
    
    CalendarDate getStartDate();
    void setStartDate(CalendarDate date);
    
    CalendarDate getEndDate();
    void setEndDate(CalendarDate date);
    
    String getTitle();
    void setTitle(String title);
    
    void setDescription(String description);
    String getDescription();
    
    //New getters/setters//
    void setStage(String stage);
    String getStage();
    
    void setPriority(String priority);
    String getPriority();
    
    void setCustomer(String customer);
    String getCustomer();
    
    void setFile(String file);
    String getFile();
    
    /*
    void setTeam(String[] team);
    String[] getTeam();
    */
    
    void setPlanningEst(double plan);
    String getPlanningEst();
    
    void setDesignEst(double design);
    String getDesignEst();
    
    void setDesignReviewEst(double designRev);
    String getDesignReviewEst();
    
    void setCodeEst(double code);
    String getCodeEst();
    
    void setCodeReviewEst(double codeRev);
    String getCodeReviewEst();
    
    void setCompileEst(double compile);
    String getCompileEst();
    
    void setTestEst(double test);
    String getTestEst();
    
    void setPostmortemEst(double post);
    String getPostmortemEst();
    //Up to here//
    
    int getStatus();
            
    //int getProgress();
    
    //TaskList getTaskList();
    
    //NoteList getNoteList();
    
    //ResourcesList getResourcesList();
    
    void freeze();
    void unfreeze();  
    
}
