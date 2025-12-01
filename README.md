## maven, mysql 기준 사전 settings :

---

### SQL 실행

    create database board_service;
    
    use board_service;
    
    CREATE TABLE IF NOT EXISTS member (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL UNIQUE, -- 로그인 ID
        password VARCHAR(255) NOT NULL, -- 암호화된 비밀번호(BCrypt 해싱)
        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
    );
    
    -- 지금은 쓰지 않지만, 나중에 관리자 권한으로 계정 찾기나 글, 댓글 삭제할 경우 필요하다고 판단
    CREATE TABLE IF NOT EXISTS member_role (
        member_id BIGINT NOT NULL,
        role_name VARCHAR(50) NOT NULL,  -- 예: 'ROLE_USER', 'ROLE_ADMIN'
        PRIMARY KEY (member_id, role_name),
    
    FOREIGN KEY (member_id) REFERENCES member(id)
    );
    
    CREATE TABLE IF NOT EXISTS article (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(256) NOT NULL,
        content TEXT NOT NULL,
        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at DATETIME NULL,
        member_id BIGINT NOT NULL,
        view_count BIGINT NOT NULL DEFAULT 0,
    
    FOREIGN KEY(member_id) REFERENCES member(id) ON DELETE CASCADE -- 게시글은 멤버 삭제 시 연쇄적으로 삭제
    );
    
    CREATE TABLE IF NOT EXISTS comment (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        content VARCHAR(1024) NOT NULL,
        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    member_id BIGINT NOT NULL,
        article_id BIGINT NOT NULL,
    
    FOREIGN KEY (member_id) REFERENCES member(id),
        FOREIGN KEY (article_id) REFERENCES article(id) ON DELETE CASCADE -- 댓글은 게시글 삭제 시 연쇄적으로 삭제
    );

### application.properties

    spring.application.name=boardservice
    
    # Active Profile
    spring.profiles.active=jpa
    
    # Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306(3305)/board_service
    spring.datasource.username=myid
    spring.datasource.password=mypassword
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    
    # JPA Configuration
    spring.jpa.hibernate.ddl-auto=validate
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

## 인증, 인가 방식

---

### 인증(Authentication) :

    스프링 시큐리티 사용
    
    UserDetailService를 통한 username/password 기반 폼 로그인
    
    PasswordEncoder(BcryptPasswordEncoder)로 단방향 인코딩 암호화
    
    인증 결과를 HTTP 세션(JSESSIONID)에 저장하는 세션 기반 인증 방식

### 인가(Authorization) :

    SecurityFilterChain을 활용해 CSRF, 로그인, 로그아웃, URL 인가 규칙 설정