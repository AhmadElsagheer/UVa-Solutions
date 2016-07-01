package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class NumberGame_UVa10164 {
	
	static StringBuilder sb;
	static boolean first;
	
	static void print(Node cur)
	{
		if(cur.left == null && cur.right == null)
		{
			if(!first)
				sb.append(" ");
			else
				first = false;
			sb.append(cur.val);
		}
		else
		{
			print(cur.left);
			print(cur.right);
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		sb = new StringBuilder();
		
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			Node[] nodes = new Node[(N<<1)-1];
			for(int i = 0, end = (N<<1) - 1; i < end; ++i)
				nodes[i] = new Node(sc.nextInt());
			while(N > 1)
			{
				N >>= 1;
				Node[] even = new Node[nodes.length], odd = new Node[nodes.length];
				int n = 0, m = 0;
				for(int i = 0; i < nodes.length; ++i)
					if((nodes[i].val & 1) == 0)
						even[n++] = nodes[i];
					else
						odd[m++] = nodes[i];
				if((m & 1) == 1)
					--m;
				int size = (N<<1) - 1, k = 0;
				nodes = new Node[size];
				for(int i = 0; i < m && k < size; i += 2)
				{
					Node cur = new Node((odd[i].val + odd[i+1].val)>>1);
					cur.left = odd[i];
					cur.right = odd[i+1];
					nodes[k++] = cur;
				}
				
				for(int i = 0; i < n && k < size; i += 2)
				{
					Node cur = new Node((even[i].val + even[i+1].val)>>1);
					cur.left = even[i];
					cur.right = even[i+1];
					nodes[k++] = cur;
				}
				
			}
			first = true;
			sb.append("Yes\n");
			print(nodes[0]);
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Node
	{
		int val;
		Node left, right;
		
		Node(int x) { val = x; }
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