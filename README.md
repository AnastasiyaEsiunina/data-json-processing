# Тест
- Обработка событий пользователя в приложении и сохранение данных в формате parquet на диск.
- Аналитика произошедших событий. Вычисление процента открывших приложение пользователей на следующей неделе после регистрации.

**Требования к запуску приложения:**
Запуск необходимо производить в JDK 8 версии

**Параметры запуска:**
* Для запуска приложения в режиме обработки - класс:
>ru.task.processing.json.loading.LoadingData
* Для запуска приложения в режиме аналитики - класс:
>ru.task.processing.json.analysis.AnalysisData
* Пути для исходных файлов и результатов в config.properties:
>local_src_file - полный путь к json файлу,  local_target_dir - полный путь к директории, куда необходимо сохранить файлы в формате parquet
