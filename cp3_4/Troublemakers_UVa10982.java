package cp3_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Troublemakers_UVa10982 {


	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			boolean[][] adjMat = new boolean[n][n];
			int[] deg1 = new int[n], deg2 = new int[n];
			
			for(int i = 0;  i < m; ++i)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjMat[u][v] = adjMat[v][u] = true;
				deg1[u]++;
				deg1[v]++;
			}
			if(n <= 1)
			{	
				out.format("Case #%d: %d\n\n", t, 0);
				continue;
			}
			for(int i = 0; i < n; ++i)
				deg2[i] = deg1[i];
			boolean[] moved = new boolean[n];
			ArrayList<Integer> newClass = new ArrayList<Integer>();
			int k;
			for(k = m; k > m>>1; )
			{
				int cont = 0, node = -1;
				for(int i = 0; i < n; ++i)
					if(!moved[i] && cont < deg2[i] * 2 - deg1[i])
					{
						cont = deg2[i] * 2 - deg1[i];
						node = i;
					}
				if(node == -1)
					break;
				k = k - deg2[node] * 2 + deg1[node];
				newClass.add(node);
				moved[node] = true;
				for(int i = 0; i < n; ++i)
					if(adjMat[node][i])
						deg2[i]--;
					
			}
			if(k > m>>1)
				out.format("Case #%d: Impossible.\n\n", t);
			else
			{
				
				out.format("Case #%d: %d\n", t, newClass.size());
				for(int i = 0; i < newClass.size() - 1; ++i)
					out.format("%d ", newClass.get(i) + 1);
				if(newClass.isEmpty())
					out.println();
				else
					out.format("%d\n", newClass.get(newClass.size() - 1) + 1);
			}
		}
		
		out.flush();
		out.close();


	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}

		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

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


