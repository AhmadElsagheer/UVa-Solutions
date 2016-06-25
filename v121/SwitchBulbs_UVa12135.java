package v121;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SwitchBulbs_UVa12135 {
	
	static int[] bfs(int[] a, int n, int m)
	{
		int[] dist = new int[1<<n];
		Arrays.fill(dist, -1);
		dist[0] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		while(!q.isEmpty())
		{
			int cur = q.remove();
			for(int i = 0; i < m; ++i)
			{
				int nxt = cur ^ a[i];
				if(dist[nxt] == -1)
				{
					dist[nxt] = dist[cur] + 1;
					q.add(nxt);
				}
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt(), m = sc.nextInt(), a[] = new int[m];
			for(int i = 0; i < m; ++i)
			{
				int k = sc.nextInt(), bulbs = 0;
				while(k-->0)
					bulbs |= 1 << sc.nextInt();
				a[i] = bulbs;
			}
			int[] dist = bfs(a, n, m);
			
			out.printf("Case %d:\n", t);
			int q = sc.nextInt();
			while(q-->0)
				out.println(dist[Integer.parseInt(sc.next(), 2)]);
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