package br.edu.unirn.gerenciaredes.snmpinfos.visao;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();

        FrmPrincipal mainView = new FrmPrincipal();
        mainView.setLocation((tela.width - mainView.getSize().width) / 2, (tela.height - mainView.getSize().height) / 2);
        mainView.setVisible(true);
    }    
}
