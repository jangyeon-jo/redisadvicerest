# 1. 검색 API: 
* 장소 검색  				 GET   	/v1/search/place/{vendor}
* 키 검색어 목록(TOP10)      GET    /v1/search/place/bestkeyword

# 2. 테스트 방법
1. curl 을 통한 테스트방법.
2. 테스트웹을 통한 테스트 방법.
   -> http://localhost:8080/index.html
3. swagger를 통해 명세서 확인, 기능 테스트
   -> http://localhost:8080/swagger-ui.html

# 3. 실제 사용 라이브러리
* Spring Boot 2.3.3.RELEASE
* Spring JPA
* Spring Redis
* Swagger - io.springfox.springfox-swagger2
* JSON - com.google.code.gson
* Use API client - org.apache.httpcomponents:httpclient
* Lombok - org.projectlombok
* embeded H2 Database

# 4. 접속 방법(Redis, H2)
* Redis, H2 접속은 .yml 파일을 통해 진행을 합니다.(Jedis 보다 lettuce 사용)
* Redis는 실제 설치를 진행후, Redis를 기동후 진행해야함.(redis-server(redis 기동), redis-cli(터미널 접근))
* H2는 설치 진행후, 실제 DB데이터를 확인할 경우 콘솔 : http://localhost:8080/h2-console 에 접근하여 확인합니다.
  ID : sa, PW : 



