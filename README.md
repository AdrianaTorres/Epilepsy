# Epilepsy

This is a small telemedicine project which should provide some basic services to both users and hospital staff. Shouldn't be taken too seriously though.

So far, there are 2 GUI, patient and hospital GUI

Patients GUI-->Main menu, monitoring tab, symptoms window.
Hospital's GUI--> main monitoring window, monitoring tab and symptoms tab.

So far the symptoms tab should be the same, the monitoring tab should also be the same (maybe with some very light changes) and finally we are missing the monitoring window which is kinda work in progress.

GUIs asside we need to make the Bitalino methods that read and writte from the hardware into the computer and such. Shouldn't be too difficult as most of it is just crl+c crl+v. We also need to make some file system to store and read all the data of any given report.

  So, to do as of 24/09/2019
  
  I) GUI monitoring webpage and linkig the already existing GUIs
  II)Creating the file read and write methods into textfiles so that we can read stored reports
  III)Implementing the Bitalino methods to read actual data so that I don't need to fake it cheaply with some random garbage anymore.
  
  03/10/2019
  So, new update as to keep you updated on the progress of this thing... So far we have the GUI for both Hospital and user mostly done (we still need to change some things) but the big stuff is out of the way. I have also organized a bit the packages (personal petition here) so that things are a bit more tiddy and reasonable. 
  
To the important stuff now: The GUI is mostly disconnected as we are waiting on other classes to be finished. We need the bitalino managing class and the file managing class... For the latter we have to define how the data is produced and stored (again bitalino class)
So, once the bitalino class is working we can move into the file shenanigans and once that is done into the core manager(main) from which we can handle all things and connect all the interfaces. 

So, hopefully soon enough we get the bitalino working. plz <3
  
  
17/10/19
So far we have done the following: we have created a graphical interface that allows monitoring data in real time and that it is also able to store said data in text format in files in the system. We have also worked on the bitalino manager and we believe it should work (hopefully) although we still have to test that everything is working. Looks promising though... 
We also have established that from now on the parts corresponding to the doctor and the server will be done in separate projects which means that the GUIHospital should be migrated sometime soon to another project. So, the next milestones to compete this project are the following:

	I)Ensure that the bitalino works fine and that the files are written and read correctly from the file system.
	II) Start working on the server, the server should have the ability to store files, and to view data in real time just as the patient does. Reminder, the fancy list selection method should prioritize those patients with the worst conditions.
	III) work on the doctor. The doctor is a user just as the rest that can only view reports, no real time fancyness here. If all the methods of the patient project are done and working, the doctor should only be a matter of copy pasting the necessary methods. The idea is to browse through internet the files that are stored in the server and send them to be read by the doctor. Just changes the functionality of the connection. (small price to pay for salvation) 
