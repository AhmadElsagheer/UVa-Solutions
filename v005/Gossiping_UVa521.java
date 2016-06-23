package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Gossiping_UVa521 {


	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int n = sc.nextInt(), d = sc.nextInt(), s = sc.nextInt();
			if(n == 0)
				break;
			int[] bus = new int[d], startPoint = new int[d], busLine[] = new int[n][];
			for(int i = 0; i < n; ++i)
			{
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				int len = st.countTokens();
				busLine[i] = new int[len];
				for(int j = 0; j < len; ++j)
					busLine[i][j] = Integer.parseInt(st.nextToken()) - 1;
				st = new StringTokenizer(sc.nextLine());
				int p = st.countTokens() / 2;
				while(p-->0)
				{
					int stop = Integer.parseInt(st.nextToken()) - 1;
					int driver = Integer.parseInt(st.nextToken()) - 1;
					bus[driver] = i;
					for(int k = 0; k < len; ++k)
						if(busLine[i][k] == stop)
						{
							startPoint[driver] = k;		//store index (relative to bus line of start stop)
							break;
						}
				}
			}

			boolean[][] adjMat = new boolean[d][d];
			for(int i = 0; i < d; ++i)
				for(int j = i + 1; j < d; ++j)
				{
					int[] x = busLine[bus[i]], y = busLine[bus[j]];
					int lcm = lcm(x.length, y.length);
					int[] visitedStop = new int[lcm];
					//make a tour for driver i
					for(int k = 0, pos = startPoint[i]; k < lcm; ++k, pos = (pos + 1) % x.length)
						visitedStop[k] = x[pos];
					//make a tour for driver j and check meeting
					boolean meet = false;
					for(int k = 0, pos = startPoint[j]; k < lcm; ++k, pos = (pos + 1) % y.length)
						if(visitedStop[k] == y[pos])
						{
							meet = true;
							break;
						}

					//if they met, make a connection
					if(meet)
						adjMat[i][j] = adjMat[j][i] = true;
				}

			//check for graph connectivity
			for(int k = 0; k < d; ++k)
				for(int i = 0; i < d; ++i)
					for(int j = 0;j < d; ++j)
						adjMat[i][j] |= adjMat[i][k] && adjMat[k][j];

			boolean connected = true;
			for(int i = 0; i < d; ++i)
				for(int j = i + 1; j < d; ++j)
					if(!adjMat[i][j])
						connected = false;
			out.println(connected ? "Yes" : "No");
		}
		out.flush();
		out.close();
	}

	static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); }

	static int lcm(int a, int b) { return a / gcd(a, b) * b; }

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