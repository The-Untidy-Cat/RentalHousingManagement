package com.theuntidycat.rhm.view.login;

import com.theuntidycat.rhm.view.main.MainView;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;

public class LoginView {

    private JFrame frame;
    private MainView mainView;
    private LoadingDialog loading;

    public LoginView() {
        FlatLightLaf.setup();
        frame = new JFrame("Rental Housing Management");
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
        JButton button = new JButton();

        usernameLabel.setLocation(0, 0);

        passwordLabel.setBorder(new EmptyBorder(0, 0, 0, 34));

        usernameInput.setSize(70, 14);

        passwordInput.setSize(70, 14);

        button.setText("<html><center>" + "Đăng" + "<br>" + "nhập" + "</center></html>");

        button.setPreferredSize(new Dimension(80, 60));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = new String(passwordInput.getPassword());
                boolean check = controller.verifyUser(username, password);
                LoadingDialog loading = new LoadingDialog();
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

        // formPanel.add(headerImg);
        usernamePanel.add(usernameLabel);
        usernamePanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        usernamePanel.add(usernameInput);

        formPanel.add(usernamePanel);

        passwordPanel.add(passwordLabel);
        passwordPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
        passwordPanel.add(passwordInput);

        formPanel.add(passwordPanel);

        actionPanel.add(button);

        frame.add(headerImg);
        frame.add(formPanel);
        frame.add(actionPanel);

        // frame.add(button);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setSize(400, 260);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void run(MainView tempMainView) {
        mainView = tempMainView;
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
    }
}
