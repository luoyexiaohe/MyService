package ServicePac;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceFirst {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	    // ����ָ���Ķ˿�
	    int port = 8080;
	    ServerSocket server = new ServerSocket(port);
	    
	    // server��һֱ�ȴ����ӵĵ���
	    System.out.println("server��һֱ�ȴ����ӵĵ���");
	    Socket socket = server.accept();
	    // ���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
	    InputStream inputStream = socket.getInputStream();

	    OutputStream op = socket.getOutputStream();
	    byte[] bytes = new byte[1024];
	    int len;
	    StringBuilder sb = new StringBuilder();
	    String[] arrStr=null;
	    while ((len = inputStream.read(bytes)) != -1) {
	      //ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ������ʹ��UTF-8
	    	String str = new String(bytes, 0, len,"UTF-8");
	    	arrStr = str.split(System.lineSeparator());
	      sb.append(str);
	      break;
	    }
//	    for(byte b:bytes) {
//	    	System.out.print(b);
//	    }
	    if(arrStr!=null) {
	    	for(int i=0;arrStr.length>0 && i<arrStr.length;i++) {
	    		System.out.println(arrStr[i]);
	    	}
	    }
//	    System.out.println("get message from client: " + sb);
	    op.write("HTTP/1.1 200 OK".getBytes("utf-8"));
	    op.write(System.lineSeparator().getBytes("utf-8"));
	    op.write("Content-Charset:GBK".getBytes("utf-8"));
	    op.write(System.lineSeparator().getBytes("utf-8"));
	    op.write(System.lineSeparator().getBytes("utf-8"));
	    op.write("�����Ѿ��յ�".getBytes("GBK"));
	    op.write(System.lineSeparator().getBytes("utf-8"));
	    op.flush();
	    inputStream.close();
	    op.close();
	    socket.close();
	    server.close();
	}

}
