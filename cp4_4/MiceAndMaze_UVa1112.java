package cp4_4;

import java.util.*;
import java.io.*;

public class MiceAndMaze_UVa1112 {

	static int[][] adjMatrix;
	
	public static int dijkstra(int S, int T)
	{
		int[] time = new int[adjMatrix.length];
		Arrays.fill(time, 100000000);
		time[S] = 0;
		PriorityQueue<Pair> x = new PriorityQueue<Pair>();
		x.add(new Pair(S,0));
		while(!x.isEmpty())
		{
			Pair cur = x.remove();
			if(cur.time>time[cur.cell])
				continue;
			for(int i = 0; i < adjMatrix.length; i++)
			{
				if(adjMatrix[cur.cell][i]==-1)
					continue;
				if(cur.time + adjMatrix[cur.cell][i] < time[i])
				{
					time[i] = cur.time + adjMatrix[cur.cell][i];
					x.add(new Pair(i,time[i]));
				}
			}
		}
		return time[T];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(TC-->0)
		{
			br.readLine();
			int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			adjMatrix = new int[N][N];
			for(int i = 0; i < N; i++)
				Arrays.fill(adjMatrix[i], -1);
			int E = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()) - 1;
			int T = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			int M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			while(M-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int time = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = time;
			}
			int count = 0;
			for(int i = 0; i < N; i++)
				if(dijkstra(i,E)<=T)
					count++;
			
			sb.append(count+"\n");
			if(TC!=0)
				sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	static	class Pair implements Comparable<Pair>
	{
		int cell, time;
		Pair(int c, int t) {cell = c; time = t;}
		
		public int compareTo(Pair x) {
			return this.time - x.time;
		}
		
	}
	
}