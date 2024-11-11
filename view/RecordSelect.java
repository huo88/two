package com.example.t2.view;

import com.example.t2.dao.RecordsDao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;

public class RecordSelect {
    public static void main(String[] args) {
        new RecordSelect2("购买记录");
    }
}
class RecordSelect2 extends JFrame implements ActionListener{

    JPanel jp1,jp2,jp3;
    DefaultTableModel tableModel1;
    JTable jTable1;
    JLabel jl1;
    JTextField jt1;
    JButton jButton,jButton1;
    JScrollPane jScrollPane1;


    public RecordSelect2(String b){
        this.setTitle(b);//标题
        this.setSize(650,450);
        this.setLocationRelativeTo(null);//居中

        jp1 = new JPanel();
        jp1.setLayout(new BorderLayout());
        jp1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"购买记录",TitledBorder.LEADING,TitledBorder.TOP,new Font("幼圆",Font.BOLD,13),new Color(255,0,0)));
        this.add(jp1);

        jp2 = new JPanel();
        jl1 = new JLabel("用户名:");
        jt1 = new JTextField(15);
        jButton1 = new JButton("查询");
        jButton = new JButton("全部查询");
        jButton.addActionListener(this);
        jButton1.addActionListener(this);
        jt1.setPreferredSize(new Dimension(230, 30));
        jl1.setFont(new Font("幼圆",Font.PLAIN,18));
        jt1.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton.setFont(new Font("幼圆",Font.PLAIN,18));
        jButton1.setFont(new Font("幼圆",Font.PLAIN,18));
        jp2.add(jl1);
        jp2.add(jt1);
        jp2.add(jButton1);
        jp2.add(jButton);
        jp1.add(jp2,BorderLayout.NORTH);


        jp3 = new JPanel();
        jp3.setOpaque(false);
        String[] title1 = new String[]{"id","用户名","宠物编号","宠物名","购买时间","花费"};
        tableModel1 = new DefaultTableModel(title1,0);//初始化表格模型,将数据数组设置为空，并传入列标题
        jTable1 = new JTable(tableModel1);
        // 设置表格的字体大小
        Font tableFont = new Font("幼圆", Font.PLAIN, 18);
        jTable1.setFont(tableFont);
        jTable1.getTableHeader().setFont(tableFont); // 设置表头的字体大小
        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setPreferredSize(new Dimension(600, 250)); // 设置滚动面板的首选大小
        jp3.add(jScrollPane1);
        jp1.add(jp3,BorderLayout.CENTER);

        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jButton){
            try {
            ResultSet rs2 = RecordsDao.select();
            tableModel1.setRowCount(0);// 清空现有表格数据
                while (rs2.next()){
                    int id = rs2.getInt("id");
                    String user_name = rs2.getString("user_name");
                    int zoon_id = rs2.getInt("zoon_id");
                    String zoon_name = rs2.getString("zoon_name");
                    Date time = rs2.getDate("time");
                    int spend = rs2.getInt("spend");
                    tableModel1.addRow(new Object[]{id,user_name,zoon_id,zoon_name,time,spend});//将检索到的数据逐行添加到 tableModel 中
                }

            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == jButton1){
            String sname = jt1.getText();
            try {
                ResultSet rs2 = RecordsDao.select(sname);
                tableModel1.setRowCount(0);// 清空现有表格数据
                while (rs2.next()){
                    String user_name = rs2.getString("user_name");
                    int zoon_id = rs2.getInt("zoon_id");
                    String zoon_name = rs2.getString("zoon_name");
                    Date time = rs2.getDate("time");
                    int spend = rs2.getInt("spend");
                    tableModel1.addRow(new Object[]{user_name,zoon_id,zoon_name,time,spend});//将检索到的数据逐行添加到 tableModel 中
                }

            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
}
