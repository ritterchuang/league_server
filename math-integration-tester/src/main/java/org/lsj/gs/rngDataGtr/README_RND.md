# lx-math-rng-data-generator Project
隨機亂數產生器 專案

## 一、 程式入口
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/main.java


## 二、新增設定檔
### 1. 新增亂數設定檔列舉
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/config/resources/ConstRndGtrConfig.java

### 2. 新增亂數設定檔
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/config/resources

### 3. 新增測項結構列舉
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/core/entity/ConstRngDataGtr.java

### 4. 新增測項結構
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/config/entity/rngDataTester/testCase


## 三、新增模組
### 1. 數值模擬器新增局產生器
> lx-math/src/main/java/com/lx/gs/math/games

### 2. 設定亂數產生器工廠 依照遊戲類型
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/core/module/rngDataGtr/RngDataGtrFactory.java

### 3. 新增檢測器
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/core/module/rngDataTester/module/TestCaseCtr

### 4. 設定檢測器工廠
> lx-math-rng-data-generator/src/main/java/com/lx/gs/rngDataGtr/core/module/rngDataTester/module/TestCaseCtr/TestCaseCtrFactory.java

## 四、模擬器驗證亂數
### 記得在 controlConfigContainStringArray 設定指定的演算法類型