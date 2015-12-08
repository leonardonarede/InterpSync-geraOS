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
/*  48:    */ import java.util.List;
/*  49:    */ import java.util.logging.Level;
/*  50:    */ import java.util.logging.Logger;
/*  51:    */ 
/*  52:    */ public class IntInicial
/*  53:    */ {
/*  54:    */   public void executar()
/*  55:    */   {
/*  56: 60 */     Coxins_ERPDAO coxproddao = new Coxins_ERPDAO();
/*  57: 61 */     Coxins_INTERDBDAO coxintdao = new Coxins_INTERDBDAO();
/*  58: 62 */     int total = 0;
/*  59: 63 */     int cont = 0;
/*  60:    */     try
/*  61:    */     {
/*  62: 65 */       total = coxproddao.listarTodos().size();
/*  63: 66 */       for (Coxins_ERP coxin : coxproddao.listarTodos()) {
/*  64: 67 */         if ((coxin.getCodCX() != null) && (coxin.getDescrCX() != null))
/*  65:    */         {
/*  66: 68 */           if (coxintdao.localizarPorId(coxin.getCodCX()).getDescrCX() == null)
/*  67:    */           {
/*  68: 69 */             coxin.setStei(1);
/*  69: 70 */             coxintdao.inserir(coxin);
/*  70:    */           }
/*  71:    */           else
/*  72:    */           {
/*  73: 72 */             coxin.setStei(1);
/*  74: 73 */             coxintdao.atualizar(coxin);
/*  75:    */           }
/*  76: 75 */           coxproddao.atualizar(coxin);
/*  77: 76 */           System.out.println("Coxin: " + coxin.getCodCX());
/*  78:    */         }
/*  79:    */       }
/*  80:    */     }
/*  81:    */     catch (SQLException ex)
/*  82:    */     {
/*  83: 80 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/*  84:    */     }
/*  85:    */     catch (ClassNotFoundException ex)
/*  86:    */     {
/*  87: 82 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/*  88:    */     }
/*  89:    */     catch (Exception ex)
/*  90:    */     {
/*  91: 84 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/*  92:    */     }
/*  93: 87 */     Desreforma_ERPDAO desproddao = new Desreforma_ERPDAO();
/*  94: 88 */     Desreforma_INTERDBDAO desintdao = new Desreforma_INTERDBDAO();
/*  95: 89 */     total = 0;
/*  96: 90 */     cont = 0;
/*  97:    */     try
/*  98:    */     {
/*  99: 92 */       total = desproddao.listarTodos().size();
/* 100: 93 */       for (Desreforma_ERP desenho : desproddao.listarTodos()) {
/* 101: 94 */         if ((desenho.getCodDE() != null) && (desenho.getDescrDE() != null))
/* 102:    */         {
/* 103: 95 */           if (desintdao.localizarPorID(desenho.getCodDE()).getCodDE() == null)
/* 104:    */           {
/* 105: 96 */             desenho.setStEI(1);
/* 106: 97 */             desintdao.inserir(desenho);
/* 107:    */           }
/* 108:    */           else
/* 109:    */           {
/* 110: 99 */             desenho.setStEI(1);
/* 111:100 */             desintdao.atualizar(desenho);
/* 112:    */           }
/* 113:102 */           desproddao.atualizar(desenho);
/* 114:103 */           System.out.println("Desenho: " + desenho.getCodDE());
/* 115:    */         }
/* 116:    */       }
/* 117:    */     }
/* 118:    */     catch (SQLException ex)
/* 119:    */     {
/* 120:107 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 121:    */     }
/* 122:    */     catch (ClassNotFoundException ex)
/* 123:    */     {
/* 124:109 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 125:    */     }
/* 126:    */     catch (Exception ex)
/* 127:    */     {
/* 128:111 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 129:    */     }
/* 130:114 */     Laudosrec_ERPDAO lauproddao = new Laudosrec_ERPDAO();
/* 131:115 */     Laudosrec_INTERDBDAO lauintdao = new Laudosrec_INTERDBDAO();
/* 132:116 */     total = 0;
/* 133:117 */     cont = 0;
/* 134:    */     try
/* 135:    */     {
/* 136:119 */       total = lauproddao.listarTodos().size();
/* 137:120 */       for (Laudosrec_ERP laudo : lauproddao.listarTodos()) {
/* 138:121 */         if ((laudo.getCodLA() != null) && (laudo.getDescrLA() != null))
/* 139:    */         {
/* 140:122 */           if (lauintdao.localizarPorID(laudo.getCodLA()).getCodLA() == null)
/* 141:    */           {
/* 142:123 */             laudo.setStEI(1);
/* 143:124 */             lauintdao.inserir(laudo);
/* 144:    */           }
/* 145:    */           else
/* 146:    */           {
/* 147:126 */             laudo.setStEI(1);
/* 148:127 */             lauintdao.atualizar(laudo);
/* 149:    */           }
/* 150:129 */           lauproddao.atualizar(laudo);
/* 151:130 */           System.out.println("Laudo: " + laudo.getCodLA());
/* 152:    */         }
/* 153:    */       }
/* 154:    */     }
/* 155:    */     catch (SQLException ex)
/* 156:    */     {
/* 157:135 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 158:    */     }
/* 159:    */     catch (ClassNotFoundException ex)
/* 160:    */     {
/* 161:137 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 162:    */     }
/* 163:    */     catch (Exception ex)
/* 164:    */     {
/* 165:139 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 166:    */     }
/* 167:142 */     Marcas_ERPDAO marproddao = new Marcas_ERPDAO();
/* 168:143 */     Marcas_INTERDBDAO marintdao = new Marcas_INTERDBDAO();
/* 169:144 */     total = 0;
/* 170:145 */     cont = 0;
/* 171:    */     try
/* 172:    */     {
/* 173:147 */       total = marproddao.listarTodos().size();
/* 174:148 */       for (Marcas_ERP marca : marproddao.listarTodos()) {
/* 175:149 */         if ((marca.getCodMA() != null) && (marca.getDescrMA() != null))
/* 176:    */         {
/* 177:150 */           if (marintdao.localizarPorID(marca.getCodMA()).getDescrMA() == null)
/* 178:    */           {
/* 179:151 */             marca.setStEI(1);
/* 180:152 */             marintdao.inserir(marca);
/* 181:    */           }
/* 182:    */           else
/* 183:    */           {
/* 184:154 */             marca.setStEI(1);
/* 185:155 */             marintdao.atualizar(marca);
/* 186:    */           }
/* 187:157 */           marproddao.atualizar(marca);
/* 188:158 */           System.out.println("Marca " + marca.getCodMA());
/* 189:    */         }
/* 190:    */       }
/* 191:    */     }
/* 192:    */     catch (SQLException ex)
/* 193:    */     {
/* 194:163 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 195:    */     }
/* 196:    */     catch (ClassNotFoundException ex)
/* 197:    */     {
/* 198:165 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 199:    */     }
/* 200:    */     catch (Exception ex)
/* 201:    */     {
/* 202:167 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 203:    */     }
/* 204:170 */     Medidas_ERPDAO medproddao = new Medidas_ERPDAO();
/* 205:171 */     Medidas_INTERDBDAO medintdao = new Medidas_INTERDBDAO();
/* 206:172 */     total = 0;
/* 207:173 */     cont = 0;
/* 208:    */     try
/* 209:    */     {
/* 210:175 */       total = medproddao.listarTodos().size();
/* 211:176 */       for (Medidas_ERP medida : medproddao.listarTodos()) {
/* 212:177 */         if ((medida.getCodME() != null) && (medida.getDescrME() != null))
/* 213:    */         {
/* 214:178 */           if (medintdao.localizarPorID(medida.getCodME()).getCodME() == null)
/* 215:    */           {
/* 216:179 */             medida.setStEI(1);
/* 217:180 */             medintdao.inserir(medida);
/* 218:    */           }
/* 219:    */           else
/* 220:    */           {
/* 221:182 */             medida.setStEI(1);
/* 222:183 */             medintdao.atualizar(medida);
/* 223:    */           }
/* 224:185 */           medproddao.atualizar(medida);
/* 225:186 */           System.out.println("Medida: " + medida.getCodME());
/* 226:    */         }
/* 227:    */       }
/* 228:    */     }
/* 229:    */     catch (SQLException ex)
/* 230:    */     {
/* 231:191 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 232:    */     }
/* 233:    */     catch (ClassNotFoundException ex)
/* 234:    */     {
/* 235:193 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 236:    */     }
/* 237:    */     catch (Exception ex)
/* 238:    */     {
/* 239:195 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 240:    */     }
/* 241:198 */     Modelos_ERPDAO modproddao = new Modelos_ERPDAO();
/* 242:199 */     Modelos_INTERDBDAO modintdao = new Modelos_INTERDBDAO();
/* 243:200 */     total = 0;
/* 244:201 */     cont = 0;
/* 245:    */     try
/* 246:    */     {
/* 247:203 */       total = modproddao.listarTodos().size();
/* 248:204 */       for (Modelos_ERP modelo : modproddao.listarTodos()) {
/* 249:205 */         if ((modelo.getCodMO() != null) && (modelo.getDescrMO() != null))
/* 250:    */         {
/* 251:206 */           if (modintdao.localizarPorID(modelo.getCodMO()).getCodMO() == null)
/* 252:    */           {
/* 253:207 */             modelo.setStEI(1);
/* 254:208 */             modintdao.inserir(modelo);
/* 255:    */           }
/* 256:    */           else
/* 257:    */           {
/* 258:210 */             modelo.setStEI(1);
/* 259:211 */             modintdao.atualizar(modelo);
/* 260:    */           }
/* 261:213 */           modproddao.atualizar(modelo);
/* 262:214 */           System.out.println("Modelos: " + modelo.getCodMO());
/* 263:    */         }
/* 264:    */       }
/* 265:    */     }
/* 266:    */     catch (SQLException ex)
/* 267:    */     {
/* 268:218 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 269:    */     }
/* 270:    */     catch (ClassNotFoundException ex)
/* 271:    */     {
/* 272:220 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 273:    */     }
/* 274:    */     catch (Exception ex)
/* 275:    */     {
/* 276:222 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 277:    */     }
/* 278:225 */     OS_ERPDAO osproddao = new OS_ERPDAO();
/* 279:226 */     OS_INTERDBDAO osintdao = new OS_INTERDBDAO();
/* 280:227 */     total = 0;
/* 281:228 */     cont = 0;
/* 282:    */     try
/* 283:    */     {
/* 284:230 */       total = osproddao.listarTodos().size();
/* 285:231 */       for (OS_ERP os : osproddao.listarTodos())
/* 286:    */       {
/* 287:232 */         os.setERPID("1");
/* 288:233 */         if ((os.getNrOS() != null) || (os.getNrMatricula() != null))
/* 289:    */         {
/* 290:234 */           if (osintdao.localizarPorID(os.getNrOS().intValue()).getDtEmisID() == null)
/* 291:    */           {
/* 292:235 */             if (os.getStEI() == 1) {
/* 293:236 */               os.setStEI(2);
/* 294:    */             }
/* 295:238 */             if ((os.getStEI() == 3) || (os.getStEI() == 4)) {
/* 296:239 */               os.setStEI(5);
/* 297:    */             }
/* 298:241 */             osintdao.inserir(os);
/* 299:    */           }
/* 300:    */           else
/* 301:    */           {
/* 302:243 */             if (os.getStEI() == 1) {
/* 303:244 */               os.setStEI(2);
/* 304:    */             }
/* 305:246 */             if ((os.getStEI() == 3) || (os.getStEI() == 4)) {
/* 306:247 */               os.setStEI(5);
/* 307:    */             }
/* 308:249 */             osintdao.atualizar(os);
/* 309:    */           }
/* 310:251 */           osproddao.atualizar(os);
/* 311:252 */           System.out.println("Os: " + os.getNrOS());
/* 312:    */         }
/* 313:    */       }
/* 314:    */     }
/* 315:    */     catch (SQLException ex)
/* 316:    */     {
/* 317:256 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 318:    */     }
/* 319:    */     catch (ClassNotFoundException ex)
/* 320:    */     {
/* 321:258 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 322:    */     }
/* 323:    */     catch (Exception ex)
/* 324:    */     {
/* 325:260 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 326:    */     }
/* 327:263 */     Osvr_ERPDAO osvrpdao = new Osvr_ERPDAO();
/* 328:264 */     Osvr_INTERDBDAO osvridao = new Osvr_INTERDBDAO();
/* 329:265 */     total = 0;
/* 330:266 */     cont = 0;
/* 331:    */     try
/* 332:    */     {
/* 333:269 */       total = osvrpdao.listarTodos().size();
/* 334:270 */       for (Osvr_ERP osvr : osvrpdao.listarTodos()) {
/* 335:271 */         if ((osvr.getNrOS() != null) && (osvr.getTipoReg() != null) && (osvr.getCodigoReg() != null))
/* 336:    */         {
/* 337:272 */           if (osvridao.localizarPorID(osvr.getNrOS(), osvr.getTipoReg(), osvr.getCodigoReg()).getNrOS() == null)
/* 338:    */           {
/* 339:273 */             osvr.setFilialTit("002");
/* 340:274 */             osvridao.inserir(osvr);
/* 341:    */           }
/* 342:    */           else
/* 343:    */           {
/* 344:276 */             osvr.setFilialTit("002");
/* 345:277 */             osvridao.atualizar(osvr);
/* 346:    */           }
/* 347:279 */           osvrpdao.atualizar(osvr);
/* 348:280 */           System.out.println("Os: " + osvr.getNrOS() + " Reg: " + osvr.getTipoReg() + " Cod: " + osvr.getCodigoReg());
/* 349:    */         }
/* 350:    */       }
/* 351:    */     }
/* 352:    */     catch (SQLException ex)
/* 353:    */     {
/* 354:284 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 355:    */     }
/* 356:    */     catch (ClassNotFoundException ex)
/* 357:    */     {
/* 358:286 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 359:    */     }
/* 360:    */     catch (Exception ex)
/* 361:    */     {
/* 362:288 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 363:    */     }
/* 364:291 */     Parcprazos_ERPDAO parpdao = new Parcprazos_ERPDAO();
/* 365:292 */     Parcprazos_INTERDBDAO paridao = new Parcprazos_INTERDBDAO();
/* 366:293 */     total = 0;
/* 367:294 */     cont = 0;
/* 368:    */     try
/* 369:    */     {
/* 370:297 */       total = parpdao.listarTodos().size();
/* 371:298 */       for (Parcprazos_ERP parc : parpdao.listarTodos()) {
/* 372:299 */         if ((parc.getCodPR() != null) && (parc.getParcPR() != null))
/* 373:    */         {
/* 374:300 */           if (paridao.localizarPorID(parc.getCodPR()).getCodPR() == null)
/* 375:    */           {
/* 376:301 */             parc.setStEI(1);
/* 377:302 */             paridao.inserir(parc);
/* 378:    */           }
/* 379:    */           else
/* 380:    */           {
/* 381:304 */             parc.setStEI(1);
/* 382:305 */             paridao.atualizar(parc);
/* 383:    */           }
/* 384:307 */           parpdao.atualizar(parc);
/* 385:308 */           System.out.println("Parcelas: " + parc.getCodPR());
/* 386:    */         }
/* 387:    */       }
/* 388:    */     }
/* 389:    */     catch (SQLException ex)
/* 390:    */     {
/* 391:312 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 392:    */     }
/* 393:    */     catch (ClassNotFoundException ex)
/* 394:    */     {
/* 395:314 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 396:    */     }
/* 397:    */     catch (Exception ex)
/* 398:    */     {
/* 399:316 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 400:    */     }
/* 401:319 */     Prazos_ERPDAO prapdao = new Prazos_ERPDAO();
/* 402:320 */     Prazos_INTERDBDAO praidao = new Prazos_INTERDBDAO();
/* 403:321 */     total = 0;
/* 404:322 */     cont = 0;
/* 405:    */     try
/* 406:    */     {
/* 407:324 */       total = prapdao.listarTodos().size();
/* 408:325 */       for (Prazos_ERP prazo : prapdao.listarTodos()) {
/* 409:326 */         if ((prazo.getCodPR() != null) && (prazo.getDescrPR() != null))
/* 410:    */         {
/* 411:327 */           if (praidao.localizarPorID(prazo.getCodPR()).getDescrPR() == null)
/* 412:    */           {
/* 413:328 */             prazo.setStEI(1);
/* 414:329 */             praidao.inserir(prazo);
/* 415:    */           }
/* 416:    */           else
/* 417:    */           {
/* 418:331 */             prazo.setStEI(1);
/* 419:332 */             praidao.atualizar(prazo);
/* 420:    */           }
/* 421:334 */           prapdao.atualizar(prazo);
/* 422:335 */           System.out.println("Prazo: " + prazo.getCodPR());
/* 423:    */         }
/* 424:    */       }
/* 425:    */     }
/* 426:    */     catch (SQLException ex)
/* 427:    */     {
/* 428:339 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 429:    */     }
/* 430:    */     catch (ClassNotFoundException ex)
/* 431:    */     {
/* 432:341 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 433:    */     }
/* 434:    */     catch (Exception ex)
/* 435:    */     {
/* 436:343 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 437:    */     }
/* 438:346 */     Regioes_ERPDAO regpdao = new Regioes_ERPDAO();
/* 439:347 */     Regioes_INTERDBDAO regidao = new Regioes_INTERDBDAO();
/* 440:348 */     total = 0;
/* 441:349 */     cont = 0;
/* 442:    */     try
/* 443:    */     {
/* 444:351 */       total = regpdao.listarTodos().size();
/* 445:352 */       for (Regioes_ERP regiao : regpdao.listarTodos()) {
/* 446:353 */         if ((regiao.getCodRE() != null) && (regiao.getDescrRE() != null))
/* 447:    */         {
/* 448:354 */           if (regidao.localizarPorID(regiao.getCodRE()).getDescrRE() == null)
/* 449:    */           {
/* 450:355 */             regiao.setStEI(1);
/* 451:356 */             regidao.inserir(regiao);
/* 452:    */           }
/* 453:    */           else
/* 454:    */           {
/* 455:358 */             regiao.setStEI(1);
/* 456:359 */             regidao.atualizar(regiao);
/* 457:    */           }
/* 458:361 */           regpdao.atualizar(regiao);
/* 459:362 */           System.out.println("Regiao" + regiao.getCodRE());
/* 460:    */         }
/* 461:    */       }
/* 462:    */     }
/* 463:    */     catch (SQLException ex)
/* 464:    */     {
/* 465:367 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 466:    */     }
/* 467:    */     catch (ClassNotFoundException ex)
/* 468:    */     {
/* 469:369 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 470:    */     }
/* 471:    */     catch (Exception ex)
/* 472:    */     {
/* 473:371 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 474:    */     }
/* 475:374 */     Reparos_ERPDAO reppdao = new Reparos_ERPDAO();
/* 476:375 */     Reparos_INTERDBDAO repidao = new Reparos_INTERDBDAO();
/* 477:376 */     total = 0;
/* 478:377 */     cont = 0;
/* 479:    */     try
/* 480:    */     {
/* 481:379 */       total = reppdao.listarTodos().size();
/* 482:380 */       for (Reparos_ERP reparo : reppdao.listarTodos()) {
/* 483:381 */         if ((reparo.getCodRP() != null) && (reparo.getDescrRP() != null))
/* 484:    */         {
/* 485:382 */           if (repidao.localizarPorID(reparo.getCodRP()).getDescrRP() == null)
/* 486:    */           {
/* 487:383 */             reparo.setStei(1);
/* 488:384 */             repidao.inserir(reparo);
/* 489:    */           }
/* 490:    */           else
/* 491:    */           {
/* 492:386 */             reparo.setStei(1);
/* 493:387 */             repidao.atualizar(reparo);
/* 494:    */           }
/* 495:389 */           reppdao.atualizar(reparo);
/* 496:390 */           System.out.println("Reparo: " + reparo.getCodRP());
/* 497:    */         }
/* 498:    */       }
/* 499:    */     }
/* 500:    */     catch (SQLException ex)
/* 501:    */     {
/* 502:395 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 503:    */     }
/* 504:    */     catch (ClassNotFoundException ex)
/* 505:    */     {
/* 506:397 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 507:    */     }
/* 508:    */     catch (Exception ex)
/* 509:    */     {
/* 510:399 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 511:    */     }
/* 512:402 */     Resp_CliDAO resppdao = new Resp_CliDAO();
/* 513:403 */     Resp_CliINTERDBDAO respidao = new Resp_CliINTERDBDAO();
/* 514:404 */     total = 0;
/* 515:405 */     cont = 0;
/* 516:    */     try
/* 517:    */     {
/* 518:407 */       total = resppdao.listarTodos().size();
/* 519:408 */       for (Resp_Cli resp : resppdao.listarTodos()) {
/* 520:409 */         if ((resp.getCNPJCPF() != null) && (resp.getRespAVencer() != null))
/* 521:    */         {
/* 522:410 */           if (respidao.localizarPorID(resp.getCNPJCPF()).getCNPJCPF() == null)
/* 523:    */           {
/* 524:411 */             resp.setStEI(1);
/* 525:412 */             respidao.inserir(resp);
/* 526:    */           }
/* 527:    */           else
/* 528:    */           {
/* 529:414 */             resp.setStEI(1);
/* 530:415 */             respidao.atualizar(resp);
/* 531:    */           }
/* 532:417 */           resppdao.atualizar(resp);
/* 533:418 */           System.out.println("Responsabilidade: " + resp.getCNPJCPF());
/* 534:    */         }
/* 535:    */       }
/* 536:    */     }
/* 537:    */     catch (SQLException ex)
/* 538:    */     {
/* 539:423 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 540:    */     }
/* 541:    */     catch (ClassNotFoundException ex)
/* 542:    */     {
/* 543:425 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 544:    */     }
/* 545:    */     catch (Exception ex)
/* 546:    */     {
/* 547:427 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 548:    */     }
/* 549:431 */     Servicos_ERPDAO servpdao = new Servicos_ERPDAO();
/* 550:432 */     Servicos_INTERDBDAO servidao = new Servicos_INTERDBDAO();
/* 551:433 */     total = 0;
/* 552:434 */     cont = 0;
/* 553:    */     try
/* 554:    */     {
/* 555:436 */       total = servpdao.listarTodos().size();
/* 556:437 */       for (Servicos_ERP serv : servpdao.listarTodos()) {
/* 557:438 */         if ((serv.getCodSE() != null) && (serv.getDescrSE() != null))
/* 558:    */         {
/* 559:439 */           if (servidao.localizarPorID(serv.getCodSE()).getDescrSE() == null)
/* 560:    */           {
/* 561:440 */             serv.setStei(1);
/* 562:441 */             servidao.inserir(serv);
/* 563:    */           }
/* 564:    */           else
/* 565:    */           {
/* 566:443 */             serv.setStei(1);
/* 567:444 */             servidao.atualizar(serv);
/* 568:    */           }
/* 569:446 */           servpdao.atualizar(serv);
/* 570:447 */           System.out.println("Servicos: " + serv.getCodSE());
/* 571:    */         }
/* 572:    */       }
/* 573:    */     }
/* 574:    */     catch (SQLException ex)
/* 575:    */     {
/* 576:452 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 577:    */     }
/* 578:    */     catch (ClassNotFoundException ex)
/* 579:    */     {
/* 580:454 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 581:    */     }
/* 582:    */     catch (Exception ex)
/* 583:    */     {
/* 584:456 */       Logger.getLogger(IntInicial.class.getName()).log(Level.SEVERE, null, ex);
/* 585:    */     }
/* 586:    */   }
/* 587:    */ }



/* Location:           C:\Users\Leonardo\Desktop\INT\InterpSync.jar

 * Qualified Name:     br.com.interpsync.controller.IntInicial

 * JD-Core Version:    0.7.0.1

 */