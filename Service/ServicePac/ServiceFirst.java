package ServicePac;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceFirst {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	    // 监听指定的端口
	    int port = 8080;
	    ServerSocket server = new ServerSocket(port);
	    
	    // server将一直等待连接的到来
	    System.out.println("server将一直等待连接的到来");
	    Socket socket = server.accept();
	    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
	    InputStream inputStream = socket.getInputStream();

	    OutputStream op = socket.getOutputStream();
	    byte[] bytes = new byte[1024];
	    int len;
	    StringBuilder sb = new StringBuilder();
	    String[] arrStr=null;
	    while ((len = inputStream.read(bytes)) != -1) {
	      //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
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
	    op.write("数据已经收到".getBytes("GBK"));
	    op.write(System.lineSeparator().getBytes("utf-8"));
	    op.flush();
	    inputStream.close();
	    op.close();
	    socket.close();
	    server.close();
	}

}
