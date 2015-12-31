package cp4_7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreesOnTheLevel_UVa122 {


	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			Node root = new Node(-1);
			boolean complete = true;
			while(true)
			{
				String s = sc.next();
				if(s.equals("()"))
					break;
				StringTokenizer st = new StringTokenizer(s, "\\,|\\(|\\)");
				
				int val = Integer.parseInt(st.nextToken());
				String dir = st.hasMoreTokens() ? st.nextToken() : "";
				complete &= add(root, val, dir, 0);
			}
			if(!complete || !check(root))
				out.println("not complete");
			else
			{
				StringBuilder sb = new StringBuilder();
				sb.append(root.val);
				Queue<Node> q = new LinkedList<Node>();
				q.add(root.left);	q.add(root.right);
				while(!q.isEmpty())
				{
					Node cur = q.remove();
					if(cur == null)
						continue;
					sb.append(" ").append(cur.val);
					q.add(cur.left);	q.add(cur.right);
				}
				out.println(sb);
			}
		}
		out.flush();
	}
	
	static boolean check(Node cur)
	{
		if(cur == null)
			return true;
		if(cur.val == -1)
			return false;
		return check(cur.left) && check(cur.right);
	}
	
	static boolean add(Node cur, int val, String dir, int idx)
	{
		if(idx == dir.length())
		{
			if(cur.val != -1)
				return false;
			cur.val = val;
			return true;
		}
		if(dir.charAt(idx) == 'L')
		{
			if(cur.left == null)
				cur.left = new Node(-1);
			return add(cur.left, val, dir, idx + 1);
		}
		else
		{
			if(cur.right == null)
				cur.right = new Node(-1);
			return add(cur.right, val, dir, idx + 1);
		}
			
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
