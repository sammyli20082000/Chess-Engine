package UI_handler;

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

        JMenu menuFile = new JMenu("File");
        JMenu menuEdit = new JMenu("Edit");
        JMenu menuGame = new JMenu("Game");
        JMenu menuView = new JMenu("View");
        JMenu menuHelp = new JMenu("Help");

        JMenuItem item_file_new_game = new JMenuItem("New Game");
        JMenuItem item_file_load_game = new JMenuItem("Load Game");
        JMenuItem item_file_save_game = new JMenuItem("Save Game");
        JMenuItem item_file_exit = new JMenuItem("Exit");
        item_file_new_game.addActionListener(getMenuItemActionListener(UIHandler.EVENT_NEW_GAME));
        item_file_load_game.addActionListener(getMenuItemActionListener(UIHandler.EVENT_LOAD_GAME));
        item_file_save_game.addActionListener(getMenuItemActionListener(UIHandler.EVENT_SAVE_GAME));
        item_file_exit.addActionListener(getMenuItemActionListener(UIHandler.EVENT_WINDOW_CLOSING));
        menuFile.add(item_file_new_game);
        menuFile.add(item_file_load_game);
        menuFile.add(item_file_save_game);
        menuFile.add(item_file_exit);

        JMenuItem item_edit_step_undo = new JMenuItem("Undo");
        JMenuItem item_edit_step_redo = new JMenuItem("Redo");
        item_edit_step_undo.addActionListener(getMenuItemActionListener(UIHandler.EVENT_STEP_UNDO));
        item_edit_step_redo.addActionListener(getMenuItemActionListener(UIHandler.EVENT_STEP_REDO));
        menuEdit.add(item_edit_step_undo);
        menuEdit.add(item_edit_step_redo);

        JMenuItem item_game_distribute_computing = new JMenuItem("Distributed computing");
        item_game_distribute_computing.addActionListener(getMenuItemActionListener(UIHandler.EVENT_GAME_DISTRIBUTE_COMPUTING));
        menuGame.add(item_game_distribute_computing);

        JMenuItem item_view_detail_system_info = new JMenuItem("Detailed system information");
        JMenuItem item_view_ai_step = new JMenuItem("AI thinking step");
        JMenuItem item_view_game_tree = new JMenuItem("Game tree");
        item_view_detail_system_info.addActionListener(getMenuItemActionListener(UIHandler.EVENT_VIEW_DETAIL_SYSTEM_INFO));
        item_view_ai_step.addActionListener(getMenuItemActionListener(UIHandler.EVENT_VIEW_AI_THINKING_STEP));
        item_view_game_tree.addActionListener(getMenuItemActionListener(UIHandler.EVENT_VIEW_GAME_TREE));
        menuView.add(item_view_detail_system_info);
        menuView.add(item_view_ai_step);
        menuView.add(item_view_game_tree);

        JMenuItem item_help_tutorial = new JMenuItem("Tutorial");
        JMenuItem item_help_about = new JMenuItem("About");
        item_help_tutorial.addActionListener(getMenuItemActionListener(UIHandler.EVENT_HELP_TUTORIAL));
        item_help_about.addActionListener(getMenuItemActionListener(UIHandler.EVENT_HELP_ABOUT));
        menuHelp.add(item_help_tutorial);
        menuHelp.add(item_help_about);

        myMenuBar.add(menuFile);
        myMenuBar.add(menuEdit);
        myMenuBar.add(menuGame);
        myMenuBar.add(menuView);
        myMenuBar.add(menuHelp);
    }

    public JMenuBar getJMenuBar(){
        return myMenuBar;
    }

    private ActionListener getMenuItemActionListener(final int msg){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.sendMessaageToGame(msg);
            }
        };
    }
}
