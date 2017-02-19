package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Tree_UVa548 {

	static int[] pre, in;
	static int[] idxOf;	//idx in inorder
	static int minVal, leaf;
	
	static void findLeaf(Node cur, int val)
	{
		if(cur == null)
			return;
		if(cur.left == null && cur.right == null)
		{
			val += cur.val;
			if(val < minVal)
			{
				minVal = val;
				leaf = cur.val;
			}
			else
				if(val == minVal)
					leaf = Math.min(leaf, cur.val);
			return;
		}
		findLeaf(cur.left, val + cur.val);
		findLeaf(cur.right, val + cur.val);
	}
	
	static Node buildTree()
	{
		return buildTree(0, pre.length - 1, 0, pre.length - 1);
	}
	
	static Node buildTree(int b_pre, int e_pre, int b_in, int e_in)
	{
		if(b_pre > e_pre || b_in > e_in)
			return null;
		int idx = idxOf[pre[e_pre]];
		Node root = new Node(pre[e_pre]);
		
		int rightChildren = e_in - idx, leftChildren = idx - b_in;
		
		root.right = buildTree(e_pre - rightChildren, e_pre - 1, idx + 1, e_in);
		root.left = buildTree(b_pre, b_pre + leftChildren - 1, b_in, idx - 1);
		
		return root;
	}
	
	
	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		

		while(sc.ready())
		{
			int n = sc.countTokens();
			pre = new int[n]; in = new int[n]; idxOf = new int[10000];
			for(int i = 0; i < n; i++)
			{
				in[i] = sc.nextInt();
				idxOf[in[i]] = i;
			}
			for(int i = 0; i < n; i++)
				pre[i] = sc.nextInt();
			
			Node root = buildTree();
			minVal = (int)1e9;
			findLeaf(root, 0);
			out.println(leaf);
		}
		out.flush();
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

		int countTokens() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens();
		}
	}
}
