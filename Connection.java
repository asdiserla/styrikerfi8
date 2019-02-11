package ch3Processes.connectionOrientedSocket;

import java.net.*;
import java.io.*;

class Connection {
  DataInputStream in;
  DataOutputStream out;
  Socket clientSocket;

  public Connection(Socket aClientSocket) {
    try {
      clientSocket = aClientSocket;
      in = new DataInputStream(clientSocket.getInputStream());
      out = new DataOutputStream(clientSocket.getOutputStream());
      handleRequest();
    } catch (IOException e) {
      System.out.println("Connection:" + e.getMessage());
    }
  }

  public long fibo(long i) {
    if (i <= 2) {
      return 1;
    }
    return fibo(i - 1) + fibo(i - 2);
  }

  public void handleRequest() {
    try { // an echo server
      String data = in.readUTF(); // read a line of data from the stream
      long tala = Long.parseLong(data);
      long gildi = fibo(tala);

      out.writeUTF(String.valueOf(gildi));
    } catch (EOFException e) {
      System.out.println("EOF: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("readline: " + e.getMessage());
    } finally {
      try {
        clientSocket.close();
      } catch (IOException e) {/* close failed */
      }
    }
  }
}
