package v006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class TheFallingLeaves_UVa699 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while(true)
		{
			int x = sc.nextInt();
			if(x == -1)
				break;

			Stack<Node> stack = new Stack<Node>();
			int[] piles = new int[200];
			Node root = new Node(100);
			stack.push(root);
			int min = 200, max = -1;
			while(!stack.isEmpty())
			{
				Node cur = stack.pop();
				cur.val = x;
				if(x != -1)
				{
					if(cur.pos < min) min = cur.pos;
					if(cur.pos > max) max = cur.pos;
					piles[cur.pos] += cur.val;
					stack.push(cur.right = new Node(cur.pos + 1));
					stack.push(cur.left = new Node(cur.pos - 1));
				}
				if(!stack.isEmpty())
					x = sc.nextInt();
			}
			sb.append("Case "+(tc++)+":\n");
			boolean first = true;
			for(int i = min; i <= max; ++i)
			{
				if(first)
					first = false;
				else
					sb.append(" ");
				sb.append(piles[i]);
			}
			sb.append("\n\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Node
	{
		int val, pos;
		Node left, right;
		
		Node(int x) { pos = x; }
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