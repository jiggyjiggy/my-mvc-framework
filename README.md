# MVC framework from scratch (annotation 기반)
spring web 의 핵심은, spring mvc 프로젝트라고 말할 수 있다. <br>
spring이 제공하는 유연한 프레임워크 구조를 제작해보자 <br>

## spring mvc 전체적인 구조 및 흐름
<img width="1014" alt="스크린샷 2024-05-17 오후 5 07 36" src="https://github.com/user-attachments/assets/1e3c915b-bc9f-4c3f-870d-fc0429642726">

## 주요 부분
- DispatcherServlet
- AnnotationHandlerMapping (== HandlerMapping)
- HandlerAdapter
- ViewResolver

### 프론트 컨트롤러 패턴을 이용한 Dispatcher 구현
<img width="596" alt="스크린샷 2024-05-17 오후 5 16 12" src="https://github.com/user-attachments/assets/d8a23462-cacb-46ef-b7cd-7176bb105ee5"> <br>
- 서블릿 기반
- RequestMappingHandlerMapping
    - 핸들러(controller)를 매핑시키는 구체클래스
- controller (== handler)
    - interface를 통한 다형성 적용
- request 해석 가능

### 인코딩 처리
- 서블릿 filter를 이용하여 처리
  
### ViewResolver
- forward, redirect 해석 가능

### HandlerAdapter, modelAndView 구현
- HandlerAdapter를 통해 유연한 동작 가능
    - interface를 통한 다형성 적용

### annotation 기반으로, 결합도 줄이기 
- 리플렉션 라이브러리를 이용하여 구현

## 핵심 코드 (많은 부분 생략)

```java 
RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

Object handler = handlerMappings.stream()
        .filter(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
        .map(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)))
        .findFirst()
        .orElseThrow(() -> new ServletException("No handler found for [" + requestMethod + ", " + requestURI + "]"));
log.info("[DispatcherServlet] handler found: {}", handler.getClass().getName());

HandlerAdapter handlerAdapter = handlerAdapters.stream()
        .filter(ha -> ha.supports(handler))
        .findFirst()
        .orElseThrow(() -> new ServletException("No Adapter for handler [" + handler + "]"));
ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

for (ViewResolver viewResolver : viewResolvers) {
    View view = viewResolver.resolveViewName(modelAndView.getViewName());
    view.render(modelAndView.getModel(), request, response);
}
```

---

# 부록
## 트러블 슈팅. tomcat
내용 정리: https://jiggyjiggy.tistory.com/74
- 보통 9 이전 버전을 많이 쓰는데,
- 이번 프로젝트에서 tomcat 11버전을 적용하면서 생긴 이슈를 tomcat 소스코드 수준에서의 분석 및 해결
  
## Reflection API
- 힙 영역에 로드되어 있는, 클래스 타입의 개체를 통해 필드, 메서드, 생성자를 접근제어자와 상관없이 사용할 수 있도록 지원하는 API
- 컴파일타임이 아닌 런타임에 동적으로 특정 클래스의 정보를 추출가능하다
