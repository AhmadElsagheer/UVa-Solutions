package cp6_4;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PhoneList_UVa11362 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t-->0)
		{
			int n = Integer.parseInt(br.readLine());
			String[] num = new String[n];
			for(int i = 0; i < n; i++)
				num[i] = br.readLine();
			
			Arrays.sort(num);
			boolean yes = true;
			for(int i = 0; i < n - 1 && yes; i++)
				if(num[i].length() <= num[i+1].length() && num[i].equals(num[i+1].substring(0, num[i].length())))
					yes = false;
			sb.append(yes?"YES\n":"NO\n");
				
		}
		System.out.print(sb);
	}

}
