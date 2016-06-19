package cp5_2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class JackAndJill_UVa697 {
	
	static final double EPS = 1e-11, g = 32.2;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			double UP = sc.nextDouble();
			if(UP < EPS)
				break;
			if(tc != 1)
				out.println();
			double R = sc.nextDouble() / 24, L = sc.nextDouble() / 12, B = sc.nextDouble(), P = sc.nextDouble() / 12,
					DOWN  = sc.nextDouble(), V = sc.nextDouble();
			double V2 = V, L1 = L * 12, ans = 0.0;
			while(V2 > EPS)
			{
				double t1 = Math.sqrt(2 * L * g) / g;
				double remV = Math.min(V2, B);
				double t2 = L / P;
				L += remV / (Math.PI * R * R);
				ans += UP + DOWN + t1 + t2;
				V2 -= remV;
			}
			out.printf("Scenario %d:\n", tc++);
			out.printf("     up hill           %8.2f sec\n", UP);
			out.printf("     well diameter     %8.2f in\n", R * 24);
			out.printf("     water level       %8.2f in\n", L1);
			out.printf("     bucket volume     %8.2f cu ft\n", B);
			out.printf("     bucket ascent rate%8.2f in/sec\n", P * 12);
			out.printf("     down hill         %8.2f sec\n", DOWN);
			out.printf("     required volume   %8.2f cu ft\n", V);
			out.printf("     TIME REQUIRED     %8.2f sec\n", ans);
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){ br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){ br = new BufferedReader(r);}

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