===========================================================================
||Group 8: Members: Dong Won Park, Victor Mansiantima Masivi, Ashwin Rao ||
===========================================================================


Part A: AWS Structure:
(1). Three Amazon instances are involved in our setup; each instance has our web app 
     installed in there with tomcat as the webserver. To access: 
     http://PublicIPOfInstance:8080/fabflix or for employees
     http://PublicIPOfInstance:8080/fabflix/_dashboard/
    
    (a) Instance 1: The Main   Public IP: 52.32.221.238, Private IP:172.31.0.28
    (b) Instance 2: The Master Public IP: 52.40.49.134,  Private IP:172.31.14.163
    (c) Instance 3: The Slave  Public IP: 52.38.123.165, Private IP:172.31.36.252

(2). Compile
We have created 2 exectuable files, 1 is to compile the web app (prj5.sh), 
and the second is to read the log and calculate the average time (prj5b.sh). But this last file is mainly for emptying the log files. These 
2 files are on located in home directory of the ubuntu user. to execute, just type: ./prj5.sh or prj5b.sh
We recommand using the above commands as it forces to reboot tomcat as sometimes it crashes.

Here however the compile directives if for some reason the above scripts fail
(a) prj5.sh

sudo $CATALINA_HOME/bin/shutdown.sh
sudo $CATALINA_HOME/bin/startup.sh  
cd /opt/tomcat/webapps/fabflix/WEB-INF
sudo javac -classpath ./:lib/mysql-connector-java-5.1.39-bin.jar:lib/servlet-api.jar:lib/jstl-1.2.jar:lib/javax.json-1.0.jar:lib/commons-beanutils-1.9.2.jar:lib/commons-collections-3.2.2.jar:lib/commons-lang-2.6.jar:lib/commons-logging-1.2.jar:lib/ezmorph-1.0.6.jar:lib/gson-2.2.2.jar:lib/json-lib-2.2.3-jdk15.jar:lib/json-simple-1.1.1.jar -d classes sources/*.java 

(b) prj5b.sh
cd /opt/tomcat/webapps/fabflix/WEB-INF/classes/
java fabflix.AvgCalc

(3): How to Connection pooling
We are using 2 contexts for connection pooling, 1 is used when searching the movie database
and the second is when writing into the database. We are not using server.xml file, instead we created
a new file called context.xml under META-INF folder and database settings for the connection are saved in there

To perform a search of movies using connection pooling, access the webapp from any of the 3 instances and 
provide username and password (like g@email.com and password g2)... validate the re-captcha and type in
the movie title fied the name or some charcters of the movie you are searching for. This is the only form that is 
implementing connection pooling at this moment.

To perform a movie or star insertion, use employee dashboard instead. usename classta@course.edu and password: classta. All movies or stars 
insertion are taking place only on the master if using master or slave instances.  



NOTE: The database include only the very original data of project 1 

(4): The Script to deal with the log files

We have created a python file called avg.py to calculate the average of TS and TJ values. 
This file ask for the file names of both the TS and TJ when being run. 
Please provide the correct location and names of corresponding files. 
We have included testing files in the same directory as the avg.py. 
To use them if you want to verify if the script works, type in ts.txt and tj.txt and result of average should be given. 

to run the script, make sure you are in the following folder:

cd /opt/tomcat/webapps/fabflix/

and tyoe

python avg.py


then provide the file and location of your TS and  TJ files or use the included examples ts.txt and tj.txt



     
    
 