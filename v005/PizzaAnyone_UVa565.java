package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PizzaAnyone_UVa565 {
		
	static int N;
	static int[][] request;
	static boolean[] order;
	
	static boolean bt(int idx, int msk)
	{
		if((1<<N) - 1 == msk)
			return true;
		if(idx == 16)
			return false;
		
		//leave
		if(bt(idx + 1, msk | request[idx][0]))
			return true;
		//take
		order[idx] = true;
		if(bt(idx + 1, msk | request[idx][1]))
			return true;
		order[idx] = false;
		return false;
	}
	
	public static void main(String[] args) throws IOException {

		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			ArrayList<String> friends = new ArrayList<String>();
			String line;
			while(!(line = sc.next()).equals("."))
				friends.add(line);
			N = friends.size();
			request = new int[16][2];
			for(int i = 0; i < N; ++i)
			{
				String cur = friends.get(i);
				for(int j = 0; j < cur.length() - 2; j += 2)
				{
					int c = cur.charAt(j), t = cur.charAt(j + 1) - 'A';
					request[t][c == '+' ? 1 : 0] |= 1<<i;
				}
			}
			
			order = new boolean[16];
			if(!bt(0, 0))
				out.println("No pizza can satisfy these requests.");
			else
			{
				StringBuilder sb = new StringBuilder();
				sb.append("Toppings: ");
				for(int i = 0; i < 16; ++i)
					if(order[i])
						sb.append((char)(i + 'A'));
				out.println(sb);
			}
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
