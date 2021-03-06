/*
 * (C) Copyright 2014-2016 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License (LGPL)
 * version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package org.kurento.tutorial.helloworld;

import org.kurento.client.KurentoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Hello World (WebRTC in loopback with recording) main class.
 *
 * @author Ivan Gracia (igracia@kurento.org)
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @since 6.2.1
 */
@SpringBootApplication
@EnableWebSocket
public class HelloWorldRecApp implements WebSocketConfigurer {

  @Bean
  public HelloWorldRecHandler handler() {
    return new HelloWorldRecHandler();
  }

  @Bean
  public KurentoClient kurentoClient() {
    return KurentoClient.create();
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(handler(), "/recording");
  }

  @Bean
  public UserRegistry registry() {
    return new UserRegistry();
  }

  public static void main(String[] args) throws Exception {
    new SpringApplication(HelloWorldRecApp.class).run(args);
  }
}
