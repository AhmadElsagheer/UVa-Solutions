package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
class StringLen implements Comparable{
	
	String word;
	
	public StringLen(String word)
	{
		this.word = word;
	}

	@Override
	public int compareTo(Object p) {
		if(((StringLen)p).word.length() - this.word.length()!=0)
		return ((StringLen)p).word.length() - this.word.length();
		return this.word.compareTo(((StringLen)p).word);
	}
	
}
public class TheOneHandedTypist_UVa10393 {

	static StringBuilder possible;
	static String[] fingers = new String[]{"qaz","wsx","edc","rfvtgb"," "," ","yhnujm","ik","ol","p;/"};
	
	public static void generatePossible(int[] disabled)
	{
		possible = new StringBuilder();
		for(int i = 0; i < 10; i++)
			if(disabled[i]==0)
				possible.append(fingers[i]);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(br.ready())
		{
			StringTokenizer st;
			int F,N;
			int[] disabled;
			try{
				st = new StringTokenizer(br.readLine());
				F = Integer.parseInt(st.nextToken());
				N = Integer.parseInt(st.nextToken());
				disabled = new int[10];
				if(F!=0)
					st = new StringTokenizer(br.readLine());
				for(int i = 0; i < F; i++)
					disabled[Integer.parseInt(st.nextToken())-1] = 1;
				generatePossible(disabled);
			}
			catch(RuntimeException e)
			{
				return;
			}
			
			StringLen[] words = new StringLen[N];
			for(int i = 0; i < N; i++)
				words[i] = new StringLen(br.readLine());
			Arrays.sort(words);
			int max = -1;
			LinkedList<String> longestWords = new LinkedList<String>();
			for(int i = 0; i < N; i++)
			{
				String current = words[i].word;
				if(current.length()<max)
					break;
				if(longestWords.contains(current))
					continue;
				if(canType(current))
				{
					longestWords.add(current);
					max = current.length();
				}
			}
			
			sb.append(longestWords.size()+"\n");
			while(!longestWords.isEmpty())
				sb.append(longestWords.remove()+"\n");
			
			
		}
		
		
		System.out.print(sb);
	}
	
	public static boolean canType(String word)
	{
		for(int i = 0, size = word.length(); i < size; i++)
			if(possible.indexOf(""+word.charAt(i))<0)
				return false;
		return true;
	}
}
