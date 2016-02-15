package cp2_2;
	
import java.io.*; 
import java.util.*;
	
public class SynchingSignals{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();int k = 1;
	    while(br.ready())
	    {
	    	sb.append("Set "+k++);
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = st.countTokens();
	    	int[] periods = new int[n];
	    	int minPeriod = 180;
	    	for(int i = 0; i < n; i++)
	    	{
	    		periods[i] = Integer.parseInt(st.nextToken())*2;
	    		if(periods[i]<minPeriod)
	    			minPeriod = periods[i];
	    	}
	    	int time = minPeriod/2-4;boolean gotGreen = false;
	    	while(!gotGreen&&time<=3600)
	    	{
	    		gotGreen = true;
	    		for(int i = 0; i < n; i++)
	    		{ 
	    			if(time%periods[i]>=periods[i]/2-5)
	    				gotGreen = false;
	    		}
	    		time ++;
	    	}
	    	if(!gotGreen)
	    		sb.append(" is unable to synch after one hour.\n");
	    	else
	    	{
	    		time--;
	    		int minutes = time/60;
	    		int seconds = time%60;
	    		sb.append(" synchs again at "+minutes+" minute(s) and "+seconds+" second(s) after all turning green.\n");
	    	}
	    }
		
		
		
		
		
		System.out.print(sb);
		
	}
	
	
	
}	