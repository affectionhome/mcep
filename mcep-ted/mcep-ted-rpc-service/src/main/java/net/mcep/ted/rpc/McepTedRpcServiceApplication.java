package net.mcep.ted.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 */
public class McepTedRpcServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(McepTedRpcServiceApplication.class);

	public static void main(String[] args) {
		LOGGER.info(">>>>> 检测评估平台 mcep-ted-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		LOGGER.info(">>>>> 检测评估平台 mcep-ted-rpc-service 启动完成 <<<<<");
	}

}
