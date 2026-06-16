# Class Scheduling Management System (CSMS)

A Java Desktop (Swing) application designed for managing class schedules, strands, sections, subjects, announcements, comments, and student-teacher-admin messaging. Built as a senior high school capstone project.

---

## Security & Data Sanitization Notice

> [!IMPORTANT]
> All original database data (including classmate names, email addresses, phone numbers, plain-text passwords, and chat logs) has been **fully removed and sanitized**. The repository now contains only **anonymous mock data** for testing and development purposes.

---

## How to Run the Project

### Prerequisites
- **Java Development Kit (JDK) 8 or higher**
- **MySQL Database Server** (either local via XAMPP/WAMP, or via Docker)

---

### Step 1: Start the Database

Choose **one** of the options below to run your database server:

#### Option A: Run via Docker (Recommended)
If you have Docker installed, simply run the following command in the root directory:
```bash
docker compose up -d
```
This spins up a MySQL container, creates the database, and preloads the schema and mock data automatically.

#### Option B: Run via XAMPP / WAMP
1. Open XAMPP Control Panel.
2. Start the **MySQL** module (and Apache if you use phpMyAdmin).
3. Ensure MySQL is running on port `3306`. (No further manual database import is required!).

---

### Step 2: Running the Application

Upon starting, the application will automatically detect if the `csms` database exists. If it does not, it will:
1. Automatically create the `csms` database.
2. Load and execute the schema definitions.
3. Seed the database with the default mock accounts and reference data.

#### Running in NetBeans
1. Open NetBeans IDE.
2. Click **File > Open Project** and select the `CSMS` directory.
3. Clean and Build the project, then click **Run**.

#### Running from Command Line (Pre-compiled JAR)
You can run the pre-built executable directly using Java:
```bash
java -jar CSMS.jar
```

---

## Default Accounts

Use the following mock accounts to test different roles in the system:

| Role | Username | Password | Details |
| :--- | :--- | :--- | :--- |
| **Administrator** | `admin` | `password123` | Can create schedules, strands, and announcements. |
| **Teacher** | `teacher` | `password123` | Can view schedules, advisory classes, and chat with students/admin. |
| **Student** | `student` | `password123` | Can view class schedules, check announcements, and chat. |

---

## Configuration (`config.properties`)

The application database credentials are configurable. If you need to change the connection settings (e.g. if your local MySQL has a root password), edit the `CSMS/config.properties` file:

```properties
db_url=jdbc:mysql://localhost/
db_name=csms
db_username=root
db_password=your_custom_password
```

---

## Project Structure

- `CSMS/` - NetBeans Ant-based project source code, libraries, and resources.
- `CSMS MySQL files/` - Folder containing reference `schema.sql` and `data.sql` scripts.
- `Other CSMS files/` - Project documentation, draft slides, and experimental assets.
- `docker-compose.yml` - Container orchestration for the local database setup.
