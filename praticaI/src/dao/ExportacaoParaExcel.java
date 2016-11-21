/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enumeraveis.LogTipo;
import enumeraveis.TipoConta;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private final HSSFWorkbook workbook = new HSSFWorkbook();
    private final HSSFSheet firstSheet = workbook.createSheet("FluxoCaixa");
    private final FlxcxOperacoesDAO operacoesDao = new FlxcxOperacoesDAO();
    private final FlxcxEspecificacoesDAO especificacaoDAO = new FlxcxEspecificacoesDAO();
    private final CarCapContasDAO contasDAO = new CarCapContasDAO();

    public void Exportar(String nomeArquivo, Date dataInicial, Date dataFinal, int filtroData) {

        try {
            FileOutputStream arquivo = new FileOutputStream(new File(nomeArquivo));

            //controla linha posicionada
            int linha = 0;

            HSSFRow row = firstSheet.createRow(linha);

            row.createCell(0).setCellValue("Data Inicial: ");
            row.createCell(1).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(dataInicial));

            row.createCell(3).setCellValue("Data Final: ");
            row.createCell(4).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(dataFinal));

            linha += 1;
            row = firstSheet.createRow(linha);

            int coluna = 3;

            ArrayList<String> colunasExcel = RetornaColunas(filtroData, dataInicial, dataFinal);

            for (String item : colunasExcel) {

                row.createCell(coluna).setCellValue(item);

                coluna += 1;
            }

            this.ExportacaoMensal(colunasExcel, dataInicial, dataFinal, row, linha);

            this.workbook.write(arquivo);
            arquivo.close();

            utils.Utils.notificacao("Exportação concluída com sucesso", Utils.TipoNotificacao.ok, 0);

        } catch (Exception ex) {
            utils.Utils.notificacao("Não foi possível gerar o arquivo de exportação", Utils.TipoNotificacao.erro, 0);
            Utils.log("Falha ao gerar relatório Excel", ex.toString(),LogTipo.ERRO);
            
        }

    }

    public List<FlxcxEspecificacoes> BuscarEspecificoes() {

        return this.especificacaoDAO.ListarTodas();

    }

    public List<FlxcxOperacoes> BuscarOperacoes(int codigoEspecificacao) {

        return this.operacoesDao.BuscaOperacoes(codigoEspecificacao);

    }

    public List<CarCapContas> BuscaContas(int codigoOperacao, Date dataInicial, Date dataFinal) {

        return this.contasDAO.BuscarContasOperacao(codigoOperacao, dataInicial, dataFinal);

    }

    public ArrayList<String> RetornaColunas(int posicao, Date dataInicial, Date dataFinal) {

        ArrayList<String> colunas = new ArrayList<String>();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf;

        switch (posicao) {

            case 0:

                sdf = new SimpleDateFormat("yyyy");

                long data = (dataFinal.getTime() - dataInicial.getTime()) + 3600000;

                long dias = (data / 86400000L);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataInicial);

                for (int i = 0; i <= dias; i++) { //adiciona coluna de dias.

                    if (i == 0) {
                        sdf = new SimpleDateFormat("dd/MM/yyyy");
                        colunas.add(sdf.format(calendar.getTime()));
                    } else {
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        sdf = new SimpleDateFormat("dd/MM/yyyy");
                        colunas.add(sdf.format(calendar.getTime()));
                    }

                }

                break;

            case 1: //Mensal

                colunas.add("Janeiro");
                colunas.add("Fevereiro");
                colunas.add("Março");
                colunas.add("Abril");
                colunas.add("Maio");
                colunas.add("Junho");
                colunas.add("Julho");
                colunas.add("Agosto");
                colunas.add("Setembro");
                colunas.add("Outubro");
                colunas.add("Novembro");
                colunas.add("Dezembro");

                break;

            case 2: //Anual

                sdf = new SimpleDateFormat("yyyy");
                int anoInicio = Integer.valueOf(sdf.format(dataInicial));
                int anoFim = Integer.valueOf(sdf.format(BuscaMaiorData(dataInicial, dataFinal)));

                int anoDataFinal = Integer.valueOf(sdf.format(dataFinal));

                if (anoDataFinal > anoFim) {

                    anoFim = anoDataFinal;

                }

                while (anoInicio <= anoFim) {

                    colunas.add(String.valueOf(anoInicio));

                    anoInicio += 1;

                }

                break;

        }

        return colunas;
    }

    public Date BuscaMaiorData(Date dataInicial, Date dataFinal) {

        return this.contasDAO.BuscaUltimaData(TipoConta.ambos, dataInicial, dataFinal);

    }

    private void ExportacaoMensal(ArrayList<String> colunasExcel, Date dataInicial, Date dataFinal, HSSFRow row, int linha) {

        double totalEntradas;
        double totalSaidas;

        List<FlxcxEspecificacoes> especificacoes = BuscarEspecificoes();

        int mesAnterior = 0;

        for (FlxcxEspecificacoes especificacao : especificacoes) {

            List<FlxcxOperacoes> operacoes = BuscarOperacoes(especificacao.getEspCodigo());

            for (FlxcxOperacoes operacao : operacoes) {

                totalEntradas = 0;
                totalSaidas = 0;

                boolean mostrar = true;

                List<CarCapContas> contas = BuscaContas(operacao.getOpCodigo(), dataInicial, dataFinal);

                for (CarCapContas conta : contas) {

                    SimpleDateFormat sdf = new SimpleDateFormat("MM");
                    int mes = Integer.valueOf(sdf.format(conta.getContaDataEmissao()));

                    if (mostrar) {

                        //Linha alimenta uma nova especificacao
                        linha += 1;
                        row = firstSheet.createRow(linha);

                        //Coluna com a descricao da especificacao
                        row.createCell(0).setCellValue(especificacao.getEspDescricao());

                        int sequenciaOperacao = 0;

                        //Linha alimenta uma nova operacao
                        linha += 1;
                        row = firstSheet.createRow(linha);

                        //codigo sequencial
                        sequenciaOperacao += 1;
                        row.createCell(0).setCellValue(sequenciaOperacao);
                        mostrar = false;
                    } else {

                        if (mes != mesAnterior) {

                            totalEntradas = 0;
                            totalSaidas = 0;

                            mesAnterior = mes;
                        }

                    }

                    //Descricao da operacao
                    row.createCell(1).setCellValue(operacao.getOpDescricao());

                    //Realiza soma
                    if (conta.getContaTipo() == TipoConta.Entrada) {
                        totalEntradas += conta.getContaValorPago();
                    } else {
                        totalSaidas += conta.getContaValorPago();
                    }

                    switch (mes) {

                        case 1:
                            this.AdicionaLinha(row, 3, (totalEntradas - totalSaidas));
                            break;
                        case 2:
                            this.AdicionaLinha(row, 4, (totalEntradas - totalSaidas));
                            break;
                        case 3:
                            this.AdicionaLinha(row, 5, (totalEntradas - totalSaidas));
                            break;
                        case 4:
                            this.AdicionaLinha(row, 6, (totalEntradas - totalSaidas));
                            break;
                        case 5:
                            this.AdicionaLinha(row, 7, (totalEntradas - totalSaidas));
                            break;
                        case 6:
                            this.AdicionaLinha(row, 8, (totalEntradas - totalSaidas));
                            break;
                        case 7:
                            this.AdicionaLinha(row, 9, (totalEntradas - totalSaidas));
                            break;
                        case 8:
                            this.AdicionaLinha(row, 10, (totalEntradas - totalSaidas));
                            break;
                        case 9:
                            this.AdicionaLinha(row, 11, (totalEntradas - totalSaidas));
                            break;
                        case 10:
                            this.AdicionaLinha(row, 12, (totalEntradas - totalSaidas));
                            break;
                        case 11:
                            this.AdicionaLinha(row, 13, (totalEntradas - totalSaidas));
                            break;
                        case 12:
                            this.AdicionaLinha(row, 14, (totalEntradas - totalSaidas));
                            break;

                    }

                }

            }

        }

    }

    private void AdicionaLinha(HSSFRow row, int coluna, double valor) {

        //Linha alimenta novo valor
        row.createCell(coluna).setCellValue(valor);

    }

}
