package cp2_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class WordIndex_UVa417 {

	
	
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		int k = 1;
		Queue<String> q = new LinkedList<String>();
		for(char c = 'a'; c <= 'z'; c++) q.add(""+c);
		while(!q.isEmpty())
		{
			String cur = q.remove();
			map.put(cur, k++);
			
			if(cur.length() < 5) for(char c = (char)(cur.charAt(cur.length() - 1) + 1); c <= 'z' ; c++) q.add(cur+c);
		}
		while(sc.ready())
		{
			String word = sc.next();
			if(map.containsKey(word))
				sb.append(map.get(word)+"\n");
			else
				sb.append("0\n");
			
		}
		System.out.print(sb);
	}
	
	
	
	
	
	
	
	static class Scanner {
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

		public boolean ready() throws IOException {return br.ready();}


	}

}
