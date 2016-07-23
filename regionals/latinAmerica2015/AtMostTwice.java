package regionals.latinAmerica2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AtMostTwice {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())	
		{
			long x = sc.nextLong();
			char[] s = (x + "").toCharArray();
			int[] f = new int[10];
			for(int i = 0; i < s.length; ++i)
			{
				int a = s[i] - '0';
				if(f[a] == 2)
				{
					while(true)
					{
						boolean done = false;
						for(int j = a - 1; j >= 0; --j)
							if(f[j] < 2)
							{
								done = true;
								s[i] = (char)(j + '0');
								f[j]++;
								break;
							}
						if(done)
						{
							for(int k = i + 1; k < s.length; ++k)
								for(int b = 9; b >= 0; --b)
									if(f[b] < 2)
									{
										f[b]++;
										s[k] = (char)(b + '0');
										break;
									}
							
							break;
						}
						
						a = s[--i] - '0';
						f[a]--;
					}
					break;
				}
				f[a]++;
			}
			out.println(Long.parseLong(new String(s)));
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
		
		long nextLong() throws NumberFormatException, IOException { return Long.parseLong(next()); }
		
		boolean ready() throws IOException { return br.ready(); }
	}
}
