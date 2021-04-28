spring:
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    cache: false
    servlet:
      content-type: text/html
    enabled: true
    mode: HTML5

  cache:
    cache-names: redisCache
    type: redis
    redis:
      cache-null-values: true
      use-key-prefix: false
      key-prefix: demo_
#      time-to-live: 300000
  profiles:
    active: dev
mybatis:
  #这玩意不生效
  mapper-locations: classpath:com/yangweiye/miaosha/mapper/*Mapper.xml
  type-aliases-package: com.yangweiye.miaosha.pojo
  type-handlers-package: com.yangweiye.miaosha.TypeHandler
  configuration:
    map-underscore-to-camel-case: true