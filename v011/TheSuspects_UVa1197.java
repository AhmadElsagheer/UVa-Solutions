package v011;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheSuspects_UVa1197 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			if(N == 0)
				break;
			UnionFind uf = new UnionFind(N);
			while(M-->0)
			{
				int k = sc.nextInt();
				if(k-- == 0)
					continue;
				int x = sc.nextInt();
				while(k-->0)
				{
					int y = sc.nextInt();
					uf.union(x, y);
				}
			}
			out.println(uf.setSize(0));
		}
		out.flush();
		out.close();
	}
	
	static class UnionFind
	{
		int[] p, rank, size;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			size = new int[N];
			for(int i = 0; i < N; ++i)
			{
				p[i] = i;
				size[i] = 1;
			}
		}
		
		int find(int x) { return p[x] == x ? x : (p[x] = find(p[x])); }
		
		void union(int x, int y)
		{
			x = find(x);
			y = find(y);
			if(x == y)
				return;
			if(rank[x] > rank[y])
			{
				p[y] = x;
				size[x] += size[y];
			}
			else
			{
				p[x] = y;
				size[y] += size[x];
				if(rank[x] == rank[y])
					++rank[y];
			}
		}
		
		int setSize(int x) { return size[find(x)]; }
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