import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculator extends JFrame implements ActionListener {
    private JTextField[] subjectMarksFields;
    private JLabel totalMarksLabel, averagePercentageLabel, gradeLabel;
    private JButton calculateButton;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Input fields for subject marks
        int numSubjects = 5; // Adjust the number of subjects as needed
        subjectMarksFields = new JTextField[numSubjects];
        JLabel[] subjectLabels = new JLabel[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + ": ");
            subjectMarksFields[i] = new JTextField(10);
            gbc.gridx = 0;
            gbc.gridy = i;
            add(subjectLabels[i], gbc);
            gbc.gridx = 1;
            add(subjectMarksFields[i], gbc);
        }

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = numSubjects + 1;
        gbc.gridwidth = 2;
        add(calculateButton, gbc);

        // Output labels
        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        gbc.gridy++;
        add(totalMarksLabel, gbc);
        gbc.gridy++;
        add(averagePercentageLabel, gbc);
        gbc.gridy++;
        add(gradeLabel, gbc);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            for (int i = 0; i < subjectMarksFields.length; i++) {
                try {
                    int marks = Integer.parseInt(subjectMarksFields[i].getText());
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(this, "Invalid marks for Subject " + (i + 1));
                        return;
                    }
                    totalMarks += marks;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers for marks.");
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / subjectMarksFields.length;
            String grade = calculateGrade(averagePercentage);

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage));
            gradeLabel.setText("Grade: " + grade);
        }
    }

    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}