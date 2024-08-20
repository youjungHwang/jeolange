## Spring Boot + Kotlin

### 프로젝트 구조
```kotlin
api
├── board
│   ├── application
│   │   ├── port
│   │   │   └── BoardRepository
│   │   └── BoardService
│   ├── domain
│   │   ├── Board
│   │   ├── BoardCreateCommand
│   │   ├── BoardUpdateCommand
│   │   └── BoardValidator
│   ├── infrastructure
│   │   ├── jooq
│   │   │   └── BoardJooqRepository
│   │   ├── jpa
│   │   │   ├── BoardEntity
│   │   │   └── BoardJpaRepository
│   │   └── BoardRepositoryAdapter
│   └── presentation
│       ├── request
│       │   ├── BoardCreateRequest.kt
│       │   └── BoardUpdateRequest.kt
│       ├── response
│       │   └── BoardResponse
│       └── BoardController
└── common
    ├── response
    │   ├── ErrorResponse
    │   └── Response
    ├── BaseErrorCode
    ├── BaseException
    └── GlobalControllerAdvice
```
---
### 코틀린 활용하기
### 🎃 data class
주로 데이터를 저장하는 용도로 사용되며, 여러 가지 유용한 기능을 자동으로 제공합니다.

#### 1. 자동 생성
- **equals()**: 객체의 동등성을 비교합니다.
- **hashCode()**: 객체의 해시 코드를 생성합니다.
- **toString()**: 객체를 문자열로 표현합니다.
- **copy()**: 객체의 복사본을 생성합니다.
- **componentN()**: 각 속성에 접근할 수 있는 메서드를 제공합니다.

#### 2. 주의점

- **기본 생성자 필드**: 데이터 클래스의 모든 속성은 기본 생성자에 포함되어야 하며, `val` 또는 `var`로 선언해야 합니다.

- **상속 불가**: 데이터 클래스는 다른 클래스를 상속받을 수 없습니다. 즉, 다른 클래스로부터 확장할 수 없습니다.

### 🎃 null 안정성
  - 물음표
  - 엘비스 연산자
    -   findByIdOrNull(id)가 유효한 값을 반환하면 그 값을 사용하고, null을 반환하면 예외를 발생시킵니다.
  ```kotlin
    boardRepository.findByIdOrNull(id) ?: throw BaseException(BaseErrorCode.NOT_FOUND_POST)
  ```


