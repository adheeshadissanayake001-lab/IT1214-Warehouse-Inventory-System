import java.util.*;
class Item {
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;

    public Item(String itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Item{id='" + itemId + "', name='" + itemName + "', qty=" + quantity + ", price=" + price + '}';
    }
}


class Inventory {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) { items.add(item); }

    public boolean removeItem(String itemId) {
        return items.removeIf(i -> i.getItemId().equals(itemId));
    }

    public boolean updateQuantity(String itemId, int qty) {
        for (Item i : items) {
            if (i.getItemId().equals(itemId)) {
                i.setQuantity(qty);
                return true;
            }
        }
        return false;
    }

    public Item searchById(String itemId) {
        for (Item i : items) if (i.getItemId().equals(itemId)) return i;
        return null;
    }

    public List<Item> searchByName(String name) {
        List<Item> res = new ArrayList<>();
        for (Item i : items) if (i.getItemName().equalsIgnoreCase(name)) res.add(i);
        return res;
    }

    public void displayAll() {
        if (items.isEmpty()) System.out.println("Empty.");
        else for (Item i : items) System.out.println(i);
    }
}



public class Warehouse {
    private Inventory inv = new Inventory();
    private Scanner sc = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n1.Add 2.Remove 3.Update 4.Search ID 5.Search Name 6.Display 7.Exit");
            int c = sc.nextInt(); sc.nextLine();
            if (c == 1) add();
            else if (c == 2) remove();
            else if (c == 3) update();
            else if (c == 4) searchId();
            else if (c == 5) searchName();
            else if (c == 6) inv.displayAll();
            else if (c == 7) { System.out.println("Exit"); break; }
            else System.out.println("Invalid");
        }
    }

    private void add() {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Qty: "); int q = sc.nextInt();
        System.out.print("Price: "); double p = sc.nextDouble(); sc.nextLine();
        inv.addItem(new Item(id, name, q, p));
        System.out.println("Added");
    }

    private void remove() {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.println(inv.removeItem(id) ? "Removed" : "Not found");
    }

    private void update() {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("New Qty: "); int q = sc.nextInt(); sc.nextLine();
        System.out.println(inv.updateQuantity(id, q) ? "Updated" : "Not found");
    }

    private void searchId() {
        System.out.print("ID: "); String id = sc.nextLine();
        Item i = inv.searchById(id);
        System.out.println(i != null ? i : "Not found");
    }

    private void searchName() {
        System.out.print("Name: "); String n = sc.nextLine();
        List<Item> l = inv.searchByName(n);
        if (l.isEmpty()) System.out.println("None");
        else for (Item i : l) System.out.println(i);
    }

public static void main(String[] args) {
        new Warehouse().run();
    }
}
