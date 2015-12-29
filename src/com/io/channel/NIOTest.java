package com.io.channel;


/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-29 下午4:16:50
 */
public class NIOTest {

	/**
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-29 下午4:16:50
	 */
	public static void main(String[] args) {
		int port = 8899;
		NIOService server = new NIOService(port);
		server.listen();
		NIOClient client = new NIOClient(port);
		client.sendRequest();
	}

}
