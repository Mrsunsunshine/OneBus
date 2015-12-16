package com.onebus.others;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Avatar {

	/**
	 * 将制定路径的图片转化成string类型
	 */
	public String pathToImage(String path) {

		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串

	}

	/**
	 * 将imgstr中存放的图片保存到imgFilePath指定的路径
	 * @param imgStr
	 * @param imgFilePath
	 * @return
	 */
	public boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) 
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
