package cp4_4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class KrochanskaIsHere_UVa11792 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), S = sc.nextInt();
			ArrayList<Integer>[] adjList = new ArrayList[N];
			int[] importance = new int[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(S-->0)
			{
				int lst = -1;
				while(true)
				{
					int cur = sc.nextInt() - 1;
					if(cur == -1)
						break;
					importance[cur]++;
					if(lst != -1)
					{
						adjList[lst].add(cur);
						adjList[cur].add(lst);
					}
					lst = cur;
				}
			}
			
			int ans = -1, min = (int)1e9;
			for(int i = 0; i < N; ++i)
				if(importance[i] > 1)
				{
					int cur = 0;
					Queue<Integer> q = new LinkedList<Integer>();
					int[] dist = new int[N];
					Arrays.fill(dist, -1);
					q.add(i); dist[i] = 0;
					while(!q.isEmpty())
					{
						int u = q.remove();
						if(importance[u] > 1)
							cur += dist[u];
						for(int v: adjList[u])
							if(dist[v] == -1)
							{
								dist[v] = dist[u] + 1;
								q.add(v);
							}
					}
					if(cur < min)
					{
						min = cur;
						ans = i + 1;
					}
				}
			out.printf("Krochanska is in: %d\n", ans);
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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