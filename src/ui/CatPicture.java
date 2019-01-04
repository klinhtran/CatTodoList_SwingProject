package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import static sound.PlaySound.playASound;

public class CatPicture extends JFrame implements ActionListener {
    String[] positiveSayings = {"You Got This", "Have a Nice Day", "Hello World", "He He He",
            "Meow Meow Meow", "This Cat Loves You", "Good Luck on Your Finals Hooman", "Cuteness Overload", "Good Vibes Only",
            "Don't Worry Be Happy", "Meowy Christmas", "This Hooman is Beautiful"};
    private final static int X_COORD_PIC = 0;
    private final static int Y_COORD_PIC = 0;
    private static final Integer FRAMEWIDTH = 400;
    private static final Integer FRAMEHEIGHT = 400;
    private static final Integer PANELWIDTH = 200;
    private static final Integer PANELHEIGHT = 200;
    private static final String FILENAME = "merry.wav";
    private JPanel picpart;
    private JPanel btnpart;

    public CatPicture() throws IOException {
        super("Your Cat Picture");

        Random rand = new Random();
        int value = rand.nextInt(positiveSayings.length);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JButton btn = new JButton("Hear me meow");
        btn.setActionCommand("Meow");
        btn.addActionListener( this);




        String theURL = "https://cataas.com/cat/says/" + positiveSayings[value];

        URL url = new URL(theURL);



        setSize(FRAMEWIDTH,FRAMEHEIGHT);

        ImageIcon cat = new ImageIcon(url); // load the image to a imageIcon
        Image catpic = cat.getImage(); // transform it
        Image newcat = catpic.getScaledInstance(PANELWIDTH, PANELHEIGHT,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        cat = new ImageIcon(newcat);  // transform it back

        //setSize(cat.getIconWidth()+200, cat.getIconHeight()+200);

        JLabel catLabel = new JLabel(cat);

        picpart = new JPanel(new FlowLayout());
        picpart.add(catLabel);
        btnpart = new JPanel(new FlowLayout());
        btnpart.add(btn);

        add(picpart);
        add(btnpart);

        setLocation(X_COORD_PIC, Y_COORD_PIC); //jframe w cat pic at left edge of screen
        setVisible(true); //makes the jframe visible
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Meow")){
            playASound(FILENAME);

        }
    }
}