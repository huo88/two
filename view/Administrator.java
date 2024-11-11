package com.example.t2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrator {
    public static void main(String[] args) {
        new Administrator2("管理员端","admin");
    }
}

class  Administrator2 extends JFrame {
    JMenuBar menuBar;


    public Administrator2(String s,  String username){
        setTitle(s);
        setSize(800,600);
        this.setLocationRelativeTo(null);


        // 创建背景面板
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("E:\\JavaImage\\13.jpg"); // 图片路径
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

        menuBar = new JMenuBar();//创建菜单栏
        this.add(menuBar);
        this.setJMenuBar(menuBar);//添加菜单栏到主窗体，设置窗体菜单栏
        //用户信息管理子菜单
        JMenu jMenu = new JMenu("用户信息管理");
        JMenuItem jMenuItem = new JMenuItem("用户信息添加和删除");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserAdd2("用户信息添加和删除");
            }
        });
        jMenu.add(jMenuItem);
        jMenu.addSeparator();//分隔符
        JMenuItem jMenuItem1 = new JMenuItem("用户信息查询和修改");
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserEdit2("用户信息查询和修改");
            }
        });
        jMenu.add(jMenuItem1);
        menuBar.add(jMenu);

        //宠物信息管理子菜单
        JMenu jMenu1 = new JMenu("宠物信息管理");
        JMenuItem jMenuItem2 = new JMenuItem("宠物信息添加和删除");
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ZoonAdd2("宠物信息添加和删除");
            }
        });
        JMenuItem jMenuItem3 = new JMenuItem("宠物信息查询与修改");
        jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ZoonEdit2("宠物信息查询与修改");
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.addSeparator();//分隔符
        jMenu1.add(jMenuItem3);
        menuBar.add(jMenu1);

        //宠物类别
        JMenu jMenu2 = new JMenu("宠物类别管理");
        JMenuItem jMenuItem4 = new JMenuItem("宠物类别的添加和删除");
        jMenuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BreedAdd2("宠物类别的添加和删除");
            }
        });
        JMenuItem jMenuItem5 = new JMenuItem("宠物类别的查询和修改");
        jMenuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BreedEdit2("宠物类别的查询和修改");
            }
        });
        jMenu2.add(jMenuItem4);
        jMenu2.addSeparator();//分隔符
        jMenu2.add(jMenuItem5);
        menuBar.add(jMenu2);

        //购买记录
        JMenu jMenu4 = new JMenu("购买记录");
        JMenuItem jMenuItem7 = new JMenuItem("购买记录的查询");
        jMenuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RecordSelect2("购买记录");
            }
        });
        jMenu4.add(jMenuItem7);
        menuBar.add(jMenu4);


        JMenu jMenu3 = new JMenu("个人中心");
        JMenuItem jMenuItem6 = new JMenuItem("个人中心");
        jMenuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Personage2("个人中心",username);
            }
        });
        jMenu3.add(jMenuItem6);
        menuBar.add(jMenu3);

        jMenu.setFont(new Font("幼圆",Font.BOLD,18));
        jMenu1.setFont(new Font("幼圆",Font.BOLD,18));
        jMenu2.setFont(new Font("幼圆",Font.BOLD,18));
        jMenu3.setFont(new Font("幼圆",Font.BOLD,18));
        jMenu4.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem1.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem2.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem3.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem4.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem5.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem6.setFont(new Font("幼圆",Font.BOLD,18));
        jMenuItem7.setFont(new Font("幼圆",Font.BOLD,18));

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }


}
