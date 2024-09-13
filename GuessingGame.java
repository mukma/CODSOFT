
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int randomNumber;
    private int attemptsLeft;
    private int score;
    private int round;

    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public GuessingGame() {
        // Initialize the game
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attemptsLeft = 10; // User has 10 attempts
        score = 0;
        round = 1;

        // Create UI components
        JLabel instructionsLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField();
        feedbackLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft);
        scoreLabel = new JLabel("Score: " + score + " | Round: " + round);
        JButton guessButton = new JButton("Submit Guess");
        JButton playAgainButton = new JButton("Play Again");

        // Add components to the frame
        add(instructionsLabel);
        add(guessField);
        add(guessButton);
        add(feedbackLabel);
        add(attemptsLabel);
        add(scoreLabel);

        // Action Listener for Guess Button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());

                    if (userGuess < 1 || userGuess > 100) {
                        feedbackLabel.setText("Please enter a number between 1 and 100.");
                    } else if (userGuess < randomNumber) {
                        feedbackLabel.setText("Too low!");
                        attemptsLeft--;
                    } else if (userGuess > randomNumber) {
                        feedbackLabel.setText("Too high!");
                        attemptsLeft--;
                    } else {
                        feedbackLabel.setText("Correct! You guessed the number.");
                        score += attemptsLeft;
                        round++;
                        playAgainButton.setVisible(true);
                        guessButton.setEnabled(false);
                    }

                    // Update attempts label
                    attemptsLabel.setText("Attempts left: " + attemptsLeft);

                    if (attemptsLeft == 0 && userGuess != randomNumber) {
                        feedbackLabel.setText("Game over! The number was " + randomNumber);
                        playAgainButton.setVisible(true);
                        guessButton.setEnabled(false);
                    }

                    // Update score label
                    scoreLabel.setText("Score: " + score + " | Round: " + round);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        // Action Listener for Play Again Button
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomNumber = random.nextInt(100) + 1;
                attemptsLeft = 10;
                guessField.setText("");
                feedbackLabel.setText("");
                guessButton.setEnabled(true);
                playAgainButton.setVisible(false);
                attemptsLabel.setText("Attempts left: " + attemptsLeft);
            }
        });

        add(playAgainButton);
        playAgainButton.setVisible(false);

        // Show the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}