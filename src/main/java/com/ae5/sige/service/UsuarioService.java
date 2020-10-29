package com.ae5.sige.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ae5.sige.exception.UserNotFound;
import com.ae5.sige.exception.UsersNotFound;
import com.ae5.sige.model.Usuario;
import com.ae5.sige.repository.UsuarioRepositoryInt;



@Service("UsuarioServiceInt")
/**
 * @author ae5
 */
@Transactional

public class UsuarioService implements UsuarioServiceInt {
  /**
   * @author ae5
   */
  private UsuarioRepositoryInt userRepository;

  /**
   * @author ae5
   */
  @Autowired

  public UsuarioService(final UsuarioRepositoryInt userRepository) {

    this.userRepository = userRepository;

  }

  /**
   * @author ae5
   */
  public Usuario findByUsernusuario(final String nusuario) {
	
	  
    final Optional<Usuario> user = userRepository.findOne(nusuario);
    
    if (user.isPresent()) {	
	return user.get();
    } else {

        throw new UserNotFound(nusuario);

      }
    
    
  }

  /**
   * @author ae5
   */
  public List<Usuario> findAll() {

	
    final Optional<List<Usuario>> users = userRepository.findAll();

    if (users.isPresent()) {	
    	return users.get();
        }else {
        	throw new UsersNotFound();
        }
        
      
  }

  /**
   * @author ae5
   */
  public void saveUsuario(final Usuario usuario) {

    userRepository.saveUsuario(usuario);

  } 

  /**
   * @author ae5
   */
  public void updateUsuario(final Usuario user) {

    userRepository.updateUsuario(user);

  }

  /**
   * @author ae5
   */
  public void deleteUsuario(final String nusuario) {

    userRepository.deleteUsuario(nusuario);

  }

  @Override
  public Usuario getUserBynusuarioAndPassword(final String nusuario, final String password) {

    final Usuario usuario = userRepository.findByDniAndContrasena(nusuario, password);
    return usuario;
  }

}
