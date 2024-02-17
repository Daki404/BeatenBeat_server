package daki.BeatenBeat;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    // createdAtTime : 네이밍 체크
    @CreatedDate
    private LocalDateTime createdTime;
    // updatedAtTime? : 네이밍 체크
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;
}
