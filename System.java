import java.util.Scanner;

class Product {
    int id;
    String name;
    double price;
    int relevance;

    Product(int id, String name, double price, int relevance) {
        this.id = id ;
        this.name = name ;
        this.price = price ;
        this.relevance = relevance ;
    }
}


class MergeSort {

    public static void sort(Product[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(arr, left, mid);
            sort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Product[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Product[] L = new Product[n1];
        Product[] R = new Product[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        
        while (i < n1 && j < n2) {
            if (L[i].relevance > R[j].relevance) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}


class BinarySearch {

    public static Product search(Product[] arr, int key) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid].id == key)
                return arr[mid];
            else if (arr[mid].id < key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return null;
    }
}


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product(101, "Laptop", 60000, 0),
            new Product(102, "Phone", 30000, 0),
            new Product(103, "Headphones", 2000, 0),
            new Product(104, "Watch", 5000, 0),
            new Product(105, "Tablet", 25000, 0)
        };

        
        System.out.println("Enter your preferred category:");
        System.out.println("1. Electronics");
        System.out.println("2. Accessories");
        int choice = sc.nextInt();

        // STEP 2: Assign relevance based on preference
        for (Product p : products) {
            if (choice == 1) {
                if (p.name.equals("Laptop") || p.name.equals("Phone") || p.name.equals("Tablet"))
                    p.relevance = 90;
                else
                    p.relevance = 60;
            } else {
                if (p.name.equals("Watch") || p.name.equals("Headphones"))
                    p.relevance = 90;
                else
                    p.relevance = 60;
            }
        }

        
        MergeSort.sort(products, 0, products.length - 1);

        System.out.println("\nRecommended Products:");
        for (Product p : products) {
            System.out.println(p.id + " " + p.name + " (Relavance1: " + p.relevance + ")");
        }

        // STEP 4: Search product
        System.out.println("\nEnter Product ID to search:");
        int searchId = sc.nextInt();

        Product result = BinarySearch.search(products, searchId);

        if (result != null) {
            System.out.println("\nProduct Found:");
            System.out.println(result.name + " - Rs." + result.price);
        } else {
            System.out.println("\nProduct Not Found");
        }

        sc.close();
    }
}
