package UIHandlerModel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by root on 11/16/15.
 */
public class StatusBarHandler {
    private JPanel basePanel;
    private UIHandler ui;
    private JLabel statusText;
    private JButton cancelMove, confirmMove;
    private GridBagConstraints statusTextGBC, cancelMoveGBC, confirmMoveGBC;

    public StatusBarHandler(UIHandler uiHandler){
        ui = uiHandler;
        basePanel = new JPanel();
        statusText = new JLabel("");
        cancelMove = new JButton("Cancel Move");
        confirmMove = new JButton("Confirm Move");

        statusTextGBC = new GridBagConstraints();
        statusTextGBC.gridx = 0;
        statusTextGBC.gridy = 0;
        statusTextGBC.weightx = 1;
        statusTextGBC.fill = GridBagConstraints.HORIZONTAL;

        cancelMoveGBC = new GridBagConstraints();
        cancelMoveGBC.gridx = 1;
        cancelMoveGBC.gridy = 0;

        confirmMoveGBC = new GridBagConstraints();
        confirmMoveGBC.gridx=2;
        confirmMoveGBC.gridy=0;

        cancelMove.addMouseListener(getOnClickListener(false, cancelMove));
        confirmMove.addMouseListener(getOnClickListener(true, confirmMove));

        basePanel.setLayout(new GridBagLayout());
        basePanel.setBackground(new Color(232, 232, 232));
        basePanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(143, 143, 143)));
        basePanel.add(statusText, statusTextGBC);
        basePanel.add(cancelMove, cancelMoveGBC);
        basePanel.add(confirmMove, confirmMoveGBC);

        setButtonsEnabled(false);
    }

    public void setButtonsEnabled(boolean opt){
        confirmMove.setEnabled(opt);
        cancelMove.setEnabled(opt);
    }

    public JPanel getJPanel(){
        return basePanel;
    }

    public void updateStatusBarStatus(String msg) {
        statusText.setText(msg);
    }

    private MouseAdapter getOnClickListener(boolean isConfirm, final JButton thisButton){
        if(isConfirm)
            return new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (!thisButton.isEnabled()) return;
                    ui.getCallback().onConfirmMovement();
                }
            };
        else
            return new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (!thisButton.isEnabled()) return;
                    ui.getCallback().onCancelMovement();
                }
            };
    }
}
