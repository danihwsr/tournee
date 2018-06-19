package danihwsr.tournee;

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

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(String id) throws UserNotFoundException {
        if ( this.userRepository.findById(id).isPresent() ) {
            return this.userRepository.findById(id).get();
        }

        String msg = String.format("User with id '%s' not found.", id);
        throw new UserNotFoundException(msg);
    }

    public User getUserByNickname(String nick) throws UserNotFoundException {
        User u = this.userRepository.getByNickName(nick);

        if ( u != null ) {
            return u;
        }

        String msg = String.format("User with nickname '%s' not found.", nick);
        throw new UserNotFoundException(msg);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> createUser(User user) throws UserAlreadyExistsException {

        if ( !this.exists( user ) ) {
            this.userRepository.save(user);
        }

        return this.userRepository.findAll();
    }

    public List<User> deleteUser(String id) {
        this.userRepository.deleteById(id);

        return this.userRepository.findAll();
    }

    public User updateUser(String id, User user) throws UserNotFoundException, UserAlreadyExistsException {

        if ( !this.exists(user) ) {
            if ( this.userRepository.findById(id).isPresent() ) {
                String msg = String.format("User with id '%s' not found.", id);
                throw new UserNotFoundException(msg);
            }
            try {
                upsert(this.userRepository.findById(id).get(), user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

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
                System.out.println("Key = " + elem.getKey() + ", Value = " + elem.getValue().toString() );
                try {
                    if ( !elem.getValue().invoke(base).equals(elem.getValue().invoke(update)) && elem.getValue().invoke(update) != null ) {
                        String setterKey = elem.getKey().replace("get", "set");
                        gas.get(setterKey).invoke(base, elem.getValue().invoke(update));
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        }

        this.userRepository.save(base);
    }

    private static String getFieldName(Method method)
    {
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

    private boolean exists(User user) throws UserAlreadyExistsException {

        if ( this.userRepository.getByNickName(user.getNickName()) != null ) {
            String msg = String.format("The name '%s' is already taken.", user.getNickName());
            throw new UserAlreadyExistsException(msg);
        } else if ( this.userRepository.getByMail(user.getMail()) != null ) {
            String msg = String.format("The mail address '%s' is already in use.", user.getMail());
            throw new UserAlreadyExistsException(msg);
        } else {
            return false;
        }

    }

}
