#1) 공통
##구현 상의 우대사항
### 대용량 트래픽, 서비스 확장성, 동시성 및 오픈 API 서비스 장애 발생을 고려한 설계 및 구현
### 트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 설계 및 구현
### 동시성 이슈가 발생할 수 있는 부분을 염두에 둔 설계 및 구현 (예시. 키워드 별로 검색된 횟수의 정확도)
-> 바로 DB에 저장하는 것이 아닌, Redis에 값을 저장하며 특정 시간을 텀으로 Scheduling DB에 저장하게끔 진행하였습니다.
   Process: 검색 -> 캐시에 저장 -> 스케쥴러에서 지정된 주기에 동작하여 DB로 저장 
-> 제공하는 API만 적용되어 있으며 인터페이스 구현을 통해 확장이 가능합니다.


#2) 장소 검색
## 보너스 +)  README 파일에 검색 서비스에 대한 부하가 매우 커졌을 경우 확장할 수 있는 방법에 대해 적어주세요.
-> (Redis)캐시 를이용 하여 클론 구성하여 장애에 유연하게 대처할수 있으며 비용적으로도 합리적이라고 생각합니다. 
-> Load balancer 적용, Scale-out, Scale-Up 두 가지 측면으로 인프라적으로 적용하면 됩니다.(웹서버, WAS 등)


##요청/응답 예시 (키워드: 은행)  아래는 예시입니다. 요청 URL, JSON Object는 요건에 맞게 자유롭게 작성해주세요.


#3) 인기 키워드 목록
## 요청/응답 URL, JSON Object는 요건에 맞게 자유롭게 작성해주세요.


# 테스트 방법 및 과제관련 내용 정의

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



