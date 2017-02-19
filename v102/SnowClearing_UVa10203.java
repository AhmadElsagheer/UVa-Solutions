package v102;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class SnowClearing_UVa10203 {

	static final double speed = (double) 50 / 9;
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			sc.next(); sc.next();
			double d = 0.0;
			while(sc.ready() && !sc.nextIsEmpty())
			{
				double xx = sc.nextInt(), yy = sc.nextInt(), x = sc.nextInt(), y = sc.nextInt();
				d += Math.sqrt((xx - x) * (xx - x) + (yy - y) * (yy - y));				
			}
			int mm = (int)Math.round(d * 2 / speed / 60.0);
			out.format("%d:%02d\n", mm/60, mm%60);
			if(tc != 0)
				out.println();
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

		public boolean nextIsEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}
