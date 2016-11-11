/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enumeraveis.TipoConta;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.CarCapContas;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.Utils;
import model.FlxcxEspecificacoes;
import model.FlxcxOperacoes;

/**
 *
 * @author Diego
 */
public class ExportacaoParaExcel {
    
    private HSSFWorkbook workbook = new HSSFWorkbook();
    private HSSFSheet firstSheet = workbook.createSheet("FluxoCaixa");
    private final FlxcxOperacoesDAO operacoesDao = new FlxcxOperacoesDAO();
    private final FlxcxEspecificacoesDAO especificacaoDAO = new FlxcxEspecificacoesDAO();
    private final CarCapContasDAO contasDAO = new CarCapContasDAO();
    
    public void Exportar(String nomeArquivo, Date dataInicial, Date dataFinal) {
        
        try {
            FileOutputStream arquivo = new FileOutputStream(new File(nomeArquivo));

            //controla linha posicionada
            int linha = 0;
            
            double totalEntradas = 0;
            double totalSaidas = 0;
            
            HSSFRow row = firstSheet.createRow(linha);
            
            Calendar c = Calendar.getInstance();
            
            c.setTime(dataInicial);
            
            row.createCell(0).setCellValue("Data Inicial: ");
            row.createCell(1).setCellValue(dataInicial.getDate() + "/" + dataInicial.getMonth() + "/" + dataInicial.getYear());
            
            
            c.setTime(dataFinal);
            
            row.createCell(3).setCellValue("Data Final: ");
            row.createCell(4).setCellValue(dataFinal.getDate() + "/" + dataFinal.getMonth() + "/" + dataFinal.getYear());
            
            linha += 1;
            row = firstSheet.createRow(linha);
            
            List<FlxcxEspecificacoes> especificacoes = BuscarEspecificoes();
            
            for (FlxcxEspecificacoes especificacao : especificacoes) {

                //Linha alimenta uma nova especificacao
                linha += 1;
                row = firstSheet.createRow(linha);

                //Coluna com a descricao da especificacao
                row.createCell(0).setCellValue(especificacao.getEspDescricao());
                
                int sequenciaOperacao = 0;
                
                for (FlxcxOperacoes operacao : BuscarOperacoes(especificacao.getEspCodigo())) {

                    //Linha alimenta uma nova operacao
                    linha += 1;
                    row = firstSheet.createRow(linha);

                    //codigo sequencial
                    sequenciaOperacao += 1;
                    row.createCell(0).setCellValue(sequenciaOperacao);

                    //Descricao da operacao
                    row.createCell(1).setCellValue(operacao.getOpDescricao());
                    
                    totalEntradas = 0;
                    totalSaidas = 0;
                    for (CarCapContas conta : BuscaContas(operacao.getOpCodigo())) {

                        //Realiza soma
                        if (conta.getContaTipo() == TipoConta.Entrada) {
                            totalEntradas += conta.getContaValorPago();
                        } else {
                            totalSaidas += conta.getContaValorPago();
                        }
                        
                    }

                    //Linha alimenta novo valor
                    row.createCell(3).setCellValue(totalEntradas - totalSaidas);
                }
            }
            
            this.workbook.write(arquivo);
            arquivo.close();
            
        } catch (Exception ex) {
            utils.Utils.notificacao("Não foi possível gerar o arquivo de exportação", Utils.TipoNotificacao.erro, 0);
        }
        
    }
    
    public List<FlxcxEspecificacoes> BuscarEspecificoes() {
        
        return this.especificacaoDAO.ListarTodas();
        
    }
    
    public List<FlxcxOperacoes> BuscarOperacoes(int codigoEspecificacao) {
        
        return this.operacoesDao.BuscaOperacoes(codigoEspecificacao);
        
    }
    
    public List<CarCapContas> BuscaContas(int codigoOperacao) {
        
        return this.contasDAO.BuscarContasOperacao(codigoOperacao);
        
    }
    
}
