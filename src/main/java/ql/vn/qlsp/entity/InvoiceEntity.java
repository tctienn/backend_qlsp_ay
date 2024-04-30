package ql.vn.qlsp.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="invoice")
@Data
public class InvoiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String magd;
    private String sohd;
    private Double tongtien;
    private String nganhang;
    private String noidung;
    private String trangthai; // trạng thái thanh toán
    private String sp;
    private String giaohang;
    private String diachi;
    private String gmail;
    private String sdt;
    private String tennguoinhan;
    private String ngaytao;

    public InvoiceEntity() {
    }

    //create

//    public InvoiceEntity(String soHD, Double tongTien, String noiDung, String trangThai, String sp, String giaoHang, String diaChi, String gmail, String sdt, String tenNguoiNhan) {
//        this.soHD = soHD;
//        this.tongTien = tongTien;
//        this.noiDung = noiDung;
//        this.trangThai = trangThai;
//        this.sp = sp;
//        this.giaoHang = giaoHang;
//        this.diaChi = diaChi;
//        this.gmail = gmail;
//        this.sdt = sdt;
//        this.tenNguoiNhan = tenNguoiNhan;
//    }

    public InvoiceEntity(String sohd, Double tongtien, String nganhang, String noidung, String trangthai, String sp, String giaohang, String diachi, String gmail, String sdt, String tennguoinhan) {
        this.sohd = sohd;
        this.tongtien = tongtien;
        this.nganhang = nganhang;
        this.noidung = noidung;
        this.trangthai = trangthai;
        this.sp = sp;
        this.giaohang = giaohang;
        this.diachi = diachi;
        this.gmail = gmail;
        this.sdt = sdt;
        this.tennguoinhan = tennguoinhan;
    }
}
