/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcentermanagementsystem;

import static healthcentermanagementsystem.Login.usernameLogin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.desktop.QuitStrategy;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Cabes-PC
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        initializeUI();

        logs();
        purok();
        pregnancyrec();
        medrelease();
        tblupdatemedinfo();
        tableupdate();
        tblupdatemed();
        resizeTable();
        tblupdate();
        birthrec();
        removeres();
        tblfourpsmem();
        medremove();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("y-MM-d");
        jtxtDateup.setText(sdf.format(d));
        jtxtDateadd.setText(sdf.format(d));
        jtxtBP1.setText(sdf.format(d));
        jtxtDatrecvd.setDate(d);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
//        password.requestFocus();
    }

    private void initializeUI() {
//    JFrame frame = new JFrame("Home");

//      jTabbedPane1.setTabComponentAt(1, frame);
//      jTabbedPane1.setIconAt(0, icon);
//     jTabbedPane1.addTab("Welcome",new ImageIcon("resources\\1.png"),mainPanel,"Takes to the welcome page");
//ImageIcon icon = new ImageIcon("src/healthcentermanagementsystem/home.png");
//JLabel label = new JLabel(jTabbedPane1.getName());
//label.setHorizontalTextPosition(SwingConstants.LEADING);
        ImageIcon icon = new ImageIcon("src/healthcentermanagementsystem/home.png");
        ImageIcon icon1 = new ImageIcon("src/healthcentermanagementsystem/personal-information.png");
        ImageIcon icon2 = new ImageIcon("src/healthcentermanagementsystem/sign-up.png");
        ImageIcon icon3 = new ImageIcon("src/healthcentermanagementsystem/user (1).png");
        ImageIcon icon4 = new ImageIcon("src/healthcentermanagementsystem/vitamin.png");
        ImageIcon icon5 = new ImageIcon("src/healthcentermanagementsystem/settings.png");

        jTabbedPane1.setIconAt(0, icon);
        jTabbedPane1.setIconAt(1, icon1);
        jTabbedPane1.setIconAt(2, icon2);
        jTabbedPane1.setIconAt(3, icon3);
        jTabbedPane1.setIconAt(4, icon4);
        jTabbedPane1.setIconAt(5, icon5);
    }

    void showDate() {

//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-y");
//        jdate.setText(sdf.format(d));
    }

    public static boolean cpnumber(String in) {
        return in.charAt(0) == '0' && in.charAt(1) == '9' && in.length() == 11 && in.matches("[0-9]+");
    }

    private void resizeTable() {
        jtbladdresident.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtxtbeditresident.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtblviewresident.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtblforpsmem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtblPregrec.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtblBirthreg.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtbladdresident1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // ===== BP COMBOX ========
        jcmbsystolic.setSelectedIndex(-1);
        jcmbdiastolic.setSelectedIndex(-1);
        jcmbPurokpreg.setSelectedIndex(-1);
        jcmbgender.setSelectedIndex(-1);
        jtxtcivilstatus.setSelectedIndex(-1);
        jtxteducationalattmnt.setSelectedIndex(-1);
        jtxtRlgn.setSelectedIndex(-1);
        jcmbPurok.setSelectedIndex(-1);
        jcmb4ps.setSelectedIndex(-1);
        jcmbgender1.setSelectedIndex(-1);
        jtxtcivilstatus1.setSelectedIndex(-1);
        jtxteducationalattmnt1.setSelectedIndex(-1);
        jtxtRlgn1.setSelectedIndex(-1);
        jcmbPurok1.setSelectedIndex(-1);
        jcmb4ps1.setSelectedIndex(-1);
    }
    Connection con;
    PreparedStatement pst;

    private void purok() {
        ppltncount();
        fnocount();
        hsecount();
        malecount();
        femalecount();
        seniorctzn();

    }
    
    private void ppltncount() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE purok ='Purok 1'");

            ResultSet rs = pst.executeQuery();
            //==================== PUROK 1 =======================
            if (rs.next()) {
                String sum = rs.getString("COUNT(id)");
                jtxtpltn.setText(sum);
            }
            //==================== PUROK 2 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE purok ='Purok 2'");
            ResultSet dos = pst.executeQuery();
            if (dos.next()) {
                String purokdos = dos.getString("COUNT(id)");
                jtxtpltn1.setText(purokdos);
            }
            //==================== PUROK 3 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE purok ='Purok 3'");
            ResultSet tres = pst.executeQuery();
            if (tres.next()) {
                String puroktres = tres.getString("COUNT(id)");
                jtxtpltn2.setText(puroktres);
            }
            //==================== PUROK 4 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE purok ='Purok 4'");
            ResultSet kwatro = pst.executeQuery();
            if (kwatro.next()) {
                String purokkwatro = kwatro.getString("COUNT(id)");
                jtxtpltn3.setText(purokkwatro);
            }
            //==================== PUROK 5 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE purok ='Purok 5'");
            ResultSet singko = pst.executeQuery();
            if (singko.next()) {
                String puroksingko = singko.getString("COUNT(id)");
                jtxtpltn4.setText(puroksingko);
            }
            //==================== PUROK 6 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE purok ='Purok 6'");
            ResultSet sais = pst.executeQuery();
            if (sais.next()) {
                String puroksais = sais.getString("COUNT(id)");
                jtxtpltn5.setText(puroksais);
            }
            //==================== PUROK 7 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE purok ='Purok 7'");
            ResultSet syete = pst.executeQuery();
            if (syete.next()) {
                String puroksyete = syete.getString("COUNT(id)");
                jtxtpltn6.setText(puroksyete);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void hsecount() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
//            pst = con.prepareStatement("SELECT COUNT(id) FROM resident");
//            pst = con.prepareStatement("SELECT COUNT(id), hno FROM resident GROUP BY hno HAVING (hno)>= 1;");
//            SELECT COUNT(CustomerID), CountryFROM CustomersGROUP BY CountryHAVING COUNT(CustomerID) > 5;
            //==================== PUROK 1 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT hno) FROM resident WHERE purok ='Purok 1';");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String result = rs.getString("COUNT(DISTINCT hno)");
                jtxtHse.setText(result);
            }
            //==================== PUROK 2 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT hno) FROM resident WHERE purok ='Purok 2';");
            ResultSet dos = pst.executeQuery();
            if (dos.next()) {
                String result = dos.getString("COUNT(DISTINCT hno)");
                jtxtHse1.setText(result);
            }
            //==================== PUROK 3 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT hno) FROM resident WHERE purok ='Purok 3';");
            ResultSet tres = pst.executeQuery();
            if (tres.next()) {
                String result = tres.getString("COUNT(DISTINCT hno)");
                jtxtHse2.setText(result);
            }
            //==================== PUROK 4 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT hno) FROM resident WHERE purok ='Purok 4';");
            ResultSet kwatro = pst.executeQuery();
            if (kwatro.next()) {
                String result = kwatro.getString("COUNT(DISTINCT hno)");
                jtxtHse3.setText(result);
            }
            //==================== PUROK 5 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT hno) FROM resident WHERE purok ='Purok 5';");
            ResultSet singko = pst.executeQuery();
            if (singko.next()) {
                String result = singko.getString("COUNT(DISTINCT hno)");
                jtxtHse4.setText(result);
            }
            //==================== PUROK 6 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT hno) FROM resident WHERE purok ='Purok 6';");
            ResultSet sais = pst.executeQuery();
            if (sais.next()) {
                String result = sais.getString("COUNT(DISTINCT hno)");
                jtxtHse5.setText(result);
            }
            //==================== PUROK 7 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT hno) FROM resident WHERE purok ='Purok 6';");
            ResultSet syete = pst.executeQuery();
            if (syete.next()) {
                String result = syete.getString("COUNT(DISTINCT hno)");
                jtxtHse6.setText(result);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void fnocount() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            //==================== PUROK 1 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT fno) FROM resident WHERE purok ='Purok 1';");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String result = rs.getString("COUNT(DISTINCT fno)");
                jtxtfno.setText(result);
            }
            //==================== PUROK 2 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT fno) FROM resident WHERE purok ='Purok 2';");

            ResultSet dos = pst.executeQuery();
            if (dos.next()) {
                String result = dos.getString("COUNT(DISTINCT fno)");
                jtxtfno1.setText(result);
            }
            //==================== PUROK 3 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT fno) FROM resident WHERE purok ='Purok 3';");
            ResultSet tres = pst.executeQuery();
            if (tres.next()) {
                String result = tres.getString("COUNT(DISTINCT fno)");
                jtxtfno2.setText(result);
            }
            //==================== PUROK 4 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT fno) FROM resident WHERE purok ='Purok 4';");
            ResultSet kwatro = pst.executeQuery();
            if (kwatro.next()) {
                String result = kwatro.getString("COUNT(DISTINCT fno)");
                jtxtfno3.setText(result);
            }
            //==================== PUROK 5 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT fno) FROM resident WHERE purok ='Purok 5';");
            ResultSet singko = pst.executeQuery();
            if (singko.next()) {
                String result = singko.getString("COUNT(DISTINCT fno)");
                jtxtfno4.setText(result);
            }
            //==================== PUROK 6 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT fno) FROM resident WHERE purok ='Purok 6';");
            ResultSet sais = pst.executeQuery();
            if (sais.next()) {
                String result = sais.getString("COUNT(DISTINCT fno)");
                jtxtfno5.setText(result);
            }
            //==================== PUROK 7 =======================
            pst = con.prepareStatement("SELECT COUNT(DISTINCT fno) FROM resident WHERE purok ='Purok 7';");
            ResultSet syete = pst.executeQuery();
            if (syete.next()) {
                String result = syete.getString("COUNT(DISTINCT fno)");
                jtxtfno6.setText(result);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void malecount() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            //==================== PUROK 1 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'Male' AND purok ='Purok 1';");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String result = rs.getString("COUNT(id)");
                jtxtMale.setText(result);
            }
            //==================== PUROK 2 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'Male' AND purok ='Purok 2';");
            ResultSet dos = pst.executeQuery();
            if (dos.next()) {
                String result = dos.getString("COUNT(id)");
                jtxtMale1.setText(result);
            }
            //==================== PUROK 3 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'Male' AND purok ='Purok 3';");
            ResultSet tres = pst.executeQuery();
            if (tres.next()) {
                String result = tres.getString("COUNT(id)");
                jtxtMale2.setText(result);
            }
            //==================== PUROK 4 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'Male' AND purok ='Purok 4';");
            ResultSet kwatro = pst.executeQuery();
            if (kwatro.next()) {
                String result = kwatro.getString("COUNT(id)");
                jtxtMale3.setText(result);
            }
            //==================== PUROK 5 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'Male' AND purok ='Purok 5';");
            ResultSet singko = pst.executeQuery();
            if (singko.next()) {
                String result = singko.getString("COUNT(id)");
                jtxtMale4.setText(result);
            }
            //==================== PUROK 6 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'Male' AND purok ='Purok 6';");
            ResultSet sais = pst.executeQuery();
            if (sais.next()) {
                String result = sais.getString("COUNT(id)");
                jtxtMale5.setText(result);
            }
            //==================== PUROK 7 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'Male' AND purok ='Purok 7';");
            ResultSet syete = pst.executeQuery();
            if (syete.next()) {
                String result = syete.getString("COUNT(id)");
                jtxtMale6.setText(result);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void femalecount() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            //==================== PUROK 1 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'female' AND purok ='Purok 1';");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String result = rs.getString("COUNT(id)");
                jtxtFemale.setText(result);
            }
            //==================== PUROK 2 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'female' AND purok ='Purok 2';");
            ResultSet dos = pst.executeQuery();
            if (dos.next()) {
                String result = dos.getString("COUNT(id)");
                jtxtFemale1.setText(result);
            }
            //==================== PUROK 3 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'female' AND purok ='Purok 3';");
            ResultSet tres = pst.executeQuery();
            if (tres.next()) {
                String result = tres.getString("COUNT(id)");
                jtxtFemale2.setText(result);
            }
            //==================== PUROK 4 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'female' AND purok ='Purok 4';");
            ResultSet kwatro = pst.executeQuery();
            if (kwatro.next()) {
                String result = kwatro.getString("COUNT(id)");
                jtxtFemale3.setText(result);
            }
            //==================== PUROK 5 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'female' AND purok ='Purok 5';");
            ResultSet singko = pst.executeQuery();
            if (singko.next()) {
                String result = singko.getString("COUNT(id)");
                jtxtFemale4.setText(result);
            }
            //==================== PUROK 6 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'female' AND purok ='Purok 6';");
            ResultSet sais = pst.executeQuery();
            if (sais.next()) {
                String result = sais.getString("COUNT(id)");
                jtxtFemale5.setText(result);
            }
            //==================== PUROK 7 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE gender = 'female' AND purok ='Purok 7';");
            ResultSet syete = pst.executeQuery();
            if (syete.next()) {
                String result = syete.getString("COUNT(id)");
                jtxtFemale6.setText(result);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void seniorctzn() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            //==================== PUROK 1 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE age >= 60 AND purok ='Purok 1';");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String result = rs.getString("COUNT(id)");
                jtxtSnrctzn.setText(result);
            }
            //==================== PUROK 2 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE age >= 60 AND purok ='Purok 2';");
            ResultSet dos = pst.executeQuery();
            if (dos.next()) {
                String result = dos.getString("COUNT(id)");
                jtxtSnrctzn1.setText(result);
            }
            //==================== PUROK 3 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE age >= 60 AND purok ='Purok 3';");
            ResultSet tres = pst.executeQuery();
            if (tres.next()) {
                String result = tres.getString("COUNT(id)");
                jtxtSnrctzn2.setText(result);
            }
            //==================== PUROK 4 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE age >= 60 AND purok ='Purok 4';");
            ResultSet kwatro = pst.executeQuery();
            if (kwatro.next()) {
                String result = kwatro.getString("COUNT(id)");
                jtxtSnrctzn3.setText(result);
            }
            //==================== PUROK 5 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE age >= 60 AND purok ='Purok 5';");
            ResultSet singko = pst.executeQuery();
            if (singko.next()) {
                String result = singko.getString("COUNT(id)");
                jtxtSnrctzn4.setText(result);
            }
            //==================== PUROK 6 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE age >= 60 AND purok ='Purok 6';");
            ResultSet sais = pst.executeQuery();
            if (sais.next()) {
                String result = sais.getString("COUNT(id)");
                jtxtSnrctzn5.setText(result);
            }
            //==================== PUROK 7 =======================
            pst = con.prepareStatement("SELECT COUNT(id) FROM resident WHERE age >= 60 AND purok ='Purok 7';");
            ResultSet syete = pst.executeQuery();
            if (rs.next()) {
                String result = syete.getString("COUNT(id)");
                jtxtSnrctzn6.setText(result);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void tableupdate() {
        try {
            int c;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM resident");

            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();
            DefaultTableModel d = (DefaultTableModel) jtbladdresident.getModel();
            DefaultTableModel e = (DefaultTableModel) jtxtbeditresident.getModel();
            DefaultTableModel f = (DefaultTableModel) jtblviewresident.getModel();

            d.setRowCount(0);
            e.setRowCount(0);
            f.setRowCount(0);
//            g.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("sname"));
                    v2.add(rs.getString("fname"));
                    v2.add(rs.getString("mname"));
                    v2.add(rs.getString("dateofbirth"));
                    v2.add(rs.getString("age"));
                    v2.add(rs.getString("gender"));
                    v2.add(rs.getString("plcofbirth"));
                    v2.add(rs.getString("rwheadfamily"));
                    v2.add(rs.getString("contactno"));
                    v2.add(rs.getString("civilstat"));
                    v2.add(rs.getString("edcattmnt"));
                    v2.add(rs.getString("occpt"));
                    v2.add(rs.getString("rlgn"));
                    v2.add(rs.getString("ctz"));
                    v2.add(rs.getString("fno"));
                    v2.add(rs.getString("hno"));
                    v2.add(rs.getString("purok"));
                    v2.add(rs.getString("forpsmem"));
                    v2.add(rs.getString("emailadd"));
                    v2.add(rs.getString("dateadded"));
                }

                d.addRow(v2);
                e.addRow(v2);
                f.addRow(v2);
//                g.addRow(v2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void removeres() {
        try {
            int c;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM removeres");

            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();
            DefaultTableModel d = (DefaultTableModel) jtbladdresident1.getModel();

            d.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("sname"));
                    v2.add(rs.getString("fname"));
                    v2.add(rs.getString("mname"));
                    v2.add(rs.getString("dateofbirth"));
                    v2.add(rs.getString("age"));
                    v2.add(rs.getString("gender"));
                    v2.add(rs.getString("plcofbirth"));
                    v2.add(rs.getString("rwheadfamily"));
                    v2.add(rs.getString("contactno"));
                    v2.add(rs.getString("civilstat"));
                    v2.add(rs.getString("edcattmnt"));
                    v2.add(rs.getString("occpt"));
                    v2.add(rs.getString("rlgn"));
                    v2.add(rs.getString("ctz"));
                    v2.add(rs.getString("fno"));
                    v2.add(rs.getString("hno"));
                    v2.add(rs.getString("purok"));
                    v2.add(rs.getString("forpsmem"));
                    v2.add(rs.getString("emailadd"));
                    v2.add(rs.getString("dateadded"));
                    v2.add(rs.getString("dateremoved"));
                    v2.add(rs.getString("issue"));

                }

                d.addRow(v2);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void tblfourpsmem() {
        try {
            int c;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM resident WHERE forpsmem= 'Yes' ");

            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();

            DefaultTableModel d = (DefaultTableModel) jtblforpsmem.getModel();
            d.setRowCount(0);

//            g.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("sname"));
                    v2.add(rs.getString("fname"));
                    v2.add(rs.getString("mname"));
                    v2.add(rs.getString("dateofbirth"));
                    v2.add(rs.getString("age"));
                    v2.add(rs.getString("gender"));
                    v2.add(rs.getString("plcofbirth"));
                    v2.add(rs.getString("rwheadfamily"));
                    v2.add(rs.getString("contactno"));
                    v2.add(rs.getString("civilstat"));
                    v2.add(rs.getString("edcattmnt"));
                    v2.add(rs.getString("occpt"));
                    v2.add(rs.getString("rlgn"));
                    v2.add(rs.getString("ctz"));
                    v2.add(rs.getString("fno"));
                    v2.add(rs.getString("hno"));
                    v2.add(rs.getString("purok"));
                    v2.add(rs.getString("dateadded"));
                }

                d.addRow(v2);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void logs() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM logs");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblBp1.getModel();
            b.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {
                    v2.add(rs.getString("sname"));
                    v2.add(rs.getString("fname"));
                    v2.add(rs.getString("mname"));
                    v2.add(rs.getString("age"));
                    v2.add(rs.getString("contactnum"));
                    v2.add(rs.getString("concern"));
                    v2.add(rs.getString("date"));
                }

                b.addRow(v2);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
}

    private void tblupdate() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM bprecord");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblBp.getModel();
            b.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {
                    v2.add(rs.getString("name"));
                    v2.add(rs.getString("age"));
                    v2.add(rs.getString("contactno"));
                    v2.add(rs.getString("bp"));
                    v2.add(rs.getString("level"));
                    v2.add(rs.getString("date"));
                }

                b.addRow(v2);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void tblupdatemed() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM medicine");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblMedicine.getModel();
            DefaultTableModel c = (DefaultTableModel) jtblMedicine1.getModel();
            DefaultTableModel d = (DefaultTableModel) jtblMedicine2.getModel();
            DefaultTableModel e = (DefaultTableModel) jtblMedicine3.getModel();

            b.setRowCount(0);
            c.setRowCount(0);
            d.setRowCount(0);
            e.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("medname"));
                    v2.add(rs.getString("type"));
                    v2.add(rs.getString("descrip"));
                    v2.add(rs.getString("stocks"));
                }

                b.addRow(v2);
                c.addRow(v2);
                d.addRow(v2);
                e.addRow(v2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void tblupdatemedinfo() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM medrec");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblMedicine4.getModel();

//            DefaultTableModel c = (DefaultTableModel) jtblMedicine1.getModel();
            b.setRowCount(0);
//            c.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("medname"));
                    v2.add(rs.getString("type"));
                    v2.add(rs.getString("descrip"));
                    v2.add(rs.getString("quantity"));
                    v2.add(rs.getString("dateissued"));
                    v2.add(rs.getString("suppname"));
                    v2.add(rs.getString("expirydate"));

                }

                b.addRow(v2);
//                c.addRow(v2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void medremove() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM medrem");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblMedicine6.getModel();
//            DefaultTableModel c = (DefaultTableModel) jtblMedicine1.getModel();
            b.setRowCount(0);
//            c.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {

                    v2.add(rs.getString("medname"));
                    v2.add(rs.getString("quant"));
                    v2.add(rs.getString("medissue"));
                    v2.add(rs.getString("daterem"));
                }

                b.addRow(v2);
//                c.addRow(v2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void medrelease() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM medrel");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblMedicine5.getModel();
//            DefaultTableModel c = (DefaultTableModel) jtblMedicine1.getModel();
            b.setRowCount(0);
//            c.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("resname"));
                    v2.add(rs.getString("age"));
                    v2.add(rs.getString("concern"));
                    v2.add(rs.getString("quant"));
                    v2.add(rs.getString("medname"));
                    v2.add(rs.getString("daterel"));

                }

                b.addRow(v2);
//                c.addRow(v2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void pregnancyrec() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM pregnancyrec");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblPregrec.getModel();

            b.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {
                    v2.add(rs.getString("nameofparent"));
                    v2.add(rs.getString("dateofbirth"));
                    v2.add(rs.getString("age"));
                    v2.add(rs.getString("hsno"));
                    v2.add(rs.getString("fno"));
                    v2.add(rs.getString("purok"));
                    v2.add(rs.getString("mobilenum"));
                    v2.add(rs.getString("pih"));
                    v2.add(rs.getString("lmp"));
                    v2.add(rs.getString("edc"));
                    v2.add(rs.getString("gp"));
                    v2.add(rs.getString("lstcheckup"));
                    v2.add(rs.getString("datedlvrd"));
                    v2.add(rs.getString("nameofchild"));

                }

                b.addRow(v2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void birthrec() {
        try {
            int a;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM birthrec");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            DefaultTableModel b = (DefaultTableModel) jtblBirthreg.getModel();

            b.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= a; i++) {
                    v2.add(rs.getString("nameofchild"));
                    v2.add(rs.getString("sname"));
                    v2.add(rs.getString("mname"));
                    v2.add(rs.getString("nameofparent"));
                    v2.add(rs.getString("dateofbirth"));
                    v2.add(rs.getString("hsno"));
                    v2.add(rs.getString("fno"));
                    v2.add(rs.getString("purok"));
                    v2.add(rs.getString("gender"));
                    v2.add(rs.getString("birthweight"));
                    v2.add(rs.getString("plcofbirth"));
                    v2.add(rs.getString("registered"));

                }

                b.addRow(v2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jtxtMname = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jtxtAge2 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jtxtSname1 = new javax.swing.JLabel();
        jtxtDateiofbirth = new javax.swing.JLabel();
        jtxtGender = new javax.swing.JLabel();
        jtxtPlaceofBirth = new javax.swing.JLabel();
        jtxtrelwfamhead = new javax.swing.JLabel();
        jtxtCivilstat = new javax.swing.JLabel();
        jtxtEducattmnt = new javax.swing.JLabel();
        jtxtOccptn = new javax.swing.JLabel();
        jtxtRgln = new javax.swing.JLabel();
        jtxtCtzn = new javax.swing.JLabel();
        jtxtDateadded = new javax.swing.JLabel();
        jtxtFname = new javax.swing.JLabel();
        jtxtFno2 = new javax.swing.JLabel();
        jtxtHno2 = new javax.swing.JLabel();
        jtxtPurok = new javax.swing.JLabel();
        jtxtforpsmem = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        jtxtContnum = new javax.swing.JLabel();
        jtxtEmailaddviewres = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtblviewresident = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jtxtpltn = new javax.swing.JLabel();
        jtxtHse = new javax.swing.JLabel();
        jtxtMale = new javax.swing.JLabel();
        jtxtfno = new javax.swing.JLabel();
        jtxtFemale = new javax.swing.JLabel();
        jtxtSnrctzn = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jtxtpltn1 = new javax.swing.JLabel();
        jtxtfno1 = new javax.swing.JLabel();
        jtxtHse1 = new javax.swing.JLabel();
        jtxtMale1 = new javax.swing.JLabel();
        jtxtFemale1 = new javax.swing.JLabel();
        jtxtSnrctzn1 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jtxtpltn2 = new javax.swing.JLabel();
        jtxtfno2 = new javax.swing.JLabel();
        jtxtHse2 = new javax.swing.JLabel();
        jtxtMale2 = new javax.swing.JLabel();
        jtxtFemale2 = new javax.swing.JLabel();
        jtxtSnrctzn2 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jtxtpltn3 = new javax.swing.JLabel();
        jtxtfno3 = new javax.swing.JLabel();
        jtxtHse3 = new javax.swing.JLabel();
        jtxtMale3 = new javax.swing.JLabel();
        jtxtFemale3 = new javax.swing.JLabel();
        jtxtSnrctzn3 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel138 = new javax.swing.JLabel();
        jtxtpltn4 = new javax.swing.JLabel();
        jtxtfno4 = new javax.swing.JLabel();
        jtxtHse4 = new javax.swing.JLabel();
        jtxtMale4 = new javax.swing.JLabel();
        jtxtFemale4 = new javax.swing.JLabel();
        jtxtSnrctzn4 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel139 = new javax.swing.JLabel();
        jtxtpltn5 = new javax.swing.JLabel();
        jtxtfno5 = new javax.swing.JLabel();
        jtxtHse5 = new javax.swing.JLabel();
        jtxtMale5 = new javax.swing.JLabel();
        jtxtFemale5 = new javax.swing.JLabel();
        jtxtSnrctzn5 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        jtxtpltn6 = new javax.swing.JLabel();
        jtxtfno6 = new javax.swing.JLabel();
        jtxtHse6 = new javax.swing.JLabel();
        jtxtMale6 = new javax.swing.JLabel();
        jtxtFemale6 = new javax.swing.JLabel();
        jtxtSnrctzn6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jtxtSname2 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jtxtFname1 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jtxtMname1 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jtxtDateiofbirth1 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jtxtAge3 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jtxtGender1 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jtxtPlaceofBirth1 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jtxtrelwfamhead1 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jtxtCivilstat1 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jtxtDateadded1 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jtxtPurok1 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jtxtHno3 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jtxtFno3 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jtxtCtzn1 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jtxtRgln1 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jtxtOccptn1 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jtxtEducattmnt1 = new javax.swing.JLabel();
        jtxtContactnum1 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtblforpsmem = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        jLabel184 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jcmbsystolic = new javax.swing.JComboBox<>();
        jcmbdiastolic = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jtxtresname = new javax.swing.JTextField();
        jtxtage = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblBp = new javax.swing.JTable();
        jTextField14 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jtxtBP = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jtxtBP1 = new javax.swing.JTextField();
        jLabel183 = new javax.swing.JLabel();
        jtxtContnum1 = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtAgepreg = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jtxtHouseno = new javax.swing.JTextField();
        jtxtEDC = new javax.swing.JTextField();
        jtxtFamilyno = new javax.swing.JTextField();
        jtxtMobilenum = new javax.swing.JTextField();
        jtxtPih = new javax.swing.JTextField();
        jtxtNameofparent = new javax.swing.JTextField();
        jdateDlvrd = new com.toedter.calendar.JDateChooser();
        jdateLstchckup = new com.toedter.calendar.JDateChooser();
        jtxtNameofchild = new javax.swing.JTextField();
        jcmbPurokpreg = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblPregrec = new javax.swing.JTable();
        jbtnpregclear = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jDateLMP = new com.toedter.calendar.JDateChooser();
        jtxtGp = new javax.swing.JTextField();
        jDateofbirth = new com.toedter.calendar.JDateChooser();
        jButton23 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jtxtHsnobirthreg = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblBirthreg = new javax.swing.JTable();
        jtxtFnobirthreg = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jbtnBirthReg = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jtxtNameofChildreg = new javax.swing.JLabel();
        jtxtNameofparentreg = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtbladdresident1 = new javax.swing.JTable();
        jbtnsearchaddres1 = new javax.swing.JButton();
        jtxtSearchaddres1 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jcmbPurok5 = new javax.swing.JComboBox<>();
        jLabel248 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jtxtMname2 = new javax.swing.JLabel();
        jLabel250 = new javax.swing.JLabel();
        jLabel251 = new javax.swing.JLabel();
        jLabel252 = new javax.swing.JLabel();
        jLabel253 = new javax.swing.JLabel();
        jtxtAge5 = new javax.swing.JLabel();
        jLabel254 = new javax.swing.JLabel();
        jLabel255 = new javax.swing.JLabel();
        jLabel256 = new javax.swing.JLabel();
        jLabel257 = new javax.swing.JLabel();
        jLabel258 = new javax.swing.JLabel();
        jLabel259 = new javax.swing.JLabel();
        jLabel260 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        jLabel262 = new javax.swing.JLabel();
        jLabel263 = new javax.swing.JLabel();
        jLabel264 = new javax.swing.JLabel();
        jLabel265 = new javax.swing.JLabel();
        jLabel266 = new javax.swing.JLabel();
        jLabel267 = new javax.swing.JLabel();
        jLabel268 = new javax.swing.JLabel();
        jtxtSname3 = new javax.swing.JLabel();
        jtxtDateiofbirth2 = new javax.swing.JLabel();
        jtxtGender2 = new javax.swing.JLabel();
        jtxtPlaceofBirth2 = new javax.swing.JLabel();
        jtxtrelwfamhead2 = new javax.swing.JLabel();
        jtxtCivilstat2 = new javax.swing.JLabel();
        jtxtEducattmnt2 = new javax.swing.JLabel();
        jtxtOccptn2 = new javax.swing.JLabel();
        jtxtRgln2 = new javax.swing.JLabel();
        jtxtCtzn2 = new javax.swing.JLabel();
        jtxtDateadded2 = new javax.swing.JLabel();
        jtxtFname2 = new javax.swing.JLabel();
        jtxtFno5 = new javax.swing.JLabel();
        jtxtHno5 = new javax.swing.JLabel();
        jtxtPurok2 = new javax.swing.JLabel();
        jtxtforpsmem1 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        jtxtContnum2 = new javax.swing.JLabel();
        jtxtEmailaddviewres1 = new javax.swing.JLabel();
        jLabel270 = new javax.swing.JLabel();
        jLabel271 = new javax.swing.JLabel();
        jtxtDateadded3 = new javax.swing.JLabel();
        jtxtDateadded4 = new javax.swing.JLabel();
        jLabel272 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel273 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jtxtplcbirth = new javax.swing.JTextField();
        jtxtfname = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jtxtAge = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jtxtsname = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jtxtOcptn = new javax.swing.JTextField();
        jtxCtzn = new javax.swing.JTextField();
        jtxtFno = new javax.swing.JTextField();
        jtxtHno = new javax.swing.JTextField();
        jDateChooserbirth = new com.toedter.calendar.JDateChooser();
        jcmb4ps = new javax.swing.JComboBox<>();
        jcmbgender = new javax.swing.JComboBox<>();
        jcmbPurok = new javax.swing.JComboBox<>();
        jtxtrwfamilyhead = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jbtnAddres = new javax.swing.JButton();
        jbtnSaveaddresident = new javax.swing.JButton();
        jtxtcivilstatus = new javax.swing.JComboBox<>();
        jtxtmname = new javax.swing.JTextField();
        jtxtDateadd = new javax.swing.JTextField();
        jLabel192 = new javax.swing.JLabel();
        jtxtrContactnum = new javax.swing.JTextField();
        jLabel148 = new javax.swing.JLabel();
        jtxtEmailaddress = new javax.swing.JTextField();
        jtxteducationalattmnt = new javax.swing.JComboBox<>();
        jtxtRlgn = new javax.swing.JComboBox<>();
        issue1 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtbladdresident = new javax.swing.JTable();
        jtxtSearchaddres = new javax.swing.JTextField();
        jbtnsearchaddres = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jcmbPurok2 = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jtxtsname1 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jtxtOcptn1 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jtxtfname1 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jtxtmname1 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jtxCtzn1 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jDateChooserbirth1 = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jtxtAge1 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jcmbgender1 = new javax.swing.JComboBox<>();
        jLabel82 = new javax.swing.JLabel();
        jtxtplcbirth1 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jtxtrwfamilyhead1 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jtxtcivilstatus1 = new javax.swing.JComboBox<>();
        jbtnSaveaddresident1 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        jcmb4ps1 = new javax.swing.JComboBox<>();
        jLabel87 = new javax.swing.JLabel();
        jcmbPurok1 = new javax.swing.JComboBox<>();
        jLabel88 = new javax.swing.JLabel();
        jtxtHno1 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jtxtFno1 = new javax.swing.JTextField();
        jtxtrwfamilyhead2 = new com.toedter.calendar.JDateChooser();
        jLabel216 = new javax.swing.JLabel();
        jtxtContactno = new javax.swing.JTextField();
        jLabel147 = new javax.swing.JLabel();
        jtxtEmailaddeditres = new javax.swing.JTextField();
        jtxteducationalattmnt1 = new javax.swing.JComboBox<>();
        jtxtRlgn1 = new javax.swing.JComboBox<>();
        jbtnSaveaddresident2 = new javax.swing.JButton();
        issue = new javax.swing.JLabel();
        jbtnSaveaddresident3 = new javax.swing.JButton();
        issue2 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtxtbeditresident = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jTextField37 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jcmbPurok3 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel56 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel186 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jtxtStocks = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jbtnUpdatedmed = new javax.swing.JButton();
        jbtnAddmdcn = new javax.swing.JButton();
        jbtnMedrecordclear = new javax.swing.JButton();
        jtxtDesc = new javax.swing.JTextField();
        jtxtType = new javax.swing.JTextField();
        jtxtMedname = new javax.swing.JTextField();
        jLabel194 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtblMedicine = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jbtnMedsave = new javax.swing.JButton();
        jtxtDesc1 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jtxtDateexp = new com.toedter.calendar.JDateChooser();
        jtxtQuant = new javax.swing.JTextField();
        jtxtDatrecvd = new com.toedter.calendar.JDateChooser();
        jLabel198 = new javax.swing.JLabel();
        jtxtMedname1 = new javax.swing.JLabel();
        jtxtType1 = new javax.swing.JLabel();
        jtxtDescmed = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtblMedicine1 = new javax.swing.JTable();
        jButton25 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jPanel50 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel203 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        jbtnMedsave1 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jtxtMednamerel = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jDaterel = new com.toedter.calendar.JDateChooser();
        jtxtQuantrel = new javax.swing.JTextField();
        jtxtMedStocksrel = new javax.swing.JLabel();
        jtxtAgerel = new javax.swing.JTextField();
        jtxtResname = new javax.swing.JTextField();
        jtxtConcern = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtblMedicine2 = new javax.swing.JTable();
        jButton27 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jPanel51 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel211 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jbtnRemovemed = new javax.swing.JButton();
        jLabel218 = new javax.swing.JLabel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        jtxtDescrem = new javax.swing.JLabel();
        jtxtTyperem = new javax.swing.JLabel();
        jtxtMednamerem = new javax.swing.JLabel();
        jtxtStocksrem = new javax.swing.JLabel();
        jtxtMedissue = new javax.swing.JTextField();
        jtxtQuantrem = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtblMedicine3 = new javax.swing.JTable();
        jButton29 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jPanel52 = new javax.swing.JPanel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel212 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jtxtDateissued = new javax.swing.JLabel();
        jtxtExpdate = new javax.swing.JLabel();
        jxtMednameview = new javax.swing.JLabel();
        jtxtTypeview = new javax.swing.JLabel();
        jtxtMedID = new javax.swing.JLabel();
        jtxtDescview = new javax.swing.JLabel();
        jtxtSuppName = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jxtQuanttview = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtblMedicine4 = new javax.swing.JTable();
        jButton30 = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        jPanel53 = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel232 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        jLabel235 = new javax.swing.JLabel();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        jtxtAgerelmed = new javax.swing.JLabel();
        jtxtMedconcern = new javax.swing.JLabel();
        jLabel239 = new javax.swing.JLabel();
        jtxtMednamerelease = new javax.swing.JLabel();
        jtxtMedquant = new javax.swing.JLabel();
        jtxtDaterelease = new javax.swing.JLabel();
        jtxtResnamemed = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jtblMedicine5 = new javax.swing.JTable();
        jButton31 = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        jPanel54 = new javax.swing.JPanel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel238 = new javax.swing.JLabel();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        jLabel243 = new javax.swing.JLabel();
        jLabel244 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jtxtDateremoved = new javax.swing.JLabel();
        jtxtRemovemdecine = new javax.swing.JLabel();
        jLabel249 = new javax.swing.JLabel();
        jtxtQuantremove = new javax.swing.JLabel();
        jtxtMedissuerem = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtblMedicine6 = new javax.swing.JTable();
        jButton32 = new javax.swing.JButton();
        jTextField26 = new javax.swing.JTextField();
        jPanel55 = new javax.swing.JPanel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        usernameAcc = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jPanel66 = new javax.swing.JPanel();
        jLabel159 = new javax.swing.JLabel();
        jtxtnusername = new javax.swing.JTextField();
        jLabel160 = new javax.swing.JLabel();
        jcmbqstn = new javax.swing.JComboBox<>();
        jLabel173 = new javax.swing.JLabel();
        jtxtans = new javax.swing.JTextField();
        jLabel174 = new javax.swing.JLabel();
        jtxtnpass = new javax.swing.JPasswordField();
        jLabel175 = new javax.swing.JLabel();
        jcheckboxacc = new javax.swing.JCheckBox();
        jbtnupdate = new javax.swing.JButton();
        jPanel67 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jtblBp1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jtxtDateup = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1918, 1000));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/bh1200.png"))); // NOI18N

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addComponent(jLabel4)
                .addContainerGap(352, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 844, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Home", jPanel1);

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jPanel24.setBackground(new java.awt.Color(153, 204, 255));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Resident Information Record");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(738, 738, 738))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel49.setText("Personal Information");

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtMname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMname.setText(" ");
        jPanel26.add(jtxtMname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 161, 270, 36));

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel94.setText("First Name :");
        jPanel26.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 103, 110, -1));

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel95.setText("Family Head :");
        jPanel26.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 459, 122, -1));

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel96.setText("Middle Name :");
        jPanel26.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 167, 130, -1));

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel97.setText("Date of Birth :");
        jPanel26.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 224, -1, -1));

        jtxtAge2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtAge2.setText(" ");
        jPanel26.add(jtxtAge2, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 280, 90, 30));

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel98.setText("Gender :");
        jPanel26.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 340, 74, 30));

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel99.setText("Date Added :");
        jPanel26.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 543, 123, 30));

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel100.setText("Relationship w/  the ");
        jPanel26.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 426, 180, 30));

        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel101.setText("Place of Birth :");
        jPanel26.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 388, 130, -1));

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel102.setText("Civil Status :");
        jPanel26.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 543, -1, 30));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel103.setText("Educational Attainment :");
        jPanel26.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 47, -1, 30));

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel104.setText("Occupation :");
        jPanel26.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 106, 110, -1));

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel105.setText("Religion :");
        jPanel26.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 139, -1, 30));

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel106.setText("Citizenship :");
        jPanel26.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 219, 110, 30));

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel107.setText("Family No. :");
        jPanel26.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 291, 110, 30));

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel108.setText("Household No. :");
        jPanel26.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 351, 140, 30));

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel109.setText("Purok :");
        jPanel26.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, -1, 30));

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel110.setText("4P's Member :");
        jPanel26.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 461, 131, -1));

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel111.setText("Age :");
        jPanel26.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 280, 50, 30));

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel112.setText("Surname :");
        jPanel26.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 47, 90, -1));

        jtxtSname1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtSname1.setText(" ");
        jPanel26.add(jtxtSname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 50, 270, -1));

        jtxtDateiofbirth.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel26.add(jtxtDateiofbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 224, 270, 31));

        jtxtGender.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtGender.setText(" ");
        jPanel26.add(jtxtGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 342, 102, 27));

        jtxtPlaceofBirth.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel26.add(jtxtPlaceofBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 388, 270, 25));

        jtxtrelwfamhead.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtrelwfamhead.setText(" ");
        jPanel26.add(jtxtrelwfamhead, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 456, 189, 30));

        jtxtCivilstat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel26.add(jtxtCivilstat, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 543, 189, 30));

        jtxtEducattmnt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtEducattmnt.setText(" ");
        jPanel26.add(jtxtEducattmnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 50, 260, -1));

        jtxtOccptn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtOccptn.setText(" ");
        jPanel26.add(jtxtOccptn, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 106, 260, -1));

        jtxtRgln.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtRgln.setText(" ");
        jPanel26.add(jtxtRgln, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 142, 260, -1));

        jtxtCtzn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtCtzn.setText(" ");
        jPanel26.add(jtxtCtzn, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 222, 97, -1));

        jtxtDateadded.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateadded.setText(" ");
        jPanel26.add(jtxtDateadded, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 546, 260, -1));

        jtxtFname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtFname.setText(" ");
        jPanel26.add(jtxtFname, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 103, 286, 30));

        jtxtFno2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtFno2.setText(" ");
        jPanel26.add(jtxtFno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 294, 90, -1));

        jtxtHno2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtHno2.setText(" ");
        jPanel26.add(jtxtHno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 354, 90, -1));

        jtxtPurok.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtPurok.setText(" ");
        jPanel26.add(jtxtPurok, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 402, 90, -1));

        jtxtforpsmem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtforpsmem.setText(" ");
        jPanel26.add(jtxtforpsmem, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 461, 90, -1));

        jLabel222.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel222.setText("Contact No. :");
        jPanel26.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 501, -1, 30));

        jtxtContnum.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel26.add(jtxtContnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 501, 213, 30));

        jtxtEmailaddviewres.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtEmailaddviewres.setText(" ");
        jPanel26.add(jtxtEmailaddviewres, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 504, 260, -1));

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel149.setText("Email Address :");
        jPanel26.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 501, -1, 30));

        jtblviewresident.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Surname", "First Name", "Middle Name", "Date of Birth", "Age", "Gender", "Place of Birth", "RelationShip W/ the head of the Family", "Contact Number", "Civil Status", "Educational Attainment", "Occupation", "Religion", "Citezenship", "Family NO.", "Household NO.", "Purok", "4P'S member", "Email Address", "Date Added"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblviewresident.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtblviewresident.getTableHeader().setResizingAllowed(false);
        jtblviewresident.getTableHeader().setReorderingAllowed(false);
        jtblviewresident.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblviewresidentMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jtblviewresident);
        if (jtblviewresident.getColumnModel().getColumnCount() > 0) {
            jtblviewresident.getColumnModel().getColumn(0).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(1).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(2).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(3).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(4).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(5).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(6).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(7).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(8).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(9).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(10).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(11).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(12).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(13).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(14).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(15).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(16).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(17).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(18).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(19).setResizable(false);
            jtblviewresident.getColumnModel().getColumn(20).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("All Resident", jPanel5);

        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel36.setBackground(new java.awt.Color(153, 204, 255));

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel133.setText("PUROK");

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel150.setIcon(new javax.swing.ImageIcon("D:\\Download\\Icon\\placeholder 2.png")); // NOI18N

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(748, 748, 748)
                .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(860, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel133)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel150, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1740, -1));

        jPanel37.setBackground(new java.awt.Color(102, 204, 255));

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel134.setText("Purok 1");

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel141.setText("Population");

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel142.setText("Families");

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel143.setText("Household");

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel144.setText("Male");

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel145.setText("Female");

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel146.setText("Senior  Citizen");

        jtxtpltn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtpltn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtpltn.setText("0");

        jtxtHse.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtHse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtHse.setText("0");

        jtxtMale.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtMale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtMale.setText("0");

        jtxtfno.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtfno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtfno.setText("0");

        jtxtFemale.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtFemale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtFemale.setText("0");

        jtxtSnrctzn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtSnrctzn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSnrctzn.setText("0");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxtpltn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel142))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jtxtfno, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(167, 167, 167)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel143)
                    .addComponent(jtxtHse, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel144)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel145)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel146)
                        .addGap(47, 47, 47))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jtxtMale, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179)
                        .addComponent(jtxtFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtSnrctzn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel141)
                    .addComponent(jLabel142)
                    .addComponent(jLabel143)
                    .addComponent(jLabel144)
                    .addComponent(jLabel145)
                    .addComponent(jLabel146))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel134)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtpltn)
                        .addComponent(jtxtHse)
                        .addComponent(jtxtMale)
                        .addComponent(jtxtfno)
                        .addComponent(jtxtFemale)
                        .addComponent(jtxtSnrctzn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1740, -1));

        jPanel38.setBackground(new java.awt.Color(153, 255, 255));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel135.setText("Purok 2");

        jtxtpltn1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtpltn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtpltn1.setText("0");

        jtxtfno1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtfno1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtfno1.setText("0");

        jtxtHse1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtHse1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtHse1.setText("0");

        jtxtMale1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtMale1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtMale1.setText("0");

        jtxtFemale1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtFemale1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtFemale1.setText("0");

        jtxtSnrctzn1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtSnrctzn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSnrctzn1.setText("0");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jtxtpltn1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(jtxtfno1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167)
                .addComponent(jtxtHse1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addComponent(jtxtMale1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(jtxtFemale1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtxtSnrctzn1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(jtxtpltn1)
                    .addComponent(jtxtfno1)
                    .addComponent(jtxtHse1)
                    .addComponent(jtxtMale1)
                    .addComponent(jtxtFemale1)
                    .addComponent(jtxtSnrctzn1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1740, 70));

        jPanel39.setBackground(new java.awt.Color(102, 204, 255));

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel136.setText("Purok 3");

        jtxtpltn2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtpltn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtpltn2.setText("0");

        jtxtfno2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtfno2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtfno2.setText("0");

        jtxtHse2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtHse2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtHse2.setText("0");

        jtxtMale2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtMale2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtMale2.setText("0");

        jtxtFemale2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtFemale2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtFemale2.setText("0");

        jtxtSnrctzn2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtSnrctzn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSnrctzn2.setText("0");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jtxtpltn2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176)
                .addComponent(jtxtfno2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169)
                .addComponent(jtxtHse2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addComponent(jtxtMale2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(jtxtFemale2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtxtSnrctzn2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(jtxtpltn2)
                    .addComponent(jtxtfno2)
                    .addComponent(jtxtHse2)
                    .addComponent(jtxtMale2)
                    .addComponent(jtxtFemale2)
                    .addComponent(jtxtSnrctzn2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 1740, 80));

        jPanel40.setBackground(new java.awt.Color(153, 255, 255));

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel137.setText("Purok 4");

        jtxtpltn3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtpltn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtpltn3.setText("0");

        jtxtfno3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtfno3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtfno3.setText("0");

        jtxtHse3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtHse3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtHse3.setText("0");

        jtxtMale3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtMale3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtMale3.setText("0");

        jtxtFemale3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtFemale3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtFemale3.setText("0");

        jtxtSnrctzn3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtSnrctzn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSnrctzn3.setText("0");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jtxtpltn3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175)
                .addComponent(jtxtfno3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168)
                .addComponent(jtxtHse3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(jtxtMale3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182)
                .addComponent(jtxtFemale3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtxtSnrctzn3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137)
                    .addComponent(jtxtpltn3)
                    .addComponent(jtxtfno3)
                    .addComponent(jtxtHse3)
                    .addComponent(jtxtMale3)
                    .addComponent(jtxtFemale3)
                    .addComponent(jtxtSnrctzn3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 1740, 80));

        jPanel41.setBackground(new java.awt.Color(102, 204, 255));

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel138.setText("Purok 5");

        jtxtpltn4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtpltn4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtpltn4.setText("0");

        jtxtfno4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtfno4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtfno4.setText("0");

        jtxtHse4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtHse4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtHse4.setText("0");

        jtxtMale4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtMale4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtMale4.setText("0");

        jtxtFemale4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtFemale4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtFemale4.setText("0");

        jtxtSnrctzn4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtSnrctzn4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSnrctzn4.setText("0");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jtxtpltn4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(jtxtfno4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170)
                .addComponent(jtxtHse4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167)
                .addComponent(jtxtMale4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181)
                .addComponent(jtxtFemale4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtxtSnrctzn4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel138)
                    .addComponent(jtxtpltn4)
                    .addComponent(jtxtfno4)
                    .addComponent(jtxtHse4)
                    .addComponent(jtxtMale4)
                    .addComponent(jtxtFemale4)
                    .addComponent(jtxtSnrctzn4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 1740, 80));

        jPanel42.setBackground(new java.awt.Color(153, 255, 255));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel139.setText("Purok 6");

        jtxtpltn5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtpltn5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtpltn5.setText("0");

        jtxtfno5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtfno5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtfno5.setText("0");

        jtxtHse5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtHse5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtHse5.setText("0");

        jtxtMale5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtMale5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtMale5.setText("0");

        jtxtFemale5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtFemale5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtFemale5.setText("0");

        jtxtSnrctzn5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtSnrctzn5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSnrctzn5.setText("0");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jtxtpltn5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(jtxtfno5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169)
                .addComponent(jtxtHse5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168)
                .addComponent(jtxtMale5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182)
                .addComponent(jtxtFemale5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtxtSnrctzn5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139)
                    .addComponent(jtxtpltn5)
                    .addComponent(jtxtfno5)
                    .addComponent(jtxtHse5)
                    .addComponent(jtxtMale5)
                    .addComponent(jtxtFemale5)
                    .addComponent(jtxtSnrctzn5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 1740, 80));

        jPanel43.setBackground(new java.awt.Color(102, 204, 255));

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel140.setText("Purok 7");

        jtxtpltn6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtpltn6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtpltn6.setText("0");

        jtxtfno6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtfno6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtfno6.setText("0");

        jtxtHse6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtHse6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtHse6.setText("0");

        jtxtMale6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtMale6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtMale6.setText("0");

        jtxtFemale6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtFemale6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtFemale6.setText("0");

        jtxtSnrctzn6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtxtSnrctzn6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSnrctzn6.setText("0");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jtxtpltn6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176)
                .addComponent(jtxtfno6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
                .addComponent(jtxtHse6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173)
                .addComponent(jtxtMale6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178)
                .addComponent(jtxtFemale6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtxtSnrctzn6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel140)
                    .addComponent(jtxtpltn6)
                    .addComponent(jtxtfno6)
                    .addComponent(jtxtHse6)
                    .addComponent(jtxtMale6)
                    .addComponent(jtxtFemale6)
                    .addComponent(jtxtSnrctzn6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 680, 1740, 70));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Purok", jPanel8);

        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel113.setText("Surname :");
        jPanel17.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 6, 90, -1));

        jtxtSname2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtSname2.setText(" ");
        jPanel17.add(jtxtSname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 6, 270, -1));

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel114.setText("First Name :");
        jPanel17.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 57, 110, -1));

        jtxtFname1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtFname1.setText(" ");
        jPanel17.add(jtxtFname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 57, 286, 30));

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel115.setText("Middle Name :");
        jPanel17.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 126, 130, -1));

        jtxtMname1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMname1.setText(" ");
        jPanel17.add(jtxtMname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 120, 270, 36));

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel116.setText("Date of Birth :");
        jPanel17.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 183, -1, -1));

        jtxtDateiofbirth1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel17.add(jtxtDateiofbirth1, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 183, 270, 31));

        jLabel117.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel117.setText("Age :");
        jPanel17.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 239, 50, 30));

        jtxtAge3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtAge3.setText(" ");
        jPanel17.add(jtxtAge3, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 239, 90, 30));

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel118.setText("Gender :");
        jPanel17.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 299, 74, 30));

        jtxtGender1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtGender1.setText(" ");
        jPanel17.add(jtxtGender1, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 301, 102, 27));

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel119.setText("Place of Birth :");
        jPanel17.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 347, 130, -1));

        jtxtPlaceofBirth1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel17.add(jtxtPlaceofBirth1, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 347, 270, 25));

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel120.setText("Relationship w/  the ");
        jPanel17.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 416, 180, 30));

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel121.setText("Family Head :");
        jPanel17.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 449, 122, -1));

        jtxtrelwfamhead1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtrelwfamhead1.setText(" ");
        jPanel17.add(jtxtrelwfamhead1, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 446, 189, 30));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel122.setText("Civil Status :");
        jPanel17.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, 30));

        jtxtCivilstat1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel17.add(jtxtCivilstat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 189, 30));

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel123.setText("Date Added :");
        jPanel17.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, 123, 30));

        jtxtDateadded1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateadded1.setText(" ");
        jPanel17.add(jtxtDateadded1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, 250, -1));

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel125.setText("Purok :");
        jPanel17.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, -1, 30));

        jtxtPurok1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtPurok1.setText(" ");
        jPanel17.add(jtxtPurok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 350, 90, -1));

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel126.setText("Houshold No. :");
        jPanel17.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 130, 30));

        jtxtHno3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtHno3.setText(" ");
        jPanel17.add(jtxtHno3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 90, -1));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel127.setText("Family No. :");
        jPanel17.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 100, 30));

        jtxtFno3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtFno3.setText(" ");
        jPanel17.add(jtxtFno3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 90, -1));

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel128.setText("Citizenship :");
        jPanel17.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 110, 30));

        jtxtCtzn1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtCtzn1.setText(" ");
        jPanel17.add(jtxtCtzn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 97, -1));

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel129.setText("Religion :");
        jPanel17.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, -1, 30));

        jtxtRgln1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtRgln1.setText(" ");
        jPanel17.add(jtxtRgln1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 260, -1));

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel130.setText("Occupation :");
        jPanel17.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 110, -1));

        jtxtOccptn1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtOccptn1.setText(" ");
        jPanel17.add(jtxtOccptn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 270, -1));

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel131.setText("Educational Attainment :");
        jPanel17.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 17, -1, 30));

        jtxtEducattmnt1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtEducattmnt1.setText(" ");
        jPanel17.add(jtxtEducattmnt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 20, 210, -1));

        jtxtContactnum1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel17.add(jtxtContactnum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 260, 30));

        jLabel223.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel223.setText("Contact No. :");
        jPanel17.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, 30));

        jtblforpsmem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Surname", "First Name", "Middle Name", "Date of Birth", "Age", "Gender", "Place of Birth", "RelationShip W/ the head of the Family", "Contact Number", "Civil Status", "Educational Attainment", "Occupation", "Religion", "Citezenship", "Family NO.", "Household NO.", "Purok", "Date Added"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblforpsmem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtblforpsmem.getTableHeader().setResizingAllowed(false);
        jtblforpsmem.getTableHeader().setReorderingAllowed(false);
        jtblforpsmem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblforpsmemMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtblforpsmem);
        if (jtblforpsmem.getColumnModel().getColumnCount() > 0) {
            jtblforpsmem.getColumnModel().getColumn(0).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(1).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(2).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(3).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(4).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(5).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(6).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(7).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(8).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(9).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(10).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(11).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(12).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(13).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(14).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(15).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(16).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(17).setResizable(false);
            jtblforpsmem.getColumnModel().getColumn(18).setResizable(false);
        }

        jPanel44.setBackground(new java.awt.Color(153, 204, 255));

        jLabel184.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel184.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel184.setText("Pantawid Pamily Pilipino Program (4P'S)");

        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/4p's.png"))); // NOI18N

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(568, 568, 568)
                .addComponent(jLabel154)
                .addGap(18, 18, 18)
                .addComponent(jLabel184, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel184)
                        .addContainerGap(12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel154, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
            .addComponent(jPanel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("4P'S", jPanel9);

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(153, 204, 255));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Blood Pressure (BP) Monitoring");

        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel151.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/blood-pressure64.png"))); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(592, 592, 592)
                .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(869, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel24)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jLabel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 70));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Resident Name :");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 168, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Age :");
        jPanel10.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 50, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("Date :");
        jPanel10.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 60, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setText("BP Daily Record Form");
        jPanel10.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 310, -1));

        jcmbsystolic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "70", "80", "90", "100", "110", "120", "130", "140", "150", "160", "180", "190", "200", "210", "220", "230", "240", "250" }));
        jPanel10.add(jcmbsystolic, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 80, 30));

        jcmbdiastolic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150", "160", "170", "180" }));
        jPanel10.add(jcmbdiastolic, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 80, 30));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("BP :");
        jPanel10.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 60, -1));
        jPanel10.add(jtxtresname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 290, 30));
        jPanel10.add(jtxtage, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 160, 30));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 90, 50));

        jtblBp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Age", "Contact Number", "Blood Pressure", "Level", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtblBp);
        if (jtblBp.getColumnModel().getColumnCount() > 0) {
            jtblBp.getColumnModel().getColumn(0).setResizable(false);
            jtblBp.getColumnModel().getColumn(1).setResizable(false);
            jtblBp.getColumnModel().getColumn(2).setResizable(false);
            jtblBp.getColumnModel().getColumn(3).setResizable(false);
            jtblBp.getColumnModel().getColumn(4).setResizable(false);
            jtblBp.getColumnModel().getColumn(4).setHeaderValue("Level");
            jtblBp.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel10.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, 860, 560));

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, 510, 40));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/save-file.png"))); // NOI18N
        jButton8.setText("Save");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 90, 50));

        jLabel93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/output-onlinepngtools (3).png"))); // NOI18N
        jPanel10.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, -1, -1));

        jButton17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton17.setText("Result");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 80, -1));

        jtxtBP.setEditable(false);
        jtxtBP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel10.add(jtxtBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 230, 30));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("/");
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 30, -1));

        jtxtBP1.setEditable(false);
        jtxtBP1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel10.add(jtxtBP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 230, 30));

        jLabel183.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel183.setText("Contact No. :");
        jPanel10.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        jtxtContnum1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtContnum1KeyTyped(evt);
            }
        });
        jPanel10.add(jtxtContnum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 160, 30));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton22.setText("Search");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 110, 40));

        jTabbedPane2.addTab("BP Monitoring", jPanel10);

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(153, 204, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pregnacy Tracking Record");

        jLabel152.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel152.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/woman.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(618, Short.MAX_VALUE)
                .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(697, 697, 697))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel152))
        );

        jPanel11.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 70));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Pregnancy Record Form");
        jPanel11.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 310, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Name of Parent :");
        jPanel11.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 168, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Date of Birth :");
        jPanel11.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 140, -1));

        jtxtAgepreg.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtAgepreg.setText(" ");
        jPanel11.add(jtxtAgepreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 225, 180, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Household No :");
        jPanel11.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 140, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Family No :");
        jPanel11.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 100, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Purok :");
        jPanel11.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 90, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Mobile No :");
        jPanel11.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 100, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("PIH :");
        jPanel11.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("LMP :");
        jPanel11.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("EDC :");
        jPanel11.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("GP :");
        jPanel11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Date Delivered :");
        jPanel11.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 140, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Name of Child :");
        jPanel11.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 140, -1));

        jtxtHouseno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtHousenoFocusGained(evt);
            }
        });
        jPanel11.add(jtxtHouseno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 240, 30));

        jtxtEDC.setEditable(false);
        jtxtEDC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtEDCFocusGained(evt);
            }
        });
        jPanel11.add(jtxtEDC, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 240, 30));
        jPanel11.add(jtxtFamilyno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 240, 30));

        jtxtMobilenum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtMobilenumKeyTyped(evt);
            }
        });
        jPanel11.add(jtxtMobilenum, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 240, 30));
        jPanel11.add(jtxtPih, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 240, 30));
        jPanel11.add(jtxtNameofparent, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 240, 30));

        jdateDlvrd.setDateFormatString("y-MM-d");
        jPanel11.add(jdateDlvrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 240, 30));

        jdateLstchckup.setDateFormatString("y-MM-d");
        jPanel11.add(jdateLstchckup, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 240, 30));
        jPanel11.add(jtxtNameofchild, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 240, 30));

        jcmbPurokpreg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5", "Purok 6", "Purok 7" }));
        jPanel11.add(jcmbPurokpreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 240, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Age:");
        jPanel11.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 50, -1));

        jtblPregrec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NameofParent", "DateofBirth", "Age", "Household No.", "Family No.", "Purok", "Mobile No.", "PIH", "LMP", "EDC", "GP", "LastCheck-Up", "DateDelivered", "NameofChild"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblPregrec);
        if (jtblPregrec.getColumnModel().getColumnCount() > 0) {
            jtblPregrec.getColumnModel().getColumn(0).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(1).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(2).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(3).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(4).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(5).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(6).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(7).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(8).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(9).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(10).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(11).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(12).setResizable(false);
            jtblPregrec.getColumnModel().getColumn(13).setResizable(false);
        }

        jPanel11.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 820, 560));

        jbtnpregclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jbtnpregclear.setText("Clear");
        jbtnpregclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnpregclearActionPerformed(evt);
            }
        });
        jPanel11.add(jbtnpregclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 90, 50));
        jPanel11.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 90, 560, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Last Check-Up :");
        jPanel11.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 140, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/save-file.png"))); // NOI18N
        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 90, 50));

        jDateLMP.setDateFormatString("y-MM-d");
        jPanel11.add(jDateLMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 240, 30));
        jPanel11.add(jtxtGp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, 240, 30));

        jDateofbirth.setDateFormatString("y-MM-d");
        jPanel11.add(jDateofbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 240, 30));

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton23.setText("Search");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 110, 40));

        jTabbedPane2.addTab("Pregnancy Record", jPanel11);

        jPanel23.setBackground(new java.awt.Color(153, 204, 255));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Birth Registry Record");

        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setIcon(new javax.swing.ImageIcon("D:\\Download\\Icon\\boy.png")); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(625, 625, 625)
                .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel153, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setText("Birth Registry Record Form");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("Surname :");

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("Name of Child :");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Middle Name :");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setText("Name of Parent :");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setText("Registered :");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setText("Date of Birth : ");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel41.setText("Household No. :");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setText("Purok :");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel44.setText("Gender :");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel45.setText("Birth Weight :");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setText("Place of Birth :");

        jTextField16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jTextField18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jtxtHsnobirthreg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtHsnobirthreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtHsnobirthregActionPerformed(evt);
            }
        });

        jTextField21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jTextField22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jtblBirthreg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NameofChild", "Surname", "Middlename", "NameofParent", "DateofBirth", "Household No.", "Family No.", "Purok", "Gender", "Birthweight", "Place of Birth", "Registered"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblBirthreg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblBirthregMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtblBirthreg);
        if (jtblBirthreg.getColumnModel().getColumnCount() > 0) {
            jtblBirthreg.getColumnModel().getColumn(0).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(1).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(2).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(3).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(4).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(5).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(6).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(7).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(8).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(9).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(10).setResizable(false);
            jtblBirthreg.getColumnModel().getColumn(11).setResizable(false);
        }

        jtxtFnobirthreg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel48.setText("Family No. :");

        jbtnBirthReg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnBirthReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jbtnBirthReg.setText("Clear");
        jbtnBirthReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBirthRegActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/updated.png"))); // NOI18N
        jButton12.setText("Update");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));

        jComboBox5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jComboBox6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5", "Purok 6", "Purok 7" }));

        jDateChooser5.setDateFormatString("y-MM-d");

        jtxtNameofChildreg.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtNameofChildreg.setText(" ");

        jtxtNameofparentreg.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtNameofparentreg.setText(" ");

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton28.setText("Search");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtNameofChildreg, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel40)
                                    .addGap(18, 18, 18)
                                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtxtHsnobirthreg, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addGap(18, 18, 18)
                                    .addComponent(jtxtNameofparentreg, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jtxtFnobirthreg, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(jButton12)
                        .addGap(116, 116, 116)
                        .addComponent(jbtnBirthReg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel33)))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 167, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel48))
                                    .addComponent(jtxtFnobirthreg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(jtxtNameofChildreg, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34))))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38)
                                    .addComponent(jtxtNameofparentreg, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtxtHsnobirthreg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41))
                                .addGap(84, 84, 84)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtnBirthReg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel45))
                                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel39))
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(127, 127, 127))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        jTabbedPane2.addTab("Birth Registry", jPanel12);

        jtbladdresident1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Surname", "First Name", "Middle Name", "Date of Birth", "Age", "Gender", "Place of Birth", "RelationShip W/ the head of the Family", "Contact Number", "Civil Status", "Educational Attainment", "Occupation", "Religion", "Citezenship", "Family NO.", "Household NO.", "Purok", "4P'S member", "Email Address", "Date Added"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbladdresident1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbladdresident1MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(jtbladdresident1);
        if (jtbladdresident1.getColumnModel().getColumnCount() > 0) {
            jtbladdresident1.getColumnModel().getColumn(0).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(1).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(2).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(3).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(4).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(5).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(6).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(7).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(8).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(9).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(10).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(11).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(12).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(13).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(14).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(15).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(16).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(17).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(18).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(19).setResizable(false);
            jtbladdresident1.getColumnModel().getColumn(20).setResizable(false);
        }

        jbtnsearchaddres1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnsearchaddres1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jbtnsearchaddres1.setText("Search");
        jbtnsearchaddres1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsearchaddres1ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gender", "Male", "Female" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jcmbPurok5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purok", "Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5", "Purok 6", "Purok 7" }));
        jcmbPurok5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbPurok5ItemStateChanged(evt);
            }
        });

        jLabel248.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel248.setText("Remove Resident Record");

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtMname2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMname2.setText(" ");
        jPanel49.add(jtxtMname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 161, 270, 36));

        jLabel250.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel250.setText("First Name :");
        jPanel49.add(jLabel250, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 103, 110, -1));

        jLabel251.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel251.setText("Family Head :");
        jPanel49.add(jLabel251, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 459, 122, -1));

        jLabel252.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel252.setText("Middle Name :");
        jPanel49.add(jLabel252, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 167, 130, -1));

        jLabel253.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel253.setText("Date of Birth :");
        jPanel49.add(jLabel253, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 224, -1, -1));

        jtxtAge5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtAge5.setText(" ");
        jPanel49.add(jtxtAge5, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 280, 90, 30));

        jLabel254.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel254.setText("Gender :");
        jPanel49.add(jLabel254, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 340, 74, 30));

        jLabel255.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel255.setText("Date Added :");
        jPanel49.add(jLabel255, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 543, 123, 30));

        jLabel256.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel256.setText("Relationship w/  the ");
        jPanel49.add(jLabel256, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 426, 180, 30));

        jLabel257.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel257.setText("Place of Birth :");
        jPanel49.add(jLabel257, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 388, 130, -1));

        jLabel258.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel258.setText("Civil Status :");
        jPanel49.add(jLabel258, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 543, -1, 30));

        jLabel259.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel259.setText("Educational Attainment :");
        jPanel49.add(jLabel259, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 47, -1, 30));

        jLabel260.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel260.setText("Occupation :");
        jPanel49.add(jLabel260, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 106, 110, -1));

        jLabel261.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel261.setText("Religion :");
        jPanel49.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 139, -1, 30));

        jLabel262.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel262.setText("Citizenship :");
        jPanel49.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 219, 110, 30));

        jLabel263.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel263.setText("Family No. :");
        jPanel49.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 291, 110, 30));

        jLabel264.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel264.setText("Houshold No. :");
        jPanel49.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 351, 130, 30));

        jLabel265.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel265.setText("Purok :");
        jPanel49.add(jLabel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(588, 399, -1, 30));

        jLabel266.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel266.setText("4P's Member :");
        jPanel49.add(jLabel266, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 461, 131, -1));

        jLabel267.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel267.setText("Age :");
        jPanel49.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 280, 50, 30));

        jLabel268.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel268.setText("Surname :");
        jPanel49.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 47, 90, -1));

        jtxtSname3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtSname3.setText(" ");
        jPanel49.add(jtxtSname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 50, 270, -1));

        jtxtDateiofbirth2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel49.add(jtxtDateiofbirth2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 224, 270, 31));

        jtxtGender2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtGender2.setText(" ");
        jPanel49.add(jtxtGender2, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 342, 102, 27));

        jtxtPlaceofBirth2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel49.add(jtxtPlaceofBirth2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 388, 270, 25));

        jtxtrelwfamhead2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtrelwfamhead2.setText(" ");
        jPanel49.add(jtxtrelwfamhead2, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 456, 189, 30));

        jtxtCivilstat2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel49.add(jtxtCivilstat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 543, 189, 30));

        jtxtEducattmnt2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtEducattmnt2.setText(" ");
        jPanel49.add(jtxtEducattmnt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 50, 210, -1));

        jtxtOccptn2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtOccptn2.setText(" ");
        jPanel49.add(jtxtOccptn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 106, 190, -1));

        jtxtRgln2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtRgln2.setText(" ");
        jPanel49.add(jtxtRgln2, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 142, 260, -1));

        jtxtCtzn2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtCtzn2.setText(" ");
        jPanel49.add(jtxtCtzn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 222, 97, -1));

        jtxtDateadded2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateadded2.setText(" ");
        jPanel49.add(jtxtDateadded2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 550, 260, -1));

        jtxtFname2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtFname2.setText(" ");
        jPanel49.add(jtxtFname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 103, 286, 30));

        jtxtFno5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtFno5.setText(" ");
        jPanel49.add(jtxtFno5, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 294, 90, -1));

        jtxtHno5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtHno5.setText(" ");
        jPanel49.add(jtxtHno5, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 354, 90, -1));

        jtxtPurok2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtPurok2.setText(" ");
        jPanel49.add(jtxtPurok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 402, 90, -1));

        jtxtforpsmem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtforpsmem1.setText(" ");
        jPanel49.add(jtxtforpsmem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 461, 90, -1));

        jLabel269.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel269.setText("Contact No. :");
        jPanel49.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 501, -1, 30));

        jtxtContnum2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel49.add(jtxtContnum2, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 501, 213, 30));

        jtxtEmailaddviewres1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtEmailaddviewres1.setText(" ");
        jPanel49.add(jtxtEmailaddviewres1, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 504, 260, -1));

        jLabel270.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel270.setText("Email Address :");
        jPanel49.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 501, -1, 30));

        jLabel271.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel271.setText("Date Removed :");
        jPanel49.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 140, 30));

        jtxtDateadded3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateadded3.setText(" ");
        jPanel49.add(jtxtDateadded3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 590, 260, -1));

        jtxtDateadded4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateadded4.setText(" ");
        jPanel49.add(jtxtDateadded4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 590, 260, -1));

        jLabel272.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel272.setText("Issue :");
        jPanel49.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 590, 60, 30));

        jPanel64.setBackground(new java.awt.Color(153, 204, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Transfered or Deceased Resident Record");

        jLabel273.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel273.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel273.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/no-tresspasing.png"))); // NOI18N

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(588, 588, 588)
                .addComponent(jLabel273, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(659, Short.MAX_VALUE))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel273))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel248)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jbtnsearchaddres1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtSearchaddres1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbPurok5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtSearchaddres1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnsearchaddres1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcmbPurok5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel248)
                        .addGap(22, 22, 22)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addComponent(jScrollPane15))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Removed Resident", jPanel14);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 2050, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Resident Information", jPanel2);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel51.setText("Personal Information ");
        jPanel25.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel52.setText("Surname :");
        jPanel25.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 90, -1));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel53.setText("First Name :");
        jPanel25.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 110, -1));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel54.setText("Family Head :");
        jPanel25.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 110, 30));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel55.setText("Middle Name :");
        jPanel25.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 130, -1));
        jPanel25.add(jtxtplcbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 280, 30));
        jPanel25.add(jtxtfname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 280, 30));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel56.setText("Date of Birth :");
        jPanel25.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, -1));

        jtxtAge.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtAge.setText("  ");
        jPanel25.add(jtxtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 70, 30));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel58.setText("Gender :");
        jPanel25.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 70, 30));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel59.setText("Date Added :");
        jPanel25.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 520, 110, 30));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setText("Relationship w/  the ");
        jPanel25.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 180, 30));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel61.setText("Place of Birth :");
        jPanel25.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 120, 30));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel62.setText("Civil Status :");
        jPanel25.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 100, 30));
        jPanel25.add(jtxtsname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 280, 30));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel63.setText("Educational Attainment :");
        jPanel25.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 200, 30));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel64.setText("Occupation :");
        jPanel25.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 110, 30));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel65.setText("Religion :");
        jPanel25.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 80, 30));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel66.setText("Citizenship :");
        jPanel25.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 100, 30));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel67.setText("Family No. :");
        jPanel25.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 110, 30));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel68.setText("Household No. :");
        jPanel25.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 130, 30));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel69.setText("Purok :");
        jPanel25.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 60, 30));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel70.setText("4P's Member:");
        jPanel25.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 110, 30));
        jPanel25.add(jtxtOcptn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 260, 30));

        jtxCtzn.setText("Filipino");
        jPanel25.add(jtxCtzn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, 260, 30));
        jPanel25.add(jtxtFno, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 260, 30));
        jPanel25.add(jtxtHno, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, 260, 30));

        jDateChooserbirth.setDateFormatString("y-MM-d");
        jDateChooserbirth.setMaxSelectableDate(new java.util.Date(1609434072000L));
        jDateChooserbirth.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserbirthPropertyChange(evt);
            }
        });
        jPanel25.add(jDateChooserbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 280, 30));

        jcmb4ps.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        jPanel25.add(jcmb4ps, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 410, 260, 30));

        jcmbgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jcmbgender.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmbgenderFocusGained(evt);
            }
        });
        jPanel25.add(jcmbgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 120, 30));

        jcmbPurok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5", "Purok 6", "Purok 7" }));
        jPanel25.add(jcmbPurok, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, 260, 30));
        jPanel25.add(jtxtrwfamilyhead, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 280, 30));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel71.setText("Age :");
        jPanel25.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 50, 30));

        jbtnAddres.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnAddres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jbtnAddres.setText("Clear");
        jbtnAddres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddresActionPerformed(evt);
            }
        });
        jPanel25.add(jbtnAddres, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 590, 110, 50));

        jbtnSaveaddresident.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnSaveaddresident.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/save-file.png"))); // NOI18N
        jbtnSaveaddresident.setText("Save");
        jbtnSaveaddresident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveaddresidentActionPerformed(evt);
            }
        });
        jPanel25.add(jbtnSaveaddresident, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 590, 110, 50));

        jtxtcivilstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Divorced", "Widowed", "Widower", "Separated" }));
        jPanel25.add(jtxtcivilstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 280, 30));
        jPanel25.add(jtxtmname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 280, 30));

        jtxtDateadd.setEditable(false);
        jPanel25.add(jtxtDateadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 520, 260, 30));

        jLabel192.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel192.setText("Contact No. :");
        jPanel25.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 110, 30));

        jtxtrContactnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtrContactnumActionPerformed(evt);
            }
        });
        jtxtrContactnum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtrContactnumKeyTyped(evt);
            }
        });
        jPanel25.add(jtxtrContactnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 280, 30));

        jLabel148.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel148.setText("Email Address :");
        jPanel25.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, -1, -1));
        jPanel25.add(jtxtEmailaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 470, 260, 30));

        jtxteducationalattmnt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Grade Completed", "Elementary Undegraduate", "Elementary Graduate", "High School Undergraduate", "High School Graduate", "College Undergaraduate", "College Graduate" }));
        jtxteducationalattmnt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxteducationalattmntFocusGained(evt);
            }
        });
        jPanel25.add(jtxteducationalattmnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 260, 30));

        jtxtRlgn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roman Catholic", "Iglesia Ni Cristo", "Islam", "Seventh Day Adventist", "Christian" }));
        jtxtRlgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtRlgnFocusGained(evt);
            }
        });
        jPanel25.add(jtxtRlgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 260, 30));

        issue1.setForeground(new java.awt.Color(255, 255, 255));
        issue1.setText("issue");
        jPanel25.add(issue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 596, 80, 30));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel50.setText("New Resident");

        jtbladdresident.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Surname", "First Name", "Middle Name", "Date of Birth", "Age", "Gender", "Place of Birth", "RelationShip W/ the head of the Family", "Contact Number", "Civil Status", "Educational Attainment", "Occupation", "Religion", "Citezenship", "Family NO.", "Household NO.", "Purok", "4P'S member", "Email Address", "Date Added"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbladdresident.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbladdresidentMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtbladdresident);
        if (jtbladdresident.getColumnModel().getColumnCount() > 0) {
            jtbladdresident.getColumnModel().getColumn(0).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(1).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(2).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(3).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(4).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(5).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(6).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(7).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(8).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(9).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(10).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(11).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(12).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(13).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(14).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(15).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(16).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(17).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(18).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(19).setResizable(false);
            jtbladdresident.getColumnModel().getColumn(20).setResizable(false);
        }

        jbtnsearchaddres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnsearchaddres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jbtnsearchaddres.setText("Search");
        jbtnsearchaddres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsearchaddresActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gender", "Male", "Female" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jcmbPurok2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purok", "Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5", "Purok 6", "Purok 7" }));
        jcmbPurok2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbPurok2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jbtnsearchaddres, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtSearchaddres, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcmbPurok2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtSearchaddres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnsearchaddres, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcmbPurok2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel50)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Resident", jPanel3);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(910, 670));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setText("Personal Information ");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel72.setText("Surname :");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel73.setText("Educational Attainment :");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel74.setText("Occupation :");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel75.setText("First Name :");

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel76.setText("Middle Name :");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel77.setText("Religion :");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel78.setText("Citizenship :");

        jDateChooserbirth1.setDateFormatString("y-MM-d");
        jDateChooserbirth1.setMaxSelectableDate(new java.util.Date(1609434072000L));
        jDateChooserbirth1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserbirth1PropertyChange(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel79.setText("Date of Birth :");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel80.setText("Age :");

        jtxtAge1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtAge1.setText(" ");

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel81.setText("Gender :");

        jcmbgender1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jcmbgender1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmbgender1FocusGained(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel82.setText("Place of Birth :");

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel83.setText("Relationship w/  the ");

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel84.setText("Family Head :");

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel85.setText("Civil Status :");

        jtxtcivilstatus1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Divorced", "Widowed", "Widower", "Separated" }));

        jbtnSaveaddresident1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnSaveaddresident1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/updated.png"))); // NOI18N
        jbtnSaveaddresident1.setText("Update");
        jbtnSaveaddresident1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveaddresident1ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jButton15.setText("Clear");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel86.setText("Date Added :");

        jcmb4ps1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel87.setText("4P's Member:");

        jcmbPurok1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5", "Purok 6", "Purok 7" }));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel88.setText("Purok :");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel89.setText("Household No. :");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel90.setText("Family No. :");

        jtxtFno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtFno1ActionPerformed(evt);
            }
        });

        jtxtrwfamilyhead2.setDateFormatString("y-MM-d");
        jtxtrwfamilyhead2.setMaxSelectableDate(new java.util.Date(1609434072000L));
        jtxtrwfamilyhead2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtxtrwfamilyhead2PropertyChange(evt);
            }
        });

        jLabel216.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel216.setText("Contact No. :");

        jtxtContactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtContactnoKeyTyped(evt);
            }
        });

        jLabel147.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel147.setText("Email Address :");

        jtxteducationalattmnt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Grade Completed", "Elementary Undegraduate", "Elementary Graduate", "High School Undergraduate", "High School Graduate", "College Undergaraduate", "College Graduate" }));
        jtxteducationalattmnt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxteducationalattmnt1FocusGained(evt);
            }
        });

        jtxtRlgn1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roman Catholic", "Iglesia Ni Cristo", "Islam", "Seventh Day Adventist", "Christian" }));
        jtxtRlgn1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtRlgn1FocusGained(evt);
            }
        });

        jbtnSaveaddresident2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnSaveaddresident2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/remove.png"))); // NOI18N
        jbtnSaveaddresident2.setText("Remove");
        jbtnSaveaddresident2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveaddresident2ActionPerformed(evt);
            }
        });

        issue.setForeground(new java.awt.Color(255, 255, 255));
        issue.setText("issue");

        jbtnSaveaddresident3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnSaveaddresident3.setText("Concern");
        jbtnSaveaddresident3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveaddresident3ActionPerformed(evt);
            }
        });

        issue2.setForeground(new java.awt.Color(255, 255, 255));
        issue2.setText("issue");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jtxtsname1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel73)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxteducationalattmnt1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jtxtOcptn1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jDateChooserbirth1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxCtzn1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jtxtmname1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(121, 121, 121)
                                        .addComponent(jLabel77))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jtxtfname1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel74))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jtxtAge1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(269, 269, 269)
                                        .addComponent(jLabel90)))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtxtFno1)
                                    .addComponent(jtxtRlgn1, 0, 266, Short.MAX_VALUE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jtxtplcbirth1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbPurok1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jtxtrwfamilyhead1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 80, 80)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcmb4ps1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel216, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jtxtContactno, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jtxtEmailaddeditres, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jcmbgender1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225)
                        .addComponent(jLabel89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtHno1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(issue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(issue2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtcivilstatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jbtnSaveaddresident3)
                                .addGap(71, 71, 71)
                                .addComponent(jbtnSaveaddresident1)))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnSaveaddresident2))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtrwfamilyhead2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel57)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jtxtsname1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxteducationalattmnt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtxtfname1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtOcptn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jtxtmname1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtRlgn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserbirth1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79)
                                    .addComponent(jtxCtzn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtxtAge1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jtxtFno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcmbgender1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtxtHno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtplcbirth1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jtxtrwfamilyhead1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jcmb4ps1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel216, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtContactno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtEmailaddeditres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtcivilstatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtrwfamilyhead2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnSaveaddresident1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnSaveaddresident2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(issue, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnSaveaddresident3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(issue2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jcmbPurok1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jtxtbeditresident.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Surname", "First Name", "Middle Name", "Date of Birth", "Age", "Gender", "Place of Birth", "RelationShip W/ the head of the Family", "Contact Number", "Civil Status", "Educational Attainment", "Occupation", "Religion", "Citezenship", "Family NO.", "Household NO.", "Purok", "4P'S member", "Email Address", "Date Added"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtxtbeditresident.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtbeditresidentMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtxtbeditresident);
        if (jtxtbeditresident.getColumnModel().getColumnCount() > 0) {
            jtxtbeditresident.getColumnModel().getColumn(0).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(1).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(2).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(3).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(4).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(5).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(6).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(7).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(8).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(9).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(10).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(11).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(12).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(13).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(14).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(15).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(16).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(17).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(18).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(19).setResizable(false);
            jtxtbeditresident.getColumnModel().getColumn(20).setResizable(false);
        }

        jButton16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton16.setText("Search");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel91.setText("Edit Resident");

        jcmbPurok3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purok", "Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5", "Purok 6", "Purok 7" }));
        jcmbPurok3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbPurok3ItemStateChanged(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gender", "Male", "Female" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(691, 691, 691)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbPurok3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel91))
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcmbPurok3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Edit/Concern/Remove Resident", jPanel15);

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jPanel56.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel186.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel186.setText("Medicine Record Form");
        jPanel29.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, -1));

        jLabel190.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel190.setText("Medicine Name :");
        jPanel29.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        jLabel191.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel191.setText("Type : ");
        jPanel29.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jtxtStocks.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtxtStocks.setText("0");
        jPanel29.add(jtxtStocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, 30));

        jLabel193.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel193.setText("Description :");
        jPanel29.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 110, -1));

        jbtnUpdatedmed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/updated.png"))); // NOI18N
        jbtnUpdatedmed.setText("Update");
        jbtnUpdatedmed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdatedmedActionPerformed(evt);
            }
        });
        jPanel29.add(jbtnUpdatedmed, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 100, 50));

        jbtnAddmdcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/add-button.png"))); // NOI18N
        jbtnAddmdcn.setText("Add");
        jbtnAddmdcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddmdcnActionPerformed(evt);
            }
        });
        jPanel29.add(jbtnAddmdcn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 90, 50));

        jbtnMedrecordclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jbtnMedrecordclear.setText("Clear");
        jbtnMedrecordclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMedrecordclearActionPerformed(evt);
            }
        });
        jPanel29.add(jbtnMedrecordclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 90, 50));
        jPanel29.add(jtxtDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 350, 30));
        jPanel29.add(jtxtType, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 350, 30));
        jPanel29.add(jtxtMedname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 350, 30));

        jLabel194.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel194.setText("Stocks : ");
        jPanel29.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 70, -1));

        jPanel56.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 710, 500));

        jtblMedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Medicine Name", "Type", "Description", "Stocks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMedicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblMedicineMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jtblMedicine);
        if (jtblMedicine.getColumnModel().getColumnCount() > 0) {
            jtblMedicine.getColumnModel().getColumn(0).setResizable(false);
            jtblMedicine.getColumnModel().getColumn(1).setResizable(false);
            jtblMedicine.getColumnModel().getColumn(2).setResizable(false);
            jtblMedicine.getColumnModel().getColumn(3).setResizable(false);
            jtblMedicine.getColumnModel().getColumn(3).setHeaderValue("Description");
            jtblMedicine.getColumnModel().getColumn(4).setResizable(false);
            jtblMedicine.getColumnModel().getColumn(4).setHeaderValue("Stocks");
        }

        jPanel56.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 840, 600));

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton21.setText("Search");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel56.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, 110, 40));
        jPanel56.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, 640, 40));

        jPanel46.setBackground(new java.awt.Color(153, 204, 255));

        jLabel155.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel155.setText("Medicine Detail");

        jLabel156.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel156.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/medicine.png"))); // NOI18N

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel156)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel156, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel155)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel56.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, 70));

        jTabbedPane3.addTab("Medicine Record", jPanel56);

        jPanel58.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel195.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel195.setText("Add Stocks");
        jPanel30.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 310, -1));

        jLabel196.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel196.setText("Medicine Name :");
        jPanel30.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        jLabel197.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel197.setText("Type : ");
        jPanel30.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel199.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel199.setText("Description :");
        jPanel30.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 110, -1));

        jbtnMedsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/save-file.png"))); // NOI18N
        jbtnMedsave.setText("Save");
        jbtnMedsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMedsaveActionPerformed(evt);
            }
        });
        jPanel30.add(jbtnMedsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 90, 50));
        jPanel30.add(jtxtDesc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 350, 30));

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jButton24.setText("Clear");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 90, 50));

        jLabel200.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel200.setText("Supplier Name :");
        jPanel30.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 140, -1));

        jLabel201.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel201.setText("Quantity :");
        jPanel30.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 90, -1));

        jLabel202.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel202.setText("Expiry Date :");
        jPanel30.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 110, -1));

        jLabel204.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel204.setText("Date Recieved :");
        jPanel30.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 140, -1));

        jtxtDateexp.setDateFormatString("y-MM-d");
        jtxtDateexp.setMaxSelectableDate(new java.util.Date(64060563672000L));
        jtxtDateexp.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtxtDateexpPropertyChange(evt);
            }
        });
        jPanel30.add(jtxtDateexp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 240, 30));
        jPanel30.add(jtxtQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, 30));

        jtxtDatrecvd.setDateFormatString("y-MM-d");
        jtxtDatrecvd.setMaxSelectableDate(new java.util.Date(64060563672000L));
        jtxtDatrecvd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtxtDatrecvdPropertyChange(evt);
            }
        });
        jPanel30.add(jtxtDatrecvd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 302, 240, 30));

        jLabel198.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel198.setText(" ");
        jPanel30.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, -1));

        jtxtMedname1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMedname1.setText("  ");
        jPanel30.add(jtxtMedname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 350, 30));

        jtxtType1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtType1.setText("  ");
        jPanel30.add(jtxtType1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 350, 30));

        jtxtDescmed.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDescmed.setText("  ");
        jPanel30.add(jtxtDescmed, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 350, 30));

        jPanel58.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 710, 620));

        jtblMedicine1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Medicine Name", "Type", "Description", "Stocks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMedicine1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblMedicine1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jtblMedicine1);
        if (jtblMedicine1.getColumnModel().getColumnCount() > 0) {
            jtblMedicine1.getColumnModel().getColumn(0).setResizable(false);
            jtblMedicine1.getColumnModel().getColumn(1).setResizable(false);
            jtblMedicine1.getColumnModel().getColumn(2).setResizable(false);
            jtblMedicine1.getColumnModel().getColumn(3).setResizable(false);
            jtblMedicine1.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel58.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 860, 600));

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton25.setText("Search");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel58.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 100, 40));
        jPanel58.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 640, 40));

        jPanel50.setBackground(new java.awt.Color(153, 204, 255));

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel161.setText("Add Medicine");

        jLabel162.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel162.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/capsules.png"))); // NOI18N

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel161)
                .addGap(18, 18, 18)
                .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1309, Short.MAX_VALUE))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel162, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel161)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel58.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, 70));

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Add Stocks", jPanel57);

        jPanel59.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel203.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel203.setText("Releasing Medicine Form");
        jPanel31.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 310, -1));

        jLabel205.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel205.setText("Resident Name :");
        jPanel31.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        jLabel206.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel206.setText("Age :");
        jPanel31.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel207.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel207.setText("Concern :");
        jPanel31.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 80, -1));

        jbtnMedsave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/save-file.png"))); // NOI18N
        jbtnMedsave1.setText("Save");
        jbtnMedsave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMedsave1ActionPerformed(evt);
            }
        });
        jPanel31.add(jbtnMedsave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 90, 50));

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jButton26.setText("Clear");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 90, 50));

        jLabel208.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel208.setText("Medicine Name :");
        jPanel31.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 150, -1));

        jLabel209.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel209.setText("Quantity :");
        jPanel31.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 90, -1));

        jLabel210.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel210.setText("Medicine Stocks :");
        jPanel31.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 150, -1));

        jtxtMednamerel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMednamerel.setText("  ");
        jPanel31.add(jtxtMednamerel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 350, 30));

        jLabel213.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel213.setText("Date Released :");
        jPanel31.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 140, -1));

        jDaterel.setDateFormatString("y-MM-d");
        jDaterel.setMaxSelectableDate(new java.util.Date(1609434072000L));
        jDaterel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDaterelPropertyChange(evt);
            }
        });
        jPanel31.add(jDaterel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 240, 30));
        jPanel31.add(jtxtQuantrel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, 30));

        jtxtMedStocksrel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMedStocksrel.setText("  ");
        jPanel31.add(jtxtMedStocksrel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 350, 30));
        jPanel31.add(jtxtAgerel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 350, 30));
        jPanel31.add(jtxtResname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 350, 30));
        jPanel31.add(jtxtConcern, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 350, 30));

        jPanel59.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 710, 620));

        jtblMedicine2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Medicine Name", "Type", "Description", "Stocks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMedicine2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblMedicine2MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jtblMedicine2);
        if (jtblMedicine2.getColumnModel().getColumnCount() > 0) {
            jtblMedicine2.getColumnModel().getColumn(0).setResizable(false);
            jtblMedicine2.getColumnModel().getColumn(1).setResizable(false);
            jtblMedicine2.getColumnModel().getColumn(2).setResizable(false);
            jtblMedicine2.getColumnModel().getColumn(3).setResizable(false);
            jtblMedicine2.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel59.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 860, 600));

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton27.setText("Search");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel59.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 100, 40));
        jPanel59.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 640, 40));

        jPanel51.setBackground(new java.awt.Color(153, 204, 255));

        jLabel163.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel163.setText("Releasing Medicine");

        jLabel164.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel164.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/drug.png"))); // NOI18N

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel163, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1220, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel164, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel163)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel59.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, -1));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Medicine Release", jPanel18);

        jPanel60.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel211.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel211.setText("Remove Medicine ");
        jPanel32.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 230, -1));

        jLabel214.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel214.setText("Medicine Name :");
        jPanel32.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        jLabel215.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel215.setText("Type : ");
        jPanel32.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel217.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel217.setText("Description :");
        jPanel32.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 110, -1));

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/clear.png"))); // NOI18N
        jButton19.setText("Clear");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel32.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 110, 50));

        jbtnRemovemed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/remove.png"))); // NOI18N
        jbtnRemovemed.setText("Remove Stock");
        jbtnRemovemed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRemovemedActionPerformed(evt);
            }
        });
        jPanel32.add(jbtnRemovemed, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 140, 50));

        jLabel218.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel218.setText("Stocks : ");
        jPanel32.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 70, -1));

        jLabel219.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel219.setText("Medicine Issue :");
        jPanel32.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 140, -1));

        jLabel220.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel220.setText("Quantity :");
        jPanel32.add(jLabel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 90, -1));

        jtxtDescrem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtxtDescrem.setText(" ");
        jPanel32.add(jtxtDescrem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 350, -1));

        jtxtTyperem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtxtTyperem.setText(" ");
        jPanel32.add(jtxtTyperem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 350, -1));

        jtxtMednamerem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtxtMednamerem.setText(" ");
        jPanel32.add(jtxtMednamerem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 350, -1));

        jtxtStocksrem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtxtStocksrem.setText(" ");
        jPanel32.add(jtxtStocksrem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, -1));
        jPanel32.add(jtxtMedissue, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 350, 30));
        jPanel32.add(jtxtQuantrem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 350, 30));

        jPanel60.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 710, 600));

        jtblMedicine3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Medicine Name", "Type", "Description", "Stocks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMedicine3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblMedicine3MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jtblMedicine3);
        if (jtblMedicine3.getColumnModel().getColumnCount() > 0) {
            jtblMedicine3.getColumnModel().getColumn(0).setResizable(false);
            jtblMedicine3.getColumnModel().getColumn(1).setResizable(false);
            jtblMedicine3.getColumnModel().getColumn(2).setResizable(false);
            jtblMedicine3.getColumnModel().getColumn(3).setResizable(false);
            jtblMedicine3.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel60.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 860, 600));

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton29.setText("Search");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel60.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, -1, 40));
        jPanel60.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 90, 640, 40));

        jPanel52.setBackground(new java.awt.Color(153, 204, 255));

        jLabel165.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel165.setText("Remove Medicine");

        jLabel166.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel166.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel166.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/no-drugs.png"))); // NOI18N

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel165)
                .addGap(34, 34, 34)
                .addComponent(jLabel166)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel166, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel165)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel60.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, 70));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1620, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, 1616, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("Remove Medicine Stock", jPanel19);

        jPanel61.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel212.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel212.setText("Medicine Information Form");
        jPanel33.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 320, -1));

        jLabel221.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel221.setText("Medicine ID :");
        jPanel33.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 120, -1));

        jLabel225.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel225.setText("Medicine Name :");
        jPanel33.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel226.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel226.setText("Type :");
        jPanel33.add(jLabel226, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 60, -1));

        jLabel227.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel227.setText("Supplier Name :");
        jPanel33.add(jLabel227, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 140, -1));

        jLabel228.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel228.setText("Description :");
        jPanel33.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 110, -1));

        jLabel229.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel229.setText("Expiry Date :");
        jPanel33.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 110, -1));

        jLabel230.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel230.setText("Date Issued :");
        jPanel33.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 110, -1));

        jtxtDateissued.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateissued.setText(" ");
        jPanel33.add(jtxtDateissued, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 350, -1));

        jtxtExpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtExpdate.setText("  ");
        jPanel33.add(jtxtExpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, 350, 30));

        jxtMednameview.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jxtMednameview.setText("  ");
        jPanel33.add(jxtMednameview, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 350, 30));

        jtxtTypeview.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtTypeview.setText("  ");
        jPanel33.add(jtxtTypeview, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 350, 30));

        jtxtMedID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMedID.setText("  ");
        jPanel33.add(jtxtMedID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 350, 30));

        jtxtDescview.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDescview.setText("  ");
        jPanel33.add(jtxtDescview, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, 30));

        jtxtSuppName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtSuppName.setText("  ");
        jPanel33.add(jtxtSuppName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 350, 30));

        jLabel231.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel231.setText("Quantity :");
        jPanel33.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 90, -1));

        jxtQuanttview.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jxtQuanttview.setText("  ");
        jPanel33.add(jxtQuanttview, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 350, 30));

        jPanel61.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 710, 540));

        jtblMedicine4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Medicine Name", "Type", "Description", "Quantity", "Date Supplied", "Supplier Name", "Expiry Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMedicine4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblMedicine4MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jtblMedicine4);
        if (jtblMedicine4.getColumnModel().getColumnCount() > 0) {
            jtblMedicine4.getColumnModel().getColumn(0).setResizable(false);
            jtblMedicine4.getColumnModel().getColumn(1).setResizable(false);
            jtblMedicine4.getColumnModel().getColumn(2).setResizable(false);
            jtblMedicine4.getColumnModel().getColumn(3).setResizable(false);
            jtblMedicine4.getColumnModel().getColumn(4).setResizable(false);
            jtblMedicine4.getColumnModel().getColumn(5).setResizable(false);
            jtblMedicine4.getColumnModel().getColumn(6).setResizable(false);
            jtblMedicine4.getColumnModel().getColumn(7).setResizable(false);
        }

        jPanel61.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 860, 600));

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton30.setText("Search");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel61.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 100, 40));
        jPanel61.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 640, 40));

        jPanel53.setBackground(new java.awt.Color(153, 204, 255));

        jLabel167.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel167.setText("View Medicine Record");

        jLabel168.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel168.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/prescription.png"))); // NOI18N

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel167)
                .addGap(31, 31, 31)
                .addComponent(jLabel168)
                .addGap(0, 1233, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel168, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel167)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel61.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, 70));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("View Medicine Record", jPanel20);

        jPanel62.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel232.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel232.setText("Releasing Medicine Form");
        jPanel34.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 310, -1));

        jLabel233.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel233.setText("Resident Name :");
        jPanel34.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        jLabel234.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel234.setText("Age :");
        jPanel34.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel235.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel235.setText("Concern :");
        jPanel34.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 80, -1));

        jLabel236.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel236.setText("Medicine Name :");
        jPanel34.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 150, -1));

        jLabel237.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel237.setText("Quantity :");
        jPanel34.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 90, -1));

        jtxtAgerelmed.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtAgerelmed.setText("  ");
        jPanel34.add(jtxtAgerelmed, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 350, 30));

        jtxtMedconcern.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMedconcern.setText("  ");
        jPanel34.add(jtxtMedconcern, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 350, 30));

        jLabel239.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel239.setText("Date Released :");
        jPanel34.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 140, -1));

        jtxtMednamerelease.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMednamerelease.setText("  ");
        jPanel34.add(jtxtMednamerelease, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 350, 30));

        jtxtMedquant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMedquant.setText("  ");
        jPanel34.add(jtxtMedquant, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, 30));

        jtxtDaterelease.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDaterelease.setText("  ");
        jPanel34.add(jtxtDaterelease, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 350, 30));

        jtxtResnamemed.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtResnamemed.setText("  ");
        jPanel34.add(jtxtResnamemed, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 350, 30));

        jPanel62.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 710, 480));

        jtblMedicine5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Resident Name", "Age", "Concern", "Quantity", "Medname", "Date Released"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMedicine5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblMedicine5MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(jtblMedicine5);
        if (jtblMedicine5.getColumnModel().getColumnCount() > 0) {
            jtblMedicine5.getColumnModel().getColumn(0).setResizable(false);
            jtblMedicine5.getColumnModel().getColumn(1).setResizable(false);
            jtblMedicine5.getColumnModel().getColumn(2).setResizable(false);
            jtblMedicine5.getColumnModel().getColumn(3).setResizable(false);
            jtblMedicine5.getColumnModel().getColumn(4).setResizable(false);
            jtblMedicine5.getColumnModel().getColumn(5).setResizable(false);
            jtblMedicine5.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel62.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 860, 600));

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton31.setText("Search");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel62.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 100, 40));
        jPanel62.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 640, 40));

        jPanel54.setBackground(new java.awt.Color(153, 204, 255));

        jLabel169.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel169.setText("Release Medicine Record");

        jLabel170.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel170.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/prescription (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel169)
                .addGap(31, 31, 31)
                .addComponent(jLabel170)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel170, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel169)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel62.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, 70));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("View Medicine Released", jPanel27);

        jPanel63.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel238.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel238.setText("Remove Medicine ");
        jPanel45.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 250, -1));

        jLabel240.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel240.setText("Medicine Name :");
        jPanel45.add(jLabel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        jLabel241.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel241.setText("Quantity :");
        jPanel45.add(jLabel241, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel243.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel243.setText("Date Removed :");
        jPanel45.add(jLabel243, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 140, -1));

        jLabel244.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel244.setText("Medicine Issue :");
        jPanel45.add(jLabel244, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 140, -1));

        jLabel246.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel246.setText(" ");
        jPanel45.add(jLabel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 350, -1));

        jtxtDateremoved.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateremoved.setText(" ");
        jPanel45.add(jtxtDateremoved, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 350, -1));

        jtxtRemovemdecine.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtRemovemdecine.setText(" ");
        jPanel45.add(jtxtRemovemdecine, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 350, -1));

        jLabel249.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel249.setText(" ");
        jPanel45.add(jLabel249, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 350, -1));

        jtxtQuantremove.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtQuantremove.setText(" ");
        jPanel45.add(jtxtQuantremove, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 350, -1));

        jtxtMedissuerem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtMedissuerem.setText(" ");
        jPanel45.add(jtxtMedissuerem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 350, -1));

        jPanel63.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 710, 370));

        jtblMedicine6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Medicine Name", "Quantity", "Medicine Issue", "Date Removed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMedicine6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblMedicine6MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jtblMedicine6);
        if (jtblMedicine6.getColumnModel().getColumnCount() > 0) {
            jtblMedicine6.getColumnModel().getColumn(0).setResizable(false);
            jtblMedicine6.getColumnModel().getColumn(1).setResizable(false);
            jtblMedicine6.getColumnModel().getColumn(2).setResizable(false);
            jtblMedicine6.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel63.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 860, 600));

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/loupe.png"))); // NOI18N
        jButton32.setText("Search");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel63.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 100, 40));
        jPanel63.add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 640, 40));

        jPanel55.setBackground(new java.awt.Color(153, 204, 255));

        jLabel171.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel171.setText("View Removed Medicine ");

        jLabel172.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel172.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/no-drugs1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel171)
                .addGap(31, 31, 31)
                .addComponent(jLabel172)
                .addGap(0, 1200, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel172, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel171)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel63.add(jPanel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1620, -1));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1620, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, 1616, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("View Removed Medicine Record", jPanel28);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane1.addTab("Medicine Stocks", jPanel16);

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("Username :");

        jLabel157.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel157.setText("Password : ");

        usernameAcc.setEditable(false);
        usernameAcc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameAcc.setFocusable(false);

        password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        password.setEchoChar('\u25cf');
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
        });

        jPanel66.setBackground(new java.awt.Color(51, 204, 255));
        jPanel66.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel159.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel159.setText("Manage Account");
        jPanel66.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 60));

        jtxtnusername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel160.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel160.setText("New Username :");

        jcmbqstn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbqstn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is your mother’s maiden name?", "What Is your favorite book?", "What was the name of your first pet?", "Where did you go to high school/college?", "What is your favorite food?", "What city were you born in?", "Where is your favorite place to vacation?" }));

        jLabel173.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel173.setText("Secuirty Question :");

        jtxtans.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel174.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel174.setText("Answer :");

        jtxtnpass.setEchoChar('\u25cf');

        jLabel175.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel175.setText("New Password :");

        jcheckboxacc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcheckboxacc.setText("Show Password");
        jcheckboxacc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcheckboxaccActionPerformed(evt);
            }
        });

        jbtnupdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnupdate.setText("Update");
        jbtnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnupdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel65Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel157))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel31)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameAcc)
                            .addComponent(password)))
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel65Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel160)
                                .addGap(12, 12, 12)
                                .addComponent(jtxtnusername, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel65Layout.createSequentialGroup()
                                .addComponent(jLabel173)
                                .addGap(9, 9, 9)
                                .addComponent(jcmbqstn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel65Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel174)
                                .addGap(7, 7, 7)
                                .addComponent(jtxtans, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel65Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel175)
                                .addGap(7, 7, 7)
                                .addComponent(jtxtnpass, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel65Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jcheckboxacc))
                            .addGroup(jPanel65Layout.createSequentialGroup()
                                .addGap(380, 380, 380)
                                .addComponent(jbtnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernameAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(35, 35, 35)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel157)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel160))
                    .addComponent(jtxtnusername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel173))
                    .addComponent(jcmbqstn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel174))
                    .addComponent(jtxtans, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel175)
                    .addComponent(jtxtnpass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jcheckboxacc)
                .addGap(16, 16, 16)
                .addComponent(jbtnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap(741, Short.MAX_VALUE)
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(609, 609, 609))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Manage Account", jPanel13);

        jtblBp1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Surname", "Firstname", "Middlename", "Age", "Contact Number", "Concern", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jtblBp1);
        if (jtblBp1.getColumnModel().getColumnCount() > 0) {
            jtblBp1.getColumnModel().getColumn(0).setResizable(false);
            jtblBp1.getColumnModel().getColumn(1).setResizable(false);
            jtblBp1.getColumnModel().getColumn(2).setResizable(false);
            jtblBp1.getColumnModel().getColumn(3).setResizable(false);
            jtblBp1.getColumnModel().getColumn(4).setResizable(false);
            jtblBp1.getColumnModel().getColumn(5).setResizable(false);
            jtblBp1.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 1451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Logs", jPanel67);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/logout32.png"))); // NOI18N
        jButton1.setText("Logout");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Barangay Health Center Record Management System");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Admin");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Barangay San Bartolome Sto.Tomas City Batangas");

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/healthcentermanagementsystem/output-onlinepngtools.png"))); // NOI18N

        jtxtDateup.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtDateup.setText("Time");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel92)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(522, 522, 522))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(398, 398, 398)))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jtxtDateup))
                        .addGap(63, 63, 63))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1923, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jLabel1))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(40, 40, 40)))
                        .addComponent(jtxtDateup))
                    .addComponent(jLabel92))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        // TODO add your handling code here:
        tblupdatemedinfo();
    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void jtblMedicine6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblMedicine6MouseClicked
        // TODO add your handling code here:

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine6.getSelectedRow();
            String table_click = (jtblMedicine6.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medrem WHERE medname ='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String medname = rs.getString("medname");
                jtxtRemovemdecine.setText(medname);

                String type = rs.getString("quant");
                jtxtQuantremove.setText(type);

                String medissue = rs.getString("medissue");
                jtxtMedissuerem.setText(medissue);

                String daterem = rs.getString("daterem");
                jtxtDateremoved.setText(daterem);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblMedicine6MouseClicked

    private void jtblMedicine5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblMedicine5MouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine5.getSelectedRow();
            String table_click = (jtblMedicine5.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medrel WHERE id ='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String resname = rs.getString("resname");
                jtxtResnamemed.setText(resname);

                String age = rs.getString("age");
                jtxtAgerelmed.setText(age);

                String medconcern = rs.getString("concern");
                jtxtMedconcern.setText(medconcern);

                String medquant = rs.getString("quant");
                jtxtMedquant.setText(medquant);

                String medname = rs.getString("medname");
                jtxtMednamerelease.setText(medname);

                String daterel = rs.getString("daterel");
                jtxtDaterelease.setText(daterel);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblMedicine5MouseClicked

    private void jtblMedicine4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblMedicine4MouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine4.getSelectedRow();
            String table_click = (jtblMedicine4.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medrec WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                jtxtMedID.setText(id);

                String medname = rs.getString("medname");
                jxtMednameview.setText(medname);

                String type = rs.getString("type");
                jtxtTypeview.setText(type);

                String desc = rs.getString("descrip");
                jtxtDescview.setText(desc);

                String quantt = rs.getString("quantity");
                jxtQuanttview.setText(quantt);

                String dateissue = rs.getString("dateissued");
                jtxtDateissued.setText(dateissue);

                String suppname = rs.getString("suppname");
                jtxtSuppName.setText(suppname);

                String expdate = rs.getString("expirydate");
                jtxtExpdate.setText(expdate);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblMedicine4MouseClicked

    private void jtblMedicine3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblMedicine3MouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine3.getSelectedRow();
            String table_click = (jtblMedicine3.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medicine WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String medname = rs.getString("medname");
                jtxtMednamerem.setText(medname);

                String type = rs.getString("type");
                jtxtTyperem.setText(type);

                String desc = rs.getString("descrip");
                jtxtDescrem.setText(desc);

                String stocks = rs.getString("stocks");
                jtxtStocksrem.setText(stocks);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jtblMedicine3MouseClicked

    private void jbtnRemovemedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRemovemedActionPerformed
        // TODO add your handling code here:

        DefaultTableModel d1 = (DefaultTableModel) jtblMedicine3.getModel();
        int selectIndex = jtblMedicine3.getSelectedRow();
        int a, b, c;
        try {
            int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine3.getSelectedRow();
            String table_click = (jtblMedicine3.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medicine WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                String sname = rs.getString("stocks");
                String stocks = jtxtQuantrem.getText();
                c = Integer.parseInt(sname);
                a = Integer.parseInt(stocks);

                b = c - a;

                pst = con.prepareStatement("UPDATE medicine SET stocks=? WHERE id =?");

                pst.setInt(1, b);
                pst.setInt(2, id);
                pst.executeUpdate();

                pst = con.prepareStatement("INSERT INTO medrem (medname, quant, medissue, daterem) VALUES (?,?,?,?)");
                pst.setString(1, jtxtMednamerem.getText());
                pst.setString(2, jtxtQuantrem.getText());
                pst.setString(3, jtxtMedissue.getText());
                pst.setString(4, jtxtDateup.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Medicine Stock Removed");
                tblupdatemed();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "Sa MEDREMOVE");
        }

    }//GEN-LAST:event_jbtnRemovemedActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        jtxtMednamerem.setText(null);
        jtxtTyperem.setText(null);
        jtxtDescrem.setText(null);
        jtxtStocksrem.setText(null);
        jtxtQuantrem.setText(null);
        jtxtMedissue.setText(null);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jtblMedicine2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblMedicine2MouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine2.getSelectedRow();
            String table_click = (jtblMedicine2.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medicine WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String medname = rs.getString("medname");
                jtxtMednamerel.setText(medname);

                String stocks = rs.getString("stocks");
                jtxtMedStocksrel.setText(stocks);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblMedicine2MouseClicked

    private void jDaterelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDaterelPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jDaterelPropertyChange

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        jtxtResname.setText(null);
        jtxtAgerel.setText(null);
        jtxtConcern.setText(null);
        jtxtQuantrel.setText(null);
        jtxtMednamerel.setText(null);
        jtxtMedStocksrel.setText(null);
        jDaterel.setDate(null);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jbtnMedsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMedsave1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel) jtblMedicine2.getModel();
        int selectIndex = jtblMedicine2.getSelectedRow();
        int a, b, c;

        try {

            int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            // ===================== TABLE CLICK ==========================
            int row = jtblMedicine2.getSelectedRow();
            String table_click = (jtblMedicine2.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medicine WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String sname = rs.getString("stocks");
                String stocks = jtxtQuantrel.getText();
                c = Integer.parseInt(sname);
                a = Integer.parseInt(stocks);

                b = c - a;
                //================== UDPATE MEDICINE STOCKS =======================
                pst = con.prepareStatement("UPDATE medicine SET stocks=? WHERE id =?");

                pst.setInt(1, b);
                pst.setInt(2, id);
                pst.executeUpdate();

                //================== MEDICINE RESIDENT =============================
                pst = con.prepareStatement("INSERT INTO medrel (resname, age, concern, quant, medname, daterel) VALUES (?,?,?,?,?,?)");

                pst.setString(1, jtxtResname.getText());
                pst.setString(2, jtxtAgerel.getText());
                pst.setString(3, jtxtConcern.getText());
                pst.setString(4, jtxtQuantrel.getText());
                pst.setString(5, jtxtMednamerel.getText());
                pst.setString(6, ((JTextField) jDaterel.getDateEditor().getUiComponent()).getText());
                pst.executeUpdate();
                tblupdatemed();
                JOptionPane.showMessageDialog(null, "Successfully Save the Information");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jbtnMedsave1ActionPerformed

    private void jtblMedicine1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblMedicine1MouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine1.getSelectedRow();
            String table_click = (jtblMedicine1.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medicine WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String medname = rs.getString("medname");
                jtxtMedname1.setText(medname);

                String type = rs.getString("type");
                jtxtType1.setText(type);

                String desc = rs.getString("descrip");
                jtxtDescmed.setText(desc);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblMedicine1MouseClicked

    private void jtxtDatrecvdPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtxtDatrecvdPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDatrecvdPropertyChange

    private void jtxtDateexpPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtxtDateexpPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDateexpPropertyChange

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        jtxtMedname1.setText(null);
        jtxtType1.setText(null);
        jtxtDescmed.setText(null);
        jtxtQuant.setText(null);
        jtxtDatrecvd.setDate(null);
        jtxtDesc1.setText(null);
        jtxtDateexp.setDate(null);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jbtnMedsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMedsaveActionPerformed
        // TODO add your handling code here:
        // === Save BTN MEDICINE ADD STOCKS
        DefaultTableModel d1 = (DefaultTableModel) jtblMedicine1.getModel();
        int selectIndex = jtblMedicine1.getSelectedRow();
        int a, b, c;
        try {
            int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            // ===================== TABLE CLICK ==========================
            int row = jtblMedicine1.getSelectedRow();
            String table_click = (jtblMedicine1.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medicine WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String sname = rs.getString("stocks");
                String stocks = jtxtQuant.getText();
                c = Integer.parseInt(sname);
                a = Integer.parseInt(stocks);

                b = c + a;

                // ====================UPDATE STOCKS =============
                pst = con.prepareStatement("UPDATE medicine SET stocks=? WHERE id =?");

                pst.setInt(1, b);
                pst.setInt(2, id);
                pst.executeUpdate();

                pst = con.prepareStatement("INSERT INTO medrec (medname, type, descrip, quantity, dateissued, suppname, expirydate) VALUES (?,?,?,?,?,?,?)");
                pst.setString(1, jtxtMedname1.getText());
                pst.setString(2, jtxtType1.getText());
                pst.setString(3, jtxtDescmed.getText());
                pst.setString(4, jtxtQuant.getText());
                pst.setString(5, ((JTextField) jtxtDatrecvd.getDateEditor().getUiComponent()).getText());
                pst.setString(6, jtxtDesc1.getText());
                pst.setString(7, ((JTextField) jtxtDateexp.getDateEditor().getUiComponent()).getText());
                pst.executeUpdate();
                tblupdatemed();
                JOptionPane.showMessageDialog(null, "Successfully Add Stocks");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jbtnMedsaveActionPerformed

    private void jtblMedicineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblMedicineMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblMedicine.getSelectedRow();
            String table_click = (jtblMedicine.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM medicine WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String medname = rs.getString("medname");
                jtxtMedname.setText(medname);

                String type = rs.getString("type");
                jtxtType.setText(type);

                String desc = rs.getString("descrip");
                jtxtDesc.setText(desc);

                String stocks = rs.getString("stocks");
                jtxtStocks.setText(stocks);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblMedicineMouseClicked

    private void jbtnMedrecordclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMedrecordclearActionPerformed
        // TODO add your handling code here:
        jtxtMedname.setText(null);
        jtxtType.setText(null);
        jtxtDesc.setText(null);
        jtxtStocks.setText(null);
    }//GEN-LAST:event_jbtnMedrecordclearActionPerformed

    private void jbtnAddmdcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddmdcnActionPerformed
        // TODO add your handling code here:
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("INSERT INTO medicine (medname, type, descrip,stocks) VALUES (?,?,?,'" + jtxtStocks.getText() + "')");
            pst.setString(1, jtxtMedname.getText());
            pst.setString(2, jtxtType.getText());
            pst.setString(3, jtxtDesc.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Medicine Added");
            tblupdatemed();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jbtnAddmdcnActionPerformed

    private void jbtnUpdatedmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdatedmedActionPerformed
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel) jtblMedicine.getModel();
        int selectIndex = jtblMedicine.getSelectedRow();

        try {
            int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());

            // ====================MEDICINE INFORMATION =============
            pst = con.prepareStatement("UPDATE medicine SET medname=?, type=?, descrip=?, stocks=? WHERE id=?");

            pst.setString(1, jtxtMedname.getText());
            pst.setString(2, jtxtType.getText());
            pst.setString(3, jtxtDesc.getText());
            pst.setString(4, jtxtStocks.getText());
            pst.setInt(5, id);
            pst.executeUpdate();

            tblupdatemed();
            JOptionPane.showMessageDialog(null, "Successfully Update Medicine");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jbtnUpdatedmedActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT id AS 'ID', sname AS 'Surname', fname 'First Name', mname AS 'Middle Name', dateofbirth AS 'Date of Birth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'Place of Birth', rwheadfamily AS 'RelationShip W/ the head of the Family', contactno AS 'contactnumber', civilstat AS 'Civil Status', edcattmnt AS 'Educational Attainment',  occpt  AS 'Occupation', rlgn  AS 'Religion', ctz  AS 'Citizenship', fno  AS 'Family No.', hno  AS 'Household No.', purok  AS 'Purok', forpsmem  AS '4ps Member', dateadded  AS 'Date Added' FROM resident WHERE gender LIKE '%" + jComboBox2.getModel().getSelectedItem() + "%'");
            ResultSet rs = pst.executeQuery();

            jtxtbeditresident.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jcmbPurok3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbPurok3ItemStateChanged
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT id AS 'ID', sname AS 'Surname', fname 'First Name', mname AS 'Middle Name', dateofbirth AS 'Date of Birth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'Place of Birth', rwheadfamily AS 'RelationShip W/ the head of the Family', contactno AS 'contactnumber', civilstat AS 'Civil Status', edcattmnt AS 'Educational Attainment',  occpt  AS 'Occupation', rlgn  AS 'Religion', ctz  AS 'Citizenship', fno  AS 'Family No.', hno  AS 'Household No.', purok  AS 'Purok', forpsmem  AS '4ps Member', dateadded  AS 'Date Added' FROM resident WHERE purok LIKE '%" + jcmbPurok3.getModel().getSelectedItem() + "%'");
            ResultSet rs = pst.executeQuery();

            jtxtbeditresident.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jcmbPurok3ItemStateChanged

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT id AS 'ID', sname AS 'Surname', fname 'First Name', mname AS 'Middle Name', dateofbirth AS 'Date of Birth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'Place of Birth', rwheadfamily AS 'RelationShip W/ the head of the Family', contactno AS 'ContactNumber', civilstat AS 'Civil Status', edcattmnt AS 'Educational Attainment',  occpt  AS 'Occupation', rlgn  AS 'Religion', ctz  AS 'Citizenship', fno  AS 'Family No.', hno  AS 'Household No.', purok  AS 'Purok', forpsmem  AS '4ps Member', dateadded  AS 'Date Added' FROM resident WHERE sname LIKE '%" + jTextField37.getText() + "%' OR fname LIKE '%" + jTextField37.getText() + "%' OR mname LIKE '%" + jTextField37.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtxtbeditresident.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jtxtbeditresidentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtbeditresidentMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtxtbeditresident.getSelectedRow();
            String table_click = (jtxtbeditresident.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM resident WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String sname = rs.getString("sname");
                jtxtsname1.setText(sname);

                String fname = rs.getString("fname");
                jtxtfname1.setText(fname);

                String mname = rs.getString("mname");
                jtxtmname1.setText(mname);

                Date bday = rs.getDate("dateofbirth");
                jDateChooserbirth1.setDate(bday);

                String age = rs.getString("age");
                jtxtAge1.setText(age);

                String gender = rs.getString("gender");
                jcmbgender1.getModel().setSelectedItem(gender);

                String plcofbirth = rs.getString("plcofbirth");
                jtxtplcbirth1.setText(plcofbirth);

                String rwheadfamily = rs.getString("rwheadfamily");
                jtxtrwfamilyhead1.setText(rwheadfamily);

                String contactno = rs.getString("contactno");
                jtxtContactno.setText(contactno);

                String civilstat = rs.getString("civilstat");
                jtxtcivilstatus1.getModel().setSelectedItem(civilstat);

                String edcattmnt = rs.getString("edcattmnt");
                jtxteducationalattmnt1.getModel().setSelectedItem(edcattmnt);

                String occpt = rs.getString("occpt");
                jtxtOcptn1.setText(occpt);

                String rlgn = rs.getString("rlgn");
                jtxtRlgn1.getModel().setSelectedItem(rlgn);

                String ctz = rs.getString("ctz");
                jtxCtzn1.setText(ctz);

                String fno = rs.getString("fno");
                jtxtFno1.setText(fno);

                String hno = rs.getString("hno");
                jtxtHno1.setText(hno);

                String purok = rs.getString("purok");
                jcmbPurok1.getModel().setSelectedItem(purok);

                String forpsmem = rs.getString("forpsmem");
                jcmb4ps1.getModel().setSelectedItem(forpsmem);

                String emailadd = rs.getString("emailadd");
                jtxtEmailaddeditres.setText(emailadd);

                //                String dateaddedd = rs.getString("dateadded");
                //                jtxtrwfamilyhead2.setToolTipText(dateaddedd);
                Date dateaddedd = rs.getDate("dateadded");
                jtxtrwfamilyhead2.setDate(dateaddedd);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtxtbeditresidentMouseClicked

    private void jbtnSaveaddresident2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveaddresident2ActionPerformed
        // TODO add your handling code here:
        String m = JOptionPane.showInputDialog(null, "Issue:", "Remove resident", JOptionPane.INFORMATION_MESSAGE);
        issue.setText(m);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("INSERT INTO removeres (sname,fname,mname,dateofbirth,age,gender,plcofbirth,rwheadfamily,contactno,civilstat,edcattmnt,occpt,rlgn,ctz,fno,hno,purok,forpsmem,emailadd,dateadded,dateremoved,issue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, jtxtsname1.getText());
            pst.setString(2, jtxtfname1.getText());
            pst.setString(3, jtxtmname1.getText());
            pst.setString(4, ((JTextField) jDateChooserbirth1.getDateEditor().getUiComponent()).getText());
            pst.setString(5, jtxtAge1.getText());
            pst.setString(6, jcmbgender1.getSelectedItem().toString());
            pst.setString(7, jtxtplcbirth1.getText());
            pst.setString(8, jtxtrwfamilyhead1.getText());
            pst.setString(9, jtxtContactno.getText());
            pst.setString(10, jtxtcivilstatus1.getSelectedItem().toString());
            pst.setString(11, jtxteducationalattmnt1.getSelectedItem().toString());
            pst.setString(12, jtxtOcptn1.getText());
            pst.setString(13, jtxtRlgn1.getSelectedItem().toString());
            pst.setString(14, jtxCtzn1.getText());
            pst.setString(15, jtxtFno1.getText());
            pst.setString(16, jtxtHno1.getText());
            pst.setString(17, jcmbPurok1.getSelectedItem().toString());
            pst.setString(18, jcmb4ps1.getSelectedItem().toString());
            pst.setString(19, jtxtEmailaddeditres.getText());
            pst.setString(20, ((JTextField) jtxtrwfamilyhead2.getDateEditor().getUiComponent()).getText());
            pst.setString(21, jtxtDateup.getText());
            pst.setString(22, issue.getText());
            pst.executeUpdate();

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtxtbeditresident.getSelectedRow();
            String table_click = (jtxtbeditresident.getModel().getValueAt(row, 0).toString());

            pst = con.prepareStatement("DELETE FROM resident WHERE id = '" + table_click + "'");

            pst.executeUpdate();

            tableupdate();

            JOptionPane.showMessageDialog(null, "Successfully Remove");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Please Fill up the textbox! ");
        }
    }//GEN-LAST:event_jbtnSaveaddresident2ActionPerformed

    private void jtxtRlgn1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtRlgn1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtRlgn1FocusGained

    private void jtxteducationalattmnt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxteducationalattmnt1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxteducationalattmnt1FocusGained

    private void jtxtrwfamilyhead2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtxtrwfamilyhead2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtrwfamilyhead2PropertyChange

    private void jtxtFno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtFno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtFno1ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        jtxtsname1.setText(null);
        jtxtfname1.setText(null);
        jtxtmname1.setText(null);
        jDateChooserbirth1.setDate(null);
        jtxtAge1.setText(null);
        jcmbgender1.setSelectedIndex(-1);
        jtxtplcbirth1.setText(null);
        jtxtrwfamilyhead1.setText(null);
        jtxtContactno.setText(null);
        jtxtcivilstatus1.setSelectedIndex(-1);
        jtxteducationalattmnt1.setSelectedIndex(-1);
        jtxtOcptn1.setText(null);
        jtxtRlgn1.setSelectedIndex(-1);
        jtxCtzn1.setText(null);
        jtxtFno1.setText(null);
        jtxtHno1.setText(null);
        jcmbPurok1.setSelectedIndex(-1);
        jcmb4ps1.setSelectedIndex(-1);
        jtxtEmailaddeditres.setText(null);
        jtxtrwfamilyhead2.setDate(null);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jbtnSaveaddresident1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveaddresident1ActionPerformed
        // TODO add your handling code here:
        Date d = new Date();

        DefaultTableModel d1 = (DefaultTableModel) jtxtbeditresident.getModel();
        int selectIndex = jtxtbeditresident.getSelectedRow();
        if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", jtxtEmailaddeditres.getText()))) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (cpnumber(jtxtContactno.getText())) {
//            JOptionPane.showMessageDialog(null, "OK");
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid Cell Phone Number");
        }
        try {
            int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("UPDATE resident SET sname=?,fname=?, mname=?, dateofbirth=?, age=?, gender=?, plcofbirth=?, rwheadfamily=?, contactno=?, civilstat=?, edcattmnt=?, occpt=?, rlgn=?, ctz=?, fno=?, hno=?, purok=?, forpsmem=?, emailadd=?, dateadded=? WHERE id=? ");

            pst.setString(1, jtxtsname1.getText());
            pst.setString(2, jtxtfname1.getText());
            pst.setString(3, jtxtmname1.getText());
            pst.setString(4, ((JTextField) jDateChooserbirth1.getDateEditor().getUiComponent()).getText());
            pst.setString(5, jtxtAge1.getText());
            pst.setString(6, jcmbgender1.getSelectedItem().toString());
            pst.setString(7, jtxtplcbirth1.getText());
            pst.setString(8, jtxtrwfamilyhead1.getText());
            pst.setString(9, jtxtContactno.getText());
            pst.setString(10, jtxtcivilstatus1.getSelectedItem().toString());
            pst.setString(11, jtxteducationalattmnt1.getSelectedItem().toString());
            pst.setString(12, jtxtOcptn1.getText());
            pst.setString(13, jtxtRlgn1.getSelectedItem().toString());
            pst.setString(14, jtxCtzn1.getText());
            pst.setString(15, jtxtFno1.getText());
            pst.setString(16, jtxtHno1.getText());
            pst.setString(17, jcmbPurok1.getSelectedItem().toString());
            pst.setString(18, jcmb4ps1.getSelectedItem().toString());
            pst.setString(19, jtxtEmailaddeditres.getText());
            pst.setString(20, ((JTextField) jtxtrwfamilyhead2.getDateEditor().getUiComponent()).getText());
            pst.setInt(21, id);
            pst.executeUpdate();
            pst.close();

            jtxtsname1.setText(null);
            jtxtfname1.setText(null);
            jtxtmname1.setText(null);
            jDateChooserbirth1.setDate(null);
            jtxtAge1.setText(null);
            jcmbgender1.setSelectedIndex(-1);
            jtxtplcbirth1.setText(null);
            jtxtrwfamilyhead1.setText(null);
            jtxtContactno.setText(null);
            jtxtcivilstatus1.setSelectedIndex(-1);
            jtxteducationalattmnt1.setSelectedIndex(-1);
            jtxtOcptn1.setText(null);
            jtxtRlgn1.setSelectedIndex(-1);
            jtxCtzn1.setText(null);
            jtxtFno1.setText(null);
            jtxtHno1.setText(null);
            jcmbPurok1.setSelectedIndex(-1);
            jcmb4ps1.setSelectedIndex(-1);
            jtxtEmailaddeditres.setText(null);
            jtxtrwfamilyhead2.setDate(d);
            tableupdate();
            JOptionPane.showMessageDialog(null, "Successfully Updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR DITO SA SAVE SA EDIT" + e);
        }
    }//GEN-LAST:event_jbtnSaveaddresident1ActionPerformed

    private void jcmbgender1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbgender1FocusGained
        // TODO add your handling code here:
        int yearnow = YearMonth.now().getYear();
        DateFormat birthyear = new SimpleDateFormat("yyyy");
        int date = Integer.parseInt(birthyear.format(jDateChooserbirth1.getDate()));
        int age = yearnow - date;
        jtxtAge1.setText(String.valueOf(age));

    }//GEN-LAST:event_jcmbgender1FocusGained

    private void jDateChooserbirth1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserbirth1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserbirth1PropertyChange

    private void jcmbPurok2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbPurok2ItemStateChanged
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT id AS 'ID', sname AS 'Surname', fname 'First Name', mname AS 'Middle Name', dateofbirth AS 'Date of Birth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'Place of Birth', rwheadfamily AS 'RelationShip W/ the head of the Family', contactno AS 'contactnumber', civilstat AS 'Civil Status', edcattmnt AS 'Educational Attainment',  occpt  AS 'Occupation', rlgn  AS 'Religion', ctz  AS 'Citizenship', fno  AS 'Family No.', hno  AS 'Household No.', purok  AS 'Purok', forpsmem  AS '4ps Member', dateadded  AS 'Date Added' FROM resident WHERE purok LIKE '%" + jcmbPurok2.getModel().getSelectedItem() + "%'");
            ResultSet rs = pst.executeQuery();

            jtbladdresident.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jcmbPurok2ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT id AS 'ID', sname AS 'Surname', fname 'First Name', mname AS 'Middle Name', dateofbirth AS 'Date of Birth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'Place of Birth', rwheadfamily AS 'RelationShip W/ the head of the Family', contactno AS 'contactnumber', civilstat AS 'Civil Status', edcattmnt AS 'Educational Attainment',  occpt  AS 'Occupation', rlgn  AS 'Religion', ctz  AS 'Citizenship', fno  AS 'Family No.', hno  AS 'Household No.', purok  AS 'Purok', forpsmem  AS '4ps Member', dateadded  AS 'Date Added' FROM resident WHERE gender LIKE '" + jComboBox1.getModel().getSelectedItem() + "'");
            ResultSet rs = pst.executeQuery();

            jtbladdresident.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jbtnsearchaddresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnsearchaddresActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT id AS 'ID', sname AS 'Surname', fname 'First Name', mname AS 'Middle Name', dateofbirth AS 'Date of Birth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'Place of Birth', rwheadfamily AS 'RelationShip W/ the head of the Family', contactno AS 'ContactNumber', civilstat AS 'Civil Status', edcattmnt AS 'Educational Attainment',  occpt  AS 'Occupation', rlgn  AS 'Religion', ctz  AS 'Citizenship', fno  AS 'Family No.', hno  AS 'Household No.', purok  AS 'Purok', forpsmem  AS '4ps Member', dateadded  AS 'Date Added' FROM resident WHERE sname LIKE '%" + jtxtSearchaddres.getText() + "%' OR fname LIKE '%" + jtxtSearchaddres.getText() + "%' OR mname LIKE '%" + jtxtSearchaddres.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtbladdresident.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jbtnsearchaddresActionPerformed

    private void jtbladdresidentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbladdresidentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbladdresidentMouseClicked

    private void jtxtRlgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtRlgnFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtRlgnFocusGained

    private void jtxteducationalattmntFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxteducationalattmntFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxteducationalattmntFocusGained

    private void jtxtrContactnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtrContactnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtrContactnumActionPerformed

    private void jbtnSaveaddresidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveaddresidentActionPerformed
        String m = JOptionPane.showInputDialog(null, "Issue:", "Add Resident", JOptionPane.INFORMATION_MESSAGE);
        issue1.setText(m);
        if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", jtxtEmailaddress.getText()))) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } // Error in email address

        if (cpnumber(jtxtrContactnum.getText())) {
//            JOptionPane.showMessageDialog(null, "OK");
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid Cell Phone Number");
        } //error in Phone Number
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("INSERT INTO resident (sname,fname,mname,dateofbirth,age,gender,plcofbirth,rwheadfamily,contactno,civilstat,edcattmnt,occpt,rlgn,ctz,fno,hno,purok,forpsmem,emailadd,dateadded) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, jtxtsname.getText());
            pst.setString(2, jtxtfname.getText());
            pst.setString(3, jtxtmname.getText());
            pst.setString(4, ((JTextField) jDateChooserbirth.getDateEditor().getUiComponent()).getText());
            pst.setString(5, jtxtAge.getText());
            pst.setString(6, jcmbgender.getSelectedItem().toString());
            pst.setString(7, jtxtplcbirth.getText());
            pst.setString(8, jtxtrwfamilyhead.getText());
            pst.setString(9, jtxtrContactnum.getText());
            pst.setString(10, jtxtcivilstatus.getSelectedItem().toString());
            pst.setString(11, jtxteducationalattmnt.getSelectedItem().toString());
            pst.setString(12, jtxtOcptn.getText());
            pst.setString(13, jtxtRlgn.getSelectedItem().toString());
            pst.setString(14, jtxCtzn.getText());
            pst.setString(15, jtxtFno.getText());
            pst.setString(16, jtxtHno.getText());
            pst.setString(17, jcmbPurok.getSelectedItem().toString());
            pst.setString(18, jcmb4ps.getSelectedItem().toString());
            pst.setString(19, jtxtEmailaddress.getText());
            pst.setString(20, jtxtDateadd.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Saved");

            jtxtsname.setText(null);
            jtxtfname.setText(null);
            jtxtmname.setText(null);
            jDateChooserbirth.setDate(null);
            jtxtAge.setText(null);
            jcmbgender.setSelectedIndex(-1);
            jtxtplcbirth.setText(null);
            jtxtrwfamilyhead.setText(null);
            jtxtrContactnum.setText(null);
            jtxtcivilstatus.setSelectedIndex(-1);
            jtxteducationalattmnt.setSelectedIndex(-1);
            jtxtOcptn.setText(null);
            jtxtRlgn.setSelectedIndex(-1);
            jtxCtzn.setText("Filipino");
            jtxtFno.setText(null);
            jtxtHno.setText(null);
            jcmbPurok.setSelectedIndex(-1);
            jcmb4ps.setSelectedIndex(-1);
            jtxtEmailaddress.setText(null);
            jtxtEmailaddress.setText(null);
            tableupdate();
            purok();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR DITO SA SAVE" + e);
        }


    }//GEN-LAST:event_jbtnSaveaddresidentActionPerformed

    private void jbtnAddresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddresActionPerformed
        // TODO add your handling code here:
        jtxtsname.setText(null);
        jtxtfname.setText(null);
        jtxtmname.setText(null);
        jDateChooserbirth.setDate(null);
        jtxtAge.setText(null);
        jcmbgender.setSelectedIndex(-1);
        jtxtplcbirth.setText(null);
        jtxtrwfamilyhead.setText(null);
        jtxtrContactnum.setText(null);
        jtxtcivilstatus.setSelectedIndex(-1);
        jtxteducationalattmnt.setSelectedIndex(-1);
        jtxtOcptn.setText(null);
        jtxtRlgn.setSelectedIndex(-1);
        jtxCtzn.setText("Filipino");
        jtxtFno.setText(null);
        jtxtHno.setText(null);
        jcmbPurok.setSelectedIndex(-1);
        jcmb4ps.setSelectedIndex(-1);
        jtxtEmailaddress.setText(null);
        jtxtEmailaddress.setText(null);

    }//GEN-LAST:event_jbtnAddresActionPerformed

    private void jcmbgenderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbgenderFocusGained
        // TODO add your handling code here:
        int yearnow = YearMonth.now().getYear();
        DateFormat birthyear = new SimpleDateFormat("yyyy");
        int date = Integer.parseInt(birthyear.format(jDateChooserbirth.getDate()));
        int age = yearnow - date;
        jtxtAge.setText(String.valueOf(age));
    }//GEN-LAST:event_jcmbgenderFocusGained

    private void jDateChooserbirthPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserbirthPropertyChange

    }//GEN-LAST:event_jDateChooserbirthPropertyChange

    private void jcmbPurok5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbPurok5ItemStateChanged
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', sname AS 'Surname', fname AS 'Firstname', mname AS 'Middlename', dateofbirth AS 'DateofBirth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'PlaceofBirth', rwheadfamily AS 'RelationShip W/ the head of the Family', civilstat AS 'CivilStatus', edcattmnt AS 'Educational Attainment', occpt AS 'Occupation', rlgn AS 'Religion', ctz AS 'Citizenship', fno AS 'Family No.', hno AS 'Household No.', purok AS 'Purok', forpsmem AS '4PsMember', emailadd AS 'Email Address', dateadded AS 'DateAdded', dateremoved AS 'DateRemoved', issue AS 'Issue' FROM removeres WHERE purok LIKE '" + jcmbPurok5.getModel().getSelectedItem() + "'");
            ResultSet rs = pst.executeQuery();

            jtbladdresident1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jcmbPurok5ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', sname AS 'Surname', fname AS 'Firstname', mname AS 'Middlename', dateofbirth AS 'DateofBirth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'PlaceofBirth', rwheadfamily AS 'RelationShip W/ the head of the Family', civilstat AS 'CivilStatus', edcattmnt AS 'Educational Attainment', occpt AS 'Occupation', rlgn AS 'Religion', ctz AS 'Citizenship', fno AS 'Family No.', hno AS 'Household No.', purok AS 'Purok', forpsmem AS '4PsMember', emailadd AS 'Email Address', dateadded AS 'DateAdded', dateremoved AS 'DateRemoved', issue AS 'Issue' FROM removeres WHERE gender LIKE '" + jComboBox3.getModel().getSelectedItem() + "'");
            ResultSet rs = pst.executeQuery();

            jtbladdresident1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jbtnsearchaddres1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnsearchaddres1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', sname AS 'Surname', fname AS 'Firstname', mname AS 'Middlename', dateofbirth AS 'DateofBirth', age AS 'Age', gender AS 'Gender', plcofbirth AS 'PlaceofBirth', rwheadfamily AS 'RelationShip W/ the head of the Family', civilstat AS 'CivilStatus', edcattmnt AS 'Educational Attainment', occpt AS 'Occupation', rlgn AS 'Religion', ctz AS 'Citizenship', fno AS 'Family No.', hno AS 'Household No.', purok AS 'Purok', forpsmem AS '4PsMember', emailadd AS 'Email Address', dateadded AS 'DateAdded', dateremoved AS 'DateRemoved', issue AS 'Issue' FROM removeres WHERE sname LIKE '%" + jtxtSearchaddres1.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtbladdresident1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jbtnsearchaddres1ActionPerformed

    private void jtbladdresident1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbladdresident1MouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtbladdresident1.getSelectedRow();
            String table_click = (jtbladdresident1.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM removeres WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String sname = rs.getString("sname");
                jtxtSname3.setText(sname);

                String fname = rs.getString("fname");
                jtxtFname2.setText(fname);

                String mname = rs.getString("mname");
                jtxtMname2.setText(mname);

                String bday = rs.getString("dateofbirth");
                jtxtDateiofbirth2.setText(bday);

                String age = rs.getString("age");
                jtxtAge5.setText(age);

                String gender = rs.getString("gender");
                jtxtGender2.setText(gender);

                String plcofbirth = rs.getString("plcofbirth");
                jtxtPlaceofBirth2.setText(plcofbirth);

                String rwheadfamily = rs.getString("rwheadfamily");
                jtxtrelwfamhead2.setText(rwheadfamily);

                String contactno = rs.getString("contactno");
                jtxtContnum2.setText(contactno);

                String civilstat = rs.getString("civilstat");
                jtxtCivilstat2.setText(civilstat);

                String edcattmnt = rs.getString("edcattmnt");
                jtxtEducattmnt2.setText(edcattmnt);

                String occpt = rs.getString("occpt");
                jtxtOccptn2.setText(occpt);

                String rlgn = rs.getString("rlgn");
                jtxtRgln2.setText(rlgn);

                String ctz = rs.getString("ctz");
                jtxtCtzn2.setText(ctz);

                String fno = rs.getString("fno");
                jtxtFno5.setText(fno);

                String hno = rs.getString("hno");
                jtxtHno5.setText(hno);

                String purok = rs.getString("purok");
                jtxtPurok2.setText(purok);

                String forpsmem = rs.getString("forpsmem");
                jtxtforpsmem1.setText(forpsmem);

                String emailadd = rs.getString("emailadd");
                jtxtEmailaddviewres1.setText(emailadd);

                String dateaddedd = rs.getString("dateadded");
                jtxtDateadded2.setText(dateaddedd);

                String dateremoved = rs.getString("dateremoved");
                jtxtDateadded3.setForeground(Color.red);
                jtxtDateadded3.setText(dateremoved);

                String issue = rs.getString("issue");
                jtxtDateadded4.setForeground(Color.red);
                jtxtDateadded4.setText(issue);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtbladdresident1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        //         DefaultTableModel d1 = (DefaultTableModel)jtblMedicine.getModel();
        //        int selectIndex = jtblMedicine.getSelectedRow();
        try {
            //            int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");

            pst = con.prepareStatement("UPDATE birthrec SET  sname=?, mname=?, nameofparent=?, dateofbirth=?, hsno=?, fno=?, purok=?, gender=?, birthweight=?, plcofbirth=?, registered=? WHERE nameofchild=?");

            pst.setString(1, jTextField16.getText());
            pst.setString(2, jTextField18.getText());
            pst.setString(3, jtxtNameofparentreg.getText());
            pst.setString(4, ((JTextField) jDateChooser5.getDateEditor().getUiComponent()).getText());
            pst.setString(5, jtxtHsnobirthreg.getText());
            pst.setString(6, jtxtFnobirthreg.getText());
            pst.setString(7, jComboBox6.getSelectedItem().toString());
            pst.setString(8, jComboBox5.getSelectedItem().toString());
            pst.setString(9, jTextField22.getText());
            pst.setString(10, jTextField21.getText());
            pst.setString(11, jComboBox4.getSelectedItem().toString());
            pst.setString(12, jtxtNameofChildreg.getText());

            //                pst.setInt(11, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Updated Sucessfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jbtnBirthRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBirthRegActionPerformed
        // TODO add your handling code here:
        jtxtNameofChildreg.setText(null);
        jTextField16.setText(null);
//        jTextField17.setText(null);
        jTextField18.setText(null);
        jtxtNameofparentreg.setText(null);
        jDateChooser5.setDate(null);
        jtxtHsnobirthreg.setText(null);
        jtxtFnobirthreg.setText(null);
        jComboBox6.setSelectedIndex(-1);
        jComboBox5.setSelectedIndex(-1);
        jTextField22.setText(null);
        jTextField21.setText(null);
        jComboBox4.setSelectedIndex(-1);
    }//GEN-LAST:event_jbtnBirthRegActionPerformed

    private void jtblBirthregMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblBirthregMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblBirthreg.getSelectedRow();
            String table_click = (jtblBirthreg.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM birthrec WHERE nameofchild='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String nameofchild = rs.getString("nameofchild");
                jtxtNameofChildreg.setText(nameofchild);

                String sname = rs.getString("sname");
                jTextField16.setText(sname);

                String mname = rs.getString("mname");
                jTextField18.setText(mname);

                String nameofparent = rs.getString("nameofparent");
                jtxtNameofparentreg.setText(nameofparent);

                Date dateofbirth = rs.getDate("dateofbirth");
                jDateChooser5.setDate(dateofbirth);

                String hsno = rs.getString("hsno");
                jtxtHsnobirthreg.setText(hsno);

                String fno = rs.getString("fno");
                jtxtFnobirthreg.setText(fno);

                String purok = rs.getString("purok");
                jComboBox6.getModel().setSelectedItem(purok);

                String gender = rs.getString("gender");
                jComboBox5.getModel().setSelectedItem(gender);

                String birthweight = rs.getString("birthweight");
                jTextField22.setText(birthweight);

                String plcofbirth = rs.getString("plcofbirth");
                jTextField21.setText(plcofbirth);

                String registered = rs.getString("registered");
                jComboBox4.getModel().setSelectedItem(registered);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblBirthregMouseClicked

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jtxtHsnobirthregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtHsnobirthregActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtHsnobirthregActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

//            
//        if (cpnumber(jtxtContactno.getText())) {
////            JOptionPane.showMessageDialog(null, "OK");
//        } else {
//            JOptionPane.showMessageDialog(null, "Please Enter a Valid Cell Phone Number");
//        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("INSERT INTO pregnancyrec (nameofparent,dateofbirth,age,hsno,fno,purok,mobilenum,pih,lmp,edc,gp,lstcheckup,datedlvrd,nameofchild) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, jtxtNameofparent.getText());
            pst.setString(2, ((JTextField) jDateofbirth.getDateEditor().getUiComponent()).getText());
            pst.setString(3, jtxtAgepreg.getText());
            pst.setString(4, jtxtHouseno.getText());
            pst.setString(5, jtxtFamilyno.getText());
            pst.setString(6, jcmbPurokpreg.getSelectedItem().toString());
            pst.setString(7, jtxtMobilenum.getText());
            pst.setString(8, jtxtPih.getText());
            pst.setString(9, ((JTextField) jDateLMP.getDateEditor().getUiComponent()).getText());
            pst.setString(10, jtxtEDC.getText());
            pst.setString(11, jtxtGp.getText());
            pst.setString(12, ((JTextField) jdateLstchckup.getDateEditor().getUiComponent()).getText());
            pst.setString(13, ((JTextField) jdateDlvrd.getDateEditor().getUiComponent()).getText());
            pst.setString(14, jtxtNameofchild.getText());

            pst.executeUpdate();

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("INSERT INTO birthrec (nameofchild,sname,mname,nameofparent,dateofbirth,hsno,fno,purok,gender,birthweight,plcofbirth,registered) VALUES (?,'','',?,?,?,?,?,'','','','')");
            pst.setString(1, jtxtNameofchild.getText());
            pst.setString(2, jtxtNameofparent.getText());
            pst.setString(3, ((JTextField) jdateDlvrd.getDateEditor().getUiComponent()).getText());
            pst.setString(4, jtxtHouseno.getText());
            pst.setString(5, jtxtFamilyno.getText());
            pst.setString(6, jcmbPurokpreg.getSelectedItem().toString());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully Saved");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jbtnpregclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnpregclearActionPerformed
        // TODO add your handling code here:
        jtxtNameofparent.setText(null);
        jDateofbirth.setDate(null);
        jtxtAgepreg.setText(null);
        jtxtHouseno.setText(null);
        jtxtFamilyno.setText(null);
        jcmbPurokpreg.setSelectedIndex(-1);
        jtxtMobilenum.setText(null);
        jtxtPih.setText(null);
        jDateLMP.setDate(null);
        jtxtEDC.setText(null);
        jtxtGp.setText(null);
        jdateLstchckup.setDate(null);
        jdateDlvrd.setDate(null);
        jtxtNameofchild.setText(null);
    }//GEN-LAST:event_jbtnpregclearActionPerformed

    private void jtxtEDCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtEDCFocusGained
        // TODO add your handling code here:
        //        int yearnow = YearMonth.now().getMonthValue();
        try {
            //            DateFormat birthyear = new SimpleDateFormat("yyyy-MM-dd");
            //            Date date = jDateLMP.getDate();
            //       int edc = Math.abs(date.getTime() + 9);
            //        DateFormat birthyear = new SimpleDateFormat("yyyy-MM-dd");
            //        int month = Integer.parseInt(birthyear.format(jDateLMP.getDate()));
            //            date.getTime ();
            //              date.setMonth(9,Months);
            //Calendar calendar = Calendar.getInstance();
            //calendar.add(Calendar.MONTH, 8);
            //jtxtEDC.setText(calendar);

            ////        int edc = 9 + month;
            //
            //        jtxtEDC.setText(String.valueOf(month));
            //  jDateLMP.D
            Calendar now = jDateLMP.getCalendar();
            now.add(Calendar.MONTH, 9);
            now.add(Calendar.DATE, 6);
            jtxtEDC.setText(now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtxtEDCFocusGained

    private void jtxtHousenoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtHousenoFocusGained
        // TODO add your handling code here:

        int yearnow = YearMonth.now().getYear();
        DateFormat birthyear = new SimpleDateFormat("yyyy");
        int date = Integer.parseInt(birthyear.format(jDateofbirth.getDate()));
        int age = yearnow - date;
        jtxtAgepreg.setText(String.valueOf(age));
    }//GEN-LAST:event_jtxtHousenoFocusGained

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:

        String value = (String) jcmbsystolic.getSelectedItem();
        String value1 = (String) jcmbdiastolic.getSelectedItem();
        int result = Integer.parseInt(value);
        int result1 = Integer.parseInt(value1);
        if (result <= 90 && result1 <= 60) {
            jtxtBP.setText("Low");
            jtxtBP.setForeground(Color.magenta);
        } else if (result == 70 && result1 == 40) {
            jtxtBP.setText("Low");
            jtxtBP.setForeground(Color.magenta);
        } else if (result <= 120 && result1 <= 80) {
            jtxtBP.setText("Normal");
            jtxtBP.setForeground(Color.green);
        } else if (result <= 140 && result1 <= 90) {
            jtxtBP.setText("Pre-High");
            jtxtBP.setForeground(Color.orange);
        } else if (result <= 190 && result1 <= 100) {
            jtxtBP.setText("High");
            jtxtBP.setForeground(Color.red);
        } else if (result >= 190 && result1 >= 100) {
            jtxtBP.setText("High");
            jtxtBP.setForeground(Color.red);
        } else if (result >= 70 && result1 >= 110) {
            jtxtBP.setText("High");
            jtxtBP.setForeground(Color.red);
        } else if (result >= 200 && result1 >= 40) {
            jtxtBP.setText("High");
            jtxtBP.setForeground(Color.red);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String systolic = jcmbsystolic.getSelectedItem().toString();
        String diastolic = jcmbdiastolic.getSelectedItem().toString();
        String joint = systolic + "/" + diastolic;

        if (cpnumber(jtxtContnum1.getText())) {
//            JOptionPane.showMessageDialog(null, "OK");
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid Cell Phone Number");
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("INSERT INTO bprecord (name,age,contactno,bp,level,date) VALUES (?,?,?,?,?,?)");
            pst.setString(1, jtxtresname.getText());
            pst.setString(2, jtxtage.getText());
            pst.setString(3, jtxtContnum1.getText());
            pst.setString(4, joint);
            pst.setString(5, jtxtBP.getText());
            pst.setString(6, jtxtBP1.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Save Successfully");

            jtxtresname.setText(null);
            jtxtage.setText(null);
            jtxtContnum1.setText(null);
            jcmbsystolic.setSelectedIndex(-1);
            jcmbdiastolic.setSelectedIndex(-1);
            jtxtBP.setText(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jtxtresname.setText(null);
        jtxtage.setText(null);
        jtxtContnum1.setText(null);
        jcmbsystolic.setSelectedIndex(-1);
        jcmbdiastolic.setSelectedIndex(-1);
        jtxtBP.setText(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtblforpsmemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblforpsmemMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblforpsmem.getSelectedRow();
            String table_click = (jtblforpsmem.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM resident WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String sname = rs.getString("sname");
                jtxtSname2.setText(sname);

                String fname = rs.getString("fname");
                jtxtFname1.setText(fname);

                String mname = rs.getString("mname");
                jtxtMname1.setText(mname);

                String bday = rs.getString("dateofbirth");
                jtxtDateiofbirth1.setText(bday);

                String age = rs.getString("age");
                jtxtAge3.setText(age);

                String gender = rs.getString("gender");
                jtxtGender1.setText(gender);

                String plcofbirth = rs.getString("plcofbirth");
                jtxtPlaceofBirth1.setText(plcofbirth);

                String rwheadfamily = rs.getString("rwheadfamily");
                jtxtrelwfamhead1.setText(rwheadfamily);

                String civilstat = rs.getString("civilstat");
                jtxtCivilstat1.setText(civilstat);

                String contactno = rs.getString("contactno");
                jtxtContactnum1.setText(contactno);

                String edcattmnt = rs.getString("edcattmnt");
                jtxtEducattmnt1.setText(edcattmnt);

                String occpt = rs.getString("occpt");
                jtxtOccptn1.setText(occpt);

                String rlgn = rs.getString("rlgn");
                jtxtRgln1.setText(rlgn);

                String ctz = rs.getString("ctz");
                jtxtCtzn1.setText(ctz);

                String fno = rs.getString("fno");
                jtxtFno3.setText(fno);

                String hno = rs.getString("hno");
                jtxtHno3.setText(hno);

                String purok = rs.getString("purok");
                jtxtPurok1.setText(purok);

                String dateaddedd = rs.getString("dateadded");
                jtxtDateadded1.setText(dateaddedd);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblforpsmemMouseClicked

    private void jtblviewresidentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblviewresidentMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            int row = jtblviewresident.getSelectedRow();
            String table_click = (jtblviewresident.getModel().getValueAt(row, 0).toString());
            pst = con.prepareStatement("SELECT * FROM resident WHERE id='" + table_click + "' ");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String sname = rs.getString("sname");
                jtxtSname1.setText(sname);

                String fname = rs.getString("fname");
                jtxtFname.setText(fname);

                String mname = rs.getString("mname");
                jtxtMname.setText(mname);

                String bday = rs.getString("dateofbirth");
                jtxtDateiofbirth.setText(bday);

                String age = rs.getString("age");
                jtxtAge2.setText(age);

                String gender = rs.getString("gender");
                jtxtGender.setText(gender);

                String plcofbirth = rs.getString("plcofbirth");
                jtxtPlaceofBirth.setText(plcofbirth);

                String rwheadfamily = rs.getString("rwheadfamily");
                jtxtrelwfamhead.setText(rwheadfamily);

                String contactno = rs.getString("contactno");
                jtxtContnum.setText(contactno);

                String civilstat = rs.getString("civilstat");
                jtxtCivilstat.setText(civilstat);

                String edcattmnt = rs.getString("edcattmnt");
                jtxtEducattmnt.setText(edcattmnt);

                String occpt = rs.getString("occpt");
                jtxtOccptn.setText(occpt);

                String rlgn = rs.getString("rlgn");
                jtxtRgln.setText(rlgn);

                String ctz = rs.getString("ctz");
                jtxtCtzn.setText(ctz);

                String fno = rs.getString("fno");
                jtxtFno2.setText(fno);

                String hno = rs.getString("hno");
                jtxtHno2.setText(hno);

                String purok = rs.getString("purok");
                jtxtPurok.setText(purok);

                String forpsmem = rs.getString("forpsmem");
                jtxtforpsmem.setText(forpsmem);

                String emailadd = rs.getString("emailadd");
                jtxtEmailaddviewres.setText(emailadd);

                String dateaddedd = rs.getString("dateadded");
                jtxtDateadded.setText(dateaddedd);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtblviewresidentMouseClicked

    private void jtxtrContactnumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtrContactnumKeyTyped
        // TODO add your handling code here:
        char iNumber = evt.getKeyChar();
        if (!(Character.isDigit(iNumber))
                || (iNumber == KeyEvent.VK_BACK_SPACE)
                || (iNumber == KeyEvent.VK_DELETE)) {
            evt.consume();
        }

        if (jtxtrContactnum.getText().length() >= 11) // limit textfield to 3 characters
        {
            evt.consume();
        }
    }//GEN-LAST:event_jtxtrContactnumKeyTyped

    private void jtxtContactnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtContactnoKeyTyped
        // TODO add your handling code here:
        char iNumber = evt.getKeyChar();
        if (!(Character.isDigit(iNumber))
                || (iNumber == KeyEvent.VK_BACK_SPACE)
                || (iNumber == KeyEvent.VK_DELETE)) {
            evt.consume();
        }

        if (jtxtContactno.getText().length() >= 11) // limit textfield to 3 characters
        {
            evt.consume();
        }
    }//GEN-LAST:event_jtxtContactnoKeyTyped

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT id AS 'ID', name AS 'Name', age AS 'Age', contactno AS 'Contact No.', bp AS 'BP', level AS 'Level', date AS 'Date' FROM bprecord WHERE name LIKE '%" + jTextField14.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblBp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  nameofparent AS 'NameofParent', dateofbirth AS 'DateofBirth', age AS 'Age', hsno AS 'Household No.', fno AS 'Family No', purok AS 'Purok', mobilenum AS 'Mobile No.', pih AS 'PIH', lmp AS 'LMP', edc AS 'EDC', gp AS 'GP', lstcheckup AS 'LastCheckup', nameofchild AS 'NameofChild' FROM pregnancyrec WHERE nameofparent LIKE '%" + jTextField11.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblPregrec.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  nameofchild AS 'NameofChild', sname AS 'Surname', mname AS 'MiddleName', nameofparent AS 'NameofParent', dateofbirth AS 'DateofBirth', hsno AS 'Household No.', fno AS 'Family No', purok AS 'Purok', gender AS 'Gender', birthweight AS 'BirthWeight', plcofbirth AS 'PlaceofBirth', registered AS 'Registered' FROM birthrec WHERE nameofchild LIKE '%" + jTextField15.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblBirthreg.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', medname AS 'Medicine Name', type AS 'Type', descrip AS 'Description', stocks AS 'Stocks' FROM medicine WHERE medname LIKE '%" + jTextField4.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblMedicine.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', medname AS 'Medicine Name', type AS 'Type', descrip AS 'Description', stocks AS 'Stocks' FROM medicine WHERE medname LIKE '%" + jTextField8.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblMedicine1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', medname AS 'Medicine Name', type AS 'Type', descrip AS 'Description', stocks AS 'Stocks' FROM medicine WHERE medname LIKE '%" + jTextField9.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblMedicine2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', medname AS 'Medicine Name', type AS 'Type', descrip AS 'Description', stocks AS 'Stocks' FROM medicine WHERE medname LIKE '%" + jTextField13.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblMedicine3.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', medname AS 'Medicine Name', type AS 'Type', descrip AS 'Description', quantity AS 'Quantity', dateissued AS 'Date Supplied', suppname AS 'Supplier Name', expirydate AS 'Expiry Date' FROM medrec WHERE medname LIKE '%" + jTextField24.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblMedicine4.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  id AS 'ID', resname AS 'Resident Name', age AS 'Age', concern AS 'Concern', quant AS 'Quantity', medname AS 'Medicine Name', daterel AS 'Date Released' FROM medrel WHERE resname LIKE '%" + jTextField25.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblMedicine5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT  medname AS 'Medicine Name', quant AS 'Quantity', medissue AS 'Medicine Issue', daterem AS 'Date Removed' FROM medrem WHERE medname LIKE '%" + jTextField26.getText() + "%'");
            ResultSet rs = pst.executeQuery();

            jtblMedicine6.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jcheckboxaccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcheckboxaccActionPerformed
        // TODO add your handling code here:
        if (jcheckboxacc.isSelected()) {
            password.setEchoChar((char) 0);
            jtxtnpass.setEchoChar((char) 0);
        } else {

            password.setEchoChar('\u25cf');
            jtxtnpass.setEchoChar('\u25cf');
        }
    }//GEN-LAST:event_jcheckboxaccActionPerformed

    private void jbtnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnupdateActionPerformed
        // TODO add your handling code here:

        // Update query
        String nusername = jtxtnusername.getText();
        String npass = jtxtnpass.getText();
        String qstn = jcmbqstn.getSelectedItem().toString();
        String ans = jtxtans.getText();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("select username from admin where username='" + usernameLogin.getText() + "' and password=?");

            //            pst.setString(1, jtxtusername.getText());
            pst.setString(1, password.getText());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username and Password Matched");

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
                pst = con.prepareStatement("UPDATE admin SET username=?,password=?,securityqstn=?,answer=?  WHERE username ='" + usernameAcc.getText() + "' AND password='" + password.getText() + "'");
                //            pst = con.prepareStatement(sql);
                pst.setString(1, nusername);
                pst.setString(2, npass);
                pst.setString(3, qstn);
                pst.setString(4, ans);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Account Updated");

                usernameAcc.setText(null);
                password.setText(null);
                jtxtnusername.setText(null);
                jcmbqstn.setSelectedIndex(-1);
                jtxtans.setText("");
                jtxtnpass.setText(null);

                //username.getText().equals("");
                //EMTPY TEXT BOXES ERROR!
            } else if (nusername.isEmpty() || npass.isEmpty() || qstn.isEmpty() || ans.isEmpty() || qstn.equals("----")) {
                JOptionPane.showMessageDialog(null, "Please fill the textboxes! ");
            } else {
                JOptionPane.showMessageDialog(null, "Old Username and Password donot Matched");
                usernameAcc.setText("");
                password.setText("");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jbtnupdateActionPerformed

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("SELECT * FROM admin WHERE username ='" + usernameLogin.getText() + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                usernameAcc.setText(username);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_passwordFocusGained

    private void jtxtContnum1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtContnum1KeyTyped
        // TODO add your handling code here:
        char iNumber = evt.getKeyChar();
        if (!(Character.isDigit(iNumber))
                || (iNumber == KeyEvent.VK_BACK_SPACE)
                || (iNumber == KeyEvent.VK_DELETE)) {
            evt.consume();
        }

        if (jtxtContnum1.getText().length() >= 11) // limit textfield to 3 characters
        {
            evt.consume();
        }
    }//GEN-LAST:event_jtxtContnum1KeyTyped

    private void jtxtMobilenumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtMobilenumKeyTyped
        // TODO add your handling code here:
        char iNumber = evt.getKeyChar();
        if (!(Character.isDigit(iNumber))
                || (iNumber == KeyEvent.VK_BACK_SPACE)
                || (iNumber == KeyEvent.VK_DELETE)) {
            evt.consume();
        }

        if (jtxtMobilenum.getText().length() >= 11) // limit textfield to 3 characters
        {
            evt.consume();
        }
    }//GEN-LAST:event_jtxtMobilenumKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        this.hide();
        login.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnSaveaddresident3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveaddresident3ActionPerformed
        // TODO add your handling code here:
        String m = JOptionPane.showInputDialog(null, "Concern:", "Info Resident", JOptionPane.INFORMATION_MESSAGE);
        issue2.setText(m);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcenter", "root", "");
            pst = con.prepareStatement("INSERT INTO logs (sname,fname,mname,age,contactnum,concern,date) VALUES (?,?,?,?,?,?,?)");
            pst.setString(1, jtxtsname1.getText());
            pst.setString(2, jtxtfname1.getText());
            pst.setString(3, jtxtmname1.getText());
            pst.setString(4, jtxtAge1.getText());
            pst.setString(5, jtxtContactno.getText());
            pst.setString(6, issue2.getText());
            pst.setString(7, jtxtDateup.getText());
            pst.executeUpdate();
            logs();
            JOptionPane.showMessageDialog(null, "Successfully Updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Select Resident/Fill up the concern!");
        }
    }//GEN-LAST:event_jbtnSaveaddresident3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel issue;
    private javax.swing.JLabel issue1;
    private javax.swing.JLabel issue2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooserbirth;
    private com.toedter.calendar.JDateChooser jDateChooserbirth1;
    private com.toedter.calendar.JDateChooser jDateLMP;
    private com.toedter.calendar.JDateChooser jDateofbirth;
    private com.toedter.calendar.JDateChooser jDaterel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel255;
    private javax.swing.JLabel jLabel256;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel266;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
    private javax.swing.JLabel jLabel273;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton jbtnAddmdcn;
    private javax.swing.JButton jbtnAddres;
    private javax.swing.JButton jbtnBirthReg;
    private javax.swing.JButton jbtnMedrecordclear;
    private javax.swing.JButton jbtnMedsave;
    private javax.swing.JButton jbtnMedsave1;
    private javax.swing.JButton jbtnRemovemed;
    private javax.swing.JButton jbtnSaveaddresident;
    private javax.swing.JButton jbtnSaveaddresident1;
    private javax.swing.JButton jbtnSaveaddresident2;
    private javax.swing.JButton jbtnSaveaddresident3;
    private javax.swing.JButton jbtnUpdatedmed;
    private javax.swing.JButton jbtnpregclear;
    private javax.swing.JButton jbtnsearchaddres;
    private javax.swing.JButton jbtnsearchaddres1;
    public static javax.swing.JButton jbtnupdate;
    private javax.swing.JCheckBox jcheckboxacc;
    private javax.swing.JComboBox<String> jcmb4ps;
    private javax.swing.JComboBox<String> jcmb4ps1;
    private javax.swing.JComboBox<String> jcmbPurok;
    private javax.swing.JComboBox<String> jcmbPurok1;
    private javax.swing.JComboBox<String> jcmbPurok2;
    private javax.swing.JComboBox<String> jcmbPurok3;
    private javax.swing.JComboBox<String> jcmbPurok5;
    private javax.swing.JComboBox<String> jcmbPurokpreg;
    private javax.swing.JComboBox<String> jcmbdiastolic;
    private javax.swing.JComboBox<String> jcmbgender;
    private javax.swing.JComboBox<String> jcmbgender1;
    private javax.swing.JComboBox<String> jcmbqstn;
    private javax.swing.JComboBox<String> jcmbsystolic;
    private com.toedter.calendar.JDateChooser jdateDlvrd;
    private com.toedter.calendar.JDateChooser jdateLstchckup;
    private javax.swing.JTable jtblBirthreg;
    private javax.swing.JTable jtblBp;
    private javax.swing.JTable jtblBp1;
    private javax.swing.JTable jtblMedicine;
    private javax.swing.JTable jtblMedicine1;
    private javax.swing.JTable jtblMedicine2;
    private javax.swing.JTable jtblMedicine3;
    private javax.swing.JTable jtblMedicine4;
    private javax.swing.JTable jtblMedicine5;
    private javax.swing.JTable jtblMedicine6;
    private javax.swing.JTable jtblPregrec;
    private javax.swing.JTable jtbladdresident;
    private javax.swing.JTable jtbladdresident1;
    private javax.swing.JTable jtblforpsmem;
    private javax.swing.JTable jtblviewresident;
    private javax.swing.JTextField jtxCtzn;
    private javax.swing.JTextField jtxCtzn1;
    private javax.swing.JLabel jtxtAge;
    private javax.swing.JLabel jtxtAge1;
    private javax.swing.JLabel jtxtAge2;
    private javax.swing.JLabel jtxtAge3;
    private javax.swing.JLabel jtxtAge5;
    private javax.swing.JLabel jtxtAgepreg;
    private javax.swing.JTextField jtxtAgerel;
    private javax.swing.JLabel jtxtAgerelmed;
    private javax.swing.JTextField jtxtBP;
    private javax.swing.JTextField jtxtBP1;
    private javax.swing.JLabel jtxtCivilstat;
    private javax.swing.JLabel jtxtCivilstat1;
    private javax.swing.JLabel jtxtCivilstat2;
    private javax.swing.JTextField jtxtConcern;
    private javax.swing.JTextField jtxtContactno;
    private javax.swing.JLabel jtxtContactnum1;
    private javax.swing.JLabel jtxtContnum;
    private javax.swing.JTextField jtxtContnum1;
    private javax.swing.JLabel jtxtContnum2;
    private javax.swing.JLabel jtxtCtzn;
    private javax.swing.JLabel jtxtCtzn1;
    private javax.swing.JLabel jtxtCtzn2;
    private javax.swing.JTextField jtxtDateadd;
    private javax.swing.JLabel jtxtDateadded;
    private javax.swing.JLabel jtxtDateadded1;
    private javax.swing.JLabel jtxtDateadded2;
    private javax.swing.JLabel jtxtDateadded3;
    private javax.swing.JLabel jtxtDateadded4;
    private com.toedter.calendar.JDateChooser jtxtDateexp;
    private javax.swing.JLabel jtxtDateiofbirth;
    private javax.swing.JLabel jtxtDateiofbirth1;
    private javax.swing.JLabel jtxtDateiofbirth2;
    private javax.swing.JLabel jtxtDateissued;
    private javax.swing.JLabel jtxtDaterelease;
    private javax.swing.JLabel jtxtDateremoved;
    private javax.swing.JLabel jtxtDateup;
    private com.toedter.calendar.JDateChooser jtxtDatrecvd;
    private javax.swing.JTextField jtxtDesc;
    private javax.swing.JTextField jtxtDesc1;
    private javax.swing.JLabel jtxtDescmed;
    private javax.swing.JLabel jtxtDescrem;
    private javax.swing.JLabel jtxtDescview;
    private javax.swing.JTextField jtxtEDC;
    private javax.swing.JLabel jtxtEducattmnt;
    private javax.swing.JLabel jtxtEducattmnt1;
    private javax.swing.JLabel jtxtEducattmnt2;
    private javax.swing.JTextField jtxtEmailaddeditres;
    private javax.swing.JTextField jtxtEmailaddress;
    private javax.swing.JLabel jtxtEmailaddviewres;
    private javax.swing.JLabel jtxtEmailaddviewres1;
    private javax.swing.JLabel jtxtExpdate;
    private javax.swing.JTextField jtxtFamilyno;
    private javax.swing.JLabel jtxtFemale;
    private javax.swing.JLabel jtxtFemale1;
    private javax.swing.JLabel jtxtFemale2;
    private javax.swing.JLabel jtxtFemale3;
    private javax.swing.JLabel jtxtFemale4;
    private javax.swing.JLabel jtxtFemale5;
    private javax.swing.JLabel jtxtFemale6;
    private javax.swing.JLabel jtxtFname;
    private javax.swing.JLabel jtxtFname1;
    private javax.swing.JLabel jtxtFname2;
    private javax.swing.JTextField jtxtFno;
    private javax.swing.JTextField jtxtFno1;
    private javax.swing.JLabel jtxtFno2;
    private javax.swing.JLabel jtxtFno3;
    private javax.swing.JLabel jtxtFno5;
    private javax.swing.JTextField jtxtFnobirthreg;
    private javax.swing.JLabel jtxtGender;
    private javax.swing.JLabel jtxtGender1;
    private javax.swing.JLabel jtxtGender2;
    private javax.swing.JTextField jtxtGp;
    private javax.swing.JTextField jtxtHno;
    private javax.swing.JTextField jtxtHno1;
    private javax.swing.JLabel jtxtHno2;
    private javax.swing.JLabel jtxtHno3;
    private javax.swing.JLabel jtxtHno5;
    private javax.swing.JTextField jtxtHouseno;
    private javax.swing.JLabel jtxtHse;
    private javax.swing.JLabel jtxtHse1;
    private javax.swing.JLabel jtxtHse2;
    private javax.swing.JLabel jtxtHse3;
    private javax.swing.JLabel jtxtHse4;
    private javax.swing.JLabel jtxtHse5;
    private javax.swing.JLabel jtxtHse6;
    private javax.swing.JTextField jtxtHsnobirthreg;
    private javax.swing.JLabel jtxtMale;
    private javax.swing.JLabel jtxtMale1;
    private javax.swing.JLabel jtxtMale2;
    private javax.swing.JLabel jtxtMale3;
    private javax.swing.JLabel jtxtMale4;
    private javax.swing.JLabel jtxtMale5;
    private javax.swing.JLabel jtxtMale6;
    private javax.swing.JLabel jtxtMedID;
    private javax.swing.JLabel jtxtMedStocksrel;
    private javax.swing.JLabel jtxtMedconcern;
    private javax.swing.JTextField jtxtMedissue;
    private javax.swing.JLabel jtxtMedissuerem;
    private javax.swing.JTextField jtxtMedname;
    private javax.swing.JLabel jtxtMedname1;
    private javax.swing.JLabel jtxtMednamerel;
    private javax.swing.JLabel jtxtMednamerelease;
    private javax.swing.JLabel jtxtMednamerem;
    private javax.swing.JLabel jtxtMedquant;
    private javax.swing.JLabel jtxtMname;
    private javax.swing.JLabel jtxtMname1;
    private javax.swing.JLabel jtxtMname2;
    private javax.swing.JTextField jtxtMobilenum;
    private javax.swing.JLabel jtxtNameofChildreg;
    private javax.swing.JTextField jtxtNameofchild;
    private javax.swing.JTextField jtxtNameofparent;
    private javax.swing.JLabel jtxtNameofparentreg;
    private javax.swing.JLabel jtxtOccptn;
    private javax.swing.JLabel jtxtOccptn1;
    private javax.swing.JLabel jtxtOccptn2;
    private javax.swing.JTextField jtxtOcptn;
    private javax.swing.JTextField jtxtOcptn1;
    private javax.swing.JTextField jtxtPih;
    private javax.swing.JLabel jtxtPlaceofBirth;
    private javax.swing.JLabel jtxtPlaceofBirth1;
    private javax.swing.JLabel jtxtPlaceofBirth2;
    private javax.swing.JLabel jtxtPurok;
    private javax.swing.JLabel jtxtPurok1;
    private javax.swing.JLabel jtxtPurok2;
    private javax.swing.JTextField jtxtQuant;
    private javax.swing.JTextField jtxtQuantrel;
    private javax.swing.JTextField jtxtQuantrem;
    private javax.swing.JLabel jtxtQuantremove;
    private javax.swing.JLabel jtxtRemovemdecine;
    private javax.swing.JTextField jtxtResname;
    private javax.swing.JLabel jtxtResnamemed;
    private javax.swing.JLabel jtxtRgln;
    private javax.swing.JLabel jtxtRgln1;
    private javax.swing.JLabel jtxtRgln2;
    private javax.swing.JComboBox<String> jtxtRlgn;
    private javax.swing.JComboBox<String> jtxtRlgn1;
    private javax.swing.JTextField jtxtSearchaddres;
    private javax.swing.JTextField jtxtSearchaddres1;
    private javax.swing.JLabel jtxtSname1;
    private javax.swing.JLabel jtxtSname2;
    private javax.swing.JLabel jtxtSname3;
    private javax.swing.JLabel jtxtSnrctzn;
    private javax.swing.JLabel jtxtSnrctzn1;
    private javax.swing.JLabel jtxtSnrctzn2;
    private javax.swing.JLabel jtxtSnrctzn3;
    private javax.swing.JLabel jtxtSnrctzn4;
    private javax.swing.JLabel jtxtSnrctzn5;
    private javax.swing.JLabel jtxtSnrctzn6;
    private javax.swing.JLabel jtxtStocks;
    private javax.swing.JLabel jtxtStocksrem;
    private javax.swing.JLabel jtxtSuppName;
    private javax.swing.JTextField jtxtType;
    private javax.swing.JLabel jtxtType1;
    private javax.swing.JLabel jtxtTyperem;
    private javax.swing.JLabel jtxtTypeview;
    private javax.swing.JTextField jtxtage;
    private javax.swing.JTextField jtxtans;
    private javax.swing.JTable jtxtbeditresident;
    private javax.swing.JComboBox<String> jtxtcivilstatus;
    private javax.swing.JComboBox<String> jtxtcivilstatus1;
    private javax.swing.JComboBox<String> jtxteducationalattmnt;
    private javax.swing.JComboBox<String> jtxteducationalattmnt1;
    private javax.swing.JTextField jtxtfname;
    private javax.swing.JTextField jtxtfname1;
    private javax.swing.JLabel jtxtfno;
    private javax.swing.JLabel jtxtfno1;
    private javax.swing.JLabel jtxtfno2;
    private javax.swing.JLabel jtxtfno3;
    private javax.swing.JLabel jtxtfno4;
    private javax.swing.JLabel jtxtfno5;
    private javax.swing.JLabel jtxtfno6;
    private javax.swing.JLabel jtxtforpsmem;
    private javax.swing.JLabel jtxtforpsmem1;
    private javax.swing.JTextField jtxtmname;
    private javax.swing.JTextField jtxtmname1;
    private javax.swing.JPasswordField jtxtnpass;
    private javax.swing.JTextField jtxtnusername;
    private javax.swing.JTextField jtxtplcbirth;
    private javax.swing.JTextField jtxtplcbirth1;
    private javax.swing.JLabel jtxtpltn;
    private javax.swing.JLabel jtxtpltn1;
    private javax.swing.JLabel jtxtpltn2;
    private javax.swing.JLabel jtxtpltn3;
    private javax.swing.JLabel jtxtpltn4;
    private javax.swing.JLabel jtxtpltn5;
    private javax.swing.JLabel jtxtpltn6;
    private javax.swing.JTextField jtxtrContactnum;
    private javax.swing.JLabel jtxtrelwfamhead;
    private javax.swing.JLabel jtxtrelwfamhead1;
    private javax.swing.JLabel jtxtrelwfamhead2;
    private javax.swing.JTextField jtxtresname;
    private javax.swing.JTextField jtxtrwfamilyhead;
    private javax.swing.JTextField jtxtrwfamilyhead1;
    private com.toedter.calendar.JDateChooser jtxtrwfamilyhead2;
    private javax.swing.JTextField jtxtsname;
    private javax.swing.JTextField jtxtsname1;
    private javax.swing.JLabel jxtMednameview;
    private javax.swing.JLabel jxtQuanttview;
    private javax.swing.JPasswordField password;
    public static javax.swing.JTextField usernameAcc;
    // End of variables declaration//GEN-END:variables
}
