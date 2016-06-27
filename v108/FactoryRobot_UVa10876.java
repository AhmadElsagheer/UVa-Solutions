package v108;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FactoryRobot_UVa10876 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt();
			Pillar[] pillars = new Pillar[N];
			for(int i = 0; i < N; ++i)
				pillars[i] = new Pillar(sc.nextInt(), sc.nextInt(), sc.nextInt());
			double lo = 0, hi = 1000;
			for(int k = 0; k < 100; ++k)
			{
				double d = lo + hi;
				UnionFind uf = new UnionFind(N + 4);
				for(int i = 0; i < N; ++i)
				{
					Pillar p = pillars[i];
					//connect with walls
					if(p.x - p.r + EPS < d)
						uf.union(i + 4, 0);
					if(p.y - p.r + EPS < d)
						uf.union(i + 4, 1);
					if(1000- (p.x + p.r) + EPS < d)
						uf.union(i + 4, 2);
					if(1000 - (p.y + p.r) + EPS < d)
						uf.union(i + 4, 3);
					
					//connect with pillars
					for(int j = i + 1; j < N; ++j)
						if(p.dist(pillars[j]) + EPS < d)
							uf.union(i + 4, j + 4);
				}
				boolean can = true;
				for(int i = 0; i < 4; ++i)
					for(int j = i + 1; j < 4; ++j)
						if(uf.sameSet(i, j))
							can = false;
				if(can)
					lo = d / 2;
				else
					hi = d / 2;
			}
			out.printf("%.3f\n", lo);
		}
		out.flush();
		out.close();
	}
	
	static class Pillar
	{
		int x, y, r;
		
		Pillar(int a, int b, int c) { x = a; y = b; r = c; }	
		
		double dist(Pillar p) { return Math.sqrt(sq(x - p.x) + sq(y - p.y)) - (r + p.r); }
		
		int sq(int x) { return x * x; }
	}
	
	static class UnionFind
	{
		int[] p, rank;
		
		UnionFind(int N)
		{
			rank = new int[N];
			p = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		boolean sameSet(int x, int y) { return findSet(x) == findSet(y); }
		
		int findSet(int x) { return p[x] == x ? x : (p[x] = findSet(p[x])); }
		
		void union(int x, int y)
		{
			x = findSet(x);
			y = findSet(y);
			if(x == y)
				return;
			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					++rank[y];
			}
		}
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