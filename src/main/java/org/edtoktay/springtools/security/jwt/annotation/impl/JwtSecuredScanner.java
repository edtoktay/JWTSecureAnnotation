/**
 * 
 */
package org.edtoktay.springtools.security.jwt.annotation.impl;

import java.lang.reflect.Method;

import org.edtoktay.springtools.security.jwt.annotation.JwtSecured;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author deniz.toktay
 *
 */
@Configuration
public class JwtSecuredScanner implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ScanSingleton scanSingleton = ScanSingleton.getInstance();
		for (String beanName : arg0.getBeanDefinitionNames()) {
			Object obj = arg0.getBean(beanName);
			Class<?> clazz = obj.getClass();
			if (AopUtils.isAopProxy(obj)) {
				clazz = AopUtils.getTargetClass(obj);
			}
			if (clazz.isAnnotationPresent(RestController.class))
				for (Method m : clazz.getDeclaredMethods()) {
					if (m.isAnnotationPresent(RequestMapping.class)) {
						if (m.isAnnotationPresent(JwtSecured.class)) {
							JwtSecuredClasses jsc = new JwtSecuredClasses(
									m.getAnnotation(RequestMapping.class).value()[0],
									m.getAnnotation(JwtSecured.class).roles());
							scanSingleton.addPrivateObj(jsc);
						} else {
							scanSingleton.addPublicPath(m.getAnnotation(RequestMapping.class).value()[0]);
						}
					}
				}
		}
	}

}
