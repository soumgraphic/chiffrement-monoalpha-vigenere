package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.ChiffrementDeVigenere;
import service.ChiffrementService;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;

public class WindowMain extends JFrame implements java.awt.event.ActionListener {

	private JPanel contentPane;
	private JTextField txtFieldTextAchiffrerOuADechiffrer;
	private JTextField cleCryptageVigenere_txtField;
	private JButton btnChiffrer;
	private JButton btnDechiffrer;
	private JTextPane txtPaneAffichageMotChiffrerOuDechiffrer;
	private JRadioButton substitutionMonoAlpha_RdBtn;
	private JRadioButton vigenere_RdBtn;
	private ButtonGroup buttonGroup;
	ChiffrementService service = new ChiffrementService();
	ChiffrementDeVigenere vigenere = new ChiffrementDeVigenere();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowMain frame = new WindowMain();
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
	public WindowMain() {
		setResizable(false);
		setTitle("Outils de chiffrement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFieldTextAchiffrerOuADechiffrer = new JTextField();
		txtFieldTextAchiffrerOuADechiffrer.setToolTipText("Veuillez entrer le texte \u00E0 chiffrer ou a dechiffrer");
		txtFieldTextAchiffrerOuADechiffrer.setBounds(12, 115, 673, 50);
		contentPane.add(txtFieldTextAchiffrerOuADechiffrer);
		txtFieldTextAchiffrerOuADechiffrer.setColumns(10);
		
		btnChiffrer = new JButton("Chiffrer");
		btnChiffrer.setBounds(12, 178, 284, 41);
		btnChiffrer.addActionListener(this);
		btnChiffrer.setActionCommand("btnChiffrer");
		contentPane.add(btnChiffrer);
		
		JLabel lblAffichageTitreMotChiffrerOuDechiffrer = new JLabel("Affichage Mot Chiffrer ou Dechiffrer ");
		lblAffichageTitreMotChiffrerOuDechiffrer.setBounds(12, 232, 438, 29);
		contentPane.add(lblAffichageTitreMotChiffrerOuDechiffrer);
		
		btnDechiffrer = new JButton("Dechiffrer");
		btnDechiffrer.setBounds(401, 178, 284, 41);
		btnDechiffrer.addActionListener(this);
		btnDechiffrer.setActionCommand("btnDechiffrer");
		contentPane.add(btnDechiffrer);
		
		JLabel lblTitreAffichage = new JLabel("Algorithme de chiffrement et de déchiffrement");
		lblTitreAffichage.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitreAffichage.setBounds(12, 13, 673, 39);
		contentPane.add(lblTitreAffichage);
		
		txtPaneAffichageMotChiffrerOuDechiffrer = new JTextPane();
		txtPaneAffichageMotChiffrerOuDechiffrer.setToolTipText("Affichage Texte chiffrer ou dechiffrer");
		txtPaneAffichageMotChiffrerOuDechiffrer.setEditable(false);
		txtPaneAffichageMotChiffrerOuDechiffrer.setBounds(12, 274, 673, 161);
		contentPane.add(txtPaneAffichageMotChiffrerOuDechiffrer);
		
		cleCryptageVigenere_txtField = new JTextField();
		cleCryptageVigenere_txtField.setToolTipText("Cl\u00E9 de cryptage");
		cleCryptageVigenere_txtField.setBounds(425, 65, 260, 37);
		contentPane.add(cleCryptageVigenere_txtField);
		cleCryptageVigenere_txtField.setColumns(10);
		
		cleCryptageVigenere_txtField.setVisible(false);
		
		substitutionMonoAlpha_RdBtn = new JRadioButton("Substitution mono alphab\u00E9tique");
		substitutionMonoAlpha_RdBtn.setBounds(12, 71, 217, 25);
		substitutionMonoAlpha_RdBtn.addActionListener(this);
		substitutionMonoAlpha_RdBtn.setActionCommand("substitutionMonoAlpha_RdBtn");
		contentPane.add(substitutionMonoAlpha_RdBtn);
		
		vigenere_RdBtn = new JRadioButton("Vigen\u00E8re");
		vigenere_RdBtn.setBounds(233, 71, 127, 25);
		vigenere_RdBtn.addActionListener(this);
		vigenere_RdBtn.setActionCommand("vigenere_RdBtn");
		contentPane.add(vigenere_RdBtn);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(substitutionMonoAlpha_RdBtn);
		buttonGroup.add(vigenere_RdBtn);
		
		//substitutionMonoAlpha_RdBtn.setSelected(true);
		
		String getTextRdBtnSelected = checkRadioBtnInRadioGroupIsSelected(buttonGroup);
		if (getTextRdBtnSelected.equals(substitutionMonoAlpha_RdBtn.getText())) {
			System.out.println("Bonjour mono");
		}else if (getTextRdBtnSelected.equals(vigenere_RdBtn.getText())) {
			System.out.println("Bonjour vigi");
		}
	
	}
	
	public void actionPerformed(java.awt.event.ActionEvent e) {
		String getTextRdBtnSelected = checkRadioBtnInRadioGroupIsSelected(buttonGroup);
		if ("btnChiffrer".equals(e.getActionCommand())) {
			if (getTextRdBtnSelected.equals("")) {
				JOptionPane.showMessageDialog(this, "Veuillez selectionner un chiffrement mono alphabétique ou viginere ", "Erreur", 0);
				return;
			}
			if (getTextRdBtnSelected.equals(substitutionMonoAlpha_RdBtn.getText())) {
				chiffrementMessageMonoAlpha();
			}else if (getTextRdBtnSelected.equals(vigenere_RdBtn.getText())) {
				chiffrementMessageviginere();
			}
		}else if ("btnDechiffrer".equals(e.getActionCommand())) {
			if (getTextRdBtnSelected.equals("")) {
				JOptionPane.showMessageDialog(this, "Veuillez selectionner un chiffrement mono alphabétique ou viginere ", "Erreur", 0);
				return;
			}
			if (getTextRdBtnSelected.equals(substitutionMonoAlpha_RdBtn.getText())) {
				dechiffrementMessageMonoAlpha();
			}else if (getTextRdBtnSelected.equals(vigenere_RdBtn.getText())) {
				dechiffrementMessageviginere();
			}
		}else if ("substitutionMonoAlpha_RdBtn".equals(e.getActionCommand())) {
			cleCryptageVigenere_txtField.setText("");
			cleCryptageVigenere_txtField.setVisible(false);
		}else if ("vigenere_RdBtn".equals(e.getActionCommand())) {
			cleCryptageVigenere_txtField.setVisible(true);
		}
	}
	
	public void chiffrementMessageMonoAlpha() {
		String textAChif_Dechif = txtFieldTextAchiffrerOuADechiffrer.getText().toString();
		if (textAChif_Dechif.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un message à chiffrer ", "Erreur", 0);
			return;
		}
		if ((!textAChif_Dechif.isEmpty()) && (textAChif_Dechif != "")) {
			txtPaneAffichageMotChiffrerOuDechiffrer.setText(service.algoChiffrementTexte(textAChif_Dechif.toLowerCase()));
		}
	}
	
	public void dechiffrementMessageMonoAlpha() {
		String textAChif_Dechif = txtFieldTextAchiffrerOuADechiffrer.getText().toString();
		if (textAChif_Dechif.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un message à déchiffrer ", "Erreur", 0);
			return;
		}
		if ((!textAChif_Dechif.isEmpty()) && (textAChif_Dechif != "")) {
			txtPaneAffichageMotChiffrerOuDechiffrer.setText(service.algoDechiffrementTexte(textAChif_Dechif.toLowerCase()));
		}
	}
	
	public void chiffrementMessageviginere() {
		String textAChif_Dechif = txtFieldTextAchiffrerOuADechiffrer.getText().toString();
		String cleCrypt = cleCryptageVigenere_txtField.getText().toString();
		if (textAChif_Dechif.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un message à chiffrer ", "Erreur", 0);
			return;
		}
		if (cleCrypt.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez mettre une clé de cryptage pour chiffrer le message ", "Erreur", 0);
			return;
		}
		if (textAChif_Dechif.equals(cleCrypt)) {
			JOptionPane.showMessageDialog(this, "Pour une sécurité absolue, La clé de cryptage doit être différent du message ", "Erreur", 0);
			return;
		}
		if ((!textAChif_Dechif.isEmpty()) && (textAChif_Dechif != "") || (!cleCrypt.isEmpty()) && (cleCrypt != "")) {
			txtPaneAffichageMotChiffrerOuDechiffrer.setText(vigenere.chiffrerAvecVigenere(textAChif_Dechif, cleCrypt));
		}
	}
	
	public void dechiffrementMessageviginere() {
		String textAChif_Dechif = txtFieldTextAchiffrerOuADechiffrer.getText().toString();
		String cleCrypt = cleCryptageVigenere_txtField.getText().toString();
		if (textAChif_Dechif.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un message à déchiffrer ", "Erreur", 0);
			return;
		}
		if (cleCrypt.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez mettre une clé de decryptage pour déchiffrer le message ", "Erreur", 0);
			return;
		}
		if ((!textAChif_Dechif.isEmpty()) && (textAChif_Dechif != "") || (!cleCrypt.isEmpty()) && (cleCrypt != "")) {
			txtPaneAffichageMotChiffrerOuDechiffrer.setText(vigenere.dechiffrerAvecVigenere(textAChif_Dechif, cleCrypt));
		}
	}
	
	String checkRadioBtnInRadioGroupIsSelected(ButtonGroup buttonGroup){
		Enumeration<AbstractButton> bg = buttonGroup.getElements();
		String textRadioButtonSelected = "";
		while (bg.hasMoreElements()) {
			JRadioButton jRadioButton = (JRadioButton) bg.nextElement();
			if (jRadioButton.isSelected()) {
				textRadioButtonSelected = jRadioButton.getText();
			}
		}
		return textRadioButtonSelected;
	}
	
}
