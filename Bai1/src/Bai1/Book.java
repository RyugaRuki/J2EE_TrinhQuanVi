
package Bai1;
import java.util.Scanner;

public class Book {
    // Thuộc tính
    private int id;
    private String title;
    private String author;
    private double price;

    // Constructor không tham số
    public Book() {
    }

    // Constructor đầy đủ tham số
    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter và Setter (Bạn có thể dùng Insert Code trong NetBeans: Alt + Insert)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Hàm nhập thông tin sách
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma sach: ");
        this.id = Integer.parseInt(sc.nextLine()); // Dùng parse để tránh trôi lệnh
        
        System.out.print("Nhap ten sach: ");
        this.title = sc.nextLine();
        
        System.out.print("Nhap tac gia: ");
        this.author = sc.nextLine();
        
        System.out.print("Nhap don gia: ");
        this.price = Double.parseDouble(sc.nextLine());
    }

    // Hàm xuất thông tin sách (Sử dụng Text Block và format như hướng dẫn)
    public void output() {
        // Sử dụng Text Block (""") cho trực quan
        String msg = """
                     BOOK: id= %d, title= %s, author= %s, price= %.2f
                     """.formatted(id, title, author, price);
        System.out.print(msg);
    }
}