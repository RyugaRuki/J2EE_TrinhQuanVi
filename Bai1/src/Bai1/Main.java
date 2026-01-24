
package Bai1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        
        // Menu text block
        String menu = """
                      \n--------------------------------------
                      CHUONG TRINH QUAN LY SACH
                      1. Them 1 cuon sch
                      2. Xoa 1 cuon sach
                      3. Thay doi cuon sach
                      4. Xuat thong tin tat ca cac cuon sach
                      5. Tim sach theo tua de (chua tu khoa)
                      6. Lay toi da K cuon sach co gia <= P
                      7. Tim sach theo danh sach tac gia nhap vao
                      0. Thoat
                      --------------------------------------
                      Chon chuc nang: """;
        
        int chon = 0;
        do {
            System.out.print(menu);
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }

            // Switch expression (Java 14+)
            switch (chon) {
                case 1 -> {
                    System.out.println("--- THEM SACH ---");
                    Book b = new Book();
                    b.input();
                    listBook.add(b);
                }
                case 2 -> {
                    System.out.print("Nhap ma sach can xoa: ");
                    int delId = Integer.parseInt(sc.nextLine());
                    // Dùng removeIf của Collection kết hợp lambda cho ngắn gọn hơn cách trong tài liệu
                    boolean removed = listBook.removeIf(b -> b.getId() == delId);
                    System.out.println(removed ? "Da xoa thanh cong!" : "Khong tim thay ma sach.");
                }
                case 3 -> {
                    System.out.print("Nhap ma sach can sua: ");
                    int updateId = Integer.parseInt(sc.nextLine());
                    // Tìm sách bằng Stream
                    Book foundBook = listBook.stream()
                            .filter(b -> b.getId() == updateId)
                            .findFirst()
                            .orElse(null);
                    
                    if (foundBook != null) {
                        System.out.println("Nhap thong tin moi:");
                        foundBook.input(); // Nhập đè thông tin
                        System.out.println("Cap nhat thanh cong.");
                    } else {
                        System.out.println("Khong tim thay sach.");
                    }
                }
                case 4 -> {
                    System.out.println("--- DANH SACH ---");
                    // Method reference
                    listBook.forEach(Book::output);
                }
                case 5 -> {
                    System.out.print("Nhap tu khoa tua de: ");
                    String keyword = sc.nextLine().toLowerCase();
                    System.out.println("KET QUA TIM KIEM:");
                    // Stream filter + Method reference output
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains(keyword))
                            .forEach(Book::output);
                }
                case 6 -> {
                    // Yêu cầu 6: Lấy K sách có giá <= P
                    System.out.print("Nhap so luong sach (K): ");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap muc gia tran (P): ");
                    double p = Double.parseDouble(sc.nextLine());
                    
                    System.out.println("DANH SACH THOA MAN:");
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p) // Lọc giá <= P
                            .limit(k)                       // Giới hạn K cuốn
                            .forEach(Book::output);
                }
                case 7 -> {
                    // Yêu cầu 7: Nhập danh sách tác giả và lọc
                    System.out.println("Nhap cac tac gia (cach nhau boi dau phay): ");
                    String inputAuthors = sc.nextLine();
                    
                    // Chuyển chuỗi nhập vào thành Set để tìm kiếm nhanh hơn (Gợi ý case 7)
                    Set<String> authorSet = Arrays.stream(inputAuthors.split(","))
                            .map(String::trim) // Xóa khoảng trắng thừa
                            .map(String::toLowerCase) // Chẩn hóa về chữ thường
                            .collect(Collectors.toSet());

                    System.out.println("SACH CUA CAC TAC GIA: " + authorSet);
                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Dang thoat...");
                default -> System.out.println("Vui long chon tu 0 den 7.");
            }
        } while (chon != 0);
    }
}