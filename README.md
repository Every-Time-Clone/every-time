# ⏰ Every Time Clone

- 에브리타임 어플을 클론하여 구현하되 비교적 부족함이 느껴졌던 기능을 개선하는 방향으로 개발하고자 하였습니다.
- 클라이언트는 프로토타입으로 대체하여 **백엔드 개발**에 초점을 맞춰 진행했습니다.

## 사용 기술

- Java 11
- Spring Boot
- MariaDB
- JPA

## 현재 구현된 기능

- 게시글 기능
    - 게시글과 관련된 기본 CRUD
- 유저 관리 기능
    - 유저와 관련된 기본 CRUD
- 댓글 기능
    - 댓글과 관련된 기본 CRUD
- 로그인 및 회원가입
    - 세션을 이용한 로그인 기능 구현
    - 유저 CRUD와 연동하여 구현된 회원가입

## 구현할 예정인 기능

- 채팅기능
    - 원본 에브리 타임 어플에서는 쪽지기능으로 구현되어있는 것을 채팅기능으로 업그레이드하고자함
- 게시판 관리 기능
    - 게시판 관리 기능의 경우 게시판을 삭제하고 만드는 기능이므로 게시글과도 연관되어 있어 추가적인 작업이 필요함
- 기능 업그레이드
    - 도배금지,하나의 사용자가 1분내에 여러개의 글 혹은 댓글 올리는 경우를 방지
    - 댓글,동시에 댓글을 입력했을 경우 충돌문제 해결
    - 금지어 기능,음란성 및 폭력성을 띄는 단어를 입력했을 때 게시글 혹은 댓글이 올라가지 않도록 함
    - 회원가입시 이메일 인증 기능

## Issue

## 프로젝트 관리

## Api Docs

[Swagger UI](https://toy-project-mcirt.run.goorm.site/swagger-ui/index.html#/)

## Use cases
[Wiki > Use cases](https://github.com/Every-Time-Clone/every-time/wiki/Use-cases)

## ERD

<img width="234" alt="image" src="https://github.com/Every-Time-Clone/every-time/assets/42714724/d3b0f790-71e9-47b2-a805-adad31f856f1">

