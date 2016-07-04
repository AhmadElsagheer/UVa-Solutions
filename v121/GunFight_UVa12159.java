package v121;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class GunFight_UVa12159 {

	static int n, m;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int[] match;
	
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
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			Tower[] towers = new Tower[N];
			for(int i = 0; i < N; ++i)
				towers[i] = new Tower(sc.nextInt(), sc.nextInt(), sc.nextInt());
			Tower f1 = towers[sc.nextInt() - 1], f2 = towers[sc.nextInt() - 1];
			
			ArrayList<Tower> team1 = new ArrayList<Tower>();
			ArrayList<Tower> team2 = new ArrayList<Tower>();
			for(Tower t: towers)
				if(t.p != 0)
					if(ccw(f1, f2, t))
						team1.add(t);
					else
						team2.add(t);
			
			if(team1.size() > team2.size())
			{
				ArrayList<Tower> tmp = team1;
				team1 = team2;
				team2 = tmp;
			}
			n = team1.size(); m = team2.size();
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; ++i)
				adjList[i] = new ArrayList<Integer>();
			int R = sc.nextInt();
			for(int i = 0; i < n; ++i)
			{
				Tower t = team1.get(i);
				for(int j = 0; j < m; ++j)
					if(t.goodFight(team2.get(j), R))
						adjList[i].add(j);
			}
			out.printf("Case %d: %d\n", tc++, maxMatches());
		}
		out.flush();
		out.close();
	}
	
	static boolean ccw(Tower p, Tower q, Tower r) { return new Vector(p, q).cross(new Vector(p, r)) > 0; }

	static class Tower
	{
		int x, y, p;
		
		Tower(int a, int b, int c) { x =  a; y = b; p = c; }
		
		boolean goodFight(Tower t, int R) { return dist2(t) <= R * R && p > t.p; }
		
		int dist2(Tower t) { return sq(x - t.x) + sq(y - t.y); }
		
		int sq(int x) { return x * x; }
		
	}
	
	static class Vector
	{
		int x, y;
		
		Vector(Tower p, Tower q) { x = q.x - p.x; y = q.y - p.y; }
		
		int cross(Vector v) { return x * v.y - y * v.x; }
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