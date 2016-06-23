package cp4_5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ArbitrageII_UVa436 {

	static final double EPS =1e-9, INF = 1e9;
	static int V;
	static double[][] adjMat;
	
	static boolean floyd()
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.max(adjMat[i][j], adjMat[i][k] * adjMat[k][j]);
		for(int i = 0; i < V; ++i)
			if(adjMat[i][i] > 1.0 + EPS)
				return true;
		return false;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			V = sc.nextInt();
			if(V == 0)
				break;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < V; ++i)
				map.put(sc.next(), i);
			adjMat = new double[V][V];
			for(int i = 0; i < V; ++i)
				adjMat[i][i] = 1.0;
			
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = map.get(sc.next());
				double d = sc.nextDouble();
				int v = map.get(sc.next());
				adjMat[u][v] = d;
			}
			out.format("Case %d: %s\n", tc++, floyd()?"Yes":"No");
			
		}
		out.flush();
		
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

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
