package org.fearandloathing.config

import org.springframework.context.annotation.Configuration
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.h2.server.web.WebServlet

@Configuration
class H2Config {

  @Bean
  def h2servletRegistration(): ServletRegistrationBean[WebServlet] = {
    val registrationBean = new ServletRegistrationBean(new WebServlet)
    registrationBean.addUrlMappings("/console/*")
    registrationBean
  }

}