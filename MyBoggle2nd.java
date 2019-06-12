import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class MyBoggle2nd extends JFrame implements ActionListener {

 static String p1Name = "Player One";
 static String p2Name = "Player Two";
 public static boolean soloGame = false;
 static ArrayList<String> pWords = new ArrayList<String>();

 JFrame frame = new JFrame(); // window
 JLabel pane;                 // main menu panel

 JTextField soloName;         // name of the solo player
 JTextField multiName1;       // names of the multiple players
 JTextField multiName2;
 
 JButton solo;                // button to start solo game
 JButton multiplayer;         // button to start multiplayer game
 JButton instructions;        // button to display instructions       

 JLabel logo;                 // label to display boggle logo

 JPanel temp;                 // panels for main menu (formatting)
 JPanel temp2;


 // multiplayer screen
 JPanel pauseScreen;          // panel to display the paused screen
 JLabel panMPause;            // label on the paused screen

 JLabel panM;                 // main screen panel for multiplayer
 JPanel top;                  // holds main menu and pause buttons
 JPanel left;                 // store information for p1
 JPanel Midmid;               // holds boggle grid
 JPanel topMid;               // holds timer and start button
 JPanel mid;                  // panel on the back layer
 JPanel botMid;               // holds textfield and enter button
 JPanel right;                // store information for p2
 JPanel spacer1;              // spacing for formatting
 JPanel spacer2;              // spacing for formatting

 // top panel
 JButton settings;            // settings button
 JButton pause;               // pause button
 JButton restart;             // restart button
 JButton mainMenu;            // main menu button
 JLabel mode;                 // displays whether the player(s) are in single or multiplayer mode

 // left panel
 JLabel name;                 // displays name of the first player
 JLabel wordlistTitle;        // has the title of the area that stores the guessed words
 JScrollPane wordlist;        // contains the textArea and allows for scrolling
 JLabel score;                // displays the score
 JTextArea textArea1;         // text area that stores the words

 // mid panel (probably box layout and grid layout
 JLabel timer;                // displays the timer
 private Timer15 tt;          // initializes the timer object
 JButton start;               // start button
 JButton Enter;               // enter button

 // grid of JLabels for the boggle grid of letter
 JLabel[] dice = new JLabel[25]; 

 // bunch of JLabels
 JLabel playerTurnLabel;

 // bottom mid panel
 JTextField enterWord;        // textfield to enter guessed words from the player

 // rightpanel
 JLabel name2;                // name for the second player
 JLabel wordlistTitle2;       // title of the wordlist for the second player
 JScrollPane wordlist2;       // scroll pane to allow scrolling of the text area
 JLabel score2;               // displays score of the second player
 JTextArea textArea2;         // displays guessed words of the second player

 private static validateWord Derrick;              // declare object that contains the methods to validate the words
 private static int[] redValues = new int[25];     // these three arrays store the rgb value of the gradient tiles on the board
 private static int[] greenValues = new int[25];
 private static int[] blueValues = new int[25];


 GridBagConstraints c = new GridBagConstraints();

 public MyBoggle2nd() throws IOException, InterruptedException {
  // initialize main window
  setTitle("MyBoggle!");
  setLocation(100, 100);
  setSize(1355, 820);
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 

  setLayout(new FlowLayout());

  // multi screen
  panMPause = new JLabel(new ImageIcon("yellowBg.jpg"));
  panMPause.setLayout(new FlowLayout(FlowLayout.CENTER));
  panMPause.setVisible(false);
  add(panMPause);

  panM = new JLabel(new ImageIcon("yellowBg.jpg"));
  panM.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
  add(panM);

  // pause menu screen
  pauseScreen = new JPanel();
  pauseScreen.setPreferredSize(new Dimension(500, 600));
  pauseScreen.setBackground(Color.DARK_GRAY);
  pauseScreen.setVisible(false);
  panMPause.add(pauseScreen);

  // top panel of the game screen
  top = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
  top.setPreferredSize(new Dimension(1360, 70));
  top.setBackground(new Color(224, 74, 27, 255));
  panM.add(top);

  // initialize main menu button
  mainMenu = new JButton("Main Menu");
  mainMenu.addActionListener(this);
  mainMenu.setPreferredSize(new Dimension(150, 50));
  top.add(mainMenu);

  // initialize settings button
  settings = new JButton("Settings");
  settings.setPreferredSize(new Dimension(150, 50));
  top.add(settings);

  // initialize pause button
  pause = new JButton("Pause");
  pause.addActionListener(this);
  pause.setPreferredSize(new Dimension(150, 50));
  top.add(pause);

  // initialize restart button
  restart = new JButton("Restart");
  restart.setPreferredSize(new Dimension(150, 50));
  top.add(restart);

  // spacer1 = new JPanel();
  // spacer1.setPreferredSize(new Dimension(100, 600));
  // panM.add(spacer1);

  // left panel
  left = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
  left.setPreferredSize(new Dimension(260, 700));
  left.setBackground(new Color(0, 0, 0, 50));
  panM.add(left);

  // initialize names of the players
  name = new JLabel(p1Name);
  name.setPreferredSize(new Dimension(200, 30));
  name.setForeground(Color.WHITE);
  name.setFont(new Font("Helvetica", Font.PLAIN, 30));
  left.add(name);

  // initialize the title of the text area
  wordlistTitle = new JLabel("Word list:");
  wordlistTitle.setPreferredSize(new Dimension(200, 20));
  wordlistTitle.setForeground(Color.WHITE);
  wordlistTitle.setFont(new Font("Helvetica", Font.ITALIC, 20));
  left.add(wordlistTitle);

  // initialize text area where guesses are stored
  textArea1 = new JTextArea("");
  textArea1.setEditable(false);

  // initialize the scroll pane where the text area is
  wordlist = new JScrollPane(textArea1);
  wordlist.setPreferredSize(new Dimension(240, 400));
  left.add(wordlist);

  // initialize the score label
  score = new JLabel("Score");
  score.setPreferredSize(new Dimension(200, 60));
  score.setForeground(Color.WHITE);
  score.setFont(new Font("Helvetica", Font.BOLD, 30));
  left.add(score);

  // whole mid panel
  Midmid = new JPanel(new FlowLayout(FlowLayout.CENTER));
  Midmid.setPreferredSize(new Dimension(600, 700));
  Midmid.setBackground(new Color(0, 0, 0, 50));
  panM.add(Midmid);

  // topMid panel
  topMid = new JPanel(new FlowLayout(FlowLayout.CENTER));
  topMid.setOpaque(false);
  Midmid.add(topMid);

  // initialize the display of who's turn it is
  playerTurnLabel = new JLabel(p1Name + "'s Turn");
  playerTurnLabel.setForeground(Color.WHITE);
  playerTurnLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
  topMid.add(playerTurnLabel);

  // initialize start button
  start = new JButton("START");
  start.addActionListener(this);
  start.setPreferredSize(new Dimension(130, 50));
  start.setFont(new Font("Helvetica", Font.BOLD, 20));
  topMid.add(start);

  // initialize timer
  tt = new Timer15();
  tt.setPreferredSize(new Dimension(180, 50));
  topMid.add(tt);

  // mid panel with the grid of dice
  mid = new JPanel(new GridLayout(5, 5));
  mid.setPreferredSize(new Dimension(400, 400));
  mid.setBackground(new Color(255, 255, 255, 255));
  Midmid.add(mid);

  // set start colours of the gradient
  int r = 230; 
  int g = 106; 
  int b = 92;

  // initialize the tiles on the board, colors and labels
  for (int i = 0; i < 25; i++) {
   dice[i] = new JLabel("");
   dice[i].setHorizontalAlignment(SwingConstants.CENTER);
   Border raisedbevel = BorderFactory.createRaisedBevelBorder();
   dice[i].setBorder(raisedbevel);
   dice[i].setForeground(Color.WHITE);
   dice[i].setBackground(new Color(r, g, b, 255));
   redValues[i] = r;
   greenValues[i] = g;
   blueValues[i] = b;
   dice[i].setFont(new Font("Verdana", Font.PLAIN, 40));
   dice[i].setOpaque(true);
   mid.add(dice[i]);
   r -= 5;  // incrementing colours to create gradient
   g -= 3;
   b += 5;
  }

  // bottom mid panel
  botMid = new JPanel(new FlowLayout(FlowLayout.CENTER));
  botMid.setOpaque(false);

  // initialize the textfield for the player to enter their guess
  enterWord = new JTextField();
  enterWord.addActionListener(this);
  enterWord.setPreferredSize(new Dimension(400, 50));
  botMid.add(enterWord);

  // initialize enter button
  Enter = new JButton("Enter");
  Enter.setPreferredSize(new Dimension(100, 50));
  Enter.addActionListener(this);
  Enter.setFont(new Font("Helvetica", Font.BOLD, 20));
  botMid.add(Enter);

  // add panel to the frame
  Midmid.add(botMid);

  // initialize panel
  right = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
  right.setPreferredSize(new Dimension(260, 700));
  right.setBackground(new Color(0, 0, 0, 50));
  panM.add(right);

  // initialize name for the second player
  name2 = new JLabel(p2Name);
  name2.setPreferredSize(new Dimension(200, 30));
  name2.setForeground(Color.WHITE);
  name2.setFont(new Font("Helvetica", Font.PLAIN, 30));
  right.add(name2);

  // initialize the title of the word list for the second player
  wordlistTitle2 = new JLabel("Word list:");
  wordlistTitle2.setPreferredSize(new Dimension(200, 20));
  wordlistTitle2.setForeground(Color.WHITE);
  wordlistTitle2.setFont(new Font("Helvetica", Font.ITALIC, 20));
  right.add(wordlistTitle2);

  // initialize the area for the guesses to be stored
  textArea2 = new JTextArea("");
  textArea2.setEditable(false);

  // initialize the scroll pane for the text area to be displayed
  wordlist2 = new JScrollPane(textArea2);
  wordlist2.setPreferredSize(new Dimension(240, 400));
  right.add(wordlist2);

  // initialize the label has the text "Score"
  score2 = new JLabel(" Score");
  score2.setPreferredSize(new Dimension(240, 60));
  score2.setForeground(Color.WHITE);
  score2.setBackground(new Color(240, 100, 40, 255));
  score2.setOpaque(true);
  score2.setFont(new Font("Helvetica", Font.BOLD, 30));
  right.add(score2);

  // set the main game screen invisible
  panM.setVisible(false);

  // intro screen
  pane = new JLabel(new ImageIcon("bg2.jpg"));
  pane.setLayout(new GridBagLayout());

  // top panel behind boggle logo
  temp2 = new JPanel(); 
  temp2.setBackground(new Color(80, 0, 50, 50));
  temp2.setPreferredSize(new Dimension(650, 142));
  c.gridx = 0;
  c.gridy = 0;
  c.gridheight = 1;
  c.gridwidth = 8;
  c.insets = new Insets(0, 10, 10, 10);
  pane.add(temp2, c);

  // initialize the logo into the frame
  logo = new JLabel(new ImageIcon("boggle_logo.png"));
  temp2.add(logo);

  // initialize instructions button
  instructions = new JButton("Instructions");
  instructions.addActionListener(this);
  instructions.setFont(new Font("Helvetica", Font.BOLD, 20));
  c.fill = GridBagConstraints.HORIZONTAL;
  c.gridx = 1;
  c.gridy = 1;
  c.gridwidth = 4;
  c.gridheight = 1;
  c.ipadx = 10;
  c.ipady = 10;
  c.insets = new Insets(10, 120, 20, 120);
  pane.add(instructions, c);

  // initialize the panel for formatting on the main menu
  temp = new JPanel();
  temp.setBackground(new Color(100, 50, 0, 0));
  temp.setPreferredSize(new Dimension(50, 100));
  c.gridx = 2;
  c.gridy = 2;
  c.gridheight = 2;
  c.gridwidth = 1;
  c.insets = new Insets(10, 10, 10, 10);
  pane.add(temp, c);

  // initialize the solo button
  solo = new JButton("SOLO");
  solo.addActionListener(this);
  solo.setFont(new Font("Helvetica", Font.BOLD, 20));
  solo.setPreferredSize(new Dimension(250, 50));
  c.fill = GridBagConstraints.HORIZONTAL;
  c.gridx = 0;
  c.gridy = 3;
  c.gridwidth = 2;
  c.gridheight = 1;
  c.ipadx = 10;
  c.ipady = 10;
  c.insets = new Insets(10, 120, 10, 20);
  pane.add(solo, c);

  // initialize text field to enter the name of the player
  soloName = new JTextField("Enter your name");
  soloName.setFont(new Font("Helvetica", Font.BOLD, 15));
  soloName.setPreferredSize(new Dimension(250, 40));
  c.fill = GridBagConstraints.HORIZONTAL;
  c.gridx = 0;
  c.gridy = 2;
  c.gridwidth = 2;
  c.ipadx = 10;
  c.ipady = 10;
  c.insets = new Insets(10, 120, 10, 20);
  pane.add(soloName, c);

  // initialize multiplayer button
  multiplayer = new JButton("MULTIPLAYER");
  multiplayer.addActionListener(this);
  multiplayer.setFont(new Font("Helvetica", Font.BOLD, 20));
  multiplayer.setPreferredSize(new Dimension(250, 50));
  c.fill = GridBagConstraints.HORIZONTAL;
  c.gridx = 3;
  c.gridy = 3;
  c.ipadx = 10;
  c.ipady = 10;
  c.gridwidth = 4;
  c.insets = new Insets(10, 20, 10, 120);
  pane.add(multiplayer, c);

  multiName1 = new JTextField("Enter your name");
  multiName1.setFont(new Font("Helvetica", Font.BOLD, 15));
  multiName1.setPreferredSize(new Dimension(120, 40));
  // c.fill = GridBagConstraints.HORIZONTAL;
  c.gridx = 3;
  c.gridy = 2;
  c.gridwidth = 1;
  c.ipadx = 10;
  c.ipady = 10;
  c.insets = new Insets(10, 20, 10, 5);
  pane.add(multiName1, c);

  multiName2 = new JTextField("Enter your name");
  multiName2.setFont(new Font("Helvetica", Font.BOLD, 15));
  multiName2.setPreferredSize(new Dimension(120, 40));
  // c.fill = GridBagConstraints.HORIZONTAL;
  c.gridx = 4;
  c.gridy = 2;
  c.gridwidth = 1;
  c.ipadx = 10;
  c.ipady = 10;
  c.insets = new Insets(10, 5, 10, 120);
  pane.add(multiName2, c);

  add(pane);
  // pack();
  setVisible(true);
 }

 public void delay() throws InterruptedException {
  Thread.sleep(500);
 }

 public void actionPerformed(ActionEvent e) {
  String command = e.getActionCommand();

  if (command.equals("Instructions")) {
   // System.out.println("gay");
   JOptionPane.showMessageDialog(this,
     "The game of Boggle is a word puzzle game. There is a 5x5 grid of random letters (or dice), \n"
       + "and you need to find possible words that can be made from the 25 letters. You can only \n"
       + "choose letters that are connected in all 8 directions. Have Fun!",
     "Instructions", JOptionPane.INFORMATION_MESSAGE);

  } else if (command.equals("MULTIPLAYER")) {
   soloGame = false;
  // tt.start(soloGame);
  // tt.setSeconds(15);
   panM.add(right);
   pane.setVisible(false);
   panM.setVisible(true);

  } else if (command.equals("SOLO")) {
   soloGame = true;
  // tt.start(soloGame);
  // tt.setSeconds(180);
   pane.setVisible(false);
   panM.remove(right);
   panM.setVisible(true);

  } else if (command.equals("Main Menu")) {
   pane.setVisible(true);
   panM.setVisible(false);
   tt.stop();
  } else if (command.equals("START")) {
   generateBoard();
   // System.out.println(diceArray[5].getOrientation());
   for (int i = 0; i < 25; i++) {
    String temp = String.valueOf(diceArray[i].getOrientation());
    System.out.print(temp + " letter\n");
    dice[i].setText(temp);
   }

   tt.start(soloGame);

  } else if (command.equals("Enter")) {
  // revalidate();
   tt.stop();
   System.out.println("time: " + tt.getTime());

   String guessWord = enterWord.getText().toUpperCase();

   if (pWords.contains(guessWord)) {
    JOptionPane.showMessageDialog(this, "The word is invalid.", "Word", JOptionPane.WARNING_MESSAGE);
    
   } else {
    pWords.add(guessWord);
    // validate
    boolean valid = false;
    String[] board = new String[25];
    for (int i = 0; i < 25; i++) {
     board[i] = "" + diceArray[i].getOrientation();
    }

    Derrick = new validateWord(guessWord, board, startLetterIndexes, words, 3);

    Color[] colors = new Color[guessWord.length()]; // store original colours before highlighting the
                // letters
    for (int i = 0; i < guessWord.length(); i++) {
     colors[i] = UIManager.getColor(dice[i]);
     System.out.println(colors[i]);
    }

    int[] posLetters;
    if (Derrick.validateDicLen()) {
     posLetters = Derrick.validateBoard();
     valid = true;
     Border raisedbevel = BorderFactory.createRaisedBevelBorder();
     Border loweredbevel = BorderFactory.createLoweredBevelBorder();

     for (int i = 0; i < guessWord.length(); i++) {
      for (int j = 0; j < guessWord.length(); j++) {
       dice[posLetters[i]].setBackground(new Color(42, 15, 76));
       dice[posLetters[i]].setBorder(loweredbevel);
      }

     }
     new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       for (int i = 0; i < guessWord.length(); i++) {
        dice[posLetters[i]].setBackground(new Color(redValues[posLetters[i]],
          greenValues[posLetters[i]], blueValues[posLetters[i]]));
        dice[posLetters[i]].setBorder(raisedbevel);
       }
      }
     }).start();

    } else {
     valid = false;
     JOptionPane.showMessageDialog(this, "The word is invalid.", "Word", JOptionPane.WARNING_MESSAGE);
    }

    // check if it already on the list
    revalidate();
    if (!soloGame) {
     if (playerTurn == 0) {
      if (valid) {
       textArea1.append(guessWord + "\n");
       playerTurnLabel.setText(p2Name + "'s Turn");
       p1Score += guessWord.length();
       score.setText("Score: " + p1Score);
       playerTurn = 1;
      }
     } else {
      if (valid) {
       textArea2.append(guessWord + "\n");
       playerTurnLabel.setText(p1Name + "'s Turn");
       p2Score += guessWord.length();
       score2.setText("Score: " + p2Score);
       playerTurn = 0;
      }
     }
    } else {
     if (valid)
      textArea1.append(guessWord + "\n");
     playerTurnLabel.setText(p2Name + "'s Turn");
     p1Score += guessWord.length();
     score.setText("Score: " + p1Score);
    }
   // tt.setSeconds(-1);
    setSize(1355, 821);

    // setLocation(10, 100);
    revalidate();
    tt.start(soloGame);
    setSize(1355, 820);
   }
  } else if (command.equals("Pause")) {
   tt.stop();
   panMPause.setVisible(true);
   pauseScreen.setVisible(true);

   // has continue and settings
  }
 }

 public void generateBoard() {
  Random rand = new Random();

  BoggleDice[] scrambledDice = new BoggleDice[25];
  ArrayList<Integer> testArray = new ArrayList<Integer>();

  int test;

  for (int i = 0; i < 25; i++) {
   test = rand.nextInt(25);
   if (!testArray.contains(test)) {
    testArray.add(test);
    System.out.println(test);
    scrambledDice[i] = diceArray[test];
   } else {
    i--;
   }
  }
  diceArray = scrambledDice;

  for (int i = 0; i < 25; i++) {
   diceArray[i].rollDie();
  }
 }

 private int p1Score = 0;
 private int p2Score = 0;

 // score and word list
 public void scoreAndWordlist(String word, int player) {
  if (player == 1) {
   p1Score += word.length();
   wordArrayList1.add(word);
  } else if (player == 2) {
   p2Score += word.length();
   wordArrayList2.add(word);
  }
 }

 /**
  * Operations done after the game has ended either because reset was clicked or
  * the time limit ran out
  * 
  * @param wordsFound                        --> the words found in the user in
  *                                          the text box
  * @param wordsComputerFound                --> words found recursively by the
  *                                          AI (found in this method)
  * @param labelThingBottomCongratulationGay --> he label at the bottom that
  *                                          prints congratulations once the game
  *                                          has ended
  */

 // used for solo, when the game ends
 private String wordsFound[] = new String[100];
 private String wordsComputerFound[] = new String[100];
 private String labelThingBottomCongratulationGay = "";
 private int points;

 public void endGame() {
  String empty[] = new String[wordsFound.length];
  wordsFound = empty;
  wordsComputerFound = empty;
  labelThingBottomCongratulationGay = "Thank you for playing Boggle!!!!";
  if (points > 25)
   labelThingBottomCongratulationGay += " You are amazing at Boggle!";
  else if (points > 10 && points <= 25)
   labelThingBottomCongratulationGay += " You are decent at Boggle.";
  else
   labelThingBottomCongratulationGay += " You're pretty bad at this game";
 }

 public static String[] words = new String[127000];
 public static int[] startLetterIndexes = new int[26];
 public static long counter;
// private static int[] scrambledDice;
 public static BoggleDice[] diceArray = new BoggleDice[25];// = {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12,
                // d13, d14, d15, d16, d17, d18, d19, d20, d21, d22,
                // d23, d24, d25};

 public static String[] diceLetters = { "AAAGRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM", "AEEGMU", "AEGMNN",
   "AGIRSY", "BJKQXZ", "CCNSTW", "CEIILT", "CEIMPT", "CEIPST", "DDLNOR", "DHHLOR", "DHHNOT", "DHLNOR",
   "EIIITT", "EMOTTT", "ENSSSU", "FIPRSY", "GORRVW", "HIPRRY", "NOOTUW", "OOOTTU" };

 private static ArrayList<String> wordArrayList1 = new ArrayList<String>();
 private static ArrayList<String> wordArrayList2 = new ArrayList<String>();

 private int playerTurn = 0;

 public static void main(String[] args) throws IOException, InterruptedException {

  for (int i = 0; i < 25; i++) {
   diceArray[i] = new BoggleDice(diceLetters[i], 0);
  }
  MyBoggle2nd frame = new MyBoggle2nd();
  /*
   * javax.swing.SwingUtilities.invokeLater(new Runnable() { public void run() {
   * createAndShowGUI(); } });
   * 
   */

  File List = new File("wordlist.txt");
  Scanner fileScanner = new Scanner(List);
  int c = 0;
  int letterCounter = 0;
  String startLetter = "B";
  while (fileScanner.hasNext()) {
   words[c] = fileScanner.next().toUpperCase();
   if (!startLetter.equals(words[c].substring(0, 1))) {
    // System.out.println(startLetter + " " + c);
    startLetterIndexes[letterCounter] = c;
    letterCounter++;
   }
   startLetter = words[c].substring(0, 1);
   c++;
  }
  fileScanner.close();

  /*
   * for(int i = 0; i < 26; i++) { System.out.println(startLetterIndexes[i]); }
   */

 }

}
