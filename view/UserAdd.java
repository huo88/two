package com.example.t2.view;
import com.example.t2.dao.UserDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Date;

public class UserAdd {
    public static void main(String[] args) {
        new UserAdd2("用户信息添加和删除");
    }
}

class UserAdd2 extends JFrame implements ActionListener {

    JPanel jp1,jp2,jp3,jp4,jp5;
    JLabel jl1,jl2,jl3,jl4,jl5,jl7;
    JTextField jt1,jt2;
    JPasswordField jPasswordField;
    JRadioButton jRadioButton1,jRadioButton2;//单选按钮
    JButton jb1,jb2,jb3;
    String str = "";


    public UserAdd2(String s){
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
        jl1 = new JLabel("用户名：");
        jl1.setFont(new Font("幼圆",Font.BOLD,16));
        jt1 = new JTextField(15);
        jp1.add(jl1);
        jp1.add(jt1);
        contentPanel.add(jp1, gbc);
        gbc.gridy++;

        jp2 = new JPanel();//密码
        jp2.setOpaque(false);
        jl2 = new JLabel("密码：  ");
        jl2.setFont(new Font("幼圆",Font.BOLD,16));
        jPasswordField = new JPasswordField(15);
        jp2.add(jl2);
        jp2.add(jPasswordField);
        contentPanel.add(jp2, gbc);
        gbc.gridy++;

        jp3 = new JPanel();//手机号
        jp3.setOpaque(false);
        jl3 = new JLabel("手机号:  ");
        jl3.setFont(new Font("幼圆",Font.BOLD,16));
        jt2 = new JTextField(15);
        jp3.add(jl3);
        jp3.add(jt2);
        contentPanel.add(jp3, gbc);
        gbc.gridy++;


        jp4 = new JPanel();//性别
        jp4.setOpaque(false);
        jl4 = new JLabel("性别:   ");
        jl4.setFont(new Font("幼圆",Font.BOLD,16));
        jRadioButton1 = new JRadioButton("男");
        jRadioButton1.setFont(new Font("幼圆",Font.BOLD,16));
        jRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = "男";
            }
        });
        jl5 = new JLabel("             ");
        jRadioButton2 = new JRadioButton("女");
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = "女";
            }
        });
        jRadioButton2.setFont(new Font("幼圆",Font.BOLD,16));
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
        jb3 = new JButton("批量插入");
        jb1 = new JButton("添加");
        jb2 = new JButton("删除");
        jb3.setFont(new Font("幼圆",Font.BOLD,16));
        jb1.setFont(new Font("幼圆",Font.BOLD,16));
        jb2.setFont(new Font("幼圆",Font.BOLD,16));
        jb3.addActionListener(this);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jp5.add(jb3);
        jp5.add(jb1);
        jp5.add(jb2);
        contentPanel.add(jp5, gbc);
        gbc.gridy++;

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setResizable(false);//不可以更改窗口大小
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1){
            String name = jt1.getText();
            String password = jPasswordField.getText();
            String phone = jt2.getText();
            int role = 1;
            if (name.equals("") || password.equals("") || phone.equals("")|| str.equals("")) {
                JOptionPane.showMessageDialog(this,"请填写完整信息");
                return;
            }
            else{
                int n = UserDao.addUser(name,password,phone,role,str);
                if (n == 1){
                    JOptionPane.showMessageDialog(this,"添加成功");
                }
                else if (n == 2)
                {
                    JOptionPane.showMessageDialog(this,"该用户已存在");
                }
                else {
                    JOptionPane.showMessageDialog(this,"添加失败");
                }
            }
        }
        else if (e.getSource() == jb2){
            String name = jt1.getText();
            String password = jPasswordField.getText();
            if (name.equals("") || password.equals("")|| str.equals("")) {
                JOptionPane.showMessageDialog(this,"请填写完整信息");
                return;
            }
            else{
            int h = UserDao.deleteUser(name,password);
            if (h == 1){
                JOptionPane.showMessageDialog(this,"删除成功");
            }
            else if (h == 2 )
            {
                JOptionPane.showMessageDialog(this,"该用户不存在");
            }
            else {
                JOptionPane.showMessageDialog(this,"删除失败");
            }
        }
    }
        else if (e.getSource() == jb3){
            JFileChooser fileChooser = new JFileChooser();//创建一个JFileChooser对象，用于让用户选择一个文件。
            int result = fileChooser.showOpenDialog(this);//显示文件选择对话框，并返回用户的选择结果
            if (result == JFileChooser.APPROVE_OPTION) {//用户选择了文件并点击了“打开”按钮。
                File file = fileChooser.getSelectedFile();//获取用户选择的文件对象
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {//读取文件内容
                    String line;//存储从文件中读取的每一行数据
                    int successCount = 0;
                    int failureCount = 0;
                    while ((line = br.readLine()) != null) {//读取文件内容直到文件的所有行都被读取完毕
                        String[] parts = line.split(",");
                        if (parts.length == 6) {
                            String name = parts[0];
                            String password = parts[1];
                            String birthday = parts[2];
                            String phone = parts[3];
                            int role = Integer.parseInt(parts[4]);
                            String sex = parts[5];
                           int i= UserDao.addUser(name, password,birthday, phone, role,sex);
                            if (i == 1) {
                                successCount++;
                            }
                        } else {
                            failureCount++;
                        }
                    }
                    if (failureCount == 0) {
                        JOptionPane.showMessageDialog(this, "批量插入成功，共插入 " + successCount + " 条记录");
                    } else {
                        JOptionPane.showMessageDialog(this, "批量插入完成，成功 " + successCount + " 条，失败 " + failureCount + " 条");
                    }

            }catch (Exception e1){
                e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "批量插入失败");
            }

        }
}
}
}
