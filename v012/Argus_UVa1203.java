package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Argus_UVa1203 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Pair> r = new PriorityQueue<Pair>(1000);
		while(true)
		{
			String line = br.readLine();
			if(line.equals("#"))
				break;
			StringTokenizer st = new StringTokenizer(line);
			st.nextToken();
			r.add(new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		int k = Integer.parseInt(br.readLine());
		while(k-->0)
		{
			Pair cur = r.remove();
			sb.append(cur.id).append("\n");
			cur.period += cur.c;
			r.add(cur);
		}
		System.out.print(sb);
		
	}
}

class Pair implements Comparable<Pair>
{
	int id, period, c;
	Pair(int x, int y)
	{
		id = x;
		period = c = y;
	}
	
	public int compareTo(Pair x)
	{
		if(period!=x.period)
			return period - x.period;
		return id - x.id;
	}
}