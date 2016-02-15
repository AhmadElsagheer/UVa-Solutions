package cp2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class HardwoodSpecies_UVa10226 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		br.readLine();
		while(tc-->0)
		{
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			ArrayList<String> names = new ArrayList<String>(10000);
			int total = 0;
			String tree = br.readLine();
			while(tree != null && !tree.isEmpty())
			{
				total++;
				Integer count = map.get(tree);
				if(count == null)
				{
					names.add(tree);
					map.put(tree, 1);
				}
				else
					map.put(tree, count + 1);
				tree = br.readLine();
			}
			Collections.sort(names);
			for(int i = 0; i < names.size(); i++)
				sb.append(names.get(i)+" "+(new DecimalFormat("0.0000").format(map.get(names.get(i))*100.0/total))+"\n");
			if(tc!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
