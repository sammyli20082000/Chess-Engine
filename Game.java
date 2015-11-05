import java.io.File;

import Board.Board;
import UI_handler.UIHandler;

public class Game {
    Board board;
    AI ai;
    Node tree_root;
    UIHandler ui;
    String myLocation;

    public Game() {
        myLocation =
                (new File(Game.class.getClassLoader().getResource("").getPath()))
                        .getAbsolutePath();
        ai = new AI(this);
        ui = new UIHandler(new UIHandler.eventCallBack() {
            public void onUICallBack(UIHandler.Message msg) {
                handleUIEvent(msg);
            }
        });
    }

    public static void main(String[] args) {
        new Game();
    }

    private void handleUIEvent(UIHandler.Message msg) {
        switch (msg){
            case EVENT_PIECE_SELECTED:
                break;
            case EVENT_WINDOW_CLOSING:
                programTerminate();
                break;
            case EVENT_NEW_GAME:
                ui.updateStatus("New game");
                break;
            case EVENT_LOAD_GAME:
                ui.updateStatus("Load game");
                break;
            case EVENT_SAVE_GAME:
                ui.updateStatus("Save game");
                break;
            case EVENT_STEP_UNDO:
                ui.updateStatus("Step undo");
                break;
            case EVENT_STEP_REDO:
                ui.updateStatus("Step redo");
                break;
            case EVENT_GAME_DISTRIBUTE_COMPUTING:
                ui.updateStatus("Distributed computing");
                break;
            case EVENT_VIEW_DETAIL_SYSTEM_INFO:
                ui.updateStatus("Detailed system information");
                break;
            case EVENT_VIEW_AI_THINKING_STEP:
                ui.updateStatus("AI thinking step");
                break;
            case EVENT_VIEW_GAME_TREE:
                ui.updateStatus("Game tree");
                break;
            case EVENT_HELP_ABOUT:
                ui.updateStatus("About");
                break;
            case EVENT_HELP_TUTORIAL:
                ui.updateStatus("Tutorial");
                break;
        }
    }

    private void programTerminate(){
        System.exit(0);
    }
}
