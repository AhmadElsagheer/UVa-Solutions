package cp6_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Election_UVa10374 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(tc-->0)
		{
			br.readLine();
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			TreeMap<String, String> map = new TreeMap<String, String>();
			TreeMap<String, Integer> votes = new TreeMap<String, Integer>();
			while(n-->0)
			{
				String name = br.readLine();
				String party = br.readLine();
				
				map.put(name, party);
			}
			int m = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			while(m-->0)
			{
				String name = br.readLine();
				if(map.containsKey(name))
				{
					if(votes.containsKey(name))
						votes.put(name, votes.get(name) + 1);
					else
						votes.put(name, 1);
				}
			}
			
			int max = 0, count = 0;
			String winner = "";
			for(Entry<String, Integer> entry: votes.entrySet())
			{
				int val = entry.getValue();
				if(val > max)
				{
					winner  = entry.getKey();
					max = val;
					count = 1;
				}
				else
					if(val == max)
						count++;
			}
			if(count != 1)
				System.out.println("tie");
			else
				System.out.println(map.get(winner));
			
			if(tc!=0)
				System.out.println();
		}
	}
}
