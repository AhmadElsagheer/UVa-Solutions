package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AustralianVoting_UVa10142 {

	static int winner(int N, boolean[] eliminated, ArrayList<ArrayList<Integer>> votes)
	{
		int[] local = new int[N+1];
		for(int i = 0; i < votes.size(); i++)
		{
			int j;
			for(j = 0; eliminated[votes.get(i).get(j)]; j++);
			local[votes.get(i).get(j)]++;
		}
		int max = -1;int index = -1;
		for(int i = 1; i <= N; i++)
			if(local[i] > max)
			{
				max = local[i];
				index = i;
			}
		
		if((local[index]*100+votes.size()-1)/votes.size() > 50)
			return index;
		
		int min = votes.size();
		for(int i = 1; i <= N; i++)
			if(!eliminated[i] && local[i] < min)
				min = local[i];
		
		if(local[index]==min)
			return -1;
		
		for(int i = 1; i <= N; i++)
			if(local[i]==min)
				eliminated[i] = true;
		return 0;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			int N = Integer.parseInt(br.readLine());
			String[] names = new String[N+1];
			for(int i = 1; i <= N; i++)
				names[i] = br.readLine();
			ArrayList<ArrayList<Integer>> votes = new ArrayList<ArrayList<Integer>>();
			
			while(br.ready())
			{
				String line = br.readLine();
				if(line.isEmpty())
					break;
				StringTokenizer st = new StringTokenizer(line);
				ArrayList<Integer> cur = new ArrayList<Integer>(N);
				for(int i = 0; i < N; i++)
					cur.add(Integer.parseInt(st.nextToken()));
				votes.add(cur);
				
			}
			boolean[] eliminated = new boolean[N+1];
			int res; 
			while((res = winner(N,eliminated,votes))==0);
			if(res==-1)
			{
				for(int i = 1; i <= N; i++)
					if(!eliminated[i])
						sb.append(names[i]+"\n");
			}
			else
				sb.append(names[res]+"\n");
			
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
