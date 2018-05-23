## 基于Aspectjx 实现的Android 计时工具--AOP 思想


### 使用步骤

- 在项目根目录build.gradle中添加：



```
        dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.1'
        ..........

    }
```

- 在app目录中build.gradle中添加：


```
apply plugin: 'android-aspectjx'
```
添加依赖：

```
compile 'com.dabaozi:CountTime_aop:1.0.1'
```

- 使用

如下在方法上添加计时注解：

```
   @CountTime("Hello")
    public void Hello() {

        SystemClock.sleep(1000);
}
```

-实现ICountTime接口，在


```
@Override
    public void GetTimes(String methodName, long times) {
        Log.e("AAA","---"+methodName+"---"+times);
    }
```
GetTime 回调方法中，返回当前方法名，和方法的时间。可以在此种自定义一些事件（比如写到文件中，或者打印到日志中等等）。


- 此工具只要借助了AOP 面向切面编程，解决了OOP在侵入性业务中的不足，不对业务代码产生影响，实现解耦。
- 实现AOP的技术，主要分为两大类：
1. 采用动态代理技术，利用截取消息的方式，对该信息进行装饰，以取代原有对象行为的执行
2. 采用静态织入的方式，引入特定的语句创建“方面”，从而使得编译器可以在编译期间织入有关“方面的代码。此工具采用了第2种方式。