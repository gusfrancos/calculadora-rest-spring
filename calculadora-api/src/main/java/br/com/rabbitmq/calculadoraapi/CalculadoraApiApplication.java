package br.com.rabbitmq.calculadoraapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableRabbit
@SpringBootApplication
public class CalculadoraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculadoraApiApplication.class, args);
	}

}

//**** Atenção

//Não esquecer de instalar o pluging do lombok no eclipse, além de configurar as refêrencias
//https://dicasdejava.com.br/como-configurar-o-lombok-no-eclipse/

//Não esquecer de habilitar o campo: enable annotation processing
//https://docs.jboss.org/hibernate/validator/5.2/reference/en-US/html/ch12.html

/**
* @EnableAutoConfiguration 
* A anotação diz ao Spring Boot para "adivinhar" como você deseja configurar o Spring, 
* com base nas dependências do jar que você adicionou. Por exemplo, se o HSQLDB estiver 
* no seu caminho de classe e você não tiver configurado manualmente nenhum bean de conexão 
* com o banco de dados, o Spring configurará automaticamente um banco de dados na memória.
* 
* @ComponentScan 
* diz ao Spring para procurar outros componentes, configurações e serviços no pacote especificado. 
* O Spring é capaz de verificar automaticamente, detectar e registrar seus beans 
* ou componentes de um pacote de projeto predefinido. Se nenhum pacote for especificado, 
* o pacote de classe atual será considerado o pacote raiz.
* 
* @Component
* A annotation básica que indica que uma classe vai ser gerenciada pelo container do Spring. 
* Todas as annotations descritas acima são, na verdade, derivadas de @Component. 
*
* @SpringBootApplication
* Ela engloba a @Component, @ComponentScan e mais uma chamada @EnableAutoConfiguration, 
* que é utilizada pelo Spring Boot para tentar advinhar as configurações necessárias para rodar o seu projeto.
*
*/

