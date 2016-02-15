package cp2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BankruptBaker_UVa11308 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			sb.append(br.readLine().toUpperCase()).append("\n");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			TreeMap<String,Integer> ings = new TreeMap<String,Integer>();
			while(m-->0)
			{
				st = new StringTokenizer(br.readLine());
				ings.put(st.nextToken(),Integer.parseInt(st.nextToken()));
			}
			ArrayList<Recipe> possible  = new ArrayList<Recipe>();
			while(n-->0)
			{
				Recipe cur = new Recipe(br.readLine());
				m = Integer.parseInt(br.readLine());
				while(m-->0)
				{
					st = new StringTokenizer(br.readLine());
					cur.cost += ings.get(st.nextToken()) * Integer.parseInt(st.nextToken());
				}
				if(cur.cost <= b)
					possible.add(cur);
			}
			Collections.sort(possible);
			if(possible.size()==0)
				sb.append("Too expensive!\n");
			else
				for(int i = 0; i < possible.size(); i++)
					sb.append(possible.get(i).name).append("\n");
			sb.append("\n");
		}
		System.out.print(sb);
	}
}

class Recipe implements Comparable<Recipe>
{
	String name;
	int cost;
	Recipe(String x) {name = x;}
	@Override
	public int compareTo(Recipe o) {
		if(cost!=o.cost)
			return cost - o.cost;
		return name.compareTo(o.name);
	}
}