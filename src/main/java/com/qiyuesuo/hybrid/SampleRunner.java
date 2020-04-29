package com.qiyuesuo.hybrid;

import com.qiyuesuo.hybrid.http.HybridClient;
import com.qiyuesuo.hybrid.sample.BaseSample;
import com.qiyuesuo.hybrid.sample.SinglePlatformSignSample;

/**
 * 示例运行
 */
public class SampleRunner {
	public static void main(String[] args) {
		/**
		 * 初始化混合云连接客户端
		 */
		HybridClient client = new HybridClient("替换为混合云服务地址");
//		HybridClient client = new HybridClient("替换为混合云服务地址", "替换为混合云服务部署时设置的requestToken");
		/**
		 * 选择运行的样本示例
		 */
		BaseSample sample = selectSample(client);
		try {
			sample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择运行样本示例
	 * 目前提供三种样本示例：（1）企业单签（2）企业&个人（3）企业&企业
	 */
	private static BaseSample selectSample(HybridClient client) {
		/**
		 * 对接方单签案例
		 * 
		 * @场景：仅对接方自身参数签署，即合同仅需要对接方参与签署，例如收据、公司内部报文等
		 * @流程：通过文件创建合同，对接方参与签署（接口静默、签署页面），完成合同
		 * @可附带的操作：下载合同、存证合同等
		 */
		return new SinglePlatformSignSample(client);
		/**
		 * 对接方以及个人双方签署案例
		 * 
		 * @场景：对接方公司与个人双方参与合同签署，例如：人事合同等
		 * @流程：（1）通过文件创建合同（2）对接方参与签署（接口静默、签署页面）（3）生成个人认证链接，个人完成认证（4）生成个人签署页面链接，个人完成签署（5）完成合同
		 * @可附带的操作：下载合同、存证合同等，另对接方公司与个人的签署顺序可根据自身业务场景调整
		 */
//		return new PlatformAndPersonSignSample(client);
		/**
		 * 对接方以及接收方公司双方签署案例
		 * 
		 * @场景：对接方公司与接收方公司双方参与合同签署，例如：销售合同等
		 * @流程：（1）通过文件创建合同（2）对接方参与签署（接口静默、签署页面）（3）生成公司认证链接，公司完成认证（4）生成公司签署页面链接，公司完成签署（5）完成合同
		 * @可附带的操作：下载合同、存证合同等，另对接方公司与接收方公司的签署顺序可根据自身业务场景调整
		 */
//		return new PlatformAndCompanySignSample(client);
	}
}
