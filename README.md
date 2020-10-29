# opencv
[참고]
 * https://opencv-java-tutorials.readthedocs.io/en/latest/01-installing-opencv-for-java.html
 * https://ldh-6019.tistory.com/48 
 
### 개발환경
- java 14 
- opencv 4.5
- springboot, gradle
- MacOS
 
#### MacOS
##### ant 설치 

```brew install ant```

확인 : /usr/local/bin/ant

##### opencv 파일 수정

```
brew edit opencv
```
```
-DBUILD_opencv_java=OFF 를 -DBUILD_opencv_java=ON
```
##### 설치 - 드릅게 오래걸림
```
brew install --build-from-source opencv
```



#### 인텔리제이
VM option  
-Djava.library.path=/opencv/build/lib 경

```-Djava.library.path=libs```


#### swagger
* http://localhost:8080/swagger-ui.html#/  
#### web
* http://localhost:8080/file/
