## Todo Project

<U>*updated 2024.04.28*</U>

---

### BackEnd
- Java 17
- Gradle 3.0.17
- Spring 3.2.5
- MySQL 8.0

---

### Concept
#### User
Todo 서비스를 이용할 회원

##### nickname
회원이 설정한 이름

##### password
회원의 비밀번호

#### Todo
회원의 할일을 기록하는 것

##### TodoStatus
할일의 진행 상태값

| enum        | 설명              | 
|-------------|-----------------|
| READY       | 할일 등록한 상태       | 
| IN_PROGRESS | 할일 진행중인 상태      |
| DONE        | 할일 완료인 상태       | 
| HOLD        | 진행중이었던 할일 대기 상태 | 

---

### Data Model
**User**

| 항목          | 데이터타입        | 설명       | 비고          |
|-------------|--------------|----------|-------------|
| id          | bigint       | 유저 id    | primary_key |
| nickname    | varchar(255) | 닉네임      |             |
| password    | varchar(255) | 비밀번호     |             |
| role        | varchar(255) | 유저 역할    |             |
| create_time | datetime     | 유저 가입 시간 |             |

**Todo**

| 항목          | 데이터타입    | 설명       | 비고          |
|-------------|----------|----------|-------------|
| id          | bigint   | todo id  | primary_key |
| user_id     | bigint   | 유저 id    | foreign_key |
| text        | longtext | 할일 내용    |             |
| status      | varchar(255) | 할일 상태    |             |
| create_time | datetime | 할일 생성 시간 |             |
| update_time | datetime | 할일 변경 시간 |             |
---

### API
http://localhost:8080/swagger-ui/index.html#/todo-controller/home

### 환경변수
https://drive.google.com/drive/folders/1V2KP4sQqmoFpNWkrNJWpYD_JcigXSLll?usp=drive_link

### Docker
docker-compose --env-file ./.env up