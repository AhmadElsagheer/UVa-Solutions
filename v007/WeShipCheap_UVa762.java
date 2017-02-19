package v007;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class WeShipCheap_UVa762 {

	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			int N = sc.nextInt();
			TreeMap<String, City> map = new TreeMap<String, City>();
			while(N-->0)
			{
				String u = sc.next(), v = sc.next();
				City x = map.get(u), y = map.get(v);
				if(x == null)
					map.put(u, x = new City(u));
				if(y == null)
					map.put(v, y = new City(v));
				x.next.add(y);
				y.next.add(x);
			}
			City s = map.get(sc.next()), t = map.get(sc.next());
			if(s == null || t == null)
				out.println("No route");
			else
			{
				
				s.visited = true;
				Queue<City> q = new LinkedList<City>();
				q.add(s);
				while(!q.isEmpty())
				{
					City u = q.remove();
					if(u == t)
						break;
					for(int i = 0; i < u.next.size(); ++i)
					{
						City v = u.next.get(i);
						if(!v.visited)
						{
							v.visited = true;
							v.parent = u;
							q.add(v);
						}
					}
				}
				if(!t.visited)
					out.println("No route");
				else
				{
					Stack<City> stack = new Stack<City>();
					while(t.parent != null)
					{
						stack.push(t);
						t = t.parent;
					}
					while(!stack.isEmpty())
						out.format("%s %s\n", stack.peek().parent.name, stack.pop().name);
				}
			}
		}
		
		out.flush();
	}

	static class City
	{
		String name;
		City parent;
		boolean visited;
		
		ArrayList<City> next = new ArrayList<City>();
		
		City(String x) { name = x; }
		
		
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
