package v101;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ShellSort_UVa10152 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Deque<Turtle> org = new LinkedList<Turtle>(); Stack<Turtle> arr = new Stack<Turtle>();
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			int[] p = new int[n];
			for(int i = 0; i < n; ++i)
			{
				Turtle t = new Turtle(sc.nextLine(), i);
				org.addFirst(t);
				map.put(t.name, i);
			}
			for(int i = 0; i < n; ++i)
			{
				Turtle t = new Turtle(sc.nextLine(), -1, n - i);
				arr.push(t);
				t.idx = map.get(t.name);
				p[t.idx] = n - i;
			}
			PriorityQueue<Turtle> pq = new PriorityQueue<Turtle>();
			
			while(!arr.isEmpty())
			{
				Turtle t = arr.pop();
				while(!org.isEmpty() && t.idx != org.peek().idx)
				{
					Turtle tt = org.remove();
					tt.p = p[tt.idx];
					pq.add(tt);
				}
				if(org.isEmpty())
					break;
				org.remove();
			}
			while(!pq.isEmpty())
				out.println(pq.remove().name);

			out.println();
		}
		out.flush();
		
	}
	
	static class Turtle implements Comparable<Turtle>
	{
		String name;
		int idx, p;
		
		Turtle(String x, int y) { name = x; idx = y; }
		
		Turtle(String x, int y, int z) { name = x; idx = y; p = z; }
		
		public int compareTo(Turtle t) { return p - t.p; }
		
		public String toString() { return name + " " + idx + " " + p;}
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
