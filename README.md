# ⚾️ 야구 나두? 프로젝트
<img width="498" alt="스크린샷 2024-11-14 19 54 08" src="https://github.com/user-attachments/assets/e39c46d5-c91a-458e-bcf0-cca6fdb6ad45">
<img width="393" alt="스크린샷 2024-11-14 20 07 35" src="https://github.com/user-attachments/assets/a204f8fc-6b98-43bd-a37d-44360d124f38">

## 📖 목차
1. 프로젝트 소개
2. 프로젝트 핵심 목표
3. KEY SUMMARY
4. 인프라 아키텍쳐 & 적용 기술
5. 주요기능
6. 기술적 고도화
7. 역할 분담 및 협업 방식
8. 성과 및 회고

## 프로젝트 소개
**“야구 너도 할 수 있어”** 야구의 야자도 모르는 “**야린이”**들을 **야구 입문소 커뮤니티**
야구 규칙부터 선수 덕질까지, 광범위한 정보를 쉽고 재미있게 배울 수 있습니다. 팬덤 활동을 즐기고, 좋아하는 선수를 알아가며, 야구의 매력을 함께 알아볼까요?

## 프로젝트 핵심 목표
### 1. Gradle MultiModle 을 통한 의존성 분리 + Elastic Beanstalk 를 통한 무중단 배포 
- **“야구 나두?”** 는 최종적으로 코드의 재사용성과 의존성 분리 측면에서의 높은 효율성을 가진 **Gradle MultiModule** 과 자동화된 인프라 관리가 가능한 **Elastic Beanstalk** 도입을 통해 EC2, ASG, ALB 를 자동으로 설정하고 관리하는 무중단 배포를 하여, 개발 효율성을 향상 시켰습니다.

### 2. Github Actions 를 통한 배포 효율성 향상
- **“Github Actions”** 를 도입하여, 코드 품질과 배포 효율성을 높여 코드 커밋 시 자동 빌드, 테스트, 배포 파이프라인을 구축하여 일관된 품질을 유지하는 시스템을 구축하였습니다.

### 3. Redis Cache 와 Pub/Sub 을 AWS ElastiCache 와 접목
- **“Redis Cache”** 를 통해 선수 팔로우 랭킹 시스템에 대해 실시간으로 팔로우 수에 따른 순위 조회 속도를 높이고 DB 부하를 줄이기 위하여, 4가지의 캐시 무효화 전략을 사용하였습니다.
- **“Redis Pub / Sub”** 을 통해 구단 경기에 대한 채팅방에서 트래픽이 몰릴 경우 Auto Scaling 환경에서 서버 간 채팅 메세지 동기화 문제를 해결하기 위해 각 인스턴스가 Redis 를 통해 메세지를 주고받으며 동기화 문제를 해소할 수 있습니다.
- 최종적으로 완전 관리형 서비스인 **“AWS ElastiCache”** 에 연결하여 고가용성, 확장성, 운영측면에서의 효율성을 높히는 작업을 수행하였습니다.

### 4. SQS 를 통한 메세지큐
- **“AWS SQS”** 를 통해 야구장 내 가게 주문의 상태를 가게 사장님이 실시간으로 업데이트 하여, **메세지큐**를 통해 처리할 수 있도록 하였고, SaaS 서비스인 SQS 는 간편하게 사용이 가능하며, 간단한 이벤트 처리에 적합하여, 해당 프로젝트에 도입하였습니다.

### 5. 대규모 동시성 제어인 분산락
- **“분산락”** 을 도입하여, 대규모 동시성 환경에서 음식 주문을 처리하기 위해 분산락 방식을 도입하였습니다. 
- 이러한 방식은 사전 주문 시스템의 효율적 운영을 위해 높은 동시성을 지원하고, 데이터 일관성을 보장하는 분산락 방식을 선택하였습니다.
- 이를 통해 주문 처리가 보다 신속하고 안정적으로 이루어 질 수 있도록 설계하였습니다.

### 6. 쿼리 속도 향상 인덱싱
- **“인덱싱”** 을 적용하여, 선수 검색 속도를 최적화하여, **조회 성능**을 강화하였습니다.

### 7. CDN(Content Delivery Network) 을 통한 S3 전송 속도 향상
- **“AWS CloudFront”** 를 사용하여 전 세계에 분산된 **Edge Location** 을 통해 사용자와 가까운 위치에서 콘텐츠를 제공하여, **S3** 와 같은 정적 콘텐츠를 캐시를 통해 빠르게 전송할 수 있도록 하였습니다. 
- 이는 기존 **S3** 에서 직접 접근할 때보다 콘텐츠 전송 속도가 크게 개선된다는 장점뿐만 아니라, 전송 요금 **비용절감**, HTTPS 를 통한 **보안 강화**, **트래픽에 대한 안정적인 대응**, TTL 을 통한 **캐시 제어 기능** 등에 대한 장점이 있습니다.

### 8. 카카오 소셜 로그인 기능 
- **“소셜 로그인 연동”** 을 통해 사용자들이 간편하게 로그인할 수 있도록 구현하였습니다.
- OAuth2 인증 방식을 사용하여 **카카오 계정으로 안전하고 빠르게 로그인** 을 할 수 있습니다.
- 따라서 **카카오 인증 서버에서의 로그인 흐름** 을 통해 보안성을 높이는 동시에 사용자 편의성을 증대시켰습니다.

### 9. JMeter 를 통한 성능, 부하 테스트
- **“Apache JMeter”** 를 사용하여 동시성제어, 인덱싱, 캐시에 대한 성능 테스트와, 스트레스 테스트를 검증한 결과를 토대로 애플리케이션의 응답 시간을 최적화하고, 병목 현상을 분석하여 성능을 개선하였습니다.

## KEY SUMMARY


## 인프라 아키텍쳐 & 적용 기술


## 주요기능


## 기술적 고도화


## 역할 분담 및 협업 방식
| 팀장 | 부팀장 | 팀원 | 팀원 |
| :------------: | :------------: |:------------:|:------------:|
|[@정은교](https://github.com/ekj1003)|[@이재희](https://github.com/leejaehee0807)|[@오현택](https://github.com/duduio2050)|[@박현국](https://github.com/HyunKook-Park)|


## 성과 및 회고


## 👨‍🏫 프로젝트 소개




## 💜 주요기능

- 덕질 커뮤니티
    : 최애 선수 팔로잉, 구단 응원 게시물, 댓글, 좋아요 기능

- 실시간 채팅
    : Web Socket을 통한 매 경기마다 실시간 채팅으로 야구인들과 소통!

- 구장별 가게 음식 예약
    : 음식을 주문/예약할 때 분산락을 이용해 메뉴의 재고와 순서를 파악해 혼잡스럽지 않도록 교통정리 하는 기능
  
- 음식 주문 알림
    : AWS SQS를 통해 비동기 메세지큐로 특정 이벤트에 대한 실시간 음식 주문 알림 기능

- CI/CD
    : Elastic Beanstalk를 사용한 무중단 배포 구현
  
- 인덱싱
    : 선수 테이블 인덱싱


## 📚️ 기술스택

### ✔️ Tool
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
<img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white">
<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=amazon rds&logoColor=white">

### ✔️ Language
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

### ✔️ Version Control
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

### ✔️ IDE
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

### ✔️ Framework
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">

### ✔️  DBMS
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

### ✔️ Library
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

