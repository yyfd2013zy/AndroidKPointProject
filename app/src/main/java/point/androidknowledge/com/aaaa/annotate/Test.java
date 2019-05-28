package point.androidknowledge.com.aaaa.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 定义一个注解的方式
 */
@Target(ElementType.METHOD)
public @interface Test {
}
