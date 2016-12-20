package v002;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
 
public class RepeatingDecimals_UVa202 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int num = sc.nextInt(), den = sc.nextInt();
			out.printf("%d/%d = ", num, den);
			int con  = num / den;
			num %= den;
			StringBuilder quo = new StringBuilder();
			int[] vis = new int[den];
			int time = 1;
			while(vis[num] == 0)
			{
				vis[num] = time++;
				num *= 10;
				quo.append(num / den);
				num %= den;
			}
			
			String rep = quo.substring(vis[num] - 1), pre = quo.substring(0, vis[num] - 1);
			if(quo.length() <= 50)
				out.printf("%d.%s(%s)\n", con, pre, rep);
			else
			{
				rep = rep.substring(0, 50 - pre.length());
				out.printf("%d.%s(%s...)\n", con, pre, rep);
			}
			out.printf("   %d = number of digits in repeating cycle\n\n", quo.length() - pre.length());
		}
		out.flush();
		out.close();
	}
		
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