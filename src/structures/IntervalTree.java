package structures;

import java.util.*;

/**
 * Encapsulates an interval tree.
 * 
 * @author runb-cs112
 */
public class IntervalTree {
	
	/**
	 * The root of the interval tree
	 */
	IntervalTreeNode root;
	
	/**
	 * Constructs entire interval tree from set of input intervals. Constructing the tree
	 * means building the interval tree structure and mapping the intervals to the nodes.
	 * 
	 * @param intervals Array list of intervals for which the tree is constructed
	 */
	public IntervalTree(ArrayList<Interval> intervals) {
		
		// make a copy of intervals to use for right sorting
		ArrayList<Interval> intervalsRight = new ArrayList<Interval>(intervals.size());
		for (Interval iv : intervals) {
			intervalsRight.add(iv);
		}
		
		// rename input intervals for left sorting
		ArrayList<Interval> intervalsLeft = intervals;
		
		// sort intervals on left and right end points
		Sorter.sortIntervals(intervalsLeft, 'l');
		Sorter.sortIntervals(intervalsRight,'r');
		
		// get sorted list of end points without duplicates
		ArrayList<Integer> sortedEndPoints = Sorter.getSortedEndPoints(intervalsLeft, intervalsRight);
		
		// build the tree nodes
		root = buildTreeNodes(sortedEndPoints);
		
		// map intervals to the tree nodes
		mapIntervalsToTree(intervalsLeft, intervalsRight);
	}
	
	/**
	 * Builds the interval tree structure given a sorted array list of end points.
	 * 
	 * @param endPoints Sorted array list of end points
	 * @return Root of the tree structure
	 */
	public static IntervalTreeNode buildTreeNodes(ArrayList<Integer> endPoints) {
		
		Queue<IntervalTreeNode> Q = new Queue<IntervalTreeNode>();
		IntervalTreeNode T;
		
		for(int i=0; i<endPoints.size(); i++){
			T = new IntervalTreeNode(endPoints.get(i),endPoints.get(i),endPoints.get(i));
			T.leftIntervals = new ArrayList<Interval>();
			T.rightIntervals = new ArrayList<Interval>();
			Q.enqueue(T);
			
		}
		
	
		
		while(true){
			
			int s=Q.size();	
			
			if(s==1)
				break;
				
			
		
		
			int temp = s;
		
			while(temp>1){
			
				IntervalTreeNode T1 = Q.dequeue();
				IntervalTreeNode T2 = Q.dequeue();
				float V1 = T1.maxSplitValue;
				float V2 = T2.minSplitValue;
				IntervalTreeNode N = new IntervalTreeNode((V1+V2)/2,T1.minSplitValue,T2.maxSplitValue); 
				N.leftIntervals = new ArrayList<Interval>();
				N.rightIntervals = new ArrayList<Interval>();
				N.leftChild = T1;
				N.rightChild = T2;
				Q.enqueue(N);
				temp = temp - 2;
			
			
			}
		
			if(temp==1)
			Q.enqueue(Q.dequeue());
		
		
		
		}
		
		T = Q.dequeue();
		return T;
		
		
		
		
		
		
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		
	}
	
	/**
	 * Maps a set of intervals to the nodes of this interval tree. 
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 */
	public void mapIntervalsToTree(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		
		for(int i=0; i<leftSortedIntervals.size(); i++){
			
			Interval x = leftSortedIntervals.get(i);
			IntervalTreeNode temp = root;
			boolean a = false;
			
			while(a==false){
				
			
				
				if(x.contains(temp.splitValue)==true){
					temp.leftIntervals.add(x);
					a=true;
				}
				
				else if(temp.splitValue<(x.leftEndPoint))
					temp = temp.rightChild;
				
				else
					temp=temp.leftChild;
			}
			
			
			
		}
		
		for(int i=0; i<rightSortedIntervals.size(); i++){
			
			Interval x = rightSortedIntervals.get(i);
			IntervalTreeNode temp = root;
			
			while(true){
				if(x.contains(temp.splitValue)==true){
					temp.rightIntervals.add(x);
					break;
				}
				
				else if(temp.splitValue < x.leftEndPoint)
					temp = temp.rightChild;
				
				else
					temp=temp.leftChild;
			}
			
			
			
		}
		
		return;
		
		
		
		
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Gets all intervals in this interval tree that intersect with a given interval.
	 * 
	 * @param q The query interval for which intersections are to be found
	 * @return Array list of all intersecting intervals; size is 0 if there are no intersections
	 */
	public ArrayList<Interval> findIntersectingIntervals(Interval q) {
		ArrayList<Interval> result = new ArrayList<Interval>();
		
		return findIntersectingIntervalsRecursively(q, root, result);
		
		
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		
	}
	
	private ArrayList<Interval> findIntersectingIntervalsRecursively(Interval q, IntervalTreeNode root, ArrayList<Interval> result){
		
		if(root.leftChild == null && root.rightChild == null){
			return result;
		}
		
		else if(q.contains(root.splitValue) == true){
			
			
			for(int i=0; i<root.leftIntervals.size(); i++){
				result.add(root.leftIntervals.get(i));
			}
			
			  findIntersectingIntervalsRecursively(q, root.leftChild, result); 
			  findIntersectingIntervalsRecursively(q, root.rightChild, result);
			  return result;
		}
		
		else if(root.splitValue < q.leftEndPoint){
			int i = root.rightIntervals.size()-1;
			while(i>=0){
				if(root.rightIntervals.get(i).intersects(q)==true){
					result.add(root.rightIntervals.get(i));
				}
				else
					break;
				
				i--;
			}
			
			findIntersectingIntervalsRecursively(q, root.rightChild, result);
			return result;
				
				
		}
		
		else{
			for(int i=0; i<root.leftIntervals.size(); i++){
				if(root.leftIntervals.get(i).intersects(q)==true){
					result.add(root.leftIntervals.get(i));
				}
				
				else
					break;
			}
			
			findIntersectingIntervalsRecursively(q, root.leftChild, result);
			return result;
		}
			
		
		
	}
	
	/**
	 * Returns the root of this interval tree.
	 * 
	 * @return Root of interval tree.
	 */
	public IntervalTreeNode getRoot() {
		return root;
	}
	

}

