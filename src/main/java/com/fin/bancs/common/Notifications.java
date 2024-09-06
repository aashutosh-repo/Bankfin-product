package com.fin.bancs.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Notifications{
    @Id
	private String ntfctnId;
    private String title;
    private String message;
    private int isActive;

}
