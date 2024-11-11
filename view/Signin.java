package com.example.t2.view;
import com.example.t2.dao.UserDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class Signin {
    public static void main(String[] args) {
        new Signin2("用户注册");
    }
}
class Signin2 extends JFrame implements ActionListener {
    JPanel jp1,jp2,jp3,jp4,jp5,jp6;
    JLabel jl1,jl2,jl3,jl4,jl5,jl7;
    JTextField jt1,jt2;
    JPasswordField jPasswordField;
    JRadioButton jRadioButton1,jRadioButton2;//单选按钮
    JButton jb1,jb2;
    JComboBox comboBox;
    String str = "";

    public Signin2(String s){
        setTitle(s);
        setSize(650, 600);
        this.setLocationRelativeTo(null);

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

        jp1 = new JPanel();//用户名
        jp1.setOpaque(false);
        jl1 = new JLabel("用户名:");
        jl1.setFont(new Font("幼圆",Font.BOLD,18));
        jt1 = new JTextField(20);
        jt1.setPreferredSize(new Dimension(200, 30)); // 设置文本框的宽度和高度
        jp1.add(jl1);
        jp1.add(jt1);
        contentPanel.add(jp1, gbc);
        gbc.gridy++;


        jp2 = new JPanel();//密码
        jp2.setOpaque(false);
        jl2 = new JLabel("密码:  ");
        jl2.setFont(new Font("幼圆",Font.BOLD,18));
        jPasswordField = new JPasswordField(20);
        jPasswordField.setPreferredSize(new Dimension(200, 30)); // 设置文本框的宽度和高度
        jp2.add(jl2);
        jp2.add(jPasswordField);
        contentPanel.add(jp2, gbc);
        gbc.gridy++;

        jp3 = new JPanel();//手机号
        jp3.setOpaque(false);
        jl3 = new JLabel("手机号:");
        jl3.setFont(new Font("幼圆",Font.BOLD,18));
        jt2 = new JTextField(20);
        jt2.setPreferredSize(new Dimension(200, 30)); // 设置文本框的宽度和高度
        jp3.add(jl3);
        jp3.add(jt2);
        contentPanel.add(jp3, gbc);
        gbc.gridy++;

        jp6 =new JPanel();//角色
        jp6.setOpaque(false);
        jl7 = new JLabel("角色: ");
        jl7.setFont(new Font("幼圆",Font.BOLD,20));
        comboBox = new JComboBox<String>();
        comboBox.addItem("用户           ");
        comboBox.addItem("管理员          ");
        comboBox.setFont(new Font("幼圆",Font.BOLD,20));
        jp6.add(jl7);
        jp6.add(comboBox);
        contentPanel.add(jp6, gbc);
        gbc.gridy++;


        jp4 = new JPanel();//性别
        jp4.setOpaque(false);
        jl4 = new JLabel("性别:   ");
        jl4.setFont(new Font("幼圆",Font.BOLD,20));
        jRadioButton1 = new JRadioButton("男");
        jRadioButton1.setFont(new Font("幼圆",Font.BOLD,20));
        jRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = "男";
            }
        });
        jl5 = new JLabel("             ");
        jRadioButton2 = new JRadioButton("女");
        jRadioButton2.setFont(new Font("幼圆",Font.BOLD,20));
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = "女";
            }
        });
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(jRadioButton1);
        genderGroup.add(jRadioButton2);
        jRadioButton1.setOpaque(false);
        jRadioButton2.setOpaque(false);
        jp4.add(jl4);
        jp4.add(jRadioButton1);
        jp4.add(jl5);
        jp4.add(jRadioButton2);
        contentPanel.add(jp4, gbc);
        gbc.gridy++;

        jp5 = new JPanel();
        jp5.setOpaque(false);
        jb1 = new JButton("注册");
        jb1.setFont(new Font("幼圆",Font.BOLD,20));
        jl5 = new JLabel("                    ");
        jb2 = new JButton("前往登录页面");
        jb2.setFont(new Font("幼圆",Font.BOLD,20));
        jp5.add(jb1);
        jp5.add(jl5);
        jp5.add(jb2);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        contentPanel.add(jp5, gbc);
        gbc.gridy++;

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setResizable(false);//不可以更改窗口大小
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            int role = comboBox.getSelectedIndex() + 1;
            String name = jt1.getText();
            String password = jPasswordField.getText();
            String phone = jt2.getText();
            if (name.equals("") || password.equals("") || phone.equals("") || str.equals("")) {
                JOptionPane.showMessageDialog(this, "请填写完整信息");
                return; // 提前返回，不再执行后续的检查
            }

            if (UserDao.selName(name) != null) {
                JOptionPane.showMessageDialog(this, "该用户名已存在");
            } else {
                int n = UserDao.addUser(name, password, phone, role, str);
                if (n == 1) {
                    JOptionPane.showMessageDialog(this, "注册成功");
                } else if (n == 2) {
                    JOptionPane.showMessageDialog(this, "该用户已存在");
                } else {
                    JOptionPane.showMessageDialog(this, "注册失败");
                }
        }
        }
        else if (e.getSource() == jb2)
        {
            this.dispose();
            new Login2("注册");
        }

    }
}
