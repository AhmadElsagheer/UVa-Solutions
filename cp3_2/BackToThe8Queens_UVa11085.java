package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BackToThe8Queens_UVa11085 {

	static ArrayList<Integer> possible = new ArrayList<Integer>(100);
	
	static int[] row = new int[8];
	
	static void generate(int col, int used, int conf)
	{
		if(col == 8)
			possible.add(conf);
		for(int i = 0; i < 8; ++i)
			if(((1<<i) & used) == 0)
			{
				if(!check(col, i))
					continue;
				row[col] = i;
				generate(col + 1, used | (1<<i), conf * 8 + i);
			}
	}
	
	static boolean check(int c, int r)
	{
		for(int i = 0; i < c; ++i)
			if(Math.abs(c - i) == Math.abs(r - row[i]))
				return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		generate(0, 0, 0);
		
		int tc = 1;
		while(sc.ready())
		{
			int input = 0;
			for(int i = 0; i < 8; ++i)
				input = input * 8 + sc.nextInt() - 1;
			int min = 8;
			for(int i = 0; i < 92; ++i)
			{
				int cur = 0;
				int x = input, y = possible.get(i);
				for(int j = 0; j < 8; ++j, x /= 8, y /= 8)
					if(x%8 != y%8)
						++cur;
				min = Math.min(cur, min);
			}
			out.format("Case %d: %d\n", tc++, min);
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
