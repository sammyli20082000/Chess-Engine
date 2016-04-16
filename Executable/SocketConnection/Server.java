package Executable.SocketConnection;

import java.awt.SecondaryLoop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.RoundEnvironment;
import javax.swing.JOptionPane;

import Executable.Game;
import Executable.ObjectModel.Move;

public class Server {
	public static final int PORT = 12345;

	ServerSocket serverSocket;
	Socket client;
	ArrayList<HandlerThread> clients;
	Game game;

	public Server(Game game) {
		clients = new ArrayList<>();
		this.game = game;

		this.init();
	}

	public void init() {
		try {
			serverSocket = new ServerSocket(PORT);
			new Thread() {
				@Override
				public void run() {
					try {
						client = serverSocket.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					clients.add(new HandlerThread(client));
					JOptionPane.showMessageDialog(null, "Client comes!");
				}
			}.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMove(Move move) {
		for (HandlerThread hThread : clients)
			hThread.sendMove(move);
	}

	private class HandlerThread {
		private Socket socket;
		ObjectInputStream ois;
		ObjectOutputStream oos;

		public HandlerThread(Socket client) {
			socket = client;
			new Thread() {
				@Override
				public void run() {
					while (true) {
						try {
						ois = new ObjectInputStream(socket.getInputStream());
						Move move = (Move) ois.readObject();

						game.computerMakeMove(move);
						} catch (Exception e) {
							try {
								ois.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}.start();
		}

		public void sendMove(Move move) {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(move);

				oos.flush();
			} catch (IOException e) {
				try {
					oos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
