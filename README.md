# shop
shop api (nail)

## 기본정보
* 성명 : 박성민
* 개발환경 : windows7, sts 3.8, spring-boot, spring data jpa, spring data rest, hibernate, embedded tomcat/h2db, spring data rest hal
* 실행환경 : embedded tomcat, jre1.8

## 시작하기
`git clone https://github.com/ParkSeongMin/shop.git`으로 다운로드 하거나
 [download] (https://github.com/ParkSeongMin/shop/releases/download/0.1/shop-api-0.1.jar) 한다.
 
`cd shop-api`를 하고 `mvn clean package -Dmaven.test.skip=true`를 실행하여 maven으로 생성한다.
 
`java -jar shop-api-0.1.jar` 실행하여 아래 api를 호출한다.

api - curl을 이용하거나 hal browser(http://localhost:8080)를 이용하여 호출한다.
* 상점조회
	* 상점 목록 조회
		* 기본 : curl http://localhost:8080/shop
		* page : curl http://localhost:8080/shop?page=1
		* page : curl http://localhost:8080/shop?sort=name,desc
  * 상점 상세 정보 조회
		* curl http://localhost:8080/shop/1
* 상점 사진 조회
	* curl http://localhost:8080/shop/1/photoList
* 상점 가치 산정
	* 가치 갱신
		* curl http://localhost:8080/values-calc
  * 가치 변경
		* curl -i -X PUT -H "Content-Type:application/json" -d "{\"description\":\"changed\",\"applyRate\":0.5}" http://localhost:8080/values/1
