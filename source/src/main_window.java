import java.awt.Desktop;
import java.awt.EventQueue;
import java.util.Random;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;

@SuppressWarnings("serial")
public class main_window extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
	
	public static String pwdGen(String chars, int length)
	{
		  Random randomize = new Random();
		  StringBuilder nBuilder = new StringBuilder();
		  
		  for (int i=0; i<length; i++)
		  {
			  nBuilder.append(chars.charAt(randomize.nextInt(chars.length())));
		  }
		  return nBuilder.toString();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_window frame = new main_window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		

	/**
	 * Create the frame.
	 */
	public main_window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 475);
		setTitle("Password Gen");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea passwordArea = new JTextArea();
		passwordArea.setForeground(Color.BLACK);
		passwordArea.setBounds(20, 67, 229, 80);
		passwordArea.setLineWrap(true);
		passwordArea.setWrapStyleWord(true);
		Border borderpw = BorderFactory.createLineBorder(Color.GRAY);
		passwordArea.setBorder(BorderFactory.createCompoundBorder(borderpw, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		contentPane.add(passwordArea);
		
		JScrollPane scrollPane = new JScrollPane(passwordArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 67, 229, 80);
		contentPane.add(scrollPane);
		
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGenerate.setContentAreaFilled(false);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	
				passwordArea.setText("");
				passwordArea.append(pwdGen(chars, 16));

			}
		});
		btnGenerate.setBounds(20, 169, 100, 23);
		contentPane.add(btnGenerate);
		
		btnGenerate.setFocusable(false);
		
		JCheckBox cboxSymbols = new JCheckBox("Include Symbols");
		cboxSymbols.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	
				if(cboxSymbols.isSelected())
				{
					chars = "abcdefghijklmnopqrstuvwxyz1234567890|!£$%&()=?^*[{}]§#@-.,+_:;";
				}
				else
				{
					chars = "abcdefghijklmnopqrstuvwxyz1234567890";
				}

			}
		});
		cboxSymbols.setBounds(20, 237, 126, 22);
		contentPane.add(cboxSymbols);
		
		cboxSymbols.setFocusable(false);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCopy.setContentAreaFilled(false);
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringSelection passwordSelection = new StringSelection (passwordArea.getText());
				Clipboard textCb = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				textCb.setContents(passwordSelection, null);
			}
		});
		btnCopy.setBounds(149, 169, 100, 23);
		contentPane.add(btnCopy);
		
		btnCopy.setFocusable(false);
		
		// Do not remove this
		JEditorPane watermark = new JEditorPane();
		watermark.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                    System.out.println(e.getURL());
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
		watermark.setBackground(new Color(240, 240, 240));
		watermark.setEditable(false);
		watermark.setBounds(63, 399, 164, 20);
		watermark.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		String emjoiWM = new String(Character.toChars(0x2764));
		watermark.setText("<html><a href='https://t.me/nheatyon'>"+ emjoiWM + " Made by @nheatyon</a></html");
		contentPane.add(watermark);
		//
		
		JCheckBox chckbxIncludeUppercaseCharacters = new JCheckBox("Include Uppercase Characters");
		chckbxIncludeUppercaseCharacters.setEnabled(false);
		chckbxIncludeUppercaseCharacters.setFocusable(false);
		chckbxIncludeUppercaseCharacters.setBounds(20, 212, 207, 22);
		contentPane.add(chckbxIncludeUppercaseCharacters);
		
		
		
		
	}
}
