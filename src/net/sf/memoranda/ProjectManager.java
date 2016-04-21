/**
 * ProjectManager.java
 * Created on 11.02.2003, 17:50:27 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.util.Vector;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 *
 */
/*$Id: ProjectManager.java,v 1.9 2005/12/01 08:12:26 alexeya Exp $*/
public class ProjectManager {
//    public static final String NS_JNPROJECT = "http://www.openmechanics.org/2003/jnotes-projects-file";

    public static Document _doc = null;
    static Element _root = null;
    static Element el = new Element("project");
    static Project prj;
    
    static {
    	init();
    }

    public static void init() {
        CurrentStorage.get().openProjectManager();
        if (_doc == null) {
            _root = new Element("projects-list");
//            _root.addNamespaceDeclaration("jnotes", NS_JNPROJECT);
//            _root.appendChild(new Comment("This is JNotes 2 data file. Do not modify."));
            _doc = new Document(_root);
            createProject("__default", Local.getString("Default project"), CalendarDate.today(), null);
        }
        else
            _root = _doc.getRootElement();
    }

    public static Project getProject(String id) {
        Elements prjs = _root.getChildElements("project");
        for (int i = 0; i < prjs.size(); i++) {
            String pid = ((Element) prjs.get(i)).getAttribute("id").getValue();
            if (pid.equals(id)) {
                return new ProjectImpl((Element) prjs.get(i));
            }
        }
        return null;
    }

    public static Vector getAllProjects() {
        Elements prjs = _root.getChildElements("project");
        Vector v = new Vector();
        for (int i = 0; i < prjs.size(); i++)
            v.add(new ProjectImpl((Element) prjs.get(i)));
        return v;
    }

    public static int getAllProjectsNumber() {
		int i;
        try {
			i = ((Elements)_root.getChildElements("project")).size();
		}
		catch (NullPointerException e) {
			i = 1;
		}
		return i;
    }

    public static Vector getActiveProjects() {
        Elements prjs = _root.getChildElements("project");
        Vector v = new Vector();
        for (int i = 0; i < prjs.size(); i++) {
            Project prj = new ProjectImpl((Element) prjs.get(i));
            if (prj.getStatus() == Project.ACTIVE)
                v.add(prj);
        }
        return v;
    }
		
    public static int getActiveProjectsNumber() {
        Elements prjs = _root.getChildElements("project");
        int count = 0;
        for (int i = 0; i < prjs.size(); i++) {
            Project prj = new ProjectImpl((Element) prjs.get(i));
            if (prj.getStatus() == Project.ACTIVE)
                count++;
        }
        return count;
    }

    
    public static void setTitle(String title){
    	prj.setTitle(title);
    }
    
    public static void setStartDate(CalendarDate date){
    	prj.setStartDate(date);
    }
    
    public static void setEndDate(CalendarDate date){
    	prj.setEndDate(date);
    }
    
    public static void setDescription(String description){
        el.addAttribute(new Attribute("description",description));
    	prj.setDescription(description);
    }
    
    //New setters//
    public static void setStage(String stage){
        el.addAttribute(new Attribute("stage",stage));
    	prj.setStage(stage);
    }
    public static void setPriority(String priority){
        el.addAttribute(new Attribute("priority",priority));
    	prj.setPriority(priority);
    }
    public static void setCustomer(String customer){
        el.addAttribute(new Attribute("customer",customer));
    	prj.setCustomer(customer);
    }
    public static void setFile(String file){
        el.addAttribute(new Attribute("file",file));
    	prj.setFile(file);
    }
    public static void setTeam(String team){
        el.addAttribute(new Attribute("team",team));
    	prj.setTeam(team);
    }
    
    //Estimates//
    public static void setPlanningEst(double planningEst){
        el.addAttribute(new Attribute("planningEst",planningEst + ""));
    	prj.setPlanningEst(planningEst);
    }
    public static void setDesignEst(double designEst){
        el.addAttribute(new Attribute("designEst",designEst + ""));
    	prj.setDesignEst(designEst);
    }
    public static void setDesignReviewEst(double designReviewEst){
        el.addAttribute(new Attribute("designReviewEst",designReviewEst + ""));
    	prj.setDesignReviewEst(designReviewEst);
    }
    public static void setCodeEst(double codeEst){
        el.addAttribute(new Attribute("codeEst",codeEst + ""));
    	prj.setCodeEst(codeEst);
    }
    public static void setCodeReviewEst(double codeReviewEst){
        el.addAttribute(new Attribute("codeReviewEst",codeReviewEst + ""));
    	prj.setCodeReviewEst(codeReviewEst);
    }
    public static void setCompileEst(double compileEst){
        el.addAttribute(new Attribute("compileEst",compileEst + ""));
    	prj.setCompileEst(compileEst);
    }
    public static void setTestEst(double testEst){
        el.addAttribute(new Attribute("testEst",testEst + ""));
    	prj.setTestEst(testEst);
    }
    public static void setPostmortemEst(double postmortemEst){
        el.addAttribute(new Attribute("postmortemEst",postmortemEst + ""));
    	prj.setPostmortemEst(postmortemEst);
    }
    //
    
    public static void createProject(){
    	el.addAttribute(new Attribute("id",Util.generateId()));
        _root.appendChild(el);
        prj = new ProjectImpl(el);
    }
    
    public static void createProject(String id){
    	el.addAttribute(new Attribute("id", id));
        _root.appendChild(el);
        prj = new ProjectImpl(el);
    }
    
    public static Project build(){
    	CurrentStorage.get().createProjectStorage(prj);
    	return prj;
    }

    //Example how it is used.
    public static Project createProject(String id, String title, CalendarDate startDate, CalendarDate endDate) {
    	//Create Project must be called first
		createProject();
		//After wards you can call these in any order
		setTitle(title);
		setEndDate(endDate);
		setStartDate(startDate);
		//But Build must be the last one. This returns the object.
		return build();
    }


    public static Project createProject(String id, String title, String description, CalendarDate startDate, CalendarDate endDate) {
		createProject(id);
		setEndDate(endDate);
		setStartDate(startDate);
		setDescription(description);
		setTitle(title);
		return build();
    }
    
    public static Project createProject(String title, 
    									String description, 
    									CalendarDate startDate, 
    									CalendarDate endDate,
    									String stage,
    									String priority,
    									String customer,
    									String file,
    									String team,
    									double planningEst,
    									double designEst,
    									double designRevEst,
    									double codeEst,
    									double codeRevEst,
    									double compileEst,
    									double testEst,
    									double postmortemEst) {
		createProject();
		setEndDate(endDate);
		setStartDate(startDate);
		setDescription(description);
		setTitle(title);
		setStage(stage);
		setPriority(priority);
		setCustomer(customer);
		setFile(file);
		setTeam(team);
		setPlanningEst(planningEst);
		setDesignEst(designEst);
		setDesignReviewEst(designRevEst);
		setCodeEst(codeEst);
		setCodeReviewEst(codeRevEst);
		setCompileEst(compileEst);
		setTestEst(testEst);
		setPostmortemEst(postmortemEst);
		
		return build();
    }

    public static Project createProject(String title, CalendarDate startDate, CalendarDate endDate) {
        return createProject(Util.generateId(), title, startDate, endDate);
    }

    public static Project createProject(String title, CalendarDate startDate, CalendarDate endDate,String descrption) {
        return createProject(Util.generateId(), title, descrption, startDate, endDate);
    }
    
    public static void removeProject(String id) {
        Project prj = getProject(id);
        if (prj == null)
            return;
        History.removeProjectHistory(prj);
        CurrentStorage.get().removeProjectStorage(prj);
        Elements prjs = _root.getChildElements("project");
        for (int i = 0; i < prjs.size(); i++) {
            String pid = ((Element) prjs.get(i)).getAttribute("id").getValue();
            if (pid.equals(id)) {
                _root.removeChild(prjs.get(i));
                return;
            }
        }
    }

}
