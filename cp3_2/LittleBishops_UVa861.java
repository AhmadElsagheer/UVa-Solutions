package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LittleBishops_UVa861 {

	static long ways;
	static boolean[] used;
	static int N;

	static void bt(int idx, int bishops)
	{
		if(idx == (N<<1) - 1)
		{
			if(bishops != 0)
				return;
			long add = 1;
			for(int i = 0, even = 0, odd = 0; i < (N<<1) - 1; ++i)
				if(used[i])
					add *= (i>>1) + 1 - (i/2%2 == 0 ? even++ : odd++);
			ways += add;
		}
		else
		{
			used[idx] = true;
			bt(idx + 1, bishops - 1);
			used[idx] = false;
			bt(idx + 1, bishops);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			int k = sc.nextInt();
			used = new boolean[(N<<1)-1];
			ways = 0;
			bt(0, k);
			out.println(ways);
		}
		
		out.flush();
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
