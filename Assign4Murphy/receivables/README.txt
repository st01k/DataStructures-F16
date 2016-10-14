Casey Murphy
CS-221 - Chapter 4, Problem 9 (p. 238)
Created: 16 Sep 2016
Modified: 23 Sep 2016

Included in Asign3Murphy.zip:
-----------------------------------------------------------
- Asign4Murphy.jar (executable jar file)
- Asign4Murphy-UMLDiag.vsdx (UML Diagram)  NOT COMPLETE
- Asign4Murphy-RelDiag.vsds (Relation Diagrm)
- README.txt (this file)
-----------------------------------------------------------

War v0.9

NOTE:   Contains logic problems when there are nested wars
        Unicode characters do not translate in Windows,
        changed symbols to suit letter representations.
        Some games seem to be never ending.  Thinking is
        has to do with order the won cards are added to 
        bottom of player's stack.

Changelog:
-----------------------------------------------------------
0.9 -   Compiles 

To Compile and Run from Command Line:
-----------------------------------------------------------
(Inside Fall2016 Project Foler)
javac cardGames/war/WarClient.java
java -classpath . cardGames.war.WarClient.java
 
To Execute .jar file from Command Line:
-----------------------------------------------------------
WINDOWS
Run from the command line with either: 
java -jar Asign4Murphy.jar <options>
java -jar Asign4Murphy.jar <args>

*NIX
Make executable: `chmod u+x Asign3Murphy.jar`
Run from the command line with either: 
./Asign4Murphy.jar <options>
./Asign4Murphy.jar <args>

Command Line Options:
-----------------------------------------------------------
OPTIONS:
-d      Runs program in demo mode.
-t      Runs program in test mode, then exits.
-h      Help.  Prints this list.

ARGUMENTS:
none    There are no arguments for this game.

------------------------------------------------------------------------------------------------------------------
SPECIFICATIONS:
------------------------------------------------------------------------------------------------------------------
See Chapter 4, Problem 9 (p. 238) and Exercise 13 (p. 237)