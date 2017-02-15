package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Anagram_UVa195 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			String in = sc.next(), inLow = in.toLowerCase();
			Pair[] chs = new Pair[in.length()];
			for(int i = 0; i < chs.length; ++i)
				chs[i] = new Pair(in.charAt(i), inLow.charAt(i));
			Arrays.sort(chs);
			StringBuilder sb = new StringBuilder();
			do{
				for(Pair p: chs)
					sb.append(p.c);
				sb.append("\n");
			}while(permute(chs));
			out.print(sb);
		}
		out.flush();
		out.close();
	}
	
	static boolean permute(Pair[] a)
	{
		int index = a.length - 2;
		while(index >= 0 && a[index].compareTo(a[index + 1]) >= 0)
			--index;
		if(index == -1)
			return false;
		int swapIndex = index + 1;
		while(swapIndex + 1 < a.length && a[index].compareTo(a[swapIndex + 1]) < 0)
			++swapIndex;
		swap(a, index, swapIndex);
		for(int i = index + 1, j = a.length - 1; i < j; ++i, --j)
			swap(a, i, j);
		return true;
	}
	
	static void swap(Pair[] a, int x, int y)
	{
		Pair tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
	
	static class Pair implements Comparable<Pair>
	{
		char c, d;
		
		Pair(char x, char y) { c = x; d = y; }
		
		public int compareTo(Pair p) 
		{ 
			if(d != p.d)
				return d - p.d; 
			return c - p.c;
		}
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