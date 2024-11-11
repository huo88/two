package com.example.t2.view;

import com.example.t2.dao.UserDao;
import com.example.t2.dao.ZoonDao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;

public class UserEdit {
    public static void main(String[] args) {
        new UserEdit2("用户信息查询和修改");
    }
}
class UserEdit2 extends JFrame implements ActionListener {
    JPanel jp,jp1,jp2,jp3,jp4,jp5,jp6,jp7;
    DefaultTableModel tableModel1;
    JTable jTable1;
    JScrollPane jScrollPane1;
    JLabel jl,jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JTextField jt,jt1,jt2,jt3,jt4,jt5,jt6;
    JButton jButton1,jButton2,jButton3,jButton4,jButton5;

    public UserEdit2(String s){
        this.setTitle(s);
        setTitle(s);
        setSize(650, 600);
        this.setLocationRelativeTo(null);

        jp = new JPanel();
        jp.setOpaque(false);
        jp.setLayout(new BorderLayout());
        this.add(jp);

        jp1 = new JPanel();
        jp1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"用户信息",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.PLAIN,13),new Color(255,0,0)));
        jl = new JLabel("用户名:  ");
        jt = new JTextField(15);
        jButton1 = new JButton("查询");
        jButton1.addActionListener(this);
        jButton3 = new JButton("全部查询");
        jButton3.addActionListener(this);
        jl.setFont(new Font("幼圆",Font.PLAIN,18));
        jt.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton1.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton3.setFont(new Font("幼圆",Font.PLAIN,18));
        jp1.add(jl);
        jp1.add(jt);
        jp1.add(jButton1);
        jp1.add(jButton3);
        jp.add(jp1,BorderLayout.NORTH);

        jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        jp2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"用户信息列表",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.PLAIN,13),new Color(255,0,0)));

        jp3 = new JPanel();
        String[] title2 = new String[]{"编号","用户名","密码","性别","生日","手机号"};
        tableModel1 = new DefaultTableModel(title2,0);//初始化表格模型,将数据数组设置为空，并传入列标题
        jTable1 = new JTable(tableModel1);
        Font tableFont = new Font("幼圆", Font.PLAIN, 18);
        jTable1.setFont(tableFont);
        jTable1.getTableHeader().setFont(tableFont); // 设置表头的字体大小
        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setPreferredSize(new Dimension(580, 250)); // 设置滚动面板的首选大小
        jButton4 = new JButton("下载");
        jButton4.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton4.addActionListener(this);
        jp3.add(jScrollPane1);
        jp3.add(jButton4);
        jp2.add(jp3);
        jp.add(jp2,BorderLayout.CENTER);


        jp4 = new JPanel();
        jp4.setSize(590,200);
        jp4.setLayout(new GridLayout(3,1));
        jp4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"信息编辑",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.PLAIN,13),new Color(255,0,0)));


        jp7 = new JPanel();
        jl7 = new JLabel("请输入要修改人的编号：");
        jt6 = new JTextField(12);
        jl7.setFont(new Font("幼圆",Font.PLAIN,18));
        jt6.setFont(new Font("幼圆",Font.PLAIN,18));
        jp7.add(jl7);
        jp7.add(jt6);
        jp4.add(jp7);

        jp5 = new JPanel();
        jl1 = new JLabel("用户名:");
        jt1 = new JTextField(12);
        jl2 = new JLabel("   密码:");
        jt2 = new JTextField(12);
        jl3 = new JLabel("   生日:");
        jt3 = new JTextField(12);
        jt1.setFont(new Font("幼圆",Font.PLAIN,18));
        jl1.setFont(new Font("幼圆",Font.PLAIN,18));
        jl2.setFont(new Font("幼圆",Font.PLAIN,18));
        jt2.setFont(new Font("幼圆",Font.PLAIN,18));
        jl3.setFont(new Font("幼圆",Font.PLAIN,18));
        jt3.setFont(new Font("幼圆",Font.PLAIN,18));
        jp5.add(jl1);
        jp5.add(jt1);
        jp5.add(jl2);
        jp5.add(jt2);
        jp5.add(jl3);
        jp5.add(jt3);
        jp4.add(jp5);

        jp6 = new JPanel();
        jl4 = new JLabel("性别:");
        jt4 = new JTextField(12);
        jl5 = new JLabel("    手机号:");
        jt5 = new JTextField(12);
        jl6 = new JLabel("                                ");
        jButton2 = new JButton("修改");
        jButton2.addActionListener(this);
        jl4.setFont(new Font("幼圆",Font.PLAIN,18));
        jt4.setFont(new Font("幼圆",Font.PLAIN,18));
        jl5.setFont(new Font("幼圆",Font.PLAIN,18));
        jt5.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton2.setFont(new Font("幼圆",Font.PLAIN,18));
        jp6.add(jl4);
        jp6.add(jt4);
        jp6.add(jl5);
        jp6.add(jt5);
        jp6.add(jl6);
        jp6.add(jButton2);
        jp4.add(jp6);

        jp.add(jp4,BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1){
            String sname = jt.getText();
            try {
                ResultSet rs = UserDao.selName(sname);
                tableModel1.setRowCount(0);// 清空现有表格数据
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    Date birthday = rs.getDate("birthday");
                    String sex = rs.getString("sex");
                    String phone = rs.getString("phone");
                    tableModel1.addRow(new Object[]{id,name,password,birthday,sex,phone});//将检索到的数据逐行添加到 tableModel 中
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == jButton2){
           int  id = Integer.parseInt(jt6.getText());
            String sname = jt1.getText();
            String password = jt2.getText();
            String birthday = jt3.getText();
            String sex = jt4.getText();
            String phone = jt5.getText();
            try {
                int h = UserDao.update(id,sname,password,birthday,sex,phone);
                if (h>0)
                    JOptionPane.showConfirmDialog(this,"修改成功");
                else
                    JOptionPane.showConfirmDialog(this,"修改失败");


            }catch (Exception e1){
                e1.printStackTrace();
            }

        }
        else if (e.getSource() == jButton3){
            try {
                ResultSet rs = UserDao.All_user();
                tableModel1.setRowCount(0);// 清空现有表格数据
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String password= rs.getString("password");
                    String birthday = rs.getString("birthday");
                    String sex = rs.getString("sex");
                    String phone = rs.getString("phone");
                    tableModel1.addRow(new Object[]{id,name,password,sex,birthday,phone});//将检索到的数据逐行添加到 tableModel 中
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == jButton4) {
            if (tableModel1 == null) {
                JOptionPane.showMessageDialog(this, "表格模型未初始化");
                return;
            }
            if (tableModel1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "没有数据可以下载");
                return;
            }
            try {
                FileWriter writer = new FileWriter("E:\\user_info.csv");
                for (int i = 0; i < tableModel1.getColumnCount(); i++) {
                    writer.append(tableModel1.getColumnName(i));
                    if (i < tableModel1.getColumnCount() - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
                for (int i = 0; i < tableModel1.getRowCount(); i++) {
                    for (int j = 0; j < tableModel1.getColumnCount(); j++) {
                        writer.append(tableModel1.getValueAt(i, j).toString());
                        if (j < tableModel1.getColumnCount() - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
                writer.flush();
                writer.close();
                JOptionPane.showMessageDialog(this, "数据已成功下载到E盘的user_info.csv文件中");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "下载失败，请检查E盘是否有写权限");
            }
        }

    }
}
