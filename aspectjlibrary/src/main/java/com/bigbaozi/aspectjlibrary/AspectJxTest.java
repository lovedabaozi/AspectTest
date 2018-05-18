package com.bigbaozi.aspectjlibrary;

import android.util.Log;

import com.bigbaozi.aspectjlibrary.annotation.CountTime;

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
    public  static final String  COUNT_TIME="execution(@com.bigbaozi.aspectjlibrary.annotation.CountTime * *(..))";

    /**
     *  after 使用  在方法后使用， execution
     * @param joinPoint
     */
   /*@After("execution (*android.support.v7.app.AppCompatActivity.onCreate(..))")
    public  void  OnActivityBefore(JoinPoint joinPoint){

        String s = joinPoint.getSignature().toString();
        Log.e("AAA","OnActivityBefore="+s);
    }*/


    /**
     *
     ()	表示方法没有任何参数
     (..)	表示匹配接受任意个参数的方法
     (..,java.lang.String)	表示匹配接受java.lang.String类型的参数结束，且其前边可以接受有任意个参数的方法
     (java.lang.String,..)	表示匹配接受java.lang.String类型的参数开始，且其后边可以接受任意个参数的方法
     (*,java.lang.String)	表示匹配接受java.lang.String类型的参数结束，且其前边接受有一个任意类型参数的方法
     *
     *
     */


 /*@After("execution(* android.app.Activity.on**(..))")
    public void onResumeMethod(JoinPoint joinPoint) throws Throwable {
        Log.e("AAA", "aspect:::" + joinPoint.getSignature());
    }

   @After("execution (* com.bigbaozi.aspecttest.MainActivity.Hello())")
   public  void  OnHello(JoinPoint joinPoint){
       String s = joinPoint.getSignature().toString();
        long timeMillis = System.currentTimeMillis();
        Log.e("AAA","After="+s+timeMillis);
   }

  @Before("execution (* com.bigbaozi.aspecttest.MainActivity.Hello())")
    public  void  Hello(JoinPoint joinPoint){
        String s = joinPoint.getSignature().toString();

        long Before = System.currentTimeMillis();
        Log.e("AAA","Before="+s+Before);
    }

   @Around("execution(* com.bigbaozi.aspecttest.MainActivity.Around())")
    public  void  AroudMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String s = proceedingJoinPoint.getSignature().toString();
        Log.e("AAA","before"+s);
        proceedingJoinPoint.proceed();
        Log.e("AAA","after"+s);
    }
*/
   // @Pointcut(COUNT_TIME + " && @annotation()")



    @Pointcut(COUNT_TIME)
    public void DebugCountTime() {

    }

    @Around("DebugCountTime()")
    public void onDebugCountTimeMethodBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = joinPoint.getThis();
        if(object==null)return;
        Class<?> aClass = object.getClass();
        Method[] methods = aClass.getMethods();
        if(methods==null||methods.length==0)return;
        for (Method method :methods){
            boolean annotationPresent = method.isAnnotationPresent(CountTime.class);
            if(annotationPresent){
                System.nanoTime();
                CountTime annotation = method.getAnnotation(CountTime.class);
                String[] value = annotation.value();
                String methodName = value[0];
                if(joinPoint.getSignature().toString().endsWith(methodName+"()")){
                    long timeMillis = System.currentTimeMillis();
                    joinPoint.proceed();
                    long timeMillis2 = System.currentTimeMillis();
                    Log.e("AAA",  "方法"+methodName+"的耗时间为："+(timeMillis2-timeMillis)+"ms" );
                }

            }
        }

    }



}
