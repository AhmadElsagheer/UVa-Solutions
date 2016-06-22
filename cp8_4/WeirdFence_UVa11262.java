package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class WeirdFence_UVa11262 {
	
	static int n, m, match[];
	static ArrayList<Edge>[] adjList;
	static ArrayList<Pole> blue, red;
	static boolean[] vis;
	
	static int maxMatches(int len)
	{
		int matches = 0;
		match = new int[m];
		Arrays.fill(match, -1);
		for(int i = 0; i < n; ++i)
		{
			vis = new boolean[n];
			matches += aug(i, len);
		}
		return matches;
	}
	
	static int aug(int u, int len)
	{
		vis[u] = true;
		for(Edge e: adjList[u])
			if(e.d2 <= len && (match[e.v] == -1 || !vis[match[e.v]] && aug(match[e.v], len) == 1))
			{
				match[e.v] = u;
				return 1;
			}
		return 0;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int P = sc.nextInt(), k = sc.nextInt();
			blue = new ArrayList<Pole>(P>>1);
			red = new ArrayList<Pole>(P>>1);
			while(P-->0)
			{
				Pole p = new Pole(sc.nextInt(), sc.nextInt());
				if(sc.next().equals("blue"))
					blue.add(p);
				else
					red.add(p);
			}
			n = blue.size();
			m = red.size();
			adjList = new ArrayList[n];
			for(int i = 0; i < n; ++i)
				adjList[i] = new ArrayList<Edge>();
			for(int i = 0; i < n; ++i)
			{
				Pole p = blue.get(i);
				for(int j = 0; j < m; ++j)
				{
					Pole q = red.get(j);
					adjList[i].add(new Edge(j, p.dist2(q)));
				}
			}
			int ans = -1, lo = 0, hi = (int)1e4;
			while(lo <= hi)
			{
				int mid = lo + hi >> 1;
				if(maxMatches(mid * mid) >= k)
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			if(ans != -1)
				out.println(ans);
			else
				out.println("Impossible");
		}
		out.flush();
		out.close();
	}

	static class Pole
	{
		int x, y;

		Pole(int a, int b) { x = a; y = b; }
		
		int dist2(Pole p) {	return sq(x - p.x) + sq(y - p.y); }
		
		int sq(int x) { return x * x; }
	}
	
	static class Edge
	{
		int v, d2;
		
		Edge(int x, int y) { v = x; d2 = y; }
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