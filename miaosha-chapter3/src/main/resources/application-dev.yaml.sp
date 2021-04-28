server:
  port: 80

spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/miaosha
    username: root
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
    host: 127.0.0.1
    port: 6379
    timeout: 10000

    jedis:
      pool:
        max-idle: 3
        min-idle: 1
        max-active: 5
        max-wait: 2000

rocketmq:
  name-server: 127.0.0.1:9876
  #  consumer:
  #    topic: orders_topic
  #    group: orders_consumer_group
  producer:
    #    customized-trace-topic: order_topic
    group: order_producer_group
    send-message-timeout: 100000