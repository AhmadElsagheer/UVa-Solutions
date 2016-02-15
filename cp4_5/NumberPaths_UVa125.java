package cp4_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumberPaths_UVa125 {

	static int[][] adjMat;
	static int N;
	static boolean[][] reach;

	static void floyd()
	{

		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					reach[i][j] |= reach[i][k] & reach[k][j];
		for(int i = 0; i < N; ++i)
			for(int j = i; j < N; ++j)
				if(reach[i][j] && reach[j][i])
					adjMat[i][j] = adjMat[j][i] = -1;
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					if(adjMat[i][j] != -1)
					{
						if(infiniteWay(i, j, k))
							adjMat[i][j] = -1;
						else
							adjMat[i][j] = adjMat[i][j] + (adjMat[i][k] * adjMat[k][j]);
					}


	}

	static boolean infiniteWay(int i, int j, int k)
	{
		if(adjMat[i][k] == -1 && adjMat[k][j] == -1)
			return true;
		if(adjMat[i][k] == -1 && adjMat[k][j] > 0)
			return true;
		if(adjMat[i][k] > 0 && adjMat[k][j] == -1)
			return true;
		return false;
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 0;

		while(sc.ready())
		{
			int E = sc.nextInt();
			if(E == 0)
			{	
				out.printf("matrix for city %d\n", tc++);
				continue;
			}
			N = 0;
			adjMat = new int[30][30];
			reach = new boolean[30][30];
			while(E-->0)
			{
				int u = sc.nextInt(), v = sc.nextInt();
				N = Math.max(N, Math.max(u, v));
				adjMat[u][v]++;
				reach[u][v] = true;
			}
			N = N + 1;

			floyd();

			out.printf("matrix for city %d\n", tc++);
			for(int i = 0; i < N; ++i)
			{
				for(int j = 0; j < N - 1; ++j)
					out.printf("%d ", adjMat[i][j]);
				out.printf("%d\n", adjMat[i][N - 1]);
				
			}
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
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
