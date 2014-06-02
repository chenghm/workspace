package com.cinsec.dmc.ipparse;

public class IPtest {
	public  void testIp() {
		// 指定纯真数据库的文件名，所在文件夹
//		IPSeeker ip = new IPSeeker("QQWry.Dat", this.getClass().getResource("/qqwry").getPath());
		IPSeeker ip = new IPSeeker();
		// 测试IP 58.20.43.13
		System.out.println(ip.getIPLocation("127.0.0.1").getCountry() + ":"
				+ ip.getIPLocation("127.0.0.1").getArea());
	}

	public static void main(String[] args) {
		IPtest t = new IPtest();
		t.testIp();
	}

}
