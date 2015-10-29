package UI_handler;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Created by him on 30/10/2015.
 */
public class InfoScrollPanelHandler {
    private JPanel base_panel;
    private JScrollPane scroll_panel;

    public InfoScrollPanelHandler(){
        base_panel = new JPanel();
        scroll_panel = new JScrollPane(base_panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_panel.add(new Button("test button"));
    }

    public JScrollPane getJPanel(){
        return scroll_panel;
    }
}
