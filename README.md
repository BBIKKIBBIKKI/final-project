# ⚾️ 야구 나두? : 야구 입문소 커뮤니티 서비스
<img width="498" alt="스크린샷 2024-11-14 19 54 08" src="https://github.com/user-attachments/assets/e39c46d5-c91a-458e-bcf0-cca6fdb6ad45">
<img width="393" alt="스크린샷 2024-11-14 20 07 35" src="https://github.com/user-attachments/assets/a204f8fc-6b98-43bd-a37d-44360d124f38">
<br/><br/>

## ⚾️ 목차
1. 프로젝트 소개
2. 프로젝트 핵심 목표
3. KEY SUMMARY
4. 인프라 아키텍쳐 & 적용 기술
5. 주요기능
6. 기술적 고도화
7. 역할 분담 및 협업 방식
8. 성과 및 회고
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
---
### 🍁 **성능 개선 : 동시성제어 - 분산락**
---
#### 📌 **한 줄 요약**  
   - Redis 기반 분산락(Redisson)을 사용하여 음식 주문/예약 시 재고 맞게 주문 완료. 
   - 재고 부족 상태에서 새로운 주문이 발생하지 않아 동시성 제어 성공

#### 📌 **도입 배경**  
   - 사전 주문 시스템에서는 높은 동시성(ex)재고가 없는데 주문이 들어간 경우)을 요구하므로, 분산락을 통해 처리 속도와 일관성을 동시에 확보.

#### 📌 **기술적 선택지**  

![스크린샷 2024-11-14 오후 2 39 33](https://github.com/user-attachments/assets/757d9148-5fd8-498c-9195-39618bba14df)

#### 📌 성능 테스트
<details>
  <summary>🧪 락 미적용 테스트 결과</summary>

#### 테스트 시나리오
- **테스트 시나리오**: 100명의 사용자가 동시에 재고 2개씩 주문.

#### 재고 감소 처리 결과
- Redis에서 동시성 문제가 발생하여 재고가 음수로 감소.

**결론**:
- 락 미적용 상태에서는 동시성 문제로 인해 데이터 충돌 발생.
- 음수 재고가 기록되며, 데이터 무결성이 크게 훼손됨.

</details>

<details>
  <summary>🧪 락 적용 테스트 결과</summary>

#### 테스트 시나리오
- **테스트 시나리오**: 100명의 사용자가 동시에 재고 2개씩 주문.

#### 재고 감소 처리 결과
- Redis에서 동시성 제어가 성공적으로 작동하여 정확한 재고 감소 확인.
- 재고 부족 시 정확히 예외(`ApiException`) 발생.

**결론**:
- 락이 적용된 경우, 정확히 100개의 주문이 처리되었으며, 재고 부족 상태에서 새로운 주문이 발생하지 않음.
- 모든 스레드에서 데이터 무결성이 유지됨.

</details>

#### 📌 종합결과
![스크린샷 2024-11-18 오후 1 20 09](https://github.com/user-attachments/assets/956d4ed9-fc17-40b5-88b5-3f8999229f03)


#### 📌 결론

- **락 적용 결과**: Redis 분산 락을 적용한 경우, 데이터의 무결성과 안정성이 크게 향상되었습니다. 동시성 문제를 완벽히 해결하여 모든 요청이 정확히 처리되었습니다.
- **락 미적용 결과**: 락을 적용하지 않은 경우, 데이터 충돌로 인해 재고가 음수로 기록되는 문제가 발생하였습니다.

---
### 🍁 **트러블 슈팅 : Gradle Multimodule 구조와 Elastic Beanstalk 도입**
---

#### 📌 **배경**
   - **의존성 분리를 위한 구조 채택**
     - 의존성 분리를 위해 MSA를 도입하려 했으나, 튜터의 추천으로 Multi-Module 구조가 MSA의 의존성 분리와 비슷한 효과를 제공할 수 있음이 확인됨.
     - Gradle Multimodule 구조로 전환하여, api module, batch module, 채팅 module, integration module을 각 모듈로 구성 
   - **프로젝트의 효율적인 인프라 관리**
     - Auto Scaling Group, Application Load Balancer 등의 설정을 간단히 관리할 수 있는 AWS Elastic Beanstalk 도입을 검토


#### 📌 구조 검토 및 문제점 
<details>
<summary>구조 2: ALB 및 Auto Scaling Group 사용</summary>

#### 구성
- EC2 인스턴스를 Auto Scaling Group으로 묶고, ALB를 통해 트래픽을 분산.
<img width="424" alt="스크린샷 2024-11-20 14 45 56" src="https://github.com/user-attachments/assets/3e9bcd2e-4898-409e-976d-2a77f58e7835">

#### 문제점
- 서버 확장에는 유리하지만 인스턴스 간 상태 유지 및 유지보수 문제 발생.
- 복잡한 유지보수로 인해 제외.

</details>


<details>
<summary>구조 3: 개별 EC2 인스턴스에 MySQL 설치</summary>

#### 구성
- 각 EC2 인스턴스에 MySQL을 설치.
<img width="541" alt="스크린샷 2024-11-20 14 46 55" src="https://github.com/user-attachments/assets/2f5acb89-4924-4dd1-85ca-61acdd704c9b">

#### 문제점
- 데이터 동기화 문제로 데이터 일관성 부족.
- 관리 및 확장성 어려움.

#### 대안
1. **AWS RDS 활용 (채택)**:
    - **장점**: 중앙 집중 데이터 관리 및 유지보수 용이.
2. **하나의 EC2에서 MySQL 공유**:
    - **장점**: 비용 절감 가능.
    - **단점**: 확장성 제한.
    
</details>


<details>
<summary>구조 4: Auto Scaling 및 Redis 동기화 문제</summary>

#### 구성
- EC2 인스턴스 간 Auto Scaling, Redis 캐시 사용.
<img width="590" alt="스크린샷 2024-11-20 14 47 45" src="https://github.com/user-attachments/assets/aa0226af-081f-4744-a07f-cb841a9dc779">

#### 문제점
- Redis 동기화 미흡 → 캐시 일관성 문제 발생 가능.

#### 대안
- **ElastiCache 사용**:
    - Redis를 중앙 관리로 전환하여 캐시 일관성 보장 및 성능 최적화.

</details>

<details>
<summary>구조 5: MSA (Microservices Architecture) 도입 필요성</summary>

#### 구성
- 각 EC2 인스턴스에서 독립적인 서비스(`chat`, `api`, `batch`)를 운영.
<img width="707" alt="스크린샷 2024-11-20 14 52 43" src="https://github.com/user-attachments/assets/c459f054-de45-402e-be8e-cd0462fbb371">

#### 문제점
- 서비스 간 결합도가 높아 장애 발생 시 다른 서비스에 영향을 미침.

#### 대안
- **MSA 도입**:
    - 서비스 독립성 강화.
    - 장애가 전체 시스템에 미치는 영향을 최소화.

</details>

<details>
<summary>구조 6: 서비스별 Auto Scaling Group 구성</summary>

#### 구성
- 각 서비스(`일반 CRUD`, `채팅`, `주문`)에 독립적인 Auto Scaling Group 배정.
<img width="571" alt="스크린샷 2024-11-20 14 53 07" src="https://github.com/user-attachments/assets/297bdc72-88cb-4090-a491-554a2ee2a2bb">

#### 문제점
- 단일 서버 사용 시 트래픽 집중 문제.

#### 대안
- 각 서비스에 Auto Scaling Group 적용.
- 동적 트래픽 조정으로 서비스 유연성 확보.

</details>


<details>
<summary>구조 7: MSA 대신 Spring Boot Multi-Module 전환</summary>

#### 구성
- Spring Boot Multi-Module 구조로 전환:
    - `api module`, `batch module`, `채팅 module`, `integration module`.
<img width="701" alt="스크린샷 2024-11-20 14 53 36" src="https://github.com/user-attachments/assets/aa4312b2-77ab-4ddf-9145-588ed3e7faeb">

#### 문제점
- MSA 도입의 복잡성을 해결할 시간 부족.

#### 대안
- **Multi-Module 전환**:
    - MSA의 의존성 분리 효과를 간소화된 구조로 구현.

</details>



<details>
<summary>구조 8: Auto Scaling Group, ALB 관리</summary>

![스크린샷 2024-11-14 오후 8 57 27 (3)](https://github.com/user-attachments/assets/551a505f-dcba-4a4a-aed3-1dda83be6972)

#### 문제점
- Auto Scaling Group과 ALB의 개별 관리로 인한 복잡성 증가.
- 프로젝트 진행 속도 저하.

#### 대안
- **Elastic Beanstalk 도입**:
    - Auto Scaling Group 및 ALB 설정 간소화.
    - 인프라 관리 부담 감소, 개발 속도 향상.

</details>

#### 📌 결론 및 핵심 기능 개선 방향

 Elastic Beanstalk 도입을 통해 다음과 같은 개선 사항을 기대할 수 있습니다.

1. **자동화된 인프라 관리**: Elastic Beanstalk가 EC2, Auto Scaling Group, Application Load Balancer를 자동으로 설정하고 관리하여 인프라 설정 및 유지보수를 단순화.
2. **개발 효율성 향상**: 개발팀이 인프라 관리에 소요되는 시간을 줄이고, 애플리케이션 개발에 집중할 수 있는 환경 제공.

<br/><br/>

## ⚾️ 인프라 아키텍쳐 & 적용 기술
<br/><br/>


## ⚾️ 주요기능
<br/><br/>

## ⚾️ 기술적 고도화
<br/><br/>

## ⚾️ 역할 분담 및 협업 방식

| 역할 | 이름 | 역할 설명 |
| :------------: | :------------: | :------------ |
| **👑 팀장** | [@정은교](https://github.com/ekj1003) | - 프로젝트 전체 관리 및 방향 설정<br>- 주요 의사결정과 팀 내 소통 담당 <br>- 구단/구장/선수(CRUD) <br>- AWS S3 <br>- Indexing <br>- MultiModule <br>- AWS RDS <br>- OAuth 2.0 카카오 소셜로그인|
| **👑 부팀장** | [@이재희](https://github.com/leejaehee0807) | - 팀장 보조 및 작업 분배<br>- 주요 문서 작성 및 발표 자료 준비 <br>- 서버와 데이터베이스 설계 및 관리 <br>- 회원/유저/선수/구단 커뮤니티/일정(CRUD) <br>- 팀원 간 협업 조율과 피드백 제공 <br>- 동시성제어 <br>- OAuth 2.0 카카오 소셜로그인|
| **👑 팀원** | [@오현택](https://github.com/duduio2050) | - 주요 개발 작업 수행<br>- 기술적 난관 발생 시 해결책 제안<br>- 구단 게시판/댓글(CRUD) <br>- 실시간 채팅 <br>- CI/CD <br>- ElasticBeanstalk <br>- ElasticCache |
| **👑 팀원** | [@박현국](https://github.com/HyunKook-Park) | - 주요 개발 작업 수행<br>- 코어 기능 구현 및 문제 해결 담당 <br>- 가게/메뉴/주문(CRUD) <br>- AWS SQS <br>- MultiModule <br>- Redis Cache <br>- Cloudfront |

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
