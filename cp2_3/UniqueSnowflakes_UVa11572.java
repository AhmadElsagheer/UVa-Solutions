package cp2_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class UniqueSnowflakes_UVa11572 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(TC-->0)
		{
			int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
			int first = 1; int last = 1;int max = 0;
			while(N-->0)
			{
				int cur = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
				if(map.containsKey(cur) && map.get(cur)>=first)
				{
					max = Math.max(last-first,max);
					first = map.get(cur) + 1;
					map.put(cur, last++);
				}
				else
					map.put(cur, last++);
				
				
			}
			max = Math.max(last-first, max);
			sb.append(max+"\n");
		}
		
		System.out.print(sb);
	}
}
