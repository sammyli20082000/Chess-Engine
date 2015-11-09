package UIHandlerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Created by him on 29/10/2015.
 */
public class MenuBarHandler {
    private JMenuBar myMenuBar;
    private UIHandler handler;

    public MenuBarHandler(UIHandler ui){
        handler = ui;
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

            for(int j=0; j<menuItemName[i].length; j++)
                makeMenuItem(menu, menuItemName[i][j], menuMessage[i][j]);

            myMenuBar.add(menu);
        }
    }

    private void makeMenuItem(JMenu menu, String menuItemName, UIHandler.MenubarMessage msg){
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(getMenuItemActionListener(msg));
        menu.add(menuItem);
    }

    public JMenuBar getJMenuBar(){
        return myMenuBar;
    }

    private ActionListener getMenuItemActionListener(final UIHandlerModel.UIHandler.MenubarMessage msg){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.getCalback().onMenuBarItemClicked(msg);
            }
        };
    }
}
