import org.example.mvc.annotation.Controller;
import org.example.mvc.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class));

        logger.debug("beans: [{}]", beans);

        assertThat(beans).isNotEmpty();
        assertThat(beans).allMatch(bean -> bean.isAnnotationPresent(Controller.class));

    }

    /**
     * 클래스 내부 정보 확인하기
     */
    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        List<String> declaredFields = Arrays.stream(clazz.getDeclaredFields())
                .map(f -> f.getName())
                .collect(Collectors.toList());
        List<String> declaredConstructors = Arrays.stream(clazz.getDeclaredConstructors())
                .map(c -> c.getName())
                .collect(Collectors.toList());
        List<String> declaredMethods = Arrays.stream(clazz.getDeclaredMethods())
                .map(m -> m.getName())
                .collect(Collectors.toList());

        assertThat(declaredFields).isNotEmpty();
        assertThat(declaredConstructors).isNotEmpty();
        assertThat(declaredMethods).isNotEmpty();

        logger.debug("User all declared fields: [{}]", declaredFields);
        logger.debug("User all declared constructors: [{}]", declaredConstructors);
        logger.debug("User all declared methods: [{}]", declaredMethods);
    }

    /**
     * Class 를 load 방법은 3가지가 존재한다.
     */
    @Test
    void load() throws ClassNotFoundException {
        // 1
        Class<User> clazz = User.class;

        // 2
        User user = new User("asdf", "김아무개");
        Class<? extends User> clazz2 = user.getClass();

        // 3
        Class<?> clazz3 = Class.forName("org.example.mvc.model.User");

        logger.debug("clazz: [{}]", clazz);
        logger.debug("clazz2: [{}]", clazz2);
        logger.debug("clazz3: [{}]", clazz3);

        assertThat(clazz).isSameAs(clazz2);
        assertThat(clazz2).isSameAs(clazz3);
        assertThat(clazz3).isSameAs(clazz);
    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example"); //  해당 패키지 아래의 클래스들에 대해 리플렉션 사용

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}