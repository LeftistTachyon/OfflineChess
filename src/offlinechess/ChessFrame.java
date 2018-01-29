package offlinechess;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * A Frame for the application
 * @author Jed Wang
 */
public class ChessFrame extends JFrame {
    
    /**
     * Default constructor
     */
    public ChessFrame() {
        initComponents();
        /*setSize(new Dimension(535, 560));
        super.getContentPane().add(cp);
        super.setResizable(false);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
    }
    
    /**
     * Stops redrawing the chess board
     */
    public void stop() {
        System.out.println("STOP!");
        chessPanel.stop();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. (Unless you're Jed.)
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        chessPanel = new ChessPanel();
        sidePanel = new JPanel();
        yourTime = new JLabel();
        theirTime = new JLabel();
        jSeparator1 = new JSeparator();
        jSeparator2 = new JSeparator();
        theirName = new JLabel();
        yourName = new JLabel();
        jSeparator3 = new JSeparator();
        firstButton = new JButton();
        secondButton = new JButton();
        thirdButton = new JButton();
        jSeparator4 = new JSeparator();
        flipBoardButton = new JToggleButton();
        jSeparator5 = new JSeparator();
        rematchButton = new JButton();
        newGameButton = new JButton();
        gameHistoryPane = new JScrollPane();
        scrollContentPane = new JPanel();
        gameOutcomePanel = new JPanel();
        outcomeLabel4 = new JLabel();
        whyWinLabel4 = new JLabel();
        whoWinsLabel4 = new JLabel();
        textAreaWrapPane = new JScrollPane();
        moveTextArea = new JTextArea();
        jSeparator6 = new JSeparator();
        blackStatusPanel = new JPanel();
        whiteStatusPanel = new JPanel();
        infoPanel = new JPanel();
        middleSeparator = new JSeparator();
        blackName = new JLabel();
        whiteName = new JLabel();
        playingStatus = new JLabel();
        gameInfo = new JLabel();
        gameResult = new JLabel();
        whiteOrBlackPanel = new JPanel();
        speedPictureLabel = new JLabel();
        title = new JLabel();
        blackAdvantagePane = new JPanel();
        whiteAdvantagePane = new JPanel();
        chatPanel = new JPanel();
        chatCheckBox = new JCheckBox();
        jSeparator7 = new JSeparator();
        textPaneWrapScrollPane = new JScrollPane();
        chatHistoryPane = new JTextPane();
        chatTextField = new JTextField();
        jSeparator8 = new JSeparator();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Offline Chess");

        chessPanel.setBackground(new Color(200, 200, 200));
        chessPanel.setPreferredSize(new Dimension(535, 560));

        GroupLayout chessPanelLayout = new GroupLayout(chessPanel);
        chessPanel.setLayout(chessPanelLayout);
        chessPanelLayout.setHorizontalGroup(
            chessPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );
        chessPanelLayout.setVerticalGroup(
            chessPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        sidePanel.setBorder(BorderFactory.createLineBorder(new Color(203, 203, 203), 2));
        sidePanel.setPreferredSize(new Dimension(242, 333));

        yourTime.setFont(new Font("Consolas", 0, 28)); // NOI18N
        yourTime.setHorizontalAlignment(SwingConstants.CENTER);
        yourTime.setText("01:00");
        yourTime.setMaximumSize(new Dimension(126, 34));
        yourTime.setMinimumSize(new Dimension(126, 34));
        yourTime.setPreferredSize(new Dimension(126, 46));

        theirTime.setFont(new Font("Consolas", 0, 28)); // NOI18N
        theirTime.setHorizontalAlignment(SwingConstants.CENTER);
        theirTime.setText("01:00");
        theirTime.setMaximumSize(new Dimension(126, 34));
        theirTime.setMinimumSize(new Dimension(126, 34));
        theirTime.setPreferredSize(new Dimension(126, 46));

        jSeparator1.setBackground(new Color(203, 203, 203));
        jSeparator1.setForeground(new Color(203, 203, 203));

        jSeparator2.setBackground(new Color(203, 203, 203));
        jSeparator2.setForeground(new Color(203, 203, 203));

        theirName.setFont(new Font("Verdana", 0, 15)); // NOI18N
        theirName.setText("Anonymous");

        yourName.setFont(new Font("Verdana", 0, 15)); // NOI18N
        yourName.setText("Anonymous");

        jSeparator3.setBackground(new Color(225, 225, 225));
        jSeparator3.setForeground(new Color(225, 225, 225));

        firstButton.setMaximumSize(new Dimension(44, 33));
        firstButton.setPreferredSize(new Dimension(44, 33));
        firstButton.addActionListener(this::firstButtonPressed);

        secondButton.setMaximumSize(new Dimension(44, 33));
        secondButton.setPreferredSize(new Dimension(44, 33));
        secondButton.addActionListener(this::secondButtonPressed);

        thirdButton.setMaximumSize(new Dimension(44, 33));
        thirdButton.setPreferredSize(new Dimension(44, 33));
        thirdButton.addActionListener(this::thirdButtonPressed);

        jSeparator4.setBackground(new Color(225, 225, 225));
        jSeparator4.setForeground(new Color(225, 225, 225));

        flipBoardButton.setPreferredSize(new Dimension(23, 23));
        flipBoardButton.addActionListener(this::flipBoard);

        jSeparator5.setBackground(new Color(225, 225, 225));
        jSeparator5.setForeground(new Color(225, 225, 225));

        rematchButton.setFont(new Font("Verdana", 0, 12)); // NOI18N
        rematchButton.setText("REMATCH");
        rematchButton.addActionListener(this::rematchButtonPressed);

        newGameButton.setFont(new Font("Verdana", 0, 11)); // NOI18N
        newGameButton.setText("New Opponent");

        gameHistoryPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        gameHistoryPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        outcomeLabel4.setFont(new Font("Verdana", 1, 12)); // NOI18N
        outcomeLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        outcomeLabel4.setText("1-0");

        whyWinLabel4.setFont(new Font("Verdana", 2, 10)); // NOI18N
        whyWinLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        whyWinLabel4.setText("Time out");

        whoWinsLabel4.setFont(new Font("Verdana", 2, 10)); // NOI18N
        whoWinsLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        whoWinsLabel4.setText("White is victorious");

        GroupLayout gameOutcomePanelLayout = new GroupLayout(gameOutcomePanel);
        gameOutcomePanel.setLayout(gameOutcomePanelLayout);
        gameOutcomePanelLayout.setHorizontalGroup(
            gameOutcomePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(whoWinsLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(whyWinLabel4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(outcomeLabel4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        gameOutcomePanelLayout.setVerticalGroup(
            gameOutcomePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(gameOutcomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outcomeLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whyWinLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whoWinsLabel4)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textAreaWrapPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textAreaWrapPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textAreaWrapPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        moveTextArea.setEditable(false);
        moveTextArea.setBackground(new Color(240, 240, 240));
        moveTextArea.setColumns(19);
        moveTextArea.setFont(new Font("Verdana", 0, 12)); // NOI18N
        moveTextArea.setRows(5);
        textAreaWrapPane.setViewportView(moveTextArea);

        jSeparator6.setBackground(new Color(220, 220, 220));
        jSeparator6.setForeground(new Color(220, 220, 220));

        GroupLayout scrollContentPaneLayout = new GroupLayout(scrollContentPane);
        scrollContentPane.setLayout(scrollContentPaneLayout);
        scrollContentPaneLayout.setHorizontalGroup(
            scrollContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(gameOutcomePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textAreaWrapPane)
            .addComponent(jSeparator6)
        );
        scrollContentPaneLayout.setVerticalGroup(
            scrollContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, scrollContentPaneLayout.createSequentialGroup()
                .addComponent(textAreaWrapPane, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameOutcomePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        gameHistoryPane.setViewportView(scrollContentPane);

        blackStatusPanel.setPreferredSize(new Dimension(20, 20));

        GroupLayout blackStatusPanelLayout = new GroupLayout(blackStatusPanel);
        blackStatusPanel.setLayout(blackStatusPanelLayout);
        blackStatusPanelLayout.setHorizontalGroup(
            blackStatusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        blackStatusPanelLayout.setVerticalGroup(
            blackStatusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        whiteStatusPanel.setPreferredSize(new Dimension(20, 20));

        GroupLayout whiteStatusPanelLayout = new GroupLayout(whiteStatusPanel);
        whiteStatusPanel.setLayout(whiteStatusPanelLayout);
        whiteStatusPanelLayout.setHorizontalGroup(
            whiteStatusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        whiteStatusPanelLayout.setVerticalGroup(
            whiteStatusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addComponent(jSeparator5)
            .addComponent(rematchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(newGameButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(yourTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(theirTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(gameHistoryPane)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(GroupLayout.Alignment.LEADING, sidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(whiteStatusPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(yourName))
                            .addGroup(GroupLayout.Alignment.LEADING, sidePanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(firstButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(secondButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addComponent(thirdButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(sidePanelLayout.createSequentialGroup()
                                .addComponent(blackStatusPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(theirName))
                            .addComponent(flipBoardButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addComponent(theirTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(theirName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blackStatusPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flipBoardButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameHistoryPane, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rematchButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newGameButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(thirdButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(secondButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(firstButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(whiteStatusPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(yourName))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yourTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(203, 203, 203), 2));
        infoPanel.setPreferredSize(new Dimension(226, 99));

        middleSeparator.setBackground(new Color(203, 203, 203));
        middleSeparator.setForeground(new Color(203, 203, 203));

        blackName.setFont(new Font("Verdana", 0, 11)); // NOI18N
        blackName.setText("Anonymous");

        whiteName.setFont(new Font("Verdana", 0, 11)); // NOI18N
        whiteName.setText("Anonymous");

        playingStatus.setFont(new Font("Verdana", 0, 11)); // NOI18N
        playingStatus.setText("Playing right now");

        gameInfo.setFont(new Font("Verdana", 0, 11)); // NOI18N
        gameInfo.setText("1+0 • BULLET • CASUAL");

        gameResult.setFont(new Font("Verdana", 0, 11)); // NOI18N
        gameResult.setText("Time out • White is victorious");

        whiteOrBlackPanel.setPreferredSize(new Dimension(24, 36));

        GroupLayout whiteOrBlackPanelLayout = new GroupLayout(whiteOrBlackPanel);
        whiteOrBlackPanel.setLayout(whiteOrBlackPanelLayout);
        whiteOrBlackPanelLayout.setHorizontalGroup(
            whiteOrBlackPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        whiteOrBlackPanelLayout.setVerticalGroup(
            whiteOrBlackPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        GroupLayout infoPanelLayout = new GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(middleSeparator))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(whiteOrBlackPanel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(whiteName)
                            .addComponent(blackName)
                            .addComponent(gameResult))
                        .addGap(76, 76, 76)))
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(speedPictureLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(gameInfo)
                    .addComponent(playingStatus))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(gameInfo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playingStatus))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(speedPictureLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(middleSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(whiteName)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blackName)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gameResult))
                    .addComponent(whiteOrBlackPanel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        title.setFont(new Font("Tahoma", 0, 40)); // NOI18N
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("jedchess");

        blackAdvantagePane.setPreferredSize(new Dimension(235, 25));

        GroupLayout blackAdvantagePaneLayout = new GroupLayout(blackAdvantagePane);
        blackAdvantagePane.setLayout(blackAdvantagePaneLayout);
        blackAdvantagePaneLayout.setHorizontalGroup(
            blackAdvantagePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        blackAdvantagePaneLayout.setVerticalGroup(
            blackAdvantagePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        whiteAdvantagePane.setPreferredSize(new Dimension(235, 25));

        GroupLayout whiteAdvantagePaneLayout = new GroupLayout(whiteAdvantagePane);
        whiteAdvantagePane.setLayout(whiteAdvantagePaneLayout);
        whiteAdvantagePaneLayout.setHorizontalGroup(
            whiteAdvantagePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        whiteAdvantagePaneLayout.setVerticalGroup(
            whiteAdvantagePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        chatPanel.setBorder(BorderFactory.createLineBorder(new Color(203, 203, 203), 2));

        chatCheckBox.setFont(new Font("Verdana", 0, 11)); // NOI18N
        chatCheckBox.setSelected(true);
        chatCheckBox.setText("Chat room");
        chatCheckBox.setToolTipText("");
        chatCheckBox.addActionListener(this::chatToggled);

        jSeparator7.setBackground(new Color(203, 203, 203));
        jSeparator7.setForeground(new Color(203, 203, 203));

        textPaneWrapScrollPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textPaneWrapScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        chatHistoryPane.setBackground(new Color(240, 240, 240));
        chatHistoryPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textPaneWrapScrollPane.setViewportView(chatHistoryPane);

        chatTextField.setBackground(new Color(234, 234, 227));
        chatTextField.setText("  Login to chat");
        chatTextField.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jSeparator8.setBackground(new Color(203, 203, 203));
        jSeparator8.setForeground(new Color(203, 203, 203));

        GroupLayout chatPanelLayout = new GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(textPaneWrapScrollPane)
            .addComponent(jSeparator8)
            .addComponent(chatTextField,GroupLayout.Alignment.TRAILING)
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPaneWrapScrollPane)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chessPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(blackAdvantagePane, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(whiteAdvantagePane, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoPanel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(blackAdvantagePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whiteAdvantagePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(chessPanel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        
        chessPanel.start();
    }// </editor-fold>

    /**
     * Flips the board
     * @param evt the ActionEvent that triggered this event
     */
    private void flipBoard(ActionEvent evt) {
        chessPanel.flipBoard();
    }

    /**
     * Performs the action when the first button is pressed
     * @param evt the ActionEvent that triggered this event
     */
    private void firstButtonPressed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * Performs the action when the second button is pressed
     * @param evt the ActionEvent that triggered this event
     */
    private void secondButtonPressed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * Performs the action when the third button is pressed
     * @param evt the ActionEvent that triggered this event
     */
    private void thirdButtonPressed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * Notifies the server for a rematch
     * @param evt the ActionEvent that triggered this event
     */
    private void rematchButtonPressed(ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    /**
     * Toggles the chat
     * @param evt the ActionEvent that triggered this event
     */
    private void chatToggled(ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    /**
     * Runs this ChessFrame and creates a new instance
     * @return A ChessFrame
     */
    public static ChessFrame run() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels=UIManager.getInstalledLookAndFeels();
            for (UIManager.LookAndFeelInfo installedLookAndFeel : installedLookAndFeels) {
                if ("Nimbus".equals(installedLookAndFeel.getName())) {
                    UIManager.setLookAndFeel(installedLookAndFeel.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChessFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        ChessFrame output = new ChessFrame();
        
        /* Create and display the form */
        EventQueue.invokeLater(() -> {
            output.setResizable(false);
            output.setVisible(true);
        });
        
        return output;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Variables">
    // Variables declaration - do not modify
    private JPanel blackAdvantagePane;
    private JLabel blackName;
    private JPanel blackStatusPanel;
    private JCheckBox chatCheckBox;
    private JTextPane chatHistoryPane;
    private JPanel chatPanel;
    private JTextField chatTextField;
    private ChessPanel chessPanel;
    private JButton firstButton;
    private JToggleButton flipBoardButton;
    private JScrollPane gameHistoryPane;
    private JLabel gameInfo;
    private JPanel gameOutcomePanel;
    private JLabel gameResult;
    private JPanel infoPanel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    private JSeparator jSeparator5;
    private JSeparator jSeparator6;
    private JSeparator jSeparator7;
    private JSeparator jSeparator8;
    private JSeparator middleSeparator;
    private JTextArea moveTextArea;
    private JButton newGameButton;
    private JLabel outcomeLabel4;
    private JLabel playingStatus;
    private JButton rematchButton;
    private JPanel scrollContentPane;
    private JButton secondButton;
    private JPanel sidePanel;
    private JLabel speedPictureLabel;
    private JScrollPane textAreaWrapPane;
    private JScrollPane textPaneWrapScrollPane;
    private JLabel theirName;
    private JLabel theirTime;
    private JButton thirdButton;
    private JLabel title;
    private JPanel whiteAdvantagePane;
    private JLabel whiteName;
    private JPanel whiteOrBlackPanel;
    private JPanel whiteStatusPanel;
    private JLabel whoWinsLabel4;
    private JLabel whyWinLabel4;
    private JLabel yourName;
    private JLabel yourTime;
    // End of variables declaration
    //</editor-fold>
}
