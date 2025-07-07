# 📚 Library Management Microservice

Микросервис (или модуль) для управления книгами и читателями с помощью Spring Boot + JPA.  
Позволяет выполнять CRUD-операции, назначать/отвязывать книги, искать людей и книги по первым буквам, а также отправлять данные асинхронно.

---

## 🔧 Технологии

- Java 17+
- Spring Boot
- Spring Data JPA + Hibernate
- Spring Web (REST API)
- PostgreSQL / H2

---

## 📁 Сущности

### 👤 Person
- `id`
- `name`
- `birthYear`
- `List<Book> books`

### 📕 Book
- `id`
- `title`
- `author`
- `year`
- `Person owner` *(nullable)*

> Один человек может иметь много книг, но одна книга — только у одного человека.

## 🛠 Основные возможности

- 📋 CRUD для `Person` и `Book`
- 🔍 Поиск книг по первым буквам (`findByNameStartsWith`, `findByTitleStartsWith`)
- 🔗 Назначение книги человеку
- ❌ Отвязка книги от человека
- 🚀 Асинхронная отправка данных (например, логирование или статистика)

👨‍💻 Автор
Абылай Аубакиров
