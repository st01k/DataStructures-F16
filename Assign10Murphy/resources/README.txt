Complete Programming Problem 2 on page 558 of the text Walls and Mirrors

Please include a text file that answers the questions posed
-------------------------------------------------------------------------
2.  The method quicksort uses the method choosePivot to choose a pivot
and place it into the first array location.  Implement choosePivot in
two ways:

	a.  Always choose the first item in the array as pivot.
	b.	Choose a pivot as exercise 22a describes.
			22a.  Middle pivot
			
Add a counter to the method partition that counts the number of 
comparisons that are made.  Run quicksort with each pivot selection
strategy and with arrays of various sizes.  


ANSWERS:
-------------------------------------------------------------------------
On what size array does the difference in number of comparisons 
become significant?  

In the worst-case, once the array has more than 10 elements in it
the difference in comparisons becomes proportionally significant.
---
For which pivot selection strategy does the difference in the number of 
comparisons become significant?

The middle pivot shows the most significant difference when the 
amount of data increases.  Comparison counts at high data levels
represent the significantly greater efficiency of using the middle
pivot point in a quick sort.