# SpringCloudMicroservices

## 1.【專案文件】

* [SQL](_doc%2Fsql)
* [Postman](_doc%2FSpringCloudMicroservices.postman_collection.json)

## 2. 【系統服務架構】

* 微服務
    * eureka-service: http://127.0.0.1:7001/
        - 開發與 Eureka 服務（如服務註冊和發現）相關的功能，確保服務的有效性。
    * account-service: http://127.0.0.1:8001/actuator/info
        - 開發與帳號服務相關的功能，保持功能開發的獨立性。
* 共用工具
    * common
        - 包含工具類、配置文件等共用程式碼，以促進程式碼重用性。

## 3.【Git 分支管理策略】

* 環境分支
    * development：開發整合環境
    * testing：QA 測試環境
    * staging：預生產環境，最終測試驗證
    * production：正式環境，最終穩定版本
* 服務分支
    * eureka-service：管理微服務註冊與發現
    * account-service：帳號服務
* 工具分支
    * common：共用工具與組件

## 4.【Git 開發流程】：以帳號服務更新帳號資訊為例

* 4-1. 建立新功能分支
    * 從 account-service 建立新分支 account-service-20240929-update-account-info
      ```bash
      # 確保本地最新
      git checkout account-service
      git pull origin account-service
      
      # 建立並切換到新分支
      git checkout -b account-service-20240929-update-account-info
      ```

* 4-2. 開發與提交變更
    * 在 account-service-20240929-update-account-info 分支上進行開發，完成後提交程式碼
    ```bash
    git add .
    git commit -m "[feat] update account info API"
    git push origin account-service-20240929-update-account-info
    ```

* 4-3. 提交 Pull Request (PR)
    * 在 GitHub 建立 Pull Request (PR)，請求合併至 account-service 分支，並進行 Code Review
    ```text
    PR 標題: [feat] update account info API
    描述: 此 PR 新增帳號資訊的修改功能
    ```

    * 4-4. 合併至 account-service
        * 經過 Code Review 後，將 account-service-20240929-update-account-info 分支合併回 account-service
      ```text
      git checkout account-service
      git pull origin account-service
      git merge --no-ff account-service-20240929-update-account-info
      git push origin account-service
      ```

* 4-5. 部署至不同環境
    * development：開發整合環境
    ```text
    git checkout development
    git merge --no-ff account-service
    git push origin development
    ```
    * testing：QA 測試環境
    ```text
    git checkout testing
    git merge --no-ff development
    git push origin testing
    ```
    * staging：預生產環境，最終測試驗證
    ```text
    git checkout staging
    git merge --no-ff testing
    git push origin staging
    ```
    * production：正式環境，最終穩定版本
    ```text
    git checkout production
    git merge --no-ff staging
    git push origin production
    ```

## 5.【Git 提交信息標籤】

- **feat**: 新增功能（feature）
- **fix**: 修復錯誤（bug fix）
- **docs**: 修改文檔（documentation）
- **style**: 代碼風格變更（不影響功能，例如格式、空格等）
- **refactor**: 代碼重構（重構代碼而不改變其外部行為）
- **perf**: 優化性能（性能改進）
- **test**: 添加或修正測試（unit tests, integration tests）
- **chore**: 其他日常維護工作（如更新依賴、配置文件等）
- **build**: 影響構建系統或外部依賴的變更（如 Maven、Gradle、npm 等）
- **ci**: 持續集成的相關變更（如 CI/CD 設定）
- **revert**: 撤銷先前的提交

## 6. 使用 IntelliJ IDEA 啟動 Eureka Service 及 Account Service

* 6-1.
  啟動 [EureKaServiceApplication.java](eureka-service%2Fsrc%2Fmain%2Fjava%2Forg%2Fecommerce%2Feureka%2FEureKaServiceApplication.java)
    * 右鍵點擊 -> Run 'EureKaServiceApplication'
* 6-2.
  啟動 [AccountServiceApplication.java](account-service%2Fsrc%2Fmain%2Fjava%2Forg%2Fecommerce%2Faccount%2FAccountServiceApplication.java)
    * 右鍵點擊 -> Run 'AccountServiceApplication'
    * 在 IntelliJ IDEA 中的 "Run/Debug Configurations" 設定環境變數，找到 "Environment variables" 的欄位加入
      --spring.profiles.active=development

## 7.【手動使用 Docker 部署服務】：以 eureka-service 為例

* **7-1. 建立資料夾**
  ```shell
  sudo mkdir -p /opt/SpringCloudMicroservices
  sudo chown -R ubuntu:ubuntu /opt/SpringCloudMicroservices
  sudo mkdir -p /opt/SpringCloudMicroservices/eureka-service
  ```
* **7-2. 部署 JAR 文件**
  ```text
  將 eureka-service 編譯完成後的 jar 檔，放在 /opt/SpringCloudMicroservices/eureka-service 資料夾下
  ```
* **7-3. 建立 Dockerfile 檔案**
  ```text
  sudo touch /opt/SpringCloudMicroservices/eureka-service/Dockerfile
  ```
* **7-4. 撰寫 Dockerfile 文件 (簡單範例)**
  ```text
  FROM openjdk:8-jre-slim
  COPY eureka-service*.jar ./eureka-service.jar
  ENTRYPOINT ["java", "-jar", "eureka-service.jar", "--spring.profiles.active=development"]
  ```
* **7-5. 建構 Docker 映像檔**
  ```shell
  docker build -t eureka-service .
  ```
* **7-6. 啟動容器**
  ```shell
  docker run -di --name=eureka-service -p 7001:7001 eureka-service
  ```