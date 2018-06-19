package danihwsr.tournee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{ 'nickName' : ?0, 'mail' : ?1 }")
    User exist(String nickName, String mail);
    User getByNickNameAndAndMail(String nickName, String mail);
    User getByNickName(String nickName);
    User getByMail(String mail);
}
