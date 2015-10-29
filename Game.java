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
            public void onUICallBack(int msg) {
                handleUIEvent(msg);
            }
        });
    }

    public static void main(String[] args) {
        new Game();
    }

    private void handleUIEvent(int msg) {
        switch (msg){
            case UIHandler.EVENT_PIECE_SELECTED:
                break;
            case UIHandler.EVENT_WINDOW_CLOSING:
                programTerminate();
                break;
            case UIHandler.EVENT_NEW_GAME:
                ui.updateStatus("New game");
                break;
            case UIHandler.EVENT_LOAD_GAME:
                ui.updateStatus("Load game");
                break;
            case UIHandler.EVENT_SAVE_GAME:
                ui.updateStatus("Save game");
                break;
            case UIHandler.EVENT_STEP_UNDO:
                ui.updateStatus("Step undo");
                break;
            case UIHandler.EVENT_STEP_REDO:
                ui.updateStatus("Step redo");
                break;
            case UIHandler.EVENT_GAME_DISTRIBUTE_COMPUTING:
                ui.updateStatus("Distributed computing");
                break;
            case UIHandler.EVENT_VIEW_DETAIL_SYSTEM_INFO:
                ui.updateStatus("Detailed system information");
                break;
            case UIHandler.EVENT_VIEW_AI_THINKING_STEP:
                ui.updateStatus("AI thinking step");
                break;
            case UIHandler.EVENT_VIEW_GAME_TREE:
                ui.updateStatus("Game tree");
                break;
            case UIHandler.EVENT_HELP_ABOUT:
                ui.updateStatus("About");
                break;
            case UIHandler.EVENT_HELP_TUTORIAL:
                ui.updateStatus("Tutorial");
                break;
        }
    }

    private void programTerminate(){
        System.exit(0);
    }
}
