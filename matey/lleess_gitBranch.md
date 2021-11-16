# 스마트팜 프로젝트의 Git branch 전략

> **목차**
> 
> 1. [Git-Flow]()
> 2. [Git branch 전략]()

# 1. **Git-Flow**

- git을 통해 효율적으로 프로젝트를 관리하고 배포하기 위한 전략
- 규모가 큰 프로젝트 배포를 중점으로 브랜치 관리를 엄격하게 하는 모델

![다운로드.png](image/다운로드.png)

- **master** : 제품으로 출시될 수 있는 브랜치
- **develop** : 다음 출시 버전을 개발하는 브랜치
- **feature** : 기능을 개발하는 브랜치
- **release** : 이번 출시 버전을 준비하는 브랜치
- **hotfix** : master 브랜치에서 발생한 버그를 긴급하게 수정하는 브랜치

 참고 : [Git,Git-Flow](https://www.notion.so/Git-Git-Flow-5fdaa4633c564d2ba6dcb95d6884384c) 

# 2. Git branch전략

<aside>
💡 메이티의 스마트팜 학습 시스템은 Git으로 버전관리를 하고 **솔루션, 클라우드** 서비스로 구분됩니다.
서비스를 안정적으로 운영하며 오류를 수정하고, 새로운 기능을 개발할 수 있도록 **각 서비스별 소스코드 버전관리 전략을 구상**해보세요.

1. **솔루션: 독립 서비스 -** 주로 학교를 대상으로한 서버 납품/설치형 서비스 제공
	- 대부분의 소스 파일이 서로 동일하지만, 같은 경로와 파일명으로 각자 유지해야 하는 파일도 존재 (ex: school_log.jpg)

2. **클라우드: 공통 서비스 -** 클라우드 인프라를 통해 교육 서비스 제공
	- 하나의 서비스에 지속적인 기능 업데이트, 오류 hotfix 처리, 신규 기관 추가 작업(이미지, 데이터 외)등을 처리

</aside>

- git-flow 전략은 절대적으로 5개의 branch를 적용하는 것이 아니라 상황에 맞게 적절한 branch를 이용함
- 솔루션과 클라우드 서비스는 학교별, 기관별로 관리하는 branch를 설정

      (기능을 담당하는 feature branch보다는 더 큰 범위의 branch 설정)

---

### 솔루션

- 모든 학교가 각각의 다른 서버에서 독립적으로 설치된 서비스를 이용
- main brunch(**master**) : 모든 학교의 공통적인 부분을 관리
- 대부분의 소스코드는 공통 branch에서 가져오면서 **각 학교별 branch 생성**해서 따로 관리
- 새로운 기능, 개발 소스코드 생성시 feature branch 생성 -> 해당 학교 brunch에 merge
- 특성화된 기능이 생기면 학교 branch에서 분기한 feature branch 생성

       → 작업이 완료되면 다시 학교 branch에 merge

- 공통 branch에서의 기능 업데이트가 있을 경우 master branch에서 분기한 feature branch 생성

      → 작업이 완료되면 다시 master branch로 merge

- 운영 배포 전 release branch는 각 학교 브랜치별 생성 후 실행
- test가 완료되면 배포용 branch 생성 후 배포

### 클라우드

- 모든 기관이 함께 하나의 클라우드 서버에 설치된 서비스를 이용
- main brunch(**cloud/master**) : 모든 기관의 공통적인 부분을 **cloud/master branch**에서 관리
- **각 기관별 branch(cloud/기관)을 생성**해서 관리
- 기능 추가, 개발
    - 공통 기능 업데이트 또는 운영 오류에 대한 hotfix가 발생했을 때,
    
           cloud/feature branch 또는 hotfix branch 생성 후 작업 ⇒ cloud/master branch에 merge
    
    - 특정 기관별로 적용해야하는 기능 업데이트가 존재할 때,
    
           cloud/feature branch 생성 후 작업 ⇒ 해당 기관 branch에 merge
    
- cloud는 모든 기관이 함께 하나의 클라우드 서버에 설치된 서비스를 이용하기 때문에

      ⇒ 모든 기관에 해당하는 작업이 cloud/master branch에 모두 합쳐져야 됨

- 운영 배포 전 release branch는 모든 작업이 병합된 cloud/master branch에서 분기해서 생성 후 실행
- test가 완료되면 배포용 branch 생성 후 배포