package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
 
public class Ananagrams_UVa156 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		TreeMap<String, String> map = new TreeMap<>();
		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("#"))
				break;
			StringTokenizer st = new StringTokenizer(s);
			while(st.hasMoreTokens())
			{
				String t = st.nextToken(), an = trans(t), pre = map.get(an);
				if(pre == null)
					map.put(an, t);
				else if(!pre.equals(t))
					map.put(an, "?");
			}
		}
		ArrayList<String> ans = new ArrayList<>();
		for(String t: map.values())
			if(!t.equals("?"))
				ans.add(t);
		Collections.sort(ans);
		for(String t: ans)
			out.println(t);
		out.flush();
		out.close();
	}
	
	static String trans(String s)
	{
		char[] t = s.toLowerCase().toCharArray();
		Arrays.sort(t);
		return new String(t);
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