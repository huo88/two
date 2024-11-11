package com.example.t2.view;

import com.example.t2.dao.RecordsDao;
import com.example.t2.dao.ZoonDao;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;

public class UserDemo {
    public static void main(String[] args) {
        new Window("用户端", "admin");
    }
}

class Window extends JFrame implements ActionListener {
    JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9;
    JLabel jl4, jl5, jl6;
    JTextField jt1;
    JComboBox comboBox1;
    JButton jb1, jb2, jb3, jb4, jb5, jb6;

    DefaultTableModel tableModel1, tableModel2;
    JTable jTable1, jTable2;
    JScrollPane jScrollPane1, jScrollPane2;
    String username1; // 将username1定义为类的成员变量
    private Clip audioClip;
    private boolean isPlaying = false;

    public Window(String b, String username) { // 构造方法
        this.setTitle(b); // 标题
        this.setSize(800, 930);
        this.setLocationRelativeTo(null); // 居中
        this.setLayout(new BorderLayout(10, 10));
        this.username1 = username;

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("E:\\JavaImage\\8.jpg"); // 图片路径
                Image image = imageIcon.getImage();// 获得图片
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);//根据面板大小自动适配图片大小
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        this.setContentPane(backgroundPanel);//将背景面板设置为内容面板

        // 创建透明面板来容纳其他组件
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // 设置为透明
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // 列索引,设置为0,表示将组件放置在第一列。
        gbc.gridy = 0; // 行索引,设置为0,表示将组件放置在第一行。
        gbc.insets = new Insets(5, 10, 5, 10); // 这里设置了上、左、下、右四个方向的边距，都是10个像素。
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.weighty = 0;

        jp1 = new JPanel();
        jp1.setOpaque(false);
        jp1.setLayout(new BorderLayout());

        jp6 = new JPanel();
        jp6.setOpaque(false);
        jl4 = new JLabel("                                                                                                          ");
        jl5 = new JLabel("    ");
        jl6 = new JLabel("    ");
        jb3 = new JButton("个人中心");
        jb4 = new JButton("退出");
        jb6 = new JButton("播放");
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        jb6.addActionListener(this);
        jb3.setFont(new Font("幼圆", Font.PLAIN, 18));
        jb4.setFont(new Font("幼圆", Font.PLAIN, 18));
        jb6.setFont(new Font("幼圆", Font.PLAIN, 18));
        jp6.add(jl4);
        jp6.add(jb3);
        jp6.add(jl5);
        jp6.add(jb4);
        jp6.add(jl6);
        jp6.add(jb6);
        jp1.add(jp6, BorderLayout.NORTH);
        contentPanel.add(jp6, gbc);
        gbc.gridy++;

        // 宠物信息
        jp2 = new JPanel();
        // jp2.setOpaque(false);
        jp2.setLayout(new BorderLayout());
        jp2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "宠物信息", TitledBorder.LEADING, TitledBorder.TOP, new Font("幼圆", Font.PLAIN, 13), new Color(255, 0, 0)));
        jp2.setPreferredSize(new Dimension(680, 400)); // 设置 jp2 的首选大小

        jp3 = new JPanel();
        jp3.setOpaque(false);
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("全部查询           ");
        comboBox1.addItem("根据品种id查询          ");
        comboBox1.addItem("根据性别查询          ");
        comboBox1.addItem("根据编号查询          ");
        jt1 = new JTextField(15);
        jb1 = new JButton("查询");
        jb1.addActionListener(this);
        jt1.setFont(new Font("幼圆", Font.PLAIN, 18));
        jb1.setFont(new Font("幼圆", Font.PLAIN, 18));
        comboBox1.setFont(new Font("幼圆", Font.PLAIN, 18));
        comboBox1.setPreferredSize(new Dimension(200, 30));
        jt1.setPreferredSize(new Dimension(200, 30));
        jp3.add(comboBox1);
        jp3.add(jt1);
        jp3.add(jb1);

        jp4 = new JPanel();
        jp4.setOpaque(false);
        String[] title1 = new String[]{"编号", "姓名", "品种编号", "品种名称", "性别", "生日", "单价"};
        tableModel1 = new DefaultTableModel(title1, 0); // 初始化表格模型,将数据数组设置为空，并传入列标题
        jTable1 = new JTable(tableModel1);
        // 设置表格的字体大小
        Font tableFont = new Font("幼圆", Font.PLAIN, 18);
        jTable1.setFont(tableFont);
        jTable1.getTableHeader().setFont(tableFont); // 设置表头的字体大小
        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setPreferredSize(new Dimension(600, 280)); // 设置滚动面板的首选大小
        jp4.add(jScrollPane1);

        jp5 = new JPanel();
        jb2 = new JButton("购买");
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int zoon_id = Integer.parseInt(JOptionPane.showInputDialog("输入宠物编号:"));
                String zoon_name = JOptionPane.showInputDialog("输入宠物名:");
                Date time = Date.valueOf(JOptionPane.showInputDialog("输入购买时间:"));
                int spend = Integer.parseInt(JOptionPane.showInputDialog("输入总花费："));
                int b = RecordsDao.update(username, zoon_id, zoon_name, time, spend);
            }
        });
        jb2.setFont(new Font("幼圆", Font.PLAIN, 18));

        jp5.add(jb2);

        jp2.add(jp3, BorderLayout.NORTH);
        jp2.add(jp4, BorderLayout.CENTER);
        jp2.add(jp5, BorderLayout.SOUTH);
        jp1.add(jp2, BorderLayout.CENTER);
        contentPanel.add(jp2, gbc);
        gbc.gridy++;

        // 购买记录
        jp7 = new JPanel();
        // jp7.setOpaque(false);
        jp7.setLayout(new BorderLayout());
        jp7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "购买记录", TitledBorder.LEADING, TitledBorder.TOP, new Font("幼圆", Font.BOLD, 13), new Color(255, 0, 0)));
        jp7.setPreferredSize(new Dimension(680, 370));


        jp8 = new JPanel();
        jp8.setOpaque(false);
        String[] title2 = new String[]{"用户名", "宠物编号", "宠物名", "购买时间", "花费"};
        tableModel2 = new DefaultTableModel(title2, 0); // 初始化表格模型,将数据数组设置为空，并传入列标题
        jTable2 = new JTable(tableModel2);
        Font tableFont1 = new Font("幼圆", Font.PLAIN, 18);
        jTable2.setFont(tableFont1);
        jTable2.getTableHeader().setFont(tableFont1); // 设置表头的字体大小
        jScrollPane2 = new JScrollPane(jTable2);
        jScrollPane2.setPreferredSize(new Dimension(600, 280)); // 设置滚动面板的首选大小

        jp8.add(jScrollPane2);
        jp7.add(jp8, BorderLayout.NORTH);
        jp1.add(jp7, BorderLayout.SOUTH);

        jp9 = new JPanel();
        jb5 = new JButton("查看");
        jb5.setFont(new Font("幼圆", Font.PLAIN, 18));
        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs2 = RecordsDao.select(username);
                    tableModel2.setRowCount(0); // 清空现有表格数据
                    while (rs2.next()) {
                        String user_name = rs2.getString("user_name");
                        int zoon_id = rs2.getInt("zoon_id");
                        String zoon_name = rs2.getString("zoon_name");
                        Date time = rs2.getDate("time");
                        int spend = rs2.getInt("spend");
                        tableModel2.addRow(new Object[]{user_name, zoon_id, zoon_name, time, spend}); // 将检索到的数据逐行添加到 tableModel 中
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        jp9.add(jb5);
        jp7.add(jp9, BorderLayout.CENTER);

        contentPanel.add(jp7, gbc);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(true);

        // 初始化音频播放器
        initAudioPlayer();
    }

    private void initAudioPlayer() {
        try {
            File audioFile = new File("E:\\JavaImage\\歌.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);//获取音频输入流
            audioClip = AudioSystem.getClip();//获取一个可以播放音频的 Clip 对象。
            audioClip.open(audioStream);//打开音频输入流
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            int index = comboBox1.getSelectedIndex() + 1; // 1:全部，2：根据品种
            if (index == 1) // 全部查询
            {
                try {
                    ResultSet rs = ZoonDao.All_inquire();
                    tableModel1.setRowCount(0); // 清空现有表格数据
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int type_id = rs.getInt("type_id");
                        String breed_name = rs.getString("breed_name");
                        String sex = rs.getString("sex");
                        String birthday = rs.getString("birthday");
                        int unit = rs.getInt("unit");
                        tableModel1.addRow(new Object[]{id, name, type_id, breed_name, sex, birthday, unit}); // 将检索到的数据逐行添加到 tableModel 中
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (index == 2) { // 根据品种id查询
                try {
                    ResultSet rs2 = ZoonDao.id_inquire2(Integer.parseInt(jt1.getText()));
                    tableModel1.setRowCount(0); // 清空现有表格数据
                    while (rs2.next()) {
                        int id = rs2.getInt("id");
                        String name = rs2.getString("name");
                        int type_id = rs2.getInt("type_id");
                        String breed_name = rs2.getString("breed_name");
                        String sex = rs2.getString("sex");
                        String birthday = rs2.getString("birthday");
                        int unit = rs2.getInt("unit");
                        tableModel1.addRow(new Object[]{id, name, type_id, breed_name, sex, birthday, unit}); // 将检索到的数据逐行添加到 tableModel 中
                    }

                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                jt1.setText("");

            } else if (index == 3) // 根据性别查询
            {
                try {
                    String sex2 = jt1.getText();
                    ResultSet rs2 = ZoonDao.sex_inquire(jt1.getText());
                    tableModel1.setRowCount(0); // 清空现有表格数据
                    while (rs2.next()) {
                        int id = rs2.getInt("id");
                        String name = rs2.getString("name");
                        int type_id = rs2.getInt("type_id");
                        String breed_name = rs2.getString("breed_name");
                        String birthday = rs2.getString("birthday");
                        int unit = rs2.getInt("unit");
                        tableModel1.addRow(new Object[]{id, name, type_id, breed_name, sex2, birthday, unit}); // 将检索到的数据逐行添加到 tableModel 中
                    }
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                jt1.setText("");
            } else if (index == 4) // 根据编号查询
            {
                try {
                    ResultSet rs2 = ZoonDao.id_inquire(Integer.parseInt(jt1.getText()));
                    tableModel1.setRowCount(0); // 清空现有表格数据
                    while (rs2.next()) {
                        int id = rs2.getInt("id");
                        String name = rs2.getString("name");
                        int type_id = rs2.getInt("type_id");
                        String breed_name = rs2.getString("breed_name");
                        String sex = rs2.getString("sex");
                        String birthday = rs2.getString("birthday");
                        int unit = rs2.getInt("unit");
                        tableModel1.addRow(new Object[]{id, name, type_id, breed_name, sex, birthday, unit}); // 将检索到的数据逐行添加到 tableModel 中
                    }
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                jt1.setText("");
            }
        } else if (e.getSource() == jb3) {
            this.dispose();
            new Personage2("个人中心", username1);
        } else if (e.getSource() == jb4) {
            System.exit(0);
        } else if (e.getSource() == jb6) {
            if (isPlaying) {
                audioClip.stop();
            } else {
                audioClip.setFramePosition(0);
                audioClip.start();
            }
            isPlaying = !isPlaying;
        }
    }
}
