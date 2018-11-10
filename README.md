### 此项目为rsa加解密的dubbo服务

如果用的多的话可以将加解密服务抽取出来单独去提供服务

主要提供两个接口，在service-api中有说明：

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