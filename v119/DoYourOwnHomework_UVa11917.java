package v119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DoYourOwnHomework_UVa11917 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			int N = Integer.parseInt(br.readLine());
			while(N-->0)
			{
				st = new StringTokenizer(br.readLine());
				map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
			}
			int D = Integer.parseInt(br.readLine());
			String s = br.readLine();
			if(!map.containsKey(s))
			{
				out.printf("Case %d: Do your own homework!\n",k);continue;
			}
			int X = map.get(s);
			if(X<=D)
				out.printf("Case %d: Yesss\n",k);
			else
				if(X <= D + 5)
					out.printf("Case %d: Late\n",k);
				else
					out.printf("Case %d: Do your own homework!\n",k);
		}
		out.flush();
		out.close();
	}
}
