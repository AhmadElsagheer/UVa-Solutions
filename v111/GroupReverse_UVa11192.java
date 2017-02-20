package v111;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GroupReverse_UVa11192{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n;
		while((n=Integer.parseInt(st.nextToken()))!=0)
		{
			String r = "";
			String s = st.nextToken();
			int size = s.length()/n;
			for(int i = 0; i < s.length(); i+=size)
				r += getReverse(s.substring(i,i+size));
			sb.append(r+"\n");
			st = new StringTokenizer(br.readLine());
		}
		System.out.print(sb);
		
	}
	
	public static String getReverse(String s)
	{
		String r = "";
		for(int i = 0; i < s.length(); i++)
			r = s.charAt(i) + r;
		return r;
	}
	
}	