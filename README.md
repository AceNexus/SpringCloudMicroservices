# SpringCloudMicroservices

## 1.【文件】

* [SQL](_doc%2Fsql)
* [Postman](_doc%2FSpringCloudMicroservices.postman_collection.json)

## 2.【服務】

* eureka-service: http://127.0.0.1:7001/
* account-service: http://127.0.0.1:8001/actuator/info

## 3.【Git 版本控制】

* 主要分支
    * **master**: 所有功能和修復最終都會合併到此分支，保持穩定的版本。
* 功能分支
    * **account-service**: 開發與帳號服務相關的功能，保持功能開發的獨立性。
    * **eureka-service**: 開發與 Eureka 服務（如服務註冊和發現）相關的功能，確保服務的有效性。
    * **common**: 包含工具類、配置文件等共用程式碼，以促進程式碼重用性。

## 4.【Git 開發流程】：以帳號服務新增功能為例

* **4-1. 建立新分支**：從 `account-service` 建立新分支為 `20240929-update-account-info`
  ```bash
  git checkout account-service
  git pull origin master
  git checkout -b 20240929-update-account-info
  ```
* **4-2. 開發功能**：在 `20240929-update-account-info` 分支上進行功能開發
  ```bash
  git add .
  git commit -m "[feat] 新增修改帳號資訊功能"
  ```
* **4-3. 合併請求至 account-service**：使用 GitHub 提交 Pull Request
  ```text
  將 20240929-update-account-info 合併回 account-service
  ```
* **4-4. 合併請求至 master**：使用 GitHub 提交 Pull Request
  ```text
  將 account-service 合併回 master
  ```
* **4-5. 標籤穩定版本**：合併到 master 後，將穩定版本打標籤
  ```bash
  git checkout master
  git pull origin master
  git tag -a v1.0.0 -m "Release version 1.0.0"
  git push origin v1.0.0
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

## 6.【Docker 部署服務】：以 eureka-service 為例

* **6-1. 建立資料夾**
  ```shell
  sudo mkdir -p /opt/SpringCloudMicroservices
  sudo chown -R ubuntu:ubuntu /opt/SpringCloudMicroservices
  sudo mkdir -p /opt/SpringCloudMicroservices/eureka-service
  ```
* **6-2. 部署 JAR 文件**
  ```text
  將 eureka-service 編譯完成後的 jar 檔，放在 /opt/SpringCloudMicroservices/eureka-service 資料夾下
  ```
* **6-3. 建立 Dockerfile 檔案**
  ```text
  sudo touch /opt/SpringCloudMicroservices/eureka-service/Dockerfile
  ```
* **6-4. 撰寫 Dockerfile 文件 (簡單範例)**
  ```text
  FROM openjdk:8-jre-slim
  COPY eureka-service*.jar ./eureka-service.jar
  ENTRYPOINT ["java", "-jar", "eureka-service.jar"]
  ```
* **6-5. 建構 Docker 映像檔**
  ```shell
  docker build -t eureka-service .
  ```
* **6-6. 啟動容器**
  ```shell
  docker run -di --name=eureka-service -p 7001:7001 eureka-service
  ```