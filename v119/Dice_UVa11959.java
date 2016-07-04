package v119;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Dice_UVa11959 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[] d1 = sc.next().toCharArray(), d2 = sc.next().toCharArray();
			boolean same = false;
			for(int k = 0; !same && k < 2; ++k, rotateLeft(d1))
				for(int i = 0; !same && i < 4; ++i, rotateDown(d1))
					for(int j = 0; !same && j < 4; ++j, rotateLeft(d1))
						same |= same(d1, d2);
			out.println(same ? "Equal": "Not Equal");
		}
		
		out.flush();
		out.close();
	}
	
	static boolean same(char[] x, char[] y)
	{
		for(int i = 0; i < 6; ++i)
			if(x[i] != y[i])
				return false;
		return true;
	}
	
	static void rotateLeft(char[] dice)
	{
		char tmp = dice[2];
		for(int i = 2; i < 5; ++i)
			dice[i] = dice[i+1];
		dice[5] = tmp;
	}
	
	static void rotateDown(char[] dice)
	{
		int[] idx = new int[] {0, 4, 1, 2};
		char tmp = dice[0];
		for(int i = 0; i < 3; ++i)
			dice[idx[i]] = dice[idx[i+1]];
		dice[2] = tmp;
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