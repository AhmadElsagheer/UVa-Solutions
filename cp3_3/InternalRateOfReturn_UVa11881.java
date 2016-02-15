package cp3_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class InternalRateOfReturn_UVa11881 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int T = Integer.parseInt(br.readLine());
			if(T==0)
				break;
			int[] CF = new int[T+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i <= T; i++)
				CF[i] = Integer.parseInt(st.nextToken());
			double IRP = -100;
			long low = - 9999999999L, high = 100000000000000L;
			long sol = -500;
			for(int i = 0; i <80000; i++)
			{
				long mid =  (low+high)/2;
				
				double x = mid/10000000000.0;
				double trial = 0;
				for(int k = 1; k <= T; k++)
					trial += CF[k]*Math.pow(10000000000L,k) / Math.pow(10000000000L+mid, k);
				
				if(Math.abs(trial+CF[0]) <= 1e-5)
				{
					IRP = x;
					sol = mid;
					break;
				}
				if(trial < -CF[0])
					high = mid;
				
				else
					low = mid;
				
			}
			if(sol==-500)
				sb.append("No\n");
			else
				sb.append(new DecimalFormat("0.00").format(Math.round(IRP*100)/100.0)).append("\n");
		
			
				
			
		}
		
		System.out.print(sb);
	}
}
