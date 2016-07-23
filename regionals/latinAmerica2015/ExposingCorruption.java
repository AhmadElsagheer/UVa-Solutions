package regionals.latinAmerica2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ExposingCorruption {

	static ArrayList<Integer>[] adjList;
	static int[] color, price;
	static int c1, c2, switchCost;
	
	static void floodFill(int u, int c)
	{
		switchCost += price[u];
		if(c == 0)
			++c1;
		else
			++c2;
		color[u] = c;
		for(int v: adjList[u])
			if(color[v] == -1)
				floodFill(v, c ^ 1);
	}
	
	static ArrayList<Rivalry> rivalries;
	static Pair[][] memo;
	
	static Pair dp(int r, int budget)
	{
		if(r == -1)
			return new Pair(0, 0);
		if(memo[r][budget] != null)
			return memo[r][budget];
		
		Rivalry riv = rivalries.get(r);
		Pair keepMembers = dp(r - 1, budget);
		Pair ret = new Pair(riv.DSP + keepMembers.DSP, riv.PPP + keepMembers.PPP);
		
		if(riv.cost <= budget)
		{
			Pair switchMembers = dp(r - 1, budget - riv.cost);
			ret.DSP = Math.max(ret.DSP, riv.PPP + switchMembers.DSP);
			ret.PPP = Math.max(ret.PPP, riv.DSP + switchMembers.PPP);
		}
		return memo[r][budget] = ret;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int D = sc.nextInt(), P =sc.nextInt(), R = sc.nextInt(), B = sc.nextInt();
			int V = D + P;
			price = new int[V];
			for(int i = 0; i < V; ++i)
				price[i] = sc.nextInt();
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(R-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(D + v);
				adjList[D + v].add(u);
			}
			color = new int[V];
			Arrays.fill(color, -1);
			rivalries = new ArrayList<Rivalry>();
			
			for(int i = 0; i < D; ++i)
				if(color[i] == -1)
				{
					switchCost = 0; c1 = 0; c2 = 0;
					floodFill(i, 0);
					rivalries.add(new Rivalry(c1, c2, switchCost));
				}
			for(int i = D; i < V; ++i)
				if(color[i] == -1)
					rivalries.add(new Rivalry(0, 1, price[i]));
			
			memo = new Pair[rivalries.size()][B + 1];
			Pair ans = dp(rivalries.size() - 1, B);
			out.println(ans.DSP + " " + ans.PPP);
		}
		out.flush();
		out.close();
		
	}
	
	static class Rivalry { int DSP, PPP, cost; Rivalry(int a, int b, int c) { DSP = a; PPP = b; cost = c; } }
	
	static class Pair { int DSP, PPP; Pair(int a, int b) { DSP = a; PPP = b; } }
	
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
