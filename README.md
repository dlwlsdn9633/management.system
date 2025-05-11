# 📝 Management System
## 📅 프로젝트 시작일
2023.07.20 (부서 관리 시스템)

2023.04.30 (스프링 부트로 재구성)

## 🎯 프로젝트 목표
이 프로젝트는 학창시절 개발했던 부서 관리 시스템을, 회사에서의 6개월간의 실무 경험을 바탕으로 Spring Boot Framework를 활용해 재구성한 것입니다. 

스프링에 대한 학습을 주된 목표로 삼고, 기존 시스템의 핵심 기능을 실무에서 익힌 기술들과 결합하여 기능을 개선하고 확장했습니다.

앞으로도 이 프로젝트는 실무 경험과 개인 학습을 토대로 지속적으로 개선하고 발전시켜 나갈 예정입니다. 모듈형 시스템으로 확장해 나가며, 새롭게 습득한 기술을 실험하고 적용하는 실용적인 기반 프로젝트로 활용하고자 합니다.

추가 및 개선된 주요 기능은 다음과 같습니다:
- 무한 계층 구조의 댓글 기능
- 이메일 알림 및 리마인더 기능
- 엑셀 첨부 파일 업로드 및 처리 기능

## 🗄️ Entity Relationship Diagram
![Image](https://github.com/user-attachments/assets/e3d6805c-10b0-448d-93f2-1fb0cd9ee633)

## 🛠️ 기술 스택
### 백엔드: 
- Spring Boot
- MyBatis

### 프론트엔드: 
- Thymeleaf
- jQuery
- JavaScript

### 데이터베이스: 
- MySQL (InnoDB)

### 기타 사용 기술:
- Quartz Scheduler: 스케줄러 기능
- JavaMail: 이메일 발송 기능
- POI 라이브러리: 엑셀 파일 첨부 기능

## 🖥️ 주요 기능
- 프로젝트 진행 상황 추적
프로젝트의 진행 상태를 관리하고 추적하는 기능

## 🖥️ 추가 기능
### 무한 계층 구조의 댓글
하나의 댓글에서 시작해, 대댓글을 무한히 추가할 수 있도록 설계된 유연한 댓글 시스템입니다.

<img width="190" alt="Image" src="https://github.com/user-attachments/assets/99c51fe9-7f12-4758-bf29-b3c939c2d542" />

- groupNo: 그룹 ID
- step: 그룹 내 ID
- depth: 계층

#### 댓글을 추가하는 방법

댓글을 작성할 때는먼저 데이터베이스에 저장된 댓글 중 가장 큰 그룹 ID를 조회하고, 그 값에 1을 더해 새 댓글의 그룹 ID로 설정합니다.

- 최상위 댓글은 항상 step이 0으로 시작합니다.
- 이후 이 댓글에 대댓글이 달리면, step 값이 1씩 증가하며 정렬됩니다.

<p align="center"><img width="473" alt="Image" src="https://github.com/user-attachments/assets/2617129f-1833-438f-8541-0502c539c839" /></p>

<p align="center"><img width="439" alt="Image" src="https://github.com/user-attachments/assets/76d499f8-e612-4137-b9af-476806cfcd97" /></p>

#### 대댓글을 추가하는 방법 
대댓글을 작성할 경우, 현재 댓글 기준으로 다음 위치에 삽입 가능한 최소 step 값을 조회합니다. 

해당 조건을 만족하는 댓글이 없다면 기본값 0을 반환합니다.

<p align="center"><img width="448" alt="Image" src="https://github.com/user-attachments/assets/3944b10d-3159-4a30-a57f-b522c2319a16" /></p>

조회된 step 값보다 크거나 같은 기존 댓글들의 step 값을 1씩 증가시켜 대댓글 삽입 공간을 확보합니다.

<p align="center"><img width="452" alt="Image" src="https://github.com/user-attachments/assets/2190eca5-429c-4f95-81b9-8720f397130f" /></p>

<p align="center"><img width="443" alt="Image" src="https://github.com/user-attachments/assets/3bf39c51-e87c-4ea7-bfc8-91d2aca7bd83" /></p>

nextStep이 0일 경우, 해당 그룹의 마지막에 삽입되는 새로운 대댓글임을 의미합니다. 이때는 getMaxStepInGroup()을 통해 가장 큰 step 값을 조회하고, 해당 값에 1을 더해 삽입합니다.

<p align="center"><img width="448" alt="Image" src="https://github.com/user-attachments/assets/01b10d5e-7ae1-462c-9101-54b085ba84db" /></p>

### 이메일 알림 및 리마인더
프로젝트 진행 상황을 가입된 멤버들에게 정기적으로 안내하기 위해 이메일 발송 시스템을 구현했습니다.

실무에서 Quartz Scheduler를 사용하는 사례를 참고해 연습을 겸해 도입하였으며, 이를 통해 정해진 시간에 자동으로 이메일을 발송하도록 설정했습니다.

먼저, Job 인터페이스를 구현한 AlertJob 클래스의 execute 메서드에서 실제 작업 로직을 정의했습니다.

이 메서드에서는 프로젝트 마감일까지 1일, 2일, 3일, 7일, 14일이 남은 멤버들을 쿼리문으로 조회한 후, 해당 멤버들에게 안내 메일을 발송합니다.

<p align="center"><img width="466" alt="Image" src="https://github.com/user-attachments/assets/9d8b46f3-1e0c-44e0-9ea0-50a45aa5bbb7" /></p>
<p align="center"><img width="340" alt="Image" src="https://github.com/user-attachments/assets/ce5f0dc1-171c-4583-8a9b-bffb88258f08" /></p>

이메일 발송에는 JavaMail을 사용하고, SMTP 서버는 네이버 메일을 연동하여 구성했습니다.

<p align="center"><img width="302" alt="Image" src="https://github.com/user-attachments/assets/4695868c-808e-4fd3-8290-de768fc30bdb" /></p>
<p align="center"><img width="215" alt="Image" src="https://github.com/user-attachments/assets/4218ab97-b47e-455b-8f7d-7a39ccbea2a6" /></p>

### 엑셀 첨부 기능
반복적인 프로젝트 생성 작업을 줄이기 위해, 엑셀 파일을 선택하고 업로드하면 해당 데이터를 자동으로 데이터베이스에 저장하는 기능을 구현했습니다.
<p align="center">
  <img width="400" src="https://github.com/user-attachments/assets/b34953db-083d-4568-90a3-1f8d5f31b988" />
  <br>⬇️<br>
  <img width="400" src="https://github.com/user-attachments/assets/f4f3582b-6d32-49c0-8e58-aaec011c1d92" />
  <br>⬇️<br>
  <img width="400" src="https://github.com/user-attachments/assets/fedf5f52-0e38-4de5-b943-0e1671f8356d" />
</p>

