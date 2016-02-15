package cp2_3;

import java.util.*;
import java.io.*;

public class FileFragmentation_UVa10132 {
	
	static BitSet[] frags = new BitSet[150];
	static int[] length = new int[150];
	static int N, Len;
	static BitSet trial;
	public static boolean tryPattern(int i)
	{
		int L = length[0] + length[i];
		if(L<Len)
			return false;
		int countRight = 0, countLeft = 0;
		boolean[] matched = new boolean[N];
		for(int j = 0; j < N; j++)
		{
			if(trial.get(0, length[j]).equals(frags[j]))
			{
				matched[j] = true;
				countLeft++;
			}
			if(trial.get(L-length[j], L).equals(frags[j]))
			{
				matched[j] = true;
				countRight++;
			}
		}
		if(countLeft>=N/2 && countRight>= N/2 && allMatch(matched))
			return true;
		return false;
	}
	
	public static boolean allMatch(boolean[] x)
	{
		for(int i = 0; i < x.length; i++)
			if(!x[i])
				return false;
		return true;
	}
	
	public static BitSet getSet(BitSet A, BitSet B, int a, int b)
	{
		BitSet C = new BitSet(a+b);
		for(int i = 0; i < a; i++)
			C.set(i, A.get(i));
		for(int i = 0; i < b; i++)
			C.set(i+a, B.get(i));
		return C;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			String line;N = 0;int min = 100000; int max = 0;
			while(br.ready() && !(line=br.readLine()).equals(""))
			{
				length[N] = line.length();
				min = Math.min(length[N], min);
				max = Math.max(length[N], max);
				BitSet cur = new BitSet(length[N]);
				for(int i = 0; i < length[N]; i++)
					cur.set(i,line.charAt(i) - '0'==1);
				frags[N++] = cur;
			}
			Len = min + max;
			for(int i = 1; i < N; i++)
			{
				trial = getSet(frags[0],frags[i],length[0],length[i]);
				if(tryPattern(i))
					break;
				trial = getSet(frags[i],frags[0],length[i],length[0]);
				if(tryPattern(i))
					break;
			}
			for(int i = 0; i < Len; i++)
				out.print(trial.get(i)?1:0);
			out.println();
			if(TC!=0)
				out.println();
		}
		out.flush();
		out.close();
	}
}
