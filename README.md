## 사용기술
- Spring Boot 2.3.3
- JPA, QueryDsl 
- local, dev (h2 Database) 
- prod (embedded Mysql)
- Swagger

## 실행방법
1. 터미널 실행
  - cd interpark-server && java -jar build/libs/interpark-0.0.1-SNAPSHOT.jar spring.profiles.active=local
2. 인텔리제이 프로젝트 import
  - <img src= "https://user-images.githubusercontent.com/78005080/200126775-13991dcd-99a9-4361-bc8e-081a94f89135.png">
  - 빨간색 네모안에 있는 화살표 클릭
    
## 테스트 방법
- swagger (http://localhost:8080/swagger-ui/index.html#/)
    - 해당 주소로 접속후 API 테스트
- h2 console (http://localhost:8080/h2-console)
    - API 테스트 후 데이터 확인

## ATDD 
<img src= "https://user-images.githubusercontent.com/78005080/200124832-63c4e288-434b-4679-8960-7ea294e85cc0.png">
