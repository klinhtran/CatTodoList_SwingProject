package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static sound.PlaySound.playASound;


public class Frame extends JFrame implements ActionListener, KeyListener {
    private static final String FILENAME = "meow.wav";
    private static final Integer FRAMEWIDTH = 400;
    private static final Integer FRAMEHEIGHT = 400;
    private static final Integer PANELWIDTH = 100;
    private static final Integer PANELHEIGHT = 100;

    private JTextField field;
    private JPanel toBuyPanel;
    private JPanel alreadyBoughtPanel;
    private JPanel textBoxPlusButton;


    public Frame(){
        super("Your Personal Grocery List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));


        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JButton btn = new JButton("Add my item");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere



        field = new JTextField(5);

        field.addKeyListener(this);

        this.setFocusable(true);
        this.requestFocusInWindow();


        textBoxPlusButton = new JPanel(new FlowLayout());
        textBoxPlusButton.add(field);
        textBoxPlusButton.add(btn);

        toBuyPanel = new JPanel(new GridLayout(0, 1));
        toBuyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        toBuyPanel.setBackground(Color.pink);
        toBuyPanel.setPreferredSize(new Dimension(PANELWIDTH,PANELHEIGHT));
        JLabel tobuy = new JLabel();
        tobuy.setText("To Buy List");
        tobuy.setFont(tobuy.getFont().deriveFont(Font.BOLD, 14f));
        toBuyPanel.add(tobuy);


        alreadyBoughtPanel = new JPanel(new GridLayout(0,1));
        alreadyBoughtPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        alreadyBoughtPanel.setBackground(Color.lightGray);
        alreadyBoughtPanel.setPreferredSize(new Dimension(PANELWIDTH,PANELHEIGHT));
        JLabel bought = new JLabel();
        bought.setText("Already Bought List");
        bought.setFont(bought.getFont().deriveFont(Font.BOLD, 14f));
        alreadyBoughtPanel.add(bought);



        add(textBoxPlusButton);
        add(toBuyPanel);
        add(alreadyBoughtPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("myButton")) {

            playASound(FILENAME);

            String name = field.getText();

            field.setText("");
            JCheckBox checkBox = new JCheckBox(name);

            toBuyPanel.add(name,checkBox);
            toBuyPanel.revalidate();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    alreadyBoughtPanel.add(new JLabel(name));
                    toBuyPanel.remove(checkBox);
                    playASound(FILENAME);

                    toBuyPanel.revalidate();
                    alreadyBoughtPanel.revalidate();
                }
            }); //this being the frame because we're inside Frame class



        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ENTER){

            playASound(FILENAME);

            String name = field.getText();

            field.setText("");
            JCheckBox checkBox = new JCheckBox(name);

            toBuyPanel.add(name,checkBox);
            toBuyPanel.revalidate();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    alreadyBoughtPanel.add(new JLabel(name));
                    toBuyPanel.remove(checkBox);
                    playASound(FILENAME);

                    toBuyPanel.revalidate();
                    alreadyBoughtPanel.revalidate();
                }
            });


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) throws IOException {
        new CatPicture();
        new Frame();
    }

}
