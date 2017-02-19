package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MyDearNeighbours_UVa10928 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			int P = Integer.parseInt(br.readLine());
			int min = P;
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i = 1; i <= P; i++)
			{
				int cur = new StringTokenizer(br.readLine()).countTokens();
				if(cur < min)
				{
					list = new ArrayList<Integer>();
					list.add(i);
					min = cur;
				}
				else
					if(cur==min)
						list.add(i);
			}
			for(int i = 0; i < list.size() - 1; i++)
				sb.append(list.get(i)+" ");
			sb.append(list.get(list.size()-1)).append("\n");
			if(TC!=0)
				br.readLine();
			
		}
		System.out.print(sb);
		
	}
}
