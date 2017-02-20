package v004;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TheDepartmentOfRedundancyDepartment_UVa484 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		ArrayList<Integer> nums = new ArrayList<Integer>();

		while(sc.ready())
		{
			int x = sc.nextInt();
			Integer f = map.get(x);
			if(f == null)
			{
				nums.add(x);
				f = 0;
			}
			map.put(x, f + 1);
		}
		for(Integer x : nums)
			out.format("%d %d\n", x, map.get(x));
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
			
		public boolean ready() throws IOException {return br.ready() || st != null && st.hasMoreTokens();}


	}
}
