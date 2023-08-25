
package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Account;

@Aspect
@Component
public class aop {

	@Autowired
	Account account;

	@Around("execution(* com.example.demo.controller.NoteController.*(..))")
	public Object checkLogin(ProceedingJoinPoint jp) throws Throwable {
		if (account == null || account.getName() == null || account.getName().length() == 0) {
			System.err.println("ログインしていません");
			return "redirect:/login?error=notLoggedIn";
		}
		return jp.proceed();
	}

}
