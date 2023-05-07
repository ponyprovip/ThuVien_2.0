package Bang;

import java.sql.Date;

public class MuonSach {
    private String MaPM, Masach, Tensach, MaBD, TenBD, Lop;
    private Date NgayM, NgayT;
    private int Soluong;
    private String Ghichu;

    public MuonSach(String MaPM, String Masach, String Tensach, String MaBD, String TenBD, String Lop, Date NgayM, Date NgayT, int Soluong, String Ghichu) {
        this.MaPM = MaPM;
        this.Masach = Masach;
        this.Tensach = Tensach;
        this.MaBD = MaBD;
        this.TenBD = TenBD;
        this.Lop = Lop;
        this.NgayM = NgayM;
        this.NgayT = NgayT;
        this.Soluong = Soluong;
        this.Ghichu = Ghichu;
    }

    public String getMaPM() {
        return MaPM;
    }

    public String getMasach() {
        return Masach;
    }

    public String getTensach() {
        return Tensach;
    }
    
    public String getMaBD() {
        return MaBD;
    }

    public String getTenBD() {
        return TenBD;
    }

    public String getLop() {
        return Lop;
    }

    public Date getNgayM() {
        return NgayM;
    }

    public Date getNgayT() {
        return NgayT;
    }

    public int getSoluong() {
        return Soluong;
    }

    public String getGhichu() {
        return Ghichu;
    }  
}
