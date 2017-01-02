package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
 
public class KnightMoves_UVa439 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int[][] dist = new int[64][64];
		int[] dx = {1, 1, -1, -1, 2, 2, -2, -2}, dy = {2, -2, 2, -2, 1, -1, 1, -1};
		for(int i = 0; i < 64; ++i)
		{
			Arrays.fill(dist[i], 100);
			dist[i][i] = 0;
			int x = i >> 3, y = i & 7;
			for(int k = 0; k < 8; ++k)
			{
				int xx = x + dx[k], yy = y + dy[k];
				if(xx >= 0 && yy >= 0 && xx < 8 && yy < 8)
					dist[i][xx * 8 + yy] = 1;
			}
		}
		
		for(int k = 0; k < 64; ++k)
			for(int i = 0; i < 64; ++i)
				for(int j = 0; j < 64; ++j)
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
		
		String s1, s2;
		while(sc.ready())
		{
			int x = getCell(s1 = sc.next()), y = getCell(s2 = sc.next());
			out.printf("To get from %s to %s takes %d knight moves.\n", s1, s2, dist[x][y]);
		}
		out.flush();
		out.close();
	}
	
	static int getCell(String s)
	{
		return (s.charAt(0) - 'a') * 8 + s.charAt(1) - '1';
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
 
		public double nextDouble() throws IOException { return Double.parseDouble(next()); }
 
		public boolean ready() throws IOException {return br.ready();} 
	}
} 