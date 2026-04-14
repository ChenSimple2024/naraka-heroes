# Naraka Heroes (永劫无间英雄录)

An Android application built with modern development tools providing a comprehensive guide for "Naraka: Bladepoint" heroes.
一款基于现代 Android 开发技术栈构建的《永劫无间》英雄百科应用。

---

## 🌏 Language / 语言
- [English](#english-version)
- [中文版本](#中文版本)

---

## English Version

### 🚀 Overview
Naraka Heroes is a showcase app for the popular battle royale game "Naraka: Bladepoint". It features a smooth, gesture-driven UI to browse hero information, skills, and backgrounds in both Chinese and English.

### 🛠 Tech Stack
- **Kotlin**: The primary programming language, leveraging coroutines for efficient concurrency.
- **Jetpack Compose**: A modern toolkit for building native UI with a declarative approach.
- **Hilt (Dagger)**: Provides a standard way to incorporate Dagger dependency injection into an Android app.
- **Room Persistence**: A layer over SQLite that allows for fluent database access while harnessing the full power of SQLite.
- **Retrofit & OkHttp**: Used for network requests and REST API interaction.
- **Coil**: An image loading library for Android backed by Kotlin Coroutines.
- **Coroutines & Flow**: Simplifies asynchronous programming and provides reactive data streams.
- **MVVM Architecture**: Ensures separation of concerns, making the app easier to maintain and test.
- **Material 3**: Implements the latest Material Design guidelines for a modern look and feel.

### 📂 Project Structure
```text
app/src/main/java/com/naraka/heroes/
├── di/                # Hilt Modules for Dependency Injection
├── model/             # Data entities (Hero, HeroSkill, HeroWithSkills)
├── network/           # API definitions and Interceptors for remote data
├── persistence/       # Room Database, DAOs, and Type Converters
├── repository/        # Repository pattern (Single Source of Truth)
├── ui/                # UI Layer
│   ├── navigation/    # Compose Navigation (NavGraph)
│   ├── screen/        # Individual Compose Screens (List, Detail)
│   ├── theme/         # Design System (Color, Type, Theme)
│   └── viewmodel/     # State management using ViewModels
└── util/              # Helper classes and extensions
```

### 🔑 Important Files
- **`HeroListScreen.kt`**: The main entry point featuring a `HorizontalPager` with custom scale animations and language switching logic.
- **`HeroDetailScreen.kt`**: Displays detailed hero profiles, skills, and allows users to edit/save a custom bio stored in the local DB.
- **`HeroRepository.kt`**: Manages data flow between the local Room database and the remote/mock network source.
- **`NarakaDatabase.kt`**: Defines the Room database schema and versioning.
- **`heroes.json` (assets)**: Initial seed data for the application.

---

## 中文版本

### 🚀 项目概述
“永劫无间英雄录”是一款为热门动作竞技游戏《永劫无间》开发的百科类应用。它通过流畅的手势操作和精致的 UI 交互，为玩家提供中英双语的英雄资料、技能介绍及角色故事。

### 🛠 技术栈介绍
- **Kotlin**: 项目首选开发语言，利用其强大的协程能力处理并发。
- **Jetpack Compose**: 现代声明式 UI 工具包，极大简化了界面开发并提升了交互流畅度。
- **Hilt (Dagger)**: 依赖注入库，解耦代码逻辑，提高应用的可扩展性和可测试性。
- **Room 数据库**: 官方推荐的持久化库，负责英雄档案数据的本地存储。
- **Retrofit & OkHttp**: 处理网络数据通信，支持 RESTful API。
- **Coil**: 高效的图片加载库，完美支持 Compose 且基于协程开发。
- **协程与 Flow**: 简化异步任务处理，提供响应式的数据流观察。
- **MVVM 架构**: 明确关注点分离，确保 UI 与业务逻辑的独立性。
- **Material 3**: 采用最新的 Material Design 3 规范，打造现代化的视觉审美。

### 📂 目录层级
```text
app/src/main/java/com/naraka/heroes/
├── di/                # Hilt 依赖注入模块配置
├── model/             # 数据实体类 (Hero, HeroSkill, HeroWithSkills 等)
├── network/           # 远程 API 定义及拦截器 (Mock 数据处理)
├── persistence/       # Room 数据库配置、DAO 接口及类型转换器
├── repository/        # 仓库层 (负责协调本地与远程数据源)
├── ui/                # UI 表示层
│   ├── navigation/    # Compose 导航配置 (NavGraph)
│   ├── screen/        # 独立 UI 页面 (列表页、详情页)
│   ├── theme/         # 设计系统 (颜色、字体、主题皮肤)
│   └── viewmodel/     # 状态管理 (ViewModel)
└── util/              # 工具类与扩展函数
```

### 🔑 核心文件作用说明
- **`HeroListScreen.kt`**: 应用主界面。包含一个带缩放动画的轮播图 (`HorizontalPager`)，支持实时中英文切换。
- **`HeroDetailScreen.kt`**: 英雄详情页。展示立绘、档案、技能组。包含一个特色功能：允许用户修改并保存个性化的“英雄简介”。
- **`HeroRepository.kt`**: 数据中枢。负责从资源文件/网络加载数据并同步至 Room 数据库，确保 UI 获取的是最新状态。
- **`NarakaDatabase.kt`**: Room 数据库定义，管理本地数据表结构。
- **`heroes.json` (assets)**: 存放初始化的英雄数据，用于首次安装时的数据库填充。

---

## 📬 Contact / 联系方式

If you have any questions or suggestions, feel free to reach out!
如有任何疑问或建议，欢迎联系：

- **Email**: 2972261698@qq.com
