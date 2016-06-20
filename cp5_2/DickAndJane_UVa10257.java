package cp5_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class DickAndJane_UVa10257 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int s = sc.nextInt(), p = sc.nextInt(), y = sc.nextInt(), j = sc.nextInt() + 12;
			boolean done = false;
			for(int X = 1; !done && X <= j; ++X)
				for(int Y = 1; !done && X + Y <= j; ++Y)
				{
					int Z = j - X - Y;
					int xy = 0, xz = 0, yz = 0;
					if(X == Y + s + 1)
						xy = 1;
					else if(X != Y + s)
						continue;
					if(X == Z + y + 1)
						xz = 1;
					else if(X != Z + y)
						continue;
					if(Y == Z + p + 1)
						xy = 1;
					else if(Y != Z + p)
						continue;
					if(xy == 1 && yz == 1 && xz != 1)
						continue;

					out.println(X + " " + Y + " " + Z);
					done = true;

				}
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}