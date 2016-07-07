package v009;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Permutations_UVa941 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[] s = sc.next().toCharArray();
			long k = sc.nextLong();
			
			int[] f = new int[26];
			for(char c: s)
				f[c-'a']++;
			
			int[] facNum = new int[s.length];
			for(int i = 1; i <= s.length; k /= i, ++i)
				facNum[s.length - i] = (int) (k % i);
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < s.length; ++i)
			{
				int z = facNum[i];
				for(int j = 0; j < 26; ++j)
				{
					if(f[j] == 0)
						continue;
					if(f[j] > z)
					{
						sb.append((char) (j + 'a'));
						f[j]--;
						break;
					}
					z -= f[j];
				}
			}
			out.println(sb);
			
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){ br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){ br = new BufferedReader(r);}

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


	}
}