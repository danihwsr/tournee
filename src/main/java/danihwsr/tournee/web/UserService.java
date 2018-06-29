package danihwsr.tournee.web;

import danihwsr.tournee.MailAlreadyExistsException;
import danihwsr.tournee.UserAlreadyExistsException;
import danihwsr.tournee.UserNotFoundException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

@Service
public class UserService {

    // UserDAO
    private UserRepository userRepository;
    // PasswordEncoder
    private PasswordEncoder passEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passEncoder = new BCryptPasswordEncoder();
    }

    public User getUserById(String id) throws UserNotFoundException {

        String exceptionMsg = String.format("User with id '%s' not found.", id);

        return this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(exceptionMsg)
        );

    }

    public User getUserByNickname(String nick) throws UserNotFoundException {

        String exceptionMsg = String.format("User with nickname '%s' not found.", nick);

        return this.userRepository.getByNickname(nick).orElseThrow(
                () -> new UserNotFoundException(exceptionMsg)
        );

    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(User user) throws
            UserAlreadyExistsException,
            MailAlreadyExistsException {

        if ( this.userRepository.getByNickname( user.getNickname() ).isPresent() ) {
            String message = String.format("The username '%s' is already taken.", user.getNickname());
            throw new UserAlreadyExistsException(message);
        }

        if ( this.userRepository.getByMail( user.getMail() ).isPresent() ) {
            String message = String.format("The mail address '%s' is already taken.", user.getMail());
            throw new MailAlreadyExistsException(message);
        }

        user.setPassword(passEncoder.encode(user.getPassword()));

        this.userRepository.save(user);

        return this.userRepository.getByNickname(user.getNickname()).get();
    }

    public User deleteUser(String id) throws UserNotFoundException {

        String exceptionMsg = String.format("User with id '%s' not found.", id);

        User deletedUser = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(exceptionMsg)
        );

        this.userRepository.deleteById(id);

        return deletedUser;
    }

    public User updateUser(String id, User user) throws
            UserNotFoundException,
            UserAlreadyExistsException,
            MailAlreadyExistsException {

        if ( this.userRepository.getByNickname( user.getNickname() ).isPresent() ) {
            String message = String.format("The username '%s' is already taken.", user.getNickname());
            throw new UserAlreadyExistsException(message);
        }

        if ( this.userRepository.getByMail( user.getMail() ).isPresent() ) {
            String message = String.format("The mail address '%s' is already taken.", user.getMail());
            throw new MailAlreadyExistsException(message);
        }

        if ( !this.userRepository.findById(id).isPresent() ) {
            String msg = String.format("User with id '%s' not found.", id);
            throw new UserNotFoundException(msg);
        }

        try {
            this.upsert(this.userRepository.findById(id).get(), user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return this.userRepository.findById(id).get();

    }

    private void upsert(User base, User update) throws Exception {
        User temp = new User();
        Class userClass = temp.getClass();

        Method[] methods = userClass.getDeclaredMethods();
        HashMap<String,Method> gas = new HashMap<>();

        for ( Method m : methods ) {
            if ( Modifier.isPublic( m.getModifiers()) && m.getParameterTypes().length == 0 ) {
                if ( m.getName().matches("^get[A-Za-z].*") && !m.getReturnType().equals(void.class) ) {
                    gas.put("get" + getFieldName(m), m);
                }
            } else if ( Modifier.isPublic( m.getModifiers() ) &&
                    m.getParameterTypes().length == 1 &&
                    m.getReturnType().equals(void.class) &&
                    m.getName().matches("^set[A-Za-z].*")
                    ) {
                gas.put("set" + getFieldName(m), m);
            }
        }

        for (Map.Entry<String,Method> elem : gas.entrySet() ) {
            if ( elem.getKey().contains("get") && !elem.getKey().contains("id")) {
                try {
                    if ( !elem.getValue().invoke( base ).equals( elem.getValue().invoke( update ) ) &&
                            elem.getValue().invoke( update ) != null &&
                            !elem.getValue().invoke( update ).equals( 0 )
                       ) {
                            //System.out.println("Key = " + elem.getKey() + ", Value = " + elem.getValue().toString() );
                            String setterKey = elem.getKey().replace("get", "set");
                            if ( setterKey.equals("setPassword") ) {
                                gas.get(setterKey).invoke(base, passEncoder.encode( (String) elem.getValue().invoke(update) ));
                            }
                            gas.get(setterKey).invoke(base, elem.getValue().invoke(update));
                         }
                } catch (Exception e) {
                    throw e;
                }
            }
        }

        this.userRepository.save(base);
    }

    private static String getFieldName(Method method) {
        try {
            Class<?> cl = method.getDeclaringClass();
            BeanInfo info = Introspector.getBeanInfo(cl);
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : props) {
                if( method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod()) ) {
                    return pd.getName();
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
