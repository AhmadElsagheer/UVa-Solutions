package v124;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CarefulTeacher_UVa12460 {

	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		int N = 0;
		while(true)
		{
			String s = sc.next();
			if(s.equals("--"))
				break;
			map.put(s, N++);
		}
		
		UnionFind uf = new UnionFind(N);
		for(Entry<String, Integer> e1: map.entrySet())
			for(Entry<String, Integer> e2: map.entrySet())
				if(!uf.sametSet(e1.getValue(), e2.getValue()) && can(e1.getKey(), e2.getKey()))
					uf.union(e1.getValue(), e2.getValue());
		
		while(sc.ready())
		{
			int u = map.get(sc.next()), v = map.get(sc.next());
			out.println(uf.sametSet(u, v) ? "Yes" : "No");
		}
	
		out.flush();
		out.close();
	}
	
	static boolean can(String x, String y)
	{
		int miss = 0, len = x.length();
		if(len != y.length())
			return false;
		for(int i = 0; i < len; ++i)
			if(x.charAt(i) != y.charAt(i))
				if(miss == 1)
					return false;
				else
					miss = 1;
		return true;
	}
	
	static class UnionFind
	{
		int[] rank, p;
		
		UnionFind(int N)
		{
			rank = new int[N];
			p = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		boolean sametSet(int x, int y) { return find(x) == find(y); }
		
		int find(int x) { return p[x] == x ? x : (p[x] = find(p[x])); }
		
		void union(int x, int y)
		{
			x = find(x);
			y = find(y);
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
	}
}