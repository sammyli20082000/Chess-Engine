package UIHandlerModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 * Created by him on 30/10/2015.
 */
public class InfoScrollPanelHandler {
    private JPanel base_panel, startSidePanel;
    private JButton showAIButton, showGameTreeButton, undoButton, startGameButton;
    private ButtonGroup startSideGroup;
    private JScrollPane scroll_pane;
    private JTable playerSideTable, playerRemainingTimeTable, systemInfoTable;
    private JList movementHistoryList;
    private ArrayList<JRadioButton> startSideRadioButtonList;
    private UIHandler ui;
    private int numberOfComponent = 0;
    private final int scrollSpeed = 10;

    public InfoScrollPanelHandler(UIHandler parent) {
        ui = parent;
        base_panel = new JPanel();
        base_panel.setLayout(new GridBagLayout());
        scroll_pane = new JScrollPane(base_panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_pane.getVerticalScrollBar().setUnitIncrement(scrollSpeed);

        int border_width = scroll_pane.getVerticalScrollBar().getPreferredSize().width;
        base_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, border_width));
        base_panel.setBackground(Color.lightGray);

        showAIButton = new JButton("Show AI Thinking Steps");
        showAIButton.addMouseListener(getButtonOnClickListentForShowingAIOrGameTree(UIHandler.MenubarMessage.MENUITEM_VIEW_AI_THINKING_STEP, showAIButton));
        showGameTreeButton = new JButton("Show Game Tree");
        showGameTreeButton.addMouseListener(getButtonOnClickListentForShowingAIOrGameTree(UIHandler.MenubarMessage.MENUITEM_VIEW_GAME_TREE,showGameTreeButton));
        undoButton = new JButton("Undo to selected step");
        undoButton.addMouseListener(getUndoButtonOnClickListener(undoButton));
        startGameButton = new JButton("Start Game");
        startGameButton.addMouseListener(getStartGameButtonOnClickListener(startGameButton));

        addTitleLabel("Players");
        addPlayersPanel();
        addTitleLabel("Start Side");
        addStartSideControlPanel();
        addToBasePanel(startGameButton);
        addTitleLabel("Remaining Time");
        addRemainingTimePanel();
        addTitleLabel("Movement History");
        addMovementHistoryPanel();
        addToBasePanel(undoButton);
        addTitleLabel("Resources Usage");
        addResourcesUsagePanel();
        addToBasePanel(new JLabel(" "));
        addToBasePanel(showAIButton);
        addToBasePanel(showGameTreeButton);
        basePanelFillRemaining();

        scroll_pane.setPreferredSize(new Dimension(
                (int) (scroll_pane.getPreferredSize().getWidth()), 0
        ));
    }

    private void addResourcesUsagePanel() {
        JPanel jp = createGroupPanel();

        String[] columnNames = new String[]{
                "Item", "Value"
        };

        systemInfoTable = new JTable();

        createTableThenAddToJPanel(jp, systemInfoTable, columnNames);
        addToBasePanel(jp);
    }

    private void addMovementHistoryPanel() {
        JPanel jp = createGroupPanel();

        movementHistoryList = new JList();
        DefaultListModel<String> data = new DefaultListModel<String>();
        movementHistoryList.setModel(data);
        for (int i = 0; i < 0; i++)
            data.addElement("Step " + (i + 1));

        jp.add(new JScrollPane(movementHistoryList));
        addToBasePanel(jp);
    }

    private void addPlayersPanel() {
        JPanel jp = createGroupPanel();
        String[] columnNames = new String[]{
                "Side",
                "Player"
        };
        playerSideTable = new JTable();
        createTableThenAddToJPanel(jp, playerSideTable, columnNames);
        addToBasePanel(jp);
    }

    private void addRemainingTimePanel() {
        JPanel jp = createGroupPanel();
        String[] columnNames = new String[]{
                "Player",
                "Time"
        };
        playerRemainingTimeTable = new JTable();
        createTableThenAddToJPanel(jp, playerRemainingTimeTable, columnNames);
        addToBasePanel(jp);
    }

    private void addStartSideControlPanel() {
        startSidePanel = createGroupPanel();
        startSideGroup = new ButtonGroup();
        addToBasePanel(startSidePanel);
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

    private JPanel createTableThenAddToJPanel(JPanel jp, JTable contentTable, String[] columnNames) {
        Color headedrColor = new Color(232, 232, 232);
        String header[][] = new String[1][columnNames.length];
        for (int i = 0; i < columnNames.length; i++)
            header[0][i] = "<html><b>" + columnNames[i] + "</html>";
        JTable hd = new JTable(header, columnNames);
        hd.setEnabled(false);
        hd.setBackground(headedrColor);
        jp.add(hd);

        DefaultTableModel model = new DefaultTableModel(0, 0);
        model.setColumnIdentifiers(columnNames);
        contentTable.setModel(model);
        contentTable.setEnabled(false);
        jp.add(contentTable);

        return jp;
    }

    private MouseAdapter getButtonOnClickListentForShowingAIOrGameTree(final UIHandler.MenubarMessage msg, final JButton thisButton) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!thisButton.isEnabled()) return;
                ui.getCallback().onMenuBarItemClicked(msg);
            }
        };
    }

    public JScrollPane getJScrollPane() {
        return scroll_pane;
    }

    private void basePanelFillRemaining() {
        addToBasePanel(new JLabel(" "), 1.0);
    }

    private void addToBasePanel(Component p) {
        addToBasePanel(p, 0.0);
    }

    public void updatePlayerSideTableRow(String sideColor, String sideName) {
        updateTableRow(playerSideTable, sideColor, sideName);
        updatePlayerRemainingTimeTableRow(sideName, "");
        addStartSideRadioButton(sideName);
    }

    public void updatePlayerRemainingTimeTableRow(String rowTag, String value) {
        updateTableRow(playerRemainingTimeTable, rowTag, value);
    }

    public void updateSystemInfoTableRow(String rowTag, String value) {
        updateTableRow(systemInfoTable, rowTag, value);
    }

    private MouseAdapter getUndoButtonOnClickListener(final JButton thisButton){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!thisButton.isEnabled()) return;
                int listSize = ((DefaultListModel)movementHistoryList.getModel()).getSize(), selectedIndex= movementHistoryList.getSelectedIndex();
                if (listSize ==0) return;
                for(int i=0; i<listSize - selectedIndex; i++){
                    ui.getCallback().onMenuBarItemClicked(UIHandler.MenubarMessage.MENUITEM_STEP_UNDO);
                    ((DefaultListModel)movementHistoryList.getModel()).remove(
                            ((DefaultListModel) movementHistoryList.getModel()).getSize() - 1
                    );
                    historyListSelectLastItem();
                }

            }
        };
    }

    private void updateTableRow(JTable myTable, String rowTag, String value) {
        for (int i = 0; i < myTable.getRowCount(); i++) {
            if (myTable.getValueAt(i, 0).equals(rowTag)) {
                myTable.setValueAt(value, i, 1);
                return;
            }
        }
        ((DefaultTableModel) myTable.getModel()).addRow(new String[]{rowTag, value});
    }

    public void addStartSideRadioButton(String name) {
        if (startSideRadioButtonList == null)
            startSideRadioButtonList = new ArrayList<JRadioButton>();

        JRadioButton rb = new JRadioButton(name);
        startSideGroup.add(rb);
        startSidePanel.add(rb);
        startSideRadioButtonList.add(rb);
        rb.setSelected(true);
    }

    private MouseAdapter getStartGameButtonOnClickListener(final JButton thisButton) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!thisButton.isEnabled()) return;
                ui.getCallback().onStartGame(getStartGameSide());
                startGameButton.setEnabled(false);
            }
        };
    }

    public String getStartGameSide() {
        for (JRadioButton rb : startSideRadioButtonList) {
            if (rb.isSelected())
                return rb.getText();
        }
        return "";
    }

    public void addMovementHistoryRecord(String msg) {
        ((DefaultListModel<String>) movementHistoryList.getModel()).addElement(msg);
        historyListSelectLastItem();
    }

    private void historyListSelectLastItem(){
        if (movementHistoryList.getModel().getSize() ==0) return;
        movementHistoryList.setSelectedIndex(movementHistoryList.getModel().getSize() - 1);
        movementHistoryList.ensureIndexIsVisible(movementHistoryList.getModel().getSize() - 1);
    }
}