package UIHandlerModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import BoardModel.Board;
import BoardModel.Point;
import PieceModel.Piece;

public class UIHandler {
    //------------------------------callback message code------------------------------
    public enum MenubarMessage {
        MENUITEM_WINDOW_CLOSING,
        MENUITEM_NEW_GAME,
        MENUITEM_LOAD_GAME,
        MENUITEM_SAVE_GAME,
        MENUITEM_STEP_UNDO,
        MENUITEM_STEP_REDO,
        MENUITEM_GAME_DISTRIBUTE_COMPUTING,
        MENUITEM_VIEW_FIT_WINDOW,
        MENUITEM_VIEW_DETAIL_SYSTEM_INFO,
        MENUITEM_VIEW_AI_THINKING_STEP,
        MENUITEM_VIEW_GAME_TREE,
        MENUITEM_HELP_TUTORIAL,
        MENUITEM_HELP_ABOUT
    }

    //------------------------------const values for private usage------------------------------
    private static String app_name = "Chess Game Executable";

    //------------------------------private objects------------------------------
    private JFrame main_window;
    private JLabel status_text;
    private JPanel status_bar;
    private InfoScrollPanelHandler infoPanelHandler;
    private GameAreaPanel gameAreaPanel;
    private eventCallBack myCallBack;

    //------------------------------public callback template------------------------------
    public interface eventCallBack {
        public void onMenuBarItemClicked(MenubarMessage msg);
        public Board getBoard();
        public void onPieceSelected(Piece piece);
        public void onPointSelected(Point point);
        public ArrayList<Piece> getCurrentPieces();
        public Piece getSelectedPiece();
        public Point getSelectedPoint();
    }

    //------------------------------function area------------------------------
    public UIHandler(eventCallBack g) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){e.printStackTrace();}

        myCallBack = g;
        main_window = new JFrame(app_name);
        status_bar = new JPanel();
        status_text = new JLabel("Ready");
        infoPanelHandler = new InfoScrollPanelHandler(this);
        gameAreaPanel = new GameAreaPanel(this, myCallBack.getBoard());
        setupMainWindow();
    }

    private void setupMainWindow() {
        main_window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                myCallBack.onMenuBarItemClicked(MenubarMessage.MENUITEM_WINDOW_CLOSING);
            }
        });
        status_bar.setLayout(new FlowLayout(FlowLayout.LEADING));
        status_bar.setBackground(new Color(232, 232, 232));
        status_bar.add(status_text);
        status_bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(143, 143, 143)));

        main_window.setJMenuBar((new MenuBarHandler(this)).getJMenuBar());
        main_window.add(status_bar, BorderLayout.PAGE_END);
        main_window.add(infoPanelHandler.getJScrollPane(), BorderLayout.LINE_END);
        main_window.add(gameAreaPanel, BorderLayout.CENTER);

        main_window.addComponentListener(getWindowResizeHandler());

        main_window.setVisible(true);
        main_window.pack();
        int
                srW = (int) getScreenResolution().getWidth(),
                srH = (int) getScreenResolution().getHeight(),
                sW = main_window.getWidth(),
                sH = main_window.getHeight();
        main_window.setLocation((srW - sW) / 2, (srH - sH) / 2);
    }

    private ComponentAdapter getWindowResizeHandler(){
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                gameAreaPanel.notifyWindowResized();
            }
        };
    }

    public Dimension getScreenResolution(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void fitWindow(){
        main_window.pack();
    }

    public eventCallBack getCalback(){
        return myCallBack;
    }

    public void updateStatus(String msg) {
        status_text.setText(msg);
    }
}
