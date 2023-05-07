package Bang;

public class Sach {
    private String Masach, Tensach, Tacgia, Theloai, Khoa;
    private int Soluong;
    public Sach(String Masach, String Tensach, String Tacgia, String Theloai, String Khoa, int Soluong)
    {
        this.Masach = Masach;
        this.Tensach = Tensach;
        this.Tacgia = Tacgia;
        this.Theloai = Theloai;
        this.Khoa = Khoa;
        this.Soluong = Soluong;
    }
    public String getMasach(){
        return Masach;
    }
    public String getTensach(){
        return Tensach;
    }
    public String getTacgia(){
        return Tacgia;
    }
    public String getTheloai(){
        return Theloai;
    }
    public String getKhoa(){
        return Khoa;
    }
    public int getSoluong(){
        return Soluong;
    }
}

