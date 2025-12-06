package Praktikum9;

import javax.swing.*;
import java.awt.*;

public class DownloadManagerApp extends JFrame {
    private final JProgressBar[] progressBars;
    private final String[] fileNames = {"File 1", "File 2", "File 3"};
    private final JButton downloadButton;

    public DownloadManagerApp() {
        setTitle("Download Manager App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Judul
        JLabel titleLabel = new JLabel("Download Manager App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Panel utama untuk label + progress bar
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(fileNames.length, 2, 10, 10));
        progressBars = new JProgressBar[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            JLabel fileLabel = new JLabel(fileNames[i]);
            progressBars[i] = new JProgressBar(0, 100);
            progressBars[i].setStringPainted(true);

            centerPanel.add(fileLabel);
            centerPanel.add(progressBars[i]);
        }

        add(centerPanel, BorderLayout.CENTER);

        // Tombol bawah
        JPanel bottomPanel = new JPanel();
        downloadButton = new JButton("Downloading");
        downloadButton.addActionListener(e -> startDownload());
        bottomPanel.add(downloadButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void startDownload() {
        downloadButton.setEnabled(false);

        for (int i = 0; i < fileNames.length; i++) {
            final int index = i;
            new Thread(() -> {
                for (int progress = 0; progress <= 100; progress += 10) {
                    progressBars[index].setValue(progress);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Aktifkan kembali tombol setelah semua thread selesai
                if (index == fileNames.length - 1) {
                    SwingUtilities.invokeLater(() -> downloadButton.setEnabled(true));
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DownloadManagerApp frame = new DownloadManagerApp();
            frame.setVisible(true);
        });
    }
}
