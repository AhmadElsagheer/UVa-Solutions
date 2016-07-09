package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ThePsychicPokerPlayer_UVa131 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			String[] t = new String[10];
			for(int i = 0; i < 10; ++i)
				t[i] = sc.next();

			int max = 0;
			for(int msk = 0; msk < 32; ++msk)
			{
				String[]s = new String[5];
				int k = 0;
				for(int i = 0; i < 5; ++i)
					if((msk & 1<<i) == 0)
						s[i] = t[i];
					else
						s[i] = t[5+k++];
				
				if(sFlush(s))
					max = 8;
				else if(fourKind(s))
					max = 7 > max ? 7 : max;
				else if(fullHouse(s))
					max = 6 > max ? 6 : max;
				else if(flush(s))
					max = 5 > max ? 5 : max;
				else if(straight(s))
					max = 4 > max ? 4 : max;
				else if(threeKind(s))
					max = 3 > max ? 3 : max;
				else if(twoPair(s))
					max = 2 > max ? 2 : max;
				else if(onePair(s))
					max = 1 > max ? 1 : max;
			}
			
			out.print("Hand:");
			for(int i = 0; i < 5; ++i)
				out.print(" " + t[i]);
			
			out.print(" Deck:");
			for(int i = 0; i < 5; ++i)
				out.print(" " + t[5+i]);
			
			out.print(" Best hand: ");
			if(max == 8)
				out.println("straight-flush");
			else if(max == 7)
				out.println("four-of-a-kind");
			else if(max == 6)
				out.println("full-house");
			else if(max == 5)
				out.println("flush");
			else if(max == 4)
				out.println("straight");
			else if(max == 3)
				out.println("three-of-a-kind");
			else if(max == 2)
				out.println("two-pairs");
			else if(max == 1)
				out.println("one-pair");
			else
				out.println("highest-card");
			
			
		}

		out.flush();
		out.close();
	}

	static int mapSuit(char c)
	{
		switch(c)
		{
		case 'H': return 0;
		case 'S': return 1;
		case 'D': return 2;
		default: return 3;
		}
	}

	static int mapRank(char c)
	{
		switch(c)
		{
		case 'A': return 14;
		case 'K': return 13;
		case 'Q': return 12;
		case 'J': return 11;
		case 'T': return 10;
		default: return c - '0';
		}
	}

	static boolean sFlush(String[] s)
	{
		return straight(s) && flush(s);
	}

	static boolean fourKind(String[] s)
	{
		int[] c = new int[15];
		for(String t: s)
			c[mapRank(t.charAt(0))]++;
		for(int x: c)
			if(x == 4)
				return true;
		return false;
	}

	static boolean fullHouse(String[] s)
	{
		int[] c = new int[15];
		for(String t: s)
			c[mapRank(t.charAt(0))]++;
		int three = 0, two = 0;
		for(int x: c)
			if(x == 2)
				two = 1;
			else if(x >= 3)
				three++;
		return three + two >= 2;
	}

	static boolean flush(String[] s)
	{
		int[] c = new int[4];
		for(String t: s)
			c[mapSuit(t.charAt(1))]++;
		for(int x: c)
			if(x >= 5)
				return true;
		return false;
	}

	static boolean straight(String[] s)
	{
		boolean[] c = new boolean[15];
		for(String t: s)
		{
			int x = mapRank(t.charAt(0)); 
			c[x] = true;
			if(x == 4)
				c[1] = true;
		}
		int k = 0;
		for(boolean b: c)
		{
			if(b)
				++k;
			else
				k = 0;
			if(k == 5)
				return true;
		}
		return false;
	}

	static boolean threeKind(String[] s)
	{
		int[] c = new int[15];
		for(String t: s)
			c[mapRank(t.charAt(0))]++;
		for(int x: c)
			if(x == 3)
				return true;
		return false;
	}

	static boolean twoPair(String[] s)
	{
		int count = 0;
		int[] c = new int[15];
		for(String t: s)
			c[mapRank(t.charAt(0))]++;
		for(int x: c)
			if(x == 2)
				++count;
		return count >= 2;
	}

	static boolean onePair(String[] s)
	{
		int[] c = new int[15];
		for(String t: s)
			c[mapRank(t.charAt(0))]++;
		for(int x: c)
			if(x == 2)
				return true;
		return false;
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}