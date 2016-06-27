package v111;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Tautology_UVa11108 {
	
	static String s;
	static int idx;
	
	static Node build()
	{
		Node cur = new Node(s.charAt(idx++));
		if(!(cur.c >= 'p' && cur.c <= 't'))
		{
			cur.left = build();
				if(cur.c != 'N')
					cur.right = build();
		}
		return cur;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			s = sc.next();
			if(s.equals("0"))
				break;
			idx = 0;
			Node root = build();
			boolean tautology = true;
			for(int i = 0; i < 32; ++i)
				if(!root.evaluate(i))
					tautology = false;
			out.println(tautology ? "tautology" : "not");
		}
		out.flush();
		out.close();
	}

	static class Node
	{
		char c;
		Node left, right;
		
		Node(char a) { c = a; }
		
		boolean evaluate(int val)
		{
			if(c >= 'p' && c <= 't')
				return (val & (1<<c-'p')) != 0;
			if(c == 'K')
				return left.evaluate(val) && right.evaluate(val);
			if(c == 'A')
				return left.evaluate(val) || right.evaluate(val);
			if(c == 'N')
				return !left.evaluate(val);
			if(c == 'C')
				return !left.evaluate(val) || right.evaluate(val);
			return left.evaluate(val) == right.evaluate(val);
		}
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