package cp3_2;


import java.io.*;
import java.text.*;
import java.util.*;

public class MaximumSubsequenceProduct_UVa10684 {
		
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = 1;
		while(tc-->0)
		{
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			long largestSoFar = -999999999999999999L;
			long largestN = 0;
			long largestP = -1;
			
			while(true)
			{
				if(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				if(n==-999)
					break;
				if(n>0)
				{
					if(largestP==-1)
						largestP = n;
					else
						largestP = largestP * n;
						
					
					
					largestN = largestN * n;
				}
				else
					if(n<0)
					{
						long tmp = largestP;
						
						largestP = largestN * n;
						if(largestP==0)
							largestP = -1;
						
						if(tmp==-1)
							largestN = n;
						else
							largestN = largestP * n;
						
						
					}
					else
					{
						largestP = -1;
						largestN = 0;
					}
					if(largestP!=-1)
						largestSoFar = Math.max(largestSoFar, largestP);
					else
						largestSoFar = Math.max(largestSoFar, largestN);
			}
			sb.append(largestSoFar+"\n");


			
		}
		
		System.out.print(sb);
	}
	

	

}
