<h1 align="center">
    <img height="250" src="https://user-images.githubusercontent.com/78339038/149612537-518ab081-825a-48ee-b623-2321a530135b.png" />
</h1>

<p align="center">
 <img src="https://img.shields.io/badge/-spring-green" />
 <img src="https://img.shields.io/badge/-Spring%20Data%20JPA-brightgreen" />
 <img src="https://img.shields.io/badge/-Mockito-yellowgreen" />
 <img src="https://img.shields.io/badge/-JUnit-red" />
 <img src="https://img.shields.io/badge/-MySQL-blue" />
 <img src="https://img.shields.io/badge/-LOGBack-orange" />
 <img src="https://img.shields.io/badge/-Gradle-blueviolet" />
</p>

# DL-Server

[Don-Late](https://github.com/NerdNULL/Don-Late) application server

</br>

## Project Architecture
![Architecture](https://user-images.githubusercontent.com/57852139/153740995-da2c18c5-bffb-47e8-b5e1-74512d1802d0.png)

<br>

## API GUIDE

### 회원 기능
[회원 로그인](api-guide/UserLogin.md) <br>
[회원 삭제](api-guide/UserDelete.md) <br>
[회원 결제내역](api-guide/UserPaymentList.md) <br>
[회원 상세정보조회](api-guide/UserDetails.md) <br>
[회원 환금신청](api-guide/PointExchange.md) <br>


### 약속 기능
[약속 상세정보조회](api-guide/PlanDetails.md) <br>
[약속 생성](api-guide/PlanCreate.md) <br>
[약속 수정](api-guide/PlanUpdate.md) <br>
[약속 참가 신청](api-guide/PlanJoin.md) <br>
[약속 정산](api-guide/PlanAllocate.md) <br>
[약속 삭제](api-guide/PlanDelete.md) <br>
[약속 현황 입력](api-guide/PlanCalculate.md) <br>

</br>

## Database Information
[Check Database Information](https://hypnotic-mambo-7a0.notion.site/Database-f683f358d0b842c092d5fd3d3db73523)

<br>

## Scheduler
- PlanScheduler : DB 최적화를 위해 최근 1년 이전에 매일 00시에 데이터 삭제
- PaymentScheduler : DB 최적화를 위해 최근 1년 이전에 매일 00시에 데이터 삭제
- ExchangeScheduler : 24시간동안 누적된 환금 신청을 매일 17시에 파일변환 후 S3 업로드

<br>

## Logger
- 유지보수를 위해 Logging System 도입
- 하루 단위의 로그를 파일 저장

※ logback 1.2.5 에서 보안취약점의 이유로 1.2.9 으로 변경

<br>

## Wiki
[History](https://github.com/NerdNULL/Don-Late/wiki/History) <br>
[Rules](https://github.com/NerdNULL/Don-Late/wiki/Rules)


</br>

## Contributor
[kyw04004](https://github.com/kyw04004) </br>
[kimcodingvv](https://github.com/kimcodingvv) </br>
[River-Mt](https://github.com/River-Mt)

<br>

## License
DL-Server is released under the MIT License. See [LICENSE](https://github.com/NerdNULL/Don-Late/blob/main/LICENSE) file for details.

