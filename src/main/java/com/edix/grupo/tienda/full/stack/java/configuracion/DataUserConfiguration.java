
  package com.edix.grupo.tienda.full.stack.java.configuracion;
  /*
	import javax.sql.DataSource;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;

	@EnableWebSecurity

	@Configuration
	public class DataUserConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		private DataSource dataSource;

		
		 * PENDIENTE DE MODIFICAR, ESTÁ CON LOS PARÁMETROS DE LA APLICACIÓN
		 * 10_sboot_familias_jpa_security_1 , de Tomás

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
					.usersByUsernameQuery("select username, password, enabled from Usuarios where username=?")
					.authoritiesByUsernameQuery("select u.username, p.nombre from Usuario_Perfiles up "
							+ "inner join Usuarios u on u.username = up.username "
							+ "inner join Perfiles p on p.id_perfil = up.id_Perfil " + "where u.username = ?");
		}

	@Override protected void configure(HttpSecurity http) throws Exception { 
		http .csrf().disable() .authorizeRequests() 
		// Los directorios estáticos no requieren autenticacion 
		.antMatchers("/bootstrap/**", "/images/**", "/css/**", "js/**").permitAll()
		.antMatchers("/rest/demo-bcrypt/**").permitAll()
  
		// Las vistas públicas no requieren autenticación 
		.antMatchers("/", "/login", "/logout", "/registro","/search", "/app/producto/verUno/**").permitAll() 
		//  Las autorizaciones sobre urls para ROLES
		.antMatchers("/app/producto/**").hasAnyAuthority("ROLE_GESTOR",  "ROLE_ADMINISTRADOR")
		.antMatchers("/app/usuarios/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMINISTRADOR")
		.antMatchers("/app/perfiles/**").hasAnyAuthority("ROLE_ADMINISTRADOR")
		.antMatchers("/app/tipos/**").hasAnyAuthority("ROLE_GESTOR")
  
	  // Todas las demás URLs de la Aplicación requieren autenticación
	  .anyRequest().authenticated()   // El formulario de Login no requiere autenticacion 
	  .and().formLogin().permitAll() 
	  // El formulario de logout no requiere autenticacion  
	  .and().logout().permitAll();
	  
  }

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
	
	*/
