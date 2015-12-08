/*   1:    */ package br.com.interpsync.controller;
/*   2:    */ 
/*   3:    */ import br.com.interpsync.interdb.dao.Coxins_INTERDBDAO;
/*   4:    */ import br.com.interpsync.interdb.dao.Desreforma_INTERDBDAO;
/*   5:    */ import br.com.interpsync.interdb.dao.Laudosrec_INTERDBDAO;
/*   6:    */ import br.com.interpsync.interdb.dao.Marcas_INTERDBDAO;
/*   7:    */ import br.com.interpsync.interdb.dao.Medidas_INTERDBDAO;
/*   8:    */ import br.com.interpsync.interdb.dao.Modelos_INTERDBDAO;
/*   9:    */ import br.com.interpsync.interdb.dao.OS_INTERDBDAO;
/*  10:    */ import br.com.interpsync.interdb.dao.Osvr_INTERDBDAO;
/*  11:    */ import br.com.interpsync.interdb.dao.Parcprazos_INTERDBDAO;
/*  12:    */ import br.com.interpsync.interdb.dao.Prazos_INTERDBDAO;
/*  13:    */ import br.com.interpsync.interdb.dao.Regioes_INTERDBDAO;
/*  14:    */ import br.com.interpsync.interdb.dao.Reparos_INTERDBDAO;
/*  15:    */ import br.com.interpsync.interdb.dao.Resp_CliINTERDBDAO;
/*  16:    */ import br.com.interpsync.interdb.dao.Servicos_INTERDBDAO;
/*  18:    */ import br.com.interpsync.retreadsystem.dao.Coxins_ERPDAO;
/*  19:    */ import br.com.interpsync.retreadsystem.dao.Desreforma_ERPDAO;
/*  20:    */ import br.com.interpsync.retreadsystem.dao.Laudosrec_ERPDAO;
/*  21:    */ import br.com.interpsync.retreadsystem.dao.Marcas_ERPDAO;
/*  22:    */ import br.com.interpsync.retreadsystem.dao.Medidas_ERPDAO;
/*  23:    */ import br.com.interpsync.retreadsystem.dao.Modelos_ERPDAO;
/*  24:    */ import br.com.interpsync.retreadsystem.dao.OS_ERPDAO;
/*  25:    */ import br.com.interpsync.retreadsystem.dao.Osvr_ERPDAO;
/*  26:    */ import br.com.interpsync.retreadsystem.dao.Parcprazos_ERPDAO;
/*  27:    */ import br.com.interpsync.retreadsystem.dao.Prazos_ERPDAO;
/*  28:    */ import br.com.interpsync.retreadsystem.dao.Regioes_ERPDAO;
/*  29:    */ import br.com.interpsync.retreadsystem.dao.Reparos_ERPDAO;
/*  30:    */ import br.com.interpsync.retreadsystem.dao.Resp_CliDAO;
/*  31:    */ import br.com.interpsync.retreadsystem.dao.Servicos_ERPDAO;
/*  32:    */ import br.com.interpsync.retreadsystem.modelo.Coxins_ERP;
/*  33:    */ import br.com.interpsync.retreadsystem.modelo.Desreforma_ERP;
/*  34:    */ import br.com.interpsync.retreadsystem.modelo.Laudosrec_ERP;
/*  35:    */ import br.com.interpsync.retreadsystem.modelo.Marcas_ERP;
/*  36:    */ import br.com.interpsync.retreadsystem.modelo.Medidas_ERP;
/*  37:    */ import br.com.interpsync.retreadsystem.modelo.Modelos_ERP;
/*  38:    */ import br.com.interpsync.retreadsystem.modelo.OS_ERP;
/*  39:    */ import br.com.interpsync.retreadsystem.modelo.Osvr_ERP;
/*  40:    */ import br.com.interpsync.retreadsystem.modelo.Parcprazos_ERP;
/*  41:    */ import br.com.interpsync.retreadsystem.modelo.Prazos_ERP;
/*  42:    */ import br.com.interpsync.retreadsystem.modelo.Regioes_ERP;
/*  43:    */ import br.com.interpsync.retreadsystem.modelo.Reparos_ERP;
/*  44:    */ import br.com.interpsync.retreadsystem.modelo.Resp_Cli;
/*  45:    */ import br.com.interpsync.retreadsystem.modelo.Servicos_ERP;
/*  46:    */ import java.io.PrintStream;
/*  47:    */ import java.sql.SQLException;
/*  48:    */ import java.util.logging.Level;
/*  49:    */ import java.util.logging.Logger;
/*  50:    */ 
/*  51:    */ public class Integracao
/*  52:    */ {
/*  53:    */   public void executar()
/*  54:    */   {
/*  55: 61 */     System.out.println("Inicio Coxin");
/*  56: 62 */     Coxins_ERPDAO coxproddao = new Coxins_ERPDAO();
/*  57: 63 */     Coxins_INTERDBDAO coxintdao = new Coxins_INTERDBDAO();
/*  58:    */     try
/*  59:    */     {
/*  60: 65 */       for (Coxins_ERP coxin : coxproddao.listarNovos()) {
/*  61: 66 */         if ((coxin.getCodCX() != null) && (coxin.getDescrCX() != null))
/*  62:    */         {
/*  63: 67 */           if (coxintdao.localizarPorId(coxin.getCodCX()).getDescrCX() == null)
/*  64:    */           {
/*  65: 68 */             coxin.setStei(1);
/*  66: 69 */             coxintdao.inserir(coxin);
/*  67:    */           }
/*  68:    */           else
/*  69:    */           {
/*  70: 71 */             coxin.setStei(1);
/*  71: 72 */             coxintdao.atualizar(coxin);
/*  72:    */           }
/*  73: 74 */           coxproddao.atualizar(coxin);
/*  74: 75 */           System.out.println("Coxin: " + coxin.getCodCX());
/*  75:    */         }
/*  76:    */       }
/*  77:    */     }
/*  78:    */     catch (SQLException ex)
/*  79:    */     {
/*  80: 79 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/*  81:    */     }
/*  82:    */     catch (ClassNotFoundException ex)
/*  83:    */     {
/*  84: 81 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/*  85:    */     }
/*  86:    */     catch (Exception ex)
/*  87:    */     {
/*  88: 83 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/*  89:    */     }
/*  90: 85 */     System.out.println("Fim Coxin");
/*  91: 86 */     System.out.println("Inicio Desenho");
/*  92: 87 */     Desreforma_ERPDAO desproddao = new Desreforma_ERPDAO();
/*  93: 88 */     Desreforma_INTERDBDAO desintdao = new Desreforma_INTERDBDAO();
/*  94:    */     try
/*  95:    */     {
/*  96: 90 */       for (Desreforma_ERP desenho : desproddao.listarNovos()) {
/*  97: 91 */         if ((desenho.getCodDE() != null) && (desenho.getDescrDE() != null))
/*  98:    */         {
/*  99: 92 */           if (desintdao.localizarPorID(desenho.getCodDE()).getCodDE() == null)
/* 100:    */           {
/* 101: 93 */             desenho.setStEI(1);
/* 102: 94 */             desintdao.inserir(desenho);
/* 103:    */           }
/* 104:    */           else
/* 105:    */           {
/* 106: 96 */             desenho.setStEI(1);
/* 107: 97 */             desintdao.atualizar(desenho);
/* 108:    */           }
/* 109: 99 */           desproddao.atualizar(desenho);
/* 110:100 */           System.out.println("Desenho: " + desenho.getCodDE());
/* 111:    */         }
/* 112:    */       }
/* 113:    */     }
/* 114:    */     catch (SQLException ex)
/* 115:    */     {
/* 116:104 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 117:    */     }
/* 118:    */     catch (ClassNotFoundException ex)
/* 119:    */     {
/* 120:106 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 121:    */     }
/* 122:    */     catch (Exception ex)
/* 123:    */     {
/* 124:108 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 125:    */     }
/* 126:110 */     System.out.println("Fim Desenho");
/* 127:111 */     System.out.println("Inicio Laudo");
/* 128:112 */     Laudosrec_ERPDAO lauproddao = new Laudosrec_ERPDAO();
/* 129:113 */     Laudosrec_INTERDBDAO lauintdao = new Laudosrec_INTERDBDAO();
/* 130:    */     try
/* 131:    */     {
/* 132:115 */       for (Laudosrec_ERP laudo : lauproddao.listarNovos()) {
/* 133:116 */         if ((laudo.getCodLA() != null) && (laudo.getDescrLA() != null))
/* 134:    */         {
/* 135:117 */           if (lauintdao.localizarPorID(laudo.getCodLA()).getCodLA() == null)
/* 136:    */           {
/* 137:118 */             laudo.setStEI(1);
/* 138:119 */             lauintdao.inserir(laudo);
/* 139:    */           }
/* 140:    */           else
/* 141:    */           {
/* 142:121 */             laudo.setStEI(1);
/* 143:122 */             lauintdao.atualizar(laudo);
/* 144:    */           }
/* 145:124 */           lauproddao.atualizar(laudo);
/* 146:125 */           System.out.println("Laudo: " + laudo.getCodLA());
/* 147:    */         }
/* 148:    */       }
/* 149:    */     }
/* 150:    */     catch (SQLException ex)
/* 151:    */     {
/* 152:130 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 153:    */     }
/* 154:    */     catch (ClassNotFoundException ex)
/* 155:    */     {
/* 156:132 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 157:    */     }
/* 158:    */     catch (Exception ex)
/* 159:    */     {
/* 160:134 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 161:    */     }
/* 162:136 */     System.out.println("Fim Laudo");
/* 163:137 */     System.out.println("Inicio Marca");
/* 164:138 */     Marcas_ERPDAO marproddao = new Marcas_ERPDAO();
/* 165:139 */     Marcas_INTERDBDAO marintdao = new Marcas_INTERDBDAO();
/* 166:    */     try
/* 167:    */     {
/* 168:141 */       for (Marcas_ERP marca : marproddao.listarNovos()) {
/* 169:142 */         if ((marca.getCodMA() != null) && (marca.getDescrMA() != null))
/* 170:    */         {
/* 171:143 */           if (marintdao.localizarPorID(marca.getCodMA()).getDescrMA() == null)
/* 172:    */           {
/* 173:144 */             marca.setStEI(1);
/* 174:145 */             marintdao.inserir(marca);
/* 175:    */           }
/* 176:    */           else
/* 177:    */           {
/* 178:147 */             marca.setStEI(1);
/* 179:148 */             marintdao.atualizar(marca);
/* 180:    */           }
/* 181:150 */           marproddao.atualizar(marca);
/* 182:151 */           System.out.println("Marca " + marca.getCodMA());
/* 183:    */         }
/* 184:    */       }
/* 185:    */     }
/* 186:    */     catch (SQLException ex)
/* 187:    */     {
/* 188:155 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 189:    */     }
/* 190:    */     catch (ClassNotFoundException ex)
/* 191:    */     {
/* 192:157 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 193:    */     }
/* 194:    */     catch (Exception ex)
/* 195:    */     {
/* 196:159 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 197:    */     }
/* 198:161 */     System.out.println("Fim Marca");
/* 199:162 */     System.out.println("Inicio Medida");
/* 200:163 */     Medidas_ERPDAO medproddao = new Medidas_ERPDAO();
/* 201:164 */     Medidas_INTERDBDAO medintdao = new Medidas_INTERDBDAO();
/* 202:    */     try
/* 203:    */     {
/* 204:166 */       for (Medidas_ERP medida : medproddao.listarNovos()) {
/* 205:167 */         if ((medida.getCodME() != null) && (medida.getDescrME() != null))
/* 206:    */         {
/* 207:168 */           if (medintdao.localizarPorID(medida.getCodME()).getCodME() == null)
/* 208:    */           {
/* 209:169 */             medida.setStEI(1);
/* 210:170 */             medintdao.inserir(medida);
/* 211:    */           }
/* 212:    */           else
/* 213:    */           {
/* 214:172 */             medida.setStEI(1);
/* 215:173 */             medintdao.atualizar(medida);
/* 216:    */           }
/* 217:175 */           medproddao.atualizar(medida);
/* 218:176 */           System.out.println("Medida: " + medida.getCodME());
/* 219:    */         }
/* 220:    */       }
/* 221:    */     }
/* 222:    */     catch (SQLException ex)
/* 223:    */     {
/* 224:181 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 225:    */     }
/* 226:    */     catch (ClassNotFoundException ex)
/* 227:    */     {
/* 228:183 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 229:    */     }
/* 230:    */     catch (Exception ex)
/* 231:    */     {
/* 232:185 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 233:    */     }
/* 234:187 */     System.out.println("Fim Medidas");
/* 235:188 */     System.out.println("Inicio Modelo");
/* 236:189 */     Modelos_ERPDAO modproddao = new Modelos_ERPDAO();
/* 237:190 */     Modelos_INTERDBDAO modintdao = new Modelos_INTERDBDAO();
/* 238:    */     try
/* 239:    */     {
/* 240:192 */       for (Modelos_ERP modelo : modproddao.listarNovos()) {
/* 241:193 */         if ((modelo.getCodMO() != null) && (modelo.getDescrMO() != null))
/* 242:    */         {
/* 243:194 */           if (modintdao.localizarPorID(modelo.getCodMO()).getCodMO() == null)
/* 244:    */           {
/* 245:195 */             modelo.setStEI(1);
/* 246:196 */             modintdao.inserir(modelo);
/* 247:    */           }
/* 248:    */           else
/* 249:    */           {
/* 250:198 */             modelo.setStEI(1);
/* 251:199 */             modintdao.atualizar(modelo);
/* 252:    */           }
/* 253:201 */           modproddao.atualizar(modelo);
/* 254:202 */           System.out.println("Modelos: " + modelo.getCodMO());
/* 255:    */         }
/* 256:    */       }
/* 257:    */     }
/* 258:    */     catch (SQLException ex)
/* 259:    */     {
/* 260:206 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 261:    */     }
/* 262:    */     catch (ClassNotFoundException ex)
/* 263:    */     {
/* 264:208 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 265:    */     }
/* 266:    */     catch (Exception ex)
/* 267:    */     {
/* 268:210 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 269:    */     }
/* 270:212 */     System.out.println("Fim Modelo");
/* 271:213 */     System.out.println("Inicio OS");
/* 272:214 */     OS_ERPDAO osproddao = new OS_ERPDAO();
/* 273:215 */     OS_INTERDBDAO osintdao = new OS_INTERDBDAO();
/* 274:    */     try
/* 275:    */     {
/* 276:217 */       for (OS_ERP os : osproddao.listarNovos()) {
/* 277:218 */         if ((os.getNrOS() != null) || (os.getNrMatricula() != null))
/* 278:    */         {
/* 279:219 */           if (osintdao.localizarPorID(os.getNrOS().intValue()).getNrOS() == null)
/* 280:    */           {
/* 281:220 */             if (os.getStEI() == 1) {
/* 282:221 */               os.setStEI(2);
/* 283:    */             } else {
/* 284:223 */               os.setStEI(5);
/* 285:    */             }
/* 286:225 */             os.setERPID("1");
/* 287:226 */             osintdao.inserir(os);
/* 288:    */           }
/* 289:236 */           osproddao.atualizar(os);
/* 290:237 */           System.out.println("Os: " + os.getNrOS() + " Status" +os.getStEI());
/* 291:    */         }
/* 292:    */       }
/* 293:    */     }
/* 294:    */     catch (SQLException ex)
/* 295:    */     {
/* 296:241 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 297:    */     }
/* 298:    */     catch (ClassNotFoundException ex)
/* 299:    */     {
/* 300:243 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 301:    */     }
/* 302:    */     catch (Exception ex)
/* 303:    */     {
/* 304:245 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 305:    */     }
/* 306:247 */     System.out.println("Fim Os");
/* 307:248 */     System.out.println("Inicio Detalhameto OS");
/* 308:249 */     Osvr_ERPDAO osvrERPDAO = new Osvr_ERPDAO();
/* 309:250 */     Osvr_INTERDBDAO osvrINTERDBDAO = new Osvr_INTERDBDAO();
/* 310:    */     try
/* 311:    */     {
/* 312:253 */       for (Osvr_ERP osvr : osvrERPDAO.listarNovos()) {
/* 313:254 */         if ((osvr.getNrOS() != null) && (osvr.getCodigoReg() != null))
/* 314:    */         {
/* 315:255 */           if (osvrINTERDBDAO.localizarPorID(osvr.getNrOS(), osvr.getTipoReg(), osvr.getCodigoReg()).getNrOS() == null)
/* 316:    */           {
/* 317:258 */             osvr.setFilialTit("002");
/* 318:259 */             osvrINTERDBDAO.inserir(osvr);
/* 319:    */           }
/* 320:    */           else
/* 321:    */           {
/* 322:261 */             osvr.setFilialTit("002");
/* 323:262 */             osvrINTERDBDAO.atualizar(osvr);
/* 324:    */           }
/* 325:264 */           osvrERPDAO.atualizar(osvr);
/* 326:265 */           System.out.println("Detalhamenento OS: " + osvr.getNrOS() + " Tipo: " + osvr.getTipoReg() + " Codigo Reg: " + osvr.getCodigoReg());
/* 327:    */         }
/* 328:    */       }
/* 329:    */     }
/* 330:    */     catch (SQLException ex)
/* 331:    */     {
/* 332:271 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 333:    */     }
/* 334:    */     catch (ClassNotFoundException ex)
/* 335:    */     {
/* 336:273 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 337:    */     }
/* 338:    */     catch (Exception ex)
/* 339:    */     {
/* 340:275 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 341:    */     }
/* 342:278 */     System.out.println("Inicio Parcelas");
/* 343:279 */     Parcprazos_ERPDAO parpdao = new Parcprazos_ERPDAO();
/* 344:280 */     Parcprazos_INTERDBDAO paridao = new Parcprazos_INTERDBDAO();
/* 345:    */     try
/* 346:    */     {
/* 347:282 */       for (Parcprazos_ERP parc : parpdao.listarNovos()) {
/* 348:283 */         if ((parc.getCodPR() != null) && (parc.getParcPR() != null))
/* 349:    */         {
/* 350:284 */           if (paridao.localizarPorID(parc.getCodPR()).getCodPR() == null)
/* 351:    */           {
/* 352:285 */             parc.setStEI(1);
/* 353:286 */             paridao.inserir(parc);
/* 354:    */           }
/* 355:    */           else
/* 356:    */           {
/* 357:288 */             parc.setStEI(1);
/* 358:289 */             paridao.atualizar(parc);
/* 359:    */           }
/* 360:291 */           parpdao.atualizar(parc);
/* 361:292 */           System.out.println("Parcelas: " + parc.getCodPR());
/* 362:    */         }
/* 363:    */       }
/* 364:    */     }
/* 365:    */     catch (SQLException ex)
/* 366:    */     {
/* 367:296 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 368:    */     }
/* 369:    */     catch (ClassNotFoundException ex)
/* 370:    */     {
/* 371:298 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 372:    */     }
/* 373:    */     catch (Exception ex)
/* 374:    */     {
/* 375:300 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 376:    */     }
/* 377:302 */     System.out.println("Fim parcelas");
/* 378:303 */     System.out.println("Inicio Prazo");
/* 379:304 */     Prazos_ERPDAO prapdao = new Prazos_ERPDAO();
/* 380:305 */     Prazos_INTERDBDAO praidao = new Prazos_INTERDBDAO();
/* 381:    */     try
/* 382:    */     {
/* 383:307 */       for (Prazos_ERP prazo : prapdao.listarNovos()) {
/* 384:308 */         if ((prazo.getCodPR() != null) && (prazo.getDescrPR() != null))
/* 385:    */         {
/* 386:309 */           if (praidao.localizarPorID(prazo.getCodPR()).getDescrPR() == null)
/* 387:    */           {
/* 388:310 */             prazo.setStEI(1);
/* 389:311 */             praidao.inserir(prazo);
/* 390:    */           }
/* 391:    */           else
/* 392:    */           {
/* 393:313 */             prazo.setStEI(1);
/* 394:314 */             praidao.atualizar(prazo);
/* 395:    */           }
/* 396:316 */           prapdao.atualizar(prazo);
/* 397:317 */           System.out.println("Prazo: " + prazo.getCodPR());
/* 398:    */         }
/* 399:    */       }
/* 400:    */     }
/* 401:    */     catch (SQLException ex)
/* 402:    */     {
/* 403:321 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 404:    */     }
/* 405:    */     catch (ClassNotFoundException ex)
/* 406:    */     {
/* 407:323 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 408:    */     }
/* 409:    */     catch (Exception ex)
/* 410:    */     {
/* 411:325 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 412:    */     }
/* 413:327 */     System.out.println("Fim Prazos");
/* 414:328 */     System.out.println("Inicio Regioes");
/* 415:329 */     Regioes_ERPDAO regpdao = new Regioes_ERPDAO();
/* 416:330 */     Regioes_INTERDBDAO regidao = new Regioes_INTERDBDAO();
/* 417:    */     try
/* 418:    */     {
/* 419:333 */       for (Regioes_ERP regiao : regpdao.listarNovos()) {
/* 420:334 */         if ((regiao.getCodRE() != null) && (regiao.getDescrRE() != null))
/* 421:    */         {
/* 422:335 */           if (regidao.localizarPorID(regiao.getCodRE()).getDescrRE() == null)
/* 423:    */           {
/* 424:336 */             regiao.setStEI(1);
/* 425:337 */             regidao.inserir(regiao);
/* 426:    */           }
/* 427:    */           else
/* 428:    */           {
/* 429:339 */             regiao.setStEI(1);
/* 430:340 */             regidao.atualizar(regiao);
/* 431:    */           }
/* 432:342 */           regpdao.atualizar(regiao);
/* 433:343 */           System.out.println("Regiao" + regiao.getCodRE());
/* 434:    */         }
/* 435:    */       }
/* 436:    */     }
/* 437:    */     catch (SQLException ex)
/* 438:    */     {
/* 439:348 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 440:    */     }
/* 441:    */     catch (ClassNotFoundException ex)
/* 442:    */     {
/* 443:350 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 444:    */     }
/* 445:    */     catch (Exception ex)
/* 446:    */     {
/* 447:352 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 448:    */     }
/* 449:354 */     System.out.println("Fim Regioes");
/* 450:355 */     System.out.println("Inicio Reparos");
/* 451:356 */     Reparos_ERPDAO reppdao = new Reparos_ERPDAO();
/* 452:357 */     Reparos_INTERDBDAO repidao = new Reparos_INTERDBDAO();
/* 453:    */     try
/* 454:    */     {
/* 455:359 */       for (Reparos_ERP reparo : reppdao.listarNovos()) {
/* 456:360 */         if ((reparo.getCodRP() != null) && (reparo.getDescrRP() != null))
/* 457:    */         {
/* 458:361 */           if (repidao.localizarPorID(reparo.getCodRP()).getDescrRP() == null)
/* 459:    */           {
/* 460:362 */             reparo.setStei(1);
/* 461:363 */             repidao.inserir(reparo);
/* 462:    */           }
/* 463:    */           else
/* 464:    */           {
/* 465:365 */             reparo.setStei(1);
/* 466:366 */             repidao.atualizar(reparo);
/* 467:    */           }
/* 468:368 */           reppdao.atualizar(reparo);
/* 469:369 */           System.out.println("Reparo: " + reparo.getCodRP());
/* 470:    */         }
/* 471:    */       }
/* 472:    */     }
/* 473:    */     catch (SQLException ex)
/* 474:    */     {
/* 475:374 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 476:    */     }
/* 477:    */     catch (ClassNotFoundException ex)
/* 478:    */     {
/* 479:376 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 480:    */     }
/* 481:    */     catch (Exception ex)
/* 482:    */     {
/* 483:378 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 484:    */     }
/* 485:380 */     System.out.println("Fim reparos");
/* 486:381 */     System.out.println("Inicio Resp");
/* 487:382 */     Resp_CliDAO resppdao = new Resp_CliDAO();
/* 488:383 */     Resp_CliINTERDBDAO respidao = new Resp_CliINTERDBDAO();
/* 489:    */     try
/* 490:    */     {
/* 491:385 */       for (Resp_Cli resp : resppdao.listarNovos()) {
/* 492:386 */         if ((resp.getCNPJCPF() != null) && (resp.getRespAVencer() != null))
/* 493:    */         {
/* 494:387 */           if (respidao.localizarPorID(resp.getCNPJCPF()).getCNPJCPF() == null)
/* 495:    */           {
/* 496:388 */             resp.setStEI(1);
/* 497:389 */             respidao.inserir(resp);
/* 498:    */           }
/* 499:    */           else
/* 500:    */           {
/* 501:391 */             resp.setStEI(1);
/* 502:392 */             respidao.atualizar(resp);
/* 503:    */           }
/* 504:394 */           resppdao.atualizar(resp);
/* 505:395 */           System.out.println("Responsabilidade: " + resp.getCNPJCPF());
/* 506:    */         }
/* 507:    */       }
/* 508:    */     }
/* 509:    */     catch (SQLException ex)
/* 510:    */     {
/* 511:400 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 512:    */     }
/* 513:    */     catch (ClassNotFoundException ex)
/* 514:    */     {
/* 515:402 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 516:    */     }
/* 517:    */     catch (Exception ex)
/* 518:    */     {
/* 519:404 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 520:    */     }
/* 521:406 */     System.out.println("Fim Resp");
/* 522:407 */     System.out.println("Inicio serv");
/* 523:408 */     Servicos_ERPDAO servpdao = new Servicos_ERPDAO();
/* 524:409 */     Servicos_INTERDBDAO servidao = new Servicos_INTERDBDAO();
/* 525:    */     try
/* 526:    */     {
/* 527:411 */       for (Servicos_ERP serv : servpdao.listarNovos()) {
/* 528:412 */         if ((serv.getCodSE() != null) && (serv.getDescrSE() != null))
/* 529:    */         {
/* 530:413 */           if (servidao.localizarPorID(serv.getCodSE()).getDescrSE() == null)
/* 531:    */           {
/* 532:414 */             serv.setStei(1);
/* 533:415 */             servidao.inserir(serv);
/* 534:    */           }
/* 535:    */           else
/* 536:    */           {
/* 537:417 */             serv.setStei(1);
/* 538:418 */             servidao.atualizar(serv);
/* 539:    */           }
/* 540:420 */           servpdao.atualizar(serv);
/* 541:421 */           System.out.println("Servicos: " + serv.getCodSE());
/* 542:    */         }
/* 543:    */       }
/* 544:    */     }
/* 545:    */     catch (SQLException ex)
/* 546:    */     {
/* 547:426 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 548:    */     }
/* 549:    */     catch (ClassNotFoundException ex)
/* 550:    */     {
/* 551:428 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 552:    */     }
/* 553:    */     catch (Exception ex)
/* 554:    */     {
/* 555:430 */       Logger.getLogger(Integracao.class.getName()).log(Level.SEVERE, null, ex);
/* 556:    */     }
/* 557:432 */     System.out.println("Fim serv");
/* 558:    */   }
/* 559:    */ }



/* Location:           C:\Users\Leonardo\Desktop\INT\InterpSync.jar

 * Qualified Name:     br.com.interpsync.controller.Integracao

 * JD-Core Version:    0.7.0.1

 */