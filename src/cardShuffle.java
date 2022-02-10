import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class cardShuffle extends JFrame
        implements ActionListener {

    private static String cardSuits[] = {"clubs", "diamonds", "hearts", "spades"}; // array of suits
    private static ArrayList<String> cardNames =  new ArrayList<String>() ; // arraylist of file names of card_icons
    private JPanel cardLayout = new JPanel();    // where all the cards will be placed

    private boolean firstLoad = true;

    // buttons for user
    private JButton resetButton;
    private JButton shuffleButton;
    private JButton quitButton;

    // JPanel for buttons
    private JPanel buttonLayout;

    // method to sort cards into original order.
    public void sortCards()
    {
        System.out.println("Resetting to normal layout.");
        // iterate through entire deck and create button and icon components
        // then add to the JPanel holding cards (cardLayout)
        for (int j = 0 ; j < 4 ; j++) {
            for (int i = 0; i < 13; i++) {
                JButton button = new JButton();
                if (firstLoad) {
                    cardNames.add("cardIcons/" + (i + 2) + "_of_" + cardSuits[j] + "_icon.png");
                }
                ImageIcon icon = new ImageIcon("cardIcons/" + (i + 2) + "_of_" + cardSuits[j] + "_icon.png");
                icon.setDescription("_of_" + cardSuits[j] + "_icon.png");
                // add Icon to button
                button.setIcon(icon);

                cardLayout.add(button);
            }
        }
        firstLoad = false;
    }

    // method to shuffle cards into random order
    public void shuffle()
    {
        System.out.println("Shuffling Cards..");
        ArrayList<String> cardNames_copy = new ArrayList<String>(cardNames);
        while (cardNames_copy.size() > 0) {
            JButton button = new JButton();
            int randomCard = (int) (Math.random() * cardNames_copy.size());
            ImageIcon icon = new ImageIcon(cardNames_copy.get(randomCard));
            button.setIcon(icon);
            cardNames_copy.remove(randomCard);
            cardLayout.add(button);
        }
    }


    public cardShuffle() {
        super("Cards");
        quitButton = new JButton("Quit");
        resetButton = new JButton("Reset");
        shuffleButton = new JButton("Shuffle");

        // add event listeners
        quitButton.addActionListener(this);
        resetButton.addActionListener(this);
        shuffleButton.addActionListener(this);

        // add buttons to button JPanel;
        buttonLayout = new JPanel();
        buttonLayout.add(resetButton);
        buttonLayout.add(shuffleButton);
        buttonLayout.add(quitButton);

        cardLayout.setLayout( new GridLayout( 4, 13, 0, 5 ) );

        // Sort cards into natural order
        sortCards();

        // get the content pane, onto which everything is eventually added
        Container c = getContentPane();

        // Add panels to the container
        c.add(cardLayout, BorderLayout.NORTH);
        c.add(buttonLayout);

        setSize(1000, 1000);
        setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {
        cardLayout.removeAll();
        cardLayout.revalidate();
        cardLayout.repaint();
        // check which button is being clicked.
        if (e.getSource() == resetButton){ // reset
            sortCards();
        }
        else if (e.getSource() == quitButton){ // quit
            System.exit(0);
        }
        else{ // shuffle cards
            shuffle();
        }
    }

    public static void main(String args[])
    {
        cardShuffle M = new cardShuffle();
        M.addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent e)
                    {
                        System.exit(0);
                    }
                }
        );
    }
}
