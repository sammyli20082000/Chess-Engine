package UI_handler;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListDataListener;

/**
 * Created by him on 30/10/2015.
 */
public class InfoScrollPanelHandler {
    private JPanel base_panel;
    private ArrayList<JPanel> partPanels;
    private JScrollPane scroll_pane;
    private UIHandler ui;
    private int numberOfComponent = 0;

    public InfoScrollPanelHandler(UIHandler parent) {
        ui = parent;
        base_panel = new JPanel();
        base_panel.setLayout(new GridBagLayout());
        scroll_pane = new JScrollPane(base_panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        int border_width = scroll_pane.getVerticalScrollBar().getPreferredSize().width;
        base_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, border_width));
        base_panel.setBackground(Color.lightGray);

        addTitleLabel("Players");
        addPlayersPanel();
        addTitleLabel("Start Side");
        addStartSideControlPanel();
        addTitleLabel("Remaining Time");
        addRemainingTimePanel();
        addTitleLabel("Movement History");
        addMovementHistoryPanel();
        addTitleLabel("Resources Usage");
        addResourcesUsagePanel();
        addToBasePanel(new JLabel(" "));
        addToBasePanel(new JButton("Show AI Thinking Steps"));
        addToBasePanel(new JButton("Show Game Tree"));
        basePanelFillRemaining();
    }

    private void addResourcesUsagePanel() {
        JPanel jp = createGroupPanel();

        String[] columnNames = new String[]{
                "Item", "Value"
        };
        String[][] rowData = new String[][]{
                {"CPU Time", "2:50\"00"},
                {"No. Threads", "4"},
                {"Peek RAM", "1536 MB"},
                {"Curr. RAM", "64 MB"},
                {"Calc. Steps", "1048576"},
                {"1 Step Time", "0.162 ms"}
        };
        createTableThenAddToJPanel(jp, rowData, columnNames);
        addToBasePanel(jp);
    }

    private void addMovementHistoryPanel() {
        JPanel jp = createGroupPanel();

        JList movementHistory = new JList(new DefaultListModel<String>());
        DefaultListModel data = (DefaultListModel) movementHistory.getModel();
        for (int i = 0; i < 100; i++)
            data.addElement("Step " + (i + 1));

        jp.add(new JScrollPane(movementHistory));
        addToBasePanel(jp);
    }

    private void addPlayersPanel() {
        JPanel jp = createGroupPanel();
        String[] columnNames = new String[]{
                "Side",
                "Player"
        };
        String[][] rowData = new String[][]{
                {"Red", "Computer"},
                {"Black", "Player 1"}
        };
        createTableThenAddToJPanel(jp, rowData, columnNames);
        addToBasePanel(jp);
    }

    private void addRemainingTimePanel() {
        JPanel jp = createGroupPanel();
        String[] columnNames = new String[]{
                "Player",
                "Time"
        };
        String[][] rowData = new String[][]{
                {"Computer", "5:00\"00"},
                {"Player", "5:00\"00"}
        };
        createTableThenAddToJPanel(jp, rowData, columnNames);
        addToBasePanel(jp);
    }

    private void addStartSideControlPanel() {
        JPanel jp = createGroupPanel();

        ButtonGroup startSideGroup = new ButtonGroup();
        JRadioButton rb_player = new JRadioButton("Player 1"),
                rb_computer = new JRadioButton("Computer");

        startSideGroup.add(rb_player);
        startSideGroup.add(rb_computer);

        JButton startGameButton = new JButton("Start game");

        jp.add(rb_player);
        jp.add(rb_computer);
        jp.add(startGameButton);

        rb_player.setSelected(true);

        addToBasePanel(jp);
    }

    private void addTitleLabel(String title) {
        JLabel titleBar;
        if (numberOfComponent == 0)
            titleBar = new JLabel("<html>" + title + "</html>");
        else
            titleBar = new JLabel("<html><br>" + title + "</html>");

        Font lf = titleBar.getFont(), nf;
        nf = new Font(lf.getFontName(), Font.PLAIN, (int) (lf.getSize() * 1.5));
        titleBar.setFont(nf);
        titleBar.setForeground(Color.darkGray);
        titleBar.setHorizontalAlignment(JLabel.CENTER);

        addToBasePanel(titleBar);
    }

    private void addToBasePanel(Component p, double y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = numberOfComponent;
        c.gridx = 0;
        c.weighty = y;

        base_panel.add(p, c);
        numberOfComponent++;
    }

    private JPanel createGroupPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        Border tb = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        jp.setBorder(tb);
        return jp;
    }

    private JPanel createTableThenAddToJPanel(JPanel jp, String[][] data, String[] columnNames) {
        Color headedrColor = new Color(232, 232, 232);
        String boldColumnNames[] = new String[2];
        for(int i=0; i<columnNames.length; i++)
            boldColumnNames[i] = "<html><b>"+columnNames[i]+"</html>";
        String [][] header = new String[][]{boldColumnNames};
        JTable hd = new JTable(header, boldColumnNames);
        hd.setBackground(headedrColor);
        jp.add(hd);

        JTable dat = new JTable(data, columnNames);
        jp.add(dat);

        return jp;
    }

    public JScrollPane getJPanel() {
        return scroll_pane;
    }

    private void basePanelFillRemaining() {
        addToBasePanel(new JLabel(" "), 1.0);
    }

    private void addToBasePanel(Component p) {
        addToBasePanel(p, 0.0);
    }
}
