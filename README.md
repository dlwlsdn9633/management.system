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
댓글을 작성하면, 먼저 데이터베이스에 저장된 댓글들 중 가장 큰 그룹 ID를 조회한 뒤, 그 값에 1을 더해 새로운 댓글의 그룹 ID로 설정합니다. 댓글은 최상위 항목이므로 step 값은 0으로 초기화되며, 이후 이 댓글에 대댓글이 달릴 경우 step 값은 1씩 증가합니다.

<img width="473" alt="Image" src="https://github.com/user-attachments/assets/2617129f-1833-438f-8541-0502c539c839" />

<데이터 베이스에 저장된 댓글들 중 가장 큰 그룹 ID를 조회하는 Query>

<img width="439" alt="Image" src="https://github.com/user-attachments/assets/76d499f8-e612-4137-b9af-476806cfcd97" />

<댓글 추가하는 서비스 로직 코드>

#### 대댓글을 추가하는 방법 
해당 쿼리문 조건을 만족하는 가장 큰 step을 찾는다. 그리고 찾은 step보다 크거나 같은 comment들을 모두 자기 step에 1을 더한 후 업데이트를 한다. 그리고 update한 step을 


### 이메일 알림 및 리마인더
프로젝트 진행 상황에 대한 알림과 리마인더를 이메일로 발송

### 엑셀 첨부 기능
프로젝트와 관련된 데이터를 엑셀 파일로 첨부하여 관리하는 기능

