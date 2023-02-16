


  package com.edix.grupo.tienda.full.stack.java.configuracion;
  
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


		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
<<<<<<< HEAD
			.usersByUsernameQuery("select username, contrasena, enabled from usuarios where username=?")
			.authoritiesByUsernameQuery("select u.username, p.nombre from usuario_rol ur inner join usuarios u on u.username = ur.username " +
=======
			.usersByUsernameQuery("select username, contrasena, enabled from Usuarios where username=?")
			.authoritiesByUsernameQuery("select u.username, p.nombre from Usuario_Rol ur inner join Usuarios u on u.username = ur.username " +
>>>>>>> fb0d05d10416bae0b7f320cadfec4826f46f8c7d
			"inner join Roles p on p.id_rol = ur.id_rol " +  "where u.username = ?");
			}

	@Override protected void configure(HttpSecurity http) throws Exception { 
		http .csrf().disable() 
		.authorizeRequests() 
		// Los directorios estáticos no requieren autenticacion 
		.antMatchers("/bootstrap/**", "/images/**", "/css/**", "js/**").permitAll()
		.antMatchers("/rest/demo-bcrypt/**").permitAll()
  
		// Las vistas públicas no requieren autenticación 
<<<<<<< HEAD
		.antMatchers("/", "/login", "/logout", "/registro","/search", "/detallesProducto/**","/registro").permitAll()
=======
		.antMatchers("/", "/login", "/logout", "/registro","/search", "/detallesProducto/**").permitAll()
>>>>>>> fb0d05d10416bae0b7f320cadfec4826f46f8c7d
		
		//  Las autorizaciones sobre urls para ROLES
			.antMatchers("/destacados").hasAnyAuthority("ROLE_CLIENTE",  "ROLE_ADMIN")
			.antMatchers("/oferta").hasAnyAuthority("ROLE_CLIENTE","ROLE_ADMIN")
			.antMatchers("/eliminarProducto/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/modificarProducto/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/modificarProducto").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/altaProducto").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/app/tipos/**").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/tarjeta/alta").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/tarjeta/verTarjeta/**").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/tarjeta/editar").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/tarjeta/editar/**").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/tarjeta/eliminar/**").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/direccion/alta").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/direccion/alta").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/direccion/verDireccion/**").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/direccion/editar").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/direccion/editar/**").hasAnyAuthority("ROLE_CLIENTE")
			.antMatchers("/direccion/eliminar/**").hasAnyAuthority("ROLE_CLIENTE")
  
	  // Todas las demás URLs de la Aplicación requieren autenticación
<<<<<<< HEAD
	  .anyRequest().authenticated().and().formLogin().permitAll()   
=======
	  .anyRequest().authenticated()   
>>>>>>> fb0d05d10416bae0b7f320cadfec4826f46f8c7d
	  // El formulario de Login no requiere autenticacion 
	  .and().formLogin().permitAll() 
	  // El formulario de logout no requiere autenticacion  
	  .and().logout().permitAll();
	  
  }

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
	}
