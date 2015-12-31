package cp3_4;

import java.util.*;
import java.io.*;

public class PerfectChoir_UVa12485 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(br.ready())
		{
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] notes = new int[N];int sum = 0;
			for(int i = 0; i < N; i++)
			{
				notes[i] = Integer.parseInt(st.nextToken());
				sum += notes[i];
			}
			if(sum%N!=0){out.println(-1);continue;}
			int finalNote = sum/N;	
			
			int up = 0; int down = 0;
			for(int i = 0; i < N; i++)
				if(notes[i]==finalNote)
					continue;
				else
					if(notes[i]>finalNote)
						down += notes[i] - finalNote;
					else
						up += finalNote - notes[i];
			if(up==down)
				out.println(down+1);
			else
				out.println(-1);
		}
		out.flush();
		out.close();
	}
}
