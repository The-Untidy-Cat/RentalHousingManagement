package com.theuntidycat.rhm.view.login;

import com.theuntidycat.rhm.view.main.MainView;
import com.formdev.flatlaf.FlatLightLaf;
import com.theuntidycat.rhm.controller.AppPropertiseController;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.theuntidycat.rhm.controller.LoginController;
import com.theuntidycat.rhm.model.User;
import com.theuntidycat.rhm.view.utils.LoadingDialog;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class LoginView extends JFrame {

    private MainView mainView;
    private AppPropertiseController config = new AppPropertiseController();

    public LoginView() {
        
        FlatLightLaf.setup();
        setTitle("Rental Housing Management");
        LoginController controller = new LoginController();
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        ImageIcon imageIcon = new ImageIcon(s + "/assets/building.png"); // load the image to a // imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(390, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);

        JPanel formPanel = new JPanel();

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        formPanel.setLayout(new GridLayout(2, 1));

        JLabel usernameLabel = new JLabel("Tên người dùng", JLabel.LEFT);
        JLabel passwordLabel = new JLabel("Mật khẩu", JLabel.LEFT);
        JLabel headerImg = new JLabel(imageIcon);
        JTextField usernameInput = new JTextField(14);
        JPasswordField passwordInput = new JPasswordField(14);

        passwordLabel.setBorder(new EmptyBorder(0, 0, 0, 34));

        usernameInput.setText(config.getData("account_username"));
        passwordInput.setText(config.getData("account_password"));
        usernameInput.setSize(70, 14);
        passwordInput.setSize(70, 14);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JCheckBox cbRememberMe = new JCheckBox("Nhớ tài khoản", Boolean.valueOf(config.getData("account_remember_me")));
        footerPanel.setBorder(new EmptyBorder(0, 8, 10, 0));
        footerPanel.add(cbRememberMe);

        JButton button = new JButton();
        button.setIcon(new ImageIcon(s + "/assets/box-arrow-in-right.png"));
        button.setPreferredSize(new Dimension(80, 60));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = new String(passwordInput.getPassword());
                boolean check = controller.verifyUser(username, password);
                LoadingDialog loading = new LoadingDialog();
                if (cbRememberMe.isSelected()) {
                    try {
                        config.setData("account_remember_me", String.valueOf(cbRememberMe.isSelected()));
                        config.setData("account_username", String.valueOf(usernameInput.getText()));
                        config.setData("account_password", String.valueOf(passwordInput.getPassword()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        config.setData("account_username", "");
                        config.setData("account_password", "");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (check) {
                    loading.setVisible(true);
                    CompletableFuture<Void> loadMainView = CompletableFuture.runAsync(new Runnable() {
                        public void run() {
                            mainView.run();
                        }
                    });
                    User user = controller.getUser(username, password);
                    try {
                        close();
                        loadMainView.get();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (ExecutionException ex) {
                        ex.printStackTrace();
                    }
                    loading.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Xác thực thất bại. Vui lòng kiểm tra lại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameInput);

        formPanel.add(usernamePanel);

        passwordPanel.add(passwordLabel);
//        passwordPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
        passwordPanel.add(passwordInput);

        formPanel.add(passwordPanel);

        actionPanel.add(button);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bodyPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        bodyPanel.add(formPanel);
        bodyPanel.add(actionPanel);

        add(headerImg, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
//        add(actionPanel);
        // add(button);
//        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(400, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public void run(MainView tempMainView) {
        mainView = tempMainView;
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }
}
