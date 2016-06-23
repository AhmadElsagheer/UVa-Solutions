package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class WateringGrass_UVa10382 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			double[][] intervals = new double[n][2];
			
			for(int i = 0; i < n;i++)
			{
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				double halfRange = Math.sqrt(r*r-(w/2.0)*(w/2.0));
				double leftEnd = p - halfRange;
				double rightEnd = p + halfRange;
				intervals[i][0] = leftEnd;
				intervals[i][1] = rightEnd;
			}
			
			double a = 0;
			double b = l;
			int count = 0;
			boolean possible = true;
			while(a<b)
			{
				count++;
				double maxRightEnd = maxRightEnd(intervals,a);
				if(maxRightEnd==-1)
				{
					possible = false;
					break;
				}
				a = maxRightEnd; 
				
			}
			if(!possible)
				count = -1;
			sb.append(count+"\n");
			
		}
		System.out.print(sb);
	}
	
	public static double maxRightEnd(double[][] intervals, double point)
	{
		double maxRightEnd = -1;
		for(int i = 0; i < intervals.length; i++)
		{
			if(intervals[i][0]<=point && intervals[i][1]>=point)
			{
				if(intervals[i][1]>maxRightEnd)
					maxRightEnd = intervals[i][1];
					
				
			}
		}
		return maxRightEnd;
	}
	

}
