// Serve as the entry point for the game
public class Main {
    public static void main(String[] args) {
        MenuView MenuView = new MenuView();
        new MenuController(MenuView);
    }
}