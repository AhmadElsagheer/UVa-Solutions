package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RingsAndGlue_UVa10301 {

	static final double EPS = 1e-12;
	static boolean[][] adjMat;
	static boolean[] visited;
	static int n;
	
	static int cc(int u)
	{
		visited[u] = true;
		int ret = 1;
		for(int i = 0; i < n; ++i)
			if(adjMat[u][i] && !visited[i])
				ret += cc(i);
		return ret;
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			n = sc.nextInt();
			if(n == -1)
				break;
			Ring[] rings = new Ring[n];
			for(int i = 0; i < n; ++i)
				rings[i] = new Ring(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
			adjMat = new boolean[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
				{
					double d2 = rings[i].dist2(rings[j]);
					if(d2 < sq(rings[i].r + rings[j].r) + EPS && d2 + EPS > sq(Math.abs(rings[i].r - rings[j].r)))
						adjMat[i][j] = adjMat[j][i] = true;
				}
			
			visited = new boolean[n];
			int ans = 0;
			for(int i = 0; i < n; ++i)
				if(!visited[i])
					ans = Math.max(ans, cc(i));
			out.printf("The largest component contains %d ring%s.\n", ans, ans == 1?"":"s");
		}
		
		out.flush();
		out.close();
	}	
	
	static double sq(double x)
	{
		return x * x;
	}
	
	static class Ring
	{
		double x, y, r;
		
		Ring(double a, double b, double c)
		{
			x = a;
			y = b;
			r = c;
		}
		
		double dist2(Ring p)
		{
			return sq(x - p.x) + sq(y - p.y);
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

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
