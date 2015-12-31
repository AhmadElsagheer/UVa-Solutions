package cp4_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import ds.UnionFind;

public class Racing_UVa1234 {

	
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int sum = 0;
			N = sc.nextInt();
			edgeList = new Triple[sc.nextInt()];
			for(int i = 0; i < edgeList.length; ++i)
			{
				edgeList[i] = new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
				sum += edgeList[i].cost;
			}
			out.println(sum - mstKruskal());
		}
		out.flush();
		out.close();
	}
	
	static Triple[] edgeList;	//use ArrayList in case of unknown number of edges
	static int N;
	
	public static int mstKruskal()
	{
		Arrays.sort(edgeList);
		UnionFind uf = new UnionFind(N);
		int mst = 0;
		
		for(int i = 0, size = edgeList.length; i < size; i++)
		{
			Triple cur = edgeList[i];
			if(!uf.isSameSet(cur.to, cur.from))
			{
				mst += cur.cost;
				uf.unionSet(cur.to, cur.from);
			}
		}
		
		return mst;
	}
	
	

	static class Triple implements Comparable<Triple>
	{
		int from, to, cost;
		Triple(int x, int y, int z) {from = x; to = y; cost = z;}
		public int compareTo(Triple p) {return p.cost - cost;}
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
}


