package cp4_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class NewToBangladesh_UVa10525
{

	static final int INF = 1000000000;
	static int V, adjMat[][], time[][];
	
	static void floyd()
	{
		for(int k = 0; k < V; k++)
			for(int i = 0; i < V; i++)
				for(int j = 0; j < V; j++)
					if(time[i][j] > time[i][k] + time[k][j])
					{
						time[i][j] = time[i][k] + time[k][j];
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
					}
					else
						if(time[i][j] == time[i][k] + time[k][j] && adjMat[i][j] > adjMat[i][k] + adjMat[k][j])
							adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
						
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			V = sc.nextInt();
			int R = sc.nextInt();
			adjMat = new int[V][V];
			time = new int[V][V];
			
			for(int i = 0; i < V; i++)
			{
				Arrays.fill(adjMat[i], INF);
				Arrays.fill(time[i], INF);
				adjMat[i][i] = time[i][i] = 0;
			}
					
			while(R-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				int t = sc.nextInt(), d = sc.nextInt();
				if(t < time[u][v])
				{
					adjMat[u][v] = adjMat[v][u] = d;
					time[u][v] = time[v][u] = t;
				}
				else
					if(t == time[u][v] && d < adjMat[u][v])
						adjMat[u][v] = adjMat[v][u] = d;
			}
			floyd();
			int q = sc.nextInt();
			while(q-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				if(adjMat[u][v] == INF)
					sb.append("No Path.\n");
				else
					sb.append("Distance and time to reach destination is "+adjMat[u][v]+" & "+time[u][v]+".\n");
			}
			if(tc != 0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
	

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}


	}

}

