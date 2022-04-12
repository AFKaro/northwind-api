package unicap.br.northwind.dtos.mapper;

import unicap.br.northwind.dtos.response.OrdersAndCustomersReportResponse;

import java.math.BigDecimal;


public class OrdersAndCustomersReportMapper {

    public void map(Object[] values, OrdersAndCustomersReportResponse report) {
        report.setCustomerID(String.valueOf(values[0]));
        report.setCustomerName(String.valueOf(values[1]));
        report.setQuantity_orders((Integer) values[2]);
        report.setQuantity_products((Integer) values[3]);
        report.setAmountSpent((BigDecimal) values[4]);
    }
}
