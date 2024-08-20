package ru.yarilin.springcourse.Project3RestServer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project3RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project3RestServerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	//todo создать табличку наблюдателей, создать табличку свойств сенсоров с закрепленными наблюдателями// ДОБАВИЛ
	//todo добавить для этих табличек модели, сервисы и репозитории
}
