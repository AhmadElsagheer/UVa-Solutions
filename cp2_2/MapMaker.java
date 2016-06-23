package cp2_2;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
	
public class MapMaker{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); 
		int r = Integer.parseInt(st.nextToken());
		ArrayList<ArrayR> x = new ArrayList<ArrayR>(n);
		for(int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine());
			ArrayR tmp = new ArrayR(st);
			tmp.enterPairs(st);tmp.calculateCs();
			x.add(tmp);
		}
		
		for(int i = 0; i < r; i++)
		{
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			ArrayR curArray = searchForArray(x,name);
			StringBuilder indices = new StringBuilder(name+"[");
			int address = curArray.calculateAddress(st,indices);
			sb.append(indices).append(address+"\n");
		}
		System.out.print(sb);
	}
	
	
	
private static class ArrayR{
	String name;
	int base, d;
	int[]cd, ld, ud;
	
	public ArrayR(StringTokenizer st)
	{
		this.name = st.nextToken();
		this.base = Integer.parseInt(st.nextToken());
		this.cd = new int[d+1];
		this.cd[d] = Integer.parseInt(st.nextToken());
		this.d = Integer.parseInt(st.nextToken());
		ld = new int[d];
		ud = new int[d];
	}
	
	public void enterPairs(StringTokenizer st)
	{
		for(int i = 0; i < d; i++)
		{
			ld[i] = Integer.parseInt(st.nextToken());
			ud[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public void calculateCs()
	{
		for(int i = d-1; i > 0; i--)
			cd[i] = cd[i+1]*(ud[i]-ld[i]+1);
		cd[0] = base;
		for(int i = 0; i < d; i++)
			cd[0] -= cd[i+1]*ld[i];
	}
	
	public int calculateAddress(StringTokenizer st, StringBuilder indices)
	{
		int address = cd[0];
		for(int i = 1; i < d+1; i++)
		{
			String x = st.nextToken();
			if(i==d)
				indices.append(x+"] = ");
			else
				indices.append(x+", ");
			address += cd[i]*(Integer.parseInt(x));
		}
		return address;
	}
	
	
	
	
}
    public static ArrayR searchForArray(ArrayList<ArrayR> x, String name)
    {
    	int i = 0;
    	while(true)
    	{
    		ArrayR tmp = x.get(i);
    		if(tmp.name.equals(name))
    			return tmp;
    		i++;
    	}
    }

	
}

