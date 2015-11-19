package UIHandlerModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
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
        MENUITEM_VIEW_SHOW_DEBUG,
        MENUITEM_VIEW_SHOW_PIECE_PLACING_POINT,
        MENUITEM_HELP_TUTORIAL,
        MENUITEM_HELP_ABOUT
    }

    //------------------------------const values for private usage------------------------------
    private static String app_name = "Chess Game Executable";

    //------------------------------private objects------------------------------
    private JFrame main_window;
    private StatusBarHandler statusBarHandler;
    private InfoScrollPanelHandler infoPanelHandler;
    private GameAreaPanel gameAreaPanel;
    private eventCallBack myCallBack;
    private MenuBarHandler menuBarHandler;

    //------------------------------public callback template------------------------------
    public interface eventCallBack {
        public void onMenuBarItemClicked(MenubarMessage msg);
        public void onPieceOnPointSelected(Point point);
        public void onPointSelected(Point point);
        public void onCancelMovement();
        public void onConfirmMovement();
        public void onStartGame(String playerSide);
        public Point getSelectedPointOrPiece();
    }

    //------------------------------function area------------------------------
    public UIHandler(eventCallBack g) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){e.printStackTrace();}

        myCallBack = g;
        main_window = new JFrame(app_name);
        infoPanelHandler = new InfoScrollPanelHandler(this);
        gameAreaPanel = new GameAreaPanel(this);
        menuBarHandler = new MenuBarHandler(this);
        statusBarHandler = new StatusBarHandler(this);
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

        main_window.setJMenuBar(menuBarHandler.getJMenuBar());
        main_window.add(statusBarHandler.getJPanel(), BorderLayout.PAGE_END);
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
                refreshWindow();
            }
        };
    }

    public double getBoardTangent(){
        return gameAreaPanel.getBoardTangent();
    }

    public Dimension getScreenResolution(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void refreshWindow(){
        main_window.repaint();
    }

    public void fitWindow(){
        main_window.pack();
    }

    protected eventCallBack getCallback(){
        return myCallBack;
    }

    public void updateStatusBarStatus(String msg) {
        statusBarHandler.updateStatusBarStatus(msg);
    }

    public boolean getIsShowDebug(){
        return menuBarHandler.getIsShowDebug();
    }

    public boolean getIsShowPiecePlacingPoint(){
        return menuBarHandler.getIsShowPiecePlacingPoint();
    }

    public void infoPanelUpdatePlayerSideData(String rowTag, String value){
        infoPanelHandler.updatePlayerSideTableRow(rowTag, value);
    }

    public void infoPanelUpdatePlayerRemainingTimeData(String rowTag, String value){
        infoPanelHandler.updatePlayerRemainingTimeTableRow(rowTag, value);
    }
    public void infoPanelUpdateSystemInfoData(String rowTag, String value) {
        infoPanelHandler.updateSystemInfoTableRow(rowTag, value);
    }

    public void setStatusBarButtonsEnabled(boolean opt){
        statusBarHandler.setButtonsEnabled(opt);
    }

    public void addMovementHistoryRecord(String record){
        infoPanelHandler.addMovementHistoryRecord(record);
    }

    public void setBoard(Board b){
        gameAreaPanel.setBoard(b);
    }
}
