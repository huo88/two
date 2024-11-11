package com.example.t2.view;
import com.example.t2.dao.UserDao;
import com.example.t2.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public static void main(String[] args) {
       new Login2("登录界面");
    }
}
class Login2 extends JFrame implements ActionListener {//界面
    JPanel jp1,jp2,jp3,jp4;
    JLabel jl1,jl2,jl3,jl4;
    JButton jb1,jb2;
    JTextField jTextField;
    JPasswordField jPasswordField;
    JComboBox comboBox;


    public Login2(String s){
        setTitle(s);//标题
        setSize(650, 600);
        this.setLocationRelativeTo(null);

        // 创建背景面板
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("E:\\JavaImage\\12.jpg"); // 图片路径
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        this.setContentPane(backgroundPanel);

        // 创建透明面板来容纳其他组件
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // 设置为透明
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        //用户名
        jp1 = new JPanel();
        jp1.setOpaque(false);//设置透明
        jl1 = new JLabel("用户名:");
        jl1.setFont(new Font("幼圆",Font.BOLD,20));
        jTextField = new JTextField(18);
        jTextField.setFont(new Font("幼圆",Font.BOLD,20));
        jTextField.setPreferredSize(new Dimension(200, 30)); // 设置文本框的宽度和高度
        jp1.add(jl1);
        jp1.add(jTextField);
        contentPanel.add(jp1, gbc);
        gbc.gridy++;

        //密码
        jp2 = new JPanel();
        jp2.setOpaque(false);
        jl2 = new JLabel("密码:  ");
        jl2.setFont(new Font("幼圆",Font.BOLD,20));
        jPasswordField = new JPasswordField(18);
        jPasswordField.setPreferredSize(new Dimension(200, 30)); // 设置文本框的宽度和高度
        jp2.add(jl2);
        jp2.add(jPasswordField);
        contentPanel.add(jp2, gbc);
        gbc.gridy++;

        jp3 = new JPanel();//权限
        jp3.setOpaque(false);
        jl3 = new JLabel("权限:  ");
        jl3.setFont(new Font("幼圆",Font.BOLD,20));
        comboBox = new JComboBox<String>();
        comboBox.addItem("用户           ");
        comboBox.addItem("管理员          ");
        comboBox.setFont(new Font("幼圆",Font.BOLD,20));
        jp3.add(jl3);
        jp3.add(comboBox);
        contentPanel.add(jp3, gbc);
        gbc.gridy++;

        //按钮
        jp4 = new JPanel();
        jp4.setOpaque(false);
        jb1 = new JButton("登录");
        jl4 = new JLabel("           ");
        jb2 = new JButton("注册");
        jb1.setFont(new Font("幼圆",Font.BOLD,20));
        jb2.setFont(new Font("幼圆",Font.BOLD,20));
        jp4.add(jb1);
        jp4.add(jl4);
        jp4.add(jb2);
        contentPanel.add(jp4,gbc);
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setResizable(false);//不可以更改窗口大小
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1){
            String name = jTextField.getText();
            String password = jPasswordField.getText();
            int index = comboBox.getSelectedIndex()+1;
            if (name.equals("") || password.equals(""))
                JOptionPane.showMessageDialog(this,"用户名或密码不能为空");
            else {
                User user = UserDao.login(name,password,index);
                if (user != null){
                    this.dispose();//关闭当前窗口
                    if (index == 1)
                        new Window("用户界面",jTextField.getText());
                    else if (index == 2)
                        new Administrator2("管理员端",jTextField.getText());
                }else
                    JOptionPane.showMessageDialog(this,"用户名密码权限有误");
            }

        }
        else if (e.getSource() == jb2){
            this.dispose();
            new Signin2("注册");
        }
    }

}
