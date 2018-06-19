package danihwsr.tournee.web;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{ 'nickName' : ?0, 'mail' : ?1 }")
    User exist(String nickName, String mail);
    User getByNicknameAndMail(String nickName, String mail);
    User getByNickname(String nickName);
    User getByMail(String mail);
}
