package danihwsr.tournee.web;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{ 'nickName' : ?0, 'mail' : ?1 }")
    User exist(String nickName, String mail);
    User getByNicknameAndMail(String nickName, String mail);
    Optional<User> getByNickname(String nickName);
    Optional<User> getByMail(String mail);
}
