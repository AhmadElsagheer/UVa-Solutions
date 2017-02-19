package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Forests_UVa10227 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			StringTokenizer st=  new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			Opinion[] ops = new Opinion[P+1];
			for(int i = 1; i <= P; i++)
				ops[i] = new Opinion(T);
			while(br.ready())
			{
				String line = br.readLine();
				if(line.isEmpty())
					break;
				st = new StringTokenizer(line);
				int cur = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				if(!ops[cur].trees.contains(t))
					ops[cur].trees.add(t);
			}
			for(int i = 1; i <= P; i++)
				Collections.sort(ops[i].trees);
			int count = 0;
			for(int i = 1; i <= P; i++)
				if(ops[i]!=null)
				{
					count++;
					for(int j = i + 1; j <= P; j++)
						if(ops[j]!=null && ops[i].equals(ops[j]))
							ops[j] = null;
				}
			sb.append(count).append("\n");
			if(TC!=0)
				sb.append("\n");
		}
		
		System.out.print(sb);
	}
}

class Opinion
{
	ArrayList<Integer> trees;
	
	Opinion(int T)
	{
		trees = new ArrayList<Integer>(T);
	}
	boolean equals(Opinion x)
	{
		if(trees.size()!=x.trees.size())
			return false;
		for(int i = 0; i < trees.size(); i++)
			if(trees.get(i)!=x.trees.get(i))
				return false;
		return true;
	}
}