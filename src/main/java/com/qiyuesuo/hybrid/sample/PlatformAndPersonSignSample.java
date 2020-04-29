package com.qiyuesuo.hybrid.sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import com.qiyuesuo.hybrid.exception.HybridException;
import com.qiyuesuo.hybrid.http.HybridClient;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.properties.RequestPathProperties;
import com.qiyuesuo.hybrid.properties.ResponseCode;
import com.qiyuesuo.hybrid.sample.bean.SignAppearanceBean;
import com.qiyuesuo.hybrid.sample.bean.SignerBean;
import com.qiyuesuo.hybrid.sample.bean.StamperBean;
import com.qiyuesuo.hybrid.sample.bean.TemplateBean;
import com.qiyuesuo.hybrid.sample.request.ContractCompleteParam;
import com.qiyuesuo.hybrid.sample.request.CreateContractByFileParam;
import com.qiyuesuo.hybrid.sample.request.CreateContractByTemplateParam;
import com.qiyuesuo.hybrid.sample.request.DownloadContractParam;
import com.qiyuesuo.hybrid.sample.request.PersonalPageSignParam;
import com.qiyuesuo.hybrid.sample.request.PlatformInterfaceSignParam;
import com.qiyuesuo.hybrid.sample.request.PlatformPageSignParam;
import com.qiyuesuo.hybrid.sample.request.UserPersonalResultParam;
import com.qiyuesuo.hybrid.sample.request.UserPersonalUrlParam;
import com.qiyuesuo.hybrid.sample.request.ViewContractParam;
import com.qiyuesuo.hybrid.sample.response.AuthUrl;
import com.qiyuesuo.hybrid.sample.response.Contract;
import com.qiyuesuo.hybrid.sample.response.HybridResponse;
import com.qiyuesuo.hybrid.sample.response.PageBean;
import com.qiyuesuo.hybrid.sample.response.UserPerosnalResult;

/**
 * 对接方以及个人双方签署案例
 * 
 * @场景：对接方公司与个人双方参与合同签署，例如：人事合同等
 * @流程：（1）通过文件创建合同（2）对接方参与签署（接口静默、签署页面）（3）生成个人认证链接，个人完成认证（4）生成个人签署页面链接，个人完成签署（5）完成合同
 * @可附带的操作：下载合同、存证合同等，另对接方公司与个人的签署顺序可根据自身业务场景调整
 * 子方法说明：
 * 1、通过文件创建合同：
 * （1）通过文件直接创建合同（Pdf，Html，Word）:{@link PlatformAndPersonSignSample#createByFile()}}
 * （2）通过文件作为模板创建合同（Html，Word）:{@link PlatformAndPersonSignSample#createByTemplate()}}
 * 2、对接方签署(根据自身业务场景选择签署方式)：
 * （1）对接方接口静默签署：{@link PlatformAndPersonSignSample#platfromInterfaceSign(Long)}
 * （2）生成对接方签署页面链接：{@link PlatformAndPersonSignSample#platformPageSign(Long)}
 * 3、个人认证以及签署：
 * （1）生成个人认证链接：{@link PlatformAndPersonSignSample#personAuthUrl(String, String)}
 * （2）查询个人认证状态：{@link PlatformAndPersonSignSample#personAuthStatus(String)}
 * （3）生成个人签署页面链接：{@link PlatformAndPersonSignSample#personPageSign(Long, String)}
 * 4、完成合同：{@link PlatformAndPersonSignSample#completeContract(Long)}
 * 5、生成合同预览链接：{@link PlatformAndPersonSignSample#viewContract(Long)}
 * 6、下载合同文件：{@link PlatformAndPersonSignSample#downlodContract(Long)}
 */
public class PlatformAndPersonSignSample implements BaseSample {

	private HybridClient hybridClient;

	public PlatformAndPersonSignSample(HybridClient client) {
		this.hybridClient = client;
	}

	@Override
	public void run() throws Exception {
		/*
		 * 通过文件/HTML/模板创建合同，创建成功后获取到文档ID（“documentid”）,用于后续接口调用操作
		 * 此处默认使用通过文件创建，若需要通过模板创建见‘通过文件作为模板创建合同’对应的子方法
		 */
		Long documentId = null;
		documentId = this.createByFile();
		/*
		 * 对接方签署，可选择通过接口静默签署或者通过生成签署页面进行签署
		 * 此处默认使用平台方接口静默签署，若需要通过生成签署页面在页面上签署见‘生成对接方签署页面链接’对应的子方法
		 */
		this.platfromInterfaceSign(documentId);
		/*
		 * 个人签署
		 * （1）生成个人认证页面，获取认证凭证，完成并通过个人认证
		 * （2）通过认证凭证生成个人签署页面，开始签署
		 * 注：对应个人认证完成后，对接方可持久化认证凭证，后续同一个人再次进行签署时，可跳过认证步骤，直接生成个人签署页面
		 */
		String authId = this.personAuthUrl("张三", "11021044055");
		if (this.personAuthStatus(authId).equals(1)) {
			String personSignUrl = this.personPageSign(documentId, authId);
		} else {
			throw new HybridException("用户还未通过认证");
		}
		/*
		 * 待所有签署方签署完成后，完成合同
		 */
		this.completeContract(documentId);
		/*
		 * 额外可选操作
		 * （1）生成合同预览链接
		 * （2）下载合同文件
		 */
		this.viewContract(documentId);
		this.downlodContract(documentId);

	}

	/**
	 * 根据文件创建合同
	 */
	private Long createByFile() throws IOException {
		// 读取文件，设置请求参数
		ByteBuffer fileBuffer = ByteBuffer.wrap(Files.readAllBytes(Paths.get("C:\\Users\\Richard Cheung\\Documents\\契约锁\\测试\\IINT.pdf")));
		CreateContractByFileParam createByFileParam = new CreateContractByFileParam();
		createByFileParam.setFileBuffer(fileBuffer);
		createByFileParam.setTitle("示例测试文件");
		createByFileParam.setFileType("PDF");
		// 请求远程服务器
		String contractResponse = hybridClient.doService(RequestPathProperties.CREATE_CONTRACT_BYFILE.getPath(),
				createByFileParam.getHttpParameter());
		// 解析返回结果，并获取documentid
		HybridResponse<Contract> responseObject = JSONUtils.toHybridResponse(contractResponse, Contract.class);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("创建合同失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		Contract contract = responseObject.getResult();
		System.out.println("创建合同成功，文档ID：" + contract.getDocumentId());
		return contract.getDocumentId();
	}

	/**
	 * 根据模板创建合同
	 */
	private Long createByTemplate() throws IOException {
		// 读取文件，设置请求参数
		ByteBuffer fileBuffer = ByteBuffer.wrap(Files.readAllBytes(Paths.get("C:\\Users\\Richard Cheung\\Documents\\契约锁\\测试\\IINT.pdf")));
		CreateContractByTemplateParam createByTemplateParam = new CreateContractByTemplateParam();
		createByTemplateParam.setFileBuffer(fileBuffer);
		createByTemplateParam.setFileType("WORD");
		createByTemplateParam.addParam(new TemplateBean("甲方", "契约锁有限公司")); // 设置参数内容
		// 请求远程服务器
		String contractResponse = hybridClient.doService(RequestPathProperties.CREATE_CONTRACT_BYTEMPLATE.getPath(),
				createByTemplateParam.getHttpParameter());
		// 解析返回结果，并获取documentid
		HybridResponse<Contract> responseObject = JSONUtils.toHybridResponse(contractResponse, Contract.class);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("创建合同失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		Contract contract = responseObject.getResult();
		System.out.println("创建合同成功，文档ID：" + contract.getDocumentId());
		return contract.getDocumentId();
	}

	/**
	 * 对接方接口静默签署
	 * 此处制定了印章ID以及签署位置
	 * 对接方可根据自身情况，根据接口清单文档，调整接入参数
	 */
	private void platfromInterfaceSign(Long documentId) {
		// 设置请求参数，设置签署位置
		PlatformInterfaceSignParam signParam = new PlatformInterfaceSignParam();
		signParam.setDocumentId(documentId);
		signParam.setAppearance(new SignAppearanceBean(2590741398286242154L));
		StamperBean stamper = new StamperBean();
		stamper.setType("SEAL");
		stamper.setPage(1);
		stamper.setOffsetX(0.3D);
		stamper.setOffsetY(0.4D);
		signParam.addStamper(stamper);
		// 请求远程服务器
		String signResponse = hybridClient.doService(RequestPathProperties.PLATFORM_INTERFACE_SIGN.getPath(), signParam.getHttpParameter());
		// 解析返回结果，并获取documentid
		HybridResponse responseObject = JSONUtils.toHybridResponse(signResponse);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("创建合同失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		System.out.println("合同签署成功，文档ID：" + documentId);
	}

	/**
	 * 对接方页面签署，生成签署页面链接
	 * 此处制定了印章ID以及签署位置
	 * 对接方可根据自身情况，根据接口清单文档，调整接入参数
	 */
	private String platformPageSign(Long documentId) {
		// 设置请求参数，设置签署位置
		PlatformPageSignParam signParam = new PlatformPageSignParam();
		signParam.setDocumentId(documentId);
		signParam.setAppearance(new SignAppearanceBean(2590741398286242154L));
		StamperBean stamper = new StamperBean();
		stamper.setType("SEAL");
		stamper.setPage(1);
		stamper.setOffsetX(0.3D);
		stamper.setOffsetY(0.4D);
		signParam.addStamper(stamper);
		// 请求远程服务器
		String signResponse = hybridClient.doService(RequestPathProperties.PLATFORM_PAGE_SIGN.getPath(), signParam.getHttpParameter());
		// 解析返回结果，并获取documentid
		HybridResponse<PageBean> responseObject = JSONUtils.toHybridResponse(signResponse, PageBean.class);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("创建合同失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		PageBean bean = responseObject.getResult();
		System.out.println("生成合同签署连接：" + bean.getUrl());
		return bean.getUrl();
	}

	/**
	 * 完成合同，若混合云开启自动存证服务，则会在完成合同后自动存证
	 */
	private void completeContract(Long documentId) {
		ContractCompleteParam completeParam = new ContractCompleteParam(documentId);
		// 请求远程服务器
		String signResponse = hybridClient.doService(RequestPathProperties.COMPLETE_CONTRACT.getPath(), completeParam.getHttpParameter());
		// 解析返回结果，并获取documentid
		HybridResponse responseObject = JSONUtils.toHybridResponse(signResponse);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("完成合同失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		System.out.println("完成合同成功，文档ID：" + documentId);
	}

	/**
	 * 生成个人认证链接
	 * 此处仅指定了认证人姓名以及认证人手机号
	 * 对接方可根据自身情况，根据接口清单文档，调整接入参数
	 */
	private String personAuthUrl(String userName, String contact) {
		UserPersonalUrlParam urlParam = new UserPersonalUrlParam();
		urlParam.setUsername(userName);
		urlParam.setContact(contact);
		String pageResponse = hybridClient.doService(RequestPathProperties.PERSONAL_AUTH_PAGE.getPath(), urlParam.getHttpParameter());
		HybridResponse<AuthUrl> responseObject = JSONUtils.toHybridResponse(pageResponse, AuthUrl.class);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("生成个人认证链接失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		AuthUrl url = responseObject.getResult();
		System.out.println("生成个人认证链接成功，认证链接：" + url.getUrl() + "，authId：" + url.getAuthId());
		return url.getAuthId();
	}

	/**
	 * 个人认证结果查询
	 */
	private Integer personAuthStatus(String authId) {
		UserPersonalResultParam resultParam = new UserPersonalResultParam();
		resultParam.setAuthId(authId);
		String resultResponse = hybridClient.doService(RequestPathProperties.PERSONAL_AUTH_STATUS.getPath(), resultParam.getHttpParameter());
		HybridResponse<UserPerosnalResult> responseObject = JSONUtils.toHybridResponse(resultResponse, UserPerosnalResult.class);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("查询个人认证结果失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		UserPerosnalResult result = responseObject.getResult();
		System.out.println("查询个人认证结果成功，认证结果：" + resultResponse);
		return result.getStatus();
	}

	/**
	 * 生成个人签署页面
	 * 此处未指定签署位置以及签署外观，且设置校验方式为验证码校验
	 * 对接方可根据自身情况，根据接口清单文档，调整接入参数
	 */
	private String personPageSign(Long documentId, String authId) {
		PersonalPageSignParam pageSignParam = new PersonalPageSignParam();
		pageSignParam.setDocumentId(documentId);
		pageSignParam.setSigner(new SignerBean(authId, "PERSONAL"));
		pageSignParam.setSignValidateMethods(Arrays.asList("PIN"));
		String pageResponse = hybridClient.doService(RequestPathProperties.SIGN_PAGE.getPath(), pageSignParam.getHttpParameter());
		HybridResponse<PageBean> responseObject = JSONUtils.toHybridResponse(pageResponse, PageBean.class);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("生成个人认证链接失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		PageBean url = responseObject.getResult();
		System.out.println("生成个人签署链接成功，签署链接：" + url.getUrl());
		return url.getUrl();
	}

	/**
	 * 生成预览合同链接
	 */
	private String viewContract(Long documentId) {
		ViewContractParam viewParam = new ViewContractParam(documentId);
		// 请求远程服务器
		String pageResponse = hybridClient.doService(RequestPathProperties.CONTRACT_VIEW_PAGE.getPath(), viewParam.getHttpParameter());
		// 解析返回结果，并获取documentid
		HybridResponse<PageBean> responseObject = JSONUtils.toHybridResponse(pageResponse, PageBean.class);
		if (!responseObject.getCode().equals(ResponseCode.SUCCESS.getCode())) {
			throw new HybridException("生成合同预览链接失败，错误码：" + responseObject.getCode() + "，错误原因：" + responseObject.getMessage());
		}
		PageBean bean = responseObject.getResult();
		System.out.println("生成合同预览链接成功，预览链接：" + bean.getUrl());
		return bean.getUrl();
	}

	/**
	 * 下载合同文件
	 */
	private void downlodContract(Long documentId) {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			DownloadContractParam downloadParam = new DownloadContractParam(documentId);
			// 请求远程服务器，获取文件流
			hybridClient.doDownload(RequestPathProperties.DOWNLOAD_CONTRACT.getPath(), downloadParam.getHttpParameter(), outputStream);
			// 文件流写入本地
			Files.write(Paths.get("C:\\Users\\Richard Cheung\\Documents\\契约锁\\测试\\下载.pdf"), outputStream.toByteArray(),
					StandardOpenOption.CREATE_NEW);
			System.out.println("下载合同文件成功，documentId:"+documentId);
		} catch (Exception e) {
			throw new HybridException("下载合同失败，失败原因："+e.getMessage());
		}
	}

}
