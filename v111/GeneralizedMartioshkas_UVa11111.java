package v111;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class GeneralizedMartioshkas_UVa11111 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		
		while(sc.ready())
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int n = st.countTokens();
			boolean ok = true;
			Stack<Pair> s = new Stack<Pair>();
			while(n-->0)
			{
				int a = Integer.parseInt(st.nextToken());
				if(a < 0)
				{
					if(!s.isEmpty())
						s.peek().y += -a;
					s.push(new Pair(-a, 0));
				}
				else
				{
					if(s.isEmpty())
						ok = false;
					else
					{
						Pair p = s.pop();
						if(p.x != a || p.x <= p.y)
							ok = false;
					}
				}
			}
			out.println(ok && s.isEmpty()?":-) Matrioshka!":":-( Try again.");
		}
		out.flush();
		out.close();
	}
	
	static class Pair { int x, y; Pair(int a, int b) { x = a; y = b; } }

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


	}
}