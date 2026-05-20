# Project Plan

Update the 'My Tasks' app with the following:
1. Dynamic categories for short-term goals: Home screen shows 3 categories + 'View All'. 'View All' leads to a category management screen (add/rename/delete).
2. Dynamic categories for long-term goals: Home button leads to a category management screen (add/rename/delete) before entering the editor.
3. Long-term goals editor: Remove sidebar, remove top categories tab, remove save buttons. Implement auto-save.
4. Maintain persistent navigation state and modern Material 3 UI.

## Project Brief

# Project Brief: My Tasks

## Features
- **Dynamic Category Management**: A flexible system for both short-term and long-term goals that allows users to add, rename, and delete categories. The Home screen provides quick access to top short-term categories and a "View All" portal.
- **Short-Term Task Tracking**: A vertical to-do list for managing immediate tasks with descriptions and optional deadlines. Completing a task removes it from the system to keep the focus on active items.
- **Auto-Saving Long-Term Editor**: A distraction-free text editor for long-term objectives that supports strike-through formatting for completed segments and automatically saves progress without manual intervention.
- **Persistent State & Seamless UX**: The application features a professional loading screen and remembers the user's last visited screen, ensuring a continuous experience across sessions.

## High-Level Technical Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3) with Full Edge-to-Edge support
- **Architecture**: MVVM (Model-View-ViewModel)
- **Concurrency**: Kotlin Coroutines & Flow
- **Persistence**: **Room Database** (for task and goal storage) and **Jetpack DataStore** (for navigation state persistence) using **KSP** for code generation.
- **Navigation**: Jetpack Navigation Compose

## Implementation Steps

### Task_1_DataLayerPersistence: Implement Room database for short-term and long-term goals, and DataStore for persistent navigation state.
- **Status:** COMPLETED
- **Updates:** Implemented Room database for Task and LongTermGoal entities.
- **Acceptance Criteria:**
  - Room database with Task and LongTermGoal entities and DAOs created
  - DataStore implementation for saving and restoring navigation state
  - Repository classes for data operations implemented

### Task_2_NavSplashHome: Set up Jetpack Navigation with state persistence, a 2-second Splash screen, and the Home screen UI.
- **Status:** COMPLETED
- **Updates:** Implemented Jetpack Navigation Compose with routes for Splash, Home, ShortTermGoals, and LongTermGoals.
- **Acceptance Criteria:**
  - Compose Navigation graph implemented and integrated with DataStore
  - Splash screen with 2-second delay transitions to Home/Last screen
  - Home screen provides navigation to Short-term and Long-term goal sections

### Task_3_GoalsFeatures: Develop the Short-term goals (to-do list with categories and deadlines) and Long-term goals (editor with strike-through) screens.
- **Status:** COMPLETED
- **Updates:** Developed ShortTermGoalsScreen with task listing, adding (with DatePicker), and deletion upon completion.
- **Acceptance Criteria:**
  - Short-term goals feature: add tasks, categorize, and complete (delete) functionality
  - Long-term goals feature: text editor with strike-through support and category selection
  - Vertical list for short-term tasks and persistent storage for long-term goals

### Task_4_DynamicCategoriesAndHome: Implement dynamic category management for short-term and long-term goals and update the Home screen UI and navigation flow.
- **Status:** COMPLETED
- **Updates:** Implemented dynamic category management for both Short-Term and Long-Term goals. 
Updated Room database with CategoryEntity and migrated Task/LongTermGoal entities. 
Created CategoryManagementScreen for adding, renaming, and deleting categories. 
Updated Home screen to display top 3 ST categories and a 'View All' option. 
Updated navigation flow: Home -> LT Category Management -> LongTermGoalsScreen. 
The coder agent also proactively simplified the Long-Term editor (removed sidebar, tabs, and buttons) and implemented auto-save.
- **Acceptance Criteria:**
  - Room database updated with dynamic Category entities for ST and LT goals
  - Category management screens implemented (Add/Rename/Delete) for both ST and LT
  - Home screen updated to display top 3 ST categories and a 'View All' option
  - Navigation flow updated: Home -> LT Category Management -> Editor
- **Duration:** N/A

### Task_5_EditorRefinementThemingVerify: Refine the Long-term goals editor with auto-save, apply Material 3 theming/icon, and perform final Run and Verify.
- **Status:** IN_PROGRESS
- **Acceptance Criteria:**
  - Long-term editor UI simplified (no sidebar, no tabs, no save buttons)
  - Auto-save functionality implemented for long-term goal text
  - Vibrant Material 3 theme, Edge-to-Edge display, and adaptive app icon implemented
  - Critic_agent verifies application stability (no crashes)
  - Alignment with user requirements confirmed
  - Report critical UI issues
  - Build pass
  - App does not crash
  - Make sure all existing tests pass
- **StartTime:** 2026-05-19 21:06:43 IST

