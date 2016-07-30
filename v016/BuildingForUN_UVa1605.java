package v016;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class BuildingForUN_UVa1605 {
	
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		char[] c = new char[50];
		for(int i = 0; i < 26; ++i)
			c[i] = (char)('A' + i);
		for(int i = 0; i < 24; ++i)
			c[i + 26] = (char)('a' + i);
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				sb.append("\n");
			int n = sc.nextInt();
			char[][][] ans = new char[2][n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
				{
					ans[0][i][j] = c[i];
					ans[1][j][i] = c[i];
				}
			
			sb.append("2 " + n + " " + n + "\n");
			for(int i = 0; i < 2; ++i)
			{
				for(int j = 0; j < n; ++j)
				{
					for(int k = 0; k < n; ++k)
						sb.append(ans[i][j][k]);
					sb.append("\n");
				}
				if(i == 0)
					sb.append("\n");
			}
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), " |(|)|:");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		boolean ready() throws IOException { return br.ready(); }
		
		boolean hasNext() throws IOException
		{
			while (br.ready() && (st == null || !st.hasMoreTokens())) 
				st = new StringTokenizer(br.readLine(), " |(|)|:");
			return st != null && st.hasMoreTokens();
		}
	}
}