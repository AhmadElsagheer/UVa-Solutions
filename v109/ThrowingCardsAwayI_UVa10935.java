package v109;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ThrowingCardsAwayI_UVa10935 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			ArrayList<Integer> dis = new ArrayList<Integer>(n);
			Queue<Integer> deck = new LinkedList<Integer>();
			for(int i = 1; i <= n; ++i)
				deck.add(i);
			while(deck.size() > 1)
			{
				dis.add(deck.remove());
				deck.add(deck.remove());
			}
			sb.append("Discarded cards:");
			for(int i = 0; i < n - 1; ++i)
				sb.append(" ").append(dis.get(i)).append(i == n - 2 ? "" : ",");
			sb.append("\n");
			sb.append("Remaining card: ").append(deck.peek()).append("\n");
		}
		System.out.print(sb);
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
