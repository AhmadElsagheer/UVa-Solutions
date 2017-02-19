package v005;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TreeRecovery_UVa536 {

	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			String pre = sc.next(), in = sc.next();
			int[] prex = new int[pre.length()];
			for(int i = 0; i < prex.length; ++i)
				prex[i] = pre.charAt(i) - 'A';
			int[] inx = new int[in.length()], idxOf = new int[26];
			for(int i = 0; i < in.length(); ++i)
				idxOf[inx[i] = in.charAt(i)-'A'] = i;
			
			Node root = buildTree(prex, inx, idxOf);
			sb = new StringBuilder();
			post(root);
			out.println(sb);
		}

		out.flush();
	}
	
	static void post(Node cur)
	{
		if(cur == null)
			return;
		post(cur.left);
		post(cur.right);
		sb.append((char)(cur.val + 'A'));
	}

	static class Node { int val; Node left, right; Node(int x) { val = x; } }
	 
	 //idxOf is the idx of the specified node in the in[] array
	 static Node buildTree(int[] pre, int[] in, int[] idxOf)
	 {
		 return buildTree(pre, in, idxOf, 0, pre.length - 1, 0, pre.length - 1); //returns the root of the tree
	 }

	 
	 static Node buildTree(int[] pre, int[] in, int[] idxOf, int b_pre, int e_pre, int b_in, int e_in)
	 {
		 if(b_pre > e_pre || b_in > e_in)
			 return null;
		 int idx = idxOf[pre[b_pre]];
		 Node root = new Node(pre[b_pre]);

		 int rightChildren = e_in - idx, leftChildren = idx - b_in;

		 root.right = buildTree(pre, in, idxOf, e_pre - rightChildren + 1, e_pre, idx + 1, e_in);
		 root.left = buildTree(pre, in, idxOf, b_pre + 1, b_pre + leftChildren, b_in, idx - 1);

		 return root;
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
