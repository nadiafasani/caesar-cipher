/**
 * @author Nadia Fasani
 * @version 24.04.2021
*/

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Gui extends JFrame{

    public Gui(String title){
        super(title);
        Font calibri = new Font("Calibri", Font.PLAIN, 14);
        CaesarCipher cipher = new CaesarCipher();

        JLabel inputLabel = new JLabel("Input");
        inputLabel.setBounds(20, 5, 100, 30);
        inputLabel.setFont(calibri);
        
        JTextField inputField = new JTextField();
		inputField.setBounds(20, 30, 100, 30);
        inputField.setFont(calibri);

        JLabel outputTextLabel = new JLabel("Output");
        outputTextLabel.setBounds(150, 5, 100, 30);
        outputTextLabel.setFont(calibri);

        JTextArea outputTextArea= new JTextArea();
        outputTextArea.setBounds(150, 30, 300, 300);
        outputTextArea.setFont(calibri);

        JLabel shiftLabel = new JLabel("Shift");
        shiftLabel.setBounds(20, 80, 100, 30);
        shiftLabel.setFont(calibri);

        JSlider shiftSlider = new JSlider();
        shiftSlider.setBounds(15, 100, 105, 30);
        shiftSlider.setFont(calibri);
        shiftSlider.setMinimum(0);
        shiftSlider.setMaximum(25);

        JLabel resultShiftLabel = new JLabel();
        resultShiftLabel.setBounds(20,125, 100, 30);
        resultShiftLabel.setFont(calibri);

        JRadioButton radioEncrypt = new JRadioButton("encrypt", true);
        radioEncrypt.setBounds(20,150, 100, 30);
        radioEncrypt.setFont(calibri);

        JRadioButton radioDecrypt = new JRadioButton("decrypt");
        radioDecrypt.setBounds(20,175, 100, 30);
        radioDecrypt.setFont(calibri);

        ButtonGroup group = new ButtonGroup();
        group.add(radioEncrypt);
        group.add(radioDecrypt);

        add(inputField);
        add(inputLabel);
        add(outputTextLabel);
        add(outputTextArea);
        add(shiftLabel);
        add(shiftSlider);
        add(resultShiftLabel);
        add(radioEncrypt);
        add(radioDecrypt);

        shiftSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
              int value = shiftSlider.getValue();
              if(cipher.getIsUsingEncrypt()){
                  resultShiftLabel.setText(value +  "| a -> " + CaesarCipher.encrypt("a", value));
                  outputTextArea.setText(addNewLines(CaesarCipher.encrypt(inputField.getText(), value)));
                }else {
                    resultShiftLabel.setText(value +  "| a -> " + CaesarCipher.decrypt("a", value));
                    outputTextArea.setText(addNewLines(CaesarCipher.decrypt(inputField.getText(), value)));
                }
            }
        });

        

        radioEncrypt.addActionListener   (e -> {
            cipher.setIsUsingEncrypt(true);
            resultShiftLabel.setText(shiftSlider.getValue() +  "| a -> " + CaesarCipher.encrypt("a", shiftSlider.getValue()));
                  outputTextArea.setText(addNewLines(CaesarCipher.encrypt(inputField.getText(), shiftSlider.getValue())));
        });
        radioDecrypt.addActionListener   (e -> {
            cipher.setIsUsingEncrypt(false);
            resultShiftLabel.setText(shiftSlider.getValue() +  "| a -> " + CaesarCipher.decrypt("a", shiftSlider.getValue()));
                    outputTextArea.setText(addNewLines(CaesarCipher.decrypt(inputField.getText(), shiftSlider.getValue())));
        });

        inputField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
            
            public void update() {
                outputTextArea.setText(addNewLines(CaesarCipher.encrypt(inputField.getText(), shiftSlider.getValue())));

            }
        });

		setResizable(false);
        setLayout(null);
        setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String addNewLines(String text){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append(text.charAt(i));
            if(i%48 == 0 && i != 0){
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
