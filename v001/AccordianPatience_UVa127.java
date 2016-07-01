package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class AccordianPatience_UVa127 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			String s = sc.next();
			if(s.equals("#"))
				break;
			
			Pile root = new Pile(s);
			Pile p = root;
			
			for(int i = 0; i < 51; ++i)
			{
				Pile q = new Pile(sc.next());
				p.nxt = q;
				q.prev = p;
				p = q;
			}
			
			Pile cur = root;
			while(cur != null)
			{
				Pile prev = cur.getPrev(3);
				if(match(cur, prev))
				{
					cur = prev;
					continue;
				}
				
				prev = cur.getPrev(1);
				if(match(cur, prev))
				{
					cur = prev;
					continue;
				}
				
				cur = cur.nxt;
			}
			
			int size = root.chainSize();
			if(size == 1)
				out.printf("%d pile remaining:", size);
			else
				out.printf("%d piles remaining:", size);
			while(root != null)
			{
				out.print(" " + root.st.size());
				root = root.nxt;
			}
			out.println();
		}
		out.flush();
		out.close();
	}
	
	static boolean match(Pile cur, Pile prev)
	{
		if(prev != null)
		{
			String x = cur.st.peek(), y = prev.st.peek();
			if(x.charAt(0) == y.charAt(0) || x.charAt(1) == y.charAt(1))
			{
				prev.st.push(cur.st.pop());
				if(cur.st.isEmpty())
				{
					cur.prev.nxt = cur.nxt;
					if(cur.nxt != null)
						cur.nxt.prev = cur.prev;
				}
				return true;
			}
			
		}
		return false;
	}
	
	static class Pile
	{
		Stack<String> st = new Stack<String>();
		Pile nxt, prev;
		
		Pile(String x) { st.push(x); }
		
		Pile getPrev(int c)
		{
			Pile p = this;
			while(c-->0)
			{
				p = p.prev;
				if(p == null)
					return null;
			}
			return p;
		}
		
		int chainSize()
		{
			if(nxt != null)
				return 1 + nxt.chainSize();
			return 1;
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