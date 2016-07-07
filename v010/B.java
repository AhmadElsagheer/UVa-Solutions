package v010;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {

	static int N, M, L[];
	
	static boolean bt(int idx, int used, int side, int remSides)
	{
		if(remSides == 0)
			return true;
		
		if(side == 0)
			return bt(0, used, M, remSides - 1);
		
		if(idx == N)
			return false;
		
		if((used & 1<<idx) == 0 && L[idx] <= side && bt(idx + 1, used | 1<<idx, side - L[idx], remSides))
			return true;
		
		return bt(idx + 1, used, side, remSides);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			L = new int[N];
			int sum = 0;
			for(int i = 0; i < N; ++i)
				sum += L[i] = sc.nextInt();
			if(sum % 4 != 0)
				out.println("no");
			else
			{
				M = sum >> 2;
				out.println(bt(0, 0, M, 4)?"yes":"no");
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}