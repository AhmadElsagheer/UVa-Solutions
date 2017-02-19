package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;


public class TheNecklace_UVa10054 {

	static class Pair
	{
		int to;
		boolean used;
		
		Pair(int x) { to = x; }
	}
	
	static ArrayList<Pair>[] adjList;
	static LinkedList<Integer> tour;
	
	static void eulerTour(ListIterator<Integer> itr, int u)
	{
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			Pair nxt = adjList[u].get(i);
			if(!nxt.used)
			{
				nxt.used = true;
				for(int j = 0; j < adjList[nxt.to].size(); ++j)
				{
					Pair rev = adjList[nxt.to].get(j);
					if(rev.to == u && !rev.used)
					{
						rev.used = true;
						break;
					}
				}
				itr.add(u);
				eulerTour(itr, nxt.to);
				itr.previous();
			}
		}
	}
	
	static void addEdge(int u, int v)
	{
		adjList[u].add(new Pair(v));
		adjList[v].add(new Pair(u));
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			out.format("Case #%d\n", t);
			adjList = new ArrayList[51];
			for(int i = 1; i <= 50; ++i)
				adjList[i] = new ArrayList<Pair>();
			int n = sc.nextInt();
			while(n-->0)
				addEdge(sc.nextInt(), sc.nextInt());
			int odd = 0;
			for(int i = 1; i <= 50; ++i)
				odd += adjList[i].size() & 1;
			if(odd != 0)
				out.println("some beads may be lost");
			else
			{
				tour = new LinkedList<Integer>();
				int i = 1;
				while(adjList[i].size() == 0) ++i;
				eulerTour(tour.listIterator(), i);
				tour.add(i);
				Iterator<Integer> itr = tour.iterator();
				int lst = itr.next();
				while(itr.hasNext())
				{
					int cur = itr.next();
					out.format("%d %d\n", lst, cur);
					lst = cur;
				}
			}
			if(t != tc)
				out.println();
		}
		out.flush();
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
