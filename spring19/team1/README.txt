README for Java project by Mattieu and Fredrik in Java class at KNU.

Partial solution to a tinder-like application which would include multiserver chatting
functionality alongside an extensive user profile + database handling. As mentioned in 
the previous sentence we only achieved a partial solution due to time-restriction, limited
knowledge on the Java language and pressure on projects and final exams from other courses.

The partial solution includes two seperate programs, one which contains the "tinder" like 
part of the program. That is, being able to connect to a server which contains lists of registered users
and then see their profiles and decide to either "like" or "dislike" their profiles. The second program
contains a multi client chatting program where the clients can send and receive messages through the server.

The programs are unfortunately not integrates together yet, and can only be run standalone. Both programs
can be run by opening any IDE and first start the server(localhost and port is hardcoded for both programs).
After the server runs, one or more clients can be started to interact with the server and use the functionality
described above. In the current code some of the images that is in the GUI heavy part of the code is hardcoded 
from within the server with absolute paths and might cause problem when you run the application to see it. 
If this occurs, please just provide new paths in the server. 

NOTE: To run multiple instances of some classes inside some IDE's a specific settings needs to be set. In intellij
(the IDE which has been used to develop these programs) you can go under the tabs Run -> Edit configurations and in
the upper right corner of the new window, tick the checkbox that says "Allow parallell run". If this is unclear, please
check the screenshot that is also provided in this folder.
To load the images, you will need to change the path in Interface/match for like and dislike button
and at the and of Server file, all the images are in the image folder.

/Fredrik & Mattieu
