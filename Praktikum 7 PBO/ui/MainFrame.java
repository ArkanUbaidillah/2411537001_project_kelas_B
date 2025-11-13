package ui;

public class MainFrame {
	    public static void main(String[] args) {
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                CustomerFrame f = new CustomerFrame();
	                f.setVisible(true);
	            }
	        });
	    }
}
