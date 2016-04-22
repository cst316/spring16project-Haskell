#Team Haskell Memoranda README
#####Authors: Zach Jaros, Chris Young, Duncan McQuarrie, Trent Ferree, and Gabriel Waltrip


##INTRODUCTION

###What is Memoranda?
Memoranda is an open-source task scheduling application developed in 2003 that was designed to help people with multiple projects manage their time and effort in order to maximize success. It has now been refitted to also align with the Personal Software Process (PSP) often used in software-based work environments. Some of these new features include Lines of Code (LoC) tracking, task timers, defect logging, and stage estimation.

Requirements:
Java 7 or newer
1 MB of free disk space
900 Mhz processor
512 MB ram

Installation:
Make sure you have Java installed
Download the Jar
Place the jar in the desired location

Starting Up Memoranda:
Run the Jar

THE USER INTERFACE

##PROJECTS

Creating a project:
There are two ways to begin making a project:
Click on the “File” menu option at the top left corner of the window. Select “New Project”
Click on the drop down button on the right of the light-blue region. This is the projects panel that will hold all your projects for easy access. Inside this area, right click anywhere and a menu will pop up with “New Project” as an option.
This will take you to a wizard that will guide you through the process of entering key information for your project. The only required fields are:
The title of the project
The directory location (so that Memoranda can monitor your LoC)
At least 1 person added to the team
Once you have met these requirements, and filled out any other information that you would like to record, click “Next” to advance to the stage estimation section. In PSP, making time estimations for your work is a key way to develop understanding about your capabilities and show improvement. You can increment the estimation spinners for each stage of development (Planning, Design, Design Review, Code, Code Review, Compile, Test, Postmortem) using 30 minute intervals or enter a value manually (in the case of large quantities). Note that this section of the project creation isn’t mandatory. After you are satisfied with your estimations, click “Finish” to save your project.

Editing a project:


Deleting a project:
Select a project other than the one to be deleted then right click on the project to be removed and select delete

Changes to projects based on PSP:
The addition of specifying the project directory helps with analyzing 

##TASKS
After a Project has been created, Memoranda provides functionality to add individual tasks to the project, for example “create ‘Add’ method”. The task creation contains separate tabs supporting many features of PSP; Phase Estimation, code Timer, and defect list. Creating a task as is follows
Select ‘New Task’ from the Task Panel (Select ‘Tasks’ view on the left hand panel)
Enter a Task name and description (optional)
After the initial information has been given, the user is free to select ‘ok’ to close the window and complete the process, or choose from the following tabs; Phase Estimation, Timer, Defect.

The Phase Estimation tab provides 8 jSpinner objects, each spinner represents a Phase in the PSP. Planning, Design, Design Review, Code, Code Review,  Compile, Test, and Postmortem. As each individual spinner is incremented, a running total is generated automatically, giving the user an estimated time of completion for the task.

The Timer tab provides the simple functionality of a stopwatch, simply open the tab and press ‘Start’ The current time (in Hours:Minutes:Seconds) is displayed in the adjacent. When the user is ready, select ‘end’ and again, the current time will be displayed in the adjacent field. After pressing ‘End’ a Time difference, between the starting and ending periods, is displayed in the final field.

The defect tab provides an interface to add individual defects found in a task. The date of the defect is automatically generated, all the users needs to provide is the following
Class - Which class the defect is located
Line # - the Line # the defect is located
Type - UI, Control flow, Exception handling, Syntax are currently supported types of defects
Status - Provide the status of the defect (Open, in Progress, or Closed)

##EVENTS
There are two ways to create a new event 
1. The new event button in the toolbar
2. Right click in the window and select “New event”
When new event is selected a dialog box pops up and information related to the event can be added. Once the event data has been entered the “Ok” button is pressed to finish creating the event. 
After the event has been created it can be edited through the edit button in the toolbar or the right click menu when the event is highlighted. The edit window is the same as the event creation window. Events can be deleted by selecting them and right clicking and clicking “Remove event” or by the toolbar button or by pressing the delete key.

##STATS
The stats panel is updated every time you click on it. There are two tabs at the top of the screen, the first tab contains the information from the timer. The next tab contains the line counts of all the java files in the directory specified for the project.
