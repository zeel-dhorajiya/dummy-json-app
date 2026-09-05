# DummyJSON Android App

A modern Android application built to showcase industry-standard Android development practices. This project demonstrates expertise in building scalable, maintainable, and testable applications using **Clean Architecture**, **Jetpack Compose**, and modern dependency injection.

---

## 🎯 Tech Stack & Core Skills Showcased

If you are a recruiter or technical lead evaluating this repository, here are the primary skills and technologies demonstrated in this codebase:

*   **UI / Presentation:** 100% **Jetpack Compose** (Material 3, Navigation Compose, Lazy Layouts, State Management).
*   **Architecture Pattern:** **MVVM** (Model-View-ViewModel) paired with **Clean Architecture** (Data, Domain, UI layers).
*   **Dependency Injection:** **Koin 4.0** (Modules, ViewModel Injection, Scopes).
*   **REST API Integration:** **Retrofit 2** & **OkHttp** with Interceptors.
*   **JSON Serialization:** **Kotlinx Serialization** for robust, type-safe JSON parsing.
*   **Asynchronous Programming:** **Kotlin Coroutines** & **Flows** (`StateFlow`, `collectAsState`).
*   **Local Storage:** `SharedPreferences` for secure JWT Token management.

---
## API Overview

### 1. Authentication (`/auth`)

| Endpoint | Method | Description |
|---|---|---|
| `/auth/login` | POST | Login with username/password, returns `accessToken` and `refreshToken` |
| `/auth/me` | GET | Get current authenticated user (requires Bearer token) |
| `/auth/refresh` | POST | Refresh session using `refreshToken` |

**Login Response:**
```json
{
  "id": 1,
  "username": "emilys",
  "email": "emily.johnson@x.dummyjson.com",
  "firstName": "Emily",
  "lastName": "Johnson",
  "gender": "female",
  "image": "https://dummyjson.com/icon/emilys/128",
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 2. Products (`/products`)

| Endpoint | Method | Description |
|---|---|---|
| `/products` | GET | Get all products (paginated, 30 per page by default) |
| `/products/{id}` | GET | Get a single product by ID |
| `/products/search?q={query}` | GET | Search products by title |
| `/products?sortBy={field}&order={asc\|desc}` | GET | Sort products |
| `/products/categories` | GET | Get all product categories |
| `/products/category-list` | GET | Get category slugs as string array |
| `/products/category/{slug}` | GET | Get products by category |
| `/products/add` | POST | Add a new product (simulated) |
| `/products/{id}` | PUT/PATCH | Update a product (simulated) |
| `/products/{id}` | DELETE | Delete a product (simulated) |

**Product fields:** `id`, `title`, `description`, `category`, `price`, `discountPercentage`, `rating`, `stock`, `tags`, `brand`, `sku`, `weight`, `dimensions`, `warrantyInformation`, `shippingInformation`, `availabilityStatus`, `reviews`, `returnPolicy`, `minimumOrderQuantity`, `meta`, `thumbnail`, `images`

### 3. Carts (`/carts`)

| Endpoint | Method | Description |
|---|---|---|
| `/carts` | GET | Get all carts |
| `/carts/{id}` | GET | Get a single cart |
| `/carts/user/{userId}` | GET | Get carts by user ID |
| `/carts/add` | POST | Add a new cart (simulated) |
| `/carts/{id}` | PUT/PATCH | Update a cart (simulated, supports `merge: true`) |
| `/carts/{id}` | DELETE | Delete a cart (simulated) |

**Cart fields:** `id`, `products[]` (with `id`, `title`, `price`, `quantity`, `total`, `discountPercentage`, `discountedTotal`, `thumbnail`), `total`, `discountedTotal`, `userId`, `totalProducts`, `totalQuantity`

### 4. Users (`/users`)

| Endpoint | Method | Description |
|---|---|---|
| `/users` | GET | Get all users (paginated) |
| `/users/{id}` | GET | Get a single user |
| `/users/search?q={query}` | GET | Search users by name |
| `/users/filter?key={key}&value={value}` | GET | Filter users by nested key/value |
| `/users?sortBy={field}&order={asc\|desc}` | GET | Sort users |
| `/users/{id}/carts` | GET | Get user's carts |
| `/users/{id}/posts` | GET | Get user's posts |
| `/users/{id}/todos` | GET | Get user's todos |
| `/users/add` | POST | Add a new user (simulated) |
| `/users/{id}` | PUT/PATCH | Update a user (simulated) |
| `/users/{id}` | DELETE | Delete a user (simulated) |

**User fields:** `id`, `firstName`, `lastName`, `maidenName`, `age`, `gender`, `email`, `phone`, `username`, `password`, `birthDate`, `image`, `bloodGroup`, `height`, `weight`, `eyeColor`, `hair`, `ip`, `address`, `macAddress`, `university`, `bank`, `company`, `ein`, `ssn`, `userAgent`, `crypto`, `role`

### 5. Todos (`/todos`)

| Endpoint | Method | Description |
|---|---|---|
| `/todos` | GET | Get all todos (paginated, 30 per page) |
| `/todos/{id}` | GET | Get a single todo |
| `/todos/random` | GET | Get a random todo |
| `/todos/user/{userId}` | GET | Get todos by user ID |
| `/todos/add` | POST | Add a new todo (simulated) |
| `/todos/{id}` | PUT/PATCH | Update a todo (simulated) |
| `/todos/{id}` | DELETE | Delete a todo (simulated) |

**Todo fields:** `id`, `todo`, `completed`, `userId`

### 6. Posts (`/posts`)

| Endpoint | Method | Description |
|---|---|---|
| `/posts` | GET | Get all posts (paginated) |
| `/posts/{id}` | GET | Get a single post |
| `/posts/search?q={query}` | GET | Search posts by title/body |
| `/posts/user/{userId}` | GET | Get posts by user ID |
| `/posts/{id}/comments` | GET | Get comments for a post |
| `/posts/add` | POST | Add a new post (simulated) |
| `/posts/{id}` | PUT/PATCH | Update a post (simulated) |
| `/posts/{id}` | DELETE | Delete a post (simulated) |

**Post fields:** `id`, `title`, `body`, `userId`, `tags[]`, `reactions` (likes/dislikes)

### 7. Comments (`/comments`)

| Endpoint | Method | Description |
|---|---|---|
| `/comments` | GET | Get all comments (paginated) |
| `/comments/{id}` | GET | Get a single comment |
| `/comments/post/{postId}` | GET | Get comments by post ID |
| `/comments/add` | POST | Add a new comment (simulated) |
| `/comments/{id}` | PUT/PATCH | Update a comment (simulated) |
| `/comments/{id}` | DELETE | Delete a comment (simulated) |

### 8. Recipes (`/recipes`)

| Endpoint | Method | Description |
|---|---|---|
| `/recipes` | GET | Get all recipes (paginated) |
| `/recipes/{id}` | GET | Get a single recipe |
| `/recipes/search?q={query}` | GET | Search recipes |
| `/recipes/tags` | GET | Get all recipe tags |
| `/recipes/tag/{tag}` | GET | Get recipes by tag |
| `/recipes/meal/{meal}` | GET | Get recipes by meal type |
| `/recipes/add` | POST | Add a new recipe (simulated) |
| `/recipes/{id}` | PUT/PATCH | Update a recipe (simulated) |
| `/recipes/{id}` | DELETE | Delete a recipe (simulated) |

### 9. Dynamic Images (`/image`)

Generate placeholder images with customizable parameters:

| Parameter | Description |
|---|---|
| Width/Height | Custom dimensions |
| Text | Custom overlay text |
| Background color | Custom background |
| Text color | Custom text color |
| Format | PNG, JPEG, etc. |
| Font family | Custom font |
| Font size | Custom size |
| Identicon | Generate identicon images |

Example: `https://dummyjson.com/image/300x200/008080/ffffff?text=Product+Image`

---

## Project Architecture

```
com.example.cleanarchtemplate/
├── data/
│   ├── DataRepository.kt          # Repository interface & implementation
│   ├── api/                       # API service interfaces (Retrofit/Ktor)
│   ├── model/                     # Data models / DTOs
│   └── repository/                # Repository implementations
├── domain/
│   ├── model/                     # Domain models
│   ├── repository/                # Repository interfaces
│   └── usecase/                   # Use cases
├── ui/
│   ├── main/                      # Main screen (product list)
│   ├── detail/                    # Product detail screen
│   ├── cart/                      # Cart screen
│   ├── auth/                      # Login/auth screens
│   ├── search/                    # Search screen
│   └── components/                # Reusable UI components
├── Navigation.kt                  # Navigation graph
├── NavigationKeys.kt              # Navigation key definitions
└── MainActivity.kt                # App entry point
```

## Tech Stack

| Layer | Technology |
|---|---|
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation3 (Compose) |
| Architecture | MVVM + Clean Architecture |
| State | Kotlin Flow + ViewModel |
| Network | Retrofit / Ktor Client |
| Serialization | Kotlinx Serialization |
| Image Loading | Coil |
| DI | Hilt / Koin (TBD) |

## Planned Screens

### 1. Login Screen
- Username/password input
- Calls `/auth/login` to obtain tokens
- Stores tokens in SharedPreferences/DataStore

### 2. Product List Screen
- Displays paginated products from `/products`
- Search bar with `/products/search` integration
- Category filter chips from `/products/categories`
- Pull-to-refresh support
- Infinite scroll pagination

### 3. Product Detail Screen
- Full product info from `/products/{id}`
- Image gallery with thumbnail strip
- Reviews section
- "Add to Cart" button

### 4. Cart Screen
- Displays user's cart from `/carts/user/{userId}`
- Quantity adjustment (PUT/PATCH)
- Remove items (DELETE)
- Total price calculation

### 5. Profile Screen
- User info from `/auth/me`
- User's posts from `/users/{id}/posts`
- User's todos from `/users/{id}/todos`

### 6. Search Screen
- Combined search across products and posts
- Search suggestions

## Implementation Plan

### Phase 1: Foundation
- [x] Set up network layer (Retrofit/Ktor client with base URL config)
- [x] Create data models for all API responses
- [x] Implement repository pattern for API calls
- [x] Set up dependency injection

### Phase 2: Core Features
- [x] Build authentication flow (login, token storage, refresh)
- [x] Create product list screen with pagination
- [x] Create product detail screen
- [x] Implement search functionality

### Phase 3: Cart & User
- [x] Build cart management (view, add, update, delete)
- [x] Create user profile screen
- [x] Display user's posts and todos

### Phase 4: Polish
- [x] Add error handling and loading states
- [x] Implement pull-to-refresh (Integrated via Bottom Navigation UI)
- [x] Add animations and transitions
- [x] Unit tests for repositories and use cases
- [x] UI tests for critical flows

## Example API Calls

```kotlin
// Login
POST https://dummyjson.com/auth/login
Body: { "username": "emilys", "password": "emilyspass" }

// Get products with pagination
GET https://dummyjson.com/products?limit=20&skip=0

// Search products
GET https://dummyjson.com/products/search?q=phone

// Get products by category
GET https://dummyjson.com/products/category/smartphones

// Get user's cart
GET https://dummyjson.com/carts/user/5
Header: Authorization: Bearer <accessToken>

// Add to cart
POST https://dummyjson.com/carts/add
Body: { "userId": 5, "products": [{ "id": 1, "quantity": 2 }] }

// Get user's todos
GET https://dummyjson.com/users/5/todos
```

## Notes

- All POST/PUT/PATCH/DELETE operations are **simulated** by the API and do not persist data
- Default pagination returns 30 items per page; use `limit=0` to fetch all items
- Auth tokens expire after `expiresInMins` (default 60 min); use `/auth/refresh` to renew
- User credentials are available at [dummyjson.com/users](https://dummyjson.com/users) (e.g., `emilys` / `emilyspass`)

## API Reference

Full documentation: [https://dummyjson.com/docs](https://dummyjson.com/docs)

---

Built with Jetpack Compose and Clean Architecture.
