package com.qiyuesuo.hybrid.sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.qiyuesuo.hybrid.exception.HybridException;
import com.qiyuesuo.hybrid.http.HybridClient;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.properties.RequestPathProperties;
import com.qiyuesuo.hybrid.properties.ResponseCode;
import com.qiyuesuo.hybrid.sample.bean.SignAppearanceBean;
import com.qiyuesuo.hybrid.sample.bean.StamperBean;
import com.qiyuesuo.hybrid.sample.bean.TemplateBean;
import com.qiyuesuo.hybrid.sample.request.ContractCompleteParam;
import com.qiyuesuo.hybrid.sample.request.CreateContractByFileParam;
import com.qiyuesuo.hybrid.sample.request.CreateContractByTemplateParam;
import com.qiyuesuo.hybrid.sample.request.DownloadContractParam;
import com.qiyuesuo.hybrid.sample.request.PlatformInterfaceSignParam;
import com.qiyuesuo.hybrid.sample.request.PlatformPageSignParam;
import com.qiyuesuo.hybrid.sample.request.ViewContractParam;
import com.qiyuesuo.hybrid.sample.response.Contract;
import com.qiyuesuo.hybrid.sample.response.HybridResponse;
import com.qiyuesuo.hybrid.sample.response.PageBean;

/**
 * 对接方单签案例
 * 
 * @场景：仅对接方自身参数签署，即合同仅需要对接方参与签署，例如收据、公司内部报文等
 * @流程：通过文件创建合同，对接方参与签署（接口静默、签署页面），完成合同
 * @可附带的操作：下载合同、存证合同等
 * 子方法说明：
 * 1、通过文件创建合同：
 * （1）通过文件直接创建合同（Pdf，Html，Word）:{@link PlatformAndPersonSignSample#createByFile()}}
 * （2）通过文件作为模板创建合同（Html，Word）:{@link PlatformAndPersonSignSample#createByTemplate()}}
 * 2、对接方签署(根据自身业务场景选择签署方式)：
 * （1）对接方接口静默签署：{@link PlatformAndPersonSignSample#platfromInterfaceSign(Long)}
 * （2）生成对接方签署页面链接：{@link PlatformAndPersonSignSample#platformPageSign(Long)}
 * 3、完成合同：{@link PlatformAndPersonSignSample#completeContract(Long)}
 * 4、生成合同预览链接：{@link PlatformAndPersonSignSample#viewContract(Long)}
 * 5、下载合同文件：{@link PlatformAndPersonSignSample#downlodContract(Long)}
 */
public class SinglePlatformSignSample implements BaseSample {

	private HybridClient hybridClient;

	public SinglePlatformSignSample(HybridClient client) {
		this.hybridClient = client;
	}

	@Override
	public void run() throws IOException {
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
		 * 完成合同
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
			Files.write(Paths.get("C:\\Users\\Richard Cheung\\Documents\\契约锁\\测试\\下载.pdf"), outputStream.toByteArray(), StandardOpenOption.CREATE_NEW);
			System.out.println("下载合同文件");
		} catch (Exception e) {
			throw new HybridException("下载合同失败，失败原因："+e.getMessage());
		}
	}
}
