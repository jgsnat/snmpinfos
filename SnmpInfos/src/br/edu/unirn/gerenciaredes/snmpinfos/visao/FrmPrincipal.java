package br.edu.unirn.gerenciaredes.snmpinfos.visao;

import br.edu.unirn.gerenciaredes.snmpinfos.ip.IPAddress;
import br.edu.unirn.gerenciaredes.snmpinfos.ip.IpUtil;
import br.edu.unirn.gerenciaredes.snmpinfos.snmp.SNMPManager;
import br.edu.unirn.gerenciaredes.snmpinfos.util.Splash;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmPrincipal extends javax.swing.JFrame {

    private HashMap<String, HashMap<String, String>> perfis;

    public FrmPrincipal() {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) jtDados.getModel();
        model.setNumRows(0);
        model.setColumnCount(0);
        try {
            carregarPerfis();
        } catch (URISyntaxException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar carregar o arquivo de perfis!!!", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtComunidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jbPerfil = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jcbSoValido = new javax.swing.JCheckBox();
        lbQtdValidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SNMP Infos");
        setMinimumSize(new java.awt.Dimension(739, 380));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Comunidade:");

        txtComunidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComunidade.setText("public");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Perfil:");

        jbPerfil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Buscar Informações");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jcbSoValido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbSoValido.setText("Exibir IPs sem informação");

        lbQtdValidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbQtdValidos.setForeground(new java.awt.Color(255, 0, 0));
        lbQtdValidos.setText("0 Computadores Localizados");
        lbQtdValidos.setMaximumSize(new java.awt.Dimension(190, 15));
        lbQtdValidos.setMinimumSize(new java.awt.Dimension(190, 15));

        jtDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtDados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQtdValidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtComunidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jcbSoValido)))))
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtComunidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSoValido))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQtdValidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Splash s = new Splash(this);
            s.rodarThread("carregarDados");
        } catch (InterruptedException ex) {            
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado!!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void carregarDados() throws UnknownHostException, SocketException, IOException {
        DefaultTableModel model = (DefaultTableModel) jtDados.getModel();
        model.setNumRows(0);
        model.setColumnCount(0);

        List<String> colunas = new ArrayList<>(this.perfis.get(jbPerfil.getSelectedItem().toString()).keySet());
        Collections.reverse(colunas);
        model.addColumn("IP");
        model.addColumn("SysName");
        for (String col : colunas) {
            model.addColumn(col);
        }

        String[] linha;
        List<IPAddress> ips = IpUtil.getIpsRedeLocal();
        /*List<IPAddress> ips = new ArrayList<>();
         ips.add(new IPAddress("test.net-snmp.org"));*/

        SNMPManager snmpm = new SNMPManager(txtComunidade.getText());
        snmpm.start();

        for (IPAddress ip : ips) {
            linha = new String[model.getColumnCount()];
            int i = 2;
            boolean temInfo = false;
            String ret;

            linha[0] = ip.toString();
            if (IpUtil.temConexao(ip)) {
                linha[1] = snmpm.getPorOID("1.3.6.1.2.1.1.1.0", ip.toString());

                if (linha[1].equals("")) {
                    linha[1] = "ERRO SNMP";
                    for (; i < model.getColumnCount(); i++) {
                        linha[i] = "";
                    }
                } else {
                    for (String col : colunas) {
                        ret = snmpm.getPorOID(this.perfis.get(jbPerfil.getSelectedItem().toString()).get(col), ip.toString());
                        if (!ret.equals("")) {
                            temInfo = true;
                        }
                        linha[i++] = ret;
                    }
                }
            } else {
                linha[1] = "SEM CONEXAO";
                for (; i < model.getColumnCount(); i++) {
                    linha[i] = "";
                }
            }

            if (temInfo) {
                model.addRow(linha);
            } else if (jcbSoValido.isSelected()) {
                model.addRow(linha);
            }

            lbQtdValidos.setText(model.getRowCount() + " Computadores Localizados");
        }
        
        JOptionPane.showMessageDialog(this, "Sucesso!!!", "", JOptionPane.INFORMATION_MESSAGE);
    }

    private void carregarPerfis() throws URISyntaxException, FileNotFoundException, IOException {
        CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String path = jarFile.getParentFile().getPath() + "\\lista_perfis.txt";

        String linha;
        String[] dados;
        this.perfis = new HashMap<>();

        try (BufferedReader leitor = new BufferedReader((new InputStreamReader(new FileInputStream(path))))) {
            while (leitor.ready()) {
                linha = leitor.readLine().replace(";", " ; ");
                dados = linha.split(";");
                if (!this.perfis.containsKey(dados[0].trim())) {
                    this.perfis.put(dados[0].trim(), new HashMap<String, String>());
                    jbPerfil.addItem(dados[0].trim());
                }
                this.perfis.get(dados[0].trim()).put(dados[1].trim(), dados[2].trim());
            }
        }

        if (this.perfis.isEmpty()) {
            throw new FileNotFoundException("Arquivo de perfis VAZIO!!!");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jbPerfil;
    private javax.swing.JCheckBox jcbSoValido;
    private javax.swing.JTable jtDados;
    private javax.swing.JLabel lbQtdValidos;
    private javax.swing.JTextField txtComunidade;
    // End of variables declaration//GEN-END:variables
}
