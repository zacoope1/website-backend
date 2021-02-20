package com.webservice.session;

import com.webservice.common.Constants;
import com.webservice.session.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "SELECT * FROM " + Constants.SESSION_TABLE_NAME + " WHERE session_uuid = :session_uuid", nativeQuery = true)
    public Session getSession(@Param("session_uuid") String sessionUuid);

}
