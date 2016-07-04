package v121;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UnlockTheLock_UVa12160 {

	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int L = sc.nextInt(), U = sc.nextInt(), R = sc.nextInt();
			if(L == 0 && U == 0 && R == 0)
				break;
			int[] VR = new int[R];
			for(int i  = 0; i < R; ++i)
				VR[i] = sc.nextInt();
			int[] dist = new int[10000];
			dist[L] = 1;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(L);
			while(!q.isEmpty())
			{
				int u = q.remove(), d = dist[u];
				if(u == U)
					break;
				for(int x: VR)
				{
					int v = (u + x) % 10000;
					if(dist[v] == 0)
					{
						dist[v] = d + 1;
						q.add(v);
					}
				}
			}
			
			if(dist[U] == 0)
				out.printf("Case %d: Permanently Locked\n", tc++);
			else
				out.printf("Case %d: %d\n", tc++, dist[U] - 1);
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