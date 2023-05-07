package Bang;

import java.sql.Date;

public class TraSach {
    private String MaPM, MaBD, TenBD, Masach, Tensach;
    private int Soluong;
    private Date NgayM, NgayT, NgayT_TT;
    private String TinhtrangS, Ghichu;

    public TraSach(String MaPM, String MaBD, String TenBD, String Masach, String Tensach, int Soluong, Date NgayM, Date NgayT, Date NgayT_TT, String TinhtrangS, String Ghichu) {
        this.MaPM = MaPM;
        this.MaBD = MaBD;
        this.TenBD = TenBD;
        this.Masach = Masach;
        this.Tensach = Tensach;
        this.Soluong = Soluong;
        this.NgayM = NgayM;
        this.NgayT = NgayT;
        this.NgayT_TT = NgayT_TT;
        this.TinhtrangS = TinhtrangS;
        this.Ghichu = Ghichu;
    }

    public String getMaPM() {
        return MaPM;
    }

    public String getMaBD() {
        return MaBD;
    }

    public String getTenBD() {
        return TenBD;
    }

    public String getMasach() {
        return Masach;
    }

    public String getTensach() {
        return Tensach;
    }

    public int getSoluong() {
        return Soluong;
    }

    public Date getNgayM() {
        return NgayM;
    }

    public Date getNgayT() {
        return NgayT;
    }

    public Date getNgayT_TT() {
        return NgayT_TT;
    }

    public String getTinhtrangS() {
        return TinhtrangS;
    }

    public String getGhichu() {
        return Ghichu;
    }
    
}
