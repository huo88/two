package com.example.t2.view;

import com.example.t2.dao.UserDao;
import com.example.t2.dao.ZoonDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;


public class ZoonAdd {
    public static void main(String[] args) {
        new ZoonAdd2("宠物信息添加和删除");
    }
}

class ZoonAdd2 extends JFrame implements ActionListener{

    JPanel jp1,jp2,jp3,jp4,jp5,jp6;
    JLabel jl1,jl2,jl3,jl4,jl5,jl6;
    JTextField jt1,jt2,jt3,jt4;
    JRadioButton jRadioButton1,jRadioButton2;//单选按钮
    JButton jb1,jb2,jb3;
    String str = "";

    public ZoonAdd2(String s){
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
        jl1 = new JLabel("宠物名:");
        jl1.setFont(new Font("幼圆",Font.BOLD,16));
        jt1 = new JTextField(15);
        jp1.add(jl1);
        jp1.add(jt1);
        contentPanel.add(jp1, gbc);
        gbc.gridy++;


        jp2 = new JPanel();//密码
        jp2.setOpaque(false);
        jl2 = new JLabel("品种id:");
        jl2.setFont(new Font("幼圆",Font.BOLD,16));
        jt3 = new JTextField(15);
        jp2.add(jl2);
        jp2.add(jt3);
        contentPanel.add(jp2, gbc);
        gbc.gridy++;

        jp3 = new JPanel();//手机号
        jp3.setOpaque(false);
        jl3 = new JLabel("生日:  ");
        jl3.setFont(new Font("幼圆",Font.BOLD,16));
        jt2 = new JTextField(15);
        jp3.add(jl3);
        jp3.add(jt2);
        contentPanel.add(jp3, gbc);
        gbc.gridy++;

        jp6 = new JPanel();
        jp6.setOpaque(false);
        jl6 = new JLabel("单价:  ");
        jl6.setFont(new Font("幼圆",Font.BOLD,16));
        jt4 = new JTextField(15);
        jp6.add(jl6);
        jp6.add(jt4);
        contentPanel.add(jp6, gbc);
        gbc.gridy++;

        jp4 = new JPanel();//性别
        jp4.setOpaque(false);
        jl4 = new JLabel("性别:    ");
        jl4.setFont(new Font("幼圆",Font.BOLD,16));
        jRadioButton1 = new JRadioButton("雄");
        jRadioButton1.setFont(new Font("幼圆",Font.BOLD,16));
        jRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = "雄";
            }
        });
        jl5 = new JLabel("             ");
        jRadioButton2 = new JRadioButton("雌");
        jRadioButton2.setFont(new Font("幼圆",Font.BOLD,16));
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = "雌";
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
        jb1 = new JButton("添加");
        jb2 = new JButton("删除");
        jb3 = new JButton("批量插入");
        jb1.setFont(new Font("幼圆",Font.BOLD,16));
        jb2.setFont(new Font("幼圆",Font.BOLD,16));
        jb3.setFont(new Font("幼圆",Font.BOLD,16));
        jp5.add(jb3);
        jp5.add(jb1);
        jp5.add(jb2);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        contentPanel.add(jp5, gbc);
        gbc.gridy++;

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setResizable(false);//不可以更改窗口大小
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            String name = jt1.getText();
            String birthday = jt2.getText();
            int type_id = Integer.parseInt(jt3.getText());
            int unit = Integer.parseInt(jt4.getText());
            if (name.equals("") || birthday.equals("") || str.equals("")) {
                JOptionPane.showMessageDialog(this, "请填写完整信息");
                return;
            } else {
                int h = ZoonDao.addZoon(name, type_id, Date.valueOf(birthday), unit, str);
                if (h == 1) {
                    JOptionPane.showMessageDialog(this, "添加成功");
                } else {
                    JOptionPane.showMessageDialog(this, "添加失败");
                }
            }
        } else if (e.getSource() == jb2) {
            String name = jt1.getText();
            int h = ZoonDao.deleteZoon(name);
            if (h == 1) {
                JOptionPane.showMessageDialog(this, "删除成功");
            } else if (h == 2) {
                JOptionPane.showMessageDialog(this, "该宠物不存在");
            } else {
                JOptionPane.showMessageDialog(this, "删除失败");
            }
        } else if (e.getSource() == jb3) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    int successCount = 0;
                    int failureCount = 0;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 5) {
                            String name = parts[0];
                            int type_id = Integer.parseInt(parts[1]);
                            Date birthday = Date.valueOf(parts[2]);
                            int unit = Integer.parseInt(parts[3]);
                            String sex = parts[4];
                            int h = ZoonDao.addZoon(name, type_id, birthday, unit, sex);
                            if (h == 1) {
                                successCount++;
                            } else {
                                failureCount++;
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "批量插入失败: " + ex.getMessage());
                }
            }
        }

    }
}