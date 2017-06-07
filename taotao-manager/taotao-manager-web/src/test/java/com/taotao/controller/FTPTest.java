package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {

	@Test 
	public void testFtpClient() throws Exception {
		// 创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建ftp连接,默认是21端口
		ftpClient.connect("192.168.244.128", 21);
		// 使用用户名和密码，登陆ftp服务器
		ftpClient.login("ftpuser", "123456");
		// 上传文件
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(
				new File("C:\\Users\\asus\\Desktop\\素材\\4.jpg"));
		// 设置上传路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		// 修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一个参数：服务器端文件名 第二个参数：上传文件的inputStream
		ftpClient.storeFile("memory.jpg", inputStream);

		// 关闭连接
		ftpClient.logout();
	}

	@Test
	public void testFtpUtil() throws Exception {
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(
				new File("C:\\Users\\asus\\Desktop\\素材\\u=1455008960,3827276796&fm=23&gp=0.jpg"));

		FtpUtil.uploadFile("192.168.244.128", 21, "ftpuser", "123456", "/home/ftpuser/www/images", "2017/04/07",
				"hello.jpg", inputStream);
	}
}
