/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import dao.EstProdutoDAO;
import forms.busca.FormBuscaCategoria;
import forms.busca.FormBuscaMarca;
import forms.busca.FormBuscaUnidadeMedida;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.EstCategoria;
import model.EstMarca;
import model.EstProduto;
import model.EstUnidadeMedida;

/**
 *
 * @author Anderson
 */
public class FormMateriais extends javax.swing.JFrame {

    private EstProdutoDAO prodDao = new EstProdutoDAO();

    public FormMateriais(java.awt.Frame parent, boolean modal) {
        initComponents();
        //abre o form para buscar uma categoria
        FormBuscaCategoria formCategoria = new FormBuscaCategoria();
        formCategoria.buscarCategorias();
        formCategoria.setFrameBloquear(FormPrincipal.getInstance());
        this.edtCategoria.setFrame(formCategoria);

        //abre o form para buscar uma marca
        FormBuscaMarca frm = new FormBuscaMarca();
        frm.setFrameBloquear(FormPrincipal.getInstance());
        this.edtMarca.setFrame(frm);

        //busca un medida
        FormBuscaUnidadeMedida formUn = new FormBuscaUnidadeMedida();
        formUn.setFrameBloquear(FormPrincipal.getInstance());
        this.edtUnMedida.setFrame(formUn);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMateriais = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnInserir = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        edtDescricao = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtCategoria = new components.TextFieldFK();
        edtMarca = new components.TextFieldFK();
        edtUnMedida = new components.TextFieldFK();
        btnLimpar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        unMedidas = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de Materiais");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                AoAbrirTela(evt);
            }
        });

        tblMateriais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição", "Categoria", "Marca", "Un. Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMateriais);
        if (tblMateriais.getColumnModel().getColumnCount() > 0) {
            tblMateriais.getColumnModel().getColumn(0).setResizable(false);
            tblMateriais.getColumnModel().getColumn(1).setResizable(false);
            tblMateriais.getColumnModel().getColumn(2).setResizable(false);
            tblMateriais.getColumnModel().getColumn(3).setResizable(false);
            tblMateriais.getColumnModel().getColumn(4).setResizable(false);
        }

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atualizar.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/deletar.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        btnInserir.setText("Inserir");
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/anterior.png"))); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel1.setText("Descrição");

        edtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AoDigitarTecla(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Categoria");

        jLabel3.setText("Marca");

        jLabel4.setText("Un.Medida");

        edtCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCategoriaActionPerformed(evt);
            }
        });

        edtUnMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtUnMedidaActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jMenu3.setText("Cadastros");

        unMedidas.setText("Unidades de Medida");
        unMedidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unMedidasActionPerformed(evt);
            }
        });
        jMenu3.add(unMedidas);

        jMenuItem3.setText("Marcas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Categorias");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Relatórios");

        jMenuItem1.setText("Relatório de Produtos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInserir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addGap(6, 6, 6))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtUnMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(edtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(edtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtUnMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInserir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void unMedidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unMedidasActionPerformed
        // TODO add your handling code here:
        FormListaUnidadeMedida lista = new FormListaUnidadeMedida(null, rootPaneCheckingEnabled);
        lista.setVisible(true);
    }//GEN-LAST:event_unMedidasActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        FormListaMarca form = new FormListaMarca(null, rootPaneCheckingEnabled);
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        FormListaCategoria cat = new FormListaCategoria(null, rootPaneCheckingEnabled);
        cat.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void AoAbrirTela(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_AoAbrirTela
        // TODO add your handling code here:
        preencheTabela();
    }//GEN-LAST:event_AoAbrirTela

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        // TODO add your handling code here:
        FormManutencaoMateriais manut = new FormManutencaoMateriais(null, rootPaneCheckingEnabled);
        manut.setVisible(true);
        preencheTabela();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (tblMateriais.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum Registro Selecionado na Tabela");
            return;
        }
        EstProduto produto = new EstProduto();
        produto.setProdutoId(Integer.parseInt((String) tblMateriais.getValueAt(tblMateriais.getSelectedRow(), 0)));

        produto.setProdutoDescricao((String) tblMateriais.getValueAt(tblMateriais.getSelectedRow(), 1));
        produto.setEstMarca(produto.getEstMarca());
        FormManutencaoMateriais manut = new FormManutencaoMateriais(null, rootPaneCheckingEnabled, produto);
        manut.setVisible(true);
        preencheTabela();

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (tblMateriais.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum Registro Selecionado na Tabela");
            return;
        }
        DefaultTableModel modelo = (DefaultTableModel) tblMateriais.getModel();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Deseja Excluir o Registro Selecionado?", "Atenção!", dialogButton);
        if (dialogResult == 0) {
            //sim           
            String data = (String) tblMateriais.getValueAt(tblMateriais.getSelectedRow(), 0);
            int id = Integer.parseInt(data);
            EstProduto produto = new EstProduto();
            if (prodDao.delete(id)) {
                JOptionPane.showMessageDialog(null, "Registro removido com Sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            modelo.removeRow(tblMateriais.getSelectedRow());
        } else {
            //não
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        EstProdutoDAO dao = new EstProdutoDAO();
        dao.GerarRelatorioProdutos();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        EstCategoria categoria = null;
        EstMarca marca = null;
        EstUnidadeMedida unMedida = null;
        String descricao = "";

        if (!edtDescricao.getText().isEmpty()) {
            descricao = edtDescricao.getText();
        }
        if (!edtCategoria.getText().isEmpty()) {
            categoria = (EstCategoria) edtCategoria.getValue();
        }
        if (!edtMarca.getText().isEmpty()) {
            marca = (EstMarca) edtMarca.getValue();
        }
        if (!edtUnMedida.getText().isEmpty()) {
            unMedida = (EstUnidadeMedida) edtUnMedida.getValue();
        }
        preencheTabelaFiltro(descricao,categoria,unMedida,marca);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void edtCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtCategoriaActionPerformed

    private void edtUnMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtUnMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtUnMedidaActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:        
        edtDescricao.setText("");
        edtCategoria.setText("");
        //edtCategoria.setValue(null);
        edtMarca.setText("");
        //edtMarca.setValue(null);
        edtUnMedida.setText("");
        //edtUnMedida.setValue(null);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void AoDigitarTecla(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AoDigitarTecla
        // TODO add your handling code here:
        
    }//GEN-LAST:event_AoDigitarTecla

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormMateriais dialog = new FormMateriais(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void preencheTabela() {//listar dados na jtable
        DefaultTableModel modelo = (DefaultTableModel) tblMateriais.getModel();
        modelo.setNumRows(0);

        //semelhante ao foreach
        for (EstProduto prd : prodDao.getAll()) {
            modelo.addRow(new String[]{
                "" + prd.getProdutoId(),
                prd.getProdutoDescricao(),
                prd.getEstCategoria().getCategoriaDescricao(),
                prd.getEstMarca().getMarcaDescricao(),
                prd.getEstUnidadeMedida().getUnidadeMedidaDescricao(),});
        }
    }

    private void preencheTabelaFiltro(String descricao, EstCategoria categoria, EstUnidadeMedida unMedida, EstMarca marca) {//listar dados na jtable
        DefaultTableModel modelo = (DefaultTableModel) tblMateriais.getModel();
        modelo.setNumRows(0);

        //semelhante ao foreach
        for (EstProduto prd : prodDao.buscarProdutos(descricao, categoria, unMedida, marca)) {
            modelo.addRow(new String[]{
                "" + prd.getProdutoId(),
                prd.getProdutoDescricao(),
                prd.getEstCategoria().getCategoriaDescricao(),
                prd.getEstMarca().getMarcaDescricao(),
                prd.getEstUnidadeMedida().getUnidadeMedidaDescricao(),});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnVoltar;
    private components.TextFieldFK edtCategoria;
    private javax.swing.JTextField edtDescricao;
    private components.TextFieldFK edtMarca;
    private components.TextFieldFK edtUnMedida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMateriais;
    private javax.swing.JMenuItem unMedidas;
    // End of variables declaration//GEN-END:variables
}
