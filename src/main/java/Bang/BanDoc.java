package Bang;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BanDoc {
    private String MaBD, MaSV, Lop, Khoa, Hoten, Gioitinh;
    private Date Ngaysinh;
    private String  SDT, Gmail;
    private int Vipham;
    public BanDoc(String MaBD, String MaSV, String Lop, String Khoa, String Hoten, String Gioitinh, Date Ngaysinh, String SDT, String Gmail, int Vipham){
        this.MaBD = MaBD;
        this.MaSV = MaSV;
        this.Lop = Lop;
        this.Khoa = Khoa;
        this.Hoten = Hoten;
        this.Gioitinh = Gioitinh;
        this.Ngaysinh = Ngaysinh;
        this.SDT = SDT;
        this.Gmail = Gmail;
        this.Vipham = Vipham;
    }
    public String getMaBD(){
        return MaBD;
    }
    public String getMaSV(){
        return MaSV;
    }
    public String getLop(){
        return Lop;
    }
    public String getKhoa(){
        return Khoa;
    }
    public String getHoten(){
        return Hoten;
    }
    public String getGioitinh(){
        return Gioitinh;
    }
    public String getNgaysinh(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(Ngaysinh);
    }
    public String getSDT(){
        return SDT;
    }
    public String getGmail(){
        return Gmail;
    }
    public int getVipham(){
        return Vipham;
    }       
}
