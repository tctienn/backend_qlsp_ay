package ql.vn.qlsp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ql.vn.qlsp.entity.InvoiceEntity;
import ql.vn.qlsp.service.InvoiceService;

@RestController
@RequestMapping("user/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping("get-all-invoice")
    public Page<InvoiceEntity> getAllInvoice(Pageable pageable){
        return invoiceService.getallInvoice(pageable);
    }
}
