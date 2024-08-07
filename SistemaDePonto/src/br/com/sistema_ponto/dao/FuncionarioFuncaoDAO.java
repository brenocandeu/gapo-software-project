package br.com.sistema_ponto.dao;

import br.com.sistema_ponto.dto.FuncionarioFuncaoDTO;
import java.sql.*;

public class FuncionarioFuncaoDAO {
    public FuncionarioFuncaoDAO(){
    }
    
    private Statement stmt = null;

    public boolean inserirFuncionarioFuncao(FuncionarioFuncaoDTO funcionarioFuncaoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "INSERT INTO funcionario_funcao (id_funcionario, id_funcao) VALUES ("
                    + funcionarioFuncaoDTO.getId_funcionario() + ", "
                    + funcionarioFuncaoDTO.getId_funcao() + ")";
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public boolean excluirFuncionarioFuncao(FuncionarioFuncaoDTO funcionarioFuncaoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "DELETE FROM funcionario_funcao WHERE id_funcionario = "
                    + funcionarioFuncaoDTO.getId_funcionario() + " AND id_funcao = "
                    + funcionarioFuncaoDTO.getId_funcao();
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public ResultSet consultarFuncoesPorFuncionario(int idFuncionario) {
        ResultSet rs = null;
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "SELECT f.id_funcao, f.nome_funcao FROM funcao f "
                    + "INNER JOIN funcionario_funcao ff ON f.id_funcao = ff.id_funcao "
                    + "WHERE ff.id_funcionario = " + idFuncionario;
            rs = stmt.executeQuery(comando.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}