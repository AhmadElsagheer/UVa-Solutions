package v124;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Stones_UVa12469 {

	static Boolean[][] memo;
	
	static boolean win(int rem, int max)
	{
		if(max == 0)
			return false;
		if(memo[rem][max] != null)
			return memo[rem][max];
		return memo[rem][max] = !win(rem - max, Math.min(rem - max, max * 2)) || win(rem, max - 1);
	}
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
//		memo = new Boolean[1001][1001];
		boolean[] isFib = new boolean[1001];
		int f1 = 1, f2 = 1;
		while(f1 + f2 <= 1000)
		{
			int f3 = f1 + f2;
			isFib[f3] = true;
			f1 = f2;
			f2 = f3;
		}
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
//			out.println(win(n, n - 1) ? "Alicia" : "Roberto");
			out.println(isFib[n]?"Roberto":"Alicia");
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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

		public boolean hasNext() throws IOException
		{
			while(br.ready() && (st == null || !st.hasMoreTokens()))
				st = new StringTokenizer(br.readLine());
			return st.hasMoreTokens();
		}


	}
}