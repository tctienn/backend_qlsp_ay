package ql.vn.qlsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ql.vn.qlsp.dto.TopUser;
import ql.vn.qlsp.entity.InvoiceEntity;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Integer> {
    InvoiceEntity findBySohd(String soHD);

    // lấy hóa đơn theo tuần hiện tại
    @Query(value = "SELECT * FROM invoice WHERE YEARWEEK(STR_TO_DATE(ngaytao, '%Y%m%d%H%i%s')) = YEARWEEK(NOW())", nativeQuery = true)
    List<InvoiceEntity> getInvoicesByweek();

    // lấy top 10 ngươif dùng
    @Query(value = "SELECT tennguoinhan as name, sdt,gmail, COUNT(id) as coun " +
            "FROM invoice " +
            "WHERE trangthai = 'succes' " +
            "GROUP BY tennguoinhan " +
            "ORDER BY coun DESC " +
            "LIMIT 10", nativeQuery = true)
    List<TopUser> getTopPay();

    // lấy số đơn hàng đang chờ sử lý
    @Query(value = "SELECT COUNT(id) as coun " +
            "FROM invoice " +
            "WHERE trangthai = 'succes' AND giaohang='wait' ", nativeQuery = true)
   Integer coutInvoiceWait();

    @Query(value = "SELECT *  " +
            "FROM invoice " +
            "WHERE trangthai = 'succes' AND giaohang='wait' ", nativeQuery = true)
    Page<InvoiceEntity> getInvoiceWait(Pageable pageable);


}
