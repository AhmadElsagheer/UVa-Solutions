package v121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AnotherCrisis_UVa12186 {
	
	static ArrayList<Integer>[] subordinates;
	static int T;
	
	static int dfs(int u)
	{
		int size = subordinates[u].size(); 
		if(size == 0)
			return 1;
		int[] workers = new int[size];
		for(int i = 0; i < size; ++i)
			workers[i] = dfs(subordinates[u].get(i));
		Arrays.sort(workers);
		int ret = 0, count = (size * T + 99) / 100;
		for(int i = 0; i < count; ++i)
			ret += workers[i];
		return ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			T = sc.nextInt();
			subordinates = new ArrayList[N + 1];
			for(int i = 0; i <= N; ++i)
				subordinates[i] = new ArrayList<Integer>();
			for(int i = 1; i <= N; ++i)
				subordinates[sc.nextInt()].add(i);
			out.println(dfs(0));
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