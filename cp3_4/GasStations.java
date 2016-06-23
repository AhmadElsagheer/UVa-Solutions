package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GasStations {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			if(L==0)
				break;
			int G = Integer.parseInt(st.nextToken());
			Interval[] stations = new Interval[G];
			for(int i = 0; i < G; i++)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				stations[i] = new Interval(x-r,x+r);
			}
			Arrays.sort(stations);
			int a = 0, count = 0, s = 0;
			while(a<L)
			{
				int max = a;
				while(s < G && stations[s].left<=a)
						max = Math.max(stations[s++].right,max);	
				if(max==a)
					break;
				a = max;
				count++;
			}
			
			if(a<L)
				out.println(-1);
			else
				out.println(G - count);
			
		}
		out.flush();
	}
}

class Interval implements Comparable<Interval>
{
	int left, right;
	Interval(int x, int y) {left = x; right = y;}
	
	public int compareTo(Interval o) {
		if(this.left!=o.left)
			return this.left - o.left;
		return o.right - this.right;
	}
}