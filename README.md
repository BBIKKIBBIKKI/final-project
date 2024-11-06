# 야구 나두? 프로젝트

## 📖 목차
1. [프로젝트 소개](#프로젝트-소개)
2. [팀소개](#팀소개)
3. [주요기능](#주요기능)
4. [개발기간](#개발기간)
5. [기술스택](#기술스택)
6. [와이어프레임](#와이어프레임)
7. [API 명세서](#API-명세서)
8. [ERD](#ERD)
9. [Trouble Shooting](#trouble-shooting)
    
## 👨‍🏫 프로젝트 소개
**“야구 너도 할 수 있어”** 야구의 야자도 모르는 “**야린이”**들을 **야구 입문소 커뮤니티**
야구 규칙부터 선수 덕질까지, 광범위한 정보를 쉽고 재미있게 배울 수 있습니다. 팬덤 활동을 즐기고, 좋아하는 선수를 알아가며, 야구의 매력을 함께 알아볼까요?

## 💜 팀소개
| 팀장 | 실세 | 실세 | 실세 |
| :------------: | :------------: |:------------:|:------------:|
|[@정은교](https://github.com/ekj1003)|[@이재희](https://github.com/leejaehee0807)|[@오현택](https://github.com/duduio2050)|[@박현국](https://github.com/HyunKook-Park)|[@전우성](https://github.com/jeunwoosung)|

### [팀 노션](https://www.notion.so/teamsparta/7df9fd15fa05414c848d3e7d4d0c9266)

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

## ⏲️ 개발기간
- 2024.10.21(월) ~ 2024.11.22(금)

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

## ✔️ 와이어프레임
https://embed.figma.com/design/pDd7kx221auWuDxgz6xSD9/Bbikki?node-id=0-1&t=8HwyPIOHDGqx7S4q-1&embed-host=notion&footer=false&theme=system

## ✔️ API 명세서
https://www.notion.so/teamsparta/1262dc3ef51481a281d6fd7b6a26ffe9?v=1262dc3ef514813583bd000cfffdb22b&pvs=4

## ✔️ ERD
<img width="1089" alt="스크린샷 2024-10-24 오후 1 23 04" src="https://github.com/user-attachments/assets/3f37a084-2561-46c2-b206-2dfa5b78bb8c">

## ✔️ 아키텍쳐 다이아그램
<img width="902" alt="스크린샷 2024-11-06 14 51 38" src="https://github.com/user-attachments/assets/01d24b78-c12b-4605-8862-a770f5ebb7a9">

## ✔️ CI/CD 플로우 차트
<img width="671" alt="스크린샷 2024-11-06 14 52 50" src="https://github.com/user-attachments/assets/bfef9b79-c009-469d-9a84-ae15b2e7d1b5">
