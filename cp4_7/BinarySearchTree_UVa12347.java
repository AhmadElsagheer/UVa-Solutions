package cp4_7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BinarySearchTree_UVa12347 {
	
	static ArrayList<Integer> values = new ArrayList<Integer>(10000);
	static PrintWriter out = new PrintWriter(System.out);
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		while(sc.ready())
			values.add(sc.nextInt());
		Node root = build(0, values.size());
		print(root);
		out.flush();

	}
	
	static void print(Node root)
	{
		if(root == null)
			return;
		print(root.left);
		print(root.right);
		out.println(root.val);
	}
	static Node build(int idx, int end)
	{
		if(idx >= end)
			return null;
		Node ret = new Node(values.get(idx));
		int mid = idx + 1;
		while(mid < values.size() && values.get(mid) < ret.val)
			++mid;
		Node ll = build(idx + 1, mid), rr = build(mid, end);
		ret.left = ll;
		ret.right = rr;
		return ret;
	}
	
	static class Node
	{
		Node left, right;
		int val;
		
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
