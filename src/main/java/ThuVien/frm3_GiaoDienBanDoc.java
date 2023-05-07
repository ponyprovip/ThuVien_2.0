package ThuVien;

import Bang.KetNoi;
import Bang.Sach;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class frm3_GiaoDienBanDoc extends javax.swing.JFrame {

    public frm3_GiaoDienBanDoc() {
        initComponents();
        show_Sach();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public KetNoi kn = new KetNoi();
    private List<Sach> Sach;
    
    public ArrayList<Sach> sachList(){
        ArrayList<Sach> sachList = new ArrayList<>();
        try {
        Connection con = kn.getConnection();
        String query1 = "SELECT * FROM Sach";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        Sach sach;
        while(rs.next()){
            sach = new Sach(rs.getString("Masach"), rs.getString("Tensach"),rs.getString("Tacgia"), rs.getString("Theloai"), rs.getString("Khoa"), rs.getInt("Soluong"));
            sachList.add(sach);
        }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        return sachList;
    }
    
    public void show_Sach(){ //Hàm hiển thị bảng từ SQL ra màn hình
        ArrayList<Sach> list = sachList();
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = tblSach.getRowCount() + 1;
            row[1] = list.get(i).getMasach();
            row[2] = list.get(i).getTensach();
            row[3] = list.get(i).getTacgia();
            row[4] = list.get(i).getTheloai();
            row[5] = list.get(i).getKhoa();
            row[6] = list.get(i).getSoluong();
            model.addRow(row);
        }
    }
    
    public void TimkiemS(String str){
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblSach.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnTimkiem = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        x1 = new javax.swing.JPanel();
        btnDoimatkhau = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        x2 = new javax.swing.JPanel();
        btnDangxuat = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        x3 = new javax.swing.JPanel();
        btnThoatPM = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        x4 = new javax.swing.JPanel();
        panelRound1 = new Customizing.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        txtSearchS = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Menu.setBackground(new java.awt.Color(36, 45, 54));
        Menu.setPreferredSize(new java.awt.Dimension(260, 630));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("QUẢN LÝ THƯ VIỆN");
        Menu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        btnTimkiem.setBackground(new java.awt.Color(29, 38, 40));
        btnTimkiem.setPreferredSize(new java.awt.Dimension(260, 55));
        btnTimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTimkiemMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tìm kiếm");

        x1.setBackground(new java.awt.Color(255, 255, 255));
        x1.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x1Layout = new javax.swing.GroupLayout(x1);
        x1.setLayout(x1Layout);
        x1Layout.setHorizontalGroup(
            x1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x1Layout.setVerticalGroup(
            x1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnTimkiemLayout = new javax.swing.GroupLayout(btnTimkiem);
        btnTimkiem.setLayout(btnTimkiemLayout);
        btnTimkiemLayout.setHorizontalGroup(
            btnTimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTimkiemLayout.createSequentialGroup()
                .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(0, 163, Short.MAX_VALUE))
        );
        btnTimkiemLayout.setVerticalGroup(
            btnTimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTimkiemLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnTimkiemLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        btnDoimatkhau.setBackground(new java.awt.Color(36, 45, 54));
        btnDoimatkhau.setPreferredSize(new java.awt.Dimension(260, 55));
        btnDoimatkhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDoimatkhauMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Đổi mật khẩu");

        x2.setBackground(new java.awt.Color(255, 255, 255));
        x2.setOpaque(false);
        x2.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x2Layout = new javax.swing.GroupLayout(x2);
        x2.setLayout(x2Layout);
        x2Layout.setHorizontalGroup(
            x2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x2Layout.setVerticalGroup(
            x2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnDoimatkhauLayout = new javax.swing.GroupLayout(btnDoimatkhau);
        btnDoimatkhau.setLayout(btnDoimatkhauLayout);
        btnDoimatkhauLayout.setHorizontalGroup(
            btnDoimatkhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDoimatkhauLayout.createSequentialGroup()
                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(0, 133, Short.MAX_VALUE))
        );
        btnDoimatkhauLayout.setVerticalGroup(
            btnDoimatkhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDoimatkhauLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDoimatkhauLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnDoimatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        btnDangxuat.setBackground(new java.awt.Color(36, 45, 54));
        btnDangxuat.setPreferredSize(new java.awt.Dimension(260, 55));
        btnDangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDangxuatMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Đăng xuất");

        x3.setBackground(new java.awt.Color(255, 255, 255));
        x3.setOpaque(false);
        x3.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x3Layout = new javax.swing.GroupLayout(x3);
        x3.setLayout(x3Layout);
        x3Layout.setHorizontalGroup(
            x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x3Layout.setVerticalGroup(
            x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnDangxuatLayout = new javax.swing.GroupLayout(btnDangxuat);
        btnDangxuat.setLayout(btnDangxuatLayout);
        btnDangxuatLayout.setHorizontalGroup(
            btnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangxuatLayout.createSequentialGroup()
                .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(0, 156, Short.MAX_VALUE))
        );
        btnDangxuatLayout.setVerticalGroup(
            btnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangxuatLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDangxuatLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnDangxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

        btnThoatPM.setBackground(new java.awt.Color(36, 45, 54));
        btnThoatPM.setPreferredSize(new java.awt.Dimension(260, 55));
        btnThoatPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThoatPMMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Thoát phần mềm");

        x4.setBackground(new java.awt.Color(255, 255, 255));
        x4.setOpaque(false);
        x4.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x4Layout = new javax.swing.GroupLayout(x4);
        x4.setLayout(x4Layout);
        x4Layout.setHorizontalGroup(
            x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x4Layout.setVerticalGroup(
            x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnThoatPMLayout = new javax.swing.GroupLayout(btnThoatPM);
        btnThoatPM.setLayout(btnThoatPMLayout);
        btnThoatPMLayout.setHorizontalGroup(
            btnThoatPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThoatPMLayout.createSequentialGroup()
                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(0, 106, Short.MAX_VALUE))
        );
        btnThoatPMLayout.setVerticalGroup(
            btnThoatPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThoatPMLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnThoatPMLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnThoatPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Khoa", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setFocusable(false);
        tblSach.setGridColor(new java.awt.Color(204, 204, 204));
        tblSach.setRowHeight(22);
        tblSach.setShowGrid(false);
        jScrollPane1.setViewportView(tblSach);

        txtSearchS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tìm kiếm");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(675, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtSearchS, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound1Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(388, Short.MAX_VALUE))
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                    .addContainerGap(44, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimkiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimkiemMousePressed
        // TODO add your handling code here:
        setColor(btnTimkiem);
        x1.setOpaque(true);
        resetColor(new JPanel[]{btnDoimatkhau,btnDangxuat,btnThoatPM}, new JPanel[]{x2,x3,x4});
    }//GEN-LAST:event_btnTimkiemMousePressed

    private void btnDoimatkhauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoimatkhauMousePressed
        // TODO add your handling code here:
        setColor(btnDoimatkhau);
        x2.setOpaque(true);
        resetColor(new JPanel[]{btnTimkiem,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x3,x4});
        frm4_DoiMK f = new frm4_DoiMK();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_btnDoimatkhauMousePressed

    private void btnDangxuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangxuatMousePressed
        // TODO add your handling code here:
        setColor(btnDangxuat);
        x3.setOpaque(true);
        resetColor(new JPanel[]{btnTimkiem,btnDoimatkhau,btnThoatPM}, new JPanel[]{x1,x2,x4});
        JFrame frame = new JFrame("Đăng xuất");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn đăng xuất không?","Đăng xuất",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
    {
        frm1_GiaoDienDN_DK fr = new frm1_GiaoDienDN_DK();
        fr.setVisible(true);
        this.setVisible(false);
        }
    }//GEN-LAST:event_btnDangxuatMousePressed

    private void btnThoatPMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatPMMousePressed
        // TODO add your handling code here:
        setColor(btnThoatPM);
        x4.setOpaque(true);
        resetColor(new JPanel[]{btnTimkiem,btnDoimatkhau,btnDangxuat}, new JPanel[]{x1,x2,x3});
        JFrame frame = new JFrame("Thoát");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn thoát phần mềm không?","Thoát",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
    {
        System.exit(0);
        }
    }//GEN-LAST:event_btnThoatPMMousePressed

    private void txtSearchSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSKeyReleased
        // TODO add your handling code here:
        String searchString = txtSearchS.getText();
        TimkiemS(searchString);
    }//GEN-LAST:event_txtSearchSKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            
            UIManager.put("ScrollPane.border", new Color(255,255,255));
            UIManager.put("TextComponent.arc", 15);
            UIManager.put( "Component.focusWidth", 0);
            UIManager.put("Button.arc", 15);
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm3_GiaoDienBanDoc().setVisible(true);
            }
        });
    }
    
    private void setColor(JPanel pane){
       pane.setBackground(new Color(29,38,40));
   }
   
   private void resetColor(JPanel[] pane , JPanel[] x){
       for (int i = 0; i < pane.length; i++) {
           pane[i].setBackground(new Color(36,46,54));
           
       }
       for (int i = 0; i < pane.length; i++) {
           x[i].setOpaque(false);
           
       }
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel btnDangxuat;
    private javax.swing.JPanel btnDoimatkhau;
    private javax.swing.JPanel btnThoatPM;
    private javax.swing.JPanel btnTimkiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Customizing.PanelRound panelRound1;
    private javax.swing.JTable tblSach;
    private javax.swing.JTextField txtSearchS;
    private javax.swing.JPanel x1;
    private javax.swing.JPanel x2;
    private javax.swing.JPanel x3;
    private javax.swing.JPanel x4;
    // End of variables declaration//GEN-END:variables
}
