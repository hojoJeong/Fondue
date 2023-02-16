## 👩‍🏫퐁듀(FundYou)는 무엇일까요?


의미있는 집들이 선물을 위한 **선물 펀딩 서비스** 애플리케이션입니다.

## 🤔퐁듀는 왜 만들어졌을까요?


### 배경

- 집들이에 **원하지 않는 선물, 쓸모 없는 선물을 받고 난감한 경험**이 존재했습니다.
- 집들이에 **초대됐지만 개인사유로 인해 참여하지 못해 아쉬운 경험**이 있습니다.
- 휴지, 술말고 **의미 있는 선물을 해주고 싶은 사람**이 존재했습니다.

### 해결방안

- 집들이 당사자는 **자신이 원하는 물건을 등록**하여 초대장 전송
- 집들이 참여자는 해당 물건을 언제 어디서든, 자유롭게 펀딩하여 선물
- 펀딩식으로 선물해 **고가의 선물이라도 여러사람이 모여 선물**해줄 수 있음

## 👨‍❤️‍💋‍👨퐁듀는 무슨 서비스를 제공할까요?

### 서비스 핵심 기능


**AR로 물건확인하기**

![image.gif](https://user-images.githubusercontent.com/72602912/219436055-0c560e4d-78e9-447f-90ec-0c06af9eb6bf.gif)


**펀딩 통계 확인**

![image.gif](https://user-images.githubusercontent.com/72602912/219436027-e479a25c-8e88-4230-a678-90887397b70a.gif)


**펀딩 하기**

![image.gif](https://user-images.githubusercontent.com/72602912/219436041-15fb0ca0-ac37-4f42-9b00-759cb030f122.gif)

### ************************************서비스 전체 기능************************************

- **다양한 상품 제공** - 상품 랭킹, 검색 랭킹, 카테고리, 랜덤으로 상품을 보여주며 가격별로 필터링이 가능합니다.
- **상품 찜하기** - 마음에 드는 상품은 찜할 수 있습니다.
- **상품 공유하기** - 다른 사람에게 해당 상품을 카카오 딥링크를 통해 전송할 수 있습니다.
- **상품 AR로 확인해보기** - AR 사용 가능 상품은 직접 배치할 수 있는 AR기능을 제공합니다.
- **위시리스트** - 원하는 상품을 담아두고 펀딩을 시작할 수 있습니다.
- **검색** - 사용자가 많이 검색한 키워드나 직접 키워드로 검색할 수 있습니다.
- **퐁듀하기** - 위시리스트에 담긴 물건으로 펀딩을 시작할 수 있습니다.
- **내 펀딩 통계보기** - 파이차트로 펀딩받은 금액과 메시지를 확인할 수 있습니다.
- **펀딩하기** - 딥링크를 통해 초대받은 퐁듀에 메시지와 함께 펀딩할 수 있습니다.

## ⚙퐁듀는 이렇게 개발되었어요

---

### 전체 기술 스택

![Untitled](https://user-images.githubusercontent.com/72602912/219432121-45e54d9b-ba07-4dff-b511-7e3125d886b8.png)

### 시스템 구조도

![Untitled](https://user-images.githubusercontent.com/72602912/219432109-057ff799-324a-405b-b4ec-6569e9ed47c1.png)

### ERD

![Copy of 퐁듀.png](https://user-images.githubusercontent.com/72602912/219432093-4018002f-c40a-4cc4-958f-deb80e347c4e.png)

### 기능 명세서

![Untitled](https://user-images.githubusercontent.com/72602912/219432112-7f7ee718-9ff3-4539-a888-f1adda46b9d6.png)

## 📱Android


### 사용한 라이브러리

| 이름 | 설명 |
| --- | --- |
| firebase-storage | 이미지 저장을 위한 FirebaseRTDB |
| firebase-messaging | FCM 기능 구현 |
| ar-core | AR 기능 구현을 위한 오픈소스 라이브러리 |
| ar-sceneform | OpenGL을 편리하게 활용하여 3D 모델링 |
| hilt | DI 라이브러리 |
| kakao-sdk | 카카오 로그인, 링크를 위한 라이브러리 |
| databinding | XML에 데이터를 직접 바인딩하기 위한 라이브러리 |
| navigation | 프래그먼트 탐색을 위한 Jetpack 라이브러리 |
| circleIndicator3 | ViewPager의 인디케이터를 위한 라이브러리 |
| retrofit2 | HTTP 통신 라이브러리 |
| Spinkit | Loading Dialog를 위한 라이브러리 |
| okhttp3 | http logging과 interceptor를 위한 라이브러리 |
| balloon | 말풍선 라이브러리 |
| MPAndroidChart | Pie Chart를 위한 라이브러리 |
| circleimageview | 원형 이미지를 위한 라이브러리 |
| glide | URL형식의 이미지처리를 위한 라이브러리 |

### 패키지 구조

```markdown
├── 📂app
│   ├── 📂ui
│   │   ├──📂 ar
│   │   ├── 📂ar_capture_confirm
│   │   ├── 📂ar_gallery
│   │   ├── 📂common
│   │   ├── 📂funding_my
│   │   ├── 📂funding_my_detail
│   │   ├── 📂funding_my_item_detail
│   │   ├── 📂funding_my_list
│   │   ├── 📂funding_participate
│   │   ├── 📂funding_participate_item
│   │   ├── 📂funding_participate_list
│   │   ├── 📂home
│   │   ├── 📂item_detail
│   │   ├── 📂item_list
│   │   ├── 📂like
│   │   ├── 📂login
│   │   ├── 📂mypage
│   │   ├── 📂pay
│   │   ├── 📂pay_result
│   │   ├── 📂point
│   │   ├── 📂search
│   │   ├── 📂splash
│   │   ├── 📂wishlist
│   │   └── MainActivity.kt
│   ├── 📂util
│   └── GlobalApplication.kt
├── 📂domain
│   ├── 📂model
│   │   ├── 📂ar
│   │   ├── 📂auth
│   │   ├── 📂funding
│   │   ├── 📂item
│   │   ├── 📂pay
│   │   ├── 📂search
│   │   ├── 📂user
│   │   └── 📂wishlist
│   ├── 📂repository(interface)
│   │   ├── ArRepository.kt
│   │   ├── AuthRepository.kt
│   │   ├── FcmRepository.kt
│   │   ├── FundingRepository.kt
│   │   ├── ItemRepository.kt
│   │   ├── PayRepository.kt
│   │   ├── SearchRepository.kt
│   │   ├── UserRepository.kt
│   │   └── WishListReposiroty.kt
│   └── 📂usecase
│   │   ├── 📂ar
│   │   ├── 📂auth
│   │   ├── 📂funding
│   │   ├── 📂item
│   │   ├── 📂pay
│   │   ├── 📂search
│   │   ├── 📂user
│   │   └── 📂wishlist
├── 📂data
│   ├── 📂local.prefs
│   │   ├── AuthSharedPreference.kt
│   │   └── SearchKeywordPreference.kt
│   ├── 📂remote
│   │   │   ├── 📂datasource
│   │   │   ├── 📂ar
│   │   │   ├── 📂auth
│   │   │   ├── 📂funding
│   │   │   ├── 📂item
│   │   │   ├── 📂pay
│   │   │   ├── 📂search
│   │   │   ├── 📂user
│   │   │   └── 📂wishlist
│   │   ├── 📂di
│   │   │   ├── DataSourceModule.kt
│   │   │   ├── NetworkModule.kt
│   │   │   ├── RepositoryModule.kt
│   │   │   ├── ServiceModule.kt
│   │   │   └── SharedPreferenceModule.kt
│   │   ├── 📂mappers
│   │   ├── 📂reposiroty(implements)
│   │   └── 📂service
└── 📂common
```

### 아키텍처 구조

![Untitled](README%20ea2748c7cb294066b5a24086cb863585/Untitled%203.png)

## 📟BackEnd

### 사용한 라이브러리

| 이름 | 설명 |
| --- | --- |
| firebase-storage | 이미지 저장을 위한 FirebaseRTDB |
| firebase-messaging | FCM 기능 구현 |
| ar-core | AR 기능 구현을 위한 오픈소스 라이브러리 |
| ar-sceneform | OpenGL을 편리하게 활용하여 3D 모델링 |
| hilt | DI 라이브러리 |
| kakao-sdk | 카카오 로그인, 링크를 위한 라이브러리 |
| databinding | XML에 데이터를 직접 바인딩하기 위한 라이브러리 |
| navigation | 프래그먼트 탐색을 위한 Jetpack 라이브러리 |
| circleIndicator3 | ViewPager의 인디케이터를 위한 라이브러리 |
| retrofit2 | HTTP 통신 라이브러리 |
| Spinkit | Loading Dialog를 위한 라이브러리 |
| okhttp3 | http logging과 interceptor를 위한 라이브러리 |
| balloon | 말풍선 라이브러리 |
| MPAndroidChart | Pie Chart를 위한 라이브러리 |
| circleimageview | 원형 이미지를 위한 라이브러리 |
| glide | URL형식의 이미지처리를 위한 라이브러리 |

### 패키지 구조

```markdown
├── 📂app
│   ├── 📂ui
│   │   ├──📂 ar
│   │   ├── 📂ar_capture_confirm
│   │   ├── 📂ar_gallery
│   │   ├── 📂common
│   │   ├── 📂funding_my
│   │   ├── 📂funding_my_detail
│   │   ├── 📂funding_my_item_detail
│   │   ├── 📂funding_my_list
│   │   ├── 📂funding_participate
│   │   ├── 📂funding_participate_item
│   │   ├── 📂funding_participate_list
│   │   ├── 📂home
│   │   ├── 📂item_detail
│   │   ├── 📂item_list
│   │   ├── 📂like
│   │   ├── 📂login
│   │   ├── 📂mypage
│   │   ├── 📂pay
│   │   ├── 📂pay_result
│   │   ├── 📂point
│   │   ├── 📂search
│   │   ├── 📂splash
│   │   ├── 📂wishlist
│   │   └── MainActivity.kt
│   ├── 📂util
│   └── GlobalApplication.kt
├── 📂domain
│   ├── 📂model
│   │   ├── 📂ar
│   │   ├── 📂auth
│   │   ├── 📂funding
│   │   ├── 📂item
│   │   ├── 📂pay
│   │   ├── 📂search
│   │   ├── 📂user
│   │   └── 📂wishlist
│   ├── 📂repository(interface)
│   │   ├── ArRepository.kt
│   │   ├── AuthRepository.kt
│   │   ├── FcmRepository.kt
│   │   ├── FundingRepository.kt
│   │   ├── ItemRepository.kt
│   │   ├── PayRepository.kt
│   │   ├── SearchRepository.kt
│   │   ├── UserRepository.kt
│   │   └── WishListReposiroty.kt
│   └── 📂usecase
│   │   ├── 📂ar
│   │   ├── 📂auth
│   │   ├── 📂funding
│   │   ├── 📂item
│   │   ├── 📂pay
│   │   ├── 📂search
│   │   ├── 📂user
│   │   └── 📂wishlist
├── 📂data
│   ├── 📂local.prefs
│   │   ├── AuthSharedPreference.kt
│   │   └── SearchKeywordPreference.kt
│   ├── 📂remote
│   │   │   ├── 📂datasource
│   │   │   ├── 📂ar
│   │   │   ├── 📂auth
│   │   │   ├── 📂funding
│   │   │   ├── 📂item
│   │   │   ├── 📂pay
│   │   │   ├── 📂search
│   │   │   ├── 📂user
│   │   │   └── 📂wishlist
│   │   ├── 📂di
│   │   │   ├── DataSourceModule.kt
│   │   │   ├── NetworkModule.kt
│   │   │   ├── RepositoryModule.kt
│   │   │   ├── ServiceModule.kt
│   │   │   └── SharedPreferenceModule.kt
│   │   ├── 📂mappers
│   │   ├── 📂reposiroty(implements)
│   │   └── 📂service
└── 📂common
```

## 🛠저희 팀을 소개할게요

### Frontend(Mobile)

![image.png](https://user-images.githubusercontent.com/72602912/219434368-9e7414e6-a391-4ea3-b754-89801bc2cddd.png)


### Backend(Spring boot)

![image.png](https://user-images.githubusercontent.com/72602912/219435011-6e7e62c5-62aa-4310-9050-a79870ab5e85.png)
