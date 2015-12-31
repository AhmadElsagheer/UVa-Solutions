package cp4_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Rendezvous_UVa11015 {

	static final int INF = (int)1e8;
	static int N;
	static int[][] adjMat;
	
	static void floyd()
	{
		for(int k = 0; k < N; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
	}
	
	static int sum(int dest)
	{
		int ans = 0;
		for(int i = 0; i < N; i++)
			ans += adjMat[i][dest];
		return ans;
	}
	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int k = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0) break;
			int m = sc.nextInt();
			
			String[] names = new String[N];
			for(int i = 0; i < N; i++) names[i] = sc.next();
			
			adjMat = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
			}
			while(m-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjMat[u][v] = adjMat[v][u] = sc.nextInt();
			}
			floyd();
			int min = INF, idx = -1;
			for(int i = 0; i < N; i++)
			{
				int cur = sum(i);
				if(cur < min || cur == min && names[i].compareTo(names[idx]) < 0)
				{
					idx = i;
					min = cur;
				}
			}
			out.printf("Case #%d : %s\n", k++, names[idx]);
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

		int countTokens() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens();
		}
	}
}
