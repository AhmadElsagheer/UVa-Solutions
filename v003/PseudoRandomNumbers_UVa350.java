package v003;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
 
public class PseudoRandomNumbers_UVa350 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			int Z = sc.nextInt(), I = sc.nextInt(), M = sc.nextInt(), L = sc.nextInt();
			if(M == 0)
				break;
			Z = fix(Z, M);
			I = fix(I, M);
			L = fix(L, M);
			int[] vis = new int[M];
			int time = 1;
			while(true)
			{
				if(vis[L] != 0)
				{
					out.printf("Case %d: %d\n", tc++, time - vis[L]);
					break;
				}
				vis[L] = time++;
				L = (L * Z + I) % M;
			}
		}
		out.flush();
		out.close();
	}
	
	static int fix(int x, int m) { return (x % m + m) % m; }
		
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;
 
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader s){	br = new BufferedReader(s);}
 
		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
 
		public int nextInt() throws IOException {return Integer.parseInt(next());}
 
		public long nextLong() throws IOException {return Long.parseLong(next());}
 
		public String nextLine() throws IOException {return br.readLine();}
 
		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}
 
		public boolean ready() throws IOException {return br.ready();}
 
 
	}
} 