import java.util.*;


public class Main {
    public static void main(String[] args) {
       ex a = new ex();
       a.isExecute();
    }
}



class ex{
	
	void isExecute() {
		Scanner s = new Scanner(System.in);
		int totalDay, windowSize;
		int[] visitList;
		long maxVisit= 0, Visit = 0, sameDay = 1; 
		
		totalDay = s.nextInt();	windowSize = s.nextInt();
		visitList = new int[totalDay];
		
		for(int i = 0; i<totalDay; i++) {
			visitList[i] = s.nextInt();
		}
		
		for(int i = 0; i<windowSize; i++) {
			Visit += visitList[i];
			maxVisit = Visit;
		}
		
		
		for(int i =windowSize ; i<=totalDay-1; i++) {
			Visit += visitList[i];
			Visit -= visitList[i-windowSize];
			if(Visit > maxVisit) {	maxVisit = Visit;	sameDay = 1;	}
			else if(Visit == maxVisit)	{	sameDay++;	}
		}
		
		if(maxVisit == 0)	{	System.out.println("SAD");	}
		else	{	System.out.println(maxVisit);	System.out.println(sameDay);	}
		
	}
	
}