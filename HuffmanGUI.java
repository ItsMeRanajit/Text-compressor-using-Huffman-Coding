package textEndcoderDecoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HuffmanGUI extends JFrame {
    private JTextArea messageField;
    private JTextArea compressedField;
    private JButton encodeButton;
    private JButton decodeButton;
    private huffmanCoding huffman;

    public HuffmanGUI() {
        setTitle("Huffman Coding");
        setSize(800, 480); // Reduced height of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Create components
        messageField = new JTextArea();
        compressedField = new JTextArea();
        encodeButton = new JButton("Encode");
        decodeButton = new JButton("Decode");

        // Set fonts and colors
        messageField.setFont(new Font("Arial", Font.PLAIN, 18));
        compressedField.setFont(new Font("Arial", Font.PLAIN, 18));
        encodeButton.setFont(new Font("Arial", Font.BOLD, 18)); // Increased button font size
        decodeButton.setFont(new Font("Arial", Font.BOLD, 18)); // Increased button font size
        encodeButton.setBackground(Color.PINK);
        decodeButton.setBackground(Color.PINK);

        // Enable line wrapping and word wrapping
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        compressedField.setLineWrap(true);
        compressedField.setWrapStyleWord(true);

        // Create scroll panes for text areas
        JScrollPane messageScrollPane = new JScrollPane(messageField);
        JScrollPane compressedScrollPane = new JScrollPane(compressedField);

        // Set preferred sizes to increase input fields width
        messageScrollPane.setPreferredSize(new Dimension(620, 220)); // Increased width
        compressedScrollPane.setPreferredSize(new Dimension(620, 220)); // Increased width

        // Create panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Message:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(messageScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Compressed Data:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(compressedScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Create a panel for buttons to be spaced uniformly
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0)); // Uniform spacing in a row
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);

        panel.add(buttonPanel, gbc);

        // Add panel to frame
        add(panel);

        // Add action listeners
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = messageField.getText();
                    huffman = new huffmanCoding(input);
                    String encoded = huffman.encode(input);
                    compressedField.setText(encoded);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (huffman != null) {
                        String compressed = compressedField.getText();
                        String decoded = huffman.decode(compressed);
                        messageField.setText(decoded);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please encode a message first.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
