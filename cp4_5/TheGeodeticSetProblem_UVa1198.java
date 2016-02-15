package cp4_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TheGeodeticSetProblem_UVa1198 {

	static final int INF = (int)1e7;
	static ArrayList<Integer>[][] I;
	static int V, adjMat[][];
	
	static void floyd()
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
		I = new ArrayList[V][V];
		for(int i = 0; i < V; ++i)
			for(int j = 0; j < V; ++j)
				if(adjMat[i][j] != INF)
				{
					I[i][j] = new ArrayList<Integer>();
					for(int k = 0; k < V; ++k)
						if(adjMat[i][j] == adjMat[i][k] + adjMat[k][j])
							I[i][j].add(k);
					
				}
	}
	
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		V = sc.nextInt();
		adjMat = new int[V][V];
		for(int i = 0; i < V; ++i)
		{
			Arrays.fill(adjMat[i], INF);
			adjMat[i][i] = 0;
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			while(st.hasMoreTokens())
				adjMat[i][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		floyd();
		int q = sc.nextInt();
		while(q-->0)
		{
			TreeSet<Integer> D = new TreeSet<Integer>();
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int[] d = new int[st.countTokens()];
			for(int i = 0; i < d.length; ++i)
				d[i] = Integer.parseInt(st.nextToken()) - 1;
			for(int i : d)
				for(int j : d)
					if(I[i][j] != null)
						for(int v : I[i][j])
							D.add(v);
			out.println(D.size() == V ? "yes" : "no");
		}
		out.flush();
		out.close();
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
	
}


