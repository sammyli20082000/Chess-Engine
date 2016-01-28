package Executable.FileHandlerModel;

import Executable.UIHandlerModel.UIHandler.EventCallBackHandler;

public class FileHandler {
	
	EventCallBackHandler c;

	public FileHandler(EventCallBackHandler g) {
		c = g;
	}
	
	public interface EventCallBackHandler {
		public void newGame();
		
		public void saveGame();
		
		public void loadGame();
	}
}
