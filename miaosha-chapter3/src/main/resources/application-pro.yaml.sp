server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://152.136.145.182:3306/miaosha
    username: yangweiye_admin
    password: 1998yang
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    #    druid:
    #      max-active: 20
    #      initial-size: 5
    #      max-wait: 60000
    #      min-idle: 10
    #      pool-prepared-statements: true
    #      max-open-prepared-statements: 20

  redis:
    host: yangweiye.cn
    port: 6379
    password: 1998yang
    timeout: 2000

    jedis:
      pool:
        max-idle: 3
        min-idle: 1
        max-active: 5
        max-wait: 2000