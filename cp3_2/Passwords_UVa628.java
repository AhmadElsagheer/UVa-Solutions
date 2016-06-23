package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Passwords_UVa628 {

	static PrintWriter out = new PrintWriter(System.out);
	static String[] dic;
	static String[] pass;
	static int N, L;
	static String rule;
	
	public static void backtrack(int i)
	{
		if(i==L) { print(); return; }
		
		if(rule.charAt(i)=='0')
			for(int j = 0; j < 10; j++)
			{
				pass[i] = Integer.toString(j);
				backtrack(i+1);
			}
		else
			for(int j = 0; j < N; j++)
			{
				pass[i] = dic[j];
				backtrack(i+1);
			}
	}
	
	public static void print()
	{
		for(int i = 0; i < L - 1; i++)
			out.print(pass[i]);
		out.println(pass[L-1]);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(br.ready())
		{
			N = Integer.parseInt(br.readLine());
			dic = new String[N];
			for(int i = 0; i < N; i++)
				dic[i] = br.readLine();
			out.print("--\n");
			int M = Integer.parseInt(br.readLine());
			while(M-->0)
			{
				rule = br.readLine();
				L = rule.length();
				pass = new String[L];
				backtrack(0);
			}
		}
		out.flush();
		out.close();
	}
}
