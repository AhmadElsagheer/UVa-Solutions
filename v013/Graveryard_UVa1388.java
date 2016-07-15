package v013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Graveryard_UVa1388 {
	
	static final double INF = 1e9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			int n = sc.nextInt(), m = sc.nextInt();
			double d = 10000;
			double[] pos = new double[n];
			pos[0] = 0.0;
			for(int i = 1; i < n; i++) pos[i] = pos[i-1] + d / n;
			
			double[] newPos = new double[n+m];
			newPos[0] = 0.0;
			for(int i = 1; i < n + m; i++) newPos[i] = newPos[i-1] + d / (n + m);
			 
			double min = 0;
			for(int i = 0, j = 0; i < n; i++)
			{
				int idx = j;
				while(j < n + m)
				{
					if(Math.abs(newPos[j] - pos[i]) < Math.abs(newPos[idx] - pos[i]))
						idx = j;
					else if(newPos[j] > pos[i])
						break;
					++j;
				}
				min += Math.abs(newPos[idx] - pos[i]);			
			}
			out.printf("%.4f\n", min);
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
					res = Integer.parseInt(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Integer.parseInt(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
