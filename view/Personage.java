package com.example.t2.view;

import com.example.t2.dao.UserDao;
import com.example.t2.dao.ZoonDao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;

public class Personage {
    public static void main(String[] args) {
        new Personage2("个人中心","admin");
    }

}

class Personage2 extends JFrame implements ActionListener{

    JPanel jp,jp1,jp2,jp3,jp4,jp5,jp6,jp7;
    DefaultTableModel tableModel1;
    JTable jTable1;
    JScrollPane jScrollPane1;
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8;
    JTextField jt1,jt2,jt3,jt4,jt5,jt6,jt7;
    JButton jButton,jButton1,jButton2,jButton3,jButton4;
    String username;

    public Personage2(String s,String username){
        this.setTitle(s);
        setTitle(s);
        setSize(650, 600);
        this.setLocationRelativeTo(null);
        this.username = username;

        jp = new JPanel();
        jp.setOpaque(false);
        jp.setLayout(new BorderLayout());

        //信息列表
        jp1 = new JPanel();
        jp1.setLayout(new BorderLayout());
        jp1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"信息列表",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.BOLD,13),new Color(255,0,0)));

        jp7 = new JPanel();
        jButton2 = new JButton("查询");
        jButton2.addActionListener(this);
        jButton2.setFont(new Font("幼圆",Font.PLAIN,18));
        jp7.add(jButton2);
        jp1.add(jp7,BorderLayout.NORTH);


        jp2 = new JPanel();
        String[] title2 = new String[]{"用户名","密码","生日","性别","手机号"};
        tableModel1 = new DefaultTableModel(title2,0);//初始化表格模型,将数据数组设置为空，并传入列标题
        jTable1 = new JTable(tableModel1);
        Font tableFont = new Font("幼圆", Font.PLAIN, 18);
        jTable1.setFont(tableFont);
        jTable1.getTableHeader().setFont(tableFont); // 设置表头的字体大小
        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setPreferredSize(new Dimension(580, 250)); // 设置滚动面板的首选大小
        jp2.add(jScrollPane1);
        jp1.add(jp2,BorderLayout.CENTER);
        jp.add(jp1,BorderLayout.NORTH);

        jp3 = new JPanel();
        jp3.setSize(590,200);
        jp3.setLayout(new GridLayout(2,1));
        jp3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"信息编辑",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.BOLD,13),new Color(255,0,0)));

        jp4 = new JPanel();
        jl1 = new JLabel("用户名:");
        jt1 = new JTextField(12);
        jl2 = new JLabel("   密码:");
        jt2 = new JTextField(12);
        jl3 = new JLabel("   生日:");
        jt3 = new JTextField(12);
        jl1.setFont(new Font("幼圆",Font.PLAIN,18));
        jt1.setFont(new Font("幼圆",Font.PLAIN,18));
        jl2.setFont(new Font("幼圆",Font.PLAIN,18));
        jt2.setFont(new Font("幼圆",Font.PLAIN,18));
        jl3.setFont(new Font("幼圆",Font.PLAIN,18));
        jt3.setFont(new Font("幼圆",Font.PLAIN,18));
        jp4.add(jl1);
        jp4.add(jt1);
        jp4.add(jl2);
        jp4.add(jt2);
        jp4.add(jl3);
        jp4.add(jt3);
        jp3.add(jp4);

        jp5 = new JPanel();
        jl4 = new JLabel("性别:");
        jt4 = new JTextField(12);
        jl5 = new JLabel("    手机号:");
        jt5 = new JTextField(12);
        jl6 = new JLabel("                                ");
        jButton = new JButton("修改");
        jButton.addActionListener(this);
        jl4.setFont(new Font("幼圆",Font.PLAIN,18));
        jt4.setFont(new Font("幼圆",Font.PLAIN,18));
        jl5.setFont(new Font("幼圆",Font.PLAIN,18));
        jt5.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton.setFont(new Font("幼圆",Font.PLAIN,18));
        jp5.add(jl4);
        jp5.add(jt4);
        jp5.add(jl5);
        jp5.add(jt5);
        jp5.add(jl6);
        jp5.add(jButton);
        jp3.add(jp5);

        jp.add(jp3,BorderLayout.CENTER);

        jp6 = new JPanel();
        jButton4 = new JButton("返回上一页");
        jButton1 = new JButton("切换账号");
        jButton3 = new JButton("退出");
        jButton1.setFont(new Font("幼圆",Font.PLAIN,16));
        jButton3.setFont(new Font("幼圆",Font.PLAIN,16));
        jButton4.setFont(new Font("幼圆",Font.PLAIN,16));
        jButton4.addActionListener(this);
        jButton1.addActionListener(this);
        jButton3.addActionListener(this);
        jp6.add(jButton1);
        jp6.add(jButton3);
        jp6.add(jButton4);
        jp.add(jp6,BorderLayout.SOUTH);


        this.add(jp);
        //this.setResizable(false);//不可以更改窗口大小
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton){
            String name = jt1.getText();
            String password = jt2.getText();
            String birthday = jt3.getText();
            String sex = jt4.getText();
            String phone = jt5.getText();
            int h = UserDao.update(name,password,birthday,sex,phone);
            if (h>0)
                JOptionPane.showConfirmDialog(this,"修改成功");
            else
                JOptionPane.showConfirmDialog(this,"修改失败");

        }
        else if (e.getSource() == jButton1){
            this.dispose();
            new Login2("登录");
        }
        else if (e.getSource() == jButton2){
            try {
                ResultSet rs = UserDao.inquire2(username);
                tableModel1.setRowCount(0);// 清空现有表格数据
                while (rs.next()){
                    String  name = rs.getString("name");
                    String password = rs.getString("password");
                    Date birthday = rs.getDate("birthday");
                    String sex = rs.getString("sex");
                    String phone = rs.getString("phone");
                    tableModel1.addRow(new Object[]{name,password,birthday,sex,phone});//将检索到的数据逐行添加到 tableModel 中
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == jButton3){
           System.exit(0);
        }
        else if (e.getSource() == jButton4){
            this.dispose();

            int i = UserDao.getRole(username);
            System.out.println(i);
            if (i==1){
               new Window("用户中心",username);
            }
            else if (i==2){
                new Administrator2("管理员中心",username);
            }

        }
    }
}



