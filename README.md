# 📋My Todo App

작업기간: 2026년 3월 11일 → 2026년 3월 11일

## 😀 프로젝트 개요

🏷️프로젝트 명 : My Todo App

🗓️개발 기간 : 2026.03.11~2026.03.11 (1일)

👥 인원 : 유민하

🔗 링크 : https://github.com/YuMinha/todoApp

---

## 📖 소개

> SpringBoot와 Thymeleaf를 활용해 CRUD 기능을 구현하고, 인라인 수정 기능을 추가한 
할 일 정리 웹 프로그램
> 

---

## 🎯 배경

- 그동안 세팅이 완료된 프로젝트에서 유지보수와 기능 추가 위주의 경험을 쌓아왔습니다. 하지만 프레임워크의 초기 설정부터 전체적인 레이아웃 설계, 데이터 흐름의 시작과 끝을 온전히 내 손으로 구축해보고자 이 프로젝트를 시작했습니다.
- Spring Boot의 동작 원리를 이해하고, 프론트엔드와 백엔드 간의 유기적인 데이터 통신(CRUD)을 직접 구현하며 웹 개발의 A to Z를 익히는 것을 목표로 삼았습니다.

---

## ⚙️ 기술 스택

- Backend : Java21. Spring Boot, Spring Data JPA
- Frontend : HTML, Thymeleaf, Bootstrap 5, JavaScript
- Database : MySQL
- Tool : InteliJ GitHub

---

## 🚀 주요 기능

### ☑️ 할 일 추가/ 삭제

: 기본적인 데이터 생성 및 삭제 기능

![스크린샷 2026-03-11 162003.png](%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2026-03-11_162003.png)

![스크린샷 2026-03-11 162014.png](%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2026-03-11_162014.png)

- 사용자가 텍스트를 입력하고 `추가` 버튼을 누르면 실시간으로 리스트에 반영됩니다.

![스크린샷 2026-03-11 162044.png](%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2026-03-11_162044.png)

![스크린샷 2026-03-11 162051.png](%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2026-03-11_162051.png)

![스크린샷 2026-03-11 162100.png](%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2026-03-11_162100.png)

- 불필요한 항목은 `삭제` 버튼으로 즉시 제거하며, 실수 방지를 위해 확인 창을 띄우도록 했습니다.

### ☑️ 상태 토글

: 체크박스를 이용한 완료 상태 업데이트

![image.png](image.png)

![image.png](image%201.png)

- 할 일 완료 시 체크 박스를 누르면 서버에 반영되며, 취소선 처리를 통해 시각적으로 완료 상태를 보여줍니다.

### ☑️ 인라인 수정 기능

: 페이지 이동 없이 그 자리에서 바로 할 일 수정

![스크린샷 2026-04-02 151516.png](%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2026-04-02_151516.png)

![image.png](image%202.png)

![스크린샷 2026-04-02 151534.png](%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2026-04-02_151534.png)

- 사용자가 `수정` 버튼을 누르면 그 자리에서 수정이 가능합니다.
- 수정 창에서 수정 후 `확인` 버튼을 누르면 반영되고 `취소` 버튼을 누르면 반영되지 않습니다.

---

## ⚡ 트러블 슈팅

### 🚧 Docker MySQL 연결 및 포트 매핑 오류

**문제 상황**

- Spring Boot 실행 시 `Communications link failure` 에러 발생
- 데이터베이스 연결 실패

**원인 분석**

- docker-compose에서 설정한 외부 포트(3307)와 spring 설정 파일의 포트 정보 불일치
- 접속 대상 데이터베이스가 생성되지 않은 상태
- 실제 설정
    
    ```
    #docker-compose.yml
    services:
      vacation-db:
        image: mysql:8.0
        restart: always
        environment:
          MYSQL_ROOT_PASSWORD: "1234"
          MYSQL_DATABASE: "devdb"
          MYSQL_USER: "admin"
          MYSQL_PASSWORD: "1234"
        command:
          - --character-set-server=utf8mb4
          - --collation-server=utf8mb4_unicode_ci
          - --default-authentication-plugin=mysql_native_password
        ports:
          - "3307:3306"
        volumes:
          - ./database/datadir:/var/lib/mysql
    ```
    

**해결 방법**

- Docker Compose에서 포트 매핑을 `3307:3306`으로 명확히 설정
- `MYSQL_DATABASE=devdb` 옵션을 추가하여 컨테이너 실행 시 DB 자동 생성
- Spring Boot의 DB 접속 설정과 동일하게 맞춰 동기화

**배운점**

- 컨테이너 내부 포트와 외부 포트의 개념 차이 이해
- 애플리케이션과 인프라 설정 간의 정합성이 중요함을 체감

### 🎨 인라인 수정 시 레이아웃 깨짐 현상

**문제 상황**

- 수정 버튼 클릭 시 입력창이 생성되면서 버튼이 밀리고 리스트 정렬이 깨짐
- 여러 항목 중 특정 항목만 수정해야 하는데 전체 UI가 영향을 받음

**원인 분석**

- DOM 요소를 단순 토글하면서 Bootstrap 레이아웃 구조를 고려하지 않음
- 각 Todo 항목을 구분하지 않고 공통 로직으로 처리하여 특정 요소만 제어하지 못함

**해결 방법**

**1. 각 요소에 고유 ID 부여하여 특정 항목만 제어**

```
consttextSpan=document.getElementById('text-'+id);
consteditForm=document.getElementById('edit-form-'+id);
consteditBtn=document.getElementById('edit-btn-'+id);
```

**2. classList.toggle로 상태 전환 + 레이아웃 유지**

```
editForm.classList.toggle('d-none');
editForm.classList.toggle('d-flex');
```

**3. 텍스트 영역과 버튼도 함께 제어**

```
if (textSpan.style.display==='none') {
textSpan.style.display='inline';
editBtn.style.display='inline-block';
}else {
textSpan.style.display='none';
editBtn.style.display='none';
}
```

**4. Thymeleaf와 연계한 서버 통신 구조**

```
<formth:id="'edit-form-' + ${todo.id}"
th:action="@{/updateTodo/{id}(id=${todo.id})}"
method="post">
```

**배운 점**

- 단순 기능 구현뿐 아니라 UI 구조와 CSS 레이아웃에 대한 이해가 중요함
- 사용자 경험(UX)은 작은 정렬 하나로도 크게 달라질 수 있음을 체감

---

## 🛠️ 개발 과정

### 🧱 환경 구축

[docker, mysql 설정](docker,%20mysql%20%EC%84%A4%EC%A0%95%2032066b347f6080c19142d41b7764e094.md)

- Docker와 MySQL을 활용해 로컬 개발 환경을 구성하고, SpringBoot와 데이터베이스를 연동했습니다.

### 🎨 화면 설계

```html
<html lang="ko"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<h1>Todo App</h1>

<form method="post" action="/addTodo">
    <input type="text" name="todo"/>
    <input type="submit" value="Add Todo"/>
</form>

<hr>

<ul>
    <li th:each="todo : ${todos}">
        <span th:text="${todo.todo}"></span>
        
        <button type="button">수정</button>

        <form th:action="@{/deleteTodo/{id}(id=${todo.id})}" method="post" style="display:inline;">
            <input type="submit" value="삭제"/>
        </form>
    </li>
</ul>

</html>
```

- 초기에는 간단한 HTML 템플릿을 작성해 할 일 추가 및 조회 기능의 기본 구조를 구현했습니다.

```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>My Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .todo-container { max-width: 500px; margin-top: 50px; background: white; padding: 30px; border-radius: 15px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        .todo-item { display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #eee; padding: 10px 0; }
        .todo-item:last-child { border-bottom: none; }
    </style>
</head>
<body>

<div class="container todo-container">
    <h2 class="text-center mb-4 text-primary">📝 Todo List</h2>

    <form method="post" action="/addTodo" class="input-group mb-4">
        <input type="text" name="todo" class="form-control" placeholder="할 일을 입력하세요..." required>
        <button class="btn btn-primary" type="submit">추가</button>
    </form>

    <hr>

    <ul class="list-unstyled">
        <li th:each="todo : ${todos}" class="todo-item">
            <div class="flex-grow-1 d-flex align-items-center">
                <form th:action="@{/toggleTodo/{id}(id=${todo.id})}" method="post" style="display:inline;">
                    <input type="checkbox" class="form-check-input me-2" th:checked="${todo.completed}" onchange="this.form.submit()">
                </form>

                <span th:id="'text-' + ${todo.id}"
                      th:text="${todo.todo}"
                      th:classappend="${todo.completed} ? 'text-decoration-line-through text-muted' : ''">
                </span>

                <form th:id="'edit-form-' + ${todo.id}" th:action="@{/updateTodo/{id}(id=${todo.id})}" method="post" class="d-none align-items-center gap-2 flex-grow-1 me-3">
                    <input type="text" name="todo" th:value="${todo.todo}" class="form-control form-control-sm flex-grow-1">
                    <div class="d-flex gap-1">
                        <button type="submit" class="btn btn-sm btn-outline-success text-nowrap">확인</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary text-nowrap" th:onclick="'toggleEdit(' + ${todo.id} + ')'">취소</button>
                    </div>
                </form>
            </div>

            <div class="d-flex gap-1">
                <button th:id="'edit-btn-' + ${todo.id}" class="btn btn-sm btn-outline-warning" th:onclick="'toggleEdit(' + ${todo.id} + ')'">수정</button>

                <form th:action="@{/deleteTodo/{id}(id=${todo.id})}" method="post" style="display:inline;">
                    <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('정말 삭제할까요?')">삭제</button>
                </form>
            </div>
        </li>
        <li th:if="${#lists.isEmpty(todos)}" class="text-center text-muted mt-3">
            아직 등록된 할 일이 없어요!
        </li>
    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function toggleEdit(id) {
        const textSpan = document.getElementById('text-' + id);
        const editForm = document.getElementById('edit-form-' + id);
        const editBtn = document.getElementById('edit-btn-' + id);

        editForm.classList.toggle('d-none');
        editForm.classList.toggle('d-flex');

        if (textSpan.style.display === 'none') {
            textSpan.style.display = 'inline';
            editBtn.style.display = 'inline-block';
        } else {
            textSpan.style.display = 'none';
            editBtn.style.display = 'none';
        }
    }
</script>
</body>
</html>
```

- 이후 AI를 활용해 UI를 개선하고 Bootstrap 기반의 인터페이스로 발전 →
- CRUD 기능 확장 후 데이터 생성, 조회, 수정, 삭제가 가능한 구조로 발전했습니다.

### ⚙️ 기능 구현 (CRUD)

- Spring Data JPA의 `JpaRepository`를 활용하여 데이터 생성(Create), 조회(Read), 수정(Update), 삭제(Delete) 기능을 구현했습니다.
- 체크박스를 활용한 상태 토글 기능을 추가하여 사용자와 서버 간 상태를 동기화했습니다.

- 코드 흐름 예시 (수정 기능)
    1. 프론트엔드 (Thymeleaf + JavaScript)
        
        ```html
        <form th:id="'edit-form-' + ${todo.id}"
              th:action="@{/updateTodo/{id}(id=${todo.id})}"
              method="post"
              class="d-none">
            <input type="text" name="todo" th:value="${todo.todo}">
            <button type="submit">확인</button>
        </form>
        ```
        
        ```jsx
        function toggleEdit(id) {
        		 :
            const editForm = document.getElementById('edit-form-' + id);
            editForm.classList.toggle('d-none');
            editForm.classList.toggle('d-flex');
        	   :
        }
        ```
        
        - 수정 버튼 클릭 → 입력창 활성화 → 서버로 POST 요청 전송
    2. Controller
        
        ```jsx
        @PostMapping("/updateTodo/{id}")
            public String updateTodo(@PathVariable("id") Long id, @RequestParam("todo") String newTodo) {
                todoService.updateTodo(id, newTodo);
                return "redirect:/";
            }
        ```
        
        - 요청을 받아 서비스 계층으로 전달
    3. Service
        
        ```jsx
        public void updateTodo(Long id, String newText) {
                Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 항목이 없습니다."));
                todo.setTodo(newText);
                todoRepository.save(todo);
            }
        ```
        
        - 데이터 조회 → 값 변경 → 저장
    4. Repository
        
        ```jsx
        @Repository
        public interface TodoRepository extends JpaRepository<Todo, Long> {
        }
        ```
        
        - JPA 활용

---
