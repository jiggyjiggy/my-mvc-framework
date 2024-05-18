package org.example.mvc;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey); // annotation 형태로도 받을 수 있도록 만들기 위해서 object 로 리턴한다
}