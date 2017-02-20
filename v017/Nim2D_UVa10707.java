package v017;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Nim2D_UVa10707 {

	static int[] compute(int[][] grid, int W, int H)
	{
		int[][] R = new int[W][H], L = new int[W][H], D = new int[W][H], U = new int[W][H];
		
		for(int i = 0; i < W; i++)
			for(int j = 0, k = 0; j < H; j++)
				if(grid[i][j] == 1) L[i][j] = k++;
				else k = 0;
		
		for(int i = 0; i < W; i++)
			for(int j = H - 1, k = 0; j >= 0; j--)
				if(grid[i][j] == 1) R[i][j] = k++;
				else k = 0;
		
		for(int j = 0; j < H; j++)
			for(int i = 0, k = 0; i < W; i++)
				if(grid[i][j] == 1) D[i][j] = k++;
				else k = 0;
		
		for(int j = 0; j < H; j++)
			for(int i = W - 1, k = 0; i >= 0; i--)
				if(grid[i][j] == 1) U[i][j] = k++;
				else k = 0;
		
		int[] ret = new int[W*H];
		for(int i = 0, k = 0; i < W; i++)
			for(int j = 0; j < H; j++, k++)
				ret[k] = L[i][j] + R[i][j] + D[i][j] + U[i][j];
		Arrays.sort(ret);
		return ret;
	}
	
	static boolean equi(int[] x, int[] y)
	{
		for(int i = 0; i < x.length; i++)
			if(x[i] != y[i])
				return false;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		int tc = in.nextInt();
		while(tc-->0)
		{
			int W = in.nextInt(), H = in.nextInt(), n = in.nextInt();
			int[][] grid1 = new int[W][H], grid2 = new int[W][H];
			for(int i = 0; i < n; i++)
				grid1[in.nextInt()][in.nextInt()] = 1;
			for(int i = 0; i < n; i++)
				grid2[in.nextInt()][in.nextInt()] = 1;
			System.out.println(equi(compute(grid1,W,H), compute(grid2,W,H))?"YES":"NO");
		}
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
