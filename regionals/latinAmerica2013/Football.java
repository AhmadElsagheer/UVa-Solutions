package regionals.latinAmerica2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Football {

	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int N = sc.nextInt(), G = sc.nextInt();
			int[] matches = new int[N];
			for(int i = 0; i < N; ++i)
				matches[i] = -sc.nextInt() + sc.nextInt();
			int score = 0;
			Arrays.sort(matches);
			for(int i = 0; i < N; ++i)
				if(matches[i] < 0)
					score += 3;
				else
					if(G - matches[i] - 1 >= 0)
					{
						score += 3;
						G -= matches[i] + 1;
					}
					else
						if(matches[i] == 0)
							score++;
						else
							if(G - matches[i] >= 0)
							{
								score++;
								G -= matches[i];
							}
			out.println(score);
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
