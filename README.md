# Pokemons-VK
## **Artem Rekalov**
#### Тестовое задание для прохождения на стажировку в VK (направление Android-разработчик в )

### Было сделано
1. Основной экран со списокм покемонов
2. Переход на детальный экран покемона (отображение картинок и 6 ключевых полей)
3. Пагинация по 20 покемонов с прогресс барами
4. Обработка ошибок сети

### Стек
- Kotlin
- Android navigation
- Paging3
- Kotlin Coroutines
- Retrofit
- Dagger2
- Glide
- ssp, sdp <https://github.com/intuit/sdp> (лучшая адаптация приложения к разным размерам экрана)

### Архитектура
1. App (основная логика приложения)
2. Data (подключение к серверу, POJO, PagingSource)

### Особенности реализации
- Clean Architecture
- MVVM
- Single Activity
- Навигация с помощью navigation components
- Пагинация с помощью Paging 3
- Kotlin Coroutines + LiveData
- Progress bars
- Корректное отображение при повороте экрана
- Темная тема
- Красивая иконка