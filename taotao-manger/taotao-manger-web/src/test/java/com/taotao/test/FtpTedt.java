package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpTedt {
	public static void main(String[] args) throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.204.131");
		ftpClient.login("ftpuser", "ftpuser");
		FileInputStream inputStream = new FileInputStream(new File("E:\\programtry\\picture\\Sketchpad.png"));
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("123.jpg", inputStream);
		inputStream.close();
		
		ftpClient.logout();
	}


}
