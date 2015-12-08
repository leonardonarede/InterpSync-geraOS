/*   1:    */ package br.com.interpsync.controller;
/*   2:    */ 
/*   3:    */ import br.com.interpsync.interdb.dao.OS_INTERDBDAO;
/*   4:    */ import br.com.interpsync.retreadsystem.modelo.Lotes_ERP;
/*   5:    */ import br.com.interpsync.retreadsystem.modelo.OS_ERP;
/*   6:    */ import br.com.interpsync.saci.dao.Cliente_SACIDAO;
/*   7:    */ import br.com.interpsync.saci.dao.FornecedorCompl_SACIDAO;
/*   8:    */ import br.com.interpsync.saci.dao.Fornecedor_SACIDAO;
/*   9:    */ import br.com.interpsync.saci.dao.Oprd_SACIDAO;
/*  10:    */ import br.com.interpsync.saci.dao.Ords_SACIDAO;
/*  11:    */ import br.com.interpsync.saci.dao.Prd_SACIDAO;
/*  12:    */ import br.com.interpsync.saci.dao.Spedcid_SACIDAO;
/*  13:    */ import br.com.interpsync.saci.modelo.Cliente_SACI;
/*  14:    */ import br.com.interpsync.saci.modelo.FornecedorCompl_SACI;
/*  15:    */ import br.com.interpsync.saci.modelo.Fornecedor_SACI;
/*  16:    */ import br.com.interpsync.saci.modelo.Oprd_SACI;
/*  17:    */ import br.com.interpsync.saci.modelo.Ords_SACI;
/*  18:    */ import br.com.interpsync.saci.modelo.Spedcid_SACI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*  19:    */ import java.io.PrintStream;
/*  20:    */ import java.text.SimpleDateFormat;
/*  21:    */ import java.util.Date;
/*  22:    */ import java.util.logging.Level;
/*  23:    */ import java.util.logging.Logger;
/*  24:    */ import org.apache.commons.mail.Email;
/*  25:    */ import org.apache.commons.mail.SimpleEmail;
/*  26:    */ 
/*  27:    */ public class GeraOSERP
/*  28:    */ {
/*  29: 35 */   private String cpfcgc = "";
/*  30:    */   private Integer codforn;
                private String hostEmail = "";
                private String portEmail = "";
                private String userEmail = "";
                private String passEmail = "";
                private String fromEmail = "";
                private String toEmail = "";
                private String lojaSACI = "";
/*  31:    */   
/*  32:    */   public void executar()
/*  33:    */   {
                  /*Inicio - Pegar Configurações de email do arquivo*/
                  try {
                  String local = new File("./email.txt").getCanonicalFile().toString();
                  File arq = new File(local);
                  boolean existe = arq.exists();        
                      if (existe){
                          FileReader fr = new FileReader( arq );
                          BufferedReader br = new BufferedReader( fr );
                          while(br.ready()){
                              String linha = br.readLine();
                              if(linha.contains("host:")){
                                  hostEmail = linha.replace("host:", "").replace(" ", "");
                              }
                              if(linha.contains("port:")){
                                  portEmail = linha.replace("port:", "").replace(" ", "");
                              }
                              if(linha.contains("user:")){
                                  userEmail = linha.replace("user:", "").replace(" ", "");
                              }
                              if(linha.contains("pass:")){
                                  passEmail = linha.replace("pass:", "").replace(" ", "");
                              }
                              if(linha.contains("from:")){
                                  fromEmail = linha.replace("from:", "").replace(" ", "");
                              }
                              if(linha.contains("to:")){
                                  toEmail = linha.replace("to:", "").replace(" ", "");
                              }
                          }
                     
                      } 
                   } catch (FileNotFoundException ex) {
                          Logger.getLogger(GeraOSERP.class.getName()).log(Level.SEVERE, null, ex);
                           StackTraceElement st[] = ex.getStackTrace();
                           String erro = "";
                           for (int i = 0;i < st.length;i++){
                                erro += st[i].toString() + "\n";
                           } 
                           new Geralog().enviar("GeraOS", erro);
                         
                   } catch (IOException ex) {
                          Logger.getLogger(GeraOSERP.class.getName()).log(Level.SEVERE, null, ex);
                          StackTraceElement st[] = ex.getStackTrace();
                          String erro2 = "";
                          for (int i = 0;i < st.length;i++){
                              erro2 += st[i].toString() + "\n";
                          } 
                          new Geralog().enviar("GeraOS", erro2);
                  }
                  /*FIM - Pegar Configurações de email do arquivo*/
                  
                  /*Inicio - Buscar Loja da Configuracao*/
                  try {
                  String local = new File("./bdconfig.txt").getCanonicalFile().toString();
                  File arq = new File(local);
                  boolean existe = arq.exists();        
                      if (existe){
                          FileReader fr = new FileReader( arq );
                          BufferedReader br = new BufferedReader( fr );
                          while(br.ready()){
                              String linha = br.readLine();
                              if(linha.contains("SACILoja:")){
                                  lojaSACI = linha.replace("SACILoja:", "").replace(" ", "");
                              }
                          }
                     
                      } 
                   } catch (FileNotFoundException ex) {
                          Logger.getLogger(GeraOSERP.class.getName()).log(Level.SEVERE, null, ex);
                           StackTraceElement st[] = ex.getStackTrace();
                           String erro = "";
                           for (int i = 0;i < st.length;i++){
                                erro += st[i].toString() + "\n";
                           } 
                           new Geralog().enviar("GeraOS", erro);
                         
                   } catch (IOException ex) {
                          Logger.getLogger(GeraOSERP.class.getName()).log(Level.SEVERE, null, ex);
                          StackTraceElement st[] = ex.getStackTrace();
                          String erro2 = "";
                          for (int i = 0;i < st.length;i++){
                              erro2 += st[i].toString() + "\n";
                          } 
                          new Geralog().enviar("GeraOS", erro2);
                  }
                  /*Fim -  - Buscar Loja da Configuracao*/
/*  34: 38 */     OS_INTERDBDAO osdao = new OS_INTERDBDAO();
/*  35: 39 */     Fornecedor_SACIDAO forndao = new Fornecedor_SACIDAO();
/*  36: 40 */     Ords_SACIDAO ordsDAO = new Ords_SACIDAO();
/*  37: 41 */     Oprd_SACIDAO oprdDAO = new Oprd_SACIDAO();
/*  38: 42 */     Prd_SACIDAO prdDAO = new Prd_SACIDAO();
/*  39:    */     try
/*  40:    */     {
/*  41: 44 */       for (Lotes_ERP lote : osdao.listaLotesPendentesSACI())
/*  42:    */       {
/*  43: 46 */         if (lote.getClienteID().length() == 16) {
/*  44: 47 */           this.cpfcgc = (lote.getClienteID().substring(0, 2) + "." + lote.getClienteID().substring(2, 5) + "." + lote.getClienteID().substring(5, lote.getClienteID().length()));
                        System.out.println("CPFCGC: "+cpfcgc);
/*  45:    */         } else {
/*  46: 51 */           this.cpfcgc = (lote.getClienteID().substring(0, 3) + "." + lote.getClienteID().substring(3, 6) + "." + lote.getClienteID().substring(6, lote.getClienteID().length()));
                        System.out.println("CPFCGC: "+cpfcgc);
/*  47:    */         }
/*  48: 56 */         this.codforn = Integer.valueOf(forndao.buscar(this.cpfcgc).getNo() != null ? forndao.buscar(this.cpfcgc).getNo().intValue() : 0);
/*  49: 57 */         if (this.codforn.intValue() == 0)
/*  50:    */         {
/*  51: 58 */           Fornecedor_SACI novoforn = new Fornecedor_SACI();
/*  52: 59 */           Cliente_SACIDAO cldao = new Cliente_SACIDAO();
/*  53: 60 */           Cliente_SACI cliente = cldao.buscarCl(this.cpfcgc) != null ? cldao.buscarCl(this.cpfcgc) : new Cliente_SACI();
/*  54: 61 */           if (cliente.getName() == null)
/*  55:    */           {
/*  56: 62 */             System.out.println("Cliente não cadastrado no SACI");
                          System.out.println("CPF: "+cpfcgc);
/*  57:    */           }
/*  58:    */           else
/*  59:    */           {
/*  60: 65 */             this.codforn = forndao.buscarUltimo();
/*  61: 66 */             novoforn.setNo(this.codforn);
/*  62: 67 */             novoforn.setName(cliente.getName());
                          System.out.println("Cliente: "+cliente.getName());
/*  63: 68 */             novoforn.setSname(cliente.getName().substring(0, 5));
/*  64: 69 */             novoforn.setCgc(this.cpfcgc);
/*  65: 70 */             novoforn.setInsc(cliente.getBrthplace_insc());
/*  66: 71 */             novoforn.setNei(cliente.getNei1());
/*  67: 72 */             novoforn.setCity(cliente.getCity1());
/*  68: 73 */             novoforn.setState(cliente.getState1());
/*  69: 74 */             novoforn.setAuxChar1(cliente.getName());

                          char letra = cliente.getZip().charAt(8);
                          
                          if(Character.isDigit(letra)){
                              novoforn.setZip(Integer.valueOf(Integer.parseInt(cliente.getZip().substring(0, 8))));
                          }else{
                              novoforn.setZip(Integer.valueOf(Integer.parseInt(cliente.getZip().substring(0, 7))));
                          }
/*  70: 75 */             
/*  71: 76 */             novoforn.setExt("0 1026 3 0");
/*  72: 77 */             novoforn.setColl("1026");
/*  73: 78 */             novoforn.setFabOufor(Integer.valueOf(3));
/*  74: 79 */             novoforn.setAddr(cliente.getAdd1());
/*  75: 80 */             forndao.inserir(novoforn);
/*  76:    */             
/*  77: 82 */             Spedcid_SACIDAO cidadedao = new Spedcid_SACIDAO();
/*  78: 83 */             Spedcid_SACI cidade = new Spedcid_SACI();
/*  79: 84 */             cidade = cidadedao.localizar(novoforn.getCity(), novoforn.getState());
/*  80: 85 */             FornecedorCompl_SACI compl = new FornecedorCompl_SACI();
/*  81: 86 */             FornecedorCompl_SACIDAO compldao = new FornecedorCompl_SACIDAO();
/*  82: 87 */             compl.setVendno(this.codforn);
/*  83: 88 */             compl.setPais(Integer.valueOf(0));
/*  84: 89 */             compl.setAuxLong1(cidade.getNo());
/*  85: 90 */             compldao.inserir(compl);
/*  86:    */           }
/*  87:    */         }
                      if(codforn != null){
    /*  88: 95 */         Ords_SACI ordsSACI = new Ords_SACI();
    /*  89: 96 */         Integer novoNum = ordsDAO.buscaUlt(Integer.valueOf(lojaSACI).intValue());
                          novoNum = Integer.valueOf(novoNum.intValue() + 1);
    /*  91: 98 */         ordsSACI.setNo(novoNum);
    /*  92: 99 */         ordsSACI.setStoreno(Integer.valueOf(lojaSACI).intValue());
    /*  93:100 */         ordsSACI.setEmpno(Integer.valueOf(389));
    /*  94:101 */         ordsSACI.setOrdnoFromVend(lote.getNrLote().toString());
    /*  95:102 */         ordsSACI.setStatus(Integer.valueOf(0));
    /*  96:103 */         ordsSACI.setVendno(this.codforn);
    /*  97:104 */         ordsSACI.setRemarks("LOTE PRODUCAO");
    /*  98:105 */         ordsSACI.setAuxShort2(Integer.valueOf(4));
    /*  99:106 */         Date datahj = new Date();
    /* 100:107 */         SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    /* 101:108 */         ordsSACI.setData(Integer.valueOf(Integer.parseInt(df.format(datahj))));
    /* 102:    */         
    /* 103:110 */         ordsDAO.inserir(ordsSACI);
    /* 104:    */         
    /* 105:    */ 
                          Integer i = Integer.valueOf(0);
                          for (OS_ERP os : osdao.listarOsPendenteSACI(lote.getNrLote()))
    /* 108:    */         {
    /* 109:116 */           Integer localInteger1 = i;Integer localInteger2 = i = Integer.valueOf(i.intValue() + 1);
    /* 110:117 */           String codCarcaca = prdDAO.localizarCarcaca(os.getMedidaID());
    /* 111:118 */           Oprd_SACI oprdSACI = new Oprd_SACI();
    /* 112:119 */           oprdSACI.setStoreno(Integer.valueOf(lojaSACI).intValue());
    /* 113:120 */           oprdSACI.setOrdno(novoNum);
    /* 114:121 */           oprdSACI.setPrdno(codCarcaca);
    /* 115:122 */           String grade = i.toString().length() == 1 ? "0" + i.toString() : i.toString();
    /* 116:123 */           oprdSACI.setGrade(grade);
    /* 117:124 */           oprdSACI.setQtty(Integer.valueOf(1));
    /* 118:125 */           oprdSACI.setMult(Integer.valueOf(1000));
    /* 119:126 */           oprdSACI.setCost(Integer.valueOf(10));
    /* 120:127 */           oprdSACI.setStatus(Integer.valueOf(0));
    /* 121:128 */           oprdSACI.setRemarks(os.getNrOS().toString());
    /* 122:129 */           if ((oprdSACI.getPrdno() == null) || (oprdSACI.getPrdno().isEmpty()))
    /* 123:    */           {

                              Email email = new SimpleEmail();
    /*  40: 40 */             email.setHostName(hostEmail);
    /*  41: 41 */             email.setSmtpPort(Integer.parseInt(portEmail));
    /*  42: 42 */             email.setAuthentication(userEmail, passEmail);
    /*  43: 43 */             email.setFrom(fromEmail);
    /*  44: 44 */             email.setSubject("Erro na Integração SACI x Retread System");
    /*  45: 45 */             email.setMsg("Cadastrar a Carcaca com a medida: " + os.getMedidaID());
    /*  46:    */           
    /*  47: 47 */             email.addTo(toEmail);
    /*  48: 48 */             email.send();
                              oprdDAO.deletar(novoNum,Integer.valueOf(lojaSACI).intValue());
                              ordsDAO.deletar(novoNum,Integer.valueOf(lojaSACI).intValue());
    /* 133:    */           }
    /* 134:    */           else
    /* 135:    */           {
    /* 136:140 */             oprdDAO.inserir(oprdSACI);
    /* 137:141 */             os.setStatusERP(Integer.valueOf(1));
    /* 138:142 */             osdao.atualizar(os);
    /* 139:    */           }
    /* 140:    */         }
    /* 141:    */       }
    /* 142:    */      }
                  }
/* 143:    */     catch (ClassNotFoundException ex)
/* 144:    */     {
/* 145:    */       
/* 147:149 */       Logger.getLogger(GeraOSERP.class.getName()).log(Level.SEVERE, null, ex);
                     StackTraceElement st[] = ex.getStackTrace();
                      String erro3 = "";
                      for (int i = 0;i < st.length;i++){
                          erro3 += st[i].toString() + "\n";
                      } 
                      new Geralog().enviar("GeraOS", erro3);
/* 148:    */     }
/* 149:    */     catch (Exception ex)
/* 150:    */     {
/* 151:151 */        Logger.getLogger(GeraOSERP.class.getName()).log(Level.SEVERE, null, ex);
                     StackTraceElement st[] = ex.getStackTrace();
                      String erro4 = "";
                      for (int i = 0;i < st.length;i++){
                          erro4 += st[i].toString() + "\n";
                      } 
                      new Geralog().enviar("GeraOS", erro4);
/* 152:    */     }
/* 153:    */   }
/* 154:    */ }



/* Location:           C:\Users\Leonardo\Desktop\INT\InterpSync.jar

 * Qualified Name:     br.com.interpsync.controller.GeraOSERP

 * JD-Core Version:    0.7.0.1

 */