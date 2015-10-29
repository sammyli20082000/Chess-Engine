package UI_handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Board.Board;

public class UIHandler {
    //------------------------------callback message code------------------------------
    public static final int
            EVENT_PIECE_SELECTED = 1001,
            EVENT_WINDOW_CLOSING = 1002,
            EVENT_NEW_GAME = 1003,
            EVENT_LOAD_GAME = 1004,
            EVENT_SAVE_GAME = 1005,
            EVENT_STEP_UNDO = 1006,
            EVENT_STEP_REDO = 1007,
            EVENT_GAME_DISTRIBUTE_COMPUTING = 1008,
            EVENT_VIEW_DETAIL_SYSTEM_INFO = 1009,
            EVENT_VIEW_AI_THINKING_STEP = 1010,
            EVENT_VIEW_GAME_TREE = 1011,
            EVENT_HELP_TUTORIAL = 1012,
            EVENT_HELP_ABOUT = 1013
    ;

    //------------------------------const values for private usage------------------------------
    private static int
            main_window_height=720,
            main_window_width=1280;
    private static String app_name="Chess Game Executable";

    //------------------------------private objects------------------------------
    private JFrame main_window;
    private JLabel status_text;
    private JPanel status_bar;
    private eventCallBack myCallBack;

    //------------------------------public callback template------------------------------
    public interface eventCallBack {public void onUICallBack(int msg);}

    //------------------------------function area------------------------------
    public UIHandler(eventCallBack g) {
        myCallBack = g;
        main_window = new JFrame(app_name);
        status_bar = new JPanel();
        status_text = new JLabel("Ready");
        setupMainWindow();
    }

    private void setupMainWindow(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}
        main_window.setSize(main_window_width, main_window_height);
        main_window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                myCallBack.onUICallBack(EVENT_WINDOW_CLOSING);
            }
        });
        status_bar.setLayout(new FlowLayout(FlowLayout.LEADING));
        status_bar.setBackground(Color.white);
        status_bar.add(status_text);

        main_window.setJMenuBar((new MenuBarHandler(this)).getJMenuBar());
        main_window.add(status_bar, BorderLayout.PAGE_END);

        main_window.setVisible(true);
    }

    public void sendMessaageToGame(int msg){
        myCallBack.onUICallBack(msg);
    }
    public void updateStatus(String msg){
        status_text.setText(msg);
    }
}
