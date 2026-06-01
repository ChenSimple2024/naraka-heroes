# Naraka Heroes (永劫无间英雄录)

An Android application built with enterprise-level Multi-Module Clean Architecture, providing a comprehensive guide for "Naraka: Bladepoint" heroes.
一款基于企业级多模块 Clean Architecture 架构构建的《永劫无间》英雄百科应用。

---

## 🌏 Language / 语言
- [English](#english-version)
- [中文版本](#中文版本)

---

## English Version

### 🚀 Overview
Naraka Heroes is a showcase app for the popular battle royale game "Naraka: Bladepoint". It features a smooth, gesture-driven UI to browse hero information, skills, and backgrounds in both Chinese and English. The project follows the **SOT (Single Source of Truth)** principle and **Offline-First** strategy.

### 🛠 Tech Stack
- **Architecture**: Multi-Module Clean Architecture + MVVM.
- **UI Framework**: Jetpack Compose (Declarative UI) with Material 3.
- **Dependency Injection**: Hilt (Dagger) with custom Qualifiers for Local/Remote API switching.
- **Networking**: Retrofit 2 + **Sandwich** (ApiResponse encapsulation) + **Moshi** (JSON parsing).
- **Persistence**: Room 2.6.1 (with auto-migration and schema export).
- **Image Loading**: **Coil** & **Landscapist** (Glide support).
- **Asynchronous**: Kotlin Coroutines & Flow (StateFlow).
- **Build System**: Gradle **Kotlin DSL (.kts)** + **Version Catalog** (libs.versions.toml) + **buildSrc**.

### 📂 Project Structure
```text
├── app/               # UI Layer (Compose Screens, ViewModels, Navigation)
├── buildSrc/          # Build configuration and global SDK versions
├── core-data/         # Repository Layer (Single Source of Truth & Offline-First)
├── core-database/     # Persistence Layer (Room Database, DAOs, Entities)
├── core-model/        # Model Layer (Pure Kotlin data entities)
├── core-network/      # Network Layer (Retrofit, Moshi, and Modular Local API)
└── gradle/            # Version Catalog management
```

### 🌟 Key Features
- **Modular Local API**: Served from `core-network` assets. Automatically maps local image paths, allowing seamless switching to remote servers.
- **Smart UI Rendering**: Uses `BlendMode.Screen` to dynamically filter black backgrounds from game logos.
- **Offline-First**: Data is fetched from the local API and cached in Room; subsequent loads prioritize the database.
- **Responsive Design**: Standardized 3:4 aspect ratio for hero portraits to match official artwork.

---

## 中文版本

### 🚀 项目概述
“永劫无间英雄录”是一款为热门动作竞技游戏《永劫无间》开发的百科类应用。它采用**多模块 Clean Architecture** 架构，遵循 **SSOT (唯一真实数据源)** 原则和**离线优先**策略，为玩家提供流畅的中英双语英雄资料查询体验。

### 🛠 技术栈介绍
- **架构设计**: 多模块 Clean Architecture + MVVM。
- **UI 框架**: Jetpack Compose (声明式 UI) + Material 3。
- **依赖注入**: Hilt (Dagger)，支持自定义注解实现本地/远程 API 无缝切换。
- **网络处理**: Retrofit 2 + **Sandwich** (ApiResponse 封装) + **Moshi** (高性能 JSON 解析)。
- **数据存储**: Room 2.6.1 (支持 Schema 导出与自动迁移)。
- **图片加载**: **Coil** & **Landscapist** (支持调色板与动画)。
- **异步流**: Kotlin Coroutines + Flow (StateFlow)。
- **构建系统**: Gradle **Kotlin DSL (.kts)** + **Version Catalog** (统一版本管理) + **buildSrc**。

### 📂 目录层级
```text
├── app/               # UI 表现层 (Compose 页面、ViewModel、导航)
├── buildSrc/          # 构建配置管理 (统一 SDK 版本号)
├── core-data/         # 数据仓库层 (离线优先策略逻辑实现)
├── core-database/     # 持久化层 (Room 数据库配置、DAO 及 Entity)
├── core-model/        # 底层模型层 (纯 Kotlin 数据实体类)
├── core-network/      # 网络通信层 (Retrofit 配置、Moshi 驱动及本地 Mock API)
└── gradle/            # 依赖版本目录 (libs.versions.toml)
```

### 🌟 核心特色
- **模块化本地 API**: 英雄数据与立绘完全内聚在 `core-network` 模块中，支持 `file:///android_asset/` 自动映射，模拟真实网络环境。
- **智能 UI 滤色**: 针对黑色背景的 Logo，应用 Compose **`BlendMode.Screen` (滤色模式)**，实现无损透明融合。
- **离线优先**: 优先读取 Room 数据库，无数据时请求本地 API 并自动同步至本地缓存。
- **视觉优化**: 英雄立绘统一采用 **3:4 黄金比例** 展示，完美契合官方原画。

---

## 📬 Contact / 联系方式

If you have any questions or suggestions, feel free to reach out!
如有任何疑问或建议，欢迎联系：

- **Email**: 2972261698@qq.com
