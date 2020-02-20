package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.FtpUtils.FtpUtil;

public class FtpTedt {
	
	@Test
	public void testFtpClient() throws Exception{
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接。默认接口21
		ftpClient.connect("192.168.204.131");
		//用户名密码
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("E:\\programtry\\picture\\Sketchpad.png"));
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//默认格式是文本，故修改文件格式为二进制
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//文件名和上传文档的inputsteam
		ftpClient.storeFile("123.jpg", inputStream);
		inputStream.close();
		
		ftpClient.logout();
	}
	
	@Test
	public void testFtpUtil() throws Exception{
		FileInputStream inputStream = new FileInputStream(new File("E:\\programtry\\picture\\Sketchpad.png"));
		FtpUtil.uploadFile("192.168.204.131", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "/2020/2/20", "hello.jpg", inputStream);
	}


}
