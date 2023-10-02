import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

    class AgeCalculatorApp {
    private JFrame frame;
    private JPanel panel;
    private JLabel birthDateLabel;
    private JTextField birthDateTextField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public AgeCalculatorApp() {
        frame = new JFrame("Age Calculator");
        panel = new JPanel();
        birthDateLabel = new JLabel("Enter your birth date (yyyy-MM-dd): ");
        birthDateTextField = new JTextField(10);
        calculateButton = new JButton("Calculate Age");
        resultLabel = new JLabel();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAge();
            }
        });

        panel.add(birthDateLabel);
        panel.add(birthDateTextField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setVisible(true);
    }

    private void calculateAge() {
        String birthDateText = birthDateTextField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthDate = dateFormat.parse(birthDateText);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthDate);
            Calendar currentCal = Calendar.getInstance();
            
            int years = currentCal.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
            int months = currentCal.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
            int days = currentCal.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH);

            if (months < 0 || (months == 0 && days < 0)) {
                years--;
            }

            resultLabel.setText("Your age is: " + years + " years");
        } catch (ParseException e) {
            resultLabel.setText("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AgeCalculatorApp();
            }
        });
    }
}
