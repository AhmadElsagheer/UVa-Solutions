package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FerryLoadingIII_UVa10901 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), t = sc.nextInt(), m = sc.nextInt();
			Queue<Car> left = new LinkedList<Car>(), right = new LinkedList<Car>();
			for(int i = 0; i < m; ++i)
			{
				Car c = new Car(sc.nextInt(), i);
				if(sc.next().equals("left"))
					left.add(c);
				else
					right.add(c);
			}

			int[] ans = new int[m];

			int curTime = 0, curBank = 0;
			Queue<Car> b1, b2;
			while(!left.isEmpty() || !right.isEmpty())
			{
				if(curBank == 0) { b1 = left; b2 = right; }
				else { b1 = right; b2 = left; }

				int loaded = 0;
				while(loaded < n && !b1.isEmpty() && b1.peek().time <= curTime)
				{
					Car c = b1.remove();
					ans[c.index] = curTime + t;
					++loaded;
				}

				if(loaded != 0 || !b2.isEmpty() && b2.peek().time <= curTime) 
				{ 
					curTime += t; curBank ^= 1; 
				}
				else if(b1.isEmpty() || !b2.isEmpty() && b2.peek().time < b1.peek().time)
				{ 
					curTime = b2.peek().time + t; curBank ^= 1; 
				}
				else
					curTime = b1.peek().time;

			}
			for(int x: ans)
				sb.append(x + "\n");
			if(tc != 0)
				sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Car { int time, index; Car(int a, int b) { time = a; index = b; } }

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