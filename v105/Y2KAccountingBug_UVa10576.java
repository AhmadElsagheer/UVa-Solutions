package v105;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Y2KAccountingBug_UVa10576 {
	
	static int s, d, val[] = new int[12];
	static int max;
	static void bt(int m, int sum)
	{
		if(m == 12)
		{
			int c = 0;
			for(int i = 0; i + 5 <= 12; ++i)
			{
				int k = 0;
				for(int j = i; j < i + 5; ++j)
					k += val[j];
				if(k < 0)
					c++;
			}
			if(c == 8)
				max = Math.max(max, sum);
		}
		else
		{
			val[m] = s;
			bt(m + 1, sum + s);
			val[m] = -d;
			bt(m + 1, sum - d);
		}
		
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(sc.ready())
		{
			s = sc.nextInt();
			d = sc.nextInt();
			max = -1;
			bt(0, 0);
			if(max == -1)
				out.println("Deficit");
			else
				out.println(max);
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
