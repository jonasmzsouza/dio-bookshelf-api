<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/jonasmzsouza/dio-bookshelf-api?style=flat-square&color=f1783f">
  <img alt="Repository size" src="https://img.shields.io/github/repo-size/jonasmzsouza/dio-bookshelf-api?style=flat-square&color=1f6feb">
  <a href="https://github.com/jonasmzsouza/dio-bookshelf-api/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/jonasmzsouza/dio-bookshelf-api/main?style=flat-square&color=2f74c0">
  </a>
</p>

<hr>

<p align="center">
  <a href="#-about-the-project">About</a> |
  <a href="#-technologies">Technologies</a> | 
  <a href="#-how-to">How to</a> | 
  <a href="#-author">Author</a> 
</p>

## 💻 About the project

Project developed through UML modeling with Mermaid and Java code implementation with Spring Boot Structure as a challenge for Santander Bootcamp 2023 - Fullstack Java+Angular at [Digital Innovation One](https://www.dio.me/).<br>
RESTful API to Registration of books and authors.

### Class Diagram

```mermaid
classDiagram
    direction LR
    class Book {
      - id: long
      - title: string
      - sinopse: string
      - publicationYear: int
      - publisher: string
      - coverUrl: string
      - numberOfPages: int
      - genre: string
      - authors: Author[]
    }
    
    class Genre {
      - id: long
      - name: string
      - books: Book[]
    }
    
    class Author {
      - id: long
      - name: string
      - photoUrl: string
      - biography: string
      - books: Book[]
    }

    Book "*" -- "1" Genre
    Book "*" -- "1..*" Author
```

---

## 🛠 Technologies

Technologies and tools that were used in the development of the project:

### **Languages | Environments | Frameworks | Libraries | Techniques**

- Java
- Spring Boot 3
- Spring Data JPA
- Spring Web
- H2 Database
- PostgreSQL
- OpenAPI (Swagger)

### **Utilities**

- Editor: **[IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/)** | **[JSON Editor Online](https://jsoneditoronline.org/)**
- Generative AI: **[ChatGPT](https://chat.openai.com/)**
- Emojis: **[Emoji Cheat Sheet](https://github.com/ikatyang/emoji-cheat-sheet)**, **[Markdown Emoji](https://gist.github.com/rxaviers/7360908)**
- Shields: **[Shields](https://shields.io/)**

---

## 🔧 How to

### Requirements
- Java 17

### Installation
1. clone repo
2. open project from file system
3. run app `Application.java`
4. navigate to `http://localhost:8080/swagger-ui.html`

---

## 👨‍💻 Author

<table>
  <tr>
    <td align="center">
      <a href="https://jonasmzsouza.github.io/">
         <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/61324433?v=4" width="100px;" alt=""/>
         <br />
         <sub><b>Jonas Souza</b></sub>
      </a>
    </td>
  </tr>
</table>
 
[![Github Badge](https://img.shields.io/badge/-jonasmzsouza-3e4957?style=flat-square&logo=Github&logoColor=white&link=https://github.com/jonasmzsouza)](https://github.com/jonasmzsouza) [![Linkedin Badge](https://img.shields.io/badge/-jonasmzsouza-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/jonasmzsouza/)](https://www.linkedin.com/in/jonasmzsouza/)
