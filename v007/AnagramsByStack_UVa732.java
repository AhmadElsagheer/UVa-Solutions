package v007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class AnagramsByStack_UVa732 {

	static Stack<Character> stack;
	static String source, target;
	static int length;
	static ArrayList<Character> sequence;
	static StringBuilder sb = new StringBuilder();
	
	static void backtrack(int i, int j, int k)
	{
		if(j==length)
		{
			for(int idx = 0; idx < k; idx++)
				sb.append(sequence.get(idx)).append(idx==k-1?"\n":" ");
			return;
		}
		if(i<length)
		{
			stack.push(source.charAt(i));
			if(k>=sequence.size())
				sequence.add('i');
			else
				sequence.set(k,'i');
			backtrack(i+1,j,k+1);
			stack.pop();
		}
		if(!stack.isEmpty() && stack.peek() == target.charAt(j))
		{
			char tmp = stack.pop();
			if(k>=sequence.size())
				sequence.add('o');
			else
				sequence.set(k,'o');
			backtrack(i,j+1,k+1);
			stack.push(tmp);
		}
		
	}
	
	static boolean anagrams(String x, String y)
	{
		int[] xx = new int[129];
		int[] yy = new int[129];
		for(int i = 0; i < x.length(); i++)
			xx[x.charAt(i)]++;
		for(int i = 0; i < y.length(); i++)
			yy[y.charAt(i)]++;
		for(int i = 0; i < 129; i++)
			if(xx[i]!=yy[i])
				return false;
		return true;
			
	}
	 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sequence = new ArrayList<Character>();
		
		while(br.ready())
		{
			source = br.readLine();
			target = br.readLine();
			sb.append("[\n");
			if(anagrams(source,target))
			{
				length = source.length();
				stack = new Stack<Character>();
				backtrack(0,0,0);
			}
			sb.append("]\n");
		}
		
		System.out.print(sb);
	}
}
