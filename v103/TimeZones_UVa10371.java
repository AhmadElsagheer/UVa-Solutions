package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TimeZones_UVa10371 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		TreeMap<String, Integer> map = new TreeMap<String,Integer>();
		map.put("UTC", 0);			map.put("GMT", 0);			map.put("BST", 60);
		map.put("IST", 60);			map.put("WET", 0);			map.put("WEST", 60);
		map.put("CET", 60);			map.put("CEST", 120);		map.put("EET", 120);
		map.put("EEST", 180);		map.put("MSK", 180);		map.put("MSD", 240);
		map.put("AST", -240);		map.put("ADT", -180);		map.put("NST", -210);
		map.put("NDT", -150);		map.put("EST", -300);		map.put("EDT", -240);
		map.put("CST", -360);		map.put("CDT", -300);		map.put("MST", -420);
		map.put("MDT", -360);		map.put("PST", -480);		map.put("PDT", -420);
		map.put("HST", -600);		map.put("AKST", -540);		map.put("AKDT", -480);
		map.put("AEST", 600);		map.put("AEDT", 660);		map.put("ACST", 570);
		map.put("ACDT", 630);		map.put("AWST", 480);

		int N = Integer.parseInt(br.readLine());
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int mm, hh;
			String t = st.nextToken();
			if(t.equals("midnight"))
				mm = 0;
			else
				if(t.equals("noon"))
					mm = 60 * 12;
				else
				{
					StringTokenizer x = new StringTokenizer(t,":");
					hh = Integer.parseInt(x.nextToken());
					mm = Integer.parseInt(x.nextToken());
					if(st.nextToken().charAt(0)=='p')
						mm += hh*60 + (hh==12?0:(60*12));
					else
						mm += (hh==12?0:hh)*60;
				}
			mm = (mm - map.get(st.nextToken()) + map.get(st.nextToken()) + 60 * 24)%(60*24);
			hh = mm /60;
			mm %= 60;
			if(hh==0 && mm==0)
				out.println("midnight");
			else
				if(hh==12 && mm==0)
					out.println("noon");
				else
					if(hh >= 12)
						out.printf("%d:%02d p.m.\n",hh==12?12:(hh-12),mm);
					else
						out.printf("%d:%02d a.m.\n",hh==0?12:hh,mm);
			
		}
		out.flush();
	}
}
