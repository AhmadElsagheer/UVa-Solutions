package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class WordAmalgamation_UVa642 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
		TreeMap<Long,Integer> map = new TreeMap<Long,Integer>();
		int next = 0;
		while(true)
		{
			String word = br.readLine();
			if(word.equals("XXXXXX"))
				break;
			int[] arr = new int[26];
			for(int i = 0; i < word.length(); i++)
				arr[word.charAt(i)-'a']++;
			long val = 0, weight = 1;
			for(int i = 0; i < 26;i++)
				while(arr[i]!=0)
				{
					val += weight * i;
					weight *= 26;
					arr[i]--;
				}
			if(!map.containsKey(val))
			{
				words.add(new ArrayList<String>());
				map.put(val, next++);
			}
			int idx = map.get(val);
			words.get(idx).add(word);
		}
		for(int i = 0; i < words.size(); i++)
			Collections.sort(words.get(i));
		while(true)
		{
			String word = br.readLine();
			if(word.equals("XXXXXX"))
				break;
			int[] arr = new int[26];
			for(int i = 0; i < word.length(); i++)
				arr[word.charAt(i)-'a']++;
			long val = 0, weight = 1;
			for(int i = 0; i < 26;i++)
				while(arr[i]!=0)
				{
					val += weight * i;
					weight *= 26;
					arr[i]--;
				}
			if(map.containsKey(val))
			{
				int idx = map.get(val);
				for(int i = 0, size = words.get(idx).size(); i < size; i++)
					sb.append(words.get(idx).get(i)+"\n");
			}
			else
				sb.append("NOT A VALID WORD\n");
			sb.append("******\n");
		}
		System.out.print(sb);
	}
}
