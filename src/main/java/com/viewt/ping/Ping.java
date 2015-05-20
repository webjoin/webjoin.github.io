package com.viewt.ping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class Ping {

	public static void main(String[] args) {
		String usage = "java Probe <address> [<port>]";
		String hostAddress = "";
		int port;
		long timeToRespond = 0; // in milliseconds

		if (args.length < 1 || args.length > 2) {
			System.out.println("usage: " + usage);
			return;
		}

		try {
			hostAddress = args[0]; // copy the string
			if (args.length == 2)
				port = Integer.parseInt(args[1]); // convert the integer
			else
				port = 80;

			if (args.length == 1)
				timeToRespond = ping(hostAddress);
			else
				timeToRespond = ping(hostAddress, port);
		} catch (NumberFormatException e) {
			System.out.println("Problem with arguments, usage: " + usage);
			e.printStackTrace();
		}

		if (timeToRespond >= 0)
			System.out.println(hostAddress + " responded in " + timeToRespond + " ms");
		else
			System.out.println("Failed");

	}

	/**
	 * Connect using layer3
	 * 
	 * @param hostAddress
	 * @return delay if the specified host responded, -1 if failed
	 */
	public static long ping(String hostAddress) {
		InetAddress inetAddress = null;
		Date start, stop;

		try {
			inetAddress = InetAddress.getByName(hostAddress);
		} catch (UnknownHostException e) {
			System.out.println("Problem, unknown host:");
			e.printStackTrace();
		}

		try {
			start = new Date();
			if (inetAddress.isReachable(5000)) {
				stop = new Date();
				return (stop.getTime() - start.getTime());
			}

		} catch (IOException e1) {
			System.out.println("Problem, a network error has occurred:");
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			System.out.println("Problem, timeout was invalid:");
			e1.printStackTrace();
		}
		return -1; // to indicate failure
	}

	/**
	 * Connect using layer4 (sockets)
	 * 
	 * @param
	 * @return delay if the specified host responded, -1 if failed
	 */
	static long ping(String hostAddress, int port) {
		InetAddress inetAddress = null;
		InetSocketAddress socketAddress = null;
		SocketChannel sc = null;
		long timeToRespond = -1;
		Date start, stop;

		try {
			inetAddress = InetAddress.getByName(hostAddress);
		} catch (UnknownHostException e) {
			System.out.println("Problem, unknown host:");
			e.printStackTrace();
		}

		try {
			socketAddress = new InetSocketAddress(inetAddress, port);
		} catch (IllegalArgumentException e) {
			System.out.println("Problem, port may be invalid:");
			e.printStackTrace();
		}

		// Open the channel, set it to non-blocking, initiate connect
		try {
			sc = SocketChannel.open();
			sc.configureBlocking(true);
			start = new Date();
			if (sc.connect(socketAddress)) {
				stop = new Date();
				timeToRespond = (stop.getTime() - start.getTime());
			}
		} catch (IOException e) {
			System.out.println("Problem, connection could not be made:");
			e.printStackTrace();
		}

		try {
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return timeToRespond;
	}

}

