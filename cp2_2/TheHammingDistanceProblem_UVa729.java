package cp2_2;

import java.util.*;
import java.io.*;

public class TheHammingDistanceProblem_UVa729 {

	static int N,H;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int max = (int)Math.pow(2, N) - 1;
			for(int i = 1; i <= max; i++)
				if(countOnes(i)==H)
				{
					out.printf("%0"+N+"d\n",Long.parseLong(Integer.toBinaryString(i)));
				}
			if(TC!=0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	public static int countOnes(int x)
	{
		int count  = 0;
		for(int i = 0; i < N; i++)
			if((x & (1<<i))!=0)
				count++;
				
		return count;
	}
}
