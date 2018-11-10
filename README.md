### 此项目为rsa加解密的dubbo服务

如果用的多的话可以将加解密服务抽取出来单独去提供服务

### 主要提供两个接口，在service-api中有说明：

```java
    /**
     * rsa 解密
     */
    byte[] rsaDecrypt(int cryptoScene, byte[] data);

    /**
     * rsa 加密
     */
    byte[] rsaEncrypt(int cryptoScene, byte[] data);
```

### rsa公钥私钥生成方式：

（1）使用openssl
```
    terminal使用openssl命令：
    输入openssl进入OpenSSL>模式
    #生成私钥,1024是密钥长度
    OpenSSL> genrsa -out rsa_private_key.pem 1024     
    #将私钥转换成PKCS8格式(按实际需要)
    OpenSSL> pkcs8 -topk8 -inform PEM -in rsa_private_key.pem -outform PEM -nocrypt -out rsa_private_key_pkcs8.pem 
    #根据生成的私钥生成公钥
    OpenSSL> rsa -in rsa_private_key.pem -pubout -out rsa_public_key.pem
    #退出
    OpenSSL> exit 
```

（2）可以使用在线的工具来生成

其实都是对上面方式进行的封装，不建议这种方式，毕竟加解密是为了安全考虑的，还是自己去生成吧，很简单两步就可以了
    
在此推荐一个在线网站：http://web.chacuo.net/netrsakeypair