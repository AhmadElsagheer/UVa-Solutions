package v105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheOuroborosProblem_UVa10506 {

	static int N, M, L;
	static int[] seq;
	static int[] used;
	
	static boolean bt(int idx)
	{
		if(idx == L)
		{
			boolean res = true;
			for(int i = L - M + 1; i < L; ++i)
			{
				int p = 0;
				for(int j = 0; j < M; ++j)
					p = p * N + seq[(i + j)%L];
				if(used[p] != 0)
					res = false;
				used[p]++;
			}
			
			for(int i = L - M + 1; i < L; ++i)
			{
				int p = 0;
				for(int j = 0; j < M; ++j)
					p = p * N + seq[(i + j)%L];
				used[p]--;
			}
			return res;
		}
		for(int i = 0; i < N; ++i)
		{
			seq[idx] = i;
			if(idx >= M - 1)
			{
				int p = 0;
				for(int j = idx - M + 1; j <= idx; ++j)
					p = p * N + seq[j];
				if(used[p] == 0)
				{
					used[p] = 1;
					if(bt(idx + 1))
						return true;					
					used[p] = 0;
				}
			}
			else
				if(bt(idx + 1))
					return true;
		}
		return false;
	}
	
	public static void sol() throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		while(sc.ready())
		{
			M = sc.nextInt(); N = sc.nextInt(); L = 1;
			for(int i = 0; i < M; ++i)
				L *= N;
			seq = new int[L];
			used = new int[L];
			bt(0);
			for(int x: seq)
				sb.append(x);
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	
	public static void main(String[] args) 
	{
		new Thread(null, new Runnable() { public void run() {try {sol();} catch (Exception e) {
			}}}, "2",1<<26).start();
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

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}