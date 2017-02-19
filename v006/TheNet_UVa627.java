package v006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TheNet_UVa627 {

	static final int INF = 100000;
	static int V , adjMat[][], p[][];
	static ArrayList<Integer> out;
	
	static void floyd()
	{
		p = new int[V][V];
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				p[i][j] = i;
		
		for(int k = 0; k < V; k++)
			for(int i = 0; i < V; i++)
				for(int j = 0; j < V; j++)
					if(adjMat[i][j] > adjMat[i][k] + adjMat[k][j])
					{
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
						p[i][j] = p[k][j];
					}
	}
	
	static void print(int i, int j)
	{
		if(i != j) print(i, p[i][j]);
		out.add(j);
	}
	
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(in.ready())
		{
			V = in.nextInt();
			adjMat = new int[V][V];
			for(int i = 0; i < V; i++)
			{
				StringTokenizer st = new StringTokenizer(in.nextLine(),"-");
				int u = Integer.parseInt(st.nextToken()) - 1;
				Arrays.fill(adjMat[u], INF);
				adjMat[u][u] = 0;
				if(!st.hasMoreTokens())
					continue;
				st = new StringTokenizer(st.nextToken(),",");
				while(st.hasMoreTokens())
				{
					int v = Integer.parseInt(st.nextToken()) - 1;
					adjMat[u][v] = 1;
				}
			}
			floyd();
			sb.append("-----\n");
			int m = in.nextInt();
			while(m-->0)
			{
				int u = in.nextInt() - 1, v = in.nextInt() - 1;
				if(adjMat[u][v] == INF)
					sb.append("connection impossible\n");
				else
				{
					out = new ArrayList<Integer>();
					print(u,v);
					for(int i = 0; i < out.size() - 1; i++)
						sb.append(out.get(i)+1).append(" ");
					sb.append(out.get(out.size()-1)+1).append("\n");
				}
			}
		}
		System.out.print(sb);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static class InputReader {
		StringTokenizer st;
		BufferedReader br;

		public InputReader(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public String nextLine() throws IOException {st = null; return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}


	}


}
