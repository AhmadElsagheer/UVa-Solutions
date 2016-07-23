package regionals.latinAmerica2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BloodGroups {

	static int n, m, match[];
	static ArrayList<Integer>[] adjList;
	static boolean[] vis;

	static int maxMatches()
	{
		int matches = 0;
		match = new int[m];
		Arrays.fill(match, -1);
		for(int i = 0; i < n; ++i)
		{
			vis = new boolean[n];
			matches += aug(i);
		}
		return matches;
	}

	static int aug(int u)
	{
		vis[u] = true;
		for(int v : adjList[u])
			if(match[v] == -1 || !vis[match[v]] && aug(match[v]) == 1)
			{
				match[v] = u;
				return 1;
			}
		return 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();


		while(sc.ready())
		{
			int N = sc.nextInt(), Q = sc.nextInt();
			n = m = N;

			boolean[][] parentAntigen = new boolean[N][N];
			boolean O = true;
			for(int i = 0; i < N; ++i)
			{
				int B = sc.nextInt();
				O &= B != N;
				while(B-->0)
					parentAntigen[i][sc.nextInt() - 1] = true;
			}

			while(Q-->0)
			{

				int B = sc.nextInt();
				if(B == 0)
				{
					sb.append(O ? "Y\n" : "N\n");
					continue;
				}

				boolean[] childAntigen = new boolean[N];
				for(int i = 0; i < B; ++i)
					childAntigen[sc.nextInt() - 1] = true;

				adjList = new ArrayList[n];
				for(int i = 0; i < N; ++i)
					adjList[i] = new ArrayList<Integer>();

				for(int j = 0; j < N; ++j)
					if(childAntigen[j])
						for(int i = 0; i < N; ++i)
							if(parentAntigen[i][j])
								adjList[i].add(j);

				sb.append(maxMatches() == B ? "Y\n" : "N\n");					

			}
		}
		out.print(sb);
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
