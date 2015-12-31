package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MaximumSumII_UVa10656 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			ArrayList<Integer> out = new ArrayList<Integer>(N);
			while(N-->0)
			{
				int cur = Integer.parseInt(br.readLine());
				if(cur==0)
					continue;
				out.add(cur);
			}
			if(out.size()==0)
				sb.append("0\n");
			else
			{
				for(int i = 0; i < out.size() - 1; i++)
					sb.append(out.get(i)).append(" ");
				sb.append(out.get(out.size()-1)).append("\n");
			}
		}
		
		System.out.print(sb);
	}
}
