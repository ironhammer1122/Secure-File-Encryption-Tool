import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.crypto.SecretKey;

public class UIFrame {
    UIFrame() {
        JFrame frame = new JFrame("Secure File Encryption Tool");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel titleLabel = new JLabel("Secure File Encryption Tool", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel statusLabel = new JLabel("Ready", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton encryptButton = new JButton("Encrypt File");
        JButton decryptButton = new JButton("Decrypt File");
        JButton exitButton = new JButton("Exit");

        encryptButton.setFont(new Font("Arial", Font.PLAIN, 16));
        encryptButton.setPreferredSize(new Dimension(200, 40));

        decryptButton.setFont(new Font("Arial", Font.PLAIN, 16));
        decryptButton.setPreferredSize(new Dimension(200, 40));

        exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        exitButton.setPreferredSize(new Dimension(200, 40));

        panel.add(titleLabel);
        panel.add(encryptButton);
        panel.add(decryptButton);
        panel.add(exitButton);
        panel.add(statusLabel);
        panel.add(progressBar);
        frame.add(panel);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(frame);
                if (response == JFileChooser.APPROVE_OPTION) {
                    try {
                        statusLabel.setText("Encrypting...");
                        progressBar.setVisible(true);
                        progressBar.setIndeterminate(true);
                        encryptButton.setEnabled(false);
                        decryptButton.setEnabled(false);
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();
                        JPasswordField passwordField = new JPasswordField();
                        int option = JOptionPane.showConfirmDialog(
                                frame,
                                passwordField,
                                "Enter Password",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        if (option == JOptionPane.OK_OPTION) {
                            String password = new String(passwordField.getPassword());
                            if (password.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Password cannot be empty!");
                                statusLabel.setText("Ready");
                                progressBar.setVisible(false);
                                encryptButton.setEnabled(true);
                                decryptButton.setEnabled(true);
                                return;
                            }
                            SecretKey secretKey = KeyGeneratorUtil.generateKey(password);
                            byte[] fileData = FileManager.readFile(filePath);
                            if (fileData.length == 0) {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "File is empty!");
                                statusLabel.setText("Ready");
                                progressBar.setVisible(false);
                                encryptButton.setEnabled(true);
                                decryptButton.setEnabled(true);
                                return;
                            }
                            byte[] encryptedData = Encryptor.encrypt(fileData, secretKey);
                            String outputPath = filePath + ".enc";
                            FileManager.writeFile(outputPath, encryptedData);
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "File encrypted successfully!");
                            statusLabel.setText("Encryption Completed");
                            progressBar.setIndeterminate(false);
                            progressBar.setVisible(false);
                            encryptButton.setEnabled(true);
                            decryptButton.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Encryption failed!");
                        statusLabel.setText("Encryption Failed");
                        progressBar.setIndeterminate(false);
                        progressBar.setVisible(false);
                        encryptButton.setEnabled(true);
                        decryptButton.setEnabled(true);
                    }
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(frame);
                if (response == JFileChooser.APPROVE_OPTION) {
                    try {
                        statusLabel.setText("Decrypting...");
                        progressBar.setVisible(true);
                        progressBar.setIndeterminate(true);
                        encryptButton.setEnabled(false);
                        decryptButton.setEnabled(false);
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();
                        if (!filePath.endsWith(".enc")) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Please select a .enc file!");
                            statusLabel.setText("Ready");
                            progressBar.setVisible(false);
                            encryptButton.setEnabled(true);
                            decryptButton.setEnabled(true);
                            return;
                        }
                        JPasswordField passwordField = new JPasswordField();
                        int option = JOptionPane.showConfirmDialog(
                                frame,
                                passwordField,
                                "Enter Password",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        if (option == JOptionPane.OK_OPTION) {
                            String password = new String(passwordField.getPassword());
                            if (password.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Password cannot be empty!");
                                statusLabel.setText("Ready");
                                progressBar.setVisible(false);
                                encryptButton.setEnabled(true);
                                decryptButton.setEnabled(true);
                                return;
                            }
                            SecretKey secretKey = KeyGeneratorUtil.generateKey(password);
                            byte[] encryptedData = FileManager.readFile(filePath);
                            byte[] decryptedData = Decryptor.decrypt(
                                    encryptedData,
                                    secretKey);
                            String originalPath = filePath.replace(".enc", "");
                            int dotIndex = originalPath.lastIndexOf(".");
                            String outputPath;
                            if (dotIndex == -1) {
                                outputPath = originalPath + "_decrypted";
                            } else {
                                String fileName = originalPath.substring(0, dotIndex);
                                String extension = originalPath.substring(dotIndex);
                                outputPath = fileName + "_decrypted" + extension;
                            }
                            FileManager.writeFile(outputPath, decryptedData);
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "File decrypted successfully!");
                            statusLabel.setText("Decryption Completed");
                            progressBar.setIndeterminate(false);
                            progressBar.setVisible(false);
                            encryptButton.setEnabled(true);
                            decryptButton.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Wrong password or corrupted file!");
                        statusLabel.setText("Decryption Failed");
                        progressBar.setIndeterminate(false);
                        progressBar.setVisible(false);
                        encryptButton.setEnabled(true);
                        decryptButton.setEnabled(true);
                    }
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}