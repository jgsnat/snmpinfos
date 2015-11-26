package br.edu.unirn.gerenciaredes.snmpinfos.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.lang.reflect.Method;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

public class Splash extends JWindow implements Runnable {

    private String pathImagem = "/br/edu/unirn/gerenciaredes/snmpinfos/arquivos/loader.gif";
    private JFrame form;
    private int closeOperation;
    private Component[] componentes;
    private String nomeMetodo;

    public Splash(JFrame form) throws InterruptedException {
        super(form);
        this.form = form;
        this.componentes = this.form.getComponents();
        this.closeOperation = this.form.getDefaultCloseOperation();
    }

    public Splash(JFrame form, Component[] componentes) throws InterruptedException {
        super(form);
        this.form = form;
        this.componentes = componentes;
        this.closeOperation = this.form.getDefaultCloseOperation();
    }

    private void mostrar() throws InterruptedException {
        Image imagem = Toolkit.getDefaultToolkit().getImage(getClass().getResource(this.pathImagem));
        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(imagem, 0);
        mediaTracker.waitForID(0);

        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        getContentPane().setBackground(new Color(182, 179, 179));

        setSize(imagem.getWidth(null) + 10, imagem.getHeight(null) + 40);
        setLocationRelativeTo(this.form);

        setVisible(true);
    }

    public void fechar() {
        this.form.setDefaultCloseOperation(this.closeOperation);
        for(Component component : this.form.getComponents()) {
            component.setEnabled(true);
        }
        this.dispose();
    }

    public void rodarThread(String nomeMetodo) throws InterruptedException {
        this.nomeMetodo = nomeMetodo;
        this.form.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        for(Component component : this.form.getComponents()) {
            component.setEnabled(false);
        }
        this.mostrar();
        Thread splashThread = new Thread(this, "SplashThread");
        splashThread.start();
    }

    public void run() {
        try {
            this.form.toFront();
            Method m = this.form.getClass().getDeclaredMethod(this.nomeMetodo, null);
            m.invoke(this.form, null);
            this.form.toFront();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado!!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.fechar();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(this.pathImagem)), 5, 30, this);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("Processando...", 15, 20);
    }
}
