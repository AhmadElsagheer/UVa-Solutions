package v112;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Expressions_UVa11234 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			Stack<Node> stack = new Stack<Node>();
			char[] s = sc.next().toCharArray();
			for(char c: s)
				if(c >= 'a' && c <= 'z')
					stack.push(new Node(c, null, null));
				else
				{
					Node right = stack.pop(), left = stack.pop();
					stack.push(new Node(c, left, right));
				}
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(stack.pop());
			while(!queue.isEmpty())
			{
				Node cur = queue.remove();
				stack.push(cur);
				if(cur.left != null && cur.right != null)
				{
					queue.add(cur.left);					
					queue.add(cur.right);
				}
			}
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty())
				sb.append(stack.pop().c);
			out.println(sb);
		}
		out.flush();
		out.close();
	}
	
	static class Node
	{
		char c;
		Node left, right;
		
		Node(char x, Node l, Node r) { c = x; left = l; right = r; }
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}