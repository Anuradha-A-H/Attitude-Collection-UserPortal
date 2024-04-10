package Attitude_Collection.AttitudeCollection.repository;

import Attitude_Collection.AttitudeCollection.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {


    Optional<Login> findByUserName(String name);
}
