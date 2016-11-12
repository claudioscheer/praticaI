package components.panelsListagem;

import com.alee.laf.menu.PopupMenuWay;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.menu.WebPopupMenu;
import com.alee.laf.panel.WebPanel;
import com.alee.managers.hotkey.Hotkey;
import dao.PatAtivoImobilizadoDAO;
import forms.FormPrincipal;
import forms.patrimonio.FormBaixaAtivo;
import forms.patrimonio.FormControleDepreciacoes;
import forms.patrimonio.FormDepreciar;
import forms.patrimonio.FormHistoricoDepreciacoes;
import forms.patrimonio.FormQrCodeAtivoImobilizado;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatAtivoImobilizado;
import model.PatBaixa;
import net.sf.jasperreports.engine.JRException;
import utils.Utils;

public class PanelConsultaAtivoImobilizado extends WebPanel {

    private List<PatAtivoImobilizado> ativosImobilizados;

    private int paginaBuscar;
    private List<PatBaixa> baixas;

    private int indexSelecionado;

    public PanelConsultaAtivoImobilizado() {
        initComponents();

        this.tabelaAtivosImobilizados.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Código", "Descrição", "Categoria", "Marca", "Valor original", "Valor atual"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        this.tabelaAtivosImobilizados.setLoadMore(x -> new LoadAtivosImobilizados().execute());
        this.tabelaAtivosImobilizados.setSortable(true);
        this.ativosImobilizados = new ArrayList<>();

        this.createOpcoesButton();

        new LoadAtivosImobilizados().execute();

        this.txtBuscar.setEventBuscar((e) -> {
            this.paginaBuscar = 0;
            this.ativosImobilizados.clear();
            new LoadAtivosImobilizados().execute();
        });

        this.txtBuscar.addOpcoesBuscar(new String[]{
            "Código",
            "Descrição"
        });

        this.verificaPlaceholderText();
        this.txtBuscar.setEventChangeComboBox(al -> {
            this.verificaPlaceholderText();
        });
    }

    private void createOpcoesButton() {
        WebPopupMenu popupMenuOpcoes = new WebPopupMenu();
        WebMenuItem menuQrCode = new WebMenuItem("QrCode", Hotkey.NUMBER_1);
        menuQrCode.addActionListener((e) -> {
            int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
            if (linhaselecionada < 0) {
                Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
                return;
            }
            FormPrincipal formBloquear = FormPrincipal.getInstance();
            FormQrCodeAtivoImobilizado form = new FormQrCodeAtivoImobilizado();
            form.setFrameBloquear(formBloquear);
            form.setAtivoImobilizado(this.ativosImobilizados.get(linhaselecionada));
            form.setVisible(true);
            formBloquear.setEnabled(false);
        });
        popupMenuOpcoes.add(menuQrCode);

        WebMenuItem menuHistoricoDepreciacao = new WebMenuItem("Histórico depreciação", Hotkey.NUMBER_2);
        menuHistoricoDepreciacao.addActionListener((e) -> {
            int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
            if (linhaselecionada < 0) {
                Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
                return;
            }
            FormPrincipal formBloquear = FormPrincipal.getInstance();
            FormHistoricoDepreciacoes form = new FormHistoricoDepreciacoes();
            form.setFrameBloquear(formBloquear);
            form.setAtivoImobilizado(this.ativosImobilizados.get(linhaselecionada));
            form.setVisible(true);
            formBloquear.setEnabled(false);
        });
        popupMenuOpcoes.add(menuHistoricoDepreciacao);

        WebMenuItem menuBaixa = new WebMenuItem("Dar baixa", Hotkey.NUMBER_3);
        menuBaixa.addActionListener((e) -> {
            int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
            if (linhaselecionada < 0) {
                Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
                return;
            }
            FormBaixaAtivo form = new FormBaixaAtivo();
            form.setAtivoImobilizado(this.ativosImobilizados.get(linhaselecionada));
            form.setVisible(true);
            FormPrincipal.getInstance().setEnabled(false);
        });
        popupMenuOpcoes.add(menuBaixa);
        buttonOpcoes.setPopupMenuWay(PopupMenuWay.aboveStart);
        buttonOpcoes.setPopupMenu(popupMenuOpcoes);

        WebPopupMenu popupMenuManutencao = new WebPopupMenu();
        WebMenuItem menuDepreciar = new WebMenuItem("Depreciar ativos", Hotkey.NUMBER_1);
        menuDepreciar.addActionListener((e) -> {
            FormDepreciar depreciacoes = new FormDepreciar();
            depreciacoes.setVisible(true);
            FormPrincipal.getInstance().setEnabled(false);
        });
        popupMenuManutencao.add(menuDepreciar);

        WebMenuItem menuGerenciarDepreciacoes = new WebMenuItem("Gerenciar depreciação", Hotkey.NUMBER_2);
        menuGerenciarDepreciacoes.addActionListener((e) -> {
            FormControleDepreciacoes depreciacoes = new FormControleDepreciacoes();
            depreciacoes.setVisible(true);
            FormPrincipal.getInstance().setEnabled(false);
        });
        popupMenuManutencao.add(menuGerenciarDepreciacoes);
        buttonManutencoes.setPopupMenuWay(PopupMenuWay.aboveStart);
        buttonManutencoes.setPopupMenu(popupMenuManutencao);

        WebPopupMenu popupMenuRelatorios = new WebPopupMenu();
        WebMenuItem menuRelatorioInventario = new WebMenuItem("Relatório inventário", Hotkey.NUMBER_1);
        menuRelatorioInventario.addActionListener((e) -> {
            try {
                relatorios.patrimonio.RelatoriosPatrimonio.relatorioInventario();
            } catch (JRException ex) {
                utils.Utils.notificacao("Erro ao gerar relatório!", Utils.TipoNotificacao.erro, 0);
            }
        });
        popupMenuRelatorios.add(menuRelatorioInventario);

        WebMenuItem menuRelatorioOutros = new WebMenuItem("Outros", Hotkey.NUMBER_2);
        menuRelatorioOutros.addActionListener((e) -> {
            System.out.println("relatório outros");
        });
        popupMenuRelatorios.add(menuRelatorioOutros);
        buttonRelatorios.setPopupMenuWay(PopupMenuWay.aboveStart);
        buttonRelatorios.setPopupMenu(popupMenuRelatorios);
    }

    public class LoadAtivosImobilizados extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            ativosImobilizados.addAll(new PatAtivoImobilizadoDAO().getAll(paginaBuscar++, txtBuscar.getFiltroSelecionado(), txtBuscar.getText()));
            atualizarTabela(true);
            return null;
        }

        @Override
        public void done() {
        }
    }

    private void verificaPlaceholderText() {
        switch (this.txtBuscar.getFiltroSelecionado()) {
            case 0:
                this.txtBuscar.setPlaceholderText("Digite o código do ativo");
                break;

            case 1:
                this.txtBuscar.setPlaceholderText("Digite a descrição do ativo");
                break;
        }
    }

    public int getIndiceSelecionado() {
        return this.indexSelecionado;
    }

    public void addAtivoImobilizado(PatAtivoImobilizado ativo) {
        ativo = new PatAtivoImobilizadoDAO().get(ativo.getAtivoCodigo());
        this.ativosImobilizados.add(0, ativo);
        DefaultTableModel model = (DefaultTableModel) this.tabelaAtivosImobilizados.getModel();
        model.insertRow(0, ativoToArray(ativo));
        this.tabelaAtivosImobilizados.setModel(model);
    }

    public void removeAtivoImobilizado(int index) {
        this.ativosImobilizados.remove(index);
        DefaultTableModel model = (DefaultTableModel) this.tabelaAtivosImobilizados.getModel();
        model.removeRow(index);
        this.tabelaAtivosImobilizados.setModel(model);
    }

    private void atualizarTabela(boolean limpar) {
        DefaultTableModel model = (DefaultTableModel) this.tabelaAtivosImobilizados.getModel();

        if (limpar) {
            Utils.clearTableModel(model);
        }

        this.ativosImobilizados.forEach(x -> {
            model.addRow(ativoToArray(x));
        });

        this.tabelaAtivosImobilizados.setModel(model);
    }

    private Object[] ativoToArray(PatAtivoImobilizado ativo) {
        Object[] o = new Object[6];
        o[0] = ativo.getAtivoCodigo();
        o[1] = ativo.getAtivoDescricao();
        o[2] = ativo.getEstCategoria().getCategoriaDescricao();
        o[3] = ativo.getEstMarca().getMarcaDescricao();
        o[4] = ativo.getAtivoValorOriginal();
        o[5] = ativo.getAtivoValorAtual();
        return o;
    }

    public void setEvents(ActionListener add, ActionListener edit) {
        buttonAdd.addActionListener(add);
        buttonEditar.addActionListener(edit);
    }

    public PatAtivoImobilizado getAtivoImobilizadoSelecionado() {
        int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
            return null;
        }
        this.indexSelecionado = linhaselecionada;
        return this.ativosImobilizados.get(linhaselecionada);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOpcoes = new javax.swing.JPanel();
        buttonAdd = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
        buttonOpcoes = new com.alee.extended.button.WebSplitButton();
        buttonManutencoes = new com.alee.extended.button.WebSplitButton();
        buttonRelatorios = new com.alee.extended.button.WebSplitButton();
        txtBuscar = new components.TextFieldBuscar();
        tabelaAtivosImobilizados = new components.JTableLoadScroll();

        setMinimumSize(new java.awt.Dimension(565, 496));

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonAdd.setText("Novo");
        buttonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        buttonAdd.setPreferredSize(new java.awt.Dimension(24, 24));

        buttonEditar.setText("Editar");

        buttonOpcoes.setText("Opções");

        buttonManutencoes.setText("Manutenções");

        buttonRelatorios.setText("Relatórios");

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonManutencoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonManutencoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabelaAtivosImobilizados, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaAtivosImobilizados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.extended.button.WebSplitButton buttonManutencoes;
    private com.alee.extended.button.WebSplitButton buttonOpcoes;
    private com.alee.extended.button.WebSplitButton buttonRelatorios;
    private javax.swing.JPanel panelOpcoes;
    private components.JTableLoadScroll tabelaAtivosImobilizados;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
