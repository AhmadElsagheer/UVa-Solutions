package v113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Minefield_UVa11338 {

	static final int INF = 1000000000;
	static ArrayList<Pair>[] adjList;
	static double d;
	static int N;
	static double[][] pos;
	
	static boolean dijkstra()
	{
		double[] dist = new double[N];
		Arrays.fill(dist, INF);
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		dist[0] = 0;
		q.add(new Pair(0,0));
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			if(cur.distance > dist[cur.point])
				continue;
			for(int i = 0, size = adjList[cur.point].size(); i < size; i++)
			{
				Pair v = adjList[cur.point].get(i);
				double totalDist = cur.distance + v.distance;
				if(totalDist < dist[v.point])
				{
					dist[v.point] = totalDist;
					q.add(new Pair(v.point,dist[v.point]));
				}
			}
		}
		if(dist[1] < d || Math.abs(dist[1]-d)<10e-7)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb  = new StringBuilder();
		String line;StringTokenizer st;
		while(true)
		{
			line = br.readLine();
			if(line.charAt(0)=='*')
				break;
			st = new StringTokenizer(line);
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(br.readLine()) + 2;
			pos = new double[N][2];
			pos[1][0] = w;pos[1][1] = h;
			for(int i = 2; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				pos[i][0] = getDouble(st.nextToken());
				pos[i][1] = getDouble(st.nextToken());
			}
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Pair>();
			for(int i = 0; i < N; i++)
				for(int j = i + 1; j < N; j++)
				{
					double a = pos[i][0]-pos[j][0];
					double b = pos[i][1]-pos[j][1];
					double x = Math.sqrt(a*a+b*b);
					if(x < 1.5 || Math.abs(x-1.5)<10e-7)
					{
						adjList[i].add(new Pair(j,x));
						adjList[j].add(new Pair(i,x));
					}
				}
			d = getDouble(br.readLine());
			if(dijkstra())
				sb.append("I am lucky!\n");
			else
				sb.append("Boom!\n");
		}
		
		System.out.print(sb);
	}
	
	static double getDouble(String x)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < x.length(); i++)
			if(x.charAt(i) >= '0' && x.charAt(i) <= '9')
				sb.append(x.charAt(i));
		return Integer.parseInt(sb.toString())/100.0;
	}
	
	static class Pair implements Comparable<Pair>
	{
		int point;
		double distance;
		Pair(int x, double y) {point = x; distance = y;}
		@Override
		public int compareTo(Pair o) {
			return this.distance - o.distance < 0 ? -1 : 1;
		}	
	}
}