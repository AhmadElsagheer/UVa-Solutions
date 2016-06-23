package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiscretePursuit_UVa11335 {
	
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		
		while(sc.ready())
		{
			int a = sc.nextInt(),b = 0, u = sc.nextInt(), v = sc.nextInt();
			
			if(a == 0)
				System.out.println(0);
			else
			{
				int p1 = 0, p2 = 0,uCop = 0, vCop = 0;
				int k1 = 0, k2 = 0;
				while(true)
				{
					a += u;
					p1 += ++uCop;
					k1++;
					if(a <= p1)
						break;
				}
				
				while(true)
				{
					b += v;
					p2 += ++vCop;
					k2++;
					if(b <= p2)
						break;
				}
				
				System.out.println(Math.max(k1, k2));
			}
		}
		
		
		
	}
	

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){br = new BufferedReader(new InputStreamReader(s));}

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
