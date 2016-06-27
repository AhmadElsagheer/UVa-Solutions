package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BirthdayCake_UVa10167 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int N = sc.nextInt()<<1;
			if(N == 0)
				break;
			Cherry[] cherries = new Cherry[N];
			for(int i = 0; i < N; ++i)
				cherries[i] = new Cherry(sc.nextInt(), sc.nextInt());
			boolean found = false;
			for(int A = -500; !found && A <= 500; ++A)
				for(int B = -500; !found && B <= 500; ++B)
				{
					int f1 = 0, f2 = 0;
					if(B == 0)
					{
						for(Cherry c: cherries)
							if(c.x < 0)
								++f1;
							else if(c.x > 0)
								++f2;
							else
							{
								f1 = -1;
								break;
							}
					}
					else
						for(Cherry c: cherries)
						{
							int z = A * c.x + B * c.y;
							if(z > 0)
								f1++;
							else if(z < 0)
								f2++;
							else
							{
								f1 = -1;
								break;
							}
						}
					if(f1 == f2)
					{
						out.println(A + " " + B);
						found = true;
					}
				}
				
		}
		out.flush();
		out.close();
	}

	static class Cherry
	{
		int x, y;
		
		Cherry(int a, int b) { x = a; y = b; }
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

		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}