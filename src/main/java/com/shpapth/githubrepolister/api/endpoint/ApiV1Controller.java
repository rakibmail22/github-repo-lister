package com.shpapth.githubrepolister.api.endpoint;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
@RestController
@RequestMapping(value = "/api/v1")
public @interface ApiV1Controller {

	@AliasFor(annotation = RestController.class)
	String value() default "";
}
