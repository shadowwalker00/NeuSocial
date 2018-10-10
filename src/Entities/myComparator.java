package Entities;
import java.util.*;
public class myComparator implements Comparator {
	public int compare(Object o1,Object o2) { 
		Integer e1=(Integer)o1; 
		Integer e2=(Integer)o2; 
		if(e1<e2){
			return 1;
		}		 
		else{
			return -1;
		} 
	} 
}
