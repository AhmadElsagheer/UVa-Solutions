	package regionals.dhaka2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class AutomaticCheaterDetection {

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int Q = sc.nextInt();
			ArrayList<Question> q = new ArrayList<Question>(Q);
			while(Q-->0)
			{
				int d = sc.nextInt(), s = sc.nextInt();
				char r = sc.next().charAt(0);
				if(s == 1 && r == 'i' || s == 0 && r == 'c')
					continue;
				q.add(new Question(d, s, r));
			}
			Collections.sort(q);
			long ans = 0, prev = 0;
			for(Question question: q)
				if(question.leaked == 0)
					++prev;
				else
					ans += prev;
			out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static class Question implements Comparable<Question>
	{
		int leaked, correct, d;
		
		Question(int a, int b, char c) { d = a; leaked = b; correct = c == 'c' ? 1 : 0; }
		
		public int compareTo(Question q)
		{
			if(d != q.d)
				return d - q.d;
			return q.leaked - leaked;
		}
	}
	
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;
		
		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }
		
		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();	
		}
		
		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
	}
}
