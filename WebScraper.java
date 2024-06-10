import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.Elements;

import org.jsoup.Jsoup;

class Product {
    private String name;
    private String price;
    private String rating;

    public Product(String name, String price, String rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }
}

public class WebScraper {
    public static void main(String[] args) {
        String url = "https://www.example.com/products"; // Replace with the URL of the e-commerce website
        List<Product> products = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements productElements = doc.select(".product"); // Replace with the appropriate CSS selector for the products

            for (Element productElement : productElements) {
                String name = productElement.select(".product-name").text(); // Replace with the appropriate CSS selector for the product name
                String price = productElement.select(".product-price").text(); // Replace with the appropriate CSS selector for the product price
                String rating = productElement.select(".product-rating").text(); // Replace with the appropriate CSS selector for the product rating
                products.add(new Product(name, price, rating));
            }

            writeProductsToCSV(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeProductsToCSV(List<Product> products) {
        try (FileWriter writer = new FileWriter("products.csv")) {
            writer.append("Name,Price,Rating\n");

            for (Product product : products) {
                writer.append(product.getName())
                        .append(",")
                        .append(product.getPrice())
                        .append(",")
                        .append(product.getRating())
                        .append("\n");
            }

            System.out.println("Data has been written to products.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
