package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class PolynomialTimeReductions_UVa1243 {

	static boolean[][] transitiveClosure(int V, ArrayList<Integer>[] adjList)
	{
		boolean[][] adjMat = new boolean[V][V];
		for(int u = 0; u < V; ++u)
			for(int v: adjList[u])
				adjMat[u][v] = true;

		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = adjMat[i][j] || adjMat[i][k] && adjMat[k][j];

		return adjMat;
	}

	static int go(int V, ArrayList<Integer>[] adjList)
	{
		int ans = 0;
		//find SCC and construct DAG
		boolean[][] adjMat = transitiveClosure(V, adjList);
		int[] findSCC = new int[V];
		int SCC = 0;
		for(int u = 0; u < V; ++u)
			if(findSCC[u] == 0)
			{
				findSCC[u] = ++SCC;
				int sizeSCC = 1;
				for(int v = u + 1; v < V; ++v)
					if(adjMat[u][v] && adjMat[v][u])
					{
						findSCC[v] = SCC;
						++sizeSCC;
					}
				if(sizeSCC != 1)
					ans += sizeSCC;
			}

		ArrayList<Integer>[] DAG = new ArrayList[SCC];
		for(int i = 0; i < SCC; ++i)
			DAG[i] = new ArrayList<Integer>();

		for(int u = 0; u < V; ++u)
		{
			int s1 = findSCC[u] - 1;
			for(int v: adjList[u])
			{
				int s2 = findSCC[v] - 1;
				if(s1 != s2)
					DAG[s1].add(s2);
			}
		}

		//compute T(DAG) and remove extra direct edges
		adjMat = transitiveClosure(SCC, DAG);
		for(int u = 0; u < SCC; ++u)
			for(int v = 0; v < SCC; ++v)
				if(adjMat[u][v])
				{
					boolean important = true;
					for(int w: DAG[u])
						if(v != w && adjMat[w][v])
							important = false;
					if(important)
						++ans;
				}
		return ans;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{ 	
			int V = sc.nextInt(), E = sc.nextInt();
			if(V == 0)
				break;

			ArrayList<Integer>[] adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
			}
			out.printf("Case %d: %d\n", tc++, go(V, adjList));
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

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

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

		public boolean ready() throws IOException {return br.ready();}


	}
}