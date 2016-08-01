package v122;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class BoxGame_UVa12293 {

	static final int limit = (int)1e9;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		TreeSet<Integer> lose = new TreeSet<Integer>();
		int x = 1;
		while(x <= limit)
		{
			lose.add(x);
			x = x<<1|1;
		}
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			out.println(lose.contains(n)?"Bob":"Alice");
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