package br.com.sistema_ponto.view;

import br.com.sistema_ponto.ctr.FuncionarioCTR;
import br.com.sistema_ponto.dto.FuncionariosDTO;
import br.com.sistema_ponto.dto.FuncaoDTO;
import br.com.sistema_ponto.ctr.FuncaoCTR;
import br.com.sistema_ponto.ctr.FuncionarioFuncaoCTR;
import br.com.sistema_ponto.dto.FuncionarioFuncaoDTO;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class FuncionarioFuncaoVIEW extends javax.swing.JInternalFrame {
    
    FuncionariosDTO funcionariosDTO = new FuncionariosDTO();
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();
    FuncaoDTO funcaoDTO = new FuncaoDTO();
    FuncaoCTR funcaoCTR = new FuncaoCTR();
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_funcionarios;
    DefaultTableModel modelo_jtl_consultar_funcao;
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void setupListeners() {
        jtl_consultar_funcionarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jtl_consultar_funcionarios.getSelectedRow();
                if (selectedRow != -1) {
                    int idFuncionario = Integer.parseInt(jtl_consultar_funcionarios.getValueAt(selectedRow, 2).toString());
                    atualizarTabelaFuncoesAssociadas(idFuncionario);
                }
            }
        });
    }

    public FuncionarioFuncaoVIEW() {
        initComponents();
        btnAddFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFuncActionPerformed(evt);
            }
        });
        btnRemoveFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFuncActionPerformed(evt);
            }
        });
        modelo_jtl_consultar_funcionarios = (DefaultTableModel) jtl_consultar_funcionarios.getModel();
        modelo_jtl_consultar_funcao = (DefaultTableModel) jtl_consultar_funcao.getModel();
        if (jtl_consultar_funcionarios.getRowCount() > 0) {
            int idFuncionario = Integer.parseInt(jtl_consultar_funcionarios.getValueAt(0, 2).toString());
            atualizarTabelaFuncoesAssociadas(idFuncionario);
        }
        setupListeners(); // Certifique-se de chamar setupListeners
    }
    
    public void preencheTabelaFuncionarios(String nome_funcionario){
        try{
            modelo_jtl_consultar_funcionarios.setNumRows(0);
            funcionariosDTO.setNome_funcionario(nome_funcionario);
            rs = funcionarioCTR.consultarFuncionarios(funcionariosDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_funcionarios.addRow(new Object[]{
                    rs.getString("n_folha"),
                    rs.getString("nome_funcionario"),
                    rs.getString("id_funcionario"),
                });
            }
            modelo_jtl_consultar_funcionarios.fireTableDataChanged();
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcionarioCTR.CloseDB();
        }
    }
    
    public void preencheTabelaFuncao(String nome_funcao){
        try{
            modelo_jtl_consultar_funcao.setNumRows(0);
            funcaoDTO.setNome_funcao(nome_funcao);
            rs = funcaoCTR.consultarFuncao(funcaoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_funcao.addRow(new Object[]{
                    rs.getString("id_funcao"),
                    rs.getString("nome_funcao"),
                });
            }
            modelo_jtl_consultar_funcao.fireTableDataChanged();
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcaoCTR.CloseDB();
        }
    }
    
    private boolean isFuncaoAlreadyAssigned(int idFuncionario, int idFuncao) {
        try {
            FuncionarioFuncaoCTR funcionarioFuncaoCTR = new FuncionarioFuncaoCTR();
            ResultSet rs = funcionarioFuncaoCTR.consultarFuncoesPorFuncionario(idFuncionario);
            while (rs.next()) {
                if (rs.getInt("id_funcao") == idFuncao) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se a função já está associada: " + e.getMessage());
        }
        return false;
    }
    
    private void atualizarTabelaFuncoesAssociadas(int idFuncionario) {
        try {
            DefaultTableModel modelo_jtl_funcoes_associadas = (DefaultTableModel) jtl_funcoes_associadas.getModel();
            modelo_jtl_funcoes_associadas.setNumRows(0);
            FuncionarioFuncaoCTR funcionarioFuncaoCTR = new FuncionarioFuncaoCTR();
            ResultSet rs = funcionarioFuncaoCTR.consultarFuncoesPorFuncionario(idFuncionario);
            while (rs.next()) {
                modelo_jtl_funcoes_associadas.addRow(new Object[]{
                    rs.getInt("id_funcao"),
                    rs.getString("nome_funcao")
                });
            }
            modelo_jtl_funcoes_associadas.fireTableDataChanged();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar tabela de funções associadas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pesquisa_nome_func = new javax.swing.JTextField();
        btnPesquisaFunc = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtl_consultar_funcionarios = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pesquisa_nome_f = new javax.swing.JTextField();
        btnPesquisaF = new javax.swing.JButton();
        btnAddFunc = new javax.swing.JButton();
        btnRemoveFunc = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtl_consultar_funcao = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtl_funcoes_associadas = new javax.swing.JTable();
        btnSairF = new javax.swing.JToggleButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Funções");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Funcionarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nome:");

        btnPesquisaFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/pesquisar.png"))); // NOI18N
        btnPesquisaFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaFuncActionPerformed(evt);
            }
        });

        jtl_consultar_funcionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Folha", "Nome", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jtl_consultar_funcionarios);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pesquisa_nome_func)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisaFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pesquisa_nome_func, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaFunc))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Funções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Funções:");

        btnPesquisaF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/pesquisar.png"))); // NOI18N
        btnPesquisaF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaFActionPerformed(evt);
            }
        });

        btnAddFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/novo.png"))); // NOI18N
        btnAddFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFuncActionPerformed(evt);
            }
        });

        btnRemoveFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/excluir.png"))); // NOI18N
        btnRemoveFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFuncActionPerformed(evt);
            }
        });

        jtl_consultar_funcao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jtl_consultar_funcao);

        jtl_funcoes_associadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jtl_funcoes_associadas);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddFunc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveFunc)
                .addGap(131, 131, 131))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pesquisa_nome_f, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisaF, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pesquisa_nome_f, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaF))
                .addGap(134, 134, 134)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFunc)
                    .addComponent(btnRemoveFunc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(158, Short.MAX_VALUE)))
        );

        btnSairF.setText("Sair");
        btnSairF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(335, 335, 335)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSairF, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSairF)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisaFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaFuncActionPerformed
        preencheTabelaFuncionarios("");
    }//GEN-LAST:event_btnPesquisaFuncActionPerformed

    private void btnPesquisaFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaFActionPerformed
        preencheTabelaFuncao("");
    }//GEN-LAST:event_btnPesquisaFActionPerformed

    private void btnAddFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFuncActionPerformed
        int selectedFuncionarioRow = jtl_consultar_funcionarios.getSelectedRow();
        int selectedFuncaoRow = jtl_consultar_funcao.getSelectedRow();

        if (selectedFuncionarioRow != -1 && selectedFuncaoRow != -1) {
            int idFuncionario = Integer.parseInt(jtl_consultar_funcionarios.getValueAt(selectedFuncionarioRow, 2).toString());
            int idFuncao = Integer.parseInt(jtl_consultar_funcao.getValueAt(selectedFuncaoRow, 0).toString());

            try {
                FuncionarioFuncaoDTO funcionarioFuncaoDTO = new FuncionarioFuncaoDTO();
                funcionarioFuncaoDTO.setId_funcionario(idFuncionario);
                funcionarioFuncaoDTO.setId_funcao(idFuncao);

                FuncionarioFuncaoCTR funcionarioFuncaoCTR = new FuncionarioFuncaoCTR();
                if (!isFuncaoAlreadyAssigned(idFuncionario, idFuncao)) {
                    String result = funcionarioFuncaoCTR.inserirFuncionarioFuncao(funcionarioFuncaoDTO);
                    JOptionPane.showMessageDialog(this, result);
                    atualizarTabelaFuncoesAssociadas(idFuncionario);
                }
                
//                if(isFuncaoAlreadyAssigned(idFuncionario, idFuncao)){
//                    JOptionPane.showMessageDialog(this, "Esta função já está associada a este funcionário.");
//                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao associar função ao funcionário: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um funcionário e uma função.");
        }
    }//GEN-LAST:event_btnAddFuncActionPerformed

    private void btnRemoveFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFuncActionPerformed
        int selectedFuncionarioRow = jtl_consultar_funcionarios.getSelectedRow();
        int selectedFuncaoRow = jtl_funcoes_associadas.getSelectedRow();  // Assume que há uma tabela para funções associadas

        if (selectedFuncionarioRow != -1 && selectedFuncaoRow != -1) {
            int idFuncionario = Integer.parseInt(jtl_consultar_funcionarios.getValueAt(selectedFuncionarioRow, 2).toString());
            int idFuncao = Integer.parseInt(jtl_funcoes_associadas.getValueAt(selectedFuncaoRow, 0).toString());

            try {
                FuncionarioFuncaoDTO funcionarioFuncaoDTO = new FuncionarioFuncaoDTO();
                funcionarioFuncaoDTO.setId_funcionario(idFuncionario);
                funcionarioFuncaoDTO.setId_funcao(idFuncao);

                FuncionarioFuncaoCTR funcionarioFuncaoCTR = new FuncionarioFuncaoCTR();
                String result = funcionarioFuncaoCTR.excluirFuncionarioFuncao(funcionarioFuncaoDTO);
                JOptionPane.showMessageDialog(this, result);
                atualizarTabelaFuncoesAssociadas(idFuncionario); // Atualiza a tabela de funções associadas
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao remover função do funcionário: " + e.getMessage());
            }
        } 
//        else {
//            JOptionPane.showMessageDialog(this, "Por favor, selecione um funcionário e uma função associada para remover.");
//        }
    }//GEN-LAST:event_btnRemoveFuncActionPerformed

    private void btnSairFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairFActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFunc;
    private javax.swing.JButton btnPesquisaF;
    private javax.swing.JButton btnPesquisaFunc;
    private javax.swing.JButton btnRemoveFunc;
    private javax.swing.JToggleButton btnSairF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jtl_consultar_funcao;
    private javax.swing.JTable jtl_consultar_funcionarios;
    private javax.swing.JTable jtl_funcoes_associadas;
    private javax.swing.JTextField pesquisa_nome_f;
    private javax.swing.JTextField pesquisa_nome_func;
    // End of variables declaration//GEN-END:variables
}
