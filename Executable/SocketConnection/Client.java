package Executable.SocketConnection;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Executable.Game;
import Executable.ObjectModel.Move;

public class Client {
	public static final String IP_ADDR = "localhost";
	public static final int PORT = 12345;

	ObjectInputStream ois;
	ObjectOutputStream oos;

	Socket socket;
	
	Game game;

	public Client(Game game) {
		this.game = game;
		try {
			socket = new Socket(IP_ADDR, PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, "Get connected to server!");
				
				while (true) {
					Move move = null;
					try {
						ois = new ObjectInputStream(socket.getInputStream());
						move = (Move) ois.readObject();
						game.computerMakeMove(move);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(move.toPoint);
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
			e.printStackTrace();
		}
	}
}
