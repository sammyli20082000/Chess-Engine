
import Board.Board;
import UI_handler.UIHandler;

public class Game {
	Board board;
	AI ai;
	Node tree_root;
	UIHandler ui;
	
	public Game() {
		ai = new AI(this);
		ui = new UIHandler(new UIHandler.eventCallBack() {
			public void onUICallBack(int msg) {handleUIEvent(msg);}});
	}

	public static void main(String[] args) {
		new Game();
	}
	
	private void handleUIEvent(int msg){
		if(msg == UIHandler.EVENT_PIECE_SELECTED)
			;
	}
}
