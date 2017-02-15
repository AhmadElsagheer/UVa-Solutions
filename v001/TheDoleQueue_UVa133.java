package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheDoleQueue_UVa133 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int n = sc.nextInt(), k = sc.nextInt() - 1, m = sc.nextInt() - 1;
			if(n == 0)
				break;
			ArrayList<Integer> guys = new ArrayList<>(n);
			for(int i = 0; i < n; ++i)
				guys.add(i + 1);
			int ptr1 = 0, ptr2 = n - 1;
			while(true)
			{
				for(int i = 0; i < k; ++i)
					ptr1 = (ptr1 + 1) % guys.size();
				for(int i = 0; i < m; ++i)
					ptr2 = (ptr2 - 1 + guys.size()) % guys.size();
				if(ptr1 != ptr2)
				{
					out.printf("%3d%3d", guys.get(ptr1), guys.get(ptr2));
					if(guys.size() > 2)
						out.print(",");
				}
				else
				{
					out.printf("%3d", guys.get(ptr1));
					if(guys.size() > 1)
						out.print(",");
				}
				guys.remove(ptr1);
				if(guys.size() == 0)
					break;
				if(ptr1 != ptr2)
				{
					if(ptr2 > ptr1)
						ptr2 = (ptr2 - 1 + guys.size()) % guys.size();
					guys.remove(ptr2);
					if(guys.size() == 0)
						break;
					if(ptr2 < ptr1)
						ptr1 = (ptr1 - 1 + guys.size()) % guys.size();
				}			
				ptr2 = (ptr2 - 1 + guys.size()) % guys.size();
				ptr1 %= guys.size();
				ptr2 %= guys.size();
			}
			out.println();
		}
		out.flush();
		out.close();
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