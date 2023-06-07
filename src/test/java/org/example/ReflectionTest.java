package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.DisplayName;
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

/**
* @Controller 어노테이션 설정돼 있는 모든 클래스를 찾아서 출력
*/
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @DisplayName("패키지 내 모든 어노테이션 조회")
    @Test
    void controllerScan() {

        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}] ", beans);

    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {

        // 해당 패키지 내에 있는 클래스에서
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        // 리스트에 등록되어 있는 어노테이션 모두 조회 후 HashSet에 add
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }

    @DisplayName("클래스 객체 내 필드, 생성자, 메소드 조회")
    @Test
    void showClass() {

        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));

    }

    @DisplayName("힙 영역에 로드되어 있는 클래스 객체 조회")
    @Test
    void laod() throws ClassNotFoundException {

        // 1
        Class<User> clazz = User.class;

        // 2
        User user = new User("jyh", "양재");
        Class<? extends User> clazz2 = user.getClass();

        // 3
        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz: [{}]", clazz);
        logger.debug("clazz2: [{}]", clazz2);
        logger.debug("clazz3: [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();

    }
}
