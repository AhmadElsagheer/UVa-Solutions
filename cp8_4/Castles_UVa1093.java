package cp8_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Castles_UVa1093 {

	
	static class Node implements Comparable<Node>
	{
		int take, give;
		
		Node(int x, int y)
		{
			take = Math.max(x, y);
			give = take - y;
		}
		
		public int compareTo(Node n)
		{
			int x1 = take + Math.max(0, n.take - give);
			int x2 = n.take + Math.max(0, take - n.give);
			return x1 - x2;
		}
	}
	static int[] victory, lost;
	static ArrayList<Integer>[] adjList;
	static ArrayList<Node>[] children;
	
	static Node dfs1(int u, int p)
	{
		Node cur = new Node(victory[u], lost[u]);
		children[u] = new ArrayList<Node>();
		for(int v : adjList[u])
			if(v != p)
				children[u].add(dfs1(v, u));
		Collections.sort(children[u]);
		int curSoldiers = 0, lastGive = 0;
		for(Node child : children[u])
		{
			curSoldiers += Math.max(0, child.take - lastGive);
			lastGive = child.give + Math.max(0, lastGive - child.take);
		}
		
		if(cur.give >= curSoldiers)
		{
			cur.give -= curSoldiers;
			cur.give += lastGive;
		}
		else
		{
			cur.take += curSoldiers - cur.give;
			cur.give = lastGive;
		}
		return cur;
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			victory = new int[n];
			lost = new int[n];
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; ++i)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < n; ++i)
			{
				victory[i] = sc.nextInt();
				lost[i] = sc.nextInt() + sc.nextInt();
			}
			for(int i = 0; i < n - 1; ++i)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < n; ++i)
			{
				
				children = new ArrayList[n];
				dfs1(i, -1);
				Node root = dfs1(i, -1);
				min = Math.min(min, root.take);
				
			}
			out.printf("Case %d: %d\n", tc++, min);
		}
		
		out.flush();
		out.close();


	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}

		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}


