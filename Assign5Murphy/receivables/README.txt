Casey Murphy
CS-221 - Chapter 5, Problem 3 (p. 307)
Created: 24 Sep 2016
Last Modified: 4 Oct 2016
-------------------------------------------------------------------------------
SRINLLI v1.1 - Sorted Recursive IntegerNode Linked List Implementation
-------------------------------------------------------------------------------
SPECIFICATIONS:
-------------------------------------------------------------------------------
Complete Programming Problem 2 in the Walls and Mirrors text, page 307.

2.  Write a recursive, reference-based implementation of the ADT sorted list of 
    objects as a Java class SortedListRefBased such that:

    a.  Duplicates are allowed
    b.  Use Integer objects for node
-------------------------------------------------------------------------------
NOTES
-------------------------------------------------------------------------------
Recursive methods must be in the private section of the class.

REF:    p. 210 (pseudocode for sorted list)
        p. 230 (interface for list)
-------------------------------------------------------------------------------
Changelog:
-------------------------------------------------------------------------------
0.1 -   skeleton
0.9 -   program specs met except BUG in equals method (needs fixed)
        recursive solutions function correctly
        iterative solutions not yet implemented (want to try)
1.0 -   recursive equals fixed
        added list tail variable
        added print README.txt to menu/CLI options
created ReadMe.java to print text file
1.1 -   iterative equals, contains, and assign tail methods work
        iterative add and remove work, but do not correctly sort
-------------------------------------------------------------------------------
Command Line Options:
-------------------------------------------------------------------------------
OPTIONS:
-d      Demo: Runs program in demo mode, then exits.
-t      Test: Runs program in test mode, then exits.
-h      Help: Prints available options and argument parameters.
-r      Read Me: Prints this file (README.txt).

ARGUMENTS:
none    There are no arguments for this program.

-------------------------------------------------------------------------------
Included in Asign5Murphy.zip:
-------------------------------------------------------------------------------
- ch5 folder (package source files)
- 2016Fall-Murphy.zip (project zip file)
- Asign5Murphy.jar (executable jar file)
- Asign5Murphy-UMLDiag.vsdx (UML Diagram)
- Asign5Murphy-RelDiag.vsds (Relation Diagram)
- README.txt (this file)

Please read below for import instructions.

-------------------------------------------------------------------------------
To Compile and Run from Command Line:
-------------------------------------------------------------------------------
(Inside Fall2016 Project Folder)
javac ch5/client/SortedLinkedListClient.java
java -classpath . ch5.client.SortedLinkedListClient.java

-------------------------------------------------------------------------------
To import if eclipse project exists:
-------------------------------------------------------------------------------
In '2016Fall' project, right-click 'src', add new package.
Name it 'ch5'
Import from file system.
Locate 'ch5' folder from assignment submission.
Select all, including ch5 folder.
Import
Experience blissful code execution.

-------------------------------------------------------------------------------
To import if eclipse project does not exist:
-------------------------------------------------------------------------------
Project zip contains all assignments as packages.

In Eclipse:
Import > General > Projects from Folder or Archive
Archive > Murphy-2016Fall.zip

Two project folders and expanded folder will show up.
Delete all folders but the '2016Fall' project folder.
Don't know what is causing the weirdness.

Run programs by clicking on package class file with 'main' method:
Per package 'main' method locations:

ch1 > Driver.java
ch2 > Driver.java
ch2RestructuredIncomplete > Driver.java (compiles, but not fully functional)
ch3 > RDouble.java
ch4 > ch4.war > WarClient.java
ch5 > ch5.client > SortedLinkedListClient.java
