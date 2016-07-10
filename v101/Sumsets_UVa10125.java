package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Sumsets_UVa10125 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int n = sc.nextInt(), a[] = new int[n];
			if(n == 0)
				break;
			for(int i = 0; i < n; ++i)
				a[i] = sc.nextInt();
			Arrays.sort(a);
			
			TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<Integer, TreeSet<Integer>>();
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
				{
					int s = a[i] + a[j];

					TreeSet<Integer> set = map.get(s);
					if(set == null)
						map.put(s, set = new TreeSet<Integer>());
					set.add(i);
					set.add(j);
				}
			
			Integer ans = null;
			for(int i = n - 1; ans == null && i >= 0; --i)
				for(int j = 0; ans == null && j < n; ++j)
					if(i != j)
					{
						int d = a[i] - a[j];

						TreeSet<Integer> set = map.get(d);
						if(set == null)
							continue;
						int x = 2;
						if(set.contains(i))
							x += 2;
						if(set.contains(j))
							x += 2;
						if(x == 6 && a[j] == 0)
							x -= 2;
						if(set.size() < x)
							continue;
						ans = a[i];
					}
			if(ans == null)
				out.println("no solution");
			else
				out.println(ans);
		}
		
		out.flush();
		out.close();
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