package v012;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Password_UVa1262 {

	static String ans;
	static int k;
	
	static boolean generate(ArrayList<Integer>[] array, int col, long trial)
	{
		if(col == 5)
		{
			if(k == 1)
			{
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < 5; ++i, trial /= 26)
					sb.append((char)(trial%26 + 'A'));
				ans = sb.reverse().toString();
				return true;
			}
			else
				--k;
		}
		else
			for(int i = 0; i < array[col].size(); ++i)
				if(generate(array, col + 1, trial * 26 + array[col].get(i)))
					return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			k = sc.nextInt();
			
			int[][] grid1 = new int[5][6], grid2 = new int[5][6];
			for(int i = 0; i < 6; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < 5; ++j)
					grid1[j][i] = line.charAt(j) - 'A';
			}
			for(int i = 0; i < 6; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < 5; ++j)
					grid2[j][i] = line.charAt(j) - 'A';
			}
			for(int i = 0; i < 5; ++i)
			{
				Arrays.sort(grid1[i]);
				Arrays.sort(grid2[i]);
			}
			
			ArrayList<Integer>[] array = new ArrayList[5];
			for(int i = 0; i < 5; ++i)
			{
				array[i] = new ArrayList<Integer>(6);
				boolean[] used = new boolean[26];
				int j1 = 0, j2 = 0;
				while(j1 < 6 && j2 < 6)
					if(grid1[i][j1] == grid2[i][j2])
					{
						if(!used[grid1[i][j1]])
						{
							array[i].add(grid1[i][j1]);
							used[grid1[i][j1]] = true;
						}
						j1++; j2++;
					}
					else
						if(grid1[i][j1] > grid2[i][j2])
							j2++;
						else
							j1++;
			}
			
			ans = null;
			generate(array, 0, 0);
			if(ans == null)
				ans = "NO";
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
