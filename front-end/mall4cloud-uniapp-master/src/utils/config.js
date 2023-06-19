// 配置文件

// var domain = 'https://h5.gz-yami.com/api' // 统一接口域名，正式环境
// var wsDomain = 'wss://h5.gz-yami.com/api' // 统一接口域名，正式环境

// 微信公众号的appId;  小程序appId配置在 manifest.json ->  mp-weixin -> appid
var mpAppId = 'wx42bbe857570c5b40'

// 本地运行环境 ->  地址修改为本机IP; 端口与 manifest.json 中 h5 -> devServer -> port 一致;
// 运行后浏览器使用 "http://本机IP+端口" 访问
var domain = 'http://localhost:8000' // 统一接口域名，测试环境
var wsDomain = 'ws://localhost:8000' // 统一接口域名，测试环境

var picDomain = 'http://img-test.gz-yami.com/' // 图片域名

// 访问文件存储资源的url 对应阿里云的Bucket域名
// var resourcesUrl = 'http://localhost:9000/anyard_of_jiangnan'
var resourcesUrl = 'http://10.62.0.88:9000/anyard_of_jiangnan'

// 文件上传类型 0.阿里云 1.minIo
var resourcesActionType = '1'

exports.domain = domain
exports.wsDomain = wsDomain
exports.picDomain = picDomain
exports.mpAppId = mpAppId
exports.resourcesUrl = resourcesUrl
exports.resourcesActionType = resourcesActionType

