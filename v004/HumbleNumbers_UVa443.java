package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class HumbleNumbers_UVa443 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	

		ArrayList<Long> elems = new ArrayList<Long>();
		long lim = 1l << 40;
		for(long p2 = 1; p2 <= lim; p2 *= 2)
			for(long p3 = 1; p2 * p3 <= lim; p3 *= 3)
				for(long p5 = 1; p2 * p3 * p5 <= lim; p5 *= 5)
					for(long p7 = 1; p2 * p3 * p5 * p7 <= lim; p7 *= 7)
						elems.add(p2 * p3 * p5 * p7);
		Collections.sort(elems);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			String suffix = n % 10 == 1 ? "st" : n % 10 == 2 ? "nd" : n % 10 == 3 ? "rd" : "th";
			if(n % 100 >= 10 && n % 100 < 20)
				suffix = "th";
			out.printf("The %d%s humble number is %d.\n", n, suffix, elems.get(n - 1));
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