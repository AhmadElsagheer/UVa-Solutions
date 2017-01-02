package v003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MayaCalendar_UVa300 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		TreeMap<String, Integer> map = new TreeMap<String,Integer>();
		
		map.put("pop",0);
		map.put("no",1);
		map.put("zip",2);
		map.put("zotz",3);
		map.put("tzec",4);
		map.put("xul",5);
		map.put("yoxkin",6);
		map.put("mol",7);
		map.put("chen",8);
		map.put("yax",9);
		map.put("zac",10);
		map.put("ceh",11);
		map.put("mac",12);
		map.put("kankin",13);
		map.put("muan",14);
		map.put("pax",15);
		map.put("koyab",16);
		map.put("cumhu",17);
		map.put("uayet",18);
		
		String[] unmap = new String[]{"imix", "ik", "akbal", "kan", "chicchan", "cimi", "manik", "lamat", "muluk", "ok", "chuen", "eb", "ben", "ix", "mem", "cib", "caban", "eznab", "canac", "ahau"};
		int N = Integer.parseInt(br.readLine());
		sb.append(N+"\n");
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			String d = st.nextToken();
			int day = Integer.parseInt(d.substring(0,d.length()-1));
			int month = map.get(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			
			int total = year * 365 + month * 20 + day + 1;
			year = (total - 1) / 260;
			total %= 260;
			
			
			month = (total%20 + 19)%20;

			day = total%13;
			if(day==0)
				day = 13;
			
			sb.append(day+" "+unmap[month]+" "+year+"\n");
		}
		System.out.print(sb);
	}
}
