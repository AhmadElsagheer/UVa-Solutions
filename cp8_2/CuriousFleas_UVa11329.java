package cp8_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CuriousFleas_UVa11329 {
	
	static TreeMap<Vertex, Integer> dist = new TreeMap<Vertex, Integer>();
	static int[] dx = new int[]{0,0,-1,1};
	static int[] dy = new int[]{-1,1,0,0};
	
	static boolean valid(int x, int y)
	{
		if(x == -1 || y == -1 || x == 4 || y == 4)
			return false;
		return true;
	}
	static void bfs()
	{
		Queue<Vertex> q = new LinkedList<Vertex>();
		
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j < 4; ++j)
			{
				int msk = (63<<16) | (i<<22) | (j<<24);
				Vertex s = new Vertex(msk);
				dist.put(s, -1);
				q.add(s);
			}
		
		while(!q.isEmpty())
		{
			Vertex u = q.remove();
			Integer d = dist.get(u);
			
//			System.out.println(u);
			
			for(int k = 0; k < 4; ++k)
			{ 
				Vertex nxt = u.roll(k);
				if(nxt == null)
					continue;
				
				if(dist.get(nxt) == null)
				{
					dist.put(nxt, d + 1);
					q.add(nxt);
//					System.out.println("XX" + nxt);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		bfs();
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int msk = 0;
			for(int i = 0; i < 4; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < 4; ++j)
					if(line.charAt(j) == 'D')
					{
						msk |= (i<<22) | (j<<24);
					}
					else
						if(line.charAt(j) == 'X')
							msk |= 1<<((i<<2) + j);			
			}
			Integer ans = dist.get(new Vertex(msk));
			out.println(ans == null ? "impossible\n" : ans);
		}
		out.flush();
	}
	
	
	//0->15 for cells, 16->21 for die, 22->24 for pos
	static class Vertex implements Comparable<Vertex>
	{
		int msk;
			
		Vertex(int x) { msk = x; }
		
		
		public int compareTo(Vertex v)
		{
			if(msk != v.msk) 
				return msk > v.msk ? 1 : -1;
			return 0;
		}
		
		int getX() { return (msk>>22) & 3; }
		
		int getY() { return (msk>>24) & 3; }
		
		
		Vertex roll(int k)
		{
			switch(k)
			{
			case 0: return rollLeft();
			case 1: return rollright();
			case 2: return rollup();
			}
			return rolldown();
		}
		Vertex rollLeft()
		{
			int x = getX(), y = getY();
			int xx = x + dx[0], yy = y + dy[0];
			int[] s = new int[]{1, 4, 2, 0, 3, 5};
			if(!valid(xx, yy))
				return null;
			int msk2 = (xx<<22) | (yy<<24) | (msk & ((1<<16) - 1));
			for(int i = 0; i < 6; ++i)
				msk2 |= (1 & (msk>>(i+16)))<<(s[i] + 16);
			int pos = (yy + 4 * xx);
			msk2 &= ~(1<<pos);
			msk2 |= ((msk2>>16) & 1)<<pos;
			msk2 &= ~(1<<16);
			msk2 |= ((msk>>pos) & 1)<<16;
			return new Vertex(msk2);
		}
		
		Vertex rollright()
		{
			int x = getX(), y = getY();
			int xx = x + dx[1], yy = y + dy[1];
			if(!valid(xx, yy))
				return null;
			int msk2 = (xx<<22) | (yy<<24) |(msk & ((1<<16) - 1));
			int[] s = new int[]{3, 0, 2, 4, 1, 5};
			for(int i = 0; i < 6; ++i)
				msk2 |= (1 & (msk>>(i+16)))<<(s[i] + 16);
			
			int pos = (yy + 4 * xx);
			msk2 &= ~(1<<pos);
			msk2 |= ((msk2>>16) & 1)<<pos;
			msk2 &= ~(1<<16);
			msk2 |= ((msk>>pos) & 1)<<16;
			return new Vertex(msk2);
		}
		
		Vertex rollup()
		{
			int x = getX(), y = getY();
			int xx = x + dx[2], yy = y + dy[2];
			if(!valid(xx, yy))
				return null;
			int msk2 = (xx<<22) | (yy<<24) |(msk & ((1<<16) - 1));
			int[] s = new int[]{5, 1, 0, 3, 2, 4};
			for(int i = 0; i < 6; ++i)
				msk2 |= (1 & (msk>>(i+16)))<<(s[i] + 16);
			int pos = (yy + 4 * xx);
			msk2 &= ~(1<<pos);
			msk2 |= ((msk2>>16) & 1)<<pos;
			msk2 &= ~(1<<16);
			msk2 |= ((msk>>pos) & 1)<<16;
			return new Vertex(msk2);
		}
		
		Vertex rolldown()
		{
			int x = getX(), y = getY();
			int xx = x + dx[3], yy = y + dy[3];
			if(!valid(xx, yy))
				return null;
			int msk2 = (xx<<22) | (yy<<24) |(msk & ((1<<16) - 1));
			int[] s = new int[]{2, 1, 4, 3, 5, 0};
			for(int i = 0; i < 6; ++i)
				msk2 |= (1 & (msk>>(i+16)))<<(s[i] + 16);
			int pos = (yy + 4 * xx);
			msk2 &= ~(1<<pos);
			msk2 |= ((msk2>>16) & 1)<<pos;
			msk2 &= ~(1<<16);
			msk2 |= ((msk>>pos) & 1)<<16;
			return new Vertex(msk2);
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
