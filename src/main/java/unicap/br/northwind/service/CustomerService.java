package unicap.br.northwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicap.br.northwind.domain.models.Customer;
import unicap.br.northwind.dtos.mapper.OrdersAndCustomersReportMapper;
import unicap.br.northwind.dtos.request.CustomerRequest;
import unicap.br.northwind.dtos.response.ErrorResponse;
import unicap.br.northwind.dtos.response.OrdersAndCustomersReportResponse;
import unicap.br.northwind.exceptions.InvalidFieldException;
import unicap.br.northwind.exceptions.NotFoundException;
import unicap.br.northwind.exceptions.NotSavedException;
import unicap.br.northwind.repository.CustomerRepository;
import unicap.br.northwind.utils.ModelUtil;

import javax.transaction.Transactional;
import java.util.*;


@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    private ModelUtil modelUtil;

    private void verifyCustomer(CustomerRequest customerRequest) throws InvalidFieldException {
        modelUtil = ModelUtil.getInstance();
        Customer customer = new Customer();

        modelUtil.map(customerRequest, customer);
        ErrorResponse error = modelUtil.validate(customer);

        if (!Objects.isNull(error)) {
            throw new InvalidFieldException(error.getError());
        }
    }

    @Transactional
    public Customer registerCustomer(CustomerRequest customerRequest) throws InvalidFieldException,
            NotSavedException {
        verifyCustomer(customerRequest);

        try {
            Customer customer = new Customer();
            modelUtil.map(customerRequest, customer);
            customer.setCustomerID(this.generateCustomerID(customer.getCompanyName()));

            repository.save(customer);
            return customer;
        } catch (Exception e) {
            throw new NotSavedException();
        }
    }


    public Customer editCustomer(String idCustomer, CustomerRequest customerRequest)
            throws NotFoundException, InvalidFieldException {
        verifyCustomer(customerRequest);
        Customer customerBase = this.getCustomer(idCustomer);

        modelUtil.map(customerRequest, customerBase);
        customerBase.setCustomerID(idCustomer);

        repository.save(customerBase);
        return customerBase;
    }


    public Customer getCustomer(String idCustomer) throws NotFoundException {
        Optional<Customer> customer = repository.findById(idCustomer);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new NotFoundException(Customer.class.getSimpleName());
        }
    }


    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }


    public Customer deleteCustomer(String idCustomer) throws NotFoundException {
        Customer customerDeleted= this.getCustomer(idCustomer);
        repository.delete(customerDeleted);
        return customerDeleted;
    }


    private String generateCustomerID(String name){
        char[] nameArray = name.toCharArray();

        String customerIDBase = nameArray[0] + "" + nameArray[1] + "" + nameArray[2];
        String customerIDComplement = this.getCustomerIDComplement(nameArray);

        String customerID = customerIDBase + customerIDComplement;

        while(repository.findById(customerID).isPresent()){
            customerIDComplement = this.getCustomerIDComplement(nameArray);
            customerID = customerIDBase + customerIDComplement;;
        }

        return customerID.toUpperCase();
    }


    private String getCustomerIDComplement(char[] nameArray){
        Random random = new Random();
        return nameArray[random.nextInt(nameArray.length)] + "" +
                nameArray[random.nextInt(nameArray.length)];
    }


    public List<OrdersAndCustomersReportResponse> getOrdersAndCustomersReport(){
        OrdersAndCustomersReportMapper mapper = new OrdersAndCustomersReportMapper();
        List<Object[]> reportList = repository.getResumOrdersAndClients();
        List<OrdersAndCustomersReportResponse> reportResponseList = new ArrayList<>();

        reportList.forEach(r -> {
            OrdersAndCustomersReportResponse response = new OrdersAndCustomersReportResponse();

            mapper.map(r, response);
            reportResponseList.add(response);
        });

        return reportResponseList;
    }
}
