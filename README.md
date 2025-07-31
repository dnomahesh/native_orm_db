# native_orm_db

A lightweight, Kotlin-based ORM (Object Relational Mapping) library for Android, offering a modern and dependency-free alternative to `org.litepal.android:core`.

---

## 🚀 Features

- Minimal and easy-to-use ORM
- No annotation processors required
- Simple SQLite operations with model-based data mapping
- Supports Create, Read, Update, Delete (CRUD)
- Thread-safe and optimized for Android apps
- Works offline (no internet or backend required)

---




## 📦 Installation

### Step 1: Add dependency

Add the following to your app-level `build.gradle` file:
---
groovy
---
dependencies {
    implementation 'io.github.dnomahesh:native_orm_db:1.0.0'
}
---

Make sure you have mavenCentral() in your project-level repositories block:
---

groovy
---
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
---

⚙️ Setup
Initialize the library in your Application class:

kotlin
---
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        OrmDatabase.init(this)
    }
}
---
Define your data model:

kotlin
---
data class User(
    var id: Long = 0,
    var name: String,
    var email: String
) : OrmModel()
---
🛠️ Usage
Create (Insert)
kotlin
---
val user = User(name = "Alice", email = "alice@example.com")
user.save()
Read (Query All)
---
kotlin
---
val allUsers = OrmDatabase.findAll(User::class)
---
Read (Query by ID)
kotlin
---
---
val user = OrmDatabase.findById(User::class, 1L)
---
Update
kotlin
---
---
user.name = "Updated Name"
user.update()
---
---
Delete
kotlin
---
---

user.delete()
---
🧪 Testing
---
You can write unit tests by mocking the Context and using the same public API as in runtime.
---

🙌 Contribution
---
Want to contribute? Great! Please fork the repository and create a pull request with your improvements or bug fixes.
---
We follow Conventional Commits and appreciate clean, readable code.
---