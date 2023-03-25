package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {

	public static void main(String[] args) {
		createWindow();
	}

	private static void createWindow() {
		JFrame frame = new JFrame("PDF Splitter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI(frame);
		frame.setSize(560, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void createUI(final JFrame frame) {
		JPanel panel = new JPanel();
		LayoutManager layout = new FlowLayout();
		panel.setLayout(layout);
		JButton button = new JButton("Select PDF File");
		final JLabel label = new JLabel();

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("C:");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
				fileChooser.setFileFilter(filter);
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String response = splitPdf(file.getAbsolutePath());
					label.setText(response);
				} else {
					label.setText("Cancelled");
				}
			}
		});

		panel.add(button);
		panel.add(label);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
	public static String splitPdf(String filePath) {
		if (filePath != null && filePath.length() > 0) {
			try {
				return PdfSplitterTool.splitPdf(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				return "Couldn't Split the PDF as it was not found";
			}
		}
		return "";
	}
}
