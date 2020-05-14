# 契约锁混合云Java调用示例
[契约锁](http://www.qiyuesuo.com) 成立于2015年，是新一代数字签名服务领域的领军企业。依托雄厚的企业管理软件服务经验，致力为全国的企业及个人用户提供最具可用性、稳定性及前瞻性的电子签署、数据存证及数据交互服务。 契约锁深耕企业级市场，产品线涵盖电子签署、合同管理、公文签发、数据存证等企业内签署场景，并提供本地签、远程签、标准签等多种API调用方式接入企业内部管理系统。目前主要为教育、旅游、互联网金融、政府事业单位、集团企业、B2B电商、地产中介、O2O等企业及个人提供签署、存证服务。 契约锁严格遵守《中华人民共和国电子签名法》，并联合公安部公民网络身份识别系统（eID）、工商相关身份识别系统、权威CA机构、公证处及律师事务所，确保在契约锁上签署的每一份合同真实且具有法律效力。 契约锁平台由上海亘岩网络科技有限公司运营开发，核心团队具有丰富的企业管理软件、金融支付行业、数字证书行业从业经验，致力于通过技术手段让企业合同签署及管理业务更简单、更便捷。

了解更多契约锁详情请访问 [www.qiyuesuo.com](http://www.qiyuesuo.com).

# Requirements

JDK 1.8

契约锁混合云服务

# Usage

该示例主要供对接方在集成契约锁混合云服务时作为参考，提供三种集成方常见签署场景，对接方可根据自身业务场景以及接口文档清单，适当调整部分调用顺序。

### 对接方企业单方签署

**场景：**  仅对接方自身参数签署，即合同仅需要对接方参与签署，例如收据、公司内部报文等。

**详情见：**  [SinglePlatformSignSample](https://github.com/qiyuesuo/hybrid-demo/blob/master/src/main/java/com/qiyuesuo/hybrid/sample/SinglePlatformSignSample.java)

### 对接方企业与公司接收方签署

**场景：**  对接方公司与接收方公司双方参与合同签署，例如：销售合同等。

**详情见：**  [PlatformAndCompanySignSample](https://github.com/qiyuesuo/hybrid-demo/blob/master/src/main/java/com/qiyuesuo/hybrid/sample/PlatformAndCompanySignSample.java)

### 对接方企业与个人接收方签署

**场景：**  对接方公司与个人双方参与合同签署，例如：人事合同等。

**详情见：**  [PlatformAndPersonSignSample](https://github.com/qiyuesuo/hybrid-demo/blob/master/src/main/java/com/qiyuesuo/hybrid/sample/PlatformAndPersonSignSample.java)

# Guide

本示例代码通过[SampleRunner](https://github.com/qiyuesuo/hybrid-demo/blob/master/src/main/java/com/qiyuesuo/hybrid/SampleRunner.java)直接运行以上提及的各种示例，详情请根据类注释修改具体运行场景。

Notes
=======

示例代码中的参数均为测试环境参数，实际运行时需要将相关参数修改为生产环境参数，或沙箱测试环境参数。

扫码关注契约锁公众号,了解契约锁最新动态。

![公众号二维码](https://github.com/qiyuesuo/hybrid-demo/blob/master/qrcode.png)
