package v111;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DancingDigits_UVa11198 {

	static boolean isPrime[];
	
	static void compute()
	{
		isPrime = new boolean[20];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i < 20; ++i)
			if(isPrime[i])
				for(int j = i * i; j < 20; j += i)
					isPrime[j] = false;
		
	}
	
	static void swap(int[] a, int i, int j)
	{
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	}
	
	static boolean willdance(int x, int y)
	{
		return x * y < 0 && isPrime[Math.abs(x) + Math.abs(y)];
	}
	static int bfs(Vertex s)
	{
		TreeMap<Vertex, Integer> dist = new TreeMap<Vertex, Integer>();
		Queue<Vertex> q = new LinkedList<Vertex>();
		dist.put(s, 0);
		q.add(s);
		while(!q.isEmpty())
		{
			Vertex u = q.remove();
//			System.out.println(u);
			Integer d = dist.get(u);
			if(u.target)
				return d;
			int[] nxt;
			for(int i = 0; i < 8; ++i)
			{
				nxt = u.p.clone();
				for(int j = i + 1; j < 8; ++j)
				{
					swap(nxt, j, j - 1);
					if(willdance(nxt[j], nxt[j-1]) || j < 7 && willdance(nxt[j], nxt[j+1]))
					{
						Vertex to = new Vertex(nxt.clone());
						if(dist.get(to) == null)
						{
							dist.put(to, d + 1);
							q.add(to);
						}						
					}
					
				}

				nxt = u.p.clone();
				for(int j = i - 1; j >= 0; --j)
				{
					swap(nxt, j, j + 1);
//					System.out.println(Arrays.toString(nxt));
					if(willdance(nxt[j], nxt[j+1]) || j > 0 && willdance(nxt[j], nxt[j-1]))
					{
//						System.out.println(1);
						Vertex to = new Vertex(nxt.clone());
						if(dist.get(to) == null)
						{
							dist.put(to, d + 1);
							q.add(to);
						}						
					}
					
				}

			}
			
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		
		compute();
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			int a0 = sc.nextInt();
			if(a0 == 0)
				break;
			int[] p = new int[8];
			p[0] = a0;
			for(int i = 1; i < 8; ++i)
				p[i] = sc.nextInt();
			Vertex s = new Vertex(p);
			out.format("Case %d: %d\n", tc++, bfs(s));
		}
		
		out.flush();
	}
	
	static class Vertex implements Comparable<Vertex>
	{
		int[] p;
		boolean target;
		
		Vertex(int[] a) 
		{ 
			p = a; 
			target = true;
			for(int i = 0; i < 8 && target; ++i)
				if(Math.abs(p[i]) != i + 1)
					target = false;
		}
		
		public int compareTo(Vertex v)
		{
			for(int i = 0; i < 8; ++i)
				if(p[i] != v.p[i])
					return p[i] - v.p[i];
			return 0;
		}
		
		public String toString()
		{
			return Arrays.toString(p);
		}
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
