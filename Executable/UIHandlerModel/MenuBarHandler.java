package Executable.UIHandlerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Created by him on 29/10/2015.
 */
public class MenuBarHandler {
    private JMenuBar myMenuBar;
    private UIHandler ui;
    private JCheckBoxMenuItem checkItemShowDebug, checkItemShowPiecePlacing;

    public MenuBarHandler(UIHandler ui){
        this.ui = ui;
        myMenuBar = new JMenuBar();

        String menuName[] = new String[]{
            "File", "Edit", "Game", "View", "Help"
        };

        String menuItemName[][] = new String[][]{
                {"New Game", "Load Game", "Save Game", "Exit"},
                {"Undo", "Redo"},
                {"Distributed Computing"},
                {"Auto Fit Window", "Detailed system information", "AI Thinking Step", "Game Tree"},
                {"Tutorial", "About"}
        };

        UIHandler.MenubarMessage menuMessage[][] = new UIHandler.MenubarMessage[][]{
                {
                        UIHandler.MenubarMessage.MENUITEM_NEW_GAME,
                        UIHandler.MenubarMessage.MENUITEM_LOAD_GAME,
                        UIHandler.MenubarMessage.MENUITEM_SAVE_GAME,
                        UIHandler.MenubarMessage.MENUITEM_WINDOW_CLOSING
                },
                {
                        UIHandler.MenubarMessage.MENUITEM_STEP_UNDO,
                        UIHandler.MenubarMessage.MENUITEM_STEP_REDO
                },
                {
                        UIHandler.MenubarMessage.MENUITEM_GAME_DISTRIBUTE_COMPUTING
                },
                {
                        UIHandler.MenubarMessage.MENUITEM_VIEW_FIT_WINDOW,
                        UIHandler.MenubarMessage.MENUITEM_VIEW_DETAIL_SYSTEM_INFO,
                        UIHandler.MenubarMessage.MENUITEM_VIEW_AI_THINKING_STEP,
                        UIHandler.MenubarMessage.MENUITEM_VIEW_GAME_TREE
                },
                {
                        UIHandler.MenubarMessage.MENUITEM_HELP_TUTORIAL,
                        UIHandler.MenubarMessage.MENUITEM_HELP_ABOUT
                }
        };

        for(int i=0; i<menuName.length;i++){
            JMenu menu = new JMenu(menuName[i]);

            if (i == menuName.length-2){
                checkItemShowDebug = new JCheckBoxMenuItem("Show Debug");
                checkItemShowPiecePlacing = new JCheckBoxMenuItem("Show Piece Placing Points");
                makeCheckedMenuItem(menu, checkItemShowDebug, UIHandler.MenubarMessage.MENUITEM_VIEW_SHOW_DEBUG);
                makeCheckedMenuItem(menu, checkItemShowPiecePlacing, UIHandler.MenubarMessage.MENUITEM_VIEW_SHOW_PIECE_PLACING_POINT);
            }

            for(int j=0; j<menuItemName[i].length; j++)
                makeMenuItem(menu, menuItemName[i][j], menuMessage[i][j]);

            myMenuBar.add(menu);
        }
    }

    private void makeCheckedMenuItem(JMenu menu, JCheckBoxMenuItem menuItem, UIHandler.MenubarMessage msg){
        menuItem.addActionListener(getMenuItemActionListener(msg));
        menu.add(menuItem);
    }

    private void makeMenuItem(JMenu menu, String menuItemName, UIHandler.MenubarMessage msg){
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(getMenuItemActionListener(msg));
        menu.add(menuItem);
    }

    public JMenuBar getJMenuBar(){
        return myMenuBar;
    }

    private ActionListener getMenuItemActionListener(final UIHandler.MenubarMessage msg){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.getCallback().onMenuBarItemClicked(msg);
                if(msg.equals(UIHandler.MenubarMessage.MENUITEM_VIEW_SHOW_DEBUG)||
                        msg.equals(UIHandler.MenubarMessage.MENUITEM_VIEW_SHOW_PIECE_PLACING_POINT))
                    ui.refreshWindow();
            }
        };
    }

    public boolean getIsShowDebug(){
        return checkItemShowDebug.getState();
    }
    public boolean getIsShowPiecePlacingPoint(){
        return checkItemShowPiecePlacing.getState();
    }
    public void setIsShowPiecePlacingPoint(boolean opt) {
        checkItemShowPiecePlacing.setState(opt);
    }
}
