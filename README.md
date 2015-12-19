Topic: Use Socket programming to realize multiple clients online chat based on Server/Client model.

About Code:
--Server--:
  1. How to run:
Compile Server.java file first by inputing 'javac *.java' in terminal
Then, run Server file by inputing 'java Server' in terminal

  2. Functions of Server:
    2.1 Form the network: Everytime client will send their own client-id and port number to listen to. Server will collect these information if this client is the first to connect with Server.

    2.2 Receive message to send from Client: By using multiple kinds of input stream, the Server will receive three parameters here. First is the client-id of the sending client. Second is the client-id of the receiveing client. And the last one is the message. The first two is read by socket's readInt() method. And the last one is read by socket's readUTF() method.

    2.3 Sending message to destination: Server will search for the port number from LinkedList, which store the information of the network at the first step. And send out three parameters received at 2.2 orderly by using ObjectOutputStream.

  3. Server is working in multiple threads: Server is listening at port 8000. By using .accept() method, everytime when one client is connecting to Server, Server will create a new socket to receive input stream and also create a new thread to handle this socket. Thus, Server can support multiple clients at the same time.

--Client--:
  1.How to run:
Open two terminal windows
Compile all java files by inputing 'javac *.java' in terminal
Then run Upload.java and Download.java sperately

  2. Functions of Client
    2.1 Upload Side: Send out it own information which including id and port to listen to. Then chat with others by inputing their id and message. Using multiple kinds of output stream to write infomation in socket.

    2.2 Download Side: Using multiple thread here for the reason that may many user will send to the client simultaneously. Client will listen to the port number, by using .accept() to receive the socket send to client.(This part is same as Server).

Tips:
  1. If you run this code in eclipse, please add package declaration. If you run this code in terminal just do not delare the package.

  2. Finally we realize that the client can chat to a certain client instead of broadcasting.
