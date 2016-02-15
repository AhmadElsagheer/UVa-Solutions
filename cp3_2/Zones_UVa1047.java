package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Zones_UVa1047 {

	static int[][] commonAreas;
	static boolean[] used;
	static int[] customers;
	static boolean[] sol;
	static int N,T,M, max;
	static void backtrack(int k, int built,int total)
	{
		if(built==T)
		{
			total -= commonCustomers();
			if(total > max)
			{
				max = total;
				for(int i = 0; i < N; i++)
					sol[i] = used[i]; 
			}
			return;
		}
		if(k==N)
			return;
		used[k] = true;
		backtrack(k+1, built+1,customers[k] + total);
		used[k] = false;
		backtrack(k+1, built,total);
	}
	
	static int commonCustomers()
	{
		int customers = 0;
		for(int i = 0; i < M; i++)
		{
			int count = 0;
			for(int j = 0; j < commonAreas[i].length - 1; j++)
				if(used[commonAreas[i][j]])
					count++;
			if(count==0)
				continue;
			count--;
			customers += count*commonAreas[i][commonAreas[i].length - 1];
		}
			
		return customers;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int k = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			if(N == 0)
				break;
			customers = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				customers[i] = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			commonAreas = new int[M][];
			for(int i = 0; i < M; i++)
			{
				st= new StringTokenizer(br.readLine());
				int  t = Integer.parseInt(st.nextToken());
				commonAreas[i] = new int[t+1];
				for(int j = 0; j < t; j++)
					commonAreas[i][j] = Integer.parseInt(st.nextToken()) - 1;
				commonAreas[i][t] = Integer.parseInt(st.nextToken());
			}
			used = new boolean[N];
			sol = new boolean[N];
			max = 0;
			backtrack(0,0,0);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < N; i++)
				if(sol[i])
					sb.append(" ").append(i+1);
			out.printf("Case Number  %d\n",k++);
			out.printf("Number of Customers: %d\n",max);
			out.printf("Locations recommended:%s\n\n",sb);
			
		}
		out.flush();
		
	}
	
}
