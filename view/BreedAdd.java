package com.example.t2.view;

import com.example.t2.dao.BreedDao;
import com.example.t2.dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BreedAdd {
    public static void main(String[] args) {
        new BreedAdd2("宠物类别的添加和删除");
    }
}
class BreedAdd2 extends JFrame implements ActionListener{

    JPanel jp1,jp2;
    JLabel jl1;
    JTextField jt1;
    JButton jb1,jb2,jb3;

    public BreedAdd2(String s){
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

        jp1 = new JPanel();
        jp1.setOpaque(false);
        jl1 = new JLabel("类别名称：");
        jl1.setFont(new Font("幼圆",Font.BOLD,16));
        jt1 = new JTextField(15);
        jp1.add(jl1);
        jp1.add(jt1);
        contentPanel.add(jp1, gbc);
        gbc.gridy++;

        jp2 = new JPanel();
        jp2.setOpaque(false);
        jb1 = new JButton("添加");
        jb2 = new JButton("删除");
        jb3 = new JButton("批量插入");
        jb1.setFont(new Font("幼圆",Font.BOLD,16));
        jb2.setFont(new Font("幼圆",Font.BOLD,16));
        jb3.setFont(new Font("幼圆",Font.BOLD,16));
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);
        contentPanel.add(jp2, gbc);
        gbc.gridy++;

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setResizable(false);//不可以更改窗口大小
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1){
            String breed_name = jt1.getText();
            if (breed_name.equals("")) {
                JOptionPane.showMessageDialog(this,"请输入类别名称");
                return;
            }
            else {
                int n = BreedDao.addBreed(breed_name);
                if (n == 1){
                    JOptionPane.showMessageDialog(this,"添加成功");
                }
                else if (n == 2)
                {
                    JOptionPane.showMessageDialog(this,"该品种已存在");
                }
                else {
                    JOptionPane.showMessageDialog(this,"添加失败");
                }
            }
        }
        else if (e.getSource() == jb2){
            String name = jt1.getText();
            if (name.equals("")) {
                JOptionPane.showMessageDialog(this,"请输入类别名称");
                return;
            }
            else {
                int h = BreedDao.deleteBreed(name);
                if (h == 1){
                    JOptionPane.showMessageDialog(this,"删除成功");
                }
                else if (h == 2 )
                {
                    JOptionPane.showMessageDialog(this,"该品种不存在");
                }
                else {
                    JOptionPane.showMessageDialog(this,"删除失败");
                }
            }

        }
        else if (e.getSource() == jb3) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {//读取文件内容
                    String line;//存储从文件中读取的每一行数据
                    int successCount = 0;
                    int failureCount = 0;
                    while ((line = br.readLine()) != null) {//读取文件内容直到文件的所有行都被读取完毕
                        String[] parts = line.split(",");
                        if (parts.length == 1) {
                            String breed_name = parts[0];
                            int i= BreedDao.addBreed(breed_name);
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

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "批量插入失败");
                }
            }
        }
    }
}



