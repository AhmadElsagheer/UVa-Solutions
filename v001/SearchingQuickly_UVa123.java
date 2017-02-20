package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
public class SearchingQuickly_UVa123 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<String> ignore = new ArrayList<String>(50);
		
		//ignore words
		while(true)
		{
			String current = br.readLine();
			if(current.equals("::"))
				break;
			ignore.add(current);
		}
		
		LinkedList<String> keywords = new LinkedList<String>();
		LinkedList<String[]> titles = new LinkedList<String[]>();
		int[][] occurrences = new int[3000][200];
		
		int count = 0; int currentTitle = 0;
		while(br.ready())
		{
			
			String[] title = br.readLine().toLowerCase().split(" ");
			titles.add(title);
			
			for(int i = 0; i < title.length; i++)
			{
				String word = title[i];
				if(ignore.contains(word))
					continue;
				int index = keywords.indexOf(word);
				if(index==-1)
				{
					index = count++; keywords.add(word);
				}
				occurrences[index][currentTitle]++;
				
			}
			currentTitle++;
		}
		LinkedList<String> keywords_unsorted = (LinkedList<String>) keywords.clone();
		Collections.sort(keywords);
		
		for(int k = 0, size = keywords.size(); k < size; k++)
		{
			int index = keywords_unsorted.indexOf(keywords.get(k));
			for(int i = 0; i < 200; i++)
			{
				int j = 0;
				
				while(occurrences[index][i]!=0)
				{
					if(titles.get(i)[j].equals(keywords.get(k)))
					{
						String x = "";
						for(int z = 0; z < titles.get(i).length; z++)
							if(z==j)
								x += titles.get(i)[z].toUpperCase()+" ";
							else
								x += titles.get(i)[z]+" ";
						x = x.substring(0,x.length()-1);	
						sb.append(x+"\n");
						occurrences[index][i]--;	
					}
					
					j++;
				}
			}
		}
		System.out.print(sb);
	}
}
