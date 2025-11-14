Angular Authentication + To-Do App

A beginner-friendly Angular application built as an internship assignment.
Includes Signup, Login, and To-Do Management with form validations, route guards, and local storage–based persistence.

🌟 Features
🔐 1. Signup Page

Full Name, Email, Password, Confirm Password

Validations:

All fields required

Email format validation

Password minimum 6 characters

Confirm password must match

On success → user data saved (localStorage/service)

Redirects to Login page

🔑 2. Login Page

Email & Password required

Email format validation

Authenticates using saved signup data

On success → navigates to To-Do Page

On failure → shows error message

📝 3. To-Do List Page (Protected Route)

Accessible only after login.

Features:

Add new task

Title required

Optional description

Mark tasks as completed

Delete tasks

Separate lists:

Pending Tasks

Completed Tasks

Logout button (clears login state)

🎁 Bonus Features (Optional)

Task search

Filters: All / Completed / Pending

Add due date

Auto-sort based on due date

🧱 Project Structure
src/app
│
├── auth
│   ├── login
│   ├── signup
│   ├── auth.service.ts
│   └── auth.guard.ts
│
├── todo
│   ├── todo.component.ts
│   ├── todo.service.ts
│
├── shared
│   └── models
│
├── app-routing.module.ts
└── app.module.ts

🛠️ Technologies Used

Angular (latest version)

TypeScript

Reactive Forms

Local Storage for data persistence

Optional: Angular Material / Bootstrap

🚀 Run the Project
1️⃣ Clone the repository:
git clone https://github.com/your-username/angular-todo-app.git
cd angular-todo-app

2️⃣ Install dependencies:
npm install

3️⃣ Run the development server:
ng serve

4️⃣ Open in browser:
http://localhost:4200/

🔐 Default Test Credentials

You can sign up with any email/password.
Stored locally inside browser localStorage.

🧪 Testing Notes

Form validations tested

Unauthorized users cannot access To-Do page

Data persists across reloads using localStorage

📌 Future Improvements

JWT-based real backend authentication

Cloud Firestore / MongoDB storage

User profile page

UI enhancements
