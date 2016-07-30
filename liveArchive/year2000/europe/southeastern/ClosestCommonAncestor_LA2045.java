package liveArchive.year2000.europe.southeastern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class ClosestCommonAncestor_LA2045 {
	
	static int N, K, P[][], L[];
	static ArrayList<Integer>[] adjList;
	
	static void pre(int root)
	{
		K = 0;
		while(1<<K+1 <= N - 1)
			++K;
		P = new int[N][K + 1];
		for(int i = 0; i < N; ++i)
			Arrays.fill(P[i], -1);
		L = new int[N];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(root);
		while(!q.isEmpty())
		{
			int u = q.remove();
			for(int v: adjList[u])
			{
				L[v] = L[u] + 1;
				P[v][0] = u;
				q.add(v);
			}
		}
		for(int j = 1; j <= K; ++j)
			for(int i = 0; i < N; ++i)
				if(P[i][j-1] != -1)
					P[i][j] = P[P[i][j-1]][j-1];
		
	}
	
	static int query(int p, int q)
	{
		if(L[p] < L[q]) { q ^= p; p ^= q; q ^= p; }
		
		for(int i = K; i >= 0; --i)
			if(L[p] - (1<<i) >= L[q])
				p = P[p][i];
		
		if(p == q)
			return q;
		
		for(int i = K; i >= 0; --i)
			if(P[p][i] != -1 && P[p][i] != P[q][i])
			{
				p = P[p][i];
				q = P[q][i];
			}
		return P[p][0];
	}
	
	static int findRoot(boolean[] root)
	{
		for(int i = 0; i < N; ++i)
			if(root[i])
				return i;
		return -1;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.hasNext())
		{
			N = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			boolean[] root = new boolean[N];
			Arrays.fill(root, true);
			for(int i = 0; i < N; ++i)
			{
				int u = sc.nextInt() - 1;
				int k = sc.nextInt();
				while(k-->0)
				{	
					int v = sc.nextInt() - 1;
					adjList[u].add(v);
					root[v] = false;
				}
			}
			
			pre(findRoot(root));
			int M = sc.nextInt(), freq[] = new int[N];
			while(M-->0)
			{
				int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
				freq[query(x, y)]++;
			}
			
			for(int i = 0; i < N; ++i)
				if(freq[i] != 0)
					out.printf("%d:%d\n", i + 1, freq[i]);
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), " |(|)|:");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		boolean ready() throws IOException { return br.ready(); }
		
		boolean hasNext() throws IOException
		{
			while (br.ready() && (st == null || !st.hasMoreTokens())) 
				st = new StringTokenizer(br.readLine(), " |(|)|:");
			return st != null && st.hasMoreTokens();
		}
	}
}