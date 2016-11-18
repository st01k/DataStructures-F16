public class QuickSort {

	// QuickSorts -------------------------------------------------------------
	/**
	 * Quicksort using the first array element as pivot.
	 * @param ary array in
	 * @param first first index
	 * @param last last index
	 * @return comparison count
	 */
	public static <T extends Comparable<? super T>> 
		int sortA(T[] ary, int first, int last) {
		
		int[] countAndPartition;
		int pivotIndex;
		int count = 0;
				
		if (first < last) {
			
			countAndPartition = partition(ary, first, last, true);
			pivotIndex = countAndPartition[1];
			count = countAndPartition[0];
			
			count += sortA(ary, first, pivotIndex - 1);
			count += sortA(ary, pivotIndex + 1, last);
		}
		
		return count;
	}
	
	/**
	 * Quicksort using the middle array element as pivot.
	 * @param ary array in
	 * @param first first index
	 * @param last last index
	 * @return comparison count
	 */
	public static <T extends Comparable<? super T>> 
		int sortB(T[] ary, int first, int last) {
		
		int[] countAndPartition;
		int pivotIndex;
		int count = 0;
				
		if (first < last) {
		
			countAndPartition = partition(ary, first, last, false);
			pivotIndex = countAndPartition[1];
			count = countAndPartition[0];
			
			count += sortB(ary, first, pivotIndex - 1);
			count += sortB(ary, pivotIndex + 1, last);
		}
		
		return count;
	}
	
	// Partition --------------------------------------------------------------
	/**
	 * Partitions the array for sorting
	 * @param ary array in
	 * @param first first index
	 * @param last last index
	 * @param isSortA called from sort A or sort B
	 * @return comparison count and partition index
	 */
	private static <T extends Comparable<? super T>> 
		int[] partition(T[] ary, int first, int last, boolean isSortA) {
		
		int[] countAndPartition = {0,0};
		
		T tempItem;
		T pivot;
		
		// pivot is first element
		if (isSortA) pivot = ary[first];
		// pivot is middle element
		else {
			choosePivot(ary, first, last);
			pivot = ary[first];
		}
				
		int lastS1 = first;
		countAndPartition[0]++;
		
		for (int firstUnknown = first + 1; firstUnknown <= last; ++firstUnknown) {
			
			countAndPartition[0]++;
			
			if (ary[firstUnknown].compareTo(pivot) < 0) {
				++lastS1;
				tempItem = ary[firstUnknown];
				ary[firstUnknown] = ary[lastS1];
				ary[lastS1] = tempItem;
				countAndPartition[0]++;
			}
			
			countAndPartition[0]++;
		}
		
		tempItem = ary[first];
		ary[first] = ary[lastS1];
		ary[lastS1] = tempItem;
		countAndPartition[1] = lastS1;
		return countAndPartition;
	}
	
	// Choose Pivot -----------------------------------------------------------		
	/**
	 * Chooses pivot for the middle partition sort.
	 * @param ary array in
	 * @param first first index
	 * @param last last index
	 * @return pivot index
	 */
	private static <T extends Comparable<? super T>> 
		int choosePivot(T[] ary, int first, int last) {
		
		T item = ary[(first + last) / 2];
		int mid;
		
		// first > last
		if (ary[first].compareTo(ary[last]) > 0) {
		
			// first > last > item
			if (ary[last].compareTo(item) > 0) mid = last;
			// first > item > last
			else if (ary[first].compareTo(item) > 0) mid = (first + last) / 2;
			// item > first > last
			else mid = first;
		}
		// first > item
		else {
			
			// last > first > item
			if (ary[first].compareTo(item) > 0) mid = first;
			// last > item > first
			else if (ary[last].compareTo(item) > 0) mid = (first + last) / 2;
			// item > last > first
			else mid = last; 
		}
		
		if (mid != first) {
			T temp = ary[first];
			ary[first] = ary[mid];
			ary[mid] = temp;
		}
		
		return mid;
	}
}
