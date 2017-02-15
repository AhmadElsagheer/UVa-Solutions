package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheHotelwithInfiniteRooms_UVa10170 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			long S = sc.nextInt();
			long D = sc.nextLong();
			
			long ans = -1, lo = S, hi = 1l << 31;
			while(lo <= hi)
			{
				long mid = (lo + hi) / 2;
				long spentDays = mid * (mid + 1) / 2 - (S - 1) * S / 2;
				if(spentDays >= D)
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			out.println(ans);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();} 
	}
} 