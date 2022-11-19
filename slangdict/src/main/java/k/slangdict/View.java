/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package k.slangdict;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author kp
 */
public class View extends JFrame {
    
    private JLabel AppName = new JLabel("SLANG DICTIONARY");
    private JButton SeeAll = new JButton("See all");
    private JButton SearchBySlang = new JButton("Search by Slang");
    private JButton SearchByDef = new JButton("Search by Definition");
    private JButton EditWord = new JButton("Edit a Word");     
    private JButton DeleteWord = new JButton("Delete a Word");
    private JButton RandomWord = new JButton("On This Day Slang Word");
    private JButton QuizSlang = new JButton("Random Slang Quiz");
    private JButton QuizDef = new JButton("Random Definition Quiz");
    private JButton Empty = new JButton("");
    View(){
        JPanel panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setTitle("20127527 - Slang Dictionary");
        this.setMinimumSize(new Dimension(300,300));
        View.setDefaultLookAndFeelDecorated(true);
        AppName.setAlignmentX(CENTER_ALIGNMENT);
        AppName.setHorizontalTextPosition(JLabel.CENTER);
        
        SeeAll.setAlignmentX(CENTER_ALIGNMENT);
        SearchBySlang.setAlignmentX(CENTER_ALIGNMENT);
        SearchByDef.setAlignmentX(CENTER_ALIGNMENT);
        EditWord.setAlignmentX(CENTER_ALIGNMENT);
        DeleteWord.setAlignmentX(CENTER_ALIGNMENT);
        RandomWord.setAlignmentX(CENTER_ALIGNMENT);
        QuizSlang.setAlignmentX(CENTER_ALIGNMENT);  
        QuizDef.setAlignmentX(CENTER_ALIGNMENT);
        // Define the panel to hold the buttons 

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(20, 50, 20, 50)));  
        panel.add(AppName);
        panel.add(SeeAll);
        panel.add(SearchBySlang);
        panel.add(SearchByDef);
        panel.add(EditWord);
        panel.add(DeleteWord);
        panel.add(RandomWord);
        panel.add(QuizSlang);
        panel.add(QuizDef);
        
        this.add(panel);
        this.pack();
    }
}
