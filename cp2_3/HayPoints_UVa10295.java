package cp2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class HayPoints_UVa10295 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		TreeMap<String, Integer> dic = new TreeMap<String, Integer>();
		while(n-->0)
		{
			st = new StringTokenizer(br.readLine());
			dic.put(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		while(m-->0)
		{
			long ans = 0;
			String line;
			while(!(line = br.readLine()).equals("."))
			{
				st = new StringTokenizer(line);
				while(st.hasMoreTokens())
				{
					String word = st.nextToken();
					if(dic.containsKey(word))
						ans += dic.get(word);
				}
			}
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb);
	}
}
