package ThuVien;

import Bang.KetNoi;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DIENMAYXANH
 */
public class frm1_GiaoDienDN_DK extends javax.swing.JFrame {

    public frm1_GiaoDienDN_DK() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBgr(); setBgr1(); setBgr2();
    }
    
    public KetNoi kn = new KetNoi();
    
    public void setBgr(){
        ImageIcon icon = new ImageIcon("E:\\OneDrive\\Documents\\NetBeansProjects\\ThuVien\\src\\main\\java\\Background\\bgr.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(bgr.getWidth(), bgr.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bgr.setIcon(scaledIcon);
    }
    
    public void setBgr1(){
        ImageIcon icon = new ImageIcon("E:\\OneDrive\\Documents\\NetBeansProjects\\ThuVien\\src\\main\\java\\Background\\bgr.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(bgr1.getWidth(), bgr1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bgr1.setIcon(scaledIcon);
    }
    
    public void setBgr2(){
        ImageIcon icon = new ImageIcon("E:\\OneDrive\\Documents\\NetBeansProjects\\ThuVien\\src\\main\\java\\Background\\bgr.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(bgr2.getWidth(), bgr2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bgr2.setIcon(scaledIcon);
    }
    
    public String regexEmail = "^[a-z0-9]+[@]{1}+[a-z]+[.]{1}+[a-z]+|[a-z0-9]+[@]{1}+[a-z]+[.]{1}+[a-z]+[.]{1}+[a-z]+$";
    public String regexSDT = "^0\\d{9}$";
    public String regexUsername = "^[a-zA-z0-9]+$";
    public String regexPassword = "^[a-zA-z0-9]+$";

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        Menu = new javax.swing.JTabbedPane();
        Home_Page = new javax.swing.JPanel();
        panelRound1 = new Customizing.PanelRound();
        btnDangnhapHP = new javax.swing.JButton();
        btnDangkyHP = new javax.swing.JButton();
        btnThoatHP = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bgr = new javax.swing.JLabel();
        Dang_Nhap = new javax.swing.JPanel();
        panelRound2 = new Customizing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        btnBackDN = new javax.swing.JButton();
        btnDangnhap = new javax.swing.JButton();
        chkShowDN = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        txtPasswordDN = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsernameDN = new javax.swing.JTextField();
        cbType = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        bgr1 = new javax.swing.JLabel();
        Dang_Ky = new javax.swing.JPanel();
        panelRound4 = new Customizing.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        btnBackDK = new javax.swing.JButton();
        btnDangky = new javax.swing.JButton();
        chkShowDK = new javax.swing.JCheckBox();
        txtGmailDK = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSDTDK = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUsernameDK = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPasswordDK = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        txtConfirmDK = new javax.swing.JPasswordField();
        lbrLoiSDT = new javax.swing.JLabel();
        lbrLoiEmail = new javax.swing.JLabel();
        lbrLoiUsername = new javax.swing.JLabel();
        lbrLoiPassword = new javax.swing.JLabel();
        lbrLoiConfirm = new javax.swing.JLabel();
        bgr2 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1281, 700));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Home_Page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(240,240,240,200));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        btnDangnhapHP.setBackground(new java.awt.Color(240, 240, 240));
        btnDangnhapHP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDangnhapHP.setText("Đăng nhập");
        btnDangnhapHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangnhapHPActionPerformed(evt);
            }
        });

        btnDangkyHP.setBackground(new java.awt.Color(240, 240, 240));
        btnDangkyHP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDangkyHP.setText("Đăng ký");
        btnDangkyHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangkyHPActionPerformed(evt);
            }
        });

        btnThoatHP.setBackground(new java.awt.Color(240, 240, 240));
        btnThoatHP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThoatHP.setText("Thoát");
        btnThoatHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatHPActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("THƯ VIỆN ICTU");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel1)
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnThoatHP, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(btnDangnhapHP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(btnDangkyHP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addGap(100, 100, 100)
                .addComponent(btnDangnhapHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnDangkyHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnThoatHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        Home_Page.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 410, 510));
        Home_Page.add(bgr, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1281, 700));

        Menu.addTab("tab1", Home_Page);

        Dang_Nhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new java.awt.Color(240,240,240,200));
        panelRound2.setPreferredSize(new java.awt.Dimension(410, 507));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("ĐĂNG NHẬP");

        btnBackDN.setBackground(new java.awt.Color(240, 240, 240));
        btnBackDN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBackDN.setText("Thoát");
        btnBackDN.setPreferredSize(new java.awt.Dimension(72, 31));
        btnBackDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackDNActionPerformed(evt);
            }
        });

        btnDangnhap.setBackground(new java.awt.Color(240, 240, 240));
        btnDangnhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangnhap.setText("Đăng nhập");
        btnDangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangnhapActionPerformed(evt);
            }
        });

        chkShowDN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkShowDN.setText("Show");
        chkShowDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowDNActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("Bạn chưa có tài khoản?");
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtPasswordDN.setBackground(new java.awt.Color(240, 240, 240));
        txtPasswordDN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Password");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Bạn là?");

        txtUsernameDN.setBackground(new java.awt.Color(240, 240, 240));
        txtUsernameDN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbType.setBackground(new java.awt.Color(240, 240, 240));
        cbType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thủ thư", "Bạn đọc" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Username");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPasswordDN, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(chkShowDN, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                                    .addComponent(btnBackDN, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(42, 42, 42)
                                    .addComponent(btnDangnhap))
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(txtUsernameDN)
                                .addComponent(cbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addGap(85, 85, 85))))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addGap(61, 61, 61)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsernameDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPasswordDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(chkShowDN)
                .addGap(54, 54, 54)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBackDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Dang_Nhap.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 410, 510));
        Dang_Nhap.add(bgr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1281, 700));

        Menu.addTab("tab2", Dang_Nhap);

        Dang_Ky.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound4.setBackground(new java.awt.Color(240,240,240,200));
        panelRound4.setPreferredSize(new java.awt.Dimension(410, 507));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("ĐĂNG KÝ");

        btnBackDK.setBackground(new java.awt.Color(240, 240, 240));
        btnBackDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBackDK.setText("Thoát");
        btnBackDK.setPreferredSize(new java.awt.Dimension(72, 31));
        btnBackDK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackDKActionPerformed(evt);
            }
        });

        btnDangky.setBackground(new java.awt.Color(240, 240, 240));
        btnDangky.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangky.setText("Đăng ký");
        btnDangky.setPreferredSize(new java.awt.Dimension(72, 27));
        btnDangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangkyActionPerformed(evt);
            }
        });

        chkShowDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkShowDK.setText("Show");
        chkShowDK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowDKActionPerformed(evt);
            }
        });

        txtGmailDK.setBackground(new java.awt.Color(240, 240, 240));
        txtGmailDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGmailDK.setToolTipText("");
        txtGmailDK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGmailDKKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Số điện thoại");

        txtSDTDK.setBackground(new java.awt.Color(240, 240, 240));
        txtSDTDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDTDK.setToolTipText("Bao gồm 10 chữ số và bắt đầu bằng số 0");
        txtSDTDK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTDKKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Username");

        txtUsernameDK.setBackground(new java.awt.Color(240, 240, 240));
        txtUsernameDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsernameDK.setToolTipText("Bao gồm chữ cái và số viết liền không dấu");
        txtUsernameDK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameDKKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Password");

        txtPasswordDK.setBackground(new java.awt.Color(240, 240, 240));
        txtPasswordDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPasswordDK.setToolTipText("Bao gồm chữ và số viết liền không dấu");
        txtPasswordDK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordDKKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Confirm");

        txtConfirmDK.setBackground(new java.awt.Color(240, 240, 240));
        txtConfirmDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConfirmDK.setToolTipText("Bao gồm chữ và số viết liền không dấu");
        txtConfirmDK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmDKKeyReleased(evt);
            }
        });

        lbrLoiSDT.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiSDT.setForeground(new java.awt.Color(255, 0, 0));
        lbrLoiSDT.setText(" ");
        lbrLoiSDT.setBorder(new javax.swing.border.MatteBorder(null));

        lbrLoiEmail.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiEmail.setForeground(new java.awt.Color(255, 0, 0));
        lbrLoiEmail.setText(" ");
        lbrLoiEmail.setBorder(new javax.swing.border.MatteBorder(null));

        lbrLoiUsername.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiUsername.setForeground(new java.awt.Color(255, 0, 0));
        lbrLoiUsername.setText(" ");
        lbrLoiUsername.setBorder(new javax.swing.border.MatteBorder(null));

        lbrLoiPassword.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiPassword.setForeground(new java.awt.Color(255, 0, 0));
        lbrLoiPassword.setText(" ");
        lbrLoiPassword.setBorder(new javax.swing.border.MatteBorder(null));

        lbrLoiConfirm.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiConfirm.setForeground(new java.awt.Color(255, 0, 0));
        lbrLoiConfirm.setText(" ");
        lbrLoiConfirm.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                        .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound4Layout.createSequentialGroup()
                                .addComponent(btnBackDK, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDangky, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addGroup(panelRound4Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(135, 135, 135)
                                        .addComponent(chkShowDK))
                                    .addComponent(jLabel8)
                                    .addComponent(txtGmailDK)
                                    .addComponent(txtSDTDK)
                                    .addComponent(jLabel9)
                                    .addComponent(txtUsernameDK)
                                    .addComponent(jLabel10)
                                    .addComponent(txtPasswordDK)
                                    .addComponent(txtConfirmDK))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbrLoiSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrLoiEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrLoiUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrLoiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrLoiConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGmailDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsernameDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswordDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConfirmDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkShowDK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBackDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangky, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        Dang_Ky.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 410, 510));
        Dang_Ky.add(bgr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1281, 700));

        Menu.addTab("tab3", Dang_Ky);

        jPanel3.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1281, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangnhapHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangnhapHPActionPerformed
        // TODO add your handling code here:
         Menu.setSelectedIndex(1);
    }//GEN-LAST:event_btnDangnhapHPActionPerformed

    private void btnDangkyHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangkyHPActionPerformed
        // TODO add your handling code here:
         Menu.setSelectedIndex(2);
    }//GEN-LAST:event_btnDangkyHPActionPerformed

    private void btnThoatHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatHPActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame("Thoát");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn thoát không?","Thoát",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnThoatHPActionPerformed

    private void btnBackDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackDNActionPerformed
        // TODO add your handling code here:
        Menu.setSelectedIndex(0);
        cbType.setSelectedIndex(0);
        txtUsernameDN.setText("");
        txtPasswordDN.setText("");
    }//GEN-LAST:event_btnBackDNActionPerformed

    private void btnDangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangnhapActionPerformed
        // TODO add your handling code here:
        if(txtUsernameDN.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền Username");
            return;
        }else{
            if(txtPasswordDN.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Bạn chưa điền Password");
                return;
        }}
        try {
        Connection con = kn.getConnection();
        String sqlquery = "SELECT * FROM Taikhoan WHERE UserName=? AND uPassWord=? AND uType=?";
        PreparedStatement ps = con.prepareCall(sqlquery); //Thực thi câu truy vấn trong SQL
        ps.setString(1, txtUsernameDN.getText());
        ps.setString(2, txtPasswordDN.getText());
        ps.setString(3,cbType.getSelectedItem().toString());
        ResultSet rs = ps.executeQuery(); //Lấy dữ liệu từ SQL bằng con trỏ
        if(rs.next()){ //So sánh dữ liệu lấy ra từ SQL với dữ liệu người dùng nhập vào
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
            if(cbType.getSelectedIndex()==0){ //Nếu tư cách người dùng trùng với dòng "uType" trong bảng SQL thì chuyển qua giao diện tương ứng
                frm2_GiaoDienThuThu fr = new frm2_GiaoDienThuThu();
                fr.setVisible(true);
                this.setVisible(false);
            }else{
                frm3_GiaoDienBanDoc fr = new frm3_GiaoDienBanDoc();
                fr.setVisible(true);
                this.setVisible(false);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Sai Username hoặc Password");
            return;
        }     
        }catch (Exception e) {
            
        }
        txtUsernameDN.setText("");
        txtPasswordDN.setText("");
    }//GEN-LAST:event_btnDangnhapActionPerformed

    private void chkShowDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowDNActionPerformed
        // TODO add your handling code here:
        if (chkShowDN.isSelected()){
            txtPasswordDN.setEchoChar((char)0);
            
        }else{
            txtPasswordDN.setEchoChar('•');
        }
    }//GEN-LAST:event_chkShowDNActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Menu.setSelectedIndex(2);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void chkShowDKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowDKActionPerformed
        // TODO add your handling code here:
        if (chkShowDK.isSelected()){
            txtPasswordDK.setEchoChar((char)0);
            txtConfirmDK.setEchoChar((char)0);

        }else{
            txtPasswordDK.setEchoChar('•');
            txtConfirmDK.setEchoChar('•');
        }
    }//GEN-LAST:event_chkShowDKActionPerformed

    private void btnBackDKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackDKActionPerformed
        // TODO add your handling code here:
        Menu.setSelectedIndex(0);
    }//GEN-LAST:event_btnBackDKActionPerformed

    private void btnDangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangkyActionPerformed
        // TODO add your handling code here:
        if(txtGmailDK.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền Email");
            return;
        }else{
            if(txtSDTDK.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền Số điện thoại");
            return;
        }else{
            if(txtUsernameDK.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền Username");
            return;
        }else{
            if(txtPasswordDK.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền Password");
            return;
        }else{
            if(txtConfirmDK.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền Confirm password");
            return; 
        }else{
            if(!(Pattern.matches(regexEmail, txtGmailDK.getText()))){
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            return;
        }else{
            if(!(Pattern.matches(regexSDT, txtSDTDK.getText()))){
            JOptionPane.showMessageDialog(this, "SDT không đúng định dạng");
            return;
        }else{
            if(!(Pattern.matches(regexUsername, txtUsernameDK.getText()))){
            JOptionPane.showMessageDialog(this, "Username không đúng định dạng");
            return;
        }else{
            if(!(Pattern.matches(regexPassword, txtPasswordDK.getText()))){
            JOptionPane.showMessageDialog(this, "Username không đúng định dạng");
            return;  
        }else{
            if(!(txtPasswordDK.getText()).equals(txtConfirmDK.getText())){
            JOptionPane.showMessageDialog(this, "Password không khớp!");
            return;
                 }
                }    
               }
              }   
             }
            }
           }
          }
         }
        }
        try {
        Connection con = kn.getConnection();
        String sql = "insert into Taikhoan values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, txtGmailDK.getText());
        ps.setString(2, txtSDTDK.getText());
        ps.setString(3, txtUsernameDK.getText());
        ps.setString(4, txtPasswordDK.getText());
        ps.setString(5, txtConfirmDK.getText());
        ps.setString(6,"Bạn đọc");
            
            JFrame frame = new JFrame("Đăng ký");
                if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn đăng ký không?","Đăng ký",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
                JOptionPane.showMessageDialog(this, "Đăng ký thành công");
                ps.executeUpdate();//update du lieu len SQL
        }else{
                    return;
                }
        } catch (Exception e) {
        }
        
        Menu.setSelectedIndex(1);
        txtGmailDK.setText("");
        txtSDTDK.setText("");
        txtUsernameDK.setText("");
        txtPasswordDK.setText("");
        txtConfirmDK.setText("");
        txtUsernameDN.setText("");
        txtPasswordDN.setText("");
    }//GEN-LAST:event_btnDangkyActionPerformed

    private void txtGmailDKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGmailDKKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexEmail, txtGmailDK.getText()))){
            lbrLoiEmail.setText("!");
        }else{
            lbrLoiEmail.setText("");
        }
    }//GEN-LAST:event_txtGmailDKKeyReleased

    private void txtSDTDKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTDKKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexSDT, txtSDTDK.getText()))){
            lbrLoiSDT.setText("!");
        }else{
            lbrLoiSDT.setText("");
        }
    }//GEN-LAST:event_txtSDTDKKeyReleased

    private void txtUsernameDKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameDKKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexUsername, txtUsernameDK.getText()))){
            lbrLoiUsername.setText("!");
        }else{
            lbrLoiUsername.setText("");
        }
    }//GEN-LAST:event_txtUsernameDKKeyReleased

    private void txtPasswordDKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordDKKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexPassword, txtPasswordDK.getText()))){
            lbrLoiPassword.setText("!");
        }else{
            lbrLoiPassword.setText("");
        }
    }//GEN-LAST:event_txtPasswordDKKeyReleased

    private void txtConfirmDKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmDKKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexEmail, txtGmailDK.getText()))){
            lbrLoiConfirm.setText("!");
        }else{
            lbrLoiConfirm.setText("");
        }
        if(!(txtPasswordDK.getText().equals(txtConfirmDK.getText()))){
            lbrLoiConfirm.setText("!");
        }else{
            lbrLoiConfirm.setText("");
        }
    }//GEN-LAST:event_txtConfirmDKKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            
            UIManager.put("ScrollPane.border", new Color(255,255,255));
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("TextField.borderColor", new Color(255,0,0));
            UIManager.put( "Component.focusWidth", 0);
            UIManager.put("Button.arc", 15);
            //UIManager.put("Button.borderColor", new Color(177,177,177));
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm1_GiaoDienDN_DK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dang_Ky;
    private javax.swing.JPanel Dang_Nhap;
    private javax.swing.JPanel Home_Page;
    private javax.swing.JTabbedPane Menu;
    private javax.swing.JLabel bgr;
    private javax.swing.JLabel bgr1;
    private javax.swing.JLabel bgr2;
    private javax.swing.JButton btnBackDK;
    private javax.swing.JButton btnBackDN;
    private javax.swing.JButton btnDangky;
    private javax.swing.JButton btnDangkyHP;
    private javax.swing.JButton btnDangnhap;
    private javax.swing.JButton btnDangnhapHP;
    private javax.swing.JButton btnThoatHP;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JCheckBox chkShowDK;
    private javax.swing.JCheckBox chkShowDN;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbrLoiConfirm;
    private javax.swing.JLabel lbrLoiEmail;
    private javax.swing.JLabel lbrLoiPassword;
    private javax.swing.JLabel lbrLoiSDT;
    private javax.swing.JLabel lbrLoiUsername;
    private Customizing.PanelRound panelRound1;
    private Customizing.PanelRound panelRound2;
    private Customizing.PanelRound panelRound4;
    private javax.swing.JPasswordField txtConfirmDK;
    private javax.swing.JTextField txtGmailDK;
    private javax.swing.JPasswordField txtPasswordDK;
    private javax.swing.JPasswordField txtPasswordDN;
    private javax.swing.JTextField txtSDTDK;
    private javax.swing.JTextField txtUsernameDK;
    private javax.swing.JTextField txtUsernameDN;
    // End of variables declaration//GEN-END:variables
}
