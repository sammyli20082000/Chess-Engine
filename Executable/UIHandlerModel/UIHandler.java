package Executable.UIHandlerModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Executable.ObjectModel.*;
import Executable.BoardModel.Point;
import Executable.BoardModel.Board;

public class UIHandler {
	// ------------------------------callback message
	// code------------------------------
	public enum MenubarMessage {
		MENUITEM_WINDOW_CLOSING, MENUITEM_NEW_GAME, MENUITEM_LOAD_GAME, MENUITEM_SAVE_GAME, MENUITEM_STEP_UNDO, MENUITEM_STEP_REDO, MENUITEM_GAME_DISTRIBUTE_COMPUTING, MENUITEM_VIEW_FIT_WINDOW, MENUITEM_VIEW_DETAIL_SYSTEM_INFO, MENUITEM_VIEW_AI_THINKING_STEP, MENUITEM_VIEW_GAME_TREE, MENUITEM_VIEW_SHOW_DEBUG, MENUITEM_VIEW_SHOW_PIECE_PLACING_POINT, MENUITEM_HELP_TUTORIAL, MENUITEM_HELP_ABOUT, MENUITEM_SERVER, MENUITEM_CLIENT
	}

	// ------------------------------const values for private
	// usage------------------------------
	private static String app_name = "Chess Game Executable";

	// ------------------------------private
	// objects------------------------------
	private JFrame main_window;
	private StatusBarHandler statusBarHandler;
	private InfoScrollPanelHandler infoPanelHandler;
	private GameAreaPanel gameAreaPanel;
	private EventCallBackHandler myCallBack;
	private MenuBarHandler menuBarHandler;

	// ------------------------------public callback
	// template------------------------------
	public interface EventCallBackHandler {
		public void onMenuBarItemClicked(MenubarMessage msg);

		public void onPieceOnPointSelected(Point point);

		public void onPointSelected(Point point);

		public void onCancelMovement();

		public void onConfirmMovement();

		public void onStartGame(String playerSide);

		public Point getSelectedPointOrPiece();

		public ArrayList<Point> getPieceNextMovePointCandidateList();

		public void onUndo(int undoStep);
	}

	// ------------------------------function area------------------------------
	public UIHandler(EventCallBackHandler g) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		int srW = (int) getScreenResolution().getWidth(), srH = (int) getScreenResolution().getHeight(),
				sW = main_window.getWidth(), sH = main_window.getHeight();
		main_window.setLocation((srW - sW) / 2, (srH - sH) / 2);
	}

	private ComponentAdapter getWindowResizeHandler() {
		return new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				gameAreaPanel.notifyWindowResized();
				refreshWindow();
			}
		};
	}

	// ------------------------------public APIs------------------------------
	public double getBoardTangent() {
		return gameAreaPanel.getBoardTangent();
	}

	public Dimension getScreenResolution() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public void refreshWindow() {
		if (!AI.virtualMoving)
			main_window.repaint();
	}

	public void fitWindow() {
		main_window.pack();
	}

	protected EventCallBackHandler getCallback() {
		return myCallBack;
	}

	public void updateStatusBarStatus(String msg) {
		statusBarHandler.updateStatusBarStatus(msg);
	}

	public boolean getIsShowDebug() {
		return menuBarHandler.getIsShowDebug();
	}

	public boolean getIsShowPiecePlacingPoint() {
		return menuBarHandler.getIsShowPiecePlacingPoint();
	}

	public void infoPanelUpdatePlayerSideData(String rowTag, String value) {
		infoPanelHandler.updatePlayerSideTableRow(rowTag, value);
	}

	public void infoPanelUpdatePlayerRemainingTimeData(String rowTag, String value) {
		infoPanelHandler.updatePlayerRemainingTimeTableRow(rowTag, value);
	}

	public void infoPanelUpdateSystemInfoData(JRadioButton jrb) {
		infoPanelHandler.updateSystemInfoTableRow(jrb);
	}

	public void setStatusBarButtonsEnabled(boolean opt) {
		statusBarHandler.setButtonsEnabled(opt);
	}

	public void addMovementHistoryRecord(String record) {
		infoPanelHandler.addMovementHistoryRecord(record);
	}

	public void setBoard(Board b) {
		gameAreaPanel.setBoard(b);
	}

	public void setIsShowPiecePlacingPoint(boolean opt) {
		menuBarHandler.setIsShowPiecePlacingPoint(opt);
	}

	public boolean isBoardImageNotSet() {
		return gameAreaPanel.isBoardImageNotSet();
	}

	public void enableStartGameButton() {
		infoPanelHandler.enableStartGameButton();
		infoPanelHandler.enableStartSideList();
	}

	public void restoreMovementHistoryList() {
		infoPanelHandler.restoreMovementHistoryList();
	}

	public JList getMovementHistoryList() {
		return infoPanelHandler.getMovementHistoryList();
	}
	
	public String getSelectedPiece() {
		return infoPanelHandler.getSelectedPiece();
	}
}
