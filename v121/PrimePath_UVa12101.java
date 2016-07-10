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

public class PrimePath_UVa12101 {
	
	static boolean[] isPrime;
	
	static void sieve(int N)
	{
		isPrime = new boolean[N];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i * i < N; ++i)
			if(isPrime[i])
				for(int j = i * i; j < N; j += i)
					isPrime[j] = false;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		sieve(100000);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int S = sc.nextInt(), T = sc.nextInt(), dist[] = new int[100000];
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(S);
			dist[S] = 1;
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == T)
					break;
				int d = dist[u];
				for(int i = 1; i < 10000; i *= 10)
				{
					int off = u % (i * 10) / i * i;
					u -= off;
					for(int j = i == 1000 ? 1 : 0; j < 10; ++j)
					{
						int v = u + i * j;
						if(dist[v] == 0 && isPrime[v])
						{
							dist[v] = d + 1;
							q.add(v);
						}
					}
					u += off;
				}
			}
			int ans = dist[T] - 1;
			if(ans == -1)
				out.println("Impossible");
			else
				out.println(ans);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}