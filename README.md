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


## KEY SUMMARY


## ⚾️ 인프라 아키텍쳐 & 적용 기술
### 📌 인프라 아키텍쳐
<img width="928" alt="InfraStructureDiagram" src="https://github.com/user-attachments/assets/4f8508d5-3082-4a15-a28b-53899af83a83">

### 📌 적용기술
<details>
<summary><b>🔎 적용 기술 상세보기</b></summary>

#### 애플리케이션 개발 및 프레임워크
<img src="https://img.shields.io/badge/JDK 17-green?style=for-the-badge&logo=#000000&logoColor=white"><img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
#### <img src="https://img.shields.io/badge/gradle multimoduel-red?style=for-the-badge&logo=gradle&logoColor=white">
프로젝트의 모듈화를 통해 코드의 재사용성과 관리성을 높이기 위해 Gradle 멀티모듈 구성을 사용했습니다. 각 모듈별로 독립적인 개발과 테스트가 가능하며, CI/CD 과정에서의 효율성을 높입니다.
#### <img src="https://img.shields.io/badge/Amazon ElastiCache-orange?style=for-the-badge&logo=amazonelasticache&logoColor=white">
선수의 랭킹을 캐싱하여 조회 속도를 높이기 위해 Redis를 도입했습니다. Redis는 빠른 응답 속도를 제공해 성능 향상에 기여합니다.
#### <img src="https://img.shields.io/badge/indexing-yellow?style=for-the-badge&logo=&logoColor=white">
선수 검색 속도를 최적화하기 위해 주요 데이터 필드(player_name, team_name)에 인덱싱을 적용하여 조회 성능을 강화했습니다.
#### <img src="https://img.shields.io/badge/AWS-green?style=for-the-badge&logo=#232F3E&logoColor=white"><img src="https://img.shields.io/badge/EC2-blue?style=for-the-badge&logo=amazonec2&logoColor=white"><img src="https://img.shields.io/badge/S3-sodomy?style=for-the-badge&logo=amazons3&logoColor=white"><img src="https://img.shields.io/badge/SQS-purple?style=for-the-badge&logo=amazonsqs&logoColor=white">
- **EC2** : 애플리케이션 서버를 호스팅하고, 유연한 확장성을 지원하는 AWS EC2를 선택했습니다.
- **S3** : 구단, 구장, 선수의 이미지 파일들을 S3를 사용하여 안전하게 관리하며, 애플리케이션에서 파일을 간편하게 업로드 및 다운로드할 수 있습니다.
- **SQS** : 비동기 처리와 메시지 전송을 위해 SQS를 도입하여 안정적인 메시지 큐를 통해 확장성과 내구성을 보장합니다.
#### <img src="https://img.shields.io/badge/AWS elastic beanstalk -pink?style=for-the-badge&logo=#2088FF&logoColor=white">
애플리케이션의 배포 및 관리를 간소화하고, 자동화된 인프라 환경을 통해 개발에 집중할 수 있도록 AWS Elastic Beanstalk을 도입했습니다. Elastic Beanstalk은 애플리케이션 실행 환경을 자동으로 프로비저닝하고 모니터링하며, 배포 프로세스를 최적화하여 운영 효율성을 높입니다.
#### <img src="https://img.shields.io/badge/Github Actions-camel?style=for-the-badge&logo=#2088FF&logoColor=white">
CI/CD 자동화를 통해 코드 품질과 배포 효율성을 높이기 위해 GitHub Actions를 사용했습니다. 코드 커밋 시 자동 빌드, 테스트, 배포 파이프라인을 구축하여 일관된 품질을 유지합니다.
####  <img src="https://img.shields.io/badge/Apche JMeter-red?style=for-the-badge&logo=apachejmeter&logoColor=white">
애플리케이션의 성능 테스트와 부하 테스트를 위해 Apache JMeter를 사용했습니다. 이를 통해 애플리케이션의 응답 시간을 최적화하고 병목 현상을 분석하여 성능을 개선했습니다.
####  협업 도구
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)<img src="https://img.shields.io/badge/Notion-black?style=for-the-badge&logo=notion&logoColor=white">
</details>

## 주요기능
- 덕질 커뮤니티
    : 최애 선수 팔로잉, 구단 응원 게시물, 댓글, 좋아요 기능

- 실시간 채팅
    : Redis Pub/Sub을 통한 매 경기마다 실시간 채팅으로 야구인들과 소통!

- 구장별 가게 음식 예약
    : 음식을 주문/예약할 때 분산락을 이용해 메뉴의 재고와 순서를 파악해 혼잡스럽지 않도록 교통정리 하는 기능
  
- 음식 주문 알림
    : AWS SQS를 통해 비동기 메세지큐로 특정 이벤트에 대한 실시간 음식 주문 알림 기능

- CI/CD
    : Elastic Beanstalk를 사용한 무중단 배포 구현
  
- 인덱싱
    : 선수 테이블 인덱싱


## 기술적 고도화


## 역할 분담 및 협업 방식
| 팀장 | 부팀장 | 팀원 | 팀원 |
| :------------: | :------------: |:------------:|:------------:|
|[@정은교](https://github.com/ekj1003)|[@이재희](https://github.com/leejaehee0807)|[@오현택](https://github.com/duduio2050)|[@박현국](https://github.com/HyunKook-Park)

## 성과 및 회고


## 👨‍🏫 프로젝트 소개


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

