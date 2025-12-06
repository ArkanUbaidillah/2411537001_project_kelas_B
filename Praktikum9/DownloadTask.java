package Praktikum9;

public class DownloadTask extends Thread {
    private final String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
        setName(fileName); // memberi nama thread
    }

    @Override
    public void run() {
        for (int i = 10; i <= 100; i += 10) {
            System.out.println(fileName + " : " + i + "%");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(fileName + " selesai diunduh!\n");
    }
}
