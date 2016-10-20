Casey Murphy
CS-221 - Chapter 3, Problem 2 (p. 195)
Created: 9 Sep 2016
Modified: 13 Sep 2016

Included in Asign3Murphy.zip:
-----------------------------------------------------------
- Asign3Murphy.jar (executable jar file)
- Asign3Murphy.vsdx (UML Diagram)
- README.txt (this file)
-----------------------------------------------------------

rDouble v1.2

Changelog:
-----------------------------------------------------------
1.0 -   Compiles and functions correctly.
1.1 -   Integrated looped dialog until user prompted exit
1.2 -   Modified outputs by adding to date earning values
        Removed interactive validation from cli threads
        Modified test method to display to-date values
        

To Compile and Run from Command Line:
-----------------------------------------------------------
(Inside Fall2016 Project Foler)
javac recursiveDoubling/RDouble.java
java -classpath . recursiveDoubling.RDouble
 
To Execute .jar file from Command Line:
-----------------------------------------------------------
WINDOWS
Run from the command line with either: 
java -jar Asign3Murphy.jar <options>
java -jar Asign3Murphy.jar <args>

*NIX
Make executable: `chmod u+x Asign3Murphy.jar`
Run from the command line with either: 
./Asign3Murphy.jar <options>
./Asign3Murphy.jar <args>

Command Line Options:
-----------------------------------------------------------
OPTIONS:
-t      Runs program in test mode, then exits.
-h      Help.  Prints this list.

ARGUMENTS:
n       Day of month as an integer.
RANGE:  1 - 31, used to calculate daily payment.

------------------------------------------------------------------------------------------------------------------
SPECIFICATIONS:
------------------------------------------------------------------------------------------------------------------
Complete  Programming Problem 2 in the Walls and Mirrors text.  This assignment should be solved recursively.  Your method(s) should operate on a data set from 1 - 31 inclusive (a 31 day month).

Error checking should enforce appropriate data input.

The driver should contain 2 sections.  The first is non-interactive and should print print the day of the month, payment earned on that day, and the total payment received that month to date for all 31 days in the month.  The second section should be interactive, prompt for a day within that 31 day month and then print the payment received that day as well as the total payment received that month to date.  In interactive mode only one line of data should be printed.