package v011;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class XPlosives_UVa1160 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			UnionFind uf = new UnionFind(100001);
			int ans = 0;
			while(true)
			{
				int x = sc.nextInt();
				if(x == -1)
					break;
				int y = sc.nextInt();
				ans += uf.union(x, y);
			}
			out.println(ans);
		}
		out.flush();
		out.close();

	}
	
	static class UnionFind
	{
		int[] p, rank;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		int findSet(int x) { return p[x] == x ? x : (p[x] = findSet(p[x]));	}
		
		int union(int x, int y)
		{
			x = findSet(x);
			y = findSet(y);
					
			if(x == y)
				return 1;
			
			if(rank[x] > rank[y])
				p[y] = x;

			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					rank[y]++;
			}
			
			return 0;
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


	}
}