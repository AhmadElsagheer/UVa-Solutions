package cp4_7;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class IsItATree_UVa615 {
	
	static boolean dfs(Node u)
	{
		u.visited = true;
		for(Node v: u.children)
			if(v.visited || !dfs(v))
				return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			
			int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
			if(u < -1)
				break;
			TreeMap<Integer, Node> map = new TreeMap<Integer, Node>();
			
			while(u != -1 && v != -1)
			{
				Node x = map.get(u), y = map.get(v);
				if(x == null)
					map.put(u, x = new Node());
				if(y == null)
					map.put(v, y = new Node());
				x.children.add(y);
				y.isRoot = false;
				u = sc.nextInt() - 1;
				v = sc.nextInt() - 1;
			}
			
			Node root = null;
			boolean isTree = true;
			for(Node node: map.values())
				if(node.isRoot)
				{
					root = node;
					break;
				}
			if(map.size() > 1 && (root == null || !dfs(root)))
				isTree = false;
			
			for(Node node: map.values())
				if(!node.visited)
					isTree = false;
			if(isTree)
				out.printf("Case %d is a tree.\n", tc++);
			else
				out.printf("Case %d is not a tree.\n", tc++);
		}
		out.flush();
		out.close();
	}
	
	static class Node
	{
		boolean isRoot = true, visited;
		ArrayList<Node> children = new ArrayList<Node>();
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