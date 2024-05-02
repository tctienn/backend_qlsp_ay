package ql.vn.qlsp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ql.vn.qlsp.service.InvoiceService;
import ql.vn.qlsp.service.VNPayService;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

//@org.springframework.stereotype.Controller
@Controller
public class VNPAYController {
    @Autowired
    private VNPayService vnPayService;


    @GetMapping("/user/pay")
    @ResponseBody
    public String home(HttpServletRequest request){
        String scheme = request.getScheme(); // http
        String serverName = request.getServerName(); // hostname
        int serverPort = request.getServerPort(); // port number
        String contextPath = request.getContextPath(); // includes application context path

        String url = scheme+"://"+serverName+":"+serverPort+contextPath;
        return url+"/submitOrder";
    }

    @PostMapping("/submitOrder")
//    @ResponseBody
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              @RequestParam("address") String address,
                              @RequestParam("idUser") Long idUser,
                              @RequestParam("timestamp") String timestamp,
                              HttpServletRequest request){
        System.out.println("ayy"+orderTotal);
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        
        try{
            String vnpayUrl = vnPayService.createOrder(idUser,address,orderTotal, orderInfo, baseUrl,timestamp);
            return "redirect:" + vnpayUrl;
        }catch (ParseException ex){
            System.out.println(ex);
            return "false";
        }
    }

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model){
        try{
            int paymentStatus =vnPayService.orderReturn(request);

            String orderInfo = request.getParameter("vnp_OrderInfo");
            String paymentTime = request.getParameter("vnp_PayDate");
            String transactionId = request.getParameter("vnp_TransactionNo");
            String totalPrice = request.getParameter("vnp_Amount");
            String vnp_TxnRef = request.getParameter("vnp_TxnRef");

            model.addAttribute("orderId", orderInfo);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("paymentTime", paymentTime);
            model.addAttribute("transactionId", transactionId);
            try{
                if(paymentStatus==1){
                    invoiceService.succesPay(vnp_TxnRef,transactionId,paymentTime);
                }else {
                    invoiceService.falsePay(vnp_TxnRef,paymentTime);
                }
            }
            catch (Exception ex){
                System.out.println(ex);
            }
            return paymentStatus == 1 ? "ordersuccess" : "orderfail";
        }catch (ParseException ex){
            System.out.println(ex);
            return "false";
        }
       
    }
}
