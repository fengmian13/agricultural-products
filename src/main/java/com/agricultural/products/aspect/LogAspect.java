package com.agricultural.products.aspect;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.agricultural.products.annotation.Log;
import com.agricultural.products.entity.OperationLog;
import com.agricultural.products.entity.User;
import com.agricultural.products.service.IOperationLogService;
import com.agricultural.products.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * 日志切面类
 */
@Slf4j// 使用Lombok库提供的注解，自动为类添加SLF4J的Logger对象
@Aspect// 声明该类为一个切面类
@Component// 声明该类为一个Spring组件，这样Spring容器会管理这个类的实例
public class LogAspect {

    @Resource// 使用Spring的注解，自动注入IOperationLogService的实例
    private IOperationLogService operationLogService;

    /**
     * 定义一个切点，匹配所有带有@Log注解的方法
     */
    @Pointcut("@annotation(com.agricultural.products.annotation.Log)")
    public void log() {
    }

    /**
     * 环绕通知，在目标方法执行前后执行
     *
     * @param point 提供了对通知应用的上下文的信息
     * @return 目标方法执行后的返回值
     * @throws Throwable 抛出任何目标方法抛出的异常
     */
    @Around("log()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();// 执行目标方法，并获取其返回值

        try {
            // 实例化一个操作日志对象
            OperationLog operationLog = new OperationLog();

            // 获取当前正在执行的方法的签名
            MethodSignature signature = (MethodSignature) point.getSignature();
            // 从方法签名中获取@Log注解
            Log annotation = AnnotationUtils.findAnnotation(signature.getMethod(), Log.class);
            if (Objects.isNull(annotation)) {
                return result;// 如果没有Log注解，则直接返回结果
            }
            // 获取注解中定义的method和resource属性
            String method = annotation.method();
            String resource = annotation.resource();

            // 如果method为"保存"，则根据参数中的id判断是新增还是更新
            Object[] args = point.getArgs();
            if ("保存".equals(method)) {
                Object paramId = ReflectUtil.getFieldValue(args[0], "id");
                method = paramId == null ? "新增" : "更新";
            }
            // 格式化操作内容，包括method、resource和请求数据
            String content = CharSequenceUtil.format("{}{}, 请求数据: {}", method, resource, JSONUtil.toJsonStr(args));

            // 插入操作日志 // 如果操作内容不为空
            if (CharSequenceUtil.isNotBlank(content)) {
                // 获取当前用户（假设TokenUtils.getCurrentUser()能够返回当前用户）
                User currentUser = TokenUtils.getCurrentUser();
                if (currentUser != null) {
                    // 设置操作日志的创建用户
                    operationLog.setCreateUser(currentUser.getUsername());
                }
                // 设置操作日志的创建时间
                operationLog.setCreateTime(new Date());
                // 设置操作日志的方法
                operationLog.setMethod(method);
                // 设置操作日志的内容
                operationLog.setContent(content);
                // 保存或更新操作日志
                operationLogService.saveOrUpdate(operationLog);
            }
        } catch (Exception e) {
            // 如果记录操作日志失败，记录错误信息
            log.error("操作日志记录失败：", e);
        }

        // 返回目标方法的返回值
        return result;
    }

}
