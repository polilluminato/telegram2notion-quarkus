# Telegram2Notion Quarkus

<p align="center">
  <a href="https://www.java.com/"><img src="https://img.shields.io/badge/Java-3277ae?style=for-the-badge&logoColor=white"></a>
  <a href="https://quarkus.io/"><img src="https://img.shields.io/badge/Quarkus-ff004b?style=for-the-badge&logoColor=white"></a>
  <a href="https://maven.apache.org/"><img src="https://img.shields.io/badge/Maven-fb9835?style=for-the-badge&logoColor=white"></a>
  <a href="https://www.apache.org/licenses/LICENSE-2.0.html"><img src="https://img.shields.io/badge/licence-Apache%202.0-yellow?style=for-the-badge&"></a>
</p>

Telegram2Notion is a small application developed with the [Quarkus framework](https://quarkus.io/) with which it is possible to save links that are sent to a Telegram Bot in a Notion database. It is also possible to tag the links sent to the bot with hashtags to better organize your database.

Here is a small demo of the application:

![Telegram2Notion Quarkus Demo](./docs/videos/telegram-notion-quarkus-demo.gif)

With some small configurations you can configure the application to suit other use cases such as:

- send expenses and add custom tag
- send short messages to simulate a todo-list
- add appointments in a database with calendar view
- ... your imagination is the limit 🤓

You can read the blog post on my blog here: [Save Bookmarks to Notion Database with Telegram and Quarkus](https://blog.albertobonacina.com/save-bookmarks-to-notion-database-with-telegram-and-quarkus).

## 📗 Documentation

The project's documentation, made with [Docs.page](https://docs.page/) by [Invertase](https://invertase.io/), is at [Telegram2Notion Quarkus Docs](https://docs.page/polilluminato/telegram2notion-quarkus)

## 🚀 Deploy 

As a Quarkus application made with [Maven](https://maven.apache.org/index.html) you can build an _über-jar_ with the following command:

```bash
./mvnw package -Dquarkus.package.type=uber-jar
```

and run on a server with Java installed with:

```bash
nohup java -jar telegram2notion-*.jar &
```

more information can be found in the [Quarkus docs](https://docs.page/polilluminato/telegram2notion-quarkus/pages/quarkus-doc) section of the documentation.

## 💎 Contributing

If you have any idea, feel free to fork it and submit your changes back to me.

## 📋 License

```
Copyright 2023 Alberto Bonacina

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```