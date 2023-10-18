import com.enigma.tokonyadia.entity.Customer;
import com.enigma.tokonyadia.entity.Product;
import com.enigma.tokonyadia.entity.Store;
import com.enigma.tokonyadia.repository.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

//        ==================== CRUD CUSTOMER ===================
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        Customer customer = new Customer("rizdaagisa@gmail.com","Jl. siaga darma",1,"rizda","081290255683");
        Customer customer2 = new Customer("kevin@gmail.com","Jl. yahya nuih",12,"kevin","081293984352");
        customerRepository.save(customer);
        customerRepository.save(customer2);
        customerRepository.deleteById(2);
        Customer data = customerRepository.findById(3);
        System.out.println(data.toString());
        for(Customer value : customerRepository.findAll()){
            System.out.println(value.toString());
        }
        customerRepository.update(new Customer("irvan@gmail.com","Jl. yahya nuih",3,"irvan UPDATED","08888888888"));

//        ==================== CRUD STORE ======================
        StoreRepository storeRepository = new StoreRepositoryImpl();
        Store store = new Store("Toko sejahtra", "08129304923",1,"xx12345","JL.Batu raya");
        Store store2 = new Store("Toko Madura rya", "08128385832",2,"yy51231","JL.Kebagusan raya");
        storeRepository.save(store);
        storeRepository.save(store2);
        Store data2 = storeRepository.findById(2);
        System.out.println(data2.toString());
        for(Store val : storeRepository.findAll()){
            System.out.println(val.toString());
        }
        storeRepository.deleteById(1);
        storeRepository.update(new Store("Toko Madura rya UPDATED", "08128385832",2,"yy51231","JL.Kebagusan raya new"));

//        =================== CRUD PRODUCT =====================
        ProductRepository productRepository = new ProductRepositoryImpl();
        Product product = new Product(1,1,"Ini product 1", 10000l, 10, "odol");
        Product product2 = new Product(2,1,"Ini product 2", 20000l, 20, "sikat gigi");
        Product product3 = new Product(3,2,"Ini product 3", 30000l, 25, "sabun");
        Product product4 = new Product(4,2,"Ini product 4", 40000l, 30, "sampo");

        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        Product data3 = productRepository.findById(1);
        System.out.println(data3.toString());
        productRepository.deleteById(1);
        for(Product val : productRepository.findAll()){
            System.out.println(val.toString());
        }
        productRepository.update(new Product(4,2,"Ini product 4 updated", 45000l, 31, "sampo updated"));

    }
}