package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class FinestraMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public FinestraMenu(Hal gestoreApplicazione) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraMenu.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel ParteSx = new JPanel();
		ParteSx.setBackground(new Color(239, 235, 229));
		ParteSx.setBounds(0, 0, 385, 561);
		contentPane.add(ParteSx);
		ParteSx.setLayout(null);
		
		JLabel SxLogo = new JLabel("");
		SxLogo.setHorizontalAlignment(SwingConstants.CENTER);
		SxLogo.setIcon(new ImageIcon(FinestraMenu.class.getResource("/Img/SxMenu.jpg")));
		SxLogo.setBounds(66, 165, 232, 210);
		ParteSx.add(SxLogo);
		
		JPanel ParteDx = new JPanel();
		ParteDx.setBackground(new Color(119, 101, 101));
		ParteDx.setBounds(383, 0, 401, 561);
		contentPane.add(ParteDx);
	}
}
