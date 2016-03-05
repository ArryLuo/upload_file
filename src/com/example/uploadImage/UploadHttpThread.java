package com.example.uploadImage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.InputSource;

public class UploadHttpThread extends Thread {
	private String url;
	private String fileName;
	public UploadHttpThread(String url, String fileName) {
		super();
		this.url = url;
		this.fileName = fileName;
	}
	@Override
public void run() {
	// TODO Auto-generated method stub
	super.run();
	//�����ʱ��
	String boundary="----WebKitFormBoundaryx3EmesUoZkYkbjzJ";
	//�õ���ʱ��
	String perfix="--";
	String end="\r\n";//���з�,����Ҫ����
	
	try {
		URL img_path=new URL(url);
		HttpURLConnection con=(HttpURLConnection) img_path.openConnection();
		con.setReadTimeout(5000);
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);
		DataOutputStream dos=new DataOutputStream(con.getOutputStream());
		//�������д����
		dos.writeBytes(perfix+boundary+end);
		//��Ӧ��������file�����֣��Լ��ļ�������
		dos.writeBytes("Content-Disposition: form-data;"+ "name=\"file\"; filename=\""+"sc.png"+"\""+end);
		dos.writeBytes(end);
		//�������дʵ������
		//·��ͼƬ
		FileInputStream fis=new FileInputStream(new File(fileName));
		byte[]bt=new byte[4*1024];
		int len;
		while((len=fis.read(bt))!=-1){
			dos.write(bt, 0, len);
		}
		dos.writeBytes(end);
		//��β��������
		dos.writeBytes(perfix+boundary+perfix+end);
		dos.flush();
		//��ȡ�����������Ƿ��ص���Ϣ
		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder sb=new StringBuilder();
		String str;
		while((str=br.readLine())!=null){
			sb.append(str);
		}
		System.out.println("���������صĽ��"+sb.toString());
		if(dos!=null){
			dos.close();
		}if(br!=null){
			br.close();
		}
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
