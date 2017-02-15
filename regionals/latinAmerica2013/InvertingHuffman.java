package regionals.latinAmerica2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InvertingHuffman {

	
	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int n = sc.nextInt(), lvl = 0;
			int[] code = new int[52];
			for(int i = 0; i < n; ++i)
			{
				int x = sc.nextInt();
				code[x]++;
				lvl = Math.max(lvl, x);
			}
			
			ArrayList<Long> nodes = new ArrayList<Long>(code[lvl]>>1);
			for(int i = 0; i < code[lvl]>>1; ++i)
				nodes.add((long) 2);

			long ans = code[lvl--], nxtVal = 1;
			
			while(lvl > 0)
			{
				ans += nxtVal * code[lvl];
				for(int i = 0; i < code[lvl]; ++i)
					nodes.add(nxtVal);
				int size = nodes.size();
				ArrayList<Long> nxtNodes = new ArrayList<Long>(size);
				
				for(int i = 0; i < size; i += 2)
				{
					long val = nodes.get(i) + nodes.get(i + 1);
					nxtNodes.add(val);
					nxtVal = Math.max(nxtVal, Math.max(nodes.get(i), nodes.get(i+1)));
				}
				nodes = nxtNodes;
				lvl--;
			}
			out.println(ans);	
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
