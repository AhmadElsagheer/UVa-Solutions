package cp3_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DocumentAnalyzer_UVa11860 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int[] pos = new int[100001];
			int count = 0, n = 0;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			while(true)
			{
				String line = sc.nextLine();
				if(line.equals("END"))
					break;
				StringBuilder word = new StringBuilder();
				for(int i = 0; i < line.length() + 1; ++i)
					if(i < line.length() && line.charAt(i) <= 'z' && line.charAt(i) >= 'a')
						word.append(line.charAt(i));
					else
					{
						if(word.length() > 0)
						{
							String w = word.toString();
							Integer idx = map.get(w);
							if(idx == null)
								map.put(w, idx = ++count);
							pos[++n] = idx;

						}
						word = new StringBuilder();
					}
			}
			int[] f = new int[count + 1];
			int p = -1, q = n, total = 0;
			for(int i = 1, j = 1; i <= n; ++i)
			{
				if(f[pos[i]]++ == 0)
					total++;
				
				if(total >= count)
				{
					
					while(f[pos[j]] > 1)
						f[pos[j++]]--;
					
					if(i - j < q - p)
					{
						q = i;
						p = j;
					}
				}
			}
			out.printf("Document %d: %d %d\n", t, p, q);
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
