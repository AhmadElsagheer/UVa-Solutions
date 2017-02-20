package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TheMadNumerologist_UVa10785 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		int[] value = new int[27];
		int k = 1;
		for(int i = 0; i < 26; i++)
		{
			value[i] = k;
			k = (k+1)%9;
			if(k==0)
				k = 9;
		}
		int[] vowels = new int[]{'A','U','E','O','I'};
		int[] vowel_array = new int[105];
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 21; j++)
				vowel_array[j+21*i] = vowels[i] - 'A';
		int[] cons_array = new int[105];
		k = 0;
		for(int val = 1; val < 10; val++)
			for(int i = 0; i < 26; i++)
				if(!isVowel(i,vowels) && value[i]==val)
					for(int j = 0; j < 5; j++)
						cons_array[k++] = i;
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(k = 1; k <= TC; k++)
		{
			sb.append("Case "+k+": ");
			int n = Integer.parseInt(br.readLine());
			int[] cons = new int[n/2];
			for(int i = 0; i < cons.length; i++)
				cons[i] = cons_array[i];
			int[] vowel = new int[(n+1)/2];
			for(int i = 0; i < vowel.length; i++)
				vowel[i] = vowel_array[i];
			Arrays.sort(cons);
			Arrays.sort(vowel);
			int i = 0, j = 0;
			for(int cur = 1; cur <= n; cur++)
				if(cur%2==0)
					sb.append((char)(cons[i++]+'A'));
				else
					sb.append((char)(vowel[j++]+'A'));
			sb.append("\n");
		}
			
		System.out.print(sb);
	}
	
	static boolean isVowel(int k, int[] vowels)
	{
		for(int i = 0; i < 5; i++)
			if(vowels[i] - 'A'==k)
				return true;
		return false;				
	}
	
}
