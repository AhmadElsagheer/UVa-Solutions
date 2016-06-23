package cp2_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ChildrensGame_UVa10905 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			StringX[] numbers  = new StringX[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				numbers[i] = new StringX(st.nextToken());
			Arrays.sort(numbers);
			while(N-->0)
				out.print(numbers[N].num);
			out.println();
		}
		out.flush();
		out.close();
		
	}
}

class StringX implements Comparable<StringX> 
{
	String num;

	StringX (String x) {num = x;}
	public int compareTo(StringX o) {
	
		String x = this.num;
		String y = o.num;
		int i = 0, j = 0;
		int a = 0, b = 0;
		while(i < x.length() || j < y.length())
		{
			if(i==x.length()){i = a; b = j;}
				
			if(j==y.length()){j = b; a = i;}
			
			if(x.charAt(i)!=y.charAt(j))
				return x.charAt(i) - y.charAt(j);
			
			i++;j++;
		}
		return y.length() - x.length();
	}
	
}
