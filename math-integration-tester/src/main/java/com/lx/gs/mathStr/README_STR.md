# lx-math-simulator Project
數值模擬器

## 一、 程式入口
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/main.java


## 二、新增數值設定檔
### 1. 新增數值設定檔列舉
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/config/resources/ConstStrConfigResource.java

### 2. 新增情境設定檔
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/config/resources/scenarioHlr

### 3. 新增玩家工廠設定檔
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/config/resources/gamePlayerFactory

### 4. 新增共用水池設定檔
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/config/resources/poolCtr

### 5. 新增遊戲中心管理器設定檔(設定多房間與模擬方式)
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/config/resources/gameCenterMgr

### 6. 新增統計設定檔(是否輸出原始資料)
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/config/resources/agencyStn

### 7. 新增強控演算法工具包設定檔
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/config/resources/controlAlgorithm

### 備註: 
> 以上組成代理模擬器設定: agencyStrConfig

> 設定是否打印日誌: application.properties


## 三、新增模組
### 1. 設定模擬器類型
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/core/entity/ConstStr.java

### 2. 新增局產生器
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/games

### 3. 設定局產生器工廠
> lx-math-simulator/src/main/java/com/lx/gs/mathStr/core/module/boardGtr/BoardGtrFactory.java
