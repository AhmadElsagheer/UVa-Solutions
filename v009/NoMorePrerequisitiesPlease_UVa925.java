package v009;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class NoMorePrerequisitiesPlease_UVa925 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		int tc = sc.nextInt();
		while(tc-->0)
		{

			// 1. input
			int V = sc.nextInt();
			String[] names = new String[V];
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < V; ++i)
				map.put(names[i] = sc.next(), i);

			boolean[][] adjMat = new boolean[V][V];
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = map.get(sc.next()), k = sc.nextInt();
				while(k-->0)
				{
					int v = map.get(sc.next());
					adjMat[u][v] = true;
				}
			}

			// 2. transitive reduction
			for(int k = 0; k < V; ++k)
				for(int i = 0; i < V; ++i)
					for(int j = 0; j < V; ++j)
						adjMat[i][j] |= adjMat[i][k] && adjMat[k][j];

			boolean[][] res = new boolean[V][V];
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					if(adjMat[i][j])
					{
						boolean needed = true;
						for(int k = 0; needed && k < V; ++k)
							if(adjMat[i][k] && adjMat[k][j])
								needed = false;
						res[i][j] = needed;
					}

			for(Entry<String, Integer> e: map.entrySet())
			{
				int u = e.getValue();
				ArrayList<String> pre = new ArrayList<String>();
				for(int i = 0; i < V; ++i)
					if(res[u][i])
						pre.add(names[i]);
				if(pre.size() != 0)
				{
					Collections.sort(pre);
					sb.append(e.getKey() + " " + pre.size());
					for(String x: pre)
						sb.append(" " + x);
					sb.append("\n");
				}
			}
		}
		out.print(sb);
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