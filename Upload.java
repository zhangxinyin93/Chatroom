import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Upload implements Runnable{
	private static final int client_id=1;
	private static final int client_port=9001;
	//ServerSocket listen;
	//Socket send_socket;
	ObjectOutputStream oos;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
			Socket send_socket = new Socket("localhost",8000);
			oos = new ObjectOutputStream(send_socket.getOutputStream());
			oos.writeInt(client_id);
			oos.flush();
			oos.writeInt(client_port);
			oos.flush();
			System.out.println("Connected to Server, you can chat now");
				//read client id client wants to chat by input
//				BufferedReader read_id = new BufferedReader(new InputStreamReader(System.in));
//				System.out.println("----Please input the client id you want to chat with----");
//				String id=read_id.readLine();
//				int chat_id=Integer.parseInt(id);
			
				//read sentence client wants to send by input
				BufferedReader read_sentence = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("----Please input the sentence you want to send----");
				String message=read_sentence.readLine();
			
				//send id & sentence to Server
				oos.writeInt(client_id);
				oos.flush();
//				oos.writeInt(chat_id);
//				oos.flush();
				oos.writeUTF(message);
				oos.flush();
				System.out.println("----Message has been sent out successfully----");
				Thread.currentThread().sleep(2000);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally{
//			try {
//				oos.close();
//				send_socket.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Upload upload = new Upload();
		Thread uploadthread = new Thread(upload);
		uploadthread.start();
	}
	
}
