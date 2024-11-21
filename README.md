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
9. **어플리케이션 실행방법**
<br/><br/><br/>

## ⚾️ 프로젝트 소개

**“야구 너도 할 수 있어”** 야구의 야자도 모르는 "**야린이**"들을 **야구 입문소 커뮤니티**
야구 규칙부터 선수 덕질까지, 광범위한 정보를 쉽고 재미있게 배울 수 있습니다. 팬덤 활동을 즐기고, 좋아하는 선수를 알아가며, 야구의 매력을 함께 알아볼까요?
<br/><br/>

### 📚사용한 기술<br/>
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Framework-008000?style=flat-square&logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-008000?style=flat-square&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon ElastiCache-0073BB?style=flat-square&logo=amazonelasticache&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-0db7ed?style=flat-square&logo=docker&logoColor=white"/> <img src="https://img.shields.io/badge/Git-F05033?style=flat-square&logo=git&logoColor=white"/> <img src="https://img.shields.io/badge/Github-181717?style=flat-square&logo=github&logoColor=white"/> <img src="https://img.shields.io/badge/AWS-FF9900?style=flat-square&logo=amazonwebservices&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-D86613?style=flat-square&logo=amazons3&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon SQS-F2B619?style=flat-square&logo=amazonsqs&logoColor=white"/> <img src="https://img.shields.io/badge/AWS%20Elastic%20Beanstalk-6AA84F?style=flat-square&logo=amazonaws&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white"/> <img src="https://img.shields.io/badge/Apache JMeter-E2231A?style=flat-square&logo=apachejmeter&logoColor=white"/> <img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=notion&logoColor=white"/> <img src="https://img.shields.io/badge/Slack-E01E5A?style=flat-square&logo=slack&logoColor=white"/> <img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white"/> <img src="https://img.shields.io/badge/Cloud Front-F58536?style=flat-square&logo=amazonwebservices&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon Route53-FF9900?style=flat-square&logo=amazonroute53&logoColor=white"/> <img src="https://img.shields.io/badge/AWS Elastic LoadBalancing-8C4B9B?style=flat-square&logo=awselasticloadbalancing&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon RDS-0073BB?style=flat-square&logo=amazonrds&logoColor=white"/> <img src="https://img.shields.io/badge/CI/CD-28A745?style=flat-square&logo=circleci&logoColor=white"/>


<br/>

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
- **“인덱싱”** 을 적용하여, 선수 검색 속도를 최적화하여, 인덱싱 전후 **평균 쿼리 실행 시간 96% 향상**, **총 쿼리 실행 시간 95.7% 향상** 등 조회 성능을 강화하였습니다. 더하여, 부하테스트를 진행하여 인덱싱 전후 **평균 응답 시간 97.92%**, 총 **평균 소요 시간 86.51%** 향상되었습니다.
---
### 📌 CDN(Content Delivery Network) 을 통한 S3 전송 속도 향상
- **“AWS CloudFront”** 를 사용하여 전 세계에 분산된 **Edge Location** 을 통해 사용자와 가까운 위치에서 콘텐츠를 제공하여, **S3** 와 같은 정적 콘텐츠를 캐시를 통해 빠르게 전송할 수 있도록 하였습니다. 
- 이는 기존 **S3** 에서 직접 접근할 때보다 콘텐츠 전송 속도가 크게 개선된다는 장점뿐만 아니라, **보안 강화**, **트래픽에 대한 안정적인 대응**, TTL 을 통한 **캐시 제어 기능** 등에 대한 장점이 있습니다.
---
### 📌 카카오 소셜 로그인 기능 
- **“소셜 로그인 연동”** 을 통해 사용자들이 간편하게 로그인할 수 있도록 구현하였습니다.
- OAuth2 인증 방식을 사용하여 **카카오 계정으로 안전하고 빠르게 로그인** 을 할 수 있습니다.
- 따라서 **카카오 인증 서버에서의 로그인 흐름** 을 통해 보안성을 높이는 동시에 사용자 편의성을 증대시켰습니다.
---
### 📌 JMeter 를 통한 성능, 부하 테스트
- **“Apache JMeter”** 를 사용하여 동시성제어, 인덱싱, 캐시에 대한 성능 테스트와, 스트레스 테스트를 검증한 결과를 토대로 애플리케이션의 응답 시간을 최적화하고, 병목 현상을 분석하여 성능을 개선하였습니다.
<br/><br/><br/><br/>

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
  ![3](https://github.com/user-attachments/assets/84a08e76-8f79-4035-a4b4-b9169ee291e2)

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
![image](https://github.com/user-attachments/assets/0f2c99a5-03f7-48c2-b8ed-611617dd30e2)
![2](https://github.com/user-attachments/assets/bd8aa5af-670b-43cd-827b-ee2aa788a8ce)

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

1. **자동화된 인프라 관리**: Elastic Beanstalk가 EC2, Auto Scaling Group, Application Load Balancer를 자동으로 설정하고 관리하여 인프라 설정 및 유지보수를 단순화.
2. **개발 효율성 향상**: 개발팀이 인프라 관리에 소요되는 시간을 줄이고, 애플리케이션 개발에 집중할 수 있는 환경 제공.

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
<br/><br/><br/>

## ⚾️ 기술적 고도화
<details>
<summary><b> Github Actions </b></summary>

1. **문제 상황**

기존의 프로젝트 개발 및 배포 과정에서는 수동적인 빌드와 배포 작업으로 인해 많은 시간과 인적 자원이 소모되었습니다. 이러한 방식은 특히 코드 변경이 빈번한 대규모 프로젝트에서 비효율성을 초래하고, 실수의 가능성을 높였습니다. 지속적인 통합 및 배포(Continuous Integration/Continuous Deployment, CI/CD) 도구를 통해 자동화를 도입할 필요성이 제기되었습니다. 도입할 CI/CD 도구로는 GitHub Actions와 Jenkins가 고려되었습니다.

2. **대안 비교**
![image](https://github.com/user-attachments/assets/e733d6e0-e895-4fde-803e-2fc26236a223)

    
3. **GitHub Actions 도입 이유**

    - **빠른 트리거 및 빌드 속도**: GitHub Actions는 트리거가 빠르고 빌드 속도도 우수하여 코드 변경 시 빠른 피드백을 제공합니다.
    - **코드 보호 및 관리**: GitHub 리포지토리 내에서 코드와 워크플로를 함께 관리하므로, 코드 보안 유지가 용이하며 단일 플랫폼에서 코드 관리가 가능합니다.
    - **연동의 간편성**: GitHub 자체에서 제공하는 CI/CD 도구이기 때문에, GitHub Actions와 리포지토리의 연동이 간단합니다.
    - **비용 효율성**: GitHub Actions는 무료로 제공하는 2,000분의 워크플로 실행 시간을 통해 추가적인 비용 부담이 적습니다.

4. **결론**

위의 이유들로 인해 GitHub Actions를 CI/CD 도구로 최종 선택하게 되었습니다. GitHub Actions는 빠른 트리거 및 빌드 속도, 코드 보호의 용이성, 연동의 간편함, 그리고 비용적인 이점을 통해 프로젝트의 효율성을 극대화할 수 있는 선택지로 판단됩니다. Jenkins의 경우 다양한 플러그인과 유연성이 강점이지만, 초기 설정 및 유지 보수 측면에서 추가적인 관리가 필요하여 이번 프로젝트의 요구 사항에는 적합하지 않다고 판단하였습니다.
<br/><br/>
</details>

<details>
<summary><b> Elastic Beanstalk </b></summary>
  
1. **문제 상황**
    
    야구 애플리케이션의 개발 및 배포 과정에서 증가하는 사용자 수와 복잡해지는 기능에 따라 인프라의 확장성과 자동화 관리가 필수적이었습니다. 초기에는 수동으로 서버를 설정하고 유지 관리했으나, 이를 통해 서버를 수동으로 관리하는 방식은 운영상의 부담을 증가시키고, 서비스 업데이트 시마다 지속적인 유지보수 작업이 필요해졌습니다. 특히, 트래픽 변화에 따라 서버 용량을 확장하거나 축소할 수 있는 자동화된 인프라 관리 방식이 절실하게 요구되었습니다. 이에 따라 인프라 관리의 효율성을 높이기 위해 AWS Elastic Beanstalk을 도입할 필요성이 제기되었습니다.
    
2. **대안 비교**

    ![image](https://github.com/user-attachments/assets/d1c5430f-8038-487a-8249-75a6ea19823a)

3. **Elastic Beanstalk 도입이유**

    - **자동화된 관리와 편리한 배포**: Elastic Beanstalk은 배포, 로드 밸런싱, Auto Scaling, 모니터링 등을 자동으로 관리하여 인프라 관리의 부담을 덜어줍니다. 이는 초기 설정 후 지속적인 관리 작업을 최소화할 수 있어 개발자와 운영자 모두에게 효율적입니다.
    - **Auto Scaling 및 로드 밸런싱 지원**: Elastic Beanstalk은 기본적으로 Auto Scaling과 로드 밸런싱을 제공하여 트래픽 변화에 따라 서버 용량을 자동으로 조절합니다. 이 기능을 통해 예상치 못한 트래픽 증가 상황에서도 안정적으로 애플리케이션을 제공할 수 있습니다.
    - **모니터링 및 성능 관리 용이성**: Elastic Beanstalk은 AWS CloudWatch와 통합되어 실시간 모니터링과 성능 지표를 제공합니다. 이를 통해 서버 상태와 트래픽을 쉽게 모니터링할 수 있어, 장애 발생 시 신속하게 대응할 수 있습니다.
    - **비용 효율성**: Elastic Beanstalk은 서버 사용량에 따라 비용이 산정되므로, 초기 설정 비용을 줄이면서도 유연한 비용 관리를 할 수 있습니다. 소규모 서비스부터 대규모 서비스까지 규모에 맞게 확장이 가능하여 경제적입니다.

4. **결론**

    Elastic Beanstalk은 인프라 관리의 효율성과 확장성을 제공하며, 초기 설정 이후 애플리케이션 배포와 운영을 자동화하여 운영 부담을 크게 줄여줍니다. EC2를 수동으로 관리하는 방식은 운영 인프라에 많은 시간과 비용이 소요되고, Kubernetes는 관리와 설정의 복잡성이 커서 소규모 서비스에는 적합하지 않다는 점에서 Elastic Beanstalk을 도입하는 것이 최적의 선택이었습니다. Elastic Beanstalk을 통해 자동 확장, 모니터링, 성능 관리의 용이성을 확보하고, 서비스의 안정성과 신뢰성을 높일 수 있어 최종적으로 Elastic Beanstalk을 도입하기로 결정하였습니다.
   <br/><br/>
</details>

<details>
<summary><b> Redis Pub/Sub </b></summary>
  
1. **문제 상황**

    - 트래픽이 몰릴 경우  scale out 으로 서버를 관리해주는 기능을 추가함
    - 이런 경우 채팅방에서의 내용이 서버끼리 동기화가 되지 않음.

2. **대안 비교**

    a. **Redis Pub/Sub 활용**: Redis의 Pub/Sub 기능을 활용하여 각 서버 인스턴스가 Redis를 통해 채팅 메시지를 발행하고 구독하게 만듭니다. 모든 서버가 특정 채널을 구독하게 되어, 한 인스턴스에서 채팅 메시지를 발행하면 Redis가 이를 구독자(다른 인스턴스)에게 전송하여 데이터를 실시간으로 동기화합니다.
    
    - 장점 : Redis Pub/Sub은 메시지를 즉시 발행하고 구독자들에게 전달하기 때문에 서버 간 실시간 데이터 동기화가 가능합니다. 이로 인해 여러 서버가 각기 다른 위치에서 동일한 채팅 데이터를 즉각적으로 공유할 수 있습니다.
    
    - 단점 : Redis Pub/Sub은 구독자가 일시적으로 연결이 끊겼거나 새로 연결된 경우, 해당 구독자가 중간에 발행된 메시지를 수신하지 못할 수 있습니다. 이로 인해 메시지가 손실될 가능성이 있으며, 일관된 메시지 수신이 중요한 경우에는 적합하지 않습니다.
    
    b. **RabbitMQ, Kafka** 등과 같은 중앙 메시지 큐 시스템을 사용하면 모든 인스턴스가 동일한 메시지 큐를 구독할 수 있어 데이터 일관성을 유지할 수 있습니다.
    
    - 장점: 메시지 중복 처리, 순서 보장, 재전송 등의 기능을 사용할 수 있어 안정성과 확장성이 뛰어납니다.
    
    - 단점: Redis보다 설정이 복잡하고 관리가 필요하며, 초기 설치 및 설정 시 비용이 발생할 수 있습니다.

3. **Redis Pub/Sub 도입이유**

    - Redis Stream은 데이터를 순차적으로 처리할 수 있어 채팅 메시지의 순서 보장과 로그를 남길 수 있습니다. 채팅 메시지를 Stream에 저장하고, 각 인스턴스가 이를 구독함으로써 동기화를 유지할 수 있습니다.

4. **결론**

    - Auto Scaling 환경에서 서버 간 채팅 메시지 동기화 문제를 해결하기 위해 Redis Pub/Sub을 사용하면 각 인스턴스가 Redis를 통해 메시지를 주고받으며 동기화 문제를 해소할 수 있습니다.
   <br/><br/>
</details>

<details>
<summary><b> AWS SQS </b></summary>

1. **문제 상황**
    
    야구장 내 가게 주문의 상태를 실시간으로 업데이트하고 관리할 수 있는 이벤트 시스템이 필요했습니다. 주문 상태가 변경될 때마다 고객에게 알림을 제공하거나, 특정 작업을 처리할 수 있도록 메세지를 전달하는 방식이 요구되었습니다. 이러한 작업은 대규모 트래픽이 아닌, 주문 상태 변경과 같은 단순 이벤트만을 다루므로 적절한 메시징 시스템을 선택하여 구현할 필요가 있었습니다. 대규모 환경을 위한 메시지 스트리밍이나 데이터 분석 기능보다는 단순하고 비용 효율적인 비동기 메시지 큐가 요구되었습니다.
    
2. **대안 비교**

    ![image](https://github.com/user-attachments/assets/cfc8ea32-472c-4cf7-a0f5-706b913c425a)
    
3. **AWS SQS 도입이유**

    - **단순 이벤트 처리에 최적화**: SQS는 대규모 데이터 처리나 스트리밍 대신, 가벼운 비동기 메시지 전달에 특화되어 있습니다. 주문 상태 변경과 같은 단순 이벤트 전달에 적합합니다.
    - **관리형 서비스로 운영 부담 최소화**: SQS는 AWS에서 제공하는 관리형 서비스로, 추가적인 설치나 유지보수가 필요하지 않습니다. 기본 기능으로 충분히 간단한 메시지 전달을 처리할 수 있어 운영 효율이 높습니다.
    - **비용 효율성**: AWS의 관리형 서비스로써, 필요한 만큼 사용하고 비용을 지불하는 방식이므로 유지비용이 저렴합니다.
    - **프론트엔드 연동의 간편성**: SQS 메시지 큐에 기록된 로그는 SQS Listener를 통해 수집되며, 프론트엔드에서는 이를 호출하여 상태 정보를 가져올 수 있습니다. 따라서 Server-Sent Events(SSE)를 사용할 필요 없이 간단하게 비동기 통신을 구현할 수 있습니다.
       
4. **결론**

    위와 같은 이유로 SQS를 메시징 서비스로 최종 도입하게 되었습니다. SQS는 규모에 적합한 이벤트 전달 방식이며, AWS에서 관리하는 서비스로써 초기 설치나 복잡한 설정 없이 쉽게 사용할 수 있습니다. Kafka나 Kinesis는 대용량 분산 환경에 더 적합하고, Redis나 RabbitMQ는 설정과 유지보수의 복잡성이 요구되어 본 프로젝트의 요구사항에 적합하지 않다고 판단되었습니다. SQS는 주문 상태 변경과 같은 단순한 이벤트 처리에 최적화된 선택지로서, 유지 관리가 간편하고 비용 효율적이며 프로젝트의 목표에 가장 부합하는 도구였습니다.
   <br/><br/>
</details>

<details>
<summary><b> Gradle Multimodule </b></summary>
  
1. **문제 상황**

    야구 경기 관련 애플리케이션을 개발하는 과정에서 실시간 경기 채팅, API 서비스, 핵심 로직, 외부 서비스와의 연동 등을 각각 독립적으로 개발하고 유지보수할 필요성이 제기되었습니다. 각 기능이 독립적인 역할을 수행하는 동시에 서로 긴밀하게 통합되어야 하는 구조를 요구하게 되면서, 하나의 프로젝트에서 모든 기능을 관리하기엔 코드가 복잡해지고 의존성 관리가 어려워지는 문제가 발생했습니다. 이에 따라, 프로젝트의 구조를 보다 체계적으로 분리하고, 각 모듈 간의 의존성을 명확히 관리할 수 있는 방안이 필요해졌습니다.
    
2. **대안 비교**

    ![image](https://github.com/user-attachments/assets/7d4956e8-3187-41b5-a067-4720dbb88527)
    
3.  **Gradle multimodule 도입이유**

    - **모듈 간 독립성 유지**: 프로젝트를 `chat`, `api`, `core`, `integration` 등 기능별 모듈로 분리하여, 각 기능을 독립적으로 개발하고 유지보수할 수 있게 합니다. 특히 `chat` 모듈의 경우 실시간 경기 채팅 기능을 담당하고 있으며, 실시간 통신 로직과 관련된 의존성만 따로 관리할 수 있습니다.
    - **효율적인 의존성 관리**: Gradle Multi-Module 구조를 통해 공통으로 사용하는 라이브러리와 종속성을 `core` 모듈에 두어 중복 사용을 줄이고, 각 모듈이 필요한 라이브러리만 관리하여 빌드 속도와 효율성을 개선할 수 있습니다.
    - **확장성과 유연성**: Multi-Module 구조는 기능 확장 시 독립적인 모듈로 추가할 수 있어, 기능 개발의 확장성이 높습니다. `integration` 모듈을 통해 외부 서비스와의 연동을 별도로 관리하여, 나머지 모듈들이 쉽게 영향을 받지 않도록 구성할 수 있습니다.
    - **코드 재사용성 및 유지보수 용이성**: `core` 모듈에 공통 기능과 핵심 로직을 두어 재사용 가능하게 하고, 각 모듈이 이러한 기능을 공유할 수 있게 구성하여 유지보수가 용이합니다.
    - **모듈 간 독립성 유지**: 프로젝트를 chat, api, core, integration 등 기능별 모듈로 분리하여, 각 기능을 독립적으로 개발하고 유지보수할 수 있게 합니다. 특히 chat 모듈의 경우 실시간 경기 채팅 기능을 담당하고 있으며, 실시간 통신 로직과 관련된 의존성만 따로 관리할 수 있습니다.    
    - **효율적인 의존성 관리**: Gradle Multi-Module 구조를 통해 공통으로 사용하는 라이브러리와 종속성을 core 모듈에 두어 중복 사용을 줄이고, 각 모듈이 필요한 라이브러리만 관리하여 빌드 속도와 효율성을 개선할 수 있습니다.
    - **확장성과 유연성**: Multi-Module 구조는 기능 확장 시 독립적인 모듈로 추가할 수 있어, 기능 개발의 확장성이 높습니다. integration 모듈을 통해 외부 서비스와의 연동을 별도로 관리하여, 나머지 모듈들이 쉽게 영향을 받지 않도록 구성할 수 있습니다.
    - **코드 재사용성 및 유지보수 용이성**: core 모듈에 공통 기능과 핵심 로직을 두어 재사용 가능하게 하고, 각 모듈이 이러한 기능을 공유할 수 있게 구성하여 유지보수가 용이합니다.

4. **결론**
    
    Gradle Multi-Module 구조는 프로젝트를 체계적으로 관리하고, 모듈별로 독립성과 유연성을 유지하면서도 통합된 빌드 환경을 제공하여 개발 효율성을 크게 향상시킬 수 있습니다. 프로젝트를 모듈화함으로써 개발 속도와 코드 유지보수성이 증가하고, 팀 협업 시 각 모듈을 독립적으로 작업할 수 있어 개발 프로세스가 개선됩니다. 단일 프로젝트나 개별 레포지토리 분리의 경우 유지보수와 통합이 복잡해질 수 있어, Gradle Multi-Module 구조를 채택하는 것이 가장 적합한 선택으로 판단되었습니다.
   <br/><br/>
</details>

<details>
<summary><b> 동시성 제어 </b></summary>


1. **문제 상황** 

야구 경기를 관람하는 관중들이 경기장 내 매장에서 주문하고 대기하는 과정이 혼잡하고 비효율적입니다. 한꺼번에 많은 관중이 몰리면서 대기 시간이 길어져 관람 경험에 방해가 되고, 매장에서도 주문 처리가 지연되는 문제가 발생합니다. 이러한 상황에서 주문과정의 혼잡을 완화하고, 대기 시간을 최소화 할 수 있는 시스템이 필요했습니다.

2. **대안 비교** 

    ![image](https://github.com/user-attachments/assets/3fb0674b-10c0-4c7a-b808-b53475196082)


3.  **동시성 제어 분산락 도입이유** 

경기장과 같은 대규모 동시성 환경에서 효율적으로 주문을 처리하기 위해 분산락 방식을 도입했습니다. 분산락은 여러 서버에서 동시에 자원을 관리할 수 있도록 지원하며, Redis와 같은 외부 시스템을 활용해 락을 관리함으로써 데이터의 일관성과 동기화를 보장할 수 있습니다. 특히 사전 주문 시스템에서는 높은 동시성을 요구하므로, 분산락을 통해 처리 속도와 일관성을 동시에 확보하고자 했습니다.

4. **결론**

사전 주문 시스템의 효율적 운영을 위해 높은 동시성을 지원하고 데이터 일관성을 보장하는 분산락 방식을 선택했습니다. 이를 통해 주문 처리가 보다 신속하고 안정적으로 이루어질 수 있으며, 관중들이 대기 시간을 줄이고 보다 편리하게 경기를 관람할 수 있도록 지원할 수 있습니다.

 <br/><br/>

</details> 

<details>
<summary><b> Redis Cache </b></summary>
     
### 도입 이유

선수 팔로우 랭킹 시스템에서 Redis 캐시를 도입한 목적은 **실시간으로 팔로우 수에 따른 순위 조회 속도를 높이고 DB 부하를 줄이기** 위함입니다. 특히 Sorted Set 구조가 랭킹 조회에 적합하여 Redis ElastiCache를 활용하고 있습니다. 이를 위해 **Write-Through**와 **Read-Through** 전략을 조합해 캐시를 유지 관리합니다.

### 캐시 무효화 전략 설명

1. **FollowService - Write-Through 전략**
    - **목적**: 팔로우가 추가될 때마다 즉시 Redis 캐시에 반영하여 데이터 일관성을 유지하는 것입니다.
    - **동작**: 사용자가 팔로우하면 DB와 동시에 Redis의 Sorted Set에서도 해당 선수의 팔로우 수를 증가시켜 랭킹 순위를 즉시 업데이트합니다.

2. **PlayerService - Read-Through 전략**
    - **목적**: 선수 랭킹 조회 시 캐시에 있는 데이터를 우선 확인해 조회 속도를 높이는 것입니다.
    - **동작**: 랭킹 조회 요청이 들어오면 먼저 Redis 캐시에서 해당 데이터를 확인합니다. 캐시에 데이터가 없을 경우에만 DB에서 데이터를 가져와 캐시에 저장해두고 반환합니다.

### 문제 상황

1. **캐시 일관성 문제**
    - **상황**: Write-Through 전략은 팔로우 이벤트 발생 시 캐시를 갱신하여 실시간 일관성을 유지할 수 있습니다. 그러나 일부 예외 상황(예: 시스템 중단, Redis 서버 장애 등)에서는 DB와 Redis의 일관성에 문제가 발생할 수 있습니다.
    - **해결 대안**: 비정상 종료나 장애 발생 시 캐시를 초기화하고 자정에 전체 데이터를 DB에서 다시 로드하는 방법(현재 스케줄러 방식)을 사용하여 Redis와 DB의 일관성을 주기적으로 보완합니다.

2. **캐시 메모리 관리 및 용량 문제**
    - **상황**: Redis의 Sorted Set은 캐시 메모리를 차지하는데, 데이터가 많아지면 메모리 관리가 필요해집니다. 특히 팔로우 랭킹을 모든 선수를 대상으로 지속적으로 관리해야 하는 경우에는 메모리 소모가 클 수 있습니다.
    - **해결 대안**: 랭킹 조회 시 특정 범위(예: 상위 10명)만 캐시에 유지하는 방식으로 관리하면서, 캐시의 만료 기간(TTL)을 설정해 일정 주기마다 갱신할 수 있습니다.

### 대안 비교

| 전략                 | 장점                                 | 단점                                                        | 적용 시기                             |
| -------------------- | ------------------------------------ | ----------------------------------------------------------- | ------------------------------------- |
| Write-Through        | 실시간 일관성 유지, 빠른 업데이트    | 캐시와 DB 모두 업데이트하므로 약간의 오버헤드               | 팔로우 변경이 빈번히 발생하는 경우    |
| Read-Through         | 조회 시 빠른 응답 속도, DB 부하 감소 | 캐시 미스 시 초기 조회 지연                                 | 조회가 빈번히 발생하는 경우           |
| TTL 기반 초기화      | 주기적인 캐시 정리와 메모리 최적화   | 정해진 시간에만 초기화되어 실시간성을 보장하지 못할 수 있음 | 데이터가 오래될 때                    |
| 스케줄러 기반 초기화 | 정확한 시간에 캐시 일관성 보장       | 초기화 주기 설정이 고정적                                   | 자정 기준으로 캐시 정리가 필요한 경우 |

### 결론

팔로우 랭킹을 위한 캐시 구조에서 **Write-Through와 Read-Through 전략**을 병합해 활용하는 것은 각 데이터 갱신 및 조회 시점에서 캐시 일관성과 성능을 최적화할 수 있는 효과적인 접근 방식입니다. 
다만, 캐시와 DB 간의 일관성을 보완하기 위해 현재처럼 스케줄러로 자정마다 캐시를 초기화하는 추가 전략이 필요하며, 
TTL 설정은 자정 외의 시간에서도 일정 주기마다 캐시를 유지하고 싶을 때 유용합니다.
   <br/><br/>
</details>

<details> <summary> <b>Indexing</b> </summary>
   <details>
      <summary> ❌ 10만명 players </summary>
         <details> <summary> 인덱스 1개 적용</summary>
            <br>SELECT * FROM players WHERE player_name = '정지원';<br>
            ⇒ 2번의 테스트를 했으나, 인덱스를 잘못 설정한 것 같다.<br>
- **평균 실행 시간**이 인덱싱 후 증가 (0.005018초 → 0.005739초).<br>
- **최대 실행 시간**은 인덱싱 후에 약 두 배 이상 증가 (0.043525초 → 0.079399초).<br>
⇒ 아무래도, `player_name`의 중복도가 높아 발생한 것 같다.<br>
⇒ 인덱스를 `player_name`, `player_id`로 2개 설정하였다.<br>
         </details>
         <details> <summary> 인덱스 2개 적용</summary>
            <br>SELECT * FROM players WHERE player_name = '정지원';<br>

### 1. **인덱스 적용 전**
- **파일 1**:
    - 평균 실행 시간: 0.003061초
        - 최소 실행 시간: 0.000100초
        - 최대 실행 시간: 0.038680초
    - **파일 2**:
        - 평균 실행 시간: 0.002979초
        - 최소 실행 시간: 0.000053초
        - 최대 실행 시간: 0.038885초
    - **파일 3**:
        - 평균 실행 시간: 0.003079초
        - 최소 실행 시간: 0.000101초
        - 최대 실행 시간: 0.042486초
    
    ### 2. **인덱스 1개 적용 후**
    
    - **파일 1**:
        - 평균 실행 시간: 0.004245초
        - 최소 실행 시간: 0.000082초
        - 최대 실행 시간: 0.056506초
    - **파일 2**:
        - 평균 실행 시간: 0.005739초
        - 최소 실행 시간: 0.000139초
        - 최대 실행 시간: 0.079399초
    - **파일 3**:
        - 평균 실행 시간: 0.005677초
        - 최소 실행 시간: 0.000085초
        - 최대 실행 시간: 0.045023초
    
    ### 3. **인덱스 2개 적용 후**
    - **파일 1**:
        - 평균 실행 시간: 0.003278초
        - 최소 실행 시간: 0.000068초
        - 최대 실행 시간: 0.042980초
    - **파일 2**:
        - 평균 실행 시간: 0.003646초
        - 최소 실행 시간: 0.000082초
        - 최대 실행 시간: 0.051115초
    - **파일 3**:
        - 평균 실행 시간: 0.003268초
        - 최소 실행 시간: 0.000073초
        - 최대 실행 시간: 0.039152초
    
    ### 결론:
    
    1. **인덱스 1개 적용 후** 평균 실행 시간이 증가했으며, 최대 실행 시간도 전반적으로 증가했습니다.
    2. **평균 실행 시간**은 인덱스 2개 적용 후에 약간 증가했습니다:
        - 파일 1: 0.003061초 → 0.003278초
        - 파일 2: 0.002979초 → 0.003646초
        - 파일 3: 0.003079초 → 0.003268초
    3. **최소 실행 시간**은 인덱스 2개 적용 후에 약간 감소했습니다, 이는 인덱스 적용 후 일부 쿼리가 더 빠르게 실행된다는 것을 의미할 수 있습니다.
    4. **최대 실행 시간**은 인덱스 적용 후 전반적으로 증가했습니다, 특히 파일 2에서 최대 실행 시간이 증가하여 0.051115초에 도달했습니다.
         </details>
   </details>
   <details><summary> ❌ 50만명 players </summary>
      <details>
         <summary> 평균 실행 시간 </summary>
            <br>인덱스 전: 0.000821<br>
            인덱스 1개 후: 0.001032<br>
            인덱스 2개 후: 0.001110
      </details>
   </details>
   <details><summary> ⚠️ 100만명 players </summary>
      <details><summary> 평균 실행시간 </summary>
         <img width="680" alt="스크린샷 2024-11-21 오후 6 01 36" src="https://github.com/user-attachments/assets/dbce6e08-2046-4d1a-8ec3-71ecf05acd06">
      </details>
      <details><summary> 테스트 방법</summary>
         <img width="426" alt="스크린샷 2024-11-21 오후 6 05 32" src="https://github.com/user-attachments/assets/a53356d4-8d91-4aa6-861e-c9ec5eb0e5c1">
         <img width="401" alt="스크린샷 2024-11-21 오후 6 05 53" src="https://github.com/user-attachments/assets/df9ec9a6-211e-4591-b408-5c62c528a30b">
      </details>
   </details>
   <details><summary> ✅ 100만명 players with 다른 indexes </summary>
      <details><summary> 테스트 결과 </summary>
         <img width="652" alt="스크린샷 2024-11-21 오후 6 08 52" src="https://github.com/user-attachments/assets/2ffdaa0d-a0c9-4141-9a0d-03a7b61205e6">
      </details>
      <details><summary> 테스트 방법 </summary>
         <img width="645" alt="스크린샷 2024-11-21 오후 6 09 40" src="https://github.com/user-attachments/assets/436f3c00-9906-4f8e-bebc-bf756561d1e0">
      </details>
   </details>
   <details><summary> ✅ 부하테스트 </summary>
      <br><b> 테스트 진행 </b>
      <br> : 인덱싱 전/후 각각 5번 테스트 진행<br/>
      <details><summary> 시나리오 </summary>
         : 10명의 가상 사용자가 1초 간격으로 모두 시작하여, 각자 5번의 반복 루프 동안 총 100개의 요청을 보내고, 전체적으로 약 1초에 100개의 요청이 전송되도록 조절하여 <b>총 1000개의 HTTP 요청</b>을 1분에 걸쳐 전송.
      </details>
      <details><summary> 데이터 목록 </summary>
         <details><summary> 인덱스 전 </summary>
            <img width="612" alt="스크린샷 2024-11-21 오후 6 22 34" src="https://github.com/user-attachments/assets/9f1872d2-cfd0-4449-bb7b-933ca5b21b3e">
         </details>
         <details><summary> 인덱스 후 </summary>
            <img width="612" alt="스크린샷 2024-11-21 오후 6 22 53" src="https://github.com/user-attachments/assets/87aa7e5b-b486-4e15-a0f1-268cea0bffa1">
         </details>
         <details><summary> 인덱스 전/후 결과 </summary>
            <img width="646" alt="스크린샷 2024-11-21 오후 6 25 02" src="https://github.com/user-attachments/assets/7735b8fe-be24-4ea8-83ae-3099b0047b80">
         </details>
         <details><summary> 성능 개선 그래프 </summary>
            <img width="633" alt="스크린샷 2024-11-21 오후 6 25 26" src="https://github.com/user-attachments/assets/5dbe697e-091c-4f09-a97d-d431e4ab1df4">
         </details>
      </details>
   </details>
</details>   



## ⚾️ 역할 분담 및 협업 방식
### 📌 역할 분담
|     역할     |                    이름                     | 역할 설명                                                                                                                                                                                                                                    |
| :----------: | :-----------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|  **👑 팀장**  |    [@정은교](https://github.com/ekj1003)    | - 프로젝트 전체 관리 및 방향 설정<br>- 주요 의사결정과 팀 내 소통 담당 <br>- 구단/구장/선수(CRUD) <br>- AWS S3 <br>- Indexing <br>- MultiModule <br>- AWS RDS <br>- OAuth 2.0 카카오 소셜로그인                                              |
| **👑 부팀장** | [@이재희](https://github.com/leejaehee0807) | - 팀장 보조 및 작업 분배<br>- 주요 문서 작성 및 발표 자료 준비 <br>- 서버와 데이터베이스 설계 및 관리 <br>- 회원/유저/선수/구단 커뮤니티/일정(CRUD) <br>- 팀원 간 협업 조율과 피드백 제공 <br>- 동시성제어 <br>- OAuth 2.0 카카오 소셜로그인 |
|  **👑 팀원**  |  [@오현택](https://github.com/duduio2050)   | - 주요 개발 작업 수행<br>- 기술적 난관 발생 시 해결책 제안<br>- 구단 게시판/댓글(CRUD) <br>- 실시간 채팅 <br>- CI/CD <br>- ElasticBeanstalk <br>- ElasticCache                                                                               |
|  **👑 팀원**  | [@박현국](https://github.com/HyunKook-Park) | - 주요 개발 작업 수행<br>- 코어 기능 구현 및 문제 해결 담당 <br>- 가게/메뉴/주문(CRUD) <br>- AWS SQS <br>- MultiModule <br>- Redis Cache <br>- Cloudfront                                                                                    |

### 📌 협업 방식
- 점심 시간 : 12:00 ~ 13:00
- 저녁 시간 : 18:00 ~ 19: 00
- 아침 회의 : 10:00 ~ 10:30
- 회의가 길어질 경우 50분 회의 10분 휴식 하기 !!
- 저녁 회의 : 20:00 ~ 21:00 + 코드 리뷰
- 회의할 때 꼭 캠 키기, 다른 때는 자유~
- 자리비울 때, 오픈채팅방, 상태메세지, 춤추기
- 퇴실 전 매일 스크럼 기획 회의 진행상황 체크, 캘린더에 체크
- 퇴실 전 5분 기록보드 작성
- 매일 저녁 먹고 바로 진행상황 체크

<br/><br/><br/>

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
 

<br/><br/><br/>
# ⚾️ 야구 나두? : 어플리케이션 실행 방법

## ⚾️ mysql, redis 설치
1. **루트 디렉토리 이동**
2. ```docker-compose -f docker-compose-local.yml up```
<br/><br/><br/>

## ⚾️ API MODULE 실행 방법
1. **api/src/main/resources/application-local.yml 환경 변수 셋팅**
2. **application 실행**
<br/><br/><br/>

## ⚾️ CHAT MODULE 실행 방법
1. **chat/src/main/resources/application-local.yml 환경 변수 셋팅**
2. **application 실행**
<br/><br/><br/>
