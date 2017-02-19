package v107;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PreInAndPost_UVa10701 {
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt();
			int[] pre = new int[N], in = new int[N], idxOf = new int[300];
			String x = sc.next();
			for(int i = 0; i < N; ++i)
				pre[i] = x.charAt(i);
			x = sc.next();
			for(int i = 0; i < N; ++i)
				idxOf[in[i] = x.charAt(i)] = i;
			Node root = buildTree(pre, in, idxOf);
			sb = new StringBuilder();
			post(root);
			out.println(sb);
		}
		out.flush();
	}
	
	static void post(Node root)
	{
		if(root == null)
			return;
		post(root.left);
		post(root.right);
		sb.append((char)(root.val));
	}
	
	static class Node { int val; Node left, right; Node(int x) { val = x; } }
	 
	 //idxOF if the idx of the specified node in the in[] array
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
