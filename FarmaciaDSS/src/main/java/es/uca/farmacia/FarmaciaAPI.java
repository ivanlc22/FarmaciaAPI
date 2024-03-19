package es.uca.farmacia;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import es.uca.farmacia.Swagger.SwaggerConfiguration;
import es.uca.farmacia.database.ClienteRepositorySpring;
import es.uca.farmacia.database.FarmaciaRepositorySpring;

import es.uca.farmacia.repositorio.FarmaRepository;
import es.uca.farmacia.repositorio.FarmaRepositoryDB;
import es.uca.farmacia.repositorio.FarmaRepositoryMemory;
import es.uca.farmacia.repositorio.ClienteRepository;
import es.uca.farmacia.repositorio.ClienteRepositoryMemory;
import es.uca.farmacia.repositorio.ClienteRepositoryDB;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class FarmaciaAPI implements WebMvcConfigurer
{
	@Autowired
	FarmaRepository farmarepo;
	@Autowired 
	ClienteRepository clienterepo;
	
    public static void main(String[] args) {
        SpringApplication.run(FarmaciaAPI.class, args);
    }
    
    // BASE DE DATOS
    
    /*
    @Bean
    public FarmaRepository farmarepo(FarmaciaRepositorySpring farmarepo) throws ClassNotFoundException, IOException
    {
    	return new FarmaRepositoryDB(farmarepo);
    }
    
    @Bean
    public ClienteRepository clienterepo(ClienteRepositorySpring clienterepo) 
    {
    	return new ClienteRepositoryDB(clienterepo);
    }
    */
  
    // MEMORIA
    
    @Bean
    public FarmaRepository farmarepo() throws ClassNotFoundException, IOException
    {
    	return new FarmaRepositoryMemory();
    }
    
    @Bean
    public ClienteRepository clienterepo() throws ClassNotFoundException, IOException 
    {
    	return new ClienteRepositoryMemory();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }
}