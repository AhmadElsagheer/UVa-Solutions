	package regionals.dhaka2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AutomaticCheaterDetection2 {

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int Q = sc.nextInt();
			int[] leaked = new int[11], nonleaked = new int[11];
			while(Q-->0)
			{
				int d = sc.nextInt(), s = sc.nextInt();
				char r = sc.next().charAt(0);
				if(s == 1 && r == 'c')
					leaked[d]++;
				else if(s == 0 && r == 'i')
					nonleaked[d]++;
			}
			
			long ans = 0, prev = 0;
			for(int i = 1; i <= 10; ++i)
			{
				ans += leaked[i] * prev;
				prev += nonleaked[i];
			}
			out.println(ans);
		}
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
	}
}
