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

- 

- Redis를 활용한 캐시 전략

- 인덱스를 활용한 최적화

- 알림 기능

## ⏲️ 개발기간
- 2024.10.14(월) ~ 2024.10.17(목)

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
| API 명세 | API 명세  | API 명세 | API 명세 |
| :------------: | :------------: |:------------:|:------------:|
|<img src="https://github.com/SemiFinalPJ/backend/blob/dev/src/img/api%20%EB%AA%85%EC%84%B81.png" width="300" height="200"/>|<img src="https://github.com/SemiFinalPJ/backend/blob/dev/src/img/api%20%EB%AA%85%EC%84%B82.png" width="300" height="200"/>|<img src="https://github.com/SemiFinalPJ/backend/blob/dev/src/img/api%20%EB%AA%85%EC%84%B83.png" width="300" height="200"/>|<img src="https://github.com/SemiFinalPJ/backend/blob/dev/src/img/api%20%EB%AA%85%EC%84%B84.png" width="300" height="200"/>|

## ✔️ ERD
![](https://github.com/SemiFinalPJ/backend/blob/dev/src/img/ERD.png)

## ✔️ Trouble Shooting
### 강태영
- 인덱스를 조회 속도가 가장 느린 조건에 설정했으나, 성능 개선이 크게 이뤄지지 않음, 성능 개선을 위해 상대적으로 덜 중복되는 title과 info필드를 인덱스로 지정함.

### 김아름
- 하나의 카드에 여러 스레드가 접근해 변경을 시도하는 테스트에서 낙관적 락을 사용하니 충돌이 너무 많아 계속 롤백하고 다시 시도하며 테스트가 끝나지 않는 이슈 발생. 최대 시도 횟수 정의한 후 초과시 종료하도록 함.

### 정은교
- 한 유저가 카드를 무제한적으로 조회하지 않기 위해서 5초 제한을 cardService 상에서 로직으로 구현을 하였음. 하지만, 캐싱상에서 조회수 데이터뿐만 아니라 조회 기록 데이터(card:user:view:{user_id}:{card_id}) 를 ttl 5초로 설정하여 무제한으로 조회를 하지 않도록 하는 방안을 제안해주셔서 수정하는 방향으로 진행함.

### 황인서
- 레디스에 카드 ID와 조회수만 저장되어 어플리케이션 로직 내에서 다시 조회수를 가지고 정렬해야 하다 보니 시간이 오래 걸림, 이를 해결하기 위해 zest 자료구조를 사용해 조회수를 기준으로 정렬된 상태로 저장하여 랭킹 조회 성능을 개선했다.


