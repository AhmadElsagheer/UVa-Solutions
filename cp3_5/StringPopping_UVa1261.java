package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class StringPopping_UVa1261 {

	static TreeSet<String> checked;
	
	static boolean pop(StringBuilder x)
	{
		if(checked.contains(x.toString()))
			return false;
		checked.add(x.toString());
		if(x.length() == 0)
			return true;
		for(int i = 0; i < x.length(); i++)
		{
			char c = x.charAt(i);
			int j = i + 1;
			for(; j < x.length(); j++)
				if(x.charAt(j) != c)
					break;
			if(j - i < 2)
				continue;
			if(pop(new StringBuilder(x).delete(i, j)))
				return true;
			i = j - 1;
		}
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			checked = new TreeSet<String>();
			sb.append(pop(new StringBuilder(br.readLine()))?1:0).append("\n");
		}
		System.out.print(sb);
		
	}
	
}
