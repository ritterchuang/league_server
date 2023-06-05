package org.lsj.gs.math.core.common.spinResultPgr.config;

import org.lsj.gs.math.core.common.spinResultPgr.config.resource.*;

// 客端設定生產者工廠
public class ClientConfigGtrFactory {

    public IClientConfigGtr create(int gameId) {
        switch (gameId) {
            case 1: return new ClientConfigGtr_001();
            case 301: return new ClientConfigGtr_301();
            case 302: return new ClientConfigGtr_302();
            case 303: return new ClientConfigGtr_303();
            case 304: return new ClientConfigGtr_304();
            case 305: return new ClientConfigGtr_305();
            case 306: return new ClientConfigGtr_306();
            case 307: return new ClientConfigGtr_307();
            case 308: return new ClientConfigGtr_308();
            case 309: return new ClientConfigGtr_309();
            case 310: return new ClientConfigGtr_310();
            case 311: return new ClientConfigGtr_311();
            case 312: return new ClientConfigGtr_312();
            case 313: return new ClientConfigGtr_313();
            case 314: return new ClientConfigGtr_314();
            case 315: return new ClientConfigGtr_315();
            case 316: return new ClientConfigGtr_316();
            case 320: return new ClientConfigGtr_320();
            case 323: return new ClientConfigGtr_323();
            case 324: return new ClientConfigGtr_324();
            case 993: return new ClientConfigGtr_993();
            default: return new ClientConfigGtr_983();
        }
    }
}
