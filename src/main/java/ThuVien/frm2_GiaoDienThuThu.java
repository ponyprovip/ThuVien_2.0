package ThuVien;

import Bang.BanDoc;
import Bang.KetNoi;
import Bang.MuonSach;
import Bang.Sach;
import Bang.TraSach;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author DIENMAYXANH
 */
public class frm2_GiaoDienThuThu extends javax.swing.JFrame {

    public frm2_GiaoDienThuThu() {
        initComponents();
        show_Sach();
        show_BDoc();
        show_DSMuon();
        show_DSTra();
        setBgr();
        lbNgayM.setVisible(false);
        txtNgayM_M.setVisible(false);
        lbNgayT.setVisible(false);
        txtNgayT_M.setVisible(false);
        J1.setVisible(false);
        txtNgayT.setVisible(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public KetNoi kn = new KetNoi();
    private List<Sach> Sach;
    private List<BanDoc> BanDoc;
    private List<MuonSach> MuonSach;
    private List<TraSach> TraSach;
    public String regexTensach = "^[a-zA-Z0-9_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$";
    public String regexTen = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$";
    public String regexMaSV = "^[D][T][C]\\d{13}|[d][t][c]\\d{13}$";
    public String regexLop = "^[A-Z]{3,4} [K]\\d{2}[A-Z]";
    public String regexEmail = "^[a-z0-9]+[@]{1}+[a-z]+[.]{1}+[a-z]+|[a-z0-9]+[@]{1}+[a-z]+[.]{1}+[a-z]+[.]{1}+[a-z]+$";
    public String regexSDT = "^0\\d{9}$";
    public String regexSoluong = "^[1-9]+$";
    
    public void setBgr(){
        ImageIcon icon = new ImageIcon("E:\\OneDrive\\Documents\\NetBeansProjects\\ThuVien\\src\\main\\java\\Background\\bgr1.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(bgr.getWidth(), bgr.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bgr.setIcon(scaledIcon);
    }
    
    public ArrayList<TraSach> trasachList(){
        ArrayList<TraSach> trasachList = new ArrayList<>();
        try {
        Connection con = kn.getConnection();
        String query1 = "SELECT * FROM TraS";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        TraSach tra;
        while(rs.next()){
            tra = new TraSach(rs.getString("MaPM"), rs.getString("MaBD"), rs.getString("TenBD"), rs.getString("Masach"), rs.getString("Tensach"), rs.getInt("Soluong"), rs.getDate("NgayM"), rs.getDate("NgayT"), rs.getDate("NgayT_TT"), rs.getString("TinhtrangS"), rs.getString("Ghichu"));
            trasachList.add(tra);
        }
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        return trasachList;
    }
    
    public void show_DSTra(){ //Hàm hiển thị bảng từ SQL ra màn hình
        ArrayList<TraSach> list = trasachList();
        DefaultTableModel model = (DefaultTableModel) tblTraS.getModel();
        Object[] row = new Object[11];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getMaPM();
            row[1] = list.get(i).getMaBD();
            row[2] = list.get(i).getTenBD();
            row[3] = list.get(i).getMasach();
            row[4] = list.get(i).getTensach();
            row[5] = list.get(i).getSoluong();
            row[6] = list.get(i).getNgayM();
            row[7] = list.get(i).getNgayT();
            row[8] = list.get(i).getNgayT_TT();
            row[9] = list.get(i).getTinhtrangS();
            row[10] = list.get(i).getGhichu();
            model.addRow(row);
        }
    }
    
    public ArrayList<MuonSach> muonsachList(){
        ArrayList<MuonSach> muonsachList = new ArrayList<>();
        try {
        Connection con = kn.getConnection();
        String query1 = "SELECT * FROM MuonS";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        MuonSach muon;
        while(rs.next()){
            muon = new MuonSach(rs.getString("MaPM"), rs.getString("Masach"), rs.getString("Tensach"), rs.getString("MaBD"), rs.getString("TenBD"), rs.getString("Lop"), rs.getDate("NgayM"), rs.getDate("NgayT"), rs.getInt("Soluong"), rs.getString("Ghichu"));
            muonsachList.add(muon);
        }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        return muonsachList;
    }
    
    public void show_DSMuon(){ //Hàm hiển thị bảng từ SQL ra màn hình
        ArrayList<MuonSach> list = muonsachList();
        DefaultTableModel model = (DefaultTableModel) tblMuonS.getModel();
        DefaultTableModel model1 = (DefaultTableModel) tblMuonS1.getModel();
        Object[] row = new Object[10];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getMaPM();
            row[1] = list.get(i).getMasach();
            row[2] = list.get(i).getTensach();
            row[3] = list.get(i).getMaBD();
            row[4] = list.get(i).getTenBD();
            row[5] = list.get(i).getLop();
            row[6] = list.get(i).getNgayM();
            row[7] = list.get(i).getNgayT();
            row[8] = list.get(i).getSoluong();
            row[9] = list.get(i).getGhichu();
            model.addRow(row);
            model1.addRow(row);
        }
    }
    
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
    
    public ArrayList<BanDoc> bdList(){
        ArrayList<BanDoc> bdList = new ArrayList<>();
        try {
        Connection con = kn.getConnection();
        String query1 = "SELECT * FROM TTBD";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query1);
        BanDoc bdoc;
        while(rs.next()){
            bdoc = new BanDoc(rs.getString("MaBD"), rs.getString("MaSV"), rs.getString("Lop"), rs.getString("Khoa"), rs.getString("Hoten"), rs.getString("Gioitinh"), rs.getDate("Ngaysinh"), rs.getString("SDT"), rs.getString("Gmail"), rs.getInt("Vipham"));
            bdList.add(bdoc);
        }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        return bdList;
        }
    
    public void show_BDoc(){
        //tblDoc.fixTable(jScrollPane2);
        ArrayList<BanDoc> list = bdList();
        DefaultTableModel model = (DefaultTableModel) tblBDoc.getModel();
        Object[] row = new Object[11];
        for (int i = 0; i < list.size(); i++) {
            row[0] = tblBDoc.getRowCount() + 1;
            row[1] = list.get(i).getMaBD();
            row[2] = list.get(i).getMaSV();
            row[3] = list.get(i).getLop();
            row[4] = list.get(i).getKhoa();
            row[5] = list.get(i).getHoten();
            row[6] = list.get(i).getGioitinh();
            row[7] = list.get(i).getNgaysinh();
            row[8] = list.get(i).getSDT();
            row[9] = list.get(i).getGmail();
            row[10] = list.get(i).getVipham();
            model.addRow(row);
           }
        }
    
    public void TimkiemBDoc(String str){
        DefaultTableModel model = (DefaultTableModel) tblBDoc.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblBDoc.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
    
    public void hienthiTTS_M(){
        String MaS = txtMasach_M.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from Sach where Masach = ? ");
        ps.setString(1, MaS);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbrLoiMS1.setText("");
                lbrMasach_M.setText(rs.getString("Masach"));
                lbrTensach_M.setText(rs.getString("Tensach"));
                lbrTacgia_M.setText(rs.getString("Tacgia"));
                lbrSoluong_M.setText(rs.getString("Soluong"));
            }else{
                lbrLoiMS1.setText("!");
                lbrMasach_M.setText("");
                lbrTensach_M.setText("");
                lbrTacgia_M.setText("");
                lbrSoluong_M.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    public void hienthiTTBD_M(){
        String MaBD = txtMaBD_M.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from TTBD where MaBD = ? ");
        ps.setString(1, MaBD);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbrLoiBD1.setText("");
                lbrMaBD_M.setText(rs.getString("MaBD"));
                lbrTenBD_M.setText(rs.getString("Hoten"));
                lbrLop_M.setText(rs.getString("Lop"));
                lbrSDT_M.setText(rs.getString("SDT"));
                lbrVP_M.setText(rs.getString("Vipham"));
            }else{
                lbrLoiBD1.setText("!");
                lbrMaBD_M.setText("");
                lbrTenBD_M.setText("");
                lbrLop_M.setText("");
                lbrSDT_M.setText("");
                lbrVP_M.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    public void UpdateSL_M(){
        String Masach = txtMasach_M.getText();
        try {
        Connection con = kn.getConnection();
        int rSL = Integer.parseInt(lbrSoluong_M.getText());
        int SL = Integer.parseInt(txtSL_M.getText());
        PreparedStatement ps = con.prepareStatement("UPDATE Sach SET Soluong = Soluong - "+SL+" WHERE Masach = ? ");
        ps.setString(1, Masach);
        int Dem = ps.executeUpdate();
        if(Dem>0){
            JOptionPane.showMessageDialog(this, "Số lượng sách đã được cập nhật lại");
            lbrSoluong_M.setText(Integer.toString(rSL - SL));
        }else{
            JOptionPane.showMessageDialog(this, "Số lượng sách không được cập nhật lại");
        }
        } catch (Exception e) {
        }
    }
    
    public void hienthiThongtinTra(){
        String MaPM = txtMaPM_T.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from MuonS where MaPM = ? ");
        ps.setString(1, MaPM);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbrLoiMP_T.setText("");
                lbrMaBD_T.setText(rs.getString("MaBD"));
                lbrTenBD_T.setText(rs.getString("TenBD"));
                lbrMasach_T.setText(rs.getString("Masach"));
                lbrTensach_T.setText(rs.getString("Tensach"));
                lbrSoluong_T.setText(rs.getString("Soluong"));
                lbrNgayM_T.setText(rs.getString("NgayM"));
                lbrNgayT_T.setText(rs.getString("NgayT"));
            }else{
                lbrLoiMP_T.setText("!");
                lbrMaBD_T.setText("");
                lbrTenBD_T.setText("");
                lbrMaBD_T.setText("");
                lbrTensach_T.setText("");
                lbrSoluong_T.setText("");
                lbrNgayM_T.setText("");
                lbrNgayT_T.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    public void UpdateSL_T(){
        String Masach = lbrMasach_T.getText();
        try {
        Connection con = kn.getConnection();
        int SL = Integer.parseInt(lbrSoluong_T.getText());
        PreparedStatement ps = con.prepareStatement("UPDATE Sach SET Soluong = Soluong + "+SL+" WHERE Masach = ? ");
        ps.setString(1, Masach);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Đã cập nhật lại số lượng sách trong kho");
        } catch (Exception e) {
            
        }
    }
    
    public void Update_Vipham(){
        String MaBD = lbrMaBD_T.getText();
        try {
            Connection con = kn.getConnection();
            
            PreparedStatement ps = con.prepareStatement("UPDATE TTBD SET Vipham = Vipham+1 WHERE MaBD =?");
            if(chb4.isSelected()|chb6.isSelected()){
                ps.setString(1, MaBD);
            }
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void hienthiPhieuMuon(){
        String MaS = txtMaphieu_P.getText();
        try {
        Connection con = kn.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from MuonS where MaPM = ? ");
        ps.setString(1, MaS);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                xLoiMP.setText("");
                lbrTen_P.setText(rs.getString("TenBD"));
                lbrLop_P.setText(rs.getString("Lop"));
                lbrMaS_P.setText(rs.getString("Masach"));
                lbrTenS_P.setText(rs.getString("Tensach"));
                lbrNgayM_P.setText(rs.getString("NgayM"));
                lbrNgayT_P.setText(rs.getString("NgayT"));
            }else{
                xLoiMP.setText("!");
                lbrTen_P.setText("");
                lbrLop_P.setText("");
                lbrMaS_P.setText("");
                lbrTenS_P.setText("");
                lbrNgayM_P.setText("");
                lbrNgayT_P.setText("");
            }
        } catch (Exception e) {
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        Gioi_Tinh = new javax.swing.ButtonGroup();
        MuonS = new javax.swing.ButtonGroup();
        Tinh_Trang_S = new javax.swing.ButtonGroup();
        Thoi_Gian_T = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnHomePage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        x1 = new javax.swing.JPanel();
        btnQlySach = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        x2 = new javax.swing.JPanel();
        btnQlyND = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        x3 = new javax.swing.JPanel();
        btnQlyM = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        x4 = new javax.swing.JPanel();
        btnQlyT = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        x5 = new javax.swing.JPanel();
        btnPhieumuon = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        x6 = new javax.swing.JPanel();
        btnDangxuat = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        x7 = new javax.swing.JPanel();
        btnThoatPM = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        x8 = new javax.swing.JPanel();
        Menu_1 = new javax.swing.JTabbedPane();
        Home_Page = new javax.swing.JPanel();
        bgr = new javax.swing.JLabel();
        Qly_Sach = new javax.swing.JPanel();
        panelRound1 = new Customizing.PanelRound();
        txtTensach = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTacgia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbKhoa = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        cbTheloai = new javax.swing.JComboBox<>();
        btnThemS = new javax.swing.JButton();
        btnSuaS = new javax.swing.JButton();
        btnXoaS = new javax.swing.JButton();
        btnResetS = new javax.swing.JButton();
        loiTensach = new javax.swing.JLabel();
        loiSoluong = new javax.swing.JLabel();
        loiTacgia = new javax.swing.JLabel();
        panelRound2 = new Customizing.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        txtSearchS = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        Qly_NguoiDoc = new javax.swing.JPanel();
        panelRound3 = new Customizing.PanelRound();
        txtMaSV = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtLop = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbKhoa1 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        chbNam = new javax.swing.JCheckBox();
        chbNu = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnResetN = new javax.swing.JButton();
        btnXoaN = new javax.swing.JButton();
        btnSuaN = new javax.swing.JButton();
        btnThemN = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        loiMaSV = new javax.swing.JLabel();
        loiLop = new javax.swing.JLabel();
        loiEmail = new javax.swing.JLabel();
        cDate = new com.toedter.calendar.JDateChooser();
        loiHoten = new javax.swing.JLabel();
        loiSDT = new javax.swing.JLabel();
        panelRound4 = new Customizing.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBDoc = new javax.swing.JTable();
        txtSearchBDoc = new javax.swing.JTextField();
        Muon_Sach = new javax.swing.JPanel();
        panelRound5 = new Customizing.PanelRound();
        jSeparator1 = new javax.swing.JSeparator();
        TTin_PM = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtMasach_M = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtMaBD_M = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtSL_M = new javax.swing.JTextField();
        chb2 = new javax.swing.JCheckBox();
        chb1 = new javax.swing.JCheckBox();
        lbNgayM = new javax.swing.JLabel();
        txtNgayM_M = new com.toedter.calendar.JDateChooser();
        lbNgayT = new javax.swing.JLabel();
        txtNgayT_M = new com.toedter.calendar.JDateChooser();
        lbrLoiMS1 = new javax.swing.JLabel();
        lbrLoiBD1 = new javax.swing.JLabel();
        lbrLoiSL = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        TTin_Sach = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lbrMasach_M = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbrTensach_M = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbrTacgia_M = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbrSoluong_M = new javax.swing.JLabel();
        TTin_BDoc = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lbrMaBD_M = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lbrTenBD_M = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbrLop_M = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbrSDT_M = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbrVP_M = new javax.swing.JLabel();
        panelRound6 = new Customizing.PanelRound();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMuonS = new javax.swing.JTable();
        Tra_Sach = new javax.swing.JPanel();
        panelRound7 = new Customizing.PanelRound();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        txtMaPM_T = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        chb4 = new javax.swing.JCheckBox();
        chb3 = new javax.swing.JCheckBox();
        J1 = new javax.swing.JLabel();
        txtNgayT = new com.toedter.calendar.JDateChooser();
        jLabel71 = new javax.swing.JLabel();
        chb5 = new javax.swing.JCheckBox();
        chb6 = new javax.swing.JCheckBox();
        lbrLoiMP_T = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnTraS = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        lbrMaBD_T = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lbrTenBD_T = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lbrMasach_T = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lbrTensach_T = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        lbrSoluong_T = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lbrNgayM_T = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        lbrNgayT_T = new javax.swing.JLabel();
        panelRound9 = new Customizing.PanelRound();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTraS = new javax.swing.JTable();
        Phieu_Muon = new javax.swing.JPanel();
        Phieumuon = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        lbrTen_P = new javax.swing.JTextField();
        lbrLop_P = new javax.swing.JTextField();
        lbrMaS_P = new javax.swing.JTextField();
        lbrTenS_P = new javax.swing.JTextField();
        lbrNgayM_P = new javax.swing.JTextField();
        lbrNgayT_P = new javax.swing.JTextField();
        txtMaphieu_P = new javax.swing.JTextField();
        panelRound8 = new Customizing.PanelRound();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblMuonS1 = new javax.swing.JTable();
        btnInPM = new javax.swing.JButton();
        btnResetPM = new javax.swing.JButton();
        xLoiMP = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1281, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(36, 45, 54));
        Menu.setPreferredSize(new java.awt.Dimension(260, 630));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("QUẢN LÝ THƯ VIỆN");
        Menu.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        btnHomePage.setBackground(new java.awt.Color(29, 38, 40));
        btnHomePage.setPreferredSize(new java.awt.Dimension(260, 55));
        btnHomePage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHomePageMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Home page");

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

        javax.swing.GroupLayout btnHomePageLayout = new javax.swing.GroupLayout(btnHomePage);
        btnHomePage.setLayout(btnHomePageLayout);
        btnHomePageLayout.setHorizontalGroup(
            btnHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHomePageLayout.createSequentialGroup()
                .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(0, 146, Short.MAX_VALUE))
        );
        btnHomePageLayout.setVerticalGroup(
            btnHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHomePageLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHomePageLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnHomePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        btnQlySach.setBackground(new java.awt.Color(36, 45, 54));
        btnQlySach.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlySach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlySachMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quản lý sách");

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

        javax.swing.GroupLayout btnQlySachLayout = new javax.swing.GroupLayout(btnQlySach);
        btnQlySach.setLayout(btnQlySachLayout);
        btnQlySachLayout.setHorizontalGroup(
            btnQlySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlySachLayout.createSequentialGroup()
                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(0, 138, Short.MAX_VALUE))
        );
        btnQlySachLayout.setVerticalGroup(
            btnQlySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlySachLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlySachLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlySach, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        btnQlyND.setBackground(new java.awt.Color(36, 45, 54));
        btnQlyND.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlyND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlyNDMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quản lý bạn đọc");

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

        javax.swing.GroupLayout btnQlyNDLayout = new javax.swing.GroupLayout(btnQlyND);
        btnQlyND.setLayout(btnQlyNDLayout);
        btnQlyNDLayout.setHorizontalGroup(
            btnQlyNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyNDLayout.createSequentialGroup()
                .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(0, 112, Short.MAX_VALUE))
        );
        btnQlyNDLayout.setVerticalGroup(
            btnQlyNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyNDLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlyNDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlyND, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, -1));

        btnQlyM.setBackground(new java.awt.Color(36, 45, 54));
        btnQlyM.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlyM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlyMMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mượn sách");

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

        javax.swing.GroupLayout btnQlyMLayout = new javax.swing.GroupLayout(btnQlyM);
        btnQlyM.setLayout(btnQlyMLayout);
        btnQlyMLayout.setHorizontalGroup(
            btnQlyMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyMLayout.createSequentialGroup()
                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(0, 150, Short.MAX_VALUE))
        );
        btnQlyMLayout.setVerticalGroup(
            btnQlyMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyMLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlyMLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlyM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, -1, -1));

        btnQlyT.setBackground(new java.awt.Color(36, 45, 54));
        btnQlyT.setPreferredSize(new java.awt.Dimension(260, 55));
        btnQlyT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQlyTMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Trả sách");

        x5.setBackground(new java.awt.Color(255, 255, 255));
        x5.setOpaque(false);
        x5.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x5Layout = new javax.swing.GroupLayout(x5);
        x5.setLayout(x5Layout);
        x5Layout.setHorizontalGroup(
            x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x5Layout.setVerticalGroup(
            x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnQlyTLayout = new javax.swing.GroupLayout(btnQlyT);
        btnQlyT.setLayout(btnQlyTLayout);
        btnQlyTLayout.setHorizontalGroup(
            btnQlyTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyTLayout.createSequentialGroup()
                .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(0, 168, Short.MAX_VALUE))
        );
        btnQlyTLayout.setVerticalGroup(
            btnQlyTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQlyTLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQlyTLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnQlyT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, -1, -1));

        btnPhieumuon.setBackground(new java.awt.Color(36, 45, 54));
        btnPhieumuon.setPreferredSize(new java.awt.Dimension(260, 55));
        btnPhieumuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPhieumuonMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Phiếu mượn");

        x6.setBackground(new java.awt.Color(255, 255, 255));
        x6.setOpaque(false);
        x6.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x6Layout = new javax.swing.GroupLayout(x6);
        x6.setLayout(x6Layout);
        x6Layout.setHorizontalGroup(
            x6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x6Layout.setVerticalGroup(
            x6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnPhieumuonLayout = new javax.swing.GroupLayout(btnPhieumuon);
        btnPhieumuon.setLayout(btnPhieumuonLayout);
        btnPhieumuonLayout.setHorizontalGroup(
            btnPhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPhieumuonLayout.createSequentialGroup()
                .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addGap(0, 142, Short.MAX_VALUE))
        );
        btnPhieumuonLayout.setVerticalGroup(
            btnPhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPhieumuonLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPhieumuonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnPhieumuon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, -1));

        btnDangxuat.setBackground(new java.awt.Color(36, 45, 54));
        btnDangxuat.setPreferredSize(new java.awt.Dimension(260, 55));
        btnDangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDangxuatMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Đăng xuất");

        x7.setBackground(new java.awt.Color(255, 255, 255));
        x7.setOpaque(false);
        x7.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x7Layout = new javax.swing.GroupLayout(x7);
        x7.setLayout(x7Layout);
        x7Layout.setHorizontalGroup(
            x7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x7Layout.setVerticalGroup(
            x7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnDangxuatLayout = new javax.swing.GroupLayout(btnDangxuat);
        btnDangxuat.setLayout(btnDangxuatLayout);
        btnDangxuatLayout.setHorizontalGroup(
            btnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangxuatLayout.createSequentialGroup()
                .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addGap(0, 156, Short.MAX_VALUE))
        );
        btnDangxuatLayout.setVerticalGroup(
            btnDangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangxuatLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDangxuatLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnDangxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, -1, -1));

        btnThoatPM.setBackground(new java.awt.Color(36, 45, 54));
        btnThoatPM.setPreferredSize(new java.awt.Dimension(260, 55));
        btnThoatPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThoatPMMousePressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Thoát phần mềm");

        x8.setBackground(new java.awt.Color(255, 255, 255));
        x8.setOpaque(false);
        x8.setPreferredSize(new java.awt.Dimension(5, 55));

        javax.swing.GroupLayout x8Layout = new javax.swing.GroupLayout(x8);
        x8.setLayout(x8Layout);
        x8Layout.setHorizontalGroup(
            x8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        x8Layout.setVerticalGroup(
            x8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnThoatPMLayout = new javax.swing.GroupLayout(btnThoatPM);
        btnThoatPM.setLayout(btnThoatPMLayout);
        btnThoatPMLayout.setHorizontalGroup(
            btnThoatPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThoatPMLayout.createSequentialGroup()
                .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(0, 106, Short.MAX_VALUE))
        );
        btnThoatPMLayout.setVerticalGroup(
            btnThoatPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThoatPMLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnThoatPMLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Menu.add(btnThoatPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, -1, -1));

        jPanel1.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        Home_Page.setBackground(new java.awt.Color(255, 255, 255));
        Home_Page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Home_Page.add(bgr, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 710));

        Menu_1.addTab("tab1", Home_Page);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setPreferredSize(new java.awt.Dimension(852, 250));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        txtTensach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTensach.setToolTipText("Bao gồm các ký tự chữ và số. Không chứa các ký tự đặc biệt");
        txtTensach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTensachKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên sách");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tác giả");

        txtTacgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTacgia.setToolTipText("Chỉ bao gồm các ký tự chữ. Không chứa các ký tự đặc biệt");
        txtTacgia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTacgiaKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Khoa");

        cbKhoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbKhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Công nghệ thông tin", "Công nghệ điện tử và truyền thông", "Công nghệ tự động hóa", "Hệ thống thông tin kinh tế", "Khoa học cơ bản", "Truyền thông đa phương tiện" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Thể loại");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Số lượng");

        txtSoluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoluong.setToolTipText("Chỉ bao gồm các ký tự số. Không chứa các ký tự đặc biệt");
        txtSoluong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoluongKeyReleased(evt);
            }
        });

        cbTheloai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTheloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giáo trình", "Sách", "Luận án", "Sách tham khảo" }));

        btnThemS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemS.setText("Thêm");
        btnThemS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSActionPerformed(evt);
            }
        });

        btnSuaS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaS.setText("Sửa");
        btnSuaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSActionPerformed(evt);
            }
        });

        btnXoaS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaS.setText("Xóa");
        btnXoaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSActionPerformed(evt);
            }
        });

        btnResetS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetS.setText("Reset");
        btnResetS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSActionPerformed(evt);
            }
        });

        loiTensach.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiTensach.setForeground(new java.awt.Color(255, 0, 0));
        loiTensach.setText("  ");
        loiTensach.setBorder(new javax.swing.border.MatteBorder(null));

        loiSoluong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiSoluong.setForeground(new java.awt.Color(255, 0, 0));
        loiSoluong.setText(" ");
        loiSoluong.setBorder(new javax.swing.border.MatteBorder(null));

        loiTacgia.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiTacgia.setForeground(new java.awt.Color(255, 0, 0));
        loiTacgia.setText("  ");
        loiTacgia.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(txtTensach, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(txtTacgia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loiTensach, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loiTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbKhoa, 0, 220, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTheloai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(panelRound1Layout.createSequentialGroup()
                                        .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(loiSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnThemS)
                                .addGap(18, 18, 18)
                                .addComponent(btnSuaS)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaS)
                                .addGap(18, 18, 18)
                                .addComponent(btnResetS)
                                .addGap(29, 29, 29)))
                        .addGap(11, 11, 11))))
        );

        panelRound1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnResetS, btnSuaS, btnThemS, btnXoaS});

        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTensach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loiTensach, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTheloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loiSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loiTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel12))
                .addGap(83, 83, 83)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaS)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemS)
                        .addComponent(btnXoaS)
                        .addComponent(btnResetS)))
                .addGap(17, 17, 17))
        );

        panelRound1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnResetS, btnSuaS, btnThemS, btnXoaS});

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

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
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSach);

        txtSearchS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSKeyReleased(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Tìm kiếm");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel47)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Qly_SachLayout = new javax.swing.GroupLayout(Qly_Sach);
        Qly_Sach.setLayout(Qly_SachLayout);
        Qly_SachLayout.setHorizontalGroup(
            Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Qly_SachLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        Qly_SachLayout.setVerticalGroup(
            Qly_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Qly_SachLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab2", Qly_Sach);

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setPreferredSize(new java.awt.Dimension(852, 250));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        txtMaSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSV.setToolTipText("Viết in hoa. Ví dụ: DTC21H... hoặc DTC215...");
        txtMaSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSVKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Mã sinh viên");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Lớp");

        txtLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLop.setToolTipText("Viết in hoa. Ví dụ CNTT K20I, TDH K16A,...");
        txtLop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLopKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Khoa");

        cbKhoa1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbKhoa1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Công nghệ thông tin", "Công nghệ điện tử và truyền thông", "Công nghệ tự động hóa", "Hệ thống thông tin kinh tế", "Khoa học cơ bản", "Truyền thông đa phương tiện" }));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Họ tên");

        txtHoten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoten.setToolTipText("Bao gồm các ký tự chữ. Không chứa các ký tự đặc biệt");
        txtHoten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHotenKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Ngày sinh");

        Gioi_Tinh.add(chbNam);
        chbNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chbNam.setText("Nam");

        Gioi_Tinh.add(chbNu);
        chbNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chbNu.setText("Nữ");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Giới tính");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Email");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setToolTipText("Viết thường, không bao gồm ký tự đặc biệt. Ví dụ hvt@gmail.com");
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        btnResetN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetN.setText("Reset");
        btnResetN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetNActionPerformed(evt);
            }
        });

        btnXoaN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaN.setText("Xóa");
        btnXoaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNActionPerformed(evt);
            }
        });

        btnSuaN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaN.setText("Sửa");
        btnSuaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNActionPerformed(evt);
            }
        });

        btnThemN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemN.setText("Thêm");
        btnThemN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Số điện thoại");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT.setToolTipText("Bao gồm 10 chữ số và bắt đầu bằng số 0");
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        loiMaSV.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiMaSV.setForeground(new java.awt.Color(255, 0, 0));
        loiMaSV.setText(" ");
        loiMaSV.setBorder(new javax.swing.border.MatteBorder(null));
        loiMaSV.setPreferredSize(new java.awt.Dimension(7, 26));

        loiLop.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiLop.setForeground(new java.awt.Color(255, 0, 0));
        loiLop.setText(" ");
        loiLop.setBorder(new javax.swing.border.MatteBorder(null));
        loiLop.setPreferredSize(new java.awt.Dimension(7, 26));

        loiEmail.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiEmail.setForeground(new java.awt.Color(255, 0, 0));
        loiEmail.setText(" ");
        loiEmail.setBorder(new javax.swing.border.MatteBorder(null));
        loiEmail.setPreferredSize(new java.awt.Dimension(7, 26));

        cDate.setDateFormatString("dd-MM-yyyy");
        cDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cDate.setOpaque(false);

        loiHoten.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiHoten.setForeground(new java.awt.Color(255, 0, 0));
        loiHoten.setText(" ");
        loiHoten.setBorder(new javax.swing.border.MatteBorder(null));
        loiHoten.setPreferredSize(new java.awt.Dimension(7, 26));

        loiSDT.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        loiSDT.setForeground(new java.awt.Color(255, 0, 0));
        loiSDT.setText(" ");
        loiSDT.setBorder(new javax.swing.border.MatteBorder(null));
        loiSDT.setPreferredSize(new java.awt.Dimension(7, 26));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemN)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaN)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaN)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetN))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(txtMaSV)
                            .addComponent(txtLop)
                            .addComponent(cbKhoa1, 0, 220, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loiMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addComponent(chbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(chbNu))
                            .addComponent(jLabel20)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loiHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(cDate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loiEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loiSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        panelRound3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnResetN, btnSuaN, btnThemN, btnXoaN});

        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loiMaSV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loiEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loiHoten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loiLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loiSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(16, 16, 16)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chbNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chbNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cbKhoa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetN)
                    .addComponent(btnXoaN)
                    .addComponent(btnSuaN)
                    .addComponent(btnThemN))
                .addGap(24, 24, 24))
        );

        panelRound3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnResetN, btnSuaN, btnThemN, btnXoaN});

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        tblBDoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã bạn đọc", "Mã SV", "Lớp", "Khoa", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email", "Vi phạm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBDoc.setFocusable(false);
        tblBDoc.setGridColor(new java.awt.Color(204, 204, 204));
        tblBDoc.setRowHeight(22);
        tblBDoc.setShowGrid(false);
        tblBDoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBDocMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblBDoc);

        txtSearchBDoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchBDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchBDocKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addComponent(txtSearchBDoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchBDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Qly_NguoiDocLayout = new javax.swing.GroupLayout(Qly_NguoiDoc);
        Qly_NguoiDoc.setLayout(Qly_NguoiDocLayout);
        Qly_NguoiDocLayout.setHorizontalGroup(
            Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Qly_NguoiDocLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        Qly_NguoiDocLayout.setVerticalGroup(
            Qly_NguoiDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Qly_NguoiDocLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab3", Qly_NguoiDoc);

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setPreferredSize(new java.awt.Dimension(852, 250));
        panelRound5.setRoundBottomLeft(20);
        panelRound5.setRoundBottomRight(20);
        panelRound5.setRoundTopLeft(20);
        panelRound5.setRoundTopRight(20);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TTin_PM.setBackground(new java.awt.Color(255, 255, 255));
        TTin_PM.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setAutoscrolls(true);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Mã sách");

        txtMasach_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMasach_M.setToolTipText("Ghi đúng định dạng mã sách. Ví dụ: S001, S002,...");
        txtMasach_M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMasach_MKeyReleased(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Mã bạn đọc");

        txtMaBD_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaBD_M.setToolTipText("Ghi đúng định dạng mã bạn đọc. Ví dụ: BD001, BD002,...");
        txtMaBD_M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaBD_MKeyReleased(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Số lượng");

        txtSL_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSL_M.setToolTipText("Chỉ bao gồm các ký tự số và không chứa ký hiệu đặc biệt");
        txtSL_M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSL_MKeyReleased(evt);
            }
        });

        MuonS.add(chb2);
        chb2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb2.setText("Mượn về");
        chb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb2ActionPerformed(evt);
            }
        });

        MuonS.add(chb1);
        chb1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb1.setText("Đọc tại chỗ");
        chb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb1ActionPerformed(evt);
            }
        });

        lbNgayM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgayM.setText("Ngày mượn");

        txtNgayM_M.setDateFormatString("dd-MM-yyyy");
        txtNgayM_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayM_M.setPreferredSize(new java.awt.Dimension(88, 26));

        lbNgayT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgayT.setText("Ngày trả");

        txtNgayT_M.setDateFormatString("dd-MM-yyyy");
        txtNgayT_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayT_M.setPreferredSize(new java.awt.Dimension(88, 26));

        lbrLoiMS1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiMS1.setForeground(java.awt.Color.red);
        lbrLoiMS1.setText("  ");

        lbrLoiBD1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiBD1.setForeground(java.awt.Color.red);
        lbrLoiBD1.setText("  ");

        lbrLoiSL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiSL.setForeground(java.awt.Color.red);
        lbrLoiSL.setText("  ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(txtMasach_M, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbrLoiMS1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34)
                            .addComponent(txtMaBD_M, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel35)
                            .addComponent(txtSL_M, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(chb1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chb2))
                            .addComponent(lbNgayM)
                            .addComponent(txtNgayM_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNgayT)
                            .addComponent(txtNgayT_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbrLoiBD1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrLoiSL, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMasach_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiMS1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaBD_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiBD1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSL_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiSL, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chb1)
                    .addComponent(chb2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNgayM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayM_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNgayT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayT_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        TTin_PM.setViewportView(jPanel2);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Cho mượn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TTin_Sach.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Mã sách:");

        lbrMasach_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMasach_M.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Tên sách:");

        lbrTensach_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTensach_M.setText(" ");
        lbrTensach_M.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Tác giả:");

        lbrTacgia_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTacgia_M.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Số lượng:");

        lbrSoluong_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrSoluong_M.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbrSoluong_M, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrTacgia_M, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrTensach_M, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrMasach_M, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrMasach_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrTensach_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrTacgia_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrSoluong_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        TTin_Sach.setViewportView(jPanel4);

        TTin_BDoc.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Mã bạn đọc:");

        lbrMaBD_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMaBD_M.setToolTipText("");
        lbrMaBD_M.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Tên bạn đọc:");

        lbrTenBD_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTenBD_M.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Lớp:");

        lbrLop_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrLop_M.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("SĐT:");

        lbrSDT_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrSDT_M.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Số lần VP:");

        lbrVP_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrVP_M.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbrMaBD_M, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbrSDT_M, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(lbrLop_M, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbrVP_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbrTenBD_M, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrMaBD_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrTenBD_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrLop_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrSDT_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrVP_M, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        TTin_BDoc.setViewportView(jPanel5);

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(TTin_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addComponent(TTin_BDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(TTin_PM, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TTin_PM, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TTin_BDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TTin_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound6.setBackground(new java.awt.Color(255, 255, 255));
        panelRound6.setRoundBottomLeft(20);
        panelRound6.setRoundBottomRight(20);
        panelRound6.setRoundTopLeft(20);
        panelRound6.setRoundTopRight(20);

        tblMuonS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu mượn", "Mã sách", "Tên sách", "Mã bạn đọc", "Tên bạn đọc", "Lớp", "Ngày mượn", "Ngày trả", "Số lượng", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblMuonS);

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Muon_SachLayout = new javax.swing.GroupLayout(Muon_Sach);
        Muon_Sach.setLayout(Muon_SachLayout);
        Muon_SachLayout.setHorizontalGroup(
            Muon_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Muon_SachLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(Muon_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        Muon_SachLayout.setVerticalGroup(
            Muon_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Muon_SachLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab4", Muon_Sach);

        panelRound7.setBackground(new java.awt.Color(255, 255, 255));
        panelRound7.setPreferredSize(new java.awt.Dimension(852, 250));
        panelRound7.setRoundBottomLeft(20);
        panelRound7.setRoundBottomRight(20);
        panelRound7.setRoundTopLeft(20);
        panelRound7.setRoundTopRight(20);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setAutoscrolls(true);

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setText("Mã phiếu mượn");

        txtMaPM_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaPM_T.setToolTipText("Ghi đúng định dạng mã phiếu mượn. Ví dụ: PM001, PM002,...");
        txtMaPM_T.setPreferredSize(new java.awt.Dimension(200, 26));
        txtMaPM_T.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaPM_TKeyReleased(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setText("Tình trạng sách");

        Tinh_Trang_S.add(chb4);
        chb4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb4.setText("Bị rách, hư hỏng");

        Tinh_Trang_S.add(chb3);
        chb3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb3.setText("Nguyên vẹn");

        J1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        J1.setText("Ngày trả thực tế");

        txtNgayT.setDateFormatString("dd-MM-yyyy");
        txtNgayT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayT.setPreferredSize(new java.awt.Dimension(88, 26));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("Thời gian trả");

        Thoi_Gian_T.add(chb5);
        chb5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb5.setText("Trả trước/đúng hạn");
        chb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb5ActionPerformed(evt);
            }
        });

        Thoi_Gian_T.add(chb6);
        chb6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chb6.setText("Trả muộn");
        chb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb6ActionPerformed(evt);
            }
        });

        lbrLoiMP_T.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbrLoiMP_T.setForeground(new java.awt.Color(255, 0, 0));
        lbrLoiMP_T.setText(" ");
        lbrLoiMP_T.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel58)
                            .addComponent(jLabel59)
                            .addComponent(J1)
                            .addComponent(txtNgayT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71)
                            .addComponent(txtMaPM_T, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(chb5)
                                .addComponent(chb6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbrLoiMP_T, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chb3)
                            .addComponent(chb4))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPM_T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrLoiMP_T, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb4)
                .addGap(7, 7, 7)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(J1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel3);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnTraS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTraS.setText("Trả sách");
        btnTraS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Mã bạn đọc");

        lbrMaBD_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMaBD_T.setText(" ");
        lbrMaBD_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Tên bạn đọc");

        lbrTenBD_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTenBD_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setText("Mã sách");

        lbrMasach_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMasach_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setText("Tên sách");

        lbrTensach_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTensach_T.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbrMaBD_T, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrTensach_T, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrMasach_T, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrTenBD_T, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrMaBD_T, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrTenBD_T, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrMasach_T, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrTensach_T, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel6);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("Số lượng");

        lbrSoluong_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrSoluong_T.setToolTipText("");
        lbrSoluong_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setText("Ngày mượn");

        lbrNgayM_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayM_T.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setText("Ngày trả");

        lbrNgayT_T.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayT_T.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbrSoluong_T, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lbrNgayT_T, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lbrNgayM_T, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE))))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrSoluong_T, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrNgayM_T, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbrNgayT_T, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel7);

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound7Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTraS))
                .addGap(30, 30, 30))
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound7Layout.createSequentialGroup()
                        .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(jSeparator4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTraS)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        panelRound9.setBackground(new java.awt.Color(255, 255, 255));
        panelRound9.setRoundBottomLeft(20);
        panelRound9.setRoundBottomRight(20);
        panelRound9.setRoundTopLeft(20);
        panelRound9.setRoundTopRight(20);

        tblTraS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu mượn", "Mã bạn đọc", "Tên bạn đọc", "Mã sách", "Tên sách", "Số lượng", "Ngày mượn", "Ngày trả", "Ngày trả T.Tế", "Tình trạn sách", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblTraS);

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Tra_SachLayout = new javax.swing.GroupLayout(Tra_Sach);
        Tra_Sach.setLayout(Tra_SachLayout);
        Tra_SachLayout.setHorizontalGroup(
            Tra_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tra_SachLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(Tra_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        Tra_SachLayout.setVerticalGroup(
            Tra_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tra_SachLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab5", Tra_Sach);

        Phieumuon.setBackground(new java.awt.Color(255, 255, 255));
        Phieumuon.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel56.setText("THƯ VIỆN TRƯỜNG ĐẠI HỌC CÔNG NGHỆ THÔNG TIN");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setText("PHIẾU MƯỢN");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel60.setText("VÀ TRUYỀN THÔNG THÁI NGUYÊN");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setText("Họ và tên:");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setText("Lớp:");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setText("Mã sách:");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setText("Tên sách:");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setText("Ngày mượn:");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel75.setText("Ngày trả:");

        lbrTen_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTen_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrLop_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrLop_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrMaS_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrMaS_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrTenS_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrTenS_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrNgayM_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayM_P.setBorder(new javax.swing.border.MatteBorder(null));

        lbrNgayT_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbrNgayT_P.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout PhieumuonLayout = new javax.swing.GroupLayout(Phieumuon);
        Phieumuon.setLayout(PhieumuonLayout);
        PhieumuonLayout.setHorizontalGroup(
            PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhieumuonLayout.createSequentialGroup()
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PhieumuonLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel56))
                    .addGroup(PhieumuonLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60)
                            .addGroup(PhieumuonLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel57))))
                    .addGroup(PhieumuonLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74))
                        .addGap(32, 32, 32)
                        .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PhieumuonLayout.createSequentialGroup()
                                .addComponent(lbrNgayM_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbrNgayT_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbrTenS_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrMaS_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrLop_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbrTen_P, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        PhieumuonLayout.setVerticalGroup(
            PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhieumuonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(lbrTen_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(lbrLop_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(lbrMaS_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(lbrTenS_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(lbrNgayM_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrNgayT_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        txtMaphieu_P.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaphieu_P.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaphieu_PKeyReleased(evt);
            }
        });

        panelRound8.setBackground(new java.awt.Color(255, 255, 255));
        panelRound8.setRoundBottomLeft(20);
        panelRound8.setRoundBottomRight(20);
        panelRound8.setRoundTopLeft(20);
        panelRound8.setRoundTopRight(20);

        tblMuonS1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu mượn", "Mã sách", "Tên sách", "Mã bạn đọc", "Tên bạn đọc", "Lớp", "Ngày mượn", "Ngày trả", "Số lượng", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblMuonS1);

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnInPM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInPM.setText("In");
        btnInPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPMActionPerformed(evt);
            }
        });

        btnResetPM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetPM.setText("Reset");
        btnResetPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPMActionPerformed(evt);
            }
        });

        xLoiMP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        xLoiMP.setForeground(java.awt.Color.red);
        xLoiMP.setText(" ");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Nhập mã phiếu mượn:");

        javax.swing.GroupLayout Phieu_MuonLayout = new javax.swing.GroupLayout(Phieu_Muon);
        Phieu_Muon.setLayout(Phieu_MuonLayout);
        Phieu_MuonLayout.setHorizontalGroup(
            Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Phieu_MuonLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Phieu_MuonLayout.createSequentialGroup()
                        .addComponent(btnResetPM, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInPM, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Phieu_MuonLayout.createSequentialGroup()
                        .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Phieu_MuonLayout.createSequentialGroup()
                                .addComponent(txtMaphieu_P, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xLoiMP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32))
                        .addGap(25, 25, 25)
                        .addComponent(Phieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );
        Phieu_MuonLayout.setVerticalGroup(
            Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Phieu_MuonLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Phieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(xLoiMP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(Phieu_MuonLayout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMaphieu_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Phieu_MuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInPM)
                    .addComponent(btnResetPM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        Menu_1.addTab("tab6", Phieu_Muon);

        jPanel1.add(Menu_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, -40, 1020, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomePageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomePageMousePressed
        // TODO add your handling code here:
        setColor(btnHomePage);
        x1.setOpaque(true);
        resetColor(new JPanel[]{btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x2,x3,x4,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(0);
        Reset();
    }//GEN-LAST:event_btnHomePageMousePressed

    private void btnQlySachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlySachMousePressed
        // TODO add your handling code here:
        setColor(btnQlySach);
        x2.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x3,x4,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(1);
        Reset();
    }//GEN-LAST:event_btnQlySachMousePressed

    private void btnQlyNDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlyNDMousePressed
        // TODO add your handling code here:
        setColor(btnQlyND);
        x3.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x4,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(2);
        Reset();
    }//GEN-LAST:event_btnQlyNDMousePressed

    private void btnQlyMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlyMMousePressed
        // TODO add your handling code here:
        setColor(btnQlyM);
        x4.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyT,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x3,x5,x6,x7,x8});
        Menu_1.setSelectedIndex(3);
        Reset();
    }//GEN-LAST:event_btnQlyMMousePressed

    private void btnQlyTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQlyTMousePressed
        // TODO add your handling code here:
        setColor(btnQlyT);
        x5.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnPhieumuon,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x3,x4,x6,x7,x8});
        Menu_1.setSelectedIndex(4);
        Reset();
    }//GEN-LAST:event_btnQlyTMousePressed

    private void btnPhieumuonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPhieumuonMousePressed
        // TODO add your handling code here:
        setColor(btnPhieumuon);
        x6.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnDangxuat,btnThoatPM}, new JPanel[]{x1,x2,x3,x4,x5,x7,x8});
        Menu_1.setSelectedIndex(5);
        Reset();
    }//GEN-LAST:event_btnPhieumuonMousePressed

    private void btnDangxuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangxuatMousePressed
        // TODO add your handling code here:
        setColor(btnDangxuat);
        x7.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnThoatPM}, new JPanel[]{x1,x2,x3,x4,x5,x6,x8});
        JFrame frame = new JFrame("Đăng xuất");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn đăng xuất không?","Đăng xuất",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            frm1_GiaoDienDN_DK fr = new frm1_GiaoDienDN_DK();
            fr.setVisible(true);
            this.setVisible(false);
        }
        Reset();
    }//GEN-LAST:event_btnDangxuatMousePressed

    private void btnThoatPMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatPMMousePressed
        // TODO add your handling code here:
        setColor(btnThoatPM);
        x8.setOpaque(true);
        resetColor(new JPanel[]{btnHomePage,btnQlySach,btnQlyND,btnQlyM,btnQlyT,btnPhieumuon,btnDangxuat}, new JPanel[]{x1,x2,x3,x4,x5,x6,x7});
        JFrame frame = new JFrame("Thoát");
        if(JOptionPane.showConfirmDialog(frame,"Bạn có muốn thoát phần mềm không?","Thoát",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnThoatPMMousePressed

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
        // TODO add your handling code here:
        int i = tblSach.getSelectedRow();
        TableModel model = tblSach.getModel();
        txtTensach.setText(model.getValueAt(i, 2).toString());
        txtTacgia.setText(model.getValueAt(i, 3).toString());
        String Theloai = model.getValueAt(i, 4).toString();
            switch(Theloai){
                case"Giáo trình":
                    cbTheloai.setSelectedIndex(0);
                    break;
                case"Sách":
                    cbTheloai.setSelectedIndex(1);
                    break;
                case"Luận án":
                    cbTheloai.setSelectedIndex(2);
                    break;
                case"Sách tham khảo":
                    cbTheloai.setSelectedIndex(3);
                    break;
            }
        String Khoa = model.getValueAt(i, 5).toString();
            switch(Khoa){
                case"Công nghệ thông tin":
                    cbKhoa.setSelectedIndex(0);
                    break;
                case"Công nghệ điện tử và truyền thông":
                    cbKhoa.setSelectedIndex(1);
                    break;
                case"Công nghệ tự động hóa":
                    cbKhoa.setSelectedIndex(2);
                    break;
                case"Hệ thống thông tin kinh tế":
                    cbKhoa.setSelectedIndex(3);
                    break;
                case"Khoa học cơ bản":
                    cbKhoa.setSelectedIndex(4);
                    break;
                case"Truyền thông đa phương tiện":
                    cbKhoa.setSelectedIndex(5);
                    break;
            }
        txtSoluong.setText(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_tblSachMouseClicked

    private void btnThemSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSActionPerformed
        // TODO add your handling code here:
        try {
        Connection con = kn.getConnection();
        String query = "insert into Sach(Tensach, Tacgia, Theloai, Khoa, Soluong) values (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtTensach.getText());
        ps.setString(2, txtTacgia.getText());
        String Theloai;
        Theloai = cbTheloai.getSelectedItem().toString();
        ps.setString(3, Theloai);
        String Khoa;
        Khoa = cbKhoa.getSelectedItem().toString();
        ps.setString(4, Khoa);
        try {
            int sl = Integer.parseInt(txtSoluong.getText());
            ps.setInt(5, sl);
        } catch (Exception e) {
        }
        if(txtTensach.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Tên sách" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    return;
        }else{
                if(txtTacgia.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Tác giả" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    return;
        }else{
                if(txtSoluong.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Số lượng" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    return;
        }else{
                if(!(Pattern.matches(regexTensach, txtTensach.getText()))){
                JOptionPane.showMessageDialog(this, "Tên sách không hợp lệ!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                return;
        }else{
                if(!(Pattern.matches(regexTen, txtTacgia.getText()))){
                JOptionPane.showMessageDialog(this, "Tên tác giả không hợp lệ!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                return;
        }else{
                if(!(Pattern.matches(regexSoluong, txtSoluong.getText()))){
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                return;
                  }
                }
              }
            }
          }
        }
        ps.executeUpdate();//Update dữ liệu đã nhâp lên SQL
        //3 dòng sau để sau khi update xong thì bảng cũng sẽ tự cập nhật lại mà kh cần chạy lại phần mềm
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        show_Sach();
        JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
    }//GEN-LAST:event_btnThemSActionPerformed

    private void btnSuaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSActionPerformed
        // TODO add your handling code here:
        if(tblSach.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 đối tượng để sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else if(tblSach.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Bảng rỗng, vui lòng thực hiện hành động khác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(txtTensach.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Tên sách" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    return;
        }else{
                if(txtTacgia.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Tác giả" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    return;
        }else{
                if(txtSoluong.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Bạn chưa điền Số lượng" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    return;
        }else{
                if(!(Pattern.matches(regexTensach, txtTensach.getText()))){
                JOptionPane.showMessageDialog(this, "Tên sách không hợp lệ!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                return;
        }else{
                if(!(Pattern.matches(regexTen, txtTacgia.getText()))){
                JOptionPane.showMessageDialog(this, "Tên tác giả không hợp lệ!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                return;
        }else{
                if(!(Pattern.matches(regexSoluong, txtSoluong.getText()))){
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                return;
                  }
                }
              }
            }
          }
        }
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa dữ liệu không?", "Sửa", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblSach.getSelectedRow();
        String value = (tblSach.getModel().getValueAt(row, 1).toString());
        String query = "UPDATE Sach SET Tensach=?, Tacgia=?, Theloai=?, Khoa=?, Soluong=? where Masach = '"+value+"'";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtTensach.getText());
        ps.setString(2, txtTacgia.getText());
        String Theloai;
        Theloai = cbTheloai.getSelectedItem().toString();
        ps.setString(3, Theloai);
        String Khoa;
        Khoa = cbKhoa.getSelectedItem().toString();
        ps.setString(4, Khoa);
        ps.setString(5, txtSoluong.getText());
        ps.executeUpdate();
        tblSach.clearSelection();
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        show_Sach();
        JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
    }//GEN-LAST:event_btnSuaSActionPerformed

    private void btnXoaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSActionPerformed
        // TODO add your handling code here:
        if(tblSach.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 đối tượng để xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else if(tblSach.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Bảng rỗng, vui lòng thực hiện hành động khác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa dữ liệu không?", "Xoá", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblSach.getSelectedRow(); //Trả về dữ liệu của dòng đang được chọn
        String value = (tblSach.getModel().getValueAt(row, 1).toString());
        String query = "DELETE FROM Sach WHERE Masach= '"+value+"'";
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        show_Sach();
        JOptionPane.showMessageDialog(this, "Xoá dữ liệu thành công" ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
    }//GEN-LAST:event_btnXoaSActionPerformed

    private void btnResetSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSActionPerformed
        // TODO add your handling code here:
        txtTensach.setText("");
        txtTacgia.setText("");
        cbTheloai.setSelectedIndex(0);
        cbKhoa.setSelectedIndex(0);
        txtSoluong.setText("");
    }//GEN-LAST:event_btnResetSActionPerformed

    private void txtSearchSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSKeyReleased
        // TODO add your handling code here:
        String searchString = txtSearchS.getText();
        TimkiemS(searchString);
    }//GEN-LAST:event_txtSearchSKeyReleased

    private void tblBDocMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBDocMousePressed
        // TODO add your handling code here:
        int i = tblBDoc.getSelectedRow();
        TableModel model = tblBDoc.getModel();
        txtMaSV.setText(model.getValueAt(i, 2).toString());
        txtLop.setText(model.getValueAt(i, 3).toString());
        String Khoa = model.getValueAt(i, 4).toString();
            switch(Khoa){
                case"Công nghệ thông tin":
                    cbKhoa1.setSelectedIndex(0);
                    break;
                case"Công nghệ điện tử và truyền thông":
                    cbKhoa1.setSelectedIndex(1);
                    break;
                case"Công nghệ tự động hóa":
                    cbKhoa1.setSelectedIndex(2);
                    break;
                case"Hệ thống thông tin kinh tế":
                    cbKhoa1.setSelectedIndex(3);
                    break;
                case"Khoa học cơ bản":
                    cbKhoa1.setSelectedIndex(4);
                    break;
                case"Truyền thông đa phương tiện":
                    cbKhoa1.setSelectedIndex(5);
                    break;
            }
        txtHoten.setText(model.getValueAt(i, 5).toString());
        String Gioitinh = model.getValueAt(i, 6).toString();
        if(Gioitinh.equals("Nam")){
            chbNam.setSelected(true);
        }else{
            chbNu.setSelected(true);
        }
        try {
            int srow = tblBDoc.getSelectedRow();
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(srow, 7));
            cDate.setDate(date);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        txtSDT.setText(model.getValueAt(i, 8).toString());
        txtEmail.setText(model.getValueAt(i, 9).toString());
    }//GEN-LAST:event_tblBDocMousePressed

    private void btnThemNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNActionPerformed
        // TODO add your handling code here:
        if(txtMaSV.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền mã sinh viên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtLop.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền tên lớp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtHoten.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền họ tên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!(chbNam.isSelected())||chbNu.isSelected()){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn giới tính", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }else{
            if(cDate.getDate()==null){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày sinh", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }else{
            if(txtSDT.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền số điện thoại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;  
        }else{
            if(txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền email", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!Pattern.matches(regexMaSV, txtMaSV.getText())){
            JOptionPane.showMessageDialog(this, "Mã sinh viên không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!Pattern.matches(regexLop, txtLop.getText())){
            JOptionPane.showMessageDialog(this, "Tên lớp không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!Pattern.matches(regexTen, txtHoten.getText())){
            JOptionPane.showMessageDialog(this, "Họ tên không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }else{
            if(!Pattern.matches(regexSDT, txtSDT.getText())){
           JOptionPane.showMessageDialog(this, "SĐT không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;  
        }else{
            if(!Pattern.matches(regexEmail, txtEmail.getText())){
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
         }
        }
        try {
        Connection con = kn.getConnection();
        String query = "insert into TTBD(MaSV, Lop, Khoa, Hoten, Gioitinh, Ngaysinh, SDT, Gmail, Vipham) values (?,?,?,?,?,?,?,?,'0')";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtMaSV.getText());
        ps.setString(2, txtLop.getText());
        String Khoa;
        Khoa = cbKhoa1.getSelectedItem().toString();
        ps.setString(3, Khoa);
        ps.setString(4, txtHoten.getText());
        String Gioitinh = "";
        if(chbNam.isSelected()){
            Gioitinh += chbNam.getText()+"";
        }else{
            Gioitinh += chbNu.getText()+"";
        }
        ps.setString(5, Gioitinh);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(cDate.getDate());
        ps.setString(6, date);
        ps.setString(7, txtSDT.getText());
        ps.setString(8, txtEmail.getText());
        ps.executeUpdate();
        DefaultTableModel model = (DefaultTableModel) tblBDoc.getModel();
        model.setRowCount(0);
        show_BDoc();
        JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnThemNActionPerformed

    private void btnSuaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNActionPerformed
        // TODO add your handling code here:
        if(txtMaSV.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền mã sinh viên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtLop.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền tên lớp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtHoten.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền họ tên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!(chbNam.isSelected())||chbNu.isSelected()){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn giới tính", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }else{
            if(cDate.getDate()==null){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày sinh", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }else{
            if(txtSDT.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền số điện thoại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;  
        }else{
            if(txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền email", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!Pattern.matches(regexMaSV, txtMaSV.getText())){
            JOptionPane.showMessageDialog(this, "Mã sinh viên không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!Pattern.matches(regexLop, txtLop.getText())){
            JOptionPane.showMessageDialog(this, "Tên lớp không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!Pattern.matches(regexTen, txtHoten.getText())){
            JOptionPane.showMessageDialog(this, "Họ tên không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }else{
            if(!Pattern.matches(regexSDT, txtSDT.getText())){
           JOptionPane.showMessageDialog(this, "SĐT không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;  
        }else{
            if(!Pattern.matches(regexEmail, txtEmail.getText())){
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
         }
        }
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa dữ liệu không?", "Sửa", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblBDoc.getSelectedRow();
        String value = (tblBDoc.getModel().getValueAt(row, 1).toString());
        String VP = (tblBDoc.getModel().getValueAt(row, 10).toString());
        String query = "UPDATE TTBD SET MaSV=?, Lop=?, Khoa=?, Hoten=?, Gioitinh=?, NgaySinh=?, SDT=?, Gmail=?, Vipham="+VP+" WHERE MaBD='"+value+"'";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, txtMaSV.getText());
        ps.setString(2, txtLop.getText());
        String Khoa;
        Khoa = cbKhoa1.getSelectedItem().toString();
        ps.setString(3, Khoa);
        ps.setString(4, txtHoten.getText());
        String Gioitinh = "";
        if(chbNam.isSelected()){
            Gioitinh += chbNam.getText()+"";
        }else{
            Gioitinh += chbNu.getText()+"";
        }
        ps.setString(5, Gioitinh);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(cDate.getDate());
        ps.setString(6, date);
        ps.setString(7, txtSDT.getText());
        ps.setString(8, txtEmail.getText());
        ps.executeUpdate();
        Reset();
        tblBDoc.clearSelection();
        DefaultTableModel model = (DefaultTableModel) tblBDoc.getModel();
        model.setRowCount(0);
        show_BDoc();
        JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
    }//GEN-LAST:event_btnSuaNActionPerformed

    private void btnXoaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNActionPerformed
        // TODO add your handling code here:
        int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa dữ liệu không?", "Xoá", JOptionPane.YES_NO_OPTION);
        if(xn==0){
        try {
        Connection con = kn.getConnection();
        int row = tblBDoc.getSelectedRow();
        String value = (tblBDoc.getModel().getValueAt(row, 1).toString());
        String query = "DELETE FROM TTBD WHERE MaBD = '"+value+"' ";
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
        DefaultTableModel model = (DefaultTableModel) tblBDoc.getModel();
        model.setRowCount(0);
        show_BDoc();
        JOptionPane.showMessageDialog(this, "Xoá dữ liệu thành công");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }}
    }//GEN-LAST:event_btnXoaNActionPerformed

    private void btnResetNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetNActionPerformed
        // TODO add your handling code here:
        txtMaSV.setText("");
        txtLop.setText("");
        cbKhoa1.setSelectedIndex(0);
        txtEmail.setText("");
        txtHoten.setText("");
        Gioi_Tinh.clearSelection();
        txtSDT.setText("");
        cDate.setDate(null);
    }//GEN-LAST:event_btnResetNActionPerformed

    private void txtSearchBDocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBDocKeyReleased
        // TODO add your handling code here:
        String searchString = txtSearchBDoc.getText();
        TimkiemBDoc(searchString);
    }//GEN-LAST:event_txtSearchBDocKeyReleased

    private void chb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb1ActionPerformed
        // TODO add your handling code here:
        lbNgayM.setVisible(false);
        txtNgayM_M.setVisible(false);
        lbNgayT.setVisible(false);
        txtNgayT_M.setVisible(false);
        txtNgayM_M.setDate(NgayTT);
        txtNgayT_M.setDate(NgayTT);
    }//GEN-LAST:event_chb1ActionPerformed

    private void chb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb2ActionPerformed
        // TODO add your handling code here:
        lbNgayM.setVisible(true);
        txtNgayM_M.setVisible(true);
        lbNgayT.setVisible(true);
        txtNgayT_M.setVisible(true);
        txtNgayM_M.setDate(null);
        txtNgayT_M.setDate(null);
    }//GEN-LAST:event_chb2ActionPerformed

    private void txtMasach_MKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasach_MKeyReleased
        // TODO add your handling code here:
        if(!txtMasach_M.getText().equals("")){
            hienthiTTS_M();
        }
    }//GEN-LAST:event_txtMasach_MKeyReleased

    private void txtMaBD_MKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaBD_MKeyReleased
        // TODO add your handling code here:
        if(!txtMaBD_M.getText().equals("")){
            hienthiTTBD_M();
        }
    }//GEN-LAST:event_txtMaBD_MKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(txtMasach_M.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền mã sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtMaBD_M.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền mã bạn đọc", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtSL_M.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền số lượng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!(Pattern.matches(regexSoluong, txtSL_M.getText()))){
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;  
        }else{
            if(!(chb1.isSelected()|chb2.isSelected())){
            JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn hình thức mượn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtNgayM_M.getDate()==null){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày mượn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtNgayT_M.getDate()==null){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày trả", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(lbrMasach_M.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Mã sách không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if(lbrMaBD_M.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Mã bạn đọc không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; 
        }else{
            if(((Integer.parseInt(lbrVP_M.getText()))) >= 3){
            JOptionPane.showMessageDialog(this, "Bạn đọc này đã bị cấm mượn sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }else{
            if(lbrSoluong_M.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "Số lượng sách trong kho đã hết", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if((Integer.parseInt(txtSL_M.getText())) > (Integer.parseInt(lbrSoluong_M.getText()))){
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if((Integer.parseInt(txtSL_M.getText())) <= 0){
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if((txtNgayM_M.getDate()).before(NgayTT)){
            JOptionPane.showMessageDialog(this, "Ngày mượn không được nhỏ hơn ngày thực tế", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; 
        }else{
            if(txtNgayT_M.getDate().before(NgayTT)){
            JOptionPane.showMessageDialog(this, "Ngày trả không được nhỏ hơn ngày thực tế", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if(txtNgayT_M.getDate().before(txtNgayM_M.getDate())){
            JOptionPane.showMessageDialog(this, "Ngày trả không được nhỏ hơn ngày mượn", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
             }
            }
           }
          }
         }
        }
            try {
                Connection con = kn.getConnection();
                String MuonS = "insert into MuonS(Masach, TenSach, MaBD, TenBD, Lop, NgayM, NgayT, Soluong, Ghichu) values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(MuonS);
                ps.setString(1, txtMasach_M.getText());
                ps.setString(2, lbrTensach_M.getText());
                ps.setString(3, txtMaBD_M.getText());
                ps.setString(4, lbrTenBD_M.getText());
                ps.setString(5, lbrLop_M.getText());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if(chb1.isSelected()){
                    Date d = new Date();
                    String dd = df.format(d);
                    ps.setString(6, dd);
                    ps.setString(7,dd);
                }else{
                    String dateM = df.format(txtNgayM_M.getDate());
                    ps.setString(6, dateM);
                    String dateT = df.format(txtNgayT_M.getDate());
                    ps.setString(7, dateT);
                }
                try {
                    int sl = Integer.parseInt(txtSL_M.getText());
                    ps.setInt(8, sl);
                } catch (Exception e) {
                }
                String Ghichu = "";
                if(chb1.isSelected()){
                    Ghichu += chb1.getText();
                }else{
                    Ghichu += chb2.getText();
                }
                ps.setString(9, Ghichu);
                ps.executeUpdate();
                UpdateSL_M();
            DefaultTableModel model = (DefaultTableModel) tblMuonS.getModel();
            DefaultTableModel model1 = (DefaultTableModel) tblMuonS1.getModel();
            model.setRowCount(0);
            model1.setRowCount(0);
            show_DSMuon();
            show_Sach();
            JOptionPane.showMessageDialog(this, "Cho mượn thành công");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb5ActionPerformed
        // TODO add your handling code here:
        J1.setVisible(false);
        txtNgayT.setVisible(false);
        txtNgayT.setDate(NgayTT);
    }//GEN-LAST:event_chb5ActionPerformed

    private void chb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb6ActionPerformed
        // TODO add your handling code here:
        J1.setVisible(true);
        txtNgayT.setVisible(true);
        txtNgayT.setDate(null);
    }//GEN-LAST:event_chb6ActionPerformed

    private void txtMaPM_TKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaPM_TKeyReleased
        // TODO add your handling code here:
        if(!txtMaPM_T.getText().equals("")){
            hienthiThongtinTra();
        }
    }//GEN-LAST:event_txtMaPM_TKeyReleased

    private void btnTraSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSActionPerformed
        // TODO add your handling code here:
        if(txtMaPM_T.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa điền mã phiếu mượn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!(chb3.isSelected()|chb4.isSelected())){
            JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn tình trạng sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(!(chb5.isSelected()|chb6.isSelected())){
            JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn thời gian trả sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtNgayT.getDate()==null){
            JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn ngày trả sách thực tế", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            if(txtNgayT.getDate().before(NgayTT)){
            JOptionPane.showMessageDialog(this, "Ngày trả thực tế không được nhỏ hơn ngày thực tế", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if(lbrTenBD_T.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Mã phiếu mượn không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; 
             }      
            }
           }
          }
         }
        }
        try {
            Connection con = kn.getConnection();
            String TraS = "insert into TraS(MaPM, MaBD, TenBD, Masach, Tensach, Soluong, NgayM, NgayT, NgayT_TT, TinhtrangS, Ghichu) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(TraS);
            ps.setString(1, txtMaPM_T.getText());
            ps.setString(2, lbrMaBD_T.getText());
            ps.setString(3, lbrTenBD_T.getText());
            ps.setString(4, lbrMasach_T.getText());
            ps.setString(5, lbrTensach_T.getText());
            ps.setString(6, lbrSoluong_T.getText());
            ps.setString(7, lbrNgayM_T.getText());
            ps.setString(8, lbrNgayT_T.getText());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if(chb5.isSelected()){
                Date d = new Date();
                String dd = df.format(d);
                ps.setString(9, dd);
            }else{
                String date = df.format(txtNgayT.getDate());
                ps.setString(9, date);
            }
            String TinhtrangS = "";
            if(chb1.isSelected()){
            TinhtrangS += chb3.getText();
            }else{
            TinhtrangS += chb4.getText();
            }
            ps.setString(10, TinhtrangS);
            String ghichu = "";
            if(chb3.isSelected()){
                ghichu += chb5.getText();
            }else{
                ghichu += chb6.getText();
            }
            ps.setString(11, ghichu);
            String MaS = txtMaPM_T.getText();
            PreparedStatement ps1 = con.prepareStatement("Select * from TraS where MaPM = ? ");
            ps1.setString(1, MaS);
            ResultSet rs = ps1.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(this, "Phiếu mượn này đã được hoàn thành", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{
            ps.executeUpdate();
            UpdateSL_T();
        Update_Vipham();
        DefaultTableModel model = (DefaultTableModel) tblTraS.getModel();
        DefaultTableModel model1 = (DefaultTableModel) tblBDoc.getModel();
        model.setRowCount(0);
        model1.setRowCount(0);
        show_BDoc();
        show_DSTra();
            JOptionPane.showMessageDialog(this, "Trả sách thành công");}
        } catch (Exception e) {
        }
        //hienThiSLHomePage();
        //hienThiSachdangmuon();
    }//GEN-LAST:event_btnTraSActionPerformed

    private void txtMaphieu_PKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaphieu_PKeyReleased
        // TODO add your handling code here:
        if(!txtMaphieu_P.getText().equals("")){
            hienthiPhieuMuon();
        }
    }//GEN-LAST:event_txtMaphieu_PKeyReleased

    private void btnResetPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPMActionPerformed
        // TODO add your handling code here:
        txtMaphieu_P.setText("");
        xLoiMP.setText("");
        lbrTen_P.setText("");
        lbrLop_P.setText("");
        lbrMaS_P.setText("");
        lbrTenS_P.setText("");
        lbrNgayM_P.setText("");
        lbrNgayT_P.setText("");
    }//GEN-LAST:event_btnResetPMActionPerformed

    private void btnInPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPMActionPerformed
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("In phiếu mượn");
            
            job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                    pf.setOrientation(PageFormat.LANDSCAPE);
                 if(pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47,0.47);Phieumuon.print(g2);
                return Printable.PAGE_EXISTS; 
            }
    });
            boolean ok = job.printDialog();
        if(ok){
        try{
            
        job.print();
        }
        catch (PrinterException ex){
	ex.printStackTrace();
}
        }
    }//GEN-LAST:event_btnInPMActionPerformed

    private void txtTensachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTensachKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexTensach, txtTensach.getText()))){
            loiTensach.setText("!");
        }else{
            loiTensach.setText("");
        }
    }//GEN-LAST:event_txtTensachKeyReleased

    private void txtTacgiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTacgiaKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexTen, txtTacgia.getText()))){
            loiTacgia.setText("!");
        }else{
            loiTacgia.setText("");
        }
    }//GEN-LAST:event_txtTacgiaKeyReleased

    private void txtSoluongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoluongKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexSoluong, txtSoluong.getText()))){
            loiSoluong.setText("!");
        }else{
            loiSoluong.setText("");
        }
    }//GEN-LAST:event_txtSoluongKeyReleased

    private void txtMaSVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSVKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexMaSV, txtMaSV.getText()))){
            loiMaSV.setText("!");
        }else{
            loiMaSV.setText("");
        }
    }//GEN-LAST:event_txtMaSVKeyReleased

    private void txtHotenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHotenKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexTen, txtHoten.getText()))){
            loiHoten.setText("!");
        }else{
            loiHoten.setText("");
        }
    }//GEN-LAST:event_txtHotenKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexEmail, txtEmail.getText()))){
            loiEmail.setText("!");
        }else{
            loiEmail.setText("");
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexSDT, txtSDT.getText()))){
            loiSDT.setText("!");
        }else{
            loiSDT.setText("");
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtLopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLopKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexLop, txtLop.getText()))){
            loiLop.setText("!");
        }else{
            loiLop.setText("");
        }
    }//GEN-LAST:event_txtLopKeyReleased

    private void txtSL_MKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSL_MKeyReleased
        // TODO add your handling code here:
        if(!(Pattern.matches(regexSoluong, txtSL_M.getText()))){
            lbrLoiSL.setText("!");
        }else{
            lbrLoiSL.setText("");
        }
    }//GEN-LAST:event_txtSL_MKeyReleased
   /**
     * @param args the command line arguments
     */
    public Date NgayTT = new Date();
    public void Reset(){
        txtTensach.setText(null);
        txtTacgia.setText(null);
        cbTheloai.setSelectedIndex(0);
        cbKhoa.setSelectedIndex(0);
        txtSoluong.setText(null);
        txtMaSV.setText(null);
        txtLop.setText(null);
        cbKhoa1.setSelectedIndex(0);
        txtHoten.setText(null);
        Gioi_Tinh.clearSelection();
        cDate.setDate(null);
        txtEmail.setText(null);
        txtSDT.setText(null);
        txtMasach_M.setText(null);
        txtMaBD_M.setText(null);
        txtSL_M.setText(null);
        MuonS.clearSelection();
        txtNgayM_M.setDate(null);
        txtNgayT_M.setDate(null);
        txtMaPM_T.setText(null);
        Tinh_Trang_S.clearSelection();
        Thoi_Gian_T.clearSelection();
        txtNgayT.setDate(null);
        txtMaphieu_P.setText(null);
    }
    public static void main(String args[]) {
        try {
            
            UIManager.put("ScrollPane.border", new Color(255,255,255));
            UIManager.put("TextComponent.arc", 15);
            UIManager.put( "Component.focusWidth", 0);
            //UIManager.put("Button.borderColor", Color.BLACK);
            UIManager.put("Button.arc", 15);
            //UIManager.put("ScrollBar.background", new Color(255,255,255));
            UIManager.put("ScrollBar.track", new Color(255,255,255));
            UIManager.put("ScrollBar.hoverTrackColor", new Color(255,255,255));
            UIManager.put("ScrollBar.width", 10);
            /*UIManager.put("Button.background", new Color(40,42,58));
            UIManager.put("Button.hoverBackground", new Color(30,32,44));
            UIManager.put("Button.hoverBorderColor", new Color(40,42,58));
            UIManager.put("Button.focusedBorderColor", new Color(255,255,255));*/
            //UIManager.put("ComboBox.buttonArrowColor", new Color(0,50,50));
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm2_GiaoDienThuThu().setVisible(true);
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
    private javax.swing.ButtonGroup Gioi_Tinh;
    private javax.swing.JPanel Home_Page;
    private javax.swing.JLabel J1;
    private javax.swing.JPanel Menu;
    private javax.swing.JTabbedPane Menu_1;
    private javax.swing.ButtonGroup MuonS;
    private javax.swing.JPanel Muon_Sach;
    private javax.swing.JPanel Phieu_Muon;
    private javax.swing.JPanel Phieumuon;
    private javax.swing.JPanel Qly_NguoiDoc;
    private javax.swing.JPanel Qly_Sach;
    private javax.swing.JScrollPane TTin_BDoc;
    private javax.swing.JScrollPane TTin_PM;
    private javax.swing.JScrollPane TTin_Sach;
    private javax.swing.ButtonGroup Thoi_Gian_T;
    private javax.swing.ButtonGroup Tinh_Trang_S;
    private javax.swing.JPanel Tra_Sach;
    private javax.swing.JLabel bgr;
    private javax.swing.JPanel btnDangxuat;
    private javax.swing.JPanel btnHomePage;
    private javax.swing.JButton btnInPM;
    private javax.swing.JPanel btnPhieumuon;
    private javax.swing.JPanel btnQlyM;
    private javax.swing.JPanel btnQlyND;
    private javax.swing.JPanel btnQlySach;
    private javax.swing.JPanel btnQlyT;
    private javax.swing.JButton btnResetN;
    private javax.swing.JButton btnResetPM;
    private javax.swing.JButton btnResetS;
    private javax.swing.JButton btnSuaN;
    private javax.swing.JButton btnSuaS;
    private javax.swing.JButton btnThemN;
    private javax.swing.JButton btnThemS;
    private javax.swing.JPanel btnThoatPM;
    private javax.swing.JButton btnTraS;
    private javax.swing.JButton btnXoaN;
    private javax.swing.JButton btnXoaS;
    private com.toedter.calendar.JDateChooser cDate;
    private javax.swing.JComboBox<String> cbKhoa;
    private javax.swing.JComboBox<String> cbKhoa1;
    private javax.swing.JComboBox<String> cbTheloai;
    private javax.swing.JCheckBox chb1;
    private javax.swing.JCheckBox chb2;
    private javax.swing.JCheckBox chb3;
    private javax.swing.JCheckBox chb4;
    private javax.swing.JCheckBox chb5;
    private javax.swing.JCheckBox chb6;
    private javax.swing.JCheckBox chbNam;
    private javax.swing.JCheckBox chbNu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbNgayM;
    private javax.swing.JLabel lbNgayT;
    private javax.swing.JLabel lbrLoiBD1;
    private javax.swing.JLabel lbrLoiMP_T;
    private javax.swing.JLabel lbrLoiMS1;
    private javax.swing.JLabel lbrLoiSL;
    private javax.swing.JLabel lbrLop_M;
    private javax.swing.JTextField lbrLop_P;
    private javax.swing.JLabel lbrMaBD_M;
    private javax.swing.JLabel lbrMaBD_T;
    private javax.swing.JTextField lbrMaS_P;
    private javax.swing.JLabel lbrMasach_M;
    private javax.swing.JLabel lbrMasach_T;
    private javax.swing.JTextField lbrNgayM_P;
    private javax.swing.JLabel lbrNgayM_T;
    private javax.swing.JTextField lbrNgayT_P;
    private javax.swing.JLabel lbrNgayT_T;
    private javax.swing.JLabel lbrSDT_M;
    private javax.swing.JLabel lbrSoluong_M;
    private javax.swing.JLabel lbrSoluong_T;
    private javax.swing.JLabel lbrTacgia_M;
    private javax.swing.JLabel lbrTenBD_M;
    private javax.swing.JLabel lbrTenBD_T;
    private javax.swing.JTextField lbrTenS_P;
    private javax.swing.JTextField lbrTen_P;
    private javax.swing.JLabel lbrTensach_M;
    private javax.swing.JLabel lbrTensach_T;
    private javax.swing.JLabel lbrVP_M;
    private javax.swing.JLabel loiEmail;
    private javax.swing.JLabel loiHoten;
    private javax.swing.JLabel loiLop;
    private javax.swing.JLabel loiMaSV;
    private javax.swing.JLabel loiSDT;
    private javax.swing.JLabel loiSoluong;
    private javax.swing.JLabel loiTacgia;
    private javax.swing.JLabel loiTensach;
    private Customizing.PanelRound panelRound1;
    private Customizing.PanelRound panelRound2;
    private Customizing.PanelRound panelRound3;
    private Customizing.PanelRound panelRound4;
    private Customizing.PanelRound panelRound5;
    private Customizing.PanelRound panelRound6;
    private Customizing.PanelRound panelRound7;
    private Customizing.PanelRound panelRound8;
    private Customizing.PanelRound panelRound9;
    private javax.swing.JTable tblBDoc;
    private javax.swing.JTable tblMuonS;
    private javax.swing.JTable tblMuonS1;
    private javax.swing.JTable tblSach;
    private javax.swing.JTable tblTraS;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMaBD_M;
    private javax.swing.JTextField txtMaPM_T;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtMaphieu_P;
    private javax.swing.JTextField txtMasach_M;
    private com.toedter.calendar.JDateChooser txtNgayM_M;
    private com.toedter.calendar.JDateChooser txtNgayT;
    private com.toedter.calendar.JDateChooser txtNgayT_M;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSL_M;
    private javax.swing.JTextField txtSearchBDoc;
    private javax.swing.JTextField txtSearchS;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTacgia;
    private javax.swing.JTextField txtTensach;
    private javax.swing.JPanel x1;
    private javax.swing.JPanel x2;
    private javax.swing.JPanel x3;
    private javax.swing.JPanel x4;
    private javax.swing.JPanel x5;
    private javax.swing.JPanel x6;
    private javax.swing.JPanel x7;
    private javax.swing.JPanel x8;
    private javax.swing.JLabel xLoiMP;
    // End of variables declaration//GEN-END:variables
}
