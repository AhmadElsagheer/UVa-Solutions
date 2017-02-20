package v111;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WFFProof_UVa11103 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			String line = sc.next();
			if(line.equals("0"))
				break;
			StringBuilder sb = new StringBuilder();
			Queue<Character> ll = new LinkedList<Character>();
			Queue<Character> uu = new LinkedList<Character>();
			for(int i = 0; i < line.length(); ++i)
				if(Character.isLowerCase(line.charAt(i)))
					ll.add(line.charAt(i));
				else
					uu.add(line.charAt(i));
			if(ll.isEmpty())
				out.println("no WFF possible");
			else
			{
				sb.append(ll.remove());
				while(!uu.isEmpty())
				{
					char c = uu.remove();
					if(c == 'N')
						sb.append(c);
					else
						if(!ll.isEmpty())
							sb.append(ll.remove()).append(c);
				}
				out.println(sb.reverse());
			}
		}

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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
