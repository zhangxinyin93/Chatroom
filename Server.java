import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server{
	public static LinkedList<Integer> client_id_list = new LinkedList<Integer>();//un-initialization
	public static LinkedList<Integer> client_port_list = new LinkedList<Integer>();
	private static final int sPort=8000;//Server port number
	private static String message;//The message from client to be sent
	private static ServerSocket serversocket;
	public static int client_num;
	
	public static void main(String[] agrs) throws Exception{
  	serversocket = new ServerSocket(sPort);
		System.out.println("Server is working at port "+sPort);
		while(true){
			new Handler(serversocket.accept()).start();
		}
	}
	
	private static class Handler extends Thread{
		private Socket connection;
		private Socket send;
		public ObjectOutputStream oos;
		public ObjectInputStream ois;
		public Handler(Socket connection) {
			// TODO Auto-generated constructor stub
			this.connection=connection;
		}
		public void run(){
			try {
				ois = new ObjectInputStream(connection.getInputStream());
				
				//get the first connection with client
				client_num=ois.readInt();
				if(!(client_id_list.contains(client_num))){
					int port_num=ois.readInt();
					client_id_list.add(client_num);
					client_port_list.add(port_num);
					
					System.out.println("Client "+ client_num+" is Connected to Server");
				}
				else {
					System.out.println("Client is already connected");
					int store=ois.readInt();
					}
				
				//Receive the Message from Client
				System.out.println("----Receiving the message to be sent from Client "+client_num+" ----");
				int client=ois.readInt();
				//int send_id=ois.readInt();//receive the client id to be talked to
				message=ois.readUTF();//sentence to be sent
				System.out.println("----Finish receiving----");
				//find the client to be sent
				System.out.println("----Sending----");
				for(int i:client_id_list){
					int index=client_id_list.indexOf((Object)i);
					int port_to_send=client_port_list.get(index);
					send = new Socket("localhost",port_to_send);
					oos = new ObjectOutputStream(send.getOutputStream());
					oos.writeInt(client);
					oos.flush();
					oos.writeUTF(message);
					oos.flush();
				}
				System.out.println("----Finish sending----");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
