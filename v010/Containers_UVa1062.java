package v010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Containers_UVa1062 {

	static int compute(String line)
	{
		ArrayList<Integer> stacks = new ArrayList<Integer>();
		boolean[] hasStack = new boolean[26];
		
		for(int i = 0; i < line.length(); i++)
		{
			int c = line.charAt(i) - 'A';
			if(!hasStack[c])
			{
				hasStack[c] = true;
				int min = 100, idx = -1;
				for(int j = 0; j < stacks.size(); j++)
				{
					int b = stacks.get(j);
					if(c <= b && b - c < min)
					{
						min = c -b;
						idx = j;
					}
				}
				if(idx==-1)
					stacks.add(c);
				else
				{
					hasStack[stacks.get(idx)]  =false;
					stacks.set(idx, c);
				}
			}
		}
		return stacks.size();
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			String line = new StringTokenizer(br.readLine()).nextToken();
			if(line.equals("end"))
				break;
			sb.append("Case "+k+++": "+compute(line)+"\n");
		}
		System.out.print(sb);
	}
}
