package v008;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NotSoMobile_UVa839 {

	static Scanner sc = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	
	static Mobile read() throws IOException
	{
		Mobile ret = new Mobile(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
		if(ret.Wl == 0)
		{
			ret.left = read();
			ret.Wl = ret.left.Wl + ret.left.Wr;
		}
		if(ret.Wr == 0)
		{
			ret.right = read();
			ret.Wr = ret.right.Wl + ret.right.Wr;
		}
		return ret;
	}
	
	static boolean balanced(Mobile root)
	{
		if(root == null)
			return true;
		if(root.Wl * root.Dl != root.Wr * root.Dr)
			return false;
		return balanced(root.left) && balanced(root.right);
	}
	
	public static void main(String[] args) throws IOException {
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			Mobile root = read();
			out.println(balanced(root) ? "YES":"NO");
			if(tc != 0)
				out.println();
		}
		out.flush();

	}
	
	static class Mobile
	{
		int Wl, Wr, Dl, Dr;
		Mobile left, right;
		Mobile(int x, int y, int z, int w) { Wl = x; Dl = y; Wr = z; Dr = w; }
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
