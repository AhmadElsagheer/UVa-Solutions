package cp2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Conformity_UVa11286 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			TreeMap<Comb,Integer> map = new TreeMap<Comb,Integer>();
			int max = 0;int students = 0;
			while(n-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				Comb cur = new Comb();
				for(int i = 0; i < 5; i++)
					cur.add(i, Integer.parseInt(st.nextToken()));
				cur.sort();
				Integer count = map.get(cur);
				if(count==null)
					count = 1;
				else
					count++;
				map.put(cur, count);
				if(count>max)
				{
					max = count;
					students = count;
				}
				else
					if(count==max)
						students += count;
			}
			sb.append(students).append("\n");
		}
		System.out.print(sb);
	}
}

class Comb implements Comparable<Comb>
{
	int[] courses = new int[5];
	
	void add(int idx, int val) {courses[idx] = val;}
	
	void sort() {Arrays.sort(courses);}
	
	public int compareTo(Comb o) {
		for(int i = 0; i < 5; i++)
			if(courses[i]!=o.courses[i])
				return courses[i] - o.courses[i];
		return 0;
	}
	
	public boolean equals(Comb o){return this.compareTo(o) == 0;}
	
}