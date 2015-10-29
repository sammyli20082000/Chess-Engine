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
                ui.updateStatus("new game");
                break;
            case UIHandler.EVENT_LOAD_GAME:
                ui.updateStatus("load game");
                break;
            case UIHandler.EVENT_SAVE_GAME:
                ui.updateStatus("save game");
                break;
            case UIHandler.EVENT_STEP_UNDO:
                ui.updateStatus("step undo");
                break;
            case UIHandler.EVENT_STEP_REDO:
                ui.updateStatus("step redo");
                break;
            case UIHandler.EVENT_GAME_DISTRIBUTE_COMPUTING:
                ui.updateStatus("distribute computing");
                break;
            case UIHandler.EVENT_VIEW_DETAIL_SYSTEM_INFO:
                ui.updateStatus("detail system information");
                break;
            case UIHandler.EVENT_VIEW_AI_THINKING_STEP:
                ui.updateStatus("AI thinking step");
                break;
            case UIHandler.EVENT_VIEW_GAME_TREE:
                ui.updateStatus("game tree");
                break;
            case UIHandler.EVENT_HELP_ABOUT:
                ui.updateStatus("about");
                break;
            case UIHandler.EVENT_HELP_TUTORIAL:
                ui.updateStatus("tutorial");
                break;
        }
    }

    private void programTerminate(){
        System.exit(0);
    }
}
