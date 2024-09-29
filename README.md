# SpringCloudMicroservices

## 【文件】

* [SQL](_doc%2Fsql)
* [Postman](_doc%2FSpringCloudMicroservices.postman_collection.json)

## 【服務】

* eureka-service: http://127.0.0.1:7001/
* account-service: http://127.0.0.1:8001/actuator/info

## 【Git】

* 主要分支
  * **master**: 所有功能和修復最終都會合併到此分支，保持穩定的版本。
* 功能分支
  * **account-service**: 開發與帳號服務相關的功能，保持功能開發的獨立性。
  * **eureka-service**: 開發與 Eureka 服務（如服務註冊和發現）相關的功能，確保服務的有效性。
  * **common**: 包含工具類、配置文件等共用程式碼，以促進程式碼重用性。

## 【Git 開發流程】
* 以帳號服務修改帳號資訊功能為例
  * **建立新分支**：從 `account-service` 建立新分支並確保本地分支是最新的：
  ```bash
  git checkout account-service
  git pull origin account-service
  git checkout -b 20240929-update-account-info
  ```
  * **開發功能**：在 `20240929-update-account-info` 分支上進行修改帳號資訊的功能開發：
  ```bash
  git add .
  git commit -m "[feat] 新增修改帳號資訊功能"
  ```
  * **提交 Pull Request**：使用 GitHub 提交 PR 將 `20240929-update-account-info` 合併回 `account-service`。
  * **合併至 master**：完成後再提交 PR 將 account-service 合併回 master，並確保所有變更經過審查。
  * **標籤穩定版本**：合併到 master 後，將穩定版本打標籤：
  ```bash
  git checkout master
  git pull origin master
  git tag -a v1.0.0 -m "Release version 1.0.0"
  git push origin v1.0.0
  ```

## 【Git 提交信息標籤】
* 在 Git 提交信息中，使用標籤有助於描述提交的目的和內容。以下是常見標籤
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