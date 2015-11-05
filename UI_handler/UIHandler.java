package UI_handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Board.Board;

public class UIHandler {
    //------------------------------callback message code------------------------------
    public enum Message{
        EVENT_PIECE_SELECTED,
        EVENT_WINDOW_CLOSING,
        EVENT_NEW_GAME,
        EVENT_LOAD_GAME,
        EVENT_SAVE_GAME,
        EVENT_STEP_UNDO,
        EVENT_STEP_REDO,
        EVENT_GAME_DISTRIBUTE_COMPUTING,
        EVENT_VIEW_DETAIL_SYSTEM_INFO,
        EVENT_VIEW_AI_THINKING_STEP,
        EVENT_VIEW_GAME_TREE,
        EVENT_HELP_TUTORIAL,
        EVENT_HELP_ABOUT,
        EVENT_REQUEST_BOARD_SIDE_INFO,

    }

    //------------------------------const values for private usage------------------------------
    private static int
            main_window_height=720,
            main_window_width=1280;
    private static String app_name="Chess Game Executable";

    //------------------------------private objects------------------------------
    private JFrame main_window;
    private JLabel status_text;
    private JPanel status_bar;
    private InfoScrollPanelHandler info_panel;
    private eventCallBack myCallBack;

    //------------------------------public callback template------------------------------
    public interface eventCallBack {public void onUICallBack(Message msg);}

    //------------------------------function area------------------------------
    public UIHandler(eventCallBack g) {
        myCallBack = g;
        main_window = new JFrame(app_name);
        status_bar = new JPanel();
        status_text = new JLabel("Ready");
        info_panel = new InfoScrollPanelHandler(this);
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
                myCallBack.onUICallBack(Message.EVENT_WINDOW_CLOSING);
            }
        });
        status_bar.setLayout(new FlowLayout(FlowLayout.LEADING));
        status_bar.setBackground(new Color(232, 232, 232));
        status_bar.add(status_text);
        status_bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(143, 143, 143)));

        main_window.setJMenuBar((new MenuBarHandler(this)).getJMenuBar());
        main_window.add(status_bar, BorderLayout.PAGE_END);
        main_window.add(info_panel.getJPanel(), BorderLayout.LINE_END);

        main_window.setVisible(true);
    }

    public void sendMessaageToGame(Message msg){
        myCallBack.onUICallBack(msg);
    }
    public void updateStatus(String msg){
        status_text.setText(msg);
    }
}
