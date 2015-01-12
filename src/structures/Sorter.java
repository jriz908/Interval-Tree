package structures;

import java.util.ArrayList;

/**
 * This class is a repository of sorting methods used by the interval tree.
 * It's a utility class - all methods are static, and the class cannot be instantiated
 * i.e. no objects can be created for this class.
 * 
 * @author runb-cs112
 */
public class Sorter {

	private Sorter() { }
	
	/**
	 * Sorts a set of intervals in place, according to left or right endpoints.  
	 * At the end of the method, the parameter array list is a sorted list. 
	 * 
	 * @param intervals Array list of intervals to be sorted.
	 * @param lr If 'l', then sort is on left endpoints; if 'r', sort is on right endpoints
	 */
	public static void sortIntervals(ArrayList<Interval> intervals, char lr) {
	
		
		int initLength = intervals.size();
		
		
		
		if(lr == 'l'){
			for(int i=1; i<initLength; i++){
				
				int j = i;
				
				while (j>0){
				if(intervals.get(j).leftEndPoint < intervals.get(j-1).leftEndPoint)	{
					Interval temp = intervals.get(j);
					intervals.set(j, intervals.get(j-1));
					intervals.set(j-1, temp);
					
				}
				
				else
					break;
				
				j = j -1;
				}
				
				
					
			}
			
			
			
			
			
			
		}
		
		else if(lr == 'r'){
			
			
			
			for(int i=1; i<initLength; i++){
				
				int j = i;
				
				while (j>0){
				if(intervals.get(j).rightEndPoint < intervals.get(j-1).rightEndPoint)	{
					Interval temp = intervals.get(j);
					intervals.set(j, intervals.get(j-1));
					intervals.set(j-1, temp);
					
				}
				
				else
					break;
				
				j = j -1;
				}
				
				
					
			}
			
			
			
			
			
		}
		
		
		
		
		
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Given a set of intervals (left sorted and right sorted), extracts the left and right end points,
	 * and returns a sorted list of the combined end points without duplicates.
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 * @return Sorted array list of all endpoints without duplicates
	 */
	public static ArrayList<Integer> getSortedEndPoints(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
	
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		
		for(int i=0; i<leftSortedIntervals.size(); i++){
			int x = leftSortedIntervals.get(i).leftEndPoint;
			boolean duplicate = false;
			for(int j=0; j<sorted.size(); j++){
				if(sorted.get(j) == x){
					duplicate = true;
					break;
				}
			}
			
			if(duplicate==false){
				sorted.add(x);
			}
		}
		
		for(int i=0; i<rightSortedIntervals.size(); i++){
			int x = rightSortedIntervals.get(i).rightEndPoint;
			boolean duplicate = false;
			for(int j=0; j<sorted.size(); j++){
				if(sorted.get(j) == x){
					duplicate = true;
					break;
				}
			}
			
			if(duplicate ==false){
				
				boolean added = false;
				
				for(int h=0; h<sorted.size(); h++){
					
					if(sorted.get(h) > x){
						sorted.add(h, x);
						added = true;
						break;
						
					}
				}
				
				if(added==false){
					sorted.add(x);
				}
			}
			
			
		}
		
		
		
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		return sorted;
	}
	
	
	
}
