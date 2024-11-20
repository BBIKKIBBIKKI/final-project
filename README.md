# ⚾️ 야구 나두? : 야구 입문소 커뮤니티 서비스
<img width="498" alt="스크린샷 2024-11-14 19 54 08" src="https://github.com/user-attachments/assets/e39c46d5-c91a-458e-bcf0-cca6fdb6ad45">
<img width="393" alt="스크린샷 2024-11-14 20 07 35" src="https://github.com/user-attachments/assets/a204f8fc-6b98-43bd-a37d-44360d124f38">
<br/><br/>

## ⚾️ 목차
1. **프로젝트 소개**
2. **프로젝트 핵심 목표**
3. **KEY SUMMARY**
4. **인프라 아키텍쳐 & 적용 기술**
5. **주요기능**
6. **기술적 고도화**
7. **역할 분담 및 협업 방식**
8. **성과 및 회고**
<br/><br/>

## ⚾️ 프로젝트 소개

**“야구 너도 할 수 있어”** 야구의 야자도 모르는 "**야린이**"들을 **야구 입문소 커뮤니티**
야구 규칙부터 선수 덕질까지, 광범위한 정보를 쉽고 재미있게 배울 수 있습니다. 팬덤 활동을 즐기고, 좋아하는 선수를 알아가며, 야구의 매력을 함께 알아볼까요?
<br/><br/>

## ⚾️ 프로젝트 핵심 목표

### 📌 Gradle MultiModle 을 통한 의존성 분리 + Elastic Beanstalk 를 통한 무중단 배포 
- **“야구 나두?”** 는 최종적으로 코드의 재사용성과 의존성 분리 측면에서의 높은 효율성을 가진 **Gradle MultiModule** 과 자동화된 인프라 관리가 가능한 **Elastic Beanstalk** 도입을 통해 EC2, ASG, ALB 를 자동으로 설정하고 관리하는 무중단 배포를 하여, 개발 효율성을 향상 시켰습니다.
---
### 📌 Github Actions 를 통한 배포 효율성 향상
- **“Github Actions”** 를 도입하여, 코드 품질과 배포 효율성을 높여 코드 커밋 시 자동 빌드, 테스트, 배포 파이프라인을 구축하여 일관된 품질을 유지하는 시스템을 구축하였습니다.
---
### 📌 Redis Cache 와 Pub/Sub 을 AWS ElastiCache 와 접목
- **“Redis Cache”** 를 통해 선수 팔로우 랭킹 시스템에 대해 실시간으로 팔로우 수에 따른 순위 조회 속도를 높이고 DB 부하를 줄이기 위하여, 4가지의 캐시 무효화 전략을 사용하였습니다.
- **“Redis Pub / Sub”** 을 통해 구단 경기에 대한 채팅방에서 트래픽이 몰릴 경우 Auto Scaling 환경에서 서버 간 채팅 메세지 동기화 문제를 해결하기 위해 각 인스턴스가 Redis 를 통해 메세지를 주고받으며 동기화 문제를 해소할 수 있습니다.
- 최종적으로 완전 관리형 서비스인 **“AWS ElastiCache”** 에 연결하여 고가용성, 확장성, 운영측면에서의 효율성을 높히는 작업을 수행하였습니다.
---
### 📌 SQS 를 통한 메세지큐
- **“AWS SQS”** 를 통해 야구장 내 가게 주문의 상태를 가게 사장님이 실시간으로 업데이트 하여, **메세지큐**를 통해 처리할 수 있도록 하였고, SaaS 서비스인 SQS 는 간편하게 사용이 가능하며, 간단한 이벤트 처리에 적합하여, 해당 프로젝트에 도입하였습니다.
---
### 📌 대규모 동시성 제어인 분산락
- **“분산락”** 을 도입하여, 대규모 동시성 환경에서 음식 주문을 처리하기 위해 분산락 방식을 도입하였습니다. 
- 이러한 방식은 사전 주문 시스템의 효율적 운영을 위해 높은 동시성을 지원하고, 데이터 일관성을 보장하는 분산락 방식을 선택하였습니다.
- 이를 통해 주문 처리가 보다 신속하고 안정적으로 이루어 질 수 있도록 설계하였습니다.
---
### 📌 쿼리 속도 향상 인덱싱
- **“인덱싱”** 을 적용하여, 선수 검색 속도를 최적화하여, **조회 성능**을 강화하였습니다.
---
### 📌 CDN(Content Delivery Network) 을 통한 S3 전송 속도 향상
- **“AWS CloudFront”** 를 사용하여 전 세계에 분산된 **Edge Location** 을 통해 사용자와 가까운 위치에서 콘텐츠를 제공하여, **S3** 와 같은 정적 콘텐츠를 캐시를 통해 빠르게 전송할 수 있도록 하였습니다. 
- 이는 기존 **S3** 에서 직접 접근할 때보다 콘텐츠 전송 속도가 크게 개선된다는 장점뿐만 아니라, 전송 요금 **비용절감**, HTTPS 를 통한 **보안 강화**, **트래픽에 대한 안정적인 대응**, TTL 을 통한 **캐시 제어 기능** 등에 대한 장점이 있습니다.
---
### 📌 카카오 소셜 로그인 기능 
- **“소셜 로그인 연동”** 을 통해 사용자들이 간편하게 로그인할 수 있도록 구현하였습니다.
- OAuth2 인증 방식을 사용하여 **카카오 계정으로 안전하고 빠르게 로그인** 을 할 수 있습니다.
- 따라서 **카카오 인증 서버에서의 로그인 흐름** 을 통해 보안성을 높이는 동시에 사용자 편의성을 증대시켰습니다.
---
### 📌 JMeter 를 통한 성능, 부하 테스트
- **“Apache JMeter”** 를 사용하여 동시성제어, 인덱싱, 캐시에 대한 성능 테스트와, 스트레스 테스트를 검증한 결과를 토대로 애플리케이션의 응답 시간을 최적화하고, 병목 현상을 분석하여 성능을 개선하였습니다.
<br/><br/>

## ⚾️ KEY SUMMARY
<br/><br/>

## ⚾️ 인프라 아키텍쳐 & 적용 기술
### 📌 인프라 아키텍쳐
![스크린샷 2024-11-20 오후 3 06 53](https://github.com/user-attachments/assets/fefed4c4-96e0-4d9b-b6d5-f7e4cd6ebde4)

### 📌 적용기술
<details>
<summary><b>🔎 적용 기술 상세보기</b></summary>

#### 애플리케이션 개발 및 프레임워크
<img src="https://img.shields.io/badge/JDK 17-green?style=for-the-badge&logo=#000000&logoColor=white"><img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

#### <img src="https://img.shields.io/badge/gradle multimoduel-red?style=for-the-badge&logo=gradle&logoColor=white">
프로젝트의 모듈화를 통해 코드의 재사용성과 관리성을 높이기 위해 ***Gradle 멀티모듈 구성***을 사용했습니다. 각 모듈별로 독립적인 개발과 테스트가 가능하며, CI/CD 과정에서의 효율성을 높입니다.

---

#### <img src="https://img.shields.io/badge/Amazon ElastiCache-orange?style=for-the-badge&logo=amazonelasticache&logoColor=white">
선수의 랭킹을 ***캐싱***하여 조회 속도를 높이기 위해 ***Redis***를 도입했습니다. Redis는 빠른 응답 속도를 제공해 성능 향상에 기여합니다.

---

#### <img src="https://img.shields.io/badge/indexing-yellow?style=for-the-badge&logo=&logoColor=white">
선수 검색 속도를 최적화하기 위해 주요 데이터 필드(player_name, team_name)에 ***인덱싱***을 적용하여 조회 성능을 강화했습니다.

---

#### <img src="https://img.shields.io/badge/AWS-green?style=for-the-badge&logo=#232F3E&logoColor=white"><img src="https://img.shields.io/badge/EC2-blue?style=for-the-badge&logo=amazonec2&logoColor=white"><img src="https://img.shields.io/badge/S3-sodomy?style=for-the-badge&logo=amazons3&logoColor=white"><img src="https://img.shields.io/badge/SQS-purple?style=for-the-badge&logo=amazonsqs&logoColor=white">
- **EC2** : 애플리케이션 서버를 호스팅하고, 유연한 확장성을 지원하는 ***AWS EC2***를 선택했습니다.
- **S3** : 구단, 구장, 선수의 이미지 파일들을 ***S3***를 사용하여 안전하게 관리하며, 애플리케이션에서 파일을 간편하게 업로드 및 다운로드할 수 있습니다.
- **SQS** : 비동기 처리와 메시지 전송을 위해 ***SQS***를 도입하여 안정적인 메시지 큐를 통해 확장성과 내구성을 보장합니다.
  
---

#### <img src="https://img.shields.io/badge/AWS elastic beanstalk -pink?style=for-the-badge&logo=#2088FF&logoColor=white">
애플리케이션의 배포 및 관리를 간소화하고, 자동화된 인프라 환경을 통해 개발에 집중할 수 있도록 ***AWS Elastic Beanstalk***을 도입했습니다. ***Elastic Beanstalk***은 애플리케이션 실행 환경을 자동으로 프로비저닝하고 모니터링하며, 배포 프로세스를 최적화하여 운영 효율성을 높입니다.

---

#### <img src="https://img.shields.io/badge/Github Actions-camel?style=for-the-badge&logo=#2088FF&logoColor=white">
***CI/CD 자동화***를 통해 코드 품질과 배포 효율성을 높이기 위해 ***GitHub Actions***를 사용했습니다. 코드 커밋 시 자동 빌드, 테스트, 배포 파이프라인을 구축하여 일관된 품질을 유지합니다.

---

####  <img src="https://img.shields.io/badge/Apche JMeter-red?style=for-the-badge&logo=apachejmeter&logoColor=white">
애플리케이션의 성능 테스트와 부하 테스트를 위해 ***Apache JMeter***를 사용했습니다. 이를 통해 애플리케이션의 응답 시간을 최적화하고 병목 현상을 분석하여 성능을 개선했습니다.

####  협업 도구
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)<img src="https://img.shields.io/badge/Notion-black?style=for-the-badge&logo=notion&logoColor=white">
</details>
<br/><br/>

## ⚾️ 주요기능
- **덕질 커뮤니티**
    : 최애 선수 팔로잉, 구단 응원 게시물, 댓글, 좋아요 기능

- **실시간 채팅**
    : ***Redis Pub/Sub***을 통한 매 경기마다 실시간 채팅으로 야구인들과 소통!

- **구장별 가게 음식 예약**
    : 음식을 주문/예약할 때 ***분산락***을 이용해 메뉴의 재고와 순서를 파악해 혼잡스럽지 않도록 교통정리 하는 기능
  
- **음식 주문 알림**
    : ***AWS SQS***를 통해 비동기 메세지큐로 특정 이벤트에 대한 실시간 음식 주문 알림 기능

- **CI/CD**
    : ***Elastic Beanstalk***를 사용한 무중단 배포 구현
  
- **인덱싱**
    : 선수 테이블 ***인덱싱***
<br/><br/>

## ⚾️ 기술적 고도화
<br/><br/>

## ⚾️ 역할 분담 및 협업 방식

| 역할 | 이름 | 역할 설명 |
| :------------: | :------------: | :------------ |
| **팀장** | [@정은교](https://github.com/ekj1003) | - 프로젝트 전체 관리 및 방향 설정<br>- 주요 의사결정과 팀 내 소통 담당 <br>- 구단/구장/선수(CRUD) <br>- AWS S3 <br>- Indexing <br>- MultiModule <br>- AWS RDS <br>- 소셜 로그인|
| **부팀장** | [@이재희](https://github.com/leejaehee0807) | - 팀장 보조 및 작업 분배<br>- 주요 문서 작성 및 발표 자료 준비 <br>- 서버와 데이터베이스 설계 및 관리 <br>- 회원/유저/선수/구단 커뮤니티/일정(CRUD) <br>- 소셜 로그인 <br>- 동시성제어|
| **팀원** | [@오현택](https://github.com/duduio2050) | - 주요 개발 작업 수행<br>- 기술적 난관 발생 시 해결책 제안<br>- 구단 게시판/댓글(CRUD) <br>- 실시간 채팅 <br>- CI/CD <br>- ElasticBeanstalk <br>- ElasticCache |
| **팀원** | [@박현국](https://github.com/HyunKook-Park) | - 주요 개발 작업 수행<br>- 코어 기능 구현 및 문제 해결 담당 <br>- 가게/메뉴/주문(CRUD) <br>- AWS SQS <br>- MultiModule <br>- Redis Cache <br>- Cloudfront |

<br/><br/>

## ⚾️ 성과 및 회고
### 😊 잘된 점
#### 📌 **성능 최적화 성공**
  - "***Gradle Multimodule***"을 프로젝트 구조로 채택하여 '**독립적으로 모듈을 관리**'하고 코드 간의 의존성 줄여 독립성과 유지보수성 확보.
  - "***Elastic Beanstalk***"을 도입하여 '**EC2, Auto Scaling Group, Application Load Balancer를 자동으로 설정**'하고 관리하여 인프라 설정 및 유지보수를 단순화.
  - "***Redis 기반 분산락***"을 사용하여 '**정확한 재고 감소 및 동시성 문제 방지**'.
  - "***Indexing***"을 활용하여 선수 검색 쿼리 평균 시간 '**96% 성능 향상**'.
  - "***ElastiCache***"를 사용하여 선수 랭킹 조회 시 '**최소 응답 시간 약 0ms 달성**', 평균 응답 시간 90ms 이하 달성.
  - "***QueryDSL***"을 통해 구현한 구장 검색 시 요청당 '**평균 응답 시간 4.83ms를 달성**'하고, '**10만개**'의 동시적인 http request에도 '**안정적임**'.
  - "***Redis Pub/Sub***"을 통해 Auto-Scaling 환경에서 데이터를 '**순차적으로 처리**', 메세지의 '**순서를 보장**'.

#### 📌 **효율적이고 도전적인 협업**
  - 팀원 간의 **역할 분담이 명확**하고, 매일 아침 10시 각자 할일을 논의하는 **스크럼 회의를 통해 효율적으로 개발**.
  - **문제 발생시 빠르게 공유**하고, 팀원 모두가 **적극적으로 해결**.
  - Notion 캘린더의 ***마일스톤을 설정하여***, 단기 목표를 세우고 장기 목표로 이어지도록하여 **효율적인 일정 관리**.
  - 도입해보고 싶은 ***추가적인 기술***(AWS SQS, Redis Pub/Sub, AWS Cloud Front, Elastic Beanstalk, Gradle Multimodule 등)을 **적극적으로 제안**하고, 팀원 모두 **수용**하며 개발 진행.
---

### 😢 아쉬운 점
- 📌 **프로젝트 초기 설계 부족**  
  - Controller 단에서만 존재해야하는 AuthUser와 RequestDto등이 Service단에 직접적으로 주입되어 의존성이 증가하여, 이후 Gradle Multimodule 구조로 분리하면서 해당 의존성을 삭제하는데 어려움을 겪음.
  - API 명세서 작성시 RESTful하게 적지 못한 부분이 존재하여 수정하는데 어려움을 겪음.
  - 프로젝트 스코프가 생각보다 큰 편이다보니 API 명세서에 따라 와이어프레임을 구성하는 데에도 어려움을 겪음.

- 📌 **코드 리펙토링의 어려움**
  - Gradle Multimodule로 구조화를 하면서, 대부분 리펙토링을 진행하였으나 다른 팀원의 코드를 리펙토링하는데 어려움을 겪음.
---

### 🔮 향후 계획
- 📌 **추가 기능 개발**
  
  - 토스 페이먼츠를 사용하여 음식 결제 시스템 기능 개발. 
  - 카카오뿐만 아니라 다른 소셜 로그인(Naver, Google 등) 기능 추가 개발.
