import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {

    private int randomNumber;
    private JTextField textField;
    private JLabel hintLabel;
    private JLabel feedbackLabel;
    private int attempts;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Prevent resizing

        // Set background gradient
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                Color color1 = new Color(255, 239, 213); // Peach
                Color color2 = new Color(135, 206, 250); // Sky Blue
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        randomNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;

        JLabel titleLabel = new JLabel("Guess the Number (1-100)");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK); // Set label color
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setOpaque(false); // Make panel transparent
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(textField, BorderLayout.NORTH);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        guessButton.setBackground(new Color(0, 191, 255)); // Secondary color
        guessButton.setForeground(Color.WHITE);
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setFocusPainted(false); // Remove focus border
        guessButton.setBorderPainted(false); // Remove border
        guessButton.setOpaque(true); // Make button opaque for background color to show
        guessButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        inputPanel.add(guessButton, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.CENTER);

        JPanel feedbackPanel = new JPanel(new GridLayout(2, 1));
        feedbackPanel.setOpaque(false); // Make panel transparent
        feedbackPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // Add padding

        feedbackLabel = new JLabel("Enter your guess and press Guess");
        feedbackLabel.setHorizontalAlignment(JLabel.CENTER);
        feedbackLabel.setForeground(Color.BLACK); // Set label color
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        feedbackPanel.add(feedbackLabel);

        hintLabel = new JLabel("");
        hintLabel.setHorizontalAlignment(JLabel.CENTER);
        hintLabel.setForeground(Color.BLACK); // Set label color
        hintLabel.setFont(new Font("Arial", Font.BOLD, 16));
        feedbackPanel.add(hintLabel);

        add(feedbackPanel, BorderLayout.SOUTH);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(textField.getText());
                attempts++;
                if (guess < 1 || guess > 100) {
                    JOptionPane.showMessageDialog(NumberGuessingGame.this, "Please enter a number between 1 and 100.");
                } else {
                    if (guess == randomNumber) {
                        JOptionPane.showMessageDialog(NumberGuessingGame.this, "Congratulations! You guessed the correct number in " + attempts + " attempts.");
                        resetGame();
                    } else if (guess < randomNumber) {
                        hintLabel.setText("Too low! Try again.");
                    } else {
                        hintLabel.setText("Too high! Try again.");
                    }
                    textField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(NumberGuessingGame.this, "Please enter a valid number.");
            }
        }
    }

    private void resetGame() {
        randomNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;
        hintLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}
