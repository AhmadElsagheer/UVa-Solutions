package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class JillRidesAgain_UVa507 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int k = 1; k <= TC; k++)
		{
			int N =  Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			int[] routes = new int[N-1];
			for(int i  = 0; i < N - 1; i++)
				routes[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			int a =-1, b = -1, max = 0, cur = 0, x=0;
			for(int i = 0; i < N - 1; i++)
			{
				cur += routes[i];
				if(cur < 0) { cur = 0; x = i + 1; continue;}
				if(cur > max) {max = cur; a = x; b = i + 1;}
				else if (cur == max && (i + 1 - x > b - a)){a = x; b = i + 1;}
			}
			if(a==-1)
				out.printf("Route %d has no nice parts\n",k);
			else
				out.printf("The nicest part of route %d is between stops %d and %d\n",k,a+1,b+1);
			
		}
		out.flush();
		out.close();
	}
}
