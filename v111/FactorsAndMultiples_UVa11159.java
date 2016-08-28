package v111;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FactorsAndMultiples_UVa11159 {

	static int V, n, m, match[];	//n(left) + m(right) = V
	static ArrayList<Integer>[] adjList;	//size = n, idx = [0, n-1], val = [0, m-1]
	static boolean[] vis;
	static int go()
	{
		//build unweighted bipartite graph with directed edges left->right set
		int matches = 0;
		match = new int[m];
		Arrays.fill(match, -1);
		for(int i = 0; i < n; ++i)
		{
			vis = new boolean[n];
			matches += aug(i);
		}
		return matches;
	}
	
	static int aug(int u)	//returns 1 if an augment path is found
	{
		vis[u] = true;
		for(int v : adjList[u])
			if(match[v] == -1 || !vis[match[v]] && aug(match[v]) == 1)
			{
				match[v] = u;
				return 1;
			}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			m = sc.nextInt();
			int[] A = new int[m];
			for(int i = 0; i < m; ++i)
				A[i] = sc.nextInt();
			n = sc.nextInt();
			adjList = new ArrayList[n];
			for(int i = 0; i < n; ++i)
			{
				adjList[i] = new ArrayList<Integer>();
				int b = sc.nextInt();
				for(int j = 0; j < m; ++j)
					if(b == 0 || A[j] != 0 && b%A[j] == 0)
						adjList[i].add(j);
			}
			out.format("Case %d: %d\n", t, go());
		}
		out.flush();
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
