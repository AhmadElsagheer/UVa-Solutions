package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BankNotQuiteOCR_UVa433 {
	
	static int[] di = new int[]{1,2,2,1,0,0,1};
	static int[] dj = new int[]{0,1,2,2,2,1,1};
	
	static int findDigit(int x)
	{
		int ret = -1;
		switch(x)
		{
		case 63: 	ret = 0;break;
		case 6:		ret = 1;break;
		case 91:	ret = 2;break;
		case 79:	ret = 3;break;
		case 102:	ret = 4;break;
		case 109:	ret = 5;break;
		case 125:	ret = 6;break;
		case 7:		ret = 7;break;
		case 127:	ret = 8;break;
		case 111:	ret = 9;
		}
		return ret;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[][] in = new char[3][];
			for(int i = 0; i < 3; ++i)
				in[i] = sc.nextLine().toCharArray();
			int[] segments = new int[9];
			for(int k = 0; k < 9; ++k)
			{
				//read the segments
				for(int c = 0; c < 7; ++c)
				{
					int i = k * 3 + di[c];
					int j = dj[c];
					if(i < in[j].length && in[j][i] != ' ')
						segments[k] |= 1<<c;
				}
			}
			int sum = 0;
			for(int k = 0; k < 9 && sum != -1; ++k)
			{
				int y = findDigit(segments[k]);
				if(y == -1)
					sum = -1;
				else
					sum += y * (k + 2);
			}
			if(sum != -1 && sum%11 == 0)
			{
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < 9; ++i)
					sb.append(findDigit(segments[i]));
				out.println(sb);
				continue;
			}
			
			int sol = 0, idx = -1, val = -1;
			for(int i = 0; i < 9 && sol != -1; ++i)
				for(int j = 0; j < 128; ++j)
				{
					int x = j | segments[i];
					if((x ^ j) != 0)
						continue;
					int d = findDigit(j);
					if(d == -1)
						continue;
					int ans = 0;
					for(int k = 0; k < 9 && ans != -1; ++k)
						if(k == i)
							ans += d * (k + 2);
						else
						{
							int y = findDigit(segments[k]);
							if(y == -1)
								ans = -1;
							else
								ans += y * (k + 2);
						}
					if(ans == -1 || ans%11 != 0)
						continue;
					if(sol == 0)
					{
						sol = 1;
						idx = i;
						val = j;
					}
					else
						sol = -1;
				}
			if(sol == -1)
				out.print("ambiguous\n");
			else
				if(sol == 0)
					out.print("failure\n");
				else
				{
					segments[idx] = val;
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < 9; ++i)
						sb.append(findDigit(segments[i]));
					out.println(sb);
				}
		}
		out.flush();
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
