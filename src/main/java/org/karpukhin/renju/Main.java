package org.karpukhin.renju;

import javax.swing.*;
import java.awt.*;

/**
 * @author Pavel Karpukhin
 * @since 02.11.12
 */
public class Main {

    private JFrame mainFrame;
    private RenjuComponent renjuComponent = new RenjuComponent();

    public void init() {
        initFrame();
        initComponents();
        initLayout();
    }

    /**
     * Initializes frame
     */
    public void initFrame() {
        mainFrame = new JFrame("Renju");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initializes components
     */
    public void initComponents() {
    }

    /**
     * Initializes layout
     */
    public void initLayout() {
        GroupLayout layout = new GroupLayout(mainFrame.getContentPane());

        GroupLayout.Group horGroup = layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(renjuComponent)
                .addContainerGap();
        GroupLayout.Group verGroup = layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(renjuComponent)
                .addContainerGap();

        layout.setHorizontalGroup(horGroup);
        layout.setVerticalGroup(verGroup);

        mainFrame.getContentPane().setLayout(layout);
    }

    /**
     * Shows main frame
     */
    public void show() {
        mainFrame.pack();
        mainFrame.setResizable(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation((d.width - mainFrame.getWidth()) / 2, (d.height - mainFrame.getHeight()) / 2);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = new Main();
                main.init();
                main.show();
            }
        });
    }
}
