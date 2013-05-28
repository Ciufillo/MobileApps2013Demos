This demo is an adapted employeeeDirectoryModular demo app in which I provide also an implementation of its REST API in Java (via Tomcat+Jersey+MySQL). I also added a functionality to delete employees directly from the web app. 

Functionalities for adding and updating employees are supported by the REST API, but: 

1. they have not been implemented in the client web app,
1. they have not been tested

This demo is composed of 2 main parts:

1. Database: contains a MySQL dump containing all the data needed by the app 
1. Server: contains an implementation of the REST API backing up the application

Database Setup:
* install XAMPP on your machine. XAMPP can be downloaded here: http://www.apachefriends.org/en/xampp-windows.html
* Launch its MySQL module
* go to http://localhost/phpmyadmin/ with your web browser
* create a DB called employeedirectory and open it
* in the SQL tab, paste all the contents of this file: https://raw.github.com/ccoenraets/backbone-directory/master/directory.sql and execute the SQL query
* check that a table called "employee" has been created and that it contains some rows of data

Server Setup:
* install Eclipse IDE for Java EE Developers. It can be downloaded from here: http://www.eclipse.org/downloads/
* extract the just downloaded archive into any directory within your computer and run it
* in Eclipse, go to file->import...->General->"Existing Projects into Workspace"
* select both the EmployeeDirectoryServer and Servers folder
* click on "Finish"

How to Run the demo:

1. launch the Apache and MySQL modules of XAMPP and check if MySQL is correctly running via PhPMyAdmin: http://localhost/phpmyadmin/
1. in Eclipse, right-click on the EmployeeDirectoryServer project and click on RunAs->"Run on Server"
1. check that the REST API is now running by browsing to http://localhost:8080/EmployeeDirectoryServer/api/employees (you should see an XML stream now)
