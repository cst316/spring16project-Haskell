/**
 * ProjectImpl.java
 * Created on 11.02.2003, 23:06:22 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * Default implementation of Project interface
 */
/*$Id: ProjectImpl.java,v 1.7 2004/11/22 10:02:37 alexeya Exp $*/
public class ProjectImpl implements Project {

    private Element _root = null;
    

    /**
     * Constructor for ProjectImpl.
     */
    public ProjectImpl(Element root) {        
        _root = root;
    }
    
    /*
    public String getFilename(){
    	return filename;
    }
    
    public void setFilename(String f){
    	filename = f;
    }
    */
    
    /**
     * @see net.sf.memoranda.Project#getID()
     */
    public String getID() {
        return _root.getAttribute("id").getValue();
    }

    /**
     * @see net.sf.memoranda.Project#getStartDate()
     */
    public CalendarDate getStartDate() {
        Attribute d = _root.getAttribute("startDate");
        if (d == null) return null;
        return new CalendarDate(d.getValue());        
    }

    /**
     * @see net.sf.memoranda.Project#setStartDate(net.sf.memoranda.util.CalendarDate)
     */
    public void setStartDate(CalendarDate date) {
        if (date != null)
            setAttr("startDate", date.toString());
    }

    /**
     * @see net.sf.memoranda.Project#getEndDate()
     */
    public CalendarDate getEndDate() {
        Attribute d = _root.getAttribute("endDate");
        if (d == null) return null;
        return new CalendarDate(d.getValue());
    }

    /**
     * @see net.sf.memoranda.Project#setEndDate(net.sf.memoranda.util.CalendarDate)
     */
    public void setEndDate(CalendarDate date) {
        if (date != null)
            setAttr("endDate", date.toString());
        else if (_root.getAttribute("endDate") != null)
            setAttr("endDate", null);
    }

    /**
     * @see net.sf.memoranda.Project#getStatus()
     */
    public int getStatus() {
        if (isFrozen())
            return Project.FROZEN;
        CalendarDate today = CurrentDate.get();
        CalendarDate prStart = getStartDate();
        CalendarDate prEnd = getEndDate();
        if (prEnd == null) {
            if (today.before(prStart))
                return Project.SCHEDULED;
            else
                return Project.ACTIVE;                
        }    
        if (today.inPeriod(prStart, prEnd))
            return Project.ACTIVE;
        else if (today.after(prEnd)) {
            //if (getProgress() == 100)
                return Project.COMPLETED;
            /*else
                return Project.FAILED;*/
        }
        else
            return Project.SCHEDULED;
    }

    private boolean isFrozen() {
        return _root.getAttribute("frozen") != null;
    }

   
    /*public int getProgress() {
        Vector v = getAllTasks();
        if (v.size() == 0) return 0;
        int p = 0;
        for (Enumeration en = v.elements(); en.hasMoreElements();) {
          Task t = (Task) en.nextElement();
          p += t.getProgress();
        }
        return (p*100)/(v.size()*100);
    }*/
  
    
    /**
     * @see net.sf.memoranda.Project#freeze()
     */
    public void freeze() {
        _root.addAttribute(new Attribute("frozen", "yes"));
    }

    /**
     * @see net.sf.memoranda.Project#unfreeze()
     */
    public void unfreeze() {
        if (this.isFrozen())
            _root.removeAttribute(new Attribute("frozen", "yes"));
    }
    
    /**
     * @see net.sf.memoranda.Project#getTitle()
     */
    public String getTitle() {
        Attribute ta = _root.getAttribute("title");
        if (ta != null)
            return ta.getValue();
        return "";
    }
    /**
     * @see net.sf.memoranda.Project#setTitle(java.lang.String)
     */
    public void setTitle(String title) {
        setAttr("title", title);
    }
    
    private void setAttr(String name, String value) {
        Attribute a = _root.getAttribute(name);
        if (a == null) {
            if (value != null)
             _root.addAttribute(new Attribute(name, value));
        }
        else if (value != null)        
            a.setValue(value);
        else 
            _root.removeAttribute(a);
    }

	public String getDescription() {
    	Element thisElement = _root.getFirstChildElement("description");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setDescription(String s) {
    	Element desc = _root.getFirstChildElement("description");
    	if (desc == null) {
        	desc = new Element("description");
            desc.appendChild(s);
            _root.appendChild(desc);    	
    	}
    	else {
            desc.removeChildren();
            desc.appendChild(s);    	
    	}
    }
    
    public String getStage() {
    	Element thisElement = _root.getFirstChildElement("stage");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setStage(String s) {
    	Element stage = _root.getFirstChildElement("stage");
    	if (stage == null) {
    		stage = new Element("stage");
    		stage.appendChild(s);
            _root.appendChild(stage);    	
    	}
    	else {
    		stage.removeChildren();
    		stage.appendChild(s);    	
    	}
    }
    
    public String getPriority() {
    	Element thisElement = _root.getFirstChildElement("priority");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setPriority(String s) {
    	Element priority = _root.getFirstChildElement("priority");
    	if (priority == null) {
    		priority = new Element("priority");
    		priority.appendChild(s);
            _root.appendChild(priority);    	
    	}
    	else {
    		priority.removeChildren();
    		priority.appendChild(s);    	
    	}
    }
    
    public String getCustomer() {
    	Element thisElement = _root.getFirstChildElement("customer");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setCustomer(String s) {
    	Element customer = _root.getFirstChildElement("customer");
    	if (customer == null) {
    		customer = new Element("customer");
    		customer.appendChild(s);
            _root.appendChild(customer);    	
    	}
    	else {
    		customer.removeChildren();
    		customer.appendChild(s);    	
    	}
    }
    
    public String getFile() {
    	Element thisElement = _root.getFirstChildElement("file");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setFile(String s) {
    	Element file = _root.getFirstChildElement("file");
    	if (file == null) {
    		file = new Element("file");
    		file.appendChild(s);
            _root.appendChild(file);    	
    	}
    	else {
    		file.removeChildren();
    		file.appendChild(s);    	
    	}
    }
    
    
    
    
    public String getTeam() {
    	Element thisElement = _root.getFirstChildElement("team");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setTeam(String s) {
    	Element team = _root.getFirstChildElement("team");
    	if (team == null) {
    		team = new Element("team");
    		team.appendChild(s);
            _root.appendChild(team);    	
    	}
    	else {
    		team.removeChildren();
    		team.appendChild(s);    	
    	}
    }
    
    
    public String getPlanningEst() {
    	Element thisElement = _root.getFirstChildElement("planningEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setPlanningEst(double s) {
    	Element plan = _root.getFirstChildElement("planningEst");
    	if (plan == null) {
    		plan = new Element("planningEst");
    		plan.appendChild(s + "");
            _root.appendChild(plan);    	
    	}
    	else {
    		plan.removeChildren();
    		plan.appendChild(s + "");    	
    	}
    }
    
    public String getDesignEst() {
    	Element thisElement = _root.getFirstChildElement("designEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setDesignEst(double s) {
    	Element design = _root.getFirstChildElement("designEst");
    	if (design == null) {
    		design = new Element("designEst");
    		design.appendChild(s + "");
            _root.appendChild(design);    	
    	}
    	else {
    		design.removeChildren();
    		design.appendChild(s + "");    	
    	}
    }
    
    public String getDesignReviewEst() {
    	Element thisElement = _root.getFirstChildElement("designRevEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setDesignReviewEst(double s) {
    	Element designRev = _root.getFirstChildElement("designRevEst");
    	if (designRev == null) {
    		designRev = new Element("designRevEst");
    		designRev.appendChild(s + "");
            _root.appendChild(designRev);    	
    	}
    	else {
    		designRev.removeChildren();
    		designRev.appendChild(s + "");    	
    	}
    }
    
    public String getCodeEst() {
    	Element thisElement = _root.getFirstChildElement("codeEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setCodeEst(double s) {
    	Element code = _root.getFirstChildElement("codeEst");
    	if (code == null) {
    		code = new Element("codeEst");
    		code.appendChild(s + "");
            _root.appendChild(code);    	
    	}
    	else {
    		code.removeChildren();
    		code.appendChild(s + "");    	
    	}
    }
    
    public String getCodeReviewEst() {
    	Element thisElement = _root.getFirstChildElement("codeRevEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setCodeReviewEst(double s) {
    	Element codeRev = _root.getFirstChildElement("codeRevEst");
    	if (codeRev == null) {
    		codeRev = new Element("codeRevEst");
    		codeRev.appendChild(s + "");
            _root.appendChild(codeRev);    	
    	}
    	else {
    		codeRev.removeChildren();
    		codeRev.appendChild(s + "");    	
    	}
    }
    
    public String getCompileEst() {
    	Element thisElement = _root.getFirstChildElement("compileEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setCompileEst(double s) {
    	Element compile = _root.getFirstChildElement("compileEst");
    	if (compile == null) {
    		compile = new Element("compileEst");
    		compile.appendChild(s + "");
            _root.appendChild(compile);    	
    	}
    	else {
    		compile.removeChildren();
    		compile.appendChild(s + "");    	
    	}
    }
    
    public String getTestEst() {
    	Element thisElement = _root.getFirstChildElement("testEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setTestEst(double s) {
    	Element test = _root.getFirstChildElement("testEst");
    	if (test == null) {
    		test = new Element("testEst");
    		test.appendChild(s + "");
            _root.appendChild(test);    	
    	}
    	else {
    		test.removeChildren();
    		test.appendChild(s + "");    	
    	}
    }
    
    public String getPostmortemEst() {
    	Element thisElement = _root.getFirstChildElement("postmortemEst");
    	if (thisElement == null) {
    		return null;
    	}
    	else {
       		return thisElement.getValue();
    	}
    }

    public void setPostmortemEst(double s) {
    	Element postmortem = _root.getFirstChildElement("postmortemEst");
    	if (postmortem == null) {
    		postmortem = new Element("postmortemEst");
    		postmortem.appendChild(s + "");
            _root.appendChild(postmortem);    	
    	}
    	else {
    		postmortem.removeChildren();
    		postmortem.appendChild(s + "");    	
    	}
    }
    
    /**
     * @see net.sf.memoranda.Project#getTaskList()
     */
    /*public TaskList getTaskList() {
        return CurrentStorage.get().openTaskList(this);
    }*/
    /**
     * @see net.sf.memoranda.Project#getNoteList()
     */
    /*public NoteList getNoteList() {
        return CurrentStorage.get().openNoteList(this);
    }*/
    /**
     * @see net.sf.memoranda.Project#getResourcesList()
     */
    /*public ResourcesList getResourcesList() {
        return CurrentStorage.get().openResourcesList(this);
    }*/
}
