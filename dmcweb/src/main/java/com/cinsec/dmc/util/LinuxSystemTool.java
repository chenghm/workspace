package com.cinsec.dmc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;

public class LinuxSystemTool {

	/**
	 * get memory by used info
	 * 
	 * @return int[] result
	 *         result.length==4;int[0]=MemTotal;int[1]=MemFree;int[2
	 *         ]=SwapTotal;int[3]=SwapFree;
	 * @throws java.io.IOException
	 * @throws InterruptedException
	 */
	public static int[] getMemInfo() throws IOException, InterruptedException {
		File file = new File("/proc/meminfo");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		int[] result = new int[4];
		String str = null;
		StringTokenizer token = null;
		while ((str = br.readLine()) != null) {
			token = new StringTokenizer(str);
			if (!token.hasMoreTokens())
				continue;

			str = token.nextToken();
			if (!token.hasMoreTokens())
				continue;

			if (str.equalsIgnoreCase("MemTotal:"))
				result[0] = Integer.parseInt(token.nextToken());
			else if (str.equalsIgnoreCase("MemFree:"))
				result[1] = Integer.parseInt(token.nextToken());
			else if (str.equalsIgnoreCase("SwapTotal:"))
				result[2] = Integer.parseInt(token.nextToken());
			else if (str.equalsIgnoreCase("SwapFree:"))
				result[3] = Integer.parseInt(token.nextToken());
		}

		return result;
	}

	/**
	 * get memory by used info
	 * 
	 * @return float efficiency
	 * @throws java.io.IOException
	 * @throws InterruptedException
	 */
	public static float getCpuInfo() throws IOException, InterruptedException {
		File file = new File("/proc/stat");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		StringTokenizer token = new StringTokenizer(br.readLine());
		token.nextToken();
		int user1 = Integer.parseInt(token.nextToken());
		int nice1 = Integer.parseInt(token.nextToken());
		int sys1 = Integer.parseInt(token.nextToken());
		int idle1 = Integer.parseInt(token.nextToken());

		Thread.sleep(1000);

		br = new BufferedReader(
				new InputStreamReader(new FileInputStream(file)));
		token = new StringTokenizer(br.readLine());
		token.nextToken();
		int user2 = Integer.parseInt(token.nextToken());
		int nice2 = Integer.parseInt(token.nextToken());
		int sys2 = Integer.parseInt(token.nextToken());
		int idle2 = Integer.parseInt(token.nextToken());

		return (float) ((user2 + sys2 + nice2) - (user1 + sys1 + nice1))
				/ (float) ((user2 + nice2 + sys2 + idle2) - (user1 + nice1
						+ sys1 + idle1));
	}
	
	public static String getRuntime() {
		try {
			String command = "date -d \"$(awk -F. '{print $1}' /proc/uptime) second ago\" +\"%Y-%m-%d %H:%M:%S\"\"";
			Process process = Runtime.getRuntime().exec(
					command);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static float getDisk() {
		float ioUsage = 0.0f;
		Process pro = null;
		Runtime r = Runtime.getRuntime();
		try {
			String command = "iostat -d -x";
			pro = r.exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line = null;
			int count =  0;
			while((line=in.readLine()) != null){		
				if(++count >= 4){
//					log.info(line);
					String[] temp = line.split("\\s+");
					if(temp.length > 1){
						float util =  Float.parseFloat(temp[temp.length-1]);
						ioUsage = (ioUsage>util)?ioUsage:util;
					}
				}
			}
			if(ioUsage > 0){
				ioUsage /= 100; 
			}			
			in.close();
			pro.destroy();
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
		}	
		return ioUsage;
	}
}
