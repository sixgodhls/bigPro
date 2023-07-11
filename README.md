# bigPro
springboot+vue3 商城项目
## Day1
写的时候改了一下以往的编写方式  
1.不使用@Mapper 直接在启动类上加mapper扫描dao包  
2.使用private static代替@AutoWired  
然后用swagger测试接口的时候发现service层实现类mapper注入不了，导致空指针。
最后使用@Mapper解决问题。
