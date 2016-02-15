package cp1_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MirrorClock_UVa11650 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			
			String[] time = br.readLine().split("\\:");
			int h = Integer.parseInt(time[0]), m = Integer.parseInt(time[1]);
			
			h = (11 - h + 12)%12;
			if(m != 0) m = 60 - m;
			else h = (h+1)%12;
			if(h==0) h = 12;
			System.out.printf("%02d:%02d\n",h,m);
					
		}
		
	}
}
