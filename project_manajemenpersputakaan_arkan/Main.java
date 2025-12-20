import gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        try {
            new MainFrame().setVisible(true);
        } catch (Exception e) {
            System.err.println("error, ngga ada mainframe di project ini.");
            e.printStackTrace();
        }
    }
}
