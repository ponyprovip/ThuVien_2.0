package test;

import ThuVien.*;

class Test {
    private String Masach, Tensach, Tacgia;
    public Test(String Masach,String Tensach,String Tacgia)
    {
        this.Masach = Masach;
        this.Tensach = Tensach;
        this.Tacgia = Tacgia;
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
}

