package rhm.view;

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

import rhm.controller.LoginController;
import rhm.custom.CustomFont;
import rhm.model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginView {
    JFrame frame = new JFrame("Rental Housing Management");
    public LoginView() {
        LoginController controller = new LoginController();
        CustomFont customFont = new CustomFont();
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

        usernameLabel.setFont(customFont.font.deriveFont(Font.PLAIN, 12f));
        usernameLabel.setLocation(0, 0);

        passwordLabel.setFont(customFont.font.deriveFont(Font.PLAIN, 12f));
        passwordLabel.setBorder(new EmptyBorder(0, 0, 0, 38));

        usernameInput.setSize(70, 14);

        passwordInput.setSize(70, 14);

        button.setText("<html><center>" + "Đăng" + "<br>" + "nhập" + "</center></html>");
        button.setFont(customFont.font.deriveFont(Font.PLAIN, 12f));
        button.setPreferredSize(new Dimension(80, 60));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = new String(passwordInput.getPassword());
                boolean check = controller.verifyUser(username, password);
                if (check) {
                    User user = controller.getUser(username, password);
                    JOptionPane.showMessageDialog(null, "Welcome " + user.displayName, "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong username/password!", "Error", JOptionPane.ERROR_MESSAGE);
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

    public void run(){
        frame.setVisible(true);
    }
}
