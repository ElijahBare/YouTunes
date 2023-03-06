package github.elijahbare.youtunes;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestSearchResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Main UI class
 */
public class YouTunesUI extends JFrame {
    private JPanel mainPanel;
    private JPanel downloadPanel;
    private JLabel titleLabel;
    public static JTextField urlField;
    private JButton downloadButton;

    public static JPanel resultsPanel;
    private JLabel playlistTitleLabel;
    private JTextField playlistNameField;
    private JButton createPlaylistButton;

    public static JScrollPane scrollPane;

    public YouTunesUI() {
        initUI();
    }

    private void initUI() {
        setTitle("YouTunes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        // Download Panel
        downloadPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Download YouTube Video");
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        urlField = new JTextField(30);
        downloadButton = new JButton("Download");
        downloadPanel.add(titleLabel, BorderLayout.NORTH);
        downloadPanel.add(urlField, BorderLayout.CENTER);
        downloadPanel.add(downloadButton, BorderLayout.SOUTH);

        // Results Panel
        resultsPanel = new JPanel(new BorderLayout());
        playlistTitleLabel = new JLabel("Results");
        playlistTitleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        playlistNameField = new JTextField(20);
        createPlaylistButton = new JButton("Select");
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        resultsPanel.add(scrollPane);
        resultsPanel.add(playlistTitleLabel, BorderLayout.NORTH);
        resultsPanel.add(playlistNameField, BorderLayout.WEST);
        resultsPanel.add(createPlaylistButton, BorderLayout.CENTER);


        // Add Panels to Main Panel
        mainPanel.add(downloadPanel, BorderLayout.NORTH);
        mainPanel.add(resultsPanel, BorderLayout.CENTER);

        //Listeners
        downloadButton.addActionListener(new DownloadListener());

        // Set Main Window Properties
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Entry Point
     */
    public static void main(String[] args) {
        new YouTunesUI();
    }
}

/**
 * Inner class for the Download Button Listener
 */
class DownloadListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        YoutubeDownloader downloader = new YoutubeDownloader();
        RequestSearchResult request = new RequestSearchResult(YouTunesUI.urlField.getText());

        ArrayList<String> videos = new ArrayList<>();

        downloader.search(request).data().videos().forEach(v -> videos.add(v.title()));

        JPanel content = new JPanel();

        for (String vid : videos) {
            System.out.println(vid);
            content.add(new JLabel(vid));
        }

        YouTunesUI.scrollPane.setViewportView(new JPanel().add(content));
    }

}
