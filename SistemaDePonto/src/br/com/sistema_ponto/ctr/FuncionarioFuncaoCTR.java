package br.com.sistema_ponto.ctr;

import br.com.sistema_ponto.dao.ConexaoDAO;
import br.com.sistema_ponto.dao.FuncionarioFuncaoDAO;
import br.com.sistema_ponto.dto.FuncionarioFuncaoDTO;
import java.sql.ResultSet;

public class FuncionarioFuncaoCTR {
    FuncionarioFuncaoDAO funcionarioFuncaoDAO = new FuncionarioFuncaoDAO();

    public FuncionarioFuncaoCTR() {
        
    }

    public String inserirFuncionarioFuncao(FuncionarioFuncaoDTO funcionarioFuncaoDTO) {
        try {
            if (funcionarioFuncaoDAO.inserirFuncionarioFuncao(funcionarioFuncaoDTO)) {
                return "Função associada ao funcionário com sucesso!";
            } else {
                return "Erro ao associar função ao funcionário.";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Erro ao associar função ao funcionário.";
        }
    }

    public String excluirFuncionarioFuncao(FuncionarioFuncaoDTO funcionarioFuncaoDTO) {
        try {
            if (funcionarioFuncaoDAO.excluirFuncionarioFuncao(funcionarioFuncaoDTO)) {
                return "Função desassociada do funcionário com sucesso!";
            } else {
                return "Erro ao desassociar função do funcionário.";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Erro ao desassociar função do funcionário.";
        }
    }

    public ResultSet consultarFuncoesPorFuncionario(int idFuncionario) {
        ResultSet rs = null;

        try {
            rs = funcionarioFuncaoDAO.consultarFuncoesPorFuncionario(idFuncionario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}