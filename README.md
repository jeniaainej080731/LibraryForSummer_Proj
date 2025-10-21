# 📚 LibraryForSummer_Proj

A simple **web library application** for managing books and tracking their lending history.  
Built with **Java + Spring Boot**, uses **Thymeleaf** templates for UI, and **PostgreSQL** as the database.  

---

## ✨ Features

- 📖 **Book catalog**: browse, view details, and manage books  
- 👤 **Readers & history**: track who borrowed books and when  
- 🔄 **CRUD operations** for books and lending records  
- 🖼️ **Image upload** support (e.g., book covers stored on the server)  

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot (MVC, Data JPA)  
- **Frontend:** Thymeleaf, JavaScript  
- **Database:** PostgreSQL  
- **Build Tool:** Maven  
- **File Storage:** local server directory for uploaded images  

---

### ✅ Prerequisites

- **JDK** 17+  
- **Maven** 3.8+  
- **PostgreSQL** 13+  
- Free port (default: `8080`)  

---

### ⚙️ Application Configuration
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate/JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server
server.port=8080

# File uploads
app.upload-dir=uploads

---

### 📁 Project Structure
```bash
LibraryForSummer_Proj/
├─ pom.xml
├─ db_backup.sql
├─ src/
│  ├─ main/
│  │  ├─ java/             # Controllers, Services, Repositories, Entities
│  │  └─ resources/
│  │     ├─ templates/     # Thymeleaf HTML templates
│  │     ├─ static/        # CSS, JS, images
│  │     └─ application.properties
│  └─ test/                # Unit tests
```

### 📌 Roadmap
- **🔍 Book search & filtering** (author, genre, ISBN)

- **🔑 User roles & authentication** (admin, librarian, reader)

- **🌐 REST API for integration**

- **🐳 Docker support** (App + PostgreSQL)
