package regionals.swerc2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Promotions {
	
	static ArrayList<Integer>[] adjList;
	static BitSet visited;
	static int[] countR;
	
	static int dfs(int u)
	{
		visited.set(u);
		++countR[u];
		int ret = 1, v;
		ArrayList<Integer> x = adjList[u];
		for(int i = 0, size = x.size(); i < size; ++i)
		{
			v = x.get(i);
			if(!visited.get(v))
				ret += dfs(v);
		}
		return ret;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			int A = sc.nextInt(), B = sc.nextInt(), N = sc.nextInt(), M = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();

			while(M-->0)
				adjList[sc.nextInt()].add(sc.nextInt());
			
			int[] count = new int[N];
			countR = new int[N];
			for(int i = 0; i < N; ++i)
			{
				visited = new BitSet(N);
				count[i] = dfs(i);
			}

			int x = 0, y = 0, z = 0;
			
			for(int i = 0; i < N; ++i)
			{
				if(N - count[i] < A)
					++x;
				if(N - count[i] < B)
					++y;
				if(N - countR[i] < N - B)
					++z;
			}
			out.println(x);
			out.println(y);
			out.println(z);
		}
		out.flush();
		out.close();
		
	}
	
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;
		
		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }
		
		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
		
		boolean ready() throws IOException { return br.ready(); }
	}
}
