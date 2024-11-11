package com.example.t2.view;


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

public class ZoonEdit {
    public static void main(String[] args) {

        new ZoonEdit2("宠物信息的查询和修改");
    }
}

class ZoonEdit2 extends JFrame implements ActionListener{

    JPanel jp,jp1,jp2,jp3,jp4,jp5,jp6,jp7;
    DefaultTableModel tableModel1;
    JTable jTable1;
    JScrollPane jScrollPane1;
    JLabel jl,jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JTextField jt,jt1,jt2,jt3,jt4,jt5,jt6;
    JButton jButton1,jButton2,jButton3,jButton4;

    public ZoonEdit2(String s){
        this.setTitle(s);
        setTitle(s);
        setSize(700, 600);
        this.setLocationRelativeTo(null);

        jp = new JPanel();
        jp.setOpaque(false);
        jp.setLayout(new BorderLayout());
        this.add(jp);

        jp1 = new JPanel();
        jp1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"宠物信息",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.PLAIN,13),new Color(255,0,0)));
        jl = new JLabel("宠物名:  ");
        jt = new JTextField(15);
        jButton1 = new JButton("查询");
        jButton1.addActionListener(this);
        jButton3 = new JButton("全部查询");
        jButton3.addActionListener(this);
        jl.setFont(new Font("幼圆",Font.PLAIN,18));
        jt.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton1.setFont(new Font("幼圆",Font.PLAIN,18));
        jp1.add(jl);
        jButton3.setFont(new Font("幼圆",Font.PLAIN,18));
        jp1.add(jt);
        jp1.add(jButton1);
        jp1.add(jButton3);
        jp.add(jp1,BorderLayout.NORTH);

        jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        jp2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"宠物信息列表",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.PLAIN,13),new Color(255,0,0)));

        jp3 = new JPanel();
        String[] title2 = new String[]{"宠物编号","宠物名","品种id","品种名称","性别","生日","单价"};
        tableModel1 = new DefaultTableModel(title2,0);//初始化表格模型,将数据数组设置为空，并传入列标题
        jTable1 = new JTable(tableModel1);
        Font tableFont = new Font("幼圆", Font.PLAIN, 18);
        jTable1.setFont(tableFont);
        jTable1.getTableHeader().setFont(tableFont); // 设置表头的字体大小
        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setPreferredSize(new Dimension(600, 250)); // 设置滚动面板的首选大小
        jButton4 = new JButton("下载");
        jButton4.addActionListener(this);
        jButton4.setFont(new Font("幼圆",Font.PLAIN,18));
        jp3.add(jScrollPane1);
        jp3.add(jButton4);
        jp2.add(jp3);
        jp.add(jp2,BorderLayout.CENTER);


        jp4 = new JPanel();
        jp4.setSize(590,200);
        jp4.setLayout(new GridLayout(3,1));
        jp4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"信息编辑",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.PLAIN,13),new Color(255,0,0)));

        jp7 = new JPanel();
        jl7 = new JLabel("请输入要修改宠物的编号：");
        jt6 = new JTextField(12);
        jl7.setFont(new Font("幼圆",Font.PLAIN,18));
        jt6.setFont(new Font("幼圆",Font.PLAIN,18));
        jp7.add(jl7);
        jp7.add(jt6);
        jp4.add(jp7);

        jp5 = new JPanel();
        jl1 = new JLabel("宠物名:");
        jt1 = new JTextField(12);
        jl2 = new JLabel("   品种id:");
        jt2 = new JTextField(12);
        jl3 = new JLabel("   性别:");
        jt3 = new JTextField(12);
        jl1.setFont(new Font("幼圆",Font.PLAIN,18));
        jt1.setFont(new Font("幼圆",Font.PLAIN,18));
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
        jl4 = new JLabel("生日:");
        jt4 = new JTextField(12);
        jl5 = new JLabel("    单价:");
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
                ResultSet rs = ZoonDao.name1_inquire(sname);
                tableModel1.setRowCount(0);// 清空现有表格数据
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int type_id = rs.getInt("type_id");
                    String breed_name = rs.getString("breed_name");
                    String sex = rs.getString("sex");
                    Date birthday = rs.getDate("birthday");
                    int unit = rs.getInt("unit");
                    tableModel1.addRow(new Object[]{id,name,type_id,breed_name,sex,birthday,unit});//将检索到的数据逐行添加到 tableModel 中
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == jButton2){
            int  id = Integer.parseInt(jt6.getText());
            String sname = jt1.getText();
            int type_id = Integer.parseInt(jt2.getText());
            String sex = jt3.getText();
            String birthday = jt4.getText();
            int unit = Integer.parseInt(jt5.getText());
            try {
                int h = ZoonDao.update(id,sname,type_id,sex,birthday,unit);
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
                ResultSet rs = ZoonDao.All_inquire();
                tableModel1.setRowCount(0);// 清空现有表格数据
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int type_id = rs.getInt("type_id");
                    String breed_name = rs.getString("breed_name");
                    String sex = rs.getString("sex");
                    String birthday = rs.getString("birthday");
                    int unit = rs.getInt("unit");
                    tableModel1.addRow(new Object[]{id,name,type_id,breed_name,sex,birthday,unit});//将检索到的数据逐行添加到 tableModel 中
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == jButton4){
            try {
                FileWriter writer = new FileWriter("E:\\zoon_info.csv");
                for (int i = 0; i < tableModel1.getColumnCount(); i++) {//遍历列标题
                    writer.append(tableModel1.getColumnName(i));
                    if (i < tableModel1.getColumnCount() - 1) {
                        writer.append(" ");
                    }
                }
                writer.append("\n");
                for (int i = 0; i < tableModel1.getRowCount(); i++) {//遍历 tableModel1 中的所有行和列，数据写入到文件中
                    for (int j = 0; j < tableModel1.getColumnCount(); j++) {
                        writer.append(tableModel1.getValueAt(i, j).toString());
                        if (j < tableModel1.getColumnCount() - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
                writer.flush();//刷新并关闭 FileWriter 对象
                writer.close();
                JOptionPane.showMessageDialog(this, "数据已成功下载到E盘的zoon_info.csv文件中");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "下载失败，请检查E盘是否有写权限");
            }
    }
}
}
