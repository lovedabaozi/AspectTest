package com.bigbaozi.aspectjlibrary;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bigbaozi.aspectjlibrary.annotation.CountTime;
import com.bigbaozi.aspectjlibrary.interf.ICountTime;
import com.bigbaozi.aspectjlibrary.utils.NoDoubleClickUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * Name: AspectJxTest
 * Author: zhangfenglin
 * Email: zhfenglin@163.com
 * Comment: //TODO
 * Date: 2018-05-17 09:10
 */

@Aspect
public class AspectJxTest {
    public static final String COUNT_TIME = "execution(@com.bigbaozi.aspectjlibrary.annotation.CountTime * *(..))";
    /**  点击事件重复防止 */
    public  static  final String SINGLE_CLICK="execution(* android.view.View.OnClickListener.onClick(..))";



    @Pointcut(COUNT_TIME)
    public void DebugCountTime() {

    }


   @Pointcut(SINGLE_CLICK)
    public void  SingleClick(){

    }

    @Around("SingleClick()")
    public  void onSingleClick(ProceedingJoinPoint joinPoint) throws Throwable {

        if(!NoDoubleClickUtils.isDoubleClick()) joinPoint.proceed();


    }

    @Around("DebugCountTime()")
    public void onDebugCountTimeMethodBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = joinPoint.getThis();
        if (object == null) return;
        Class<?> aClass = object.getClass();
        Method[] methods = aClass.getMethods();
        if (methods == null || methods.length == 0) return;
        for (Method method : methods) {
            boolean annotationPresent = method.isAnnotationPresent(CountTime.class);
            if (annotationPresent) {
                CountTime annotation = method.getAnnotation(CountTime.class);
                String[] value = annotation.value();
                String methodName = value[0];
                if (joinPoint.getSignature().toString().endsWith(methodName + "()")) {
                    long timeMillis = System.currentTimeMillis();
                    joinPoint.proceed();
                    long timeMillis2 = System.currentTimeMillis();
                    ICountTime CTLister= (ICountTime) object;

                    Log.e("AAA", "方法" + methodName + "的耗时间为：" + (timeMillis2 - timeMillis) + "ms");
                    CTLister.GetTimes(methodName,(timeMillis2 - timeMillis));
                }

            }
        }

    }


}
