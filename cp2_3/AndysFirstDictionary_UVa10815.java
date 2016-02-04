package cp2_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class AndysFirstDictionary_UVa10815 {

	public static void main(String[] args) throws IOException {


		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		TreeSet<String> set = new TreeSet<String>();

		while(sc.ready())
		{

			StringBuilder sb = new StringBuilder();
			String line = sc.nextLine().toLowerCase();
			for(int i = 0; i < line.length(); ++i)
				if(line.charAt(i) >= 'a' && line.charAt(i) <= 'z')
					sb.append(line.charAt(i));
				else
				{
					String word = sb.toString();
					if(!word.isEmpty())
						set.add(word);
					sb = new StringBuilder();
				}
			String word = sb.toString();
			if(!word.isEmpty())
				set.add(word);
			sb = new StringBuilder();
				
		}

		
		for(String s : set)
			out.println(s);

		out.flush();
		out.close();


	}



	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}

		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}


