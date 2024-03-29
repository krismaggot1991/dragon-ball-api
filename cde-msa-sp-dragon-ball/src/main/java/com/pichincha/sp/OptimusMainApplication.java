package com.pichincha.sp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.status.StatusLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Hooks;

/**
 * Arquitectura tradicional para el recurso cde-msa-sp-dragon-ball extendiendo de la interfaz generada. <br/>
 * <b>Class</b>: PichinchaOptimusMainApplication<br/>
 * <b>Copyright</b>: &copy; 2024 Banco Pichincha<br/>
 *
 * @author Banco Pichincha <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 *
 * <li>Christian Muyon</li>
 *
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 *
 * <li>Mar 9, 2024 Creaci&oacute;n de Clase.</li>
 *
 * </ul>
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan("com.pichincha")
public class OptimusMainApplication {

  static {
    StatusLogger.getLogger().setLevel(Level.OFF);
    Hooks.enableAutomaticContextPropagation();
  }

  public static void main(String[] args) {
    SpringApplication.run(OptimusMainApplication.class, args);
  }
}