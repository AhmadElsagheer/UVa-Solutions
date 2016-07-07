package v006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NonstopTravel_UVa617 {

	static final double EPS = 1e-9;

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int N = sc.nextInt();
			if(N == -1)
				break;
			Signal[] signals = new Signal[N];
			for(int i = 0; i < N; ++i)
				signals[i] = new Signal(sc.nextDouble(), sc.nextInt(), sc.nextInt(), sc.nextInt());

			ArrayList<Integer> sol = new ArrayList<Integer>();
			for(int v = 30; v <= 60; ++v)
			{
				boolean valid = true;
				for(Signal s: signals)
				{
					double t = s.L / v * 60 * 60;

					t %= s.G + s.Y + s.R;

					if(t > s.G + s.Y + EPS)
						valid = false;
				}

				if(valid)
					sol.add(v);
			}
			out.printf("Case %d:", tc++);
			if(sol.isEmpty())
				out.printf(" No acceptable speeds.\n");
			else
			{


				boolean first = true;
				for(int i = 0; i < sol.size(); ++i)
				{
					int x = sol.get(i), y = x, j;
					for(j = i + 1; j < sol.size() && sol.get(j) - 1 == y; ++j)
						y = sol.get(j);

					i = j - 1;
					if(first)
						first = false;
					else
						out.print(",");

					if(x == y)
						out.printf(" %d", x);
					else
						out.printf(" %d-%d", x, y);
				}
				out.println();
			}
		}

		out.flush();
		out.close();
	}


	static class Signal
	{
		double L;
		int G, Y, R;

		Signal(double a, int b, int c, int d) { L = a; G = b; Y = c; R = d; }
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