package UniDy.UninaDelivery;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class FinestraMenu extends JFrame {

	JPanel parteSxPanel;
	JPanel parteDxPanel;
	JLabel logoPrincipaleImgSxL;
	
	
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
		
		parteSxPanel = new JPanel();
		parteSxPanel.setBackground(new Color(239, 235, 229));
		parteSxPanel.setBounds(0, 0, 385, 561);
		contentPane.add(parteSxPanel);
		parteSxPanel.setLayout(null);
		
		logoPrincipaleImgSxL = new JLabel("");
		logoPrincipaleImgSxL.setHorizontalAlignment(SwingConstants.CENTER);
		logoPrincipaleImgSxL.setIcon(new ImageIcon(FinestraMenu.class.getResource("/Img/SxMenu.jpg")));
		logoPrincipaleImgSxL.setBounds(66, 165, 232, 210);
		parteSxPanel.add(logoPrincipaleImgSxL);
		
		parteDxPanel = new JPanel();
		parteDxPanel.setBackground(new Color(119, 101, 101));
		parteDxPanel.setBounds(383, 0, 401, 561);
		contentPane.add(parteDxPanel);
	}
}
