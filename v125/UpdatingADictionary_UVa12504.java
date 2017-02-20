package v125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UpdatingADictionary_UVa12504 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			String dic1 = br.readLine();
			dic1 = dic1.substring(1,dic1.length()-1);
			
			String dic2 = br.readLine();
			dic2 = dic2.substring(1,dic2.length()-1);
			
			StringTokenizer st = new StringTokenizer(dic1,",");
			TreeMap<String,String> map = new TreeMap<String, String>();
			while(st.hasMoreTokens())
			{
				StringTokenizer x = new StringTokenizer(st.nextToken(),":");
				map.put(x.nextToken(), x.nextToken());
			}
			
			ArrayList<String> added = new ArrayList<String>();
			ArrayList<String> removed = new ArrayList<String>();
			ArrayList<String> edited = new ArrayList<String>();
			
			st = new StringTokenizer(dic2,",");
			while(st.hasMoreTokens())
			{
				StringTokenizer x = new StringTokenizer(st.nextToken(),":");
				String key = x.nextToken();
				String val = x.nextToken();
				if(!map.containsKey(key))
					added.add(key);
				else
				{
					if(!map.get(key).equals(val))
						edited.add(key);
					map.remove(key);
				}
			}
			
			for(Entry<String, String> entry : map.entrySet()) 
		      removed.add(entry.getKey());
		    
			if(added.size()==0 && removed.size()==0 && edited.size()==0)
				sb.append("No changes\n");
			else
			{
				Collections.sort(added);Collections.sort(removed);Collections.sort(edited);
				for(int i = 0; i < added.size(); i++)
					sb.append(i==0?"+":",").append(added.get(i)).append(i!=added.size()-1?"":"\n");
				for(int i = 0; i < removed.size(); i++)
					sb.append(i==0?"-":",").append(removed.get(i)).append(i!=removed.size()-1?"":"\n");
				for(int i = 0; i < edited.size(); i++)
					sb.append(i==0?"*":",").append(edited.get(i)).append(i!=edited.size()-1?"":"\n");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
