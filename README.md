# DogManagement-API
## API概要
里親募集の犬の情報を管理するアプリ
- 犬の名前、性別、年齢、犬種、所在地域をまとめたアプリです。
- 犬情報の検索、登録、修正、削除が可能です。
## 作成背景
いつかは犬を飼ってみたい。。。そんな中犬アレルギーの発覚。
今回身近なワンちゃんを題材にし、里親制度を知ってほしくてこのアプリを作成しました。
行き場のないペットを救うことができること。成長後のカラダの大きさや性格を予測しやすい。ペットショップやブリーダーから購入するほど費用がかからないというメリットがあります。

## API仕様書
## アプリケーション概要図
![アプリケーション概要図](https://github.com/kinta21/DogManagement-API/assets/141032732/23c213ce-60df-447a-a013-8436333cb188)

## 使用技術
### バックエンド
- Java 17.0.8
- SpringBoot 3.1.4
- MyBatis
### その他
- MySQL 8.0.35 
- Docker 24.0.5
- 自動テスト
- CI (Checkstyle, Discordへの通知, 自動テスト実行)
## アプリケーション機能一覧
|  機能 |  詳細 |  URL  |
| ---- | ---- | ---- |
|  検索  |  IDを指定して検索する  |  /dogs/{id}  |
|  作成  |  犬の情報を新規登録する  |  /dogs  |
|  更新  |  指定したIDの犬の情報を更新する  |  /dogs/{id}  |
|  削除  |  指定したIDの犬の情報を削除する  |  /dogs/{id}  |
## DB定義
テーブル名 dogs_database

|  カラム  |  データ型  |  キー  |  NOTNULL  |  備考  |
| ---- | ---- | ---- | ---- | ---- |
|  id  |  int  |  PRIMARY KEY  |    |  登録ID |
|  name  |  VARCHAR(20)  |    |  NOT NULL  |  名前  |
|  dogSex  |  VARCHAR(20)  |    |  NOT NULL  |  性別  |
|  age  |  VARCHAR(20)  |    |  NOT NULL  |  年齢  |
|  dogBreed  |  VARCHAR(20) |    |  NOT NULL  |  犬種  |
|  region  |  VARCHAR(20)  |    |  NOT NULL  |  所在地域  |

## GitHub Actionsを使用したCIの実装
- checkstyleのチェック
- discordへの通知
- junitのレポート

### 自動テスト
### Discordへのテスト結果通知
![スクリーンショット 2024-02-02 18 23 08](https://github.com/kinta21/DogManagement-API/assets/141032732/a2763477-6234-4371-bd5c-0e84783560fd)
