package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Antiarithmetic_UVa10730 {

	
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		PrintWriter out  = new PrintWriter(System.out);
		
		while(true)
		{
			String x = in.next();
			if(x.equals("0")) break;
			int n = Integer.parseInt(x.substring(0,x.length()-1));
			int[] a = new int[n], b = new int[n];
			for(int i = 0; i < n; i++)
			{
				a[i] = in.nextInt();
				b[a[i]] = i;
			}
			out.println(isAnti(a,b,n)?"yes":"no");
		}
		out.flush();
		
	}
	
	static boolean isAnti(int[] a, int[] b,int n)
	{
		int x,y;
		for(int i = 0; i < n - 2; i++)
			for(int j = 1; j <= n>>1; j++)
			{
				x = a[i] + j; y = a[i] + (j<<1);
				if(x < n && y < n && (b[x] > i && b[y] > b[x] || b[x] < i && b[y] < b[x]))
					return false;
			}
		return true;
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

