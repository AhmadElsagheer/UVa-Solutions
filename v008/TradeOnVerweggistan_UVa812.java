package v008;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class TradeOnVerweggistan_UVa812 {

	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			int w = in.nextInt();
			if(w == 0) break;
			if(k != 1) sb.append("\n"); 
			ArrayList<ArrayList<Integer>> piles = new ArrayList<ArrayList<Integer>>();
		
			int total = 0;
			for(int i = 0; i < w; i++)
			{
				int max = 0, sum = 0, b = in.nextInt();
				ArrayList<Integer> count = new ArrayList<Integer>();
				count.add(0);
				for(int j = 1; j <= b; j++)
				{
					sum += 10 - in.nextInt();
					if(sum > max)
					{
						max = sum;
						count = new ArrayList<Integer>();
						count.add(j);
					}
					else
						if(sum == max)
							count.add(j);
				}
				total += max;
				piles.add(count);
			}
			
			sb.append("Workyards "+k+++"\n");
			sb.append("Maximum profit is "+total+".\n");
			sb.append("Number of pruls to buy:");
			
			boolean[] used = new boolean[1001];
			used[0] = true;
			for(int i = 0; i < piles.size(); i++)
			{
				boolean[] tmp = new boolean[1001];
				for(int j = 0; j < piles.get(i).size(); j++)
				{
					int x = piles.get(i).get(j);
					for(int l = 0; l <= 1000; l++)
						if(used[l])
							tmp[l + x] = true;
				}
				used = tmp;
			}
			
			for(int i = 0, j = 0; i <= 1000 && j < 10; i++)
				if(used[i])
				{
					sb.append(" "+i);
					j++;
				}
			sb.append("\n");
			
		}
		System.out.print(sb);
		
	}
	
	static class InputReader {
		StringTokenizer st;
		BufferedReader br;

		public InputReader(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}


	}
}
