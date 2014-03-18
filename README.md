ToDoList
========

Group Project

If your Github Sync Stalls:

BACK UP YOUR FILES BEFORE ATTEMPTING THIS!

Hit the Tilde Key in the project to open the console:

git fetch
git reset --hard origin/master

These commands will overwrite your files with the files on Github Server.

AGAIN! PLEASE BACK UP YOUR FILES BEFORE DOING THIS!

========

Date Format is now:
"MM/DD/YYYY G 'at' HH:mm:ss z"

How to use DateFormat:

Assuming you have a Calendar object c:

\\This creates a simple date format that formats the Calendar object
DateFormat format = new SimpleDateFormat("MM/DD/YYYY G 'at' HH:mm:ss z")
\\This converts the calendar to a String
String s = format.format(c);