#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
##### Step 1. WebListener
* 톰캣 서버가 실행이 되면 바로 ServletConetextListener 인터페이스의 contextInitialized 메소드를 호출하여 동작한다.
  * DispatcherServlet 보다 먼저 동작하므로 해당 contextInitialized를 이용해 ServletContext에 객체를 생성해 놓고 DispatcherServlet에서 사용할 수 있다.
* contextInitialized에서 데이터 베이스를 초기화 한다.
##### Step 2. WebServlet
* 그다음 HttpServlet 구현체인 DispatcherServlet이 실행이 된다.
* init() 메소드를 통해 RequestMapping 클래스를 생성하고 url과 Controller를 매핑하여 초기화 해놓은다.
 


#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 브라우저에서 http://localhost:8080 엔터
* 브라우저는 요청헤더(패킷)을 만들어 송신
* 톰캣에서 해당 요청을 받고 dispatcherServlet에 위임
* DispatcherServlet은 요청라인의 url을 보고 적절한 Controller를 가져와 해당 요청을 위임
* Controller는 요청을 처리하여 적절한 View 또는 Model을 리턴한다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.

컨트롤러는 싱글톤 패턴으로 하나의 객체가 다양한 요청을 처리하게 된다.
싱글톤 패턴은 일반적으로 상태를 가지지 않은것을 사용해야한다.
왜냐하면 객체는 지역변수는 메모리 스택에 할당을 새로 해주지만, 전역변수와 메소드는 힙영역을 공유하기때문에
상태값이 전역적으로 관리된다면 모든 쓰레드가 상태를 변경을 할 수 가 있다.


