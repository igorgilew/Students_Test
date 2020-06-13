# Students_Test
REST API для таблицы Студенты [id(pk), name(not null), passport(unique)]

<h3>#СУБД: Postgres</h3>

<h3>#API:</h3>
<h4>Read:</h4>
    1)Get-запрос на api/students - вернет список всех студентов
<br>      Ответ: JSON
<br>        Пример ответа:
          [
            {
              "id": 1,
              "name": "Tom",
              "passport": "2342-2342"
            }
          ]

<br>2)Get-запрос api/students/{id} - вернет студента по id
<br>Пример api/student/1
<h4>Create:</h4>
    3)Post-запрос api/students
    RequestBody - Json- объект - новый студент
    <br>Ответ: созданный объект (в случае успеха)

<h4>Update:</h4>
    4)Put-запрос api/students
    RequestBody - Json- объект - изменяемый студент с новыми данными, указывать айди требуемого студента
    <br>Ответ: созданный объект (в случае успеха)
    Пример RequestBody (меняем имя у объекта с id=1 с Tom на John):
          {
              "id": 1,
              "name": "John",
              "passport": "2342-2342"
            }
   <br><h4>Delete:</h4>
    5)Delete-запрос api/students/{id}
    <br>Ответ: void (status: no-content)
    
