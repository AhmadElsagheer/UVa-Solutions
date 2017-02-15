package v112;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class HowOldAreYou_UVa11219 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			out.printf("Case #%d: ", t);
			String[] s1 = sc.next().split("/");
			String[] s2 = sc.next().split("/");
			
			int[] now = new int[3], birth = new int[3];
			for(int i = 0; i < 3; ++i)
			{
				now[i] = Integer.parseInt(s1[i]);
				birth[i] = Integer.parseInt(s2[i]);
			}
			int diff = getDiff(birth, now);
			if(diff < 0)
				out.println("Invalid birth date");
			else if(diff > 130)
				out.println("Check birth date");
			else
				out.println(diff);
			
		}
		out.flush();
		out.close();
	}
	
	static int getDiff(int[] a, int[] b)
	{
		int ret = b[2] - a[2];
		if(b[1] < a[1] || b[1] == a[1] && b[0] < a[0])
			--ret;
		return ret;
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