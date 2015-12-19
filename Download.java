import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Download{
	public static ObjectInputStream ois;
	public static ServerSocket receive_socket;
	private static final int client_port=9001;
	
	private static class DownHandler extends Thread{
		private Socket receive;
		public DownHandler(Socket receive){
			this.receive = receive;
		}
		@Override
		public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				ois = new ObjectInputStream(receive.getInputStream());
				int receive_client_id=ois.readInt();
				String sentence=ois.readUTF();
				System.out.println("Client "+receive_client_id+": ");
				System.out.println(sentence);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally{
//			try {
//				ois.close();
//				receive_socket.close();
//				receive.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
	public static void main(String[] args){
		try {
			receive_socket = new ServerSocket(client_port);
			while(true){
				new DownHandler(receive_socket.accept()).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
