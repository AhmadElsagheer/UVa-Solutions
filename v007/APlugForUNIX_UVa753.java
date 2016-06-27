package v007;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class APlugForUNIX_UVa753 {
	
	static int n, m, match[];
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	
	static int maxMatches()
	{
		int matches = 0;
		match = new int[m];
		Arrays.fill(match, -1);
		for(int i = 0; i < n; ++i)
		{
			visited = new boolean[n];
			matches += aug(i);
		}
		return matches;
	}
	
	static int aug(int u)
	{
		visited[u] = true;
		for(int v: adjList[u])
			if(match[v] == -1 || !visited[match[v]] && aug(match[v]) == 1)
			{
				match[v] = u;
				return 1;
			}
		return 0;	
	}

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			TreeMap<String, Integer> plugs = new TreeMap<String, Integer>();
			boolean[][] canConvert = new boolean[400][400];
			
			// 1. initial plugs
			int types = m = sc.nextInt();
			for(int i = 0; i < m; ++i)
				plugs.put(sc.next(), i);
		
			// 2. connection requests
			n = sc.nextInt();
			int[] requests = new int[n];
			for(int i = 0; i < n; ++i)
			{
				sc.next();
				String s = sc.next();
				Integer p = plugs.get(s);
				if(p == null)
					plugs.put(s, p = types++);
				requests[i] = p;
			}
			
			// 3. adapter connections
			int c = sc.nextInt();
			for(int i = 0; i < c; ++i)
			{
				String s1 = sc.next(), s2 = sc.next();
				Integer x = plugs.get(s1), y = plugs.get(s2);
				if(x == null)
					plugs.put(s1, x = types++);
				if(y == null)
					plugs.put(s2, y = types++);
				canConvert[x][y] = true;
			}
			
			// 4. Transitive Closure for convertability
			for(int k = 0; k < types; ++k)
				for(int i = 0; i < types; ++i)
					for(int j = 0; j < types; ++j)
						canConvert[i][j] |= canConvert[i][k] && canConvert[k][j];
			
			// 5. Bipartite graph :: devices => plugs
			adjList = new ArrayList[n];
			for(int i = 0; i < n; ++i)
			{
				adjList[i] = new ArrayList<Integer>();
				int r = requests[i];
				for(int j = 0; j < m; ++j)
					if(j == r || canConvert[r][j])
						adjList[i].add(j);
			}
			out.println(n - maxMatches());	
			if(tc != 0)
				out.println();
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}