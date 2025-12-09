# Music Tagger
Search for music based on user-assigned tags! Still a work in progress.

## 🧭 Overview  
**Music Tagger** is a **full-stack music discovery platform** that lets users **search, organize, and explore songs** using **community-created tags**.  
Instead of traditional genre-based search, users can collaboratively tag songs with any keywords — moods, activities, instruments, or anything else — creating a more flexible and personalized way to find music.

Built with a modern Java + React stack, this project will demonstrate full CRUD functionality, database design, REST API development, and a responsive front-end interface.

---

## 🚗 Project Roadmap
- ~~Create database structure~~ 
- **Implement CRUD endpoints** (current)
- Build frontend app
- Connect back and front end with axios

---

## 🚀 Tech Stack  

### **Backend**
- **Java 17**  
- **Spring Boot** – RESTful API development  
- **JDBC + PostgreSQL** – database connectivity and persistence  
- **Maven** – build automation and dependency management  

### **Frontend**
- **React + Vite** – responsive SPA frontend  
- **Axios** – API communication  
- **Tailwind CSS** – clean, modern UI design  

### **Tools**
- **Postman** – API testing  
- **pgAdmin** – database management  
- **Git + GitHub** – version control  
- **IntelliJ IDEA** – backend development  
- **VS Code** – frontend development  

---

## 🧩 Features (Planned)
- **Song Search:** Find songs by title, artist, or tag.  
- **Tagging System:** Add and manage user-created tags for songs (maintained by user voting system).
- **Browse by Tags:** Explore songs grouped by community-driven tags.  
- **User Accounts:** Simple authentication and user-specific tagging.  
- **Responsive UI:** Modern interface optimized for desktop and mobile.  

---

## 🗂️ Database Design (Planned)

**Tables:**
- `users` – user info and credentials  
- `songs` – song metadata (title, link, etc.)  
- `artists` 
- `sources` - albums/soundtracks
- `tags` – tag definitions  
- `song_tags` – many-to-many join table linking songs and tags  

---

## ⚙️ API Endpoints (Preview)

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/songs` | Retrieve all songs |
| `GET` | `/api/songs/{id}` | Get song details |
| `POST` | `/api/songs` | Add new song |
| `GET` | `/api/tags` | Retrieve all tags |
| `POST` | `/api/tags` | Create new tag |
| `POST` | `/api/songs/{id}/tags` | Assign a tag to a song |
| `GET` | `/api/search?tag={tag}` | Search songs by tag |

